import java.awt.*;
import java.util.List;
import javax.swing.*;


public class MainFrame extends JFrame {

    public MainFrame(String nombreAdmin) {
        ListaLigadaEmpleado listaGlobal = new ListaLigadaEmpleado();
        List<Empleado> empleados = EmpleadoManager.cargarEmpleados();
        for (Empleado e : empleados) {
        listaGlobal.insertarOrdenado(e);
        }
        setTitle("Sistema de Nómina - Bienvenido " + nombreAdmin);
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

       
        JPanel panelLateral = new JPanel();
        panelLateral.setLayout(new GridLayout(8, 1, 5, 5));
        panelLateral.setPreferredSize(new Dimension(200, 0));

        
        JButton btnRegistrar = new JButton("Registrar empleado");
        JButton btnMostrarNomina = new JButton("Mostrar nómina");
        JButton btnActualizar = new JButton("Actualizar empleado");
        JButton btnEliminar = new JButton("Eliminar empleado");
        JButton btnConsultar = new JButton("Consultar empleado");
        JButton btnHistorial = new JButton("Ver historial de bajas");
        JButton btnCola = new JButton("Ver solicitudes pendientes");
        JButton btnCerrarSesion = new JButton("Cerrar sesión");

        
        panelLateral.add(btnRegistrar);
        panelLateral.add(btnMostrarNomina);
        panelLateral.add(btnActualizar);
        panelLateral.add(btnEliminar);
        panelLateral.add(btnConsultar);
        panelLateral.add(btnHistorial);
        panelLateral.add(btnCola);
        panelLateral.add(btnCerrarSesion);


        JPanel panelContenido = new JPanel();
        panelContenido.setLayout(new BorderLayout());

        btnRegistrar.addActionListener(e -> {
        RegistroEmpleadoFrame frame = new RegistroEmpleadoFrame(listaGlobal);
        frame.setVisible(true);});
        btnMostrarNomina.addActionListener(e -> {
        NominaFrameArchivo ventana = new NominaFrameArchivo(listaGlobal);
        ventana.setVisible(true);
        });
        btnActualizar.addActionListener(e -> {
        ActualizarBusquedaFrame ventana = new ActualizarBusquedaFrame(listaGlobal);
        ventana.setVisible(true);});
        btnEliminar.addActionListener(e -> {
        EliminarEmpleadoFrame frame = new EliminarEmpleadoFrame(listaGlobal);
        frame.setVisible(true);});
        btnConsultar.addActionListener(e -> {
        ConsultarEmpleadoFrame frame = new ConsultarEmpleadoFrame(listaGlobal);
        frame.setVisible(true);}); 
        btnHistorial.addActionListener(e -> {
        HistorialEliminadosFrame frame = new HistorialEliminadosFrame();
        frame.setVisible(true);});
        btnCola.addActionListener(e -> {
        ColaEdicionFrame frame = new ColaEdicionFrame();
        frame.setVisible(true);});
        btnCerrarSesion.addActionListener(e -> {
            int opcion = JOptionPane.showConfirmDialog(this, "¿Deseas cerrar sesión?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                dispose();
                new LoginFrame().setVisible(true);
            }
        });

       
        add(panelLateral, BorderLayout.WEST);
        add(panelContenido, BorderLayout.CENTER);
    }

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "En desarrollo", JOptionPane.INFORMATION_MESSAGE);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame("admin@correo.com").setVisible(true));
    }
}
