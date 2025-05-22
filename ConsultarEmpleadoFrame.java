import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ConsultarEmpleadoFrame extends JFrame {
    private ListaLigadaEmpleado lista;
    private JTextField txtBuscar;
    private JComboBox<String> comboDepto, comboSexo;
    private DefaultTableModel modeloTabla;
    private JTable tabla;

    public ConsultarEmpleadoFrame(ListaLigadaEmpleado listaGlobal) {
        this.lista = listaGlobal;

        setTitle("Consultar Empleado");
        setSize(900, 400);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // 🔍 Panel de búsqueda
        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtBuscar = new JTextField(20);
        panelBusqueda.add(new JLabel("Buscar (ID o nombre):"));
        panelBusqueda.add(txtBuscar);

        // 📁 Combo de departamento
        comboDepto = new JComboBox<>(new String[]{"Todos", "TI", "Ventas", "RH", "Marketing"});
        panelBusqueda.add(new JLabel("Departamento:"));
        panelBusqueda.add(comboDepto);

        // 🚻 Combo de sexo
        comboSexo = new JComboBox<>(new String[]{"Todos", "M", "F"});
        panelBusqueda.add(new JLabel("Sexo:"));
        panelBusqueda.add(comboSexo);

        JButton btnBuscar = new JButton("Buscar");
        panelBusqueda.add(btnBuscar);

        add(panelBusqueda, BorderLayout.NORTH);

        // 📋 Tabla
        String[] columnas = {"ID", "Nombre", "Edad", "Sexo", "Dirección", "Teléfono", "Puesto", "Departamento", "Horas", "Costo/Hora", "Sueldo"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tabla = new JTable(modeloTabla);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        // 🔁 Acción
        btnBuscar.addActionListener(e -> buscarEmpleados());
    }

    private void buscarEmpleados() {
        String texto = txtBuscar.getText().trim().toLowerCase();
        String deptoFiltro = comboDepto.getSelectedItem().toString();
        String sexoFiltro = comboSexo.getSelectedItem().toString();

        modeloTabla.setRowCount(0); // Limpiar tabla
        Empleado actual = lista.getCabeza();

        while (actual != null) {
            boolean coincideTexto = actual.id.toLowerCase().contains(texto)
                    || actual.getNombreCompleto().toLowerCase().contains(texto);

            boolean coincideDepto = deptoFiltro.equals("Todos") || actual.departamento.equalsIgnoreCase(deptoFiltro);
            boolean coincideSexo = sexoFiltro.equals("Todos") || actual.sexo.equalsIgnoreCase(sexoFiltro);

            if (coincideTexto && coincideDepto && coincideSexo) {
                modeloTabla.addRow(new Object[]{
                        actual.id, actual.getNombreCompleto(), actual.edad, actual.sexo,
                        actual.direccion, actual.telefono, actual.puesto, actual.departamento,
                        actual.horas, actual.costoHora, actual.sueldo
                });
            }

            actual = actual.siguiente;
        }
    }
}
