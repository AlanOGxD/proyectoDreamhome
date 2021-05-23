import scala.swing._
import java.awt.Color


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
     size_=(new Dimension(50, 200))
   }
     contents = new GridPanel(7, 2){
        contents +=new Label("						")
       contents +=new Label("Inicio de sesion")
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
    
    centerOnScreen()  
} 
   top.size_=(new Dimension(300,400))
   
    
    
   
}