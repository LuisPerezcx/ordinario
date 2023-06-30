package gui;

import gui.cliente.CarritoGUI;
import gui.empleado.LoginAdmin;
import gui.imagen.ImagenCarrito;
import gui.imagen.MiImagen;
import pojo.Carrito;
import pojo.Producto;
import util.Util;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;


public class PrincipalGUI extends JFrame {
    private JPanel panelContenedor, panelBoton, pnlDetalles,pnlLogin,pnlImagen,pnlTitulo;
    private JButton btnVerCarrito, btnAgregarCarrito,btnLogin;
    private JLabel nombreProducto, marca, existencia, descripcion, precio, departamento, nombreProducto1, marca1, existencia1, descripcion1, precio1, departamento1,titulo, cantidadlbl;
    private DefaultTableModel modelo;
    private JTable jTable;
    private final ImagenCarrito imagenCarrito;
    private int renglonSeleccionado = -1;
    private JTextField txtCantidad;


    public PrincipalGUI(ImagenCarrito imagenCarrito) {
        this.imagenCarrito = imagenCarrito;
        crearComponentes();
        initControl();
        configTable();
        configurarVentana();
        dibujarCuerpo();
        add(panelContenedor);
        pack();
        addListeners();
    }

    private void crearComponentes() {
        btnLogin = new JButton("Login");
        btnVerCarrito = new JButton("Ver carrito");
        btnAgregarCarrito = new JButton("Agregar al carrito");

        nombreProducto = new JLabel("Nombre producto: ");
        marca = new JLabel("Marca: ");
        existencia = new JLabel("Existencias: ");
        descripcion = new JLabel("Descripcion: ");
        precio = new JLabel("Precio: ");
        departamento = new JLabel("Departamento: ");
        titulo = new JLabel("Productos");
        cantidadlbl = new JLabel("Cantidad de productos: ");

        nombreProducto1 = new JLabel("");
        marca1 = new JLabel("");
        existencia1 = new JLabel("");
        descripcion1 = new JLabel("");
        precio1 = new JLabel("");
        departamento1 = new JLabel("");

        modelo = new DefaultTableModel();
        jTable = new JTable(modelo);

        panelContenedor = new JPanel();
        panelBoton = new JPanel();
        pnlLogin = new JPanel();
        pnlImagen = new JPanel();
        pnlDetalles = new JPanel();
        pnlTitulo = new JPanel();

        txtCantidad = new JTextField();
    }

    private void configurarVentana() {
        setTitle("Formulario lista");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setSize(800, 720);
        setLocationRelativeTo(null);
    }

    private void dibujarCuerpo() {
        panelBoton.setLayout(new FlowLayout());
        panelBoton.setPreferredSize(new Dimension(650, 50));
        panelBoton.add(cantidadlbl);
        txtCantidad.setPreferredSize(new Dimension(100,20));
        panelBoton.add(txtCantidad);
        panelBoton.add(btnAgregarCarrito);


        pnlDetalles.setLayout(new GridLayout(3, 4));
        pnlDetalles.add(nombreProducto);
        pnlDetalles.add(nombreProducto1);
        pnlDetalles.add(marca);
        pnlDetalles.add(marca1);
        pnlDetalles.add(existencia);
        pnlDetalles.add(existencia1);
        pnlDetalles.add(descripcion);
        pnlDetalles.add(descripcion1);
        pnlDetalles.add(precio);
        pnlDetalles.add(precio1);
        pnlDetalles.add(departamento);
        pnlDetalles.add(departamento1);


        panelContenedor.setLayout(new BoxLayout(panelContenedor, BoxLayout.Y_AXIS));

        pnlImagen.setLayout(new GridLayout(1,1));
        imagenCarrito.setPreferredSize(new Dimension(128,128));
        pnlImagen.add(imagenCarrito);

        pnlLogin.setLayout(new BorderLayout());
        pnlLogin.add(btnVerCarrito, BorderLayout.WEST);
        pnlLogin.add(btnLogin, BorderLayout.EAST);

        pnlTitulo.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnlTitulo.add(titulo);

        panelContenedor.add(pnlLogin);
        panelContenedor.add(pnlImagen);
        panelContenedor.add(new JLabel(" "));

        panelContenedor.add(pnlTitulo);
        panelContenedor.add(new JScrollPane(jTable));
        panelContenedor.add(panelBoton);
        panelContenedor.add(pnlDetalles);
    }

