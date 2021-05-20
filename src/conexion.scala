

import java.sql.{Connection,DriverManager}

object conexion extends App {
  
     
   
   private var connection:Connection = null

  private def conexion(): Unit = {
    // connect to the database named "mysql" on port 8889 of localhost
    val url = "jdbc:mysql://localhost/dreamhome"
    val driver = "com.mysql.cj.jdbc.Driver"
    val username = "root"
    val password = "alanxD"
    var connection:Connection = null
    try {
        Class.forName(driver)
        connection = DriverManager.getConnection(url, username, password)
             val statement = connection.createStatement
        val rs = statement.executeQuery("Select * from branch")
        while(rs.next){
          val branch=rs.getString("branchNo")
          val street = rs.getString("Street")
          val city = rs.getString("City")
         val postCode = rs.getString("PostCode")
          println("branch = %s, Street = %s, City = %s, Post= %s".format(branch, street, city, postCode))
        }
        println("Conexion realizada")
    } catch {
        case e: Exception => e.printStackTrace
    }
    connection.close
   }
   
   def getConexion():Connection={
  if(connection==null) conexion()
  
    return connection
}
   
   
}

/*
import java.sql.{Connection, DriverManager}

class conexion{
    private var connection:Connection = null

  private def conexion(): Unit = {

    //val url = "localhost";
    val username = "root"
    val pass = "alanxD"
    val db = "dreamhome"
    val url = "jdbc:mysql://localhost/dreamhome"
    val driver = "com.mysql.cj.jdbc.Driver"
   
    try {
        Class.forName (driver)
        connection = DriverManager.getConnection (url, username, pass)
      // val statement = connection.createStatement
      //  val rs = statement.executeQuery("Select * from branch")
      //  while(rs.next){
      //    val branch=rs.getString("branchNo")
      //    val street = rs.getString("Street")
      //    val city = rs.getString("City")
      //   val postCode = rs.getString("PostCode")
       //   println("branch = %s, Street = %s, City = %s, Post= %s".format(branch, street, city, postCode))
       // }
        println("Conexion realizada")
    } catch {
      case e: Exception => e.printStackTrace;
    }
    }

 def getConexion():Connection={
  if(connection==null) conexion()
  
    return connection
}
}

object prueba extends App{
  def main(args: Array[String]): Unit = {
    var connection:Connection = conexion.getConexion(connection)
  }
}*/