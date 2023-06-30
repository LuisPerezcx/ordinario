package pojo;

public class Producto {
    private String nombreProducto;
    private String marca;
    private int existencia;
    private String descripcion;
    private double precio;
    private String Departamento;

    public Producto(String nombreProducto, String marca, int existencia, String descripcion, double precio, String departamento) {
        this.nombreProducto = nombreProducto;
        this.marca = marca;
        this.existencia = existencia;
        this.descripcion = descripcion;
        this.precio = precio;
        Departamento = departamento;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public String getMarca() {
        return marca;
    }

    public int getExistencia() {
        return existencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public String getDepartamento() {
        return Departamento;
    }
}
