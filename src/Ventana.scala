
import scala.swing._
import scala.swing.event.TableRowsSelected
import swing.event.{ TableRowsSelected, TableEvent, TableColumnsSelected, ButtonClicked }
import java.sql.Connection
import scala.collection.mutable.ListBuffer
import java.awt.{ Component => AWTComponent }
import java.awt.Dimension
import java.awt.event._
import scala.swing.event.KeyTyped
import javax.swing.plaf.basic.ComboPopup

object Ventana extends SimpleSwingApplication {

  ///  val textArea = new JTextArea
  /// val scrollPane = new JScrollPane(textArea)
  val top: Frame = new MainFrame {
    title = "Propiedades"

    centerOnScreen()
    contents = box
  }
  top.centerOnScreen()
  top.size_=(new Dimension(1500, 600))

  //val cajabuscar = new TextField() {}
  //val btnBuscar = new Button() {} //*posible cambio a key listener
  val btnCerrarSesion = new Button() {} //No es seguro
  val btnAgregar = new Button() {}

  lazy val box = new BoxPanel(Orientation.Vertical) {
    
    val output = new TextArea(6, 40) { editable = false }
    val headers = Array("NumPropiedad", "Street", "city", "codigo postal", "tipo", "habitaciones", "renta", "Propietario", "Personal", "Sucursal")

    //val rowData = Array("a")

    //val conn:Connection = conexion.getConexion()
    //val statement = conn.createStatement
    //val rs = statement.executeQuery("Select * from login")

    var listapropiedad = new ListBuffer[Propiedades]

    listapropiedad = PropiedadDao.All()

    var rowData = Array.ofDim[Any](listapropiedad.length, 13)
    

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
      //rowData(i)(10) = new Button("Reporte").peer
      //rowData(i)(11) = btnEditar.visible = true
      //rowData(i)(12) = new Button("Editar")
      //rowData(i).size_= size.setSize(40,80)

    }
    val table = new Table(rowData, headers) {
      preferredSize_=(new Dimension(1400, 300))

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

    val scrtable = new ScrollPane(table){
preferredSize_=(new Dimension(1400, 300))
    }
    val scrout = new ScrollPane(output)
    val TFBusqueda = new TextField("") {
      //size_=(new Dimension(30, 60))
      tooltip = "Ingresa la busqueda"

    }

    contents += TFBusqueda
    contents += scrtable
    listapropiedad = PropiedadDao.All()

    var rowData2 = Array.ofDim[Any](listapropiedad.length, 13)
    val headers2 = Array("NumPropiedad", "Street", "city", "codigo postal", "tipo", "habitaciones", "renta", "Propietario")

    for (i <- 0 until listapropiedad.length) {
      rowData2(i)(0) = listapropiedad(i).getPropertyNo()
      rowData2(i)(1) = listapropiedad(i).getstreet()
      rowData2(i)(2) = listapropiedad(i).getcity()
      rowData2(i)(3) = listapropiedad(i).getpostcode()
      rowData2(i)(4) = listapropiedad(i).gettypes()
      rowData2(i)(5) = listapropiedad(i).getrooms()
      rowData2(i)(6) = listapropiedad(i).getrent()
      rowData2(i)(7) = listapropiedad(i).getpropietario()
       }
    
      table.updateCell(_, _)
   
     val txtProperty = new TextField {
      columns = 10
        tooltip = "Ingresa el numero de propiedad"
        listenTo(keys)
      reactions += { case e: KeyTyped =>
        if (!e.char.isDigit) e.consume
      }
      }
      val txtStreet = new TextField {
        columns = 10
        tooltip = "Ingresa la dirección"
      }
      val txtcity = new TextField {
        columns = 10
        tooltip = "Ingresa la ciudad"
        listenTo(keys)
      reactions += { case e: KeyTyped =>
        if (e.char.isDigit) e.consume
      }
      }
      val txtpostcode = new TextField{
        columns = 10
        tooltip = "Ingresa el codigo postal"
      }
      val txttype = new TextField {
        columns = 10
        tooltip = "Ingresa el tipo"
      }
      val txtrooms = new TextField {
        columns = 10
        tooltip = "Ingresa el numero de habitaciones"
        listenTo(keys)
      reactions += { case e: KeyTyped =>
        if (!e.char.isDigit) e.consume
      }
      }
      val txtrent = new TextField {
        columns = 10
        tooltip = "Ingresa el costo de la renta"
        listenTo(keys)
      reactions += { case e: KeyTyped =>
        if (!e.char.isDigit) e.consume
      }
      }
      
      val btnEliminar = new Button("Eliminar"){
        
      }
      
      val btnModificar = new Button("Modificar"){
        
      }
      
      val btnAgregar = new Button("Agregar"){
        
      }
      
      val btnLimpiar = new Button("Limpiar"){
        
      }
      

      val itemsOwner = PropiedadDao.propietarionames()
      val itemsStaff = PropiedadDao.staffnames()
      val itemsBranch = PropiedadDao.branchnames()

            
      val combotipo = new ComboBox(List("HOUSE", "FLAT")) {}
      
      val comboOwner = new ComboBox(itemsOwner) {}

      val comboStaff = new ComboBox(itemsStaff) {}

      val comboBranch = new ComboBox(itemsBranch) {}
    contents += new BoxPanel(Orientation.Vertical) {
     

      contents += new FlowPanel {
        contents += new Label("						")
        contents += new Label("						")
      }
      contents += new FlowPanel {
        contents += new Label("número de propiedad")
        contents += txtProperty
      }
      contents += new FlowPanel {
        contents += new Label("dirección")
        contents += txtStreet
      }
      contents += new FlowPanel {
        contents += new Label("ciudad")
        contents += txtcity
      }
      contents += new FlowPanel {
        contents += new Label("codigo postal")
        contents += txtpostcode
      }
      contents += new FlowPanel {
        contents += new Label("tipo de propiedad")
        contents += combotipo
      }
      contents += new FlowPanel {
        contents += new Label("habitaciones")
        contents += txtrooms
      }
      contents += new FlowPanel {
        contents += new Label("renta")
        contents += txtrent
      }
      contents += new FlowPanel {
        contents += new Label("Propietario")
        contents += comboOwner
      }
      contents += new FlowPanel {
        contents += new Label("Personal")
        contents += comboStaff
      }
      contents += new FlowPanel {
        contents += new Label("Sucursal")
        contents += comboBranch
      }
      
      contents += new FlowPanel {
        contents += btnAgregar
        contents += btnEliminar
        contents += btnModificar
        contents += btnLimpiar
      }
      
      def actualizar_tabla(){}
      
      listenTo(btnAgregar)
      reactions += {
          case ButtonClicked(component) if component == btnAgregar =>
            val PropertyNo = txtProperty.text
            
            
            
        }
    }
    contents += scrout
    listenTo(table.selection)
    

    reactions += {
      case TableRowsSelected(source, range, false) =>
        outputSelection(source, "Rows selected, changes: %s" format range)
        for (j <- 0 to rowData.length){
          print("ds: "+ rowData.toSeq.toString())
        }
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
      val propiedadno = rowData(rowId)(0)
      val street = rowData(rowId)(1)
      val ciudad = rowData(rowId)(2)
      val cpostal = rowData(rowId)(3)
      val tipo = rowData(rowId)(4)
      val habitaciones = rowData(rowId)(5)
      val renta = rowData(rowId)(6)
      val propietario = rowData(rowId)(7)
      
      val lista = PropiedadDao.buscarPro(propiedadno.toString())
      txtProperty.text_=(lista(0).getPropertyNo())
      txtStreet.text_=(lista(0).getstreet())
      txtcity.text_=(lista(0).getcity())
      txtpostcode.text_=(lista(0).getpostcode())
      combotipo.selection.item_=(lista(0).gettypes())
      txtrooms.text_=(lista(0).getrooms().toString())
      txtrent.text_=(lista(0).getrent().toString())
      comboOwner.selection.item_=(lista(0).getpropietario())
      comboStaff.selection.item_=(lista(0).getpersonal())
      comboBranch.selection.item_=(lista(0).getsucuarsal())
      
      //output.append("%s\n  Lead: %s, %s; Rows: %s; Columns: %s\n" format (msg, rowId, colId, rows, cols))
    }
  }
  

  def setSystemLookAndFeel() {
    import javax.swing.UIManager
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName)
  }

}

case class TableColumnHeaderSelected(override val source: Table, column: Int) extends TableEvent(source)
