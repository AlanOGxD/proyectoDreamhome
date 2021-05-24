

class Usuario (private var iduser:Integer,private var usuario:String,private var password:String){
  def getId()=iduser
  def getUsuario()=usuario
  def getpass()=password
  
  def setUsuario(user:String){usuario=user}
  def setPass(pass:String){password=pass}
  
}