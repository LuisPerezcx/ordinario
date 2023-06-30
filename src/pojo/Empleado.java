package pojo;

public class Empleado extends Persona{
    private int numeroEmpleado = 0;
    private String nombreCargo;
    private String diaDescanso;
    private String cuenta;
    private String contrasena;

    public Empleado(String nombre, String apellidoP, String apellidoM, int edad, String direccion,
                    long telefono, String ine, String nombreCargo,
                    String diaDescanso, String cuenta, String contrasena) {
        super(nombre, apellidoP, apellidoM, edad, direccion, telefono, ine);
        numeroEmpleado++;
        this.nombreCargo = nombreCargo;
        this.diaDescanso = diaDescanso;
        this.cuenta = cuenta;
        this.contrasena = contrasena;
    }

    public String getNombreCargo() {
        return nombreCargo;
    }

    public String getCuenta() {
        return cuenta;
    }

    public String getContrasena() {
        return contrasena;
    }
}
