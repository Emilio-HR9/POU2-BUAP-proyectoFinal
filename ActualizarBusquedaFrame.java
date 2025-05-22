import javax.swing.*;

public class ActualizarBusquedaFrame extends JFrame {
    private ListaLigadaEmpleado listaGlobal;

    public ActualizarBusquedaFrame(ListaLigadaEmpleado lista) {
        this.listaGlobal = lista;

        setTitle("Buscar empleado por ID");
        setSize(300, 150);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel lbl = new JLabel("Ingrese ID:");
        lbl.setBounds(20, 20, 100, 25);
        add(lbl);

        JTextField txtID = new JTextField();
        txtID.setBounds(100, 20, 150, 25);
        add(txtID);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(90, 60, 100, 30);
        add(btnBuscar);

        btnBuscar.addActionListener(e -> {
            String id = txtID.getText().trim();
            Empleado encontrado = lista.buscarPorID(id);
            if (encontrado != null) {
                new EditarEmpleadoFrame(encontrado, listaGlobal).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Empleado no encontrado.");
            }
        });
    }
}
