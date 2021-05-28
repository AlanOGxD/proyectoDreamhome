
import java.sql.Connection
import scala.collection.mutable.ListBuffer
import scala.swing.Dialog

object PropiedadDao{
  val conn:Connection = conexion.getConexion()
  def main(args: Array[String]): Unit = {
    
  }
  
  def baja(no:String):Unit={
    val statement2 = conn.createStatement
      val rs2 = statement2.execute("delete from propertyforrent where PropertyNo = '"+no+"'")
  }
  
  def All(): ListBuffer[Propiedades]={
    var propiedadesx = new ListBuffer[Propiedades]()
    val conn:Connection = conexion.getConexion()
    val statement = conn.createStatement
    val rs = statement.executeQuery("Select * from propertyforrent")
    while(rs.next){
      val NoProperty=rs.getString("PropertyNo")
      val street = rs.getString("street")
      val city = rs.getString("city")
      val postcode = rs.getString("postcode")
      val types = rs.getString("type")
      val rooms = rs.getByte("rooms")
      val rent = rs.getInt("rent")
      val noOwner = rs.getString("FK_ownerNo")
      val noStaff = rs.getString("FK_staffNo")
      val nobranch = rs.getString("FK_branchNo")
      
      val statement2 = conn.createStatement
      val rs2 = statement2.executeQuery("Select * from privateowner where OwnerNo = '"+noOwner+"'")
      var propietario=""
     
      while(rs2.next) {propietario=rs2.getString("fName")}
      
      
      val statement3 = conn.createStatement
      val rs3 = statement3.executeQuery("Select * from staff where StaffNo='"+noStaff+"'")
      var personal=""
      while(rs3.next) {personal=rs3.getString("fName")}
            
      val statement4 = conn.createStatement
      val rs4 = statement4.executeQuery("Select * from branch where branchNo='"+nobranch+"'")
      var sucursal=""
     while(rs4.next) {sucursal = rs4.getString("Street")}
      
      
      val pr = new Propiedades(NoProperty, street, city, postcode, types, rooms, rent, propietario, personal, sucursal)
      propiedadesx.+=(pr)
    }
    return propiedadesx
  }
  
  
  def propietarionames(): ListBuffer[String]={
     val conn:Connection = conexion.getConexion()
    val statement2 = conn.createStatement
      val rs2 = statement2.executeQuery("Select fName from privateowner")
      var propietario=new ListBuffer[String]()
     
      while(rs2.next) {
        propietario += rs2.getString("fName")
        }
     return propietario
  }
  
  def staffnames(): ListBuffer[String]={
     val conn:Connection = conexion.getConexion()
    val statement2 = conn.createStatement
      val rs2 = statement2.executeQuery("Select fName from staff")
      var staff=new ListBuffer[String]()
     
      while(rs2.next) {
        staff += rs2.getString("fName")
        }
     return staff
  }
  
  def branchnames(): ListBuffer[String]={
     val conn:Connection = conexion.getConexion()
    val statement2 = conn.createStatement
      val rs2 = statement2.executeQuery("Select Street from branch")
      var branch=new ListBuffer[String]()
     
      while(rs2.next) {
        branch += rs2.getString("Street")
        }
     return branch
  }
  
  def buscarPro(nopro:String):ListBuffer[Propiedades]={
    var propiedadesx = new ListBuffer[Propiedades]()
    val conn:Connection = conexion.getConexion()
    val statement = conn.createStatement
    val rs = statement.executeQuery("Select * from propertyforrent where PropertyNo = '"+nopro+"'")
    while(rs.next){
      val NoProperty=rs.getString("PropertyNo")
      val street = rs.getString("street")
      val city = rs.getString("city")
      val postcode = rs.getString("postcode")
      val types = rs.getString("type")
      val rooms = rs.getByte("rooms")
      val rent = rs.getInt("rent")
      val noOwner = rs.getString("FK_ownerNo")
      val noStaff = rs.getString("FK_staffNo")
      val nobranch = rs.getString("FK_branchNo")
      
      val statement2 = conn.createStatement
      val rs2 = statement2.executeQuery("Select * from privateowner where OwnerNo = '"+noOwner+"'")
      var propietario=""
     
      while(rs2.next) {propietario=rs2.getString("fName")}
      
      
      val statement3 = conn.createStatement
      val rs3 = statement3.executeQuery("Select * from staff where StaffNo='"+noStaff+"'")
      var personal=""
      while(rs3.next) {personal=rs3.getString("fName")}
            
      val statement4 = conn.createStatement
      val rs4 = statement4.executeQuery("Select * from branch where branchNo='"+nobranch+"'")
      var sucursal=""
     while(rs4.next) {sucursal = rs4.getString("Street")}
      
      
      val pr = new Propiedades(NoProperty, street, city, postcode, types, rooms, rent, propietario, personal, sucursal)
      propiedadesx.+=(pr)
    }
    return propiedadesx
  }
  
