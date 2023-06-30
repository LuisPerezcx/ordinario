package gui.cliente;

import gui.PrincipalGUI;
import gui.imagen.ImagenCarrito;
import pojo.Carrito;
import pojo.Producto;
import util.Util;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class CarritoGUI extends JFrame {
    private JPanel panelContenedor, panelBoton, pnlTotal,pnlImagen, pnlCentar,pnlTitulo;
    private JButton btnEliminarDelCarrito, btnPagarCarrito, btnRegresar;
    private JLabel total, total1,titulo;
    private DefaultTableModel modelo;
    private JTable jTable;
    private final ImagenCarrito imagenCarrito;
    private int renglonSeleccionado = -1;


    public CarritoGUI(ImagenCarrito imagenCarrito) {
        this.imagenCarrito = imagenCarrito;
        crearComponentes();
        initControl();
        configTable();
        configurarVentana();
        dibujarCuerpo();
        add(panelContenedor);
        pack();
        addListeners();
        calcularTotal();
    }
    private void calcularTotal() {
        if (Util.carritoArrayList.isEmpty()) {
            total1.setText("$0");
            return;
        }
        ArrayList<Carrito> carritoArrayList = Util.getCarritoArrayList();
        double acom = 0;
        for (Carrito carrito : carritoArrayList) {
            int cantidad = carrito.cantidad();
            double precio = carrito.producto().getPrecio();
            double total = cantidad * precio;
            acom += total;
        }
        total1.setText("$" + acom);
    }

    private void crearComponentes() {
        btnEliminarDelCarrito = new JButton("Eliminar del carrito");
        btnPagarCarrito = new JButton("Pagar carrito");
        btnRegresar = new JButton("Regresar");

        total = new JLabel("Total: ");
        total1 = new JLabel("");
        titulo = new JLabel("Productos en el carrito");

        modelo = new DefaultTableModel();
        jTable = new JTable(modelo);

        panelContenedor = new JPanel();
        panelBoton = new JPanel();
        pnlImagen = new JPanel();
        pnlTotal = new JPanel();
        pnlCentar = new JPanel();
        pnlTitulo = new JPanel();
    }

    private void configurarVentana() {
        setTitle("Formulario lista");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setSize(700, 720);
        setLocationRelativeTo(null);
    }

    private void dibujarCuerpo() {
        panelBoton.setLayout(new FlowLayout());
        panelBoton.setPreferredSize(new Dimension(650, 50));
        panelBoton.add(btnEliminarDelCarrito);
        panelBoton.add(btnPagarCarrito);
        panelBoton.add(btnRegresar);

        pnlCentar.setLayout(new FlowLayout());
        pnlCentar.add(total);
        pnlCentar.add(total1);

        pnlTotal.setLayout(new BorderLayout());
        pnlTotal.add(pnlCentar, BorderLayout.CENTER);

        pnlTitulo.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnlTitulo.add(titulo);


        panelContenedor.setLayout(new BoxLayout(panelContenedor, BoxLayout.Y_AXIS));

        pnlImagen.setLayout(new GridLayout(1,1));
        imagenCarrito.setPreferredSize(new Dimension(128,128));
        pnlImagen.add(imagenCarrito);

        panelContenedor.add(pnlImagen);
        panelContenedor.add(new JLabel(" "));
        panelContenedor.add(pnlTitulo);
        panelContenedor.add(new JScrollPane(jTable));
        panelContenedor.add(pnlTotal);
        panelContenedor.add(panelBoton);
    }

    private void configTable() {
        jTable.setGridColor(new Color(88, 214, 141));
        jTable.setPreferredScrollableViewportSize(new Dimension(650, 350));
    }
    private void initControl(){
        Util.ordenarProductos();
        modelo.setColumnIdentifiers(Util.tablaCarrito);
        String [] fila = new String[modelo.getColumnCount()];
        ArrayList<Carrito> lista = Util.getCarritoArrayList();
        for (Carrito userTable : lista) {
            fila[0] = userTable.producto().getNombreProducto();
            fila[1] = String.valueOf(userTable.cantidad());
            fila[2] = String.valueOf(userTable.producto().getPrecio());
            modelo.addRow(fila);
        }
    }

    private void addListeners() {
        btnEliminarDelCarrito.addActionListener(e -> {
            if (renglonSeleccionado!=-1){
                eliminar(renglonSeleccionado);
                initControl();
            }else{
                JOptionPane.showMessageDialog(this,"Primero selecciona un producto");
            }
        });
        btnPagarCarrito.addActionListener(e -> {
            if(!Util.carritoArrayList.isEmpty()){
                setVisible(false);
                new PagarCarrito();
            }else{
                JOptionPane.showMessageDialog(this,"Carrito vacio");
            }
        });
        btnRegresar.addActionListener(e -> {
            setVisible(false);
            new PrincipalGUI(new ImagenCarrito(350,0));
        });
        jTable.getSelectionModel().addListSelectionListener(e -> {
            int renglon = jTable.getSelectedRow();
            if (renglon != -1) {
                renglonSeleccionado = renglon;
            }
        });
    }
    private void eliminar(int index){
        Carrito carrito = Util.getCarritoArrayList().get(index);
        Util.carritoArrayList.remove(index);
        actualizarTabla();
        int cantidadEliminada = carrito.cantidad();
        int i = carrito.index();
        Producto producto = Util.productoArrayList.get(i);
        int existencias = producto.getExistencia();
        Util.productoArrayList.get(i).setExistencia(existencias+cantidadEliminada);
    }
    private void actualizarTabla(){
        modelo.setRowCount(0); // Limpiar los datos de la tabla
        String[] fila = new String[modelo.getColumnCount()];
        ArrayList<Carrito> lista = Util.getCarritoArrayList();
        for (Carrito userTable : lista) {
            fila[0] = userTable.producto().getNombreProducto();
            fila[1] = String.valueOf(userTable.cantidad());
            fila[2] = String.valueOf(userTable.producto().getPrecio());
            modelo.addRow(fila);
        }
    }
}
