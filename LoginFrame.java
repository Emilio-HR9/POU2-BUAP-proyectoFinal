import javax.swing.*;

public class LoginFrame extends JFrame {
    private JTextField txtCorreo;
    private JPasswordField txtPassword;

    public LoginFrame() {
        setTitle("Login Administrador");
        setSize(350, 220);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblCorreo = new JLabel("Correo electrónico:");
        lblCorreo.setBounds(20, 20, 140, 25);
        add(lblCorreo);

        txtCorreo = new JTextField();
        txtCorreo.setBounds(160, 20, 150, 25);
        add(txtCorreo);

        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setBounds(20, 60, 140, 25);
        add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(160, 60, 150, 25);
        add(txtPassword);

        JButton btnLogin = new JButton("Iniciar Sesión");
        btnLogin.setBounds(20, 110, 140, 30);
        btnLogin.addActionListener(e -> login());
        add(btnLogin);

        JButton btnRegistrar = new JButton("Registrarse");
        btnRegistrar.setBounds(170, 110, 140, 30);
        btnRegistrar.addActionListener(e -> {
            RegistroFrame registroFrame = new RegistroFrame();
            registroFrame.setVisible(true);
        });
        add(btnRegistrar);
    }

    private void login() {
        String correo = txtCorreo.getText().trim();
        String password = new String(txtPassword.getPassword());

        if (!UsuarioManager.esEmailValido(correo)) {
            mostrarError("Ingrese un correo válido.");
        } else if (password.isEmpty()) {
            mostrarError("La contraseña no puede estar vacía.");
        } else if (UsuarioManager.iniciarSesion(correo, password)) {
            JOptionPane.showMessageDialog(this, "¡Bienvenido al sistema!");
            new MainFrame(correo).setVisible(true);  // Abre el menú principal
            dispose(); // Cierra el login
        } else {
            mostrarError("Usuario o contraseña incorrectos. Intente nuevamente.");
        }
    }

    private void registrar() {
        String correo = txtCorreo.getText().trim();
        String password = new String(txtPassword.getPassword());

        if (!UsuarioManager.esEmailValido(correo)) {
            mostrarError("Correo no válido.");
        } else if (password.isEmpty()) {
            mostrarError("La contraseña no puede estar vacía.");
        } else if (UsuarioManager.registrarUsuario(correo, password)) {
            JOptionPane.showMessageDialog(this, "¡Registro exitoso!");
        } else {
            mostrarError("El usuario ya existe o hubo un error.");
        }
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}
