
import java.sql.Connection
import scala.collection.mutable.ListBuffer

object PropiedadDao{
  def main(args: Array[String]): Unit = {
    print("dawfed")
    
    All()
  }
  
  def All(): ListBuffer[Propiedades]={
    var propiedadesx = new ListBuffer[Propiedades]()
    val conn:Connection = conexion.getConexion()
    print("CONNNCDS "+conn)
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
      println("o= "+propietario)
      println("p= "+personal)
      println("s= "+sucursal)
      
      val pr = new Propiedades(NoProperty, street, city, postcode, types, rooms, rent, propietario, personal, sucursal)
      propiedadesx.+=(pr)
    }
    return propiedadesx
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