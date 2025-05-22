import java.awt.*;
import java.util.Queue;
import javax.swing.*;

public class ColaEdicionFrame extends JFrame {

    public ColaEdicionFrame() {
        setTitle("Cola de Ediciones Pendientes");
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        Queue<String> cola = ColaEdicion.obtenerCola();

        DefaultListModel<String> modelo = new DefaultListModel<>();

        if (cola.isEmpty()) {
            modelo.addElement("No hay ediciones pendientes.");
        } else {
            for (String id : cola) {
                modelo.addElement("ID pendiente: " + id);
            }
        }

        JList<String> listaIDs = new JList<>(modelo);
        add(new JScrollPane(listaIDs), BorderLayout.CENTER);
    }
}
