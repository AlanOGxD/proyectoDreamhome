import scala.swing._
import java.awt.Color
import javax.swing.colorchooser.CenterLayout
import javax.swing.ImageIcon
import java.awt.image.BufferedImage
import java.io.File                                                           
import javax.imageio.ImageIO  

object Login extends SimpleSwingApplication{
   
   val top: Frame = new MainFrame{
    title="Login DreamHome"
    background_=(Color.BLACK)
   val label = new Label("Usuario"){
     background_=(Color.gray)
    }
   val textUsuario = new TextField(""){
    size_=(new Dimension(30,60))
    background
    
    }
   
   val textPass = new TextField(""){
     size_=(new Dimension(50, 50))
   }
   contents= new BoxPanel(Orientation.Vertical){
     size_=(new Dimension(800, 800))
     contents += new Label("Inicio de sesion")
     //val imagen=toolkit.getImage("/Imagenes/logo.jpg")
     contents += new ImagePanel{
       imagePath = ("Imagenes/logo.png")
       size_=(new Dimension(100,100))
         }
       val grid = new GridPanel(7, 2){
        contents +=new Label("						")
       contents +=new Label("						")
       contents += label
       contents += textUsuario 
       contents +=new Label("						")
        contents +=new Label("						")
       contents+=new Label("Contraseña")
       contents += textPass
      contents +=new Label("						")
        contents +=new Label("						")
       contents += new Button("Registro"){
         background_=(Color.lightGray)
         //action
       }
       contents += new Button("Iniciar sesion"){
         background_=(Color.CYAN)
         //action
       }
       contents +=new Label("						")
       contents +=new Label("						")
   }
       contents += grid  
       grid.hGap_=(2)
       grid.vGap_=(3)
       }
     
    
    centerOnScreen()  
} 
   top.size_=(new Dimension(300,550))
   



}

class ImagePanel extends Panel                                                
{                                                                             
  private var _imagePath = ""                                                 
  private var bufferedImage:BufferedImage = null                              

  def imagePath = _imagePath                                                  

  def imagePath_=(value:String)                                               
  {                                                                           
    _imagePath = value                                                        
    bufferedImage = ImageIO.read(new File(_imagePath))                        
  }                                                                           


  override def paintComponent(g:Graphics2D) =                                 
  {                                                                           
    if (null != bufferedImage) g.drawImage(bufferedImage, 0, 0, null)         
  }                                                                           
}                                                                             

object ImagePanel                                                             
{                                                                             
  def apply() = new ImagePanel()                                              
} 