import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class NominaFrameArchivo extends JFrame {
    private JTextField txtBuscar;
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private ListaLigadaEmpleado lista;

    public NominaFrameArchivo(ListaLigadaEmpleado listaGlobal) {
        this.lista = listaGlobal;

        setTitle("Nómina (desde memoria)");
        setSize(900, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtBuscar = new JTextField(25);
        panelSuperior.add(new JLabel("Buscar por nombre o apellido:"));
        panelSuperior.add(txtBuscar);
        add(panelSuperior, BorderLayout.NORTH);

        txtBuscar.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                filtrarTabla(txtBuscar.getText());
            }
        });

        String[] columnas = {"ID", "Nombre", "Edad", "Sexo", "Dirección", "Teléfono", "Puesto", "Departamento", "Horas", "Costo/h", "Sueldo"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tabla = new JTable(modeloTabla);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        cargarDesdeListaLigada();
    }

    private void cargarDesdeListaLigada() {
        modeloTabla.setRowCount(0);
        Empleado actual = lista.getCabeza();
        while (actual != null) {
            modeloTabla.addRow(new Object[]{
                    actual.id, actual.getNombreCompleto(), actual.edad, actual.sexo, actual.direccion,
                    actual.telefono, actual.puesto, actual.departamento,
                    actual.horas, actual.costoHora, actual.sueldo
            });
            actual = actual.siguiente;
        }
    }

    private void filtrarTabla(String texto) {
        String filtro = texto.toLowerCase();
        modeloTabla.setRowCount(0);
        Empleado actual = lista.getCabeza();
        while (actual != null) {
            if (actual.getNombreCompleto().toLowerCase().contains(filtro) || actual.id.toLowerCase().contains(filtro)) {
                modeloTabla.addRow(new Object[]{
                        actual.id, actual.getNombreCompleto(), actual.edad, actual.sexo, actual.direccion,
                        actual.telefono, actual.puesto, actual.departamento,
                        actual.horas, actual.costoHora, actual.sueldo
                });
            }
            actual = actual.siguiente;
        }
    }
}
