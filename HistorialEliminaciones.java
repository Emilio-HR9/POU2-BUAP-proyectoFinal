import java.util.Stack;

public class HistorialEliminaciones {
    private static final Stack<Empleado> pila = new Stack<>();

    public static void agregar(Empleado e) {
        pila.push(e);
    }

    public static Stack<Empleado> obtenerPila() {
        return pila;
    }

    public static boolean estaVacio() {
        return pila.isEmpty();
    }
}

