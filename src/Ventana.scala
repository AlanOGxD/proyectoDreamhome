
import scala.swing._
import scala.swing.event.TableRowsSelected
import swing.event.{ TableRowsSelected, TableEvent, TableColumnsSelected, ButtonClicked }
import java.sql.Connection
import scala.collection.mutable.ListBuffer
import java.awt.{Component => AWTComponent}

object Ventana extends SimpleSwingApplication {

  ///  val textArea = new JTextArea
  /// val scrollPane = new JScrollPane(textArea)
  val top: Frame = new MainFrame {
    title = "Propiedades"

    centerOnScreen()
    contents = box
  }
  top.size_=(new Dimension(1500, 600))

  val cajabuscar = new TextField() {}
  val btnBuscar = new Button() {} //*posible cambio a key listener
  val btnCerrarSesion = new Button() {} //No es seguro
  val btnAgregar = new Button() {}

  lazy val box = new BoxPanel(Orientation.Vertical) {
    val output = new TextArea(6, 40) { editable = false }
    val headers = Array("NumPropiedad", "Street", "city", "codigo postal", "tipo", "habitaciones", "renta", "Propietario", "Personal", "Sucursal", "Gen reporte", "Eliminar", "Editar")

    //val rowData = Array("a")
    
     val conn:Connection = conexion.getConexion()
    val statement = conn.createStatement
    val rs = statement.executeQuery("Select * from login")
    
    var listapropiedad=new ListBuffer[Propiedades]
    
    listapropiedad=PropiedadDao.All()
    
     var rowData = Array.ofDim[Any](listapropiedad.length, 13)
val btnEditar=new Button("eliminar")
    
  for (i <- 0 until listapropiedad.length) {
    rowData(i)(0) = listapropiedad(i).getPropertyNo()
    rowData(i)(1) = listapropiedad(i).getstreet()
    rowData(i)(2) = listapropiedad(i).getcity()
    rowData(i)(3) = listapropiedad(i).getpostcode()
    rowData(i)(4) = listapropiedad(i).gettypes()
    rowData(i)(5) = listapropiedad(i).getrooms()
    rowData(i)(6) = listapropiedad(i).getrent()
    rowData(i)(7) = listapropiedad(i).getpropietario()
    rowData(i)(8) = listapropiedad(i).getpersonal()
    rowData(i)(9) = listapropiedad(i).getsucuarsal()
    rowData(i)(10) = new Button("Reporte").peer 
    rowData(i)(11) = btnEditar.visible=true
    rowData(i)(12) = new Button("Editar")
    //rowData(i).size_= size.setSize(40,80)
    
  }
    val table = new Table(rowData, headers) {
      
      selection.elementMode = Table.ElementMode.Cell
      //selection.intervalMode = Table.IntervalMode.Single

      val header = {
        import java.awt.event.{ MouseEvent, MouseAdapter }

        val makeHeaderEvent = TableColumnHeaderSelected(this, _: Int)
        val tableHeader = peer.getTableHeader
        tableHeader.addMouseListener(new MouseAdapter() {
          override def mouseClicked(e: MouseEvent) {
            selection.publish(makeHeaderEvent(tableHeader.columnAtPoint(e.getPoint)))
          }
        })
        tableHeader
      }
    }

    val scrtable = new ScrollPane(table)
    val scrout = new ScrollPane(output)
    contents += scrtable
    contents += scrout
    listenTo(table.selection)

    reactions += {
      case TableRowsSelected(source, range, false) =>
        outputSelection(source, "Rows selected, changes: %s" format range)
      case TableColumnsSelected(source, range, false) =>
        outputSelection(source, "Columns selected, changes: %s" format range)
      case TableColumnHeaderSelected(source, column) =>
        outputSelection(source, "Column header %s selected" format column)
      case e => println("%s => %s" format (e.getClass.getSimpleName, e.toString))
    }
    def outputSelection(table: Table, msg: String) {
      val rowId = table.selection.rows.leadIndex
      val colId = table.selection.columns.leadIndex
      val rows = table.selection.rows.mkString(", ")
      val cols = table.selection.columns.mkString(", ")
      output.append("%s\n  Lead: %s, %s; Rows: %s; Columns: %s\n" format (msg, rowId, colId, rows, cols))
    }
  }

  def setSystemLookAndFeel() {
    import javax.swing.UIManager
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName)
  }

}

case class TableColumnHeaderSelected(override val source: Table, column: Int) extends TableEvent(source)
