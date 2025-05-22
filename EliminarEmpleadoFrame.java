import javax.swing.*;

public class EliminarEmpleadoFrame extends JFrame {
    private ListaLigadaEmpleado lista;

    public EliminarEmpleadoFrame(ListaLigadaEmpleado listaGlobal) {
        this.lista = listaGlobal;

        setTitle("Eliminar Empleado");
        setSize(300, 150);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel lbl = new JLabel("ID del empleado:");
        lbl.setBounds(20, 20, 120, 25);
        add(lbl);

        JTextField txtID = new JTextField();
        txtID.setBounds(140, 20, 120, 25);
        add(txtID);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(90, 60, 100, 30);
        add(btnEliminar);

        btnEliminar.addActionListener(e -> {
            String id = txtID.getText().trim();
            Empleado emp = lista.buscarPorID(id);

            if (emp == null) {
                JOptionPane.showMessageDialog(this, "Empleado no encontrado.");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this,
                    "¿Está seguro que desea eliminar al empleado con ID " + id + "?\nEsta acción no se puede deshacer.",
                    "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                lista.eliminarPorID(id);
                HistorialEliminaciones.agregar(emp);
                EmpleadoManager.reescribirArchivo(lista);

                JOptionPane.showMessageDialog(this, "Empleado eliminado exitosamente.");
                dispose();
            }
        });
    }
}