  def cambios(P:String , S:String, C:String, PC:String, T:String, RS:Integer, R:Integer, PN:String, SN:String, BN:String):Unit={
    val st = conn.createStatement
    val st2 = conn.createStatement
    val st3 = conn.createStatement
    
    val sql = "Select * from privateowner where fName = '"+PN+"'"
    val sql2 = "Select * from staff where fName = '"+SN+"'"
    val sql3 = "Select * from branch where Street = '"+BN+"'"
    val p = "P"+C.charAt(0)+P
    val fp = st.executeQuery(sql)
    val fs = st2.executeQuery(sql2) 
    val fb = st3.executeQuery(sql3) 
    
    var FS =""
    var FB =""
    var FP =""
    while(fp.next()){ FP = fp.getString("OwnerNo")}
    while(fs.next()){ FS=fs.getString("StaffNo")}
    while(fb.next()){ FB=fb.getString("branchNo")}
    println()
    println(p+" "+S+" "+C+" "+PC)
    println(T)
    println(R)
    println(RS)
    println(FP)
    println(FS+" "+FB)
    val st4 = conn.prepareStatement("UPDATE propertyforrent SET street=?, city=?, postcode=? , type =?, rooms=?, rent=?, FK_ownerNo=?, FK_staffNo=?, FK_branchNo=? where PropertyNo = ?;")
   st4.setString(1, S)
   st4.setString(2, C)
   st4.setString(3, PC)
   st4.setString(4, T)
   st4.setInt(5, RS)
   st4.setInt(6, R)
   st4.setString(7, FP)
   st4.setString(8, FS)
   st4.setString(9, FB)
   st4.setString(10, P)
   
   
    //val sqlf="UPDATE propertyforrent SET street='"+S+"', city='"+C+"', postcode='"+PC+"', type ='"+T+"', rooms='"+RS+"', rent='"+R+"', FK_ownerNo='"+FP+"', FK_staffNo='"+FS+"', FK_branchNo='"+FB+"' where PropertyNo = '"+p+"'"
      //val rs2 = st4.executeUpdate()
     try{
       val rs2 = st4.executeUpdate()
       if(rs2>0){
        Dialog.showMessage(Ventana.top, "El registro se realizó con exito!", "Cambios realizados", Dialog.Message.Info)
      }else{
        Dialog.showMessage(Ventana.top, "Error al realizar Cambios", "Cambios Error", Dialog.Message.Info)
      }
     }catch{
        case e: Exception => e.printStackTrace
     }
      
  }
  
  def altas(P:String , S:String, C:String, PC:String, T:String, RS:Integer, R:Integer, PN:String, SN:String, BN:String):Unit={
    val st = conn.createStatement
    val st2 = conn.createStatement
    val st3 = conn.createStatement
    val st4 = conn.createStatement
    val sql = "Select * from privateowner where fName = '"+PN+"'"
    val sql2 = "Select * from staff where fName = '"+SN+"'"
    val sql3 = "Select * from branch where Street = '"+BN+"'"
    val p = "P"+C.charAt(0)+P
    val fp = st.executeQuery(sql)
    val fs = st2.executeQuery(sql2) 
    val fb = st3.executeQuery(sql3) 
    
    var FS =""
    var FB =""
    var FP =""
    while(fp.next()){
       FP = fp.getString("OwnerNo")
       println(FP)
    }
    while(fs.next()){ FS=fs.getString("StaffNo")}
    while(fb.next()){ FB=fb.getString("branchNo")}
  
   println(FS)
   println(FB)
   val sqlf="Insert into propertyforrent (PropertyNo, street, city, postcode, type, rooms, rent, FK_ownerNo, FK_staffNo, FK_branchNo) values('"+p+"', '"+S+"','"+C+"','"+PC+"','"+T+"','"+RS+"','"+R+"','"+FP+"','"+FS+"','"+FB+"')"
      val rs2 = st4.execute(sqlf)
      
      if(rs2.!=(null)){
        Dialog.showMessage(Ventana.top, "El registro se realizó con exito!", "Alta realizada", Dialog.Message.Info)
      }else{
        Dialog.showMessage(Ventana.top, "Error al realizar alta", "Alta Error", Dialog.Message.Info)
      }
  }

}
/*object Propiedad {
  def All(): ListBuffer[Propiedades]={
    var propiedades = new ListBuffer[Propiedades]()
    
    val conn = new conexion.connection
    val statement = connection.createStatement
    val rs = statement.executeQuery("Select * from branch")
    while(rs.next){
      val branch=rs.getString("branchNo")
      //    val street = rs.getString("Street")
      //    val city = rs.getString("City")
      //   val postCode = rs.getString("PostCode")
      val pr = new Propiedad
      val p1 = pr.Propiedades
      val p1=new PropiedadDAO
    }
  }
}*/