package gui.empleado;

import gui.imagen.MiImagen;
import pojo.Carrito;
import util.Util;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ProductosPedido extends JFrame {
    private JPanel panelContenedor, panelBoton,pnlTitulo;
    private JButton btnRegresar;
    private JLabel titulo;
    private DefaultTableModel modelo;
    private JTable jTable;
    private int index;


    public ProductosPedido(int index) {
        this.index = index;
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
        btnRegresar = new JButton("Regresar");

        titulo = new JLabel("Productos");

        modelo = new DefaultTableModel();
        jTable = new JTable(modelo);

        panelContenedor = new JPanel();
        panelBoton = new JPanel();
        pnlTitulo = new JPanel();

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
        panelBoton.add(btnRegresar);

        panelContenedor.setLayout(new BoxLayout(panelContenedor, BoxLayout.Y_AXIS));

        pnlTitulo.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnlTitulo.add(titulo);
        panelContenedor.add(new JLabel(" "));

        panelContenedor.add(pnlTitulo);
        panelContenedor.add(new JScrollPane(jTable));
        panelContenedor.add(panelBoton);
    }

    private void configTable() {
        jTable.setGridColor(new Color(88, 214, 141));
        jTable.setPreferredScrollableViewportSize(new Dimension(800, 350));
    }
    private void initControl(){
        Util.ordenarProductos();
        modelo.setColumnIdentifiers(Util.tablaCarrito);
        String [] fila = new String[modelo.getColumnCount()];
        ArrayList<Carrito> lista = Util.getPedidoArrayList().get(index).carritoArrayList();
        for (Carrito userTable : lista) {
            fila[0] = userTable.producto().getNombreProducto();
            fila[1] = String.valueOf(userTable.cantidad());
            fila[2] = String.valueOf(userTable.producto().getPrecio());
            modelo.addRow(fila);
        }
    }
    private void addListeners() {
        btnRegresar.addActionListener(e -> {
            setVisible(false);
            new Repartidor(new MiImagen(350,0));
        });
    }
}
