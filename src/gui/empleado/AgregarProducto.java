package gui.empleado;

import gui.imagen.MiImagen;
import pojo.Producto;
import util.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class AgregarProducto extends JFrame implements ItemListener {
    private JPanel pnlContenedor;
    private JLabel nombreProducto, marca, existencia, departamento,descripcion,precio;
    private JTextField txtNombre, txtMarca, txtExistencia,txtDescripcion,txtPrecio;
    private JComboBox<String> departamentoCombo;
    private JButton btnRegistrar,btnRegresar;
    private static ArrayList<String> stringArrayList = new ArrayList<>();
    private static ArrayList<Producto> productoArrayList = Util.getProductoArrayList();
    private String departamento1 ="";
    public AgregarProducto(){
        initComponents();
        initFrame();
        setPnlContenedor();
        add(pnlContenedor);
        addListeners();
        llenarCombo();
    }
    private void initFrame(){
        setTitle("inicio");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(400,260);
        setVisible(true);
        setLocationRelativeTo(null);
    }
    private void initComponents(){
        pnlContenedor = new JPanel();
        nombreProducto = new JLabel("Nombre Producto: ");
        marca = new JLabel("Marca: ");
        existencia = new JLabel("Extencia: ");
        departamento = new JLabel("Departamento: ");
        descripcion = new JLabel("Descripcion: ");
        precio = new JLabel("Precio: ");
        txtNombre = new JTextField();
        txtMarca = new JTextField();
        txtExistencia = new JTextField();
        txtDescripcion = new JTextField();
        txtPrecio = new JTextField();

        departamentoCombo = new JComboBox<>();
        btnRegistrar = new JButton("Registrar");
        btnRegresar = new JButton("Regresar");
    }
    private void setPnlContenedor(){
        pnlContenedor.setLayout(new GridLayout(8,1));
        pnlContenedor.add(nombreProducto);
        pnlContenedor.add(txtNombre);
        pnlContenedor.add(marca);
        pnlContenedor.add(txtMarca);
        pnlContenedor.add(existencia);
        pnlContenedor.add(txtExistencia);
        pnlContenedor.add(descripcion);
        pnlContenedor.add(txtDescripcion);
        pnlContenedor.add(precio);
        pnlContenedor.add(txtPrecio);
        pnlContenedor.add(departamento);
        pnlContenedor.add(departamentoCombo);
        pnlContenedor.add(new JLabel());
        pnlContenedor.add(new JLabel());
        pnlContenedor.add(btnRegistrar);
        pnlContenedor.add(btnRegresar);
    }
    private void llenarCombo(){
        stringArrayList = Util.getDepartamentoArrayList();
        for (String s: stringArrayList) {
            departamentoCombo.addItem(s);
        }
    }
    private void addListeners(){
        btnRegistrar.addActionListener(e -> {
            registrarProducto();
        });
        btnRegresar.addActionListener(e -> {
            setVisible(false);
            new PanelAdministrador(new MiImagen(350,0));
        });
        departamentoCombo.addItemListener(this);
    }
    private void registrarProducto() {
        if (txtNombre.getText().equals("") || txtMarca.getText().equals("") || txtExistencia.getText().equals("") || txtPrecio.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Llena todos los campos necesarios");
            return;
        }
        String nombre = txtNombre.getText();
        String marca = txtMarca.getText();
        int nExistencias = -1;
        String existencias = txtExistencia.getText();
        while (nExistencias == -1){
            try {
                nExistencias = Integer.parseInt(existencias);
                if (nExistencias <= 0) {
                    existencias = JOptionPane.showInputDialog(null, "Ingresa un número de existencias válido (mayor a cero)", "Datos no válidos", JOptionPane.PLAIN_MESSAGE);
                    nExistencias = -1;
                }
            } catch (NumberFormatException e) {
                existencias = JOptionPane.showInputDialog(null, "Ingresa un numero de existencias valido", "Datos no validos", JOptionPane.PLAIN_MESSAGE);
            }
        }
        String descripcion = txtDescripcion.getText();
        double precio = -1.0;
        String preciotxt = txtExistencia.getText();
        while (precio == -1.0) {
            try {
                precio = Double.parseDouble(preciotxt);
                if (precio <= 0) {
                    preciotxt = JOptionPane.showInputDialog(null, "Ingresa un precio válido (mayor a cero)", "Datos no válidos", JOptionPane.PLAIN_MESSAGE);
                    precio = -1.0;
                }
            } catch (NumberFormatException e) {
                preciotxt = JOptionPane.showInputDialog(null, "Ingresa un precio valido", "Datos no válidos", JOptionPane.PLAIN_MESSAGE);
            }
        }

        productoArrayList.add(new Producto(nombre,marca,nExistencias,descripcion,precio,departamento1));
        clear();
    }
    private void clear(){
        txtNombre.setText("");
        txtMarca.setText("");
        txtExistencia.setText("");
        txtDescripcion.setText("");
        txtPrecio.setText("");
        departamentoCombo.setSelectedIndex(0);
    }
    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getSource() == departamentoCombo){
            departamento1 = (String) departamentoCombo.getSelectedItem();
        }
    }
}
