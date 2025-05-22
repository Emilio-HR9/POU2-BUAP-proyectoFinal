import java.awt.*;
import javax.swing.*;

public class RegistroEmpleadoFrame extends JFrame {
    private JTextField txtID, txtNombre, txtApPaterno, txtApMaterno, txtEdad, txtSexo,
            txtDireccion, txtTelefono, txtPuesto, txtDepartamento, txtHoras, txtCosto;

    private ListaLigadaEmpleado lista;

    public RegistroEmpleadoFrame(ListaLigadaEmpleado listaGlobal) {
        this.lista = listaGlobal;

        setTitle("Registro de Nuevo Empleado");
        setSize(450, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(14, 2, 5, 5));

        // Campos y etiquetas
        add(new JLabel("ID (6 caracteres):"));
        txtID = new JTextField(); add(txtID);

        add(new JLabel("Nombre:"));
        txtNombre = new JTextField(); add(txtNombre);

        add(new JLabel("Apellido paterno:"));
        txtApPaterno = new JTextField(); add(txtApPaterno);

        add(new JLabel("Apellido materno:"));
        txtApMaterno = new JTextField(); add(txtApMaterno);

        add(new JLabel("Sexo (M/F):"));
        txtSexo = new JTextField(); add(txtSexo);

        add(new JLabel("Edad:"));
        txtEdad = new JTextField(); add(txtEdad);

        add(new JLabel("Dirección:"));
        txtDireccion = new JTextField(); add(txtDireccion);

        add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField(); add(txtTelefono);

        add(new JLabel("Puesto:"));
        txtPuesto = new JTextField(); add(txtPuesto);

        add(new JLabel("Departamento:"));
        txtDepartamento = new JTextField(); add(txtDepartamento);

        add(new JLabel("Horas trabajadas:"));
        txtHoras = new JTextField(); add(txtHoras);

        add(new JLabel("Costo por hora:"));
        txtCosto = new JTextField(); add(txtCosto);

        JButton btnRegistrar = new JButton("Registrar");
        JButton btnCancelar = new JButton("Cancelar");
        add(btnRegistrar); add(btnCancelar);

        btnRegistrar.addActionListener(e -> registrarEmpleado());
        btnCancelar.addActionListener(e -> dispose());
    }

    private void registrarEmpleado() {
        try {
            String id = txtID.getText().trim();
            String nombre = txtNombre.getText().trim();
            String apPat = txtApPaterno.getText().trim();
            String apMat = txtApMaterno.getText().trim();
            String sexo = txtSexo.getText().trim().toUpperCase();
            int edad = Integer.parseInt(txtEdad.getText().trim());
            String direccion = txtDireccion.getText().trim();
            String telefono = txtTelefono.getText().trim();
            String puesto = txtPuesto.getText().trim();
            String depto = txtDepartamento.getText().trim();
            int horas = Integer.parseInt(txtHoras.getText().trim());
            double costo = Double.parseDouble(txtCosto.getText().trim());

            
            if (!id.matches("^\\w{6}$")) throw new Exception("ID debe tener 6 caracteres.");
            if (!(sexo.equals("M") || sexo.equals("F"))) throw new Exception("Sexo debe ser M o F.");
            if (edad <= 0) throw new Exception("Edad inválida.");
            if (horas < 0 || costo < 0) throw new Exception("Horas o costo inválidos.");

            Empleado nuevo = new Empleado(id, nombre, apPat, apMat, sexo, edad, direccion,
                    telefono, puesto, depto, horas, costo);

            lista.insertarOrdenado(nuevo);
            EmpleadoManager.guardarEmpleado(nuevo);

            JOptionPane.showMessageDialog(this,
                    "Empleado registrado con éxito.\nSueldo calculado: $" + nuevo.sueldo);
            dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(),
                    "Validación", JOptionPane.ERROR_MESSAGE);
        }
    }
}
