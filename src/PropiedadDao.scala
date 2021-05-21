
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
      
      
      
      println("s"+street)
      val pr = new Propiedades(NoProperty, street, city, postcode, types, rooms, rent, noOwner, noStaff, nobranch)
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