public class Empleado {
    String id;
    String nombre;     
    String apPaterno;
    String apMaterno;
    String sexo;
    int edad;
    String direccion;
    String telefono;
    String puesto;
    String departamento;
    int horas;
    double costoHora;
    double sueldo;

    Empleado siguiente;

    public Empleado(String id, String nombre, String apPaterno, String apMaterno, String sexo,
                    int edad, String direccion, String telefono, String puesto,
                    String departamento, int horas, double costoHora) {
        this.id = id;
        this.nombre = nombre;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
        this.sexo = sexo;
        this.edad = edad;
        this.direccion = direccion;
        this.telefono = telefono;
        this.puesto = puesto;
        this.departamento = departamento;
        this.horas = horas;
        this.costoHora = costoHora;
        this.sueldo = horas * costoHora;
        this.siguiente = null;
    }

    public String getNombreCompleto() {
        return nombre + " " + apPaterno + " " + apMaterno;
    }

    public String toLineaArchivo() {
        return String.join(",", id, nombre, apPaterno, apMaterno, sexo,
                String.valueOf(edad), direccion, telefono, puesto, departamento,
                String.valueOf(horas), String.valueOf(costoHora), String.valueOf(sueldo));
    }
}
