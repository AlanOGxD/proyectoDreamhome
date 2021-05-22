import javax.swing.JTextArea
import javax.swing.JScrollPane
import javax.swing.JFrame
import java.awt.BorderLayout
import java.awt.Dimension


object Login extends App {
  val textArea = new JTextArea
    val scrollPane = new JScrollPane(textArea)
    val frame = new JFrame("Login")
    
    frame.getContentPane.add(scrollPane, BorderLayout.CENTER)
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    frame.setSize(new Dimension(600, 400))
    frame.setLocationRelativeTo(null)
    frame.setVisible(true)
}