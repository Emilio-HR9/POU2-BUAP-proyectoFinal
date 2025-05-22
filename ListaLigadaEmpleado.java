public class ListaLigadaEmpleado {
    private Empleado cabeza;

    public void insertarOrdenado(Empleado nuevo) {
        if (cabeza == null || nuevo.nombre.compareToIgnoreCase(cabeza.nombre) < 0) {
            nuevo.siguiente = cabeza;
            cabeza = nuevo;
        } else {
            Empleado actual = cabeza;
            while (actual.siguiente != null && nuevo.nombre.compareToIgnoreCase(actual.siguiente.nombre) > 0) {
                actual = actual.siguiente;
            }
            nuevo.siguiente = actual.siguiente;
            actual.siguiente = nuevo;
        }
    }

    public Empleado buscarPorID(String id) {
    Empleado actual = cabeza;
    while (actual != null) {
        if (actual.id.equals(id)) return actual;
        actual = actual.siguiente;
    }
    return null;
    }

    public void eliminarPorID(String id) {
    if (cabeza == null) return;
    if (cabeza.id.equals(id)) {
        cabeza = cabeza.siguiente;
        return;
    }
    
    Empleado actual = cabeza;
    while (actual.siguiente != null) {
        if (actual.siguiente.id.equals(id)) {
            actual.siguiente = actual.siguiente.siguiente;
            return;
        }
        actual = actual.siguiente;
    }
    }

    public Empleado getCabeza() {
        return cabeza;
    }
}

