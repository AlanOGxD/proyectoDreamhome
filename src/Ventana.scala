import javax.swing.JTextArea
import javax.swing.JScrollPane
import javax.swing.JFrame
import java.awt.Dimension
import java.awt.BorderLayout



object Ventana extends App {
  
    val textArea = new JTextArea
    val scrollPane = new JScrollPane(textArea)
    val frame = new JFrame("Ventana")
    
    frame.getContentPane.add(scrollPane, BorderLayout.CENTER)
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    frame.setSize(new Dimension(600, 400))
    frame.setLocationRelativeTo(null)
    frame.setVisible(true)
    
}