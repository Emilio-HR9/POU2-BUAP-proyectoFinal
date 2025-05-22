import java.awt.*;
import java.util.Stack;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class HistorialEliminadosFrame extends JFrame {

    public HistorialEliminadosFrame() {
        setTitle("Historial de Empleados Eliminados");
        setSize(900, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        String[] columnas = {"ID", "Nombre", "Edad", "Sexo", "Dirección", "Teléfono",
                "Puesto", "Departamento", "Horas", "Costo/Hora", "Sueldo"};

        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        JTable tabla = new JTable(modelo);

        Stack<Empleado> pila = HistorialEliminaciones.obtenerPila();

        for (int i = pila.size() - 1; i >= 0; i--) {
            Empleado emp = pila.get(i);
            modelo.addRow(new Object[]{
                    emp.id, emp.nombre, emp.edad, emp.sexo, emp.direccion,
                    emp.telefono, emp.puesto, emp.departamento,
                    emp.horas, emp.costoHora, emp.sueldo
            });
        }

        add(new JScrollPane(tabla), BorderLayout.CENTER);
    }
}
