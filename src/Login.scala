import scala.swing._
import java.awt.Color
import javax.swing.colorchooser.CenterLayout
import javax.swing.ImageIcon
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import scala.swing.event.ButtonClicked
import java.sql.Connection

object Login extends SimpleSwingApplication {

  val top: Frame = new MainFrame {
    title = "Login DreamHome"
    background_=(Color.BLACK)
    val label = new Label("Usuario") {
      background_=(Color.gray)
    }
    val textUsuario = new TextField("") {
      size_=(new Dimension(30, 60))
      tooltip = "password"

    }

    val textPass = new PasswordField("") {
      size_=(new Dimension(50, 50))
    }
    contents = new BoxPanel(Orientation.Vertical) {
      size_=(new Dimension(800, 800))
      contents += new Label("Inicio de sesion")
      //val imagen=toolkit.getImage("/Imagenes/logo.jpg")
      contents += new ImagePanel {
        imagePath = ("Imagenes/logo.png")
        size_=(new Dimension(100, 100))
      }

      val grid = new GridPanel(7, 2) {
        contents += new Label("						")
        contents += new Label("						")
        contents += label
        contents += textUsuario
        contents += new Label("						")
        contents += new Label("						")
        contents += new Label("Contraseña")
        contents += textPass
        contents += new Label("						")
        contents += new Label("						")
        contents += new Button("Registro") {
          background_=(Color.lightGray)
          //action
        }
        val btnIniciarSesion = new Button("Iniciar sesion") {
          background_=(Color.CYAN)
          
        }
        contents += btnIniciarSesion
        listenTo(btnIniciarSesion)
        contents += new Label("						")
        contents += new Label("						")
        
        reactions += {
          case ButtonClicked(component) if component == btnIniciarSesion =>
        val pass = textPass.peer.getText().toString()
        val user = textUsuario.text.toString()
        val us =new Usuario(0,user, pass)
     validarusuario(us)  
        }
      }
      contents += grid
      grid.hGap_=(2)
      grid.vGap_=(3)
    }

    centerOnScreen()
  }
  top.size_=(new Dimension(335, 600))

  def validarusuario(user:Usuario){
    
    val conn:Connection = conexion.getConexion()
    val statement = conn.createStatement
    val pass=user.getpass()
    val usuario=user.getUsuario()
    val rs = statement.executeQuery("Select * from login where username = '"+usuario+"' AND password ='"+pass+"'")
    
    try{
    if(rs.next){
      
    }else{
      Dialog.showMessage(top, "Wrong username or password!", "Login Error", Dialog.Message.Error)
      
    }
    }catch{
       case e: Exception => e.printStackTrace
    }
    
  }
  //val auth = new LoginDialog().auth.getOrElse(throw new IllegalStateException("You should login!!!"))
}

case class Auth(userName: String, password: String)
//
//class LoginDialog extends Dialog {
//  var auth: Option[Auth] = None
//  val userName = new TextField
//  val password = new PasswordField
//
//  title = "Login"
//  modal = true
//
//  contents = new BorderPanel {
//    layout(new BoxPanel(Orientation.Vertical) {
//      border = Swing.EmptyBorder(5,5,5,5)
//
//      contents += new Label("User Name:")
//      contents += userName
//      contents += new Label("Password:")
//      contents += password
//    }) =
//
//    layout(new FlowPanel(FlowPanel.Alignment.Right)(
//      Button("Login") {
//        if (makeLogin()) {
//          auth = Some(Auth(userName.text, password.text))
//          close()
//        } else {
//          Dialog.showMessage(this, "Wrong username or password!", "Login Error", Dialog.Message.Error)
//        }
//      }
//    ))
//  }
//
//  def makeLogin() = true // here comes you login logic
//
//  centerOnScreen()
//  open()
//}



class ImagePanel extends Panel {
  private var _imagePath = ""
  private var bufferedImage: BufferedImage = null

  def imagePath = _imagePath

  def imagePath_=(value: String) {
    _imagePath = value
    bufferedImage = ImageIO.read(new File(_imagePath))
  }

  override def paintComponent(g: Graphics2D) =
    {
      if (null != bufferedImage) g.drawImage(bufferedImage, 0, 0, null)
    }
}

object ImagePanel {
  def apply() = new ImagePanel()
} 