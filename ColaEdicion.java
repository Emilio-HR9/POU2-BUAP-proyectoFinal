import java.util.LinkedList;
import java.util.Queue;

public class ColaEdicion {
    public static Queue<String> idsEnEdicion = new LinkedList<>();

    public static void agregar(String id) {
        idsEnEdicion.add(id);
    }

    public static Queue<String> obtenerCola() {
        return idsEnEdicion;
    }
}
