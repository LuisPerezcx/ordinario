package util;

import pojo.Carrito;
import pojo.Empleado;
import pojo.Pedido;
import pojo.Producto;

import java.util.ArrayList;
import java.util.Comparator;

public class Util {
    public static  ArrayList<Pedido> pedidoArrayList = new ArrayList<>();
    public static ArrayList<Producto> productoArrayList = new ArrayList<>();
    public static ArrayList<Carrito> carritoArrayList = new ArrayList<>();
    public static ArrayList<Empleado> empleadoArrayList = new ArrayList<>();
    public static ArrayList<String> departamentoArrayList = new ArrayList<>();
    public static String usuario = "admin";
    public static String contrasena = "admin";
    public static String[] tablaRep = {"Numero Pedido","Nombre Cliente","Direccion"};
    public static String[] tablaCarrito = {"Nombre producto","Cantidad","Precio"};
    public static String[] tablaPrincipal = {"Nombre producto","Existencia","Precio"};
    public static String[] productosPublicados = {"NOMBRE PRODUCTO","MARCA","EXISTENCIA","DESCRIPCION","PRECIO","DEPARTAMENTO"};
    public static String[] carrito = {"Productos en carrito"};

    public static void crearDepartamentosDefault(){
        departamentoArrayList.add("Abarrotes");
        departamentoArrayList.add("Lácteos");
        departamentoArrayList.add("Artículos de limpieza");
        departamentoArrayList.add("Embutidos");
        departamentoArrayList.add("Línea blanca");
    }
    public static void crearUsuariosDefault(){
        empleadoArrayList.add(new Empleado("Juanito","Perez","Hola",28,
                "calle que no es calle",9876543,"324jb432hi","Administrador",
                "Martes","admin","admin"));
        empleadoArrayList.add(new Empleado("Jose","Perez","Leon",24,
                "calle que no es calle # 5",9434321,"324afef2hi","Repartidor",
                "Dominngo","rep","rep"));
    }

    public static ArrayList<Pedido> getPedidoArrayList(){
        return pedidoArrayList;
    }
    public static ArrayList<Carrito> getCarritoArrayList(){
        return carritoArrayList;
    }
    public static ArrayList<String> getDepartamentoArrayList() {
        return departamentoArrayList;
    }
    public static ArrayList<Producto> getProductoArrayList(){
        return productoArrayList;
    }
    public static void ordenarProductos(){
        productoArrayList.sort(Comparator.comparing(Producto::getDepartamento));
    }
}
