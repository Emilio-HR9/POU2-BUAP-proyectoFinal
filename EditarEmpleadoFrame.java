import java.awt.*;
import javax.swing.*;

public class EditarEmpleadoFrame extends JFrame {
    private JTextField[] campos = new JTextField[10];
    private ListaLigadaEmpleado lista;

    public EditarEmpleadoFrame(Empleado empleado, ListaLigadaEmpleado listaGlobal) {
        this.lista = listaGlobal;

        setTitle("Editar Empleado");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(12, 2, 5, 5));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        String[] labels = {"ID (no editable)", "Nombre completo", "Edad", "Sexo", "Dirección", "Teléfono",
                "Puesto", "Departamento", "Horas", "Costo por hora"};

        String[] valores = {
                empleado.id,
                empleado.getNombreCompleto(),  
                String.valueOf(empleado.edad),
                empleado.sexo,
                empleado.direccion,
                empleado.telefono,
                empleado.puesto,
                empleado.departamento,
                String.valueOf(empleado.horas),
                String.valueOf(empleado.costoHora)
        };

        for (int i = 0; i < labels.length; i++) {
            add(new JLabel(labels[i]));
            campos[i] = new JTextField(valores[i]);
            if (i == 0) campos[i].setEditable(false);
            add(campos[i]);
        }

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> guardarCambios());
        add(btnGuardar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        add(btnCancelar);
    }

    private void guardarCambios() {
        int confirm = JOptionPane.showConfirmDialog(this, "¿Desea actualizar este registro?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        try {
            String[] nombreParts = campos[1].getText().trim().split(" ");
            String nombre = nombreParts.length > 0 ? nombreParts[0] : "";
            String apPaterno = nombreParts.length > 1 ? nombreParts[1] : "";
            String apMaterno = nombreParts.length > 2 ? nombreParts[2] : "";

            Empleado actualizado = new Empleado(
                    campos[0].getText(),
                    nombre,
                    apPaterno,
                    apMaterno,
                    campos[3].getText(),
                    Integer.parseInt(campos[2].getText()),
                    campos[4].getText(),
                    campos[5].getText(),
                    campos[6].getText(),
                    campos[7].getText(),
                    Integer.parseInt(campos[8].getText()),
                    Double.parseDouble(campos[9].getText())
            );

            lista.eliminarPorID(actualizado.id);
            lista.insertarOrdenado(actualizado);
            EmpleadoManager.reescribirArchivo(lista);
            ColaEdicion.agregar(actualizado.id);

            JOptionPane.showMessageDialog(this, "Empleado actualizado correctamente.");
            dispose();
        } catch (Exception e) {
            System.err.println("Error al actualizar empleado: " + e.getMessage());
        }
    }
}
