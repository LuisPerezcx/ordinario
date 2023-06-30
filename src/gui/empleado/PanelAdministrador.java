package gui.empleado;

import gui.PrincipalGUI;
import gui.imagen.ImagenCarrito;
import gui.imagen.MiImagen;
import pojo.Producto;
import util.Util;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class PanelAdministrador extends JFrame {
    private JPanel panelContenedor, panelBoton,pnlImagen,pnlTitulo;
    private JButton btnEliminarProducto, btnEditarProducto,btnAgregarProducto, btnRegresar;
    private JLabel titulo;
    private DefaultTableModel modelo;
    private JTable jTable;
    private final MiImagen imagen;
    private int renglonSeleccionado = -1;

    public PanelAdministrador(MiImagen imagen) {
        this.imagen = imagen;
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
        btnEliminarProducto = new JButton("Eliminar Producto");
        btnEditarProducto = new JButton("Editar Producto");
        btnAgregarProducto = new JButton("Agregar Producto");
        btnRegresar = new JButton("Regresar");

        titulo = new JLabel("Panel Administrador");
        modelo = new DefaultTableModel();
        jTable = new JTable(modelo);

        panelContenedor = new JPanel();
        panelBoton = new JPanel();
        pnlImagen = new JPanel();
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
        panelBoton.add(btnEliminarProducto);
        panelBoton.add(btnEditarProducto);
        panelBoton.add(btnAgregarProducto);
        panelBoton.add(btnRegresar);


        pnlTitulo.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnlTitulo.add(titulo);


        panelContenedor.setLayout(new BoxLayout(panelContenedor, BoxLayout.Y_AXIS));

        pnlImagen.setLayout(new GridLayout(1,1));
        imagen.setPreferredSize(new Dimension(128,128));
        pnlImagen.add(imagen);

        panelContenedor.add(pnlImagen);
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
        modelo.setColumnIdentifiers(Util.productosPublicados);
        String [] fila = new String[modelo.getColumnCount()];
        ArrayList<Producto> lista = Util.getProductoArrayList();
        for (Producto userTable : lista) {
            fila[0] = userTable.getNombreProducto();
            fila[1] = userTable.getMarca();
            fila[2] = String.valueOf(userTable.getExistencia());
            fila[3] = userTable.getDescripcion();
            fila[4] = String.valueOf(userTable.getPrecio());
            fila[5] = userTable.getDepartamento();
            modelo.addRow(fila);
        }
    }
    private void addListeners() {
        btnEliminarProducto.addActionListener(e -> {
            if (renglonSeleccionado!=-1){
                eliminar(renglonSeleccionado);
                initControl();
            }else{
                JOptionPane.showMessageDialog(this,"Primero selecciona un producto");
            }
        });
        btnEditarProducto.addActionListener(e -> {
            if (renglonSeleccionado!=-1){
                setVisible(false);
                new ModificarProducto(renglonSeleccionado);
            }else{
                JOptionPane.showMessageDialog(this,"Primero selecciona un producto");
            }
        });
        btnRegresar.addActionListener(e -> {
            setVisible(false);
            new PrincipalGUI(new ImagenCarrito(350,0));
        });
        btnAgregarProducto.addActionListener(e -> {
            setVisible(false);
            new AgregarProducto();
        });
        jTable.getSelectionModel().addListSelectionListener(e -> {
            int renglon = jTable.getSelectedRow();
            if (renglon != -1) {
                renglonSeleccionado = renglon;
            }
        });
    }
    private int getRenglonSeleccionado() {
        return renglonSeleccionado;
    }
    private void eliminar(int index){
        Util.productoArrayList.remove(index);
        actualizarTabla();
    }
    private void actualizarTabla(){
        modelo.setRowCount(0); // Limpiar los datos de la tabla
        String[] fila = new String[modelo.getColumnCount()];
        ArrayList<Producto> lista = Util.getProductoArrayList();
        for (Producto producto : lista) {
            fila[0] = producto.getNombreProducto();
            fila[1] = producto.getMarca();
            fila[2] = String.valueOf(producto.getExistencia());
            fila[3] = producto.getDescripcion();
            fila[4] = String.valueOf(producto.getPrecio());
            fila[5] = producto.getDepartamento();
            modelo.addRow(fila);
        }
    }
}
