package gui.empleado;

import gui.PrincipalGUI;
import gui.imagen.ImagenCarrito;
import gui.imagen.MiImagen;
import pojo.Pedido;
import util.Util;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class Repartidor extends JFrame {
    private JPanel panelContenedor, panelBoton, pnlDetalles,pnlImagen,pnlTitulo;
    private JButton  regresar,verProductos,entregado;
    private JLabel nombre, apellidoP, apellidoM, edad, direccion, telefono, txtNombre, txtApellidoP, txtApellidoM, txtEdad, txtDireccion, txtTelefono, titulo;
    private DefaultTableModel modelo;
    private JTable jTable;
    private final MiImagen imagen;
    private int renglonSeleccionado = -1;


    public Repartidor(MiImagen imagen) {
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
        verProductos = new JButton("Ver Productos Pedido");
        regresar = new JButton("Regresar");
        entregado = new JButton("Entregado");

        nombre = new JLabel("Nombre : ");
        apellidoP = new JLabel("Apellido P: ");
        apellidoM = new JLabel("Apellido M: ");
        edad = new JLabel("Edad: ");
        direccion = new JLabel("Direccion: ");
        telefono = new JLabel("Telefono: ");
        titulo = new JLabel("Panel repartidor ");

        txtNombre = new JLabel("");
        txtApellidoP = new JLabel("");
        txtApellidoM = new JLabel("");
        txtEdad = new JLabel("");
        txtDireccion = new JLabel("");
        txtTelefono = new JLabel("");

        modelo = new DefaultTableModel();
        jTable = new JTable(modelo);

        panelContenedor = new JPanel();
        panelBoton = new JPanel();
        pnlImagen = new JPanel();
        pnlDetalles = new JPanel();
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
        panelBoton.add(regresar);
        panelBoton.add(verProductos);
        panelBoton.add(entregado);



        pnlDetalles.setLayout(new GridLayout(3, 4));
        pnlDetalles.add(nombre);
        pnlDetalles.add(txtNombre);
        pnlDetalles.add(apellidoP);
        pnlDetalles.add(txtApellidoP);
        pnlDetalles.add(apellidoM);
        pnlDetalles.add(txtApellidoM);
        pnlDetalles.add(edad);
        pnlDetalles.add(txtEdad);
        pnlDetalles.add(direccion);
        pnlDetalles.add(txtDireccion);
        pnlDetalles.add(telefono);
        pnlDetalles.add(txtTelefono);


        panelContenedor.setLayout(new BoxLayout(panelContenedor, BoxLayout.Y_AXIS));

        pnlImagen.setLayout(new GridLayout(1,1));
        imagen.setPreferredSize(new Dimension(128,128));
        pnlImagen.add(imagen);

        pnlTitulo.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnlTitulo.add(titulo);

        panelContenedor.add(pnlImagen);
        panelContenedor.add(new JLabel(" "));

        panelContenedor.add(pnlTitulo);
        panelContenedor.add(new JScrollPane(jTable));
        panelContenedor.add(pnlDetalles);
        panelContenedor.add(panelBoton);
    }

    private void configTable() {
        jTable.setGridColor(new Color(88, 214, 141));
        jTable.setPreferredScrollableViewportSize(new Dimension(800, 350));
    }
    private void initControl(){
        Util.ordenarProductos();
        modelo.setColumnIdentifiers(Util.tablaRep);
        String [] fila = new String[modelo.getColumnCount()];
        ArrayList<Pedido> lista = Util.getPedidoArrayList();
        for (Pedido userTable : lista) {
            fila[0] = String.valueOf(userTable.rand());
            fila[1] = userTable.cliente().getNombre();
            fila[2] = userTable.cliente().getDireccion();
            modelo.addRow(fila);
        }
    }

    private void addListeners() {
        regresar.addActionListener(e -> {
            setVisible(false);
            new PrincipalGUI(new ImagenCarrito(350,0));
        });
        jTable.getSelectionModel().addListSelectionListener(e -> {
            int renglon = jTable.getSelectedRow();
            if (renglon != -1) {
                renglonSeleccionado = renglon;
                detalles();
            }
        });
        entregado.addActionListener(e -> {
            if (renglonSeleccionado!=-1){
                Util.pedidoArrayList.remove(renglonSeleccionado);
                actualizarTabla();
                detalles();
            }else {
                JOptionPane.showMessageDialog(this,"Primero selecciona un pedido");
            }
        });
        verProductos.addActionListener(e -> {
            if (renglonSeleccionado!=-1){
                setVisible(false);
                new ProductosPedido(renglonSeleccionado);
            }else {
                JOptionPane.showMessageDialog(this,"Primero selecciona un pedido");
            }
        });
    }
    private void detalles(){
        Pedido pedido = Util.getPedidoArrayList().get(renglonSeleccionado);
        txtNombre.setText(pedido.cliente().getNombre());
        txtApellidoP.setText(pedido.cliente().getApellidoP());
        txtApellidoM.setText(pedido.cliente().getApellidoM());
        txtEdad.setText(String.valueOf(pedido.cliente().getEdad()));
        txtDireccion.setText(pedido.cliente().getDireccion());
        txtTelefono.setText(String.valueOf(pedido.cliente().getTelefono()));
    }

    private void actualizarTabla(){
        modelo.setRowCount(0); // Limpiar los datos de la tabla
        String[] fila = new String[modelo.getColumnCount()];
        ArrayList<Pedido> lista = Util.getPedidoArrayList();
        for (Pedido userTable : lista) {
            fila[0] = String.valueOf(userTable.rand());
            fila[1] = userTable.cliente().getNombre();
            fila[2] = userTable.cliente().getDireccion();
            modelo.addRow(fila);
        }
    }
}