    private void configTable() {
        jTable.setGridColor(new Color(88, 214, 141));
        jTable.setPreferredScrollableViewportSize(new Dimension(800, 350));
    }
    private void initControl(){
        Util.ordenarProductos();
        modelo.setColumnIdentifiers(Util.tablaPrincipal);
        String [] fila = new String[modelo.getColumnCount()];
        ArrayList<Producto> lista = Util.getProductoArrayList();
        for (Producto userTable : lista) {
            fila[0] = userTable.getNombreProducto();
            fila[1] = String.valueOf(userTable.getExistencia());
            fila[2] = String.valueOf(userTable.getPrecio());
            modelo.addRow(fila);
        }
    }

    private void addListeners() {
        btnVerCarrito.addActionListener(e -> {
            setVisible(false);
            new CarritoGUI(new ImagenCarrito(260,0));
        });
        btnAgregarCarrito.addActionListener(e -> {
            if(renglonSeleccionado!=-1){
                if(!txtCantidad.getText().equals("")){
                    int cantidad = validarCantidad();
                    if (cantidad != -1){
                        agregarCarrito();
                    }
                }else{
                    JOptionPane.showMessageDialog(this,"Ingresa una cantidad");
                }
            }else {
                JOptionPane.showMessageDialog(this,"Primero selecciona un producto");
            }
        });
        btnLogin.addActionListener(e -> {
            setVisible(false);
            new LoginAdmin(new MiImagen(100,0));
        });
        jTable.getSelectionModel().addListSelectionListener(e -> {
            int renglon = jTable.getSelectedRow();
            if (renglon != -1) {
                renglonSeleccionado = renglon;
                detalles();
            }
        });
    }
    private void detalles(){
        Producto producto = Util.getProductoArrayList().get(renglonSeleccionado);
        nombreProducto1.setText(producto.getNombreProducto());
        marca1.setText(producto.getMarca());
        existencia1.setText(String.valueOf(producto.getExistencia()));
        descripcion1.setText(producto.getDescripcion());
        precio1.setText(String.valueOf(producto.getPrecio()));
        departamento1.setText(producto.getDepartamento());
    }
    private void agregarCarrito(){
        int cantidad = validarCantidad();
        Producto producto = Util.productoArrayList.get(renglonSeleccionado);
        if (validarExistencias(producto,cantidad)){
            Util.carritoArrayList.add(new Carrito(producto,cantidad,renglonSeleccionado));
            restarExistencia(producto,cantidad);
            actualizarTabla();
            detalles();
            txtCantidad.setText("");
        } else {
            JOptionPane.showMessageDialog(this,"No existen productos suficientes en el inventario");
        }
    }
    private void restarExistencia(Producto producto, int cantidad){
        int existenciasActuales = producto.getExistencia();
        producto.setExistencia(existenciasActuales - cantidad);
    }
    private int validarCantidad(){
        String cantidadtxt = txtCantidad.getText();
        int cantidad =- 1;
        while (cantidad == -1){
            try {
                cantidad = Integer.parseInt(cantidadtxt);
                if (cantidad <=0){
                    cantidadtxt = JOptionPane.showInputDialog(null, "Ingresa una cantidad valida (mayor a cero)", "Datos no válidos", JOptionPane.PLAIN_MESSAGE);
                    cantidad = -1;
                }
            } catch (NumberFormatException e){
                cantidadtxt = JOptionPane.showInputDialog(null, "Ingresa una cantidad valida", "Datos no válidos", JOptionPane.PLAIN_MESSAGE);
            }
        }
        return cantidad;
    }
    private boolean validarExistencias(Producto producto,int cantidad){
        if (cantidad<=producto.getExistencia()){
            return true;
        }else return false;
    }
    private void actualizarTabla(){
        modelo.setRowCount(0); // Limpiar los datos de la tabla
        String[] fila = new String[modelo.getColumnCount()];
        ArrayList<Producto> lista = Util.getProductoArrayList();
        for (Producto userTable : lista) {
            fila[0] = userTable.getNombreProducto();
            fila[1] = String.valueOf(userTable.getExistencia());
            fila[2] = String.valueOf(userTable.getPrecio());
            modelo.addRow(fila);
        }
    }
}