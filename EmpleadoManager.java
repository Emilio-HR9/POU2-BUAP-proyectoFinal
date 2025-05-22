import java.io.*;
import java.util.*;

public class EmpleadoManager {
    private static final String FILE_NAME = "empleados.txt";

    public static void guardarEmpleado(Empleado emp) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(emp.toLineaArchivo());
            writer.newLine();
        } catch (IOException e) {
        System.err.println("Error al guardar empleado: " + e.getMessage());
        }
    }

    public static List<Empleado> cargarEmpleados() {
        List<Empleado> empleados = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 13) {
                    Empleado emp = new Empleado(
                            datos[0], datos[1], datos[2], datos[3], datos[4],
                            Integer.parseInt(datos[5]), datos[6], datos[7],
                            datos[8], datos[9], Integer.parseInt(datos[10]),
                            Double.parseDouble(datos[11])
                    );
                    empleados.add(emp);
                }
            }
        } catch (IOException e) {
        System.err.println("Error al guardar empleado: " + e.getMessage());
        }
        return empleados;
    }

    public static void reescribirArchivo(ListaLigadaEmpleado lista) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            Empleado actual = lista.getCabeza();
            while (actual != null) {
                writer.write(actual.toLineaArchivo());
                writer.newLine();
                actual = actual.siguiente;
            }
        } catch (IOException e) {
        System.err.println("Error al guardar empleado: " + e.getMessage());
        }
    }
}
