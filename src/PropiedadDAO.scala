import java.sql.Connection
import scala.collection.mutable.ListBuffer

object PropiedadDAO{
  def main(args: Array[String]): Unit = {
    print("dawfed")
    val conn:Connection = conexion.getConexion()
    
    val statement = conn.createStatement
    val rs = statement.executeQuery("Select * from propertyforrent")
    while(rs.next){
      val NoProperty=rs.getString("PropertyNo")
      val street = rs.getString("street")
      
      
      println("s"+street)
      
    }
  }
  
  def All(): ListBuffer[Propiedades]={
    var propiedadesx = new ListBuffer[Propiedades]()
    val conn:Connection = conexion.getConexion()
    val statement = conn.createStatement
    val rs = statement.executeQuery("Select * from propertyforrent")
    while(rs.next){
      val NoProperty=rs.getString("PropertyNo")
      val street = rs.getString("street")
      
      
      println("s"+street)
      val pr = new Propiedades(NoProperty, street, )
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