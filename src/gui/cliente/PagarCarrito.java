package gui.cliente;

import gui.PrincipalGUI;
import gui.imagen.ImagenCarrito;
import pojo.Carrito;
import pojo.Cliente;
import pojo.Pedido;
import util.Util;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class PagarCarrito extends JFrame {
    private JPanel pnlContenedor;
    private JLabel nombre, apellidoP, apellidoM, direccion, telefono, edad,ine,tarjeta,expiracion,ccv;
    private JTextField txtNombre, txtApellidoP, txtApellidoM, txtEdad, txtDireccion, txtTelefono, txtIne,txtTarjeta,txtExpiracion,txtCcv;
    private JButton btnPagar,btnRegresar;
    Random random = new Random();
    public PagarCarrito(){
        initComponents();
        initFrame();
        setPnlContenedor();
        add(pnlContenedor);
        addListeners();
    }
    private void initFrame(){
        setTitle("inicio");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(500,400);
        setVisible(true);
        setLocationRelativeTo(null);
    }
    private void initComponents(){
        pnlContenedor = new JPanel();
        nombre = new JLabel("Nombre cliente: ");
        apellidoP = new JLabel("Apellido P: ");
        apellidoM = new JLabel("Apellido M: ");
        edad = new JLabel("edad: ");
        direccion = new JLabel("Direccion: ");
        telefono = new JLabel("telefono: ");
        ine = new JLabel("INE: ");
        tarjeta = new JLabel("Numero de tarjeta: ");
        expiracion = new JLabel("Expiracion: ");
        ccv = new JLabel("CCV");


        txtNombre = new JTextField();
        txtApellidoP = new JTextField();
        txtApellidoM = new JTextField();
        txtEdad = new JTextField();
        txtDireccion = new JTextField();
        txtTelefono = new JTextField();
        txtIne = new JTextField();
        txtTarjeta = new JTextField();
        txtExpiracion = new JTextField();
        txtCcv = new JTextField();

        btnPagar = new JButton("Pagar");
        btnRegresar = new JButton("Regresar");
    }
    private void setPnlContenedor(){
        pnlContenedor.setLayout(new GridLayout(15,1));
        pnlContenedor.setPreferredSize(new Dimension(450,400));
        pnlContenedor.add(nombre);
        pnlContenedor.add(txtNombre);
        pnlContenedor.add(apellidoP);
        pnlContenedor.add(txtApellidoP);
        pnlContenedor.add(apellidoM);
        pnlContenedor.add(txtApellidoM);
        pnlContenedor.add(edad);
        pnlContenedor.add(txtEdad);
        pnlContenedor.add(telefono);
        pnlContenedor.add(txtTelefono);
        pnlContenedor.add(direccion);
        pnlContenedor.add(txtDireccion);
        pnlContenedor.add(ine);
        pnlContenedor.add(txtIne);
        pnlContenedor.add(tarjeta);
        pnlContenedor.add(txtTarjeta);
        pnlContenedor.add(expiracion);
        pnlContenedor.add(txtExpiracion);
        pnlContenedor.add(ccv);
        pnlContenedor.add(txtCcv);


        pnlContenedor.add(new JLabel());
        pnlContenedor.add(new JLabel());
        pnlContenedor.add(btnPagar);
        pnlContenedor.add(btnRegresar);
    }
    private void addListeners(){
        btnPagar.addActionListener(e -> {
            registrarCliente();
        });
        btnRegresar.addActionListener(e -> {
            setVisible(false);
            new CarritoGUI(new ImagenCarrito(260,0));
        });
    }

    private void registrarCliente() {
        if (txtNombre.getText().equals("") || txtApellidoP.getText().equals("") || txtApellidoM.getText().equals("") ||
                txtEdad.getText().equals("") || txtDireccion.getText().equals("") || txtTelefono.getText().equals("") ||
                txtIne.getText().equals("") || txtTarjeta.getText().equals("") || txtExpiracion.getText().equals("") ||
                txtCcv.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Llena todos los campos");
            return;
        }

        String nombre = txtNombre.getText();
        String apellidoPat = txtApellidoP.getText();
        String apellidoMat = txtApellidoM.getText();
        int nEdad=-1;
        String edad = txtEdad.getText();
        while (nEdad == -1){
            try {
                nEdad = Integer.parseInt(edad);
            } catch (NumberFormatException e) {
                edad = JOptionPane.showInputDialog(null, "Ingresa una edad valida", "Datos no validos", JOptionPane.PLAIN_MESSAGE);
            }
        }
        String direccion = txtDireccion.getText();
        int nTelefono=-1;
        String telefono = txtTelefono.getText();
        while (nTelefono==-1){
            try {
                nTelefono = Integer.parseInt(telefono);
            }catch (NumberFormatException e){
                telefono = JOptionPane.showInputDialog(null, "Ingresa un numero de telefono valido", "Datos no validos", JOptionPane.PLAIN_MESSAGE);
            }
        }
        String ine = txtIne.getText();

        String numeroTarjeta = txtTarjeta.getText();
        String fechaExp = txtExpiracion.getText();
        String ccv1 = txtCcv.getText();

        while (numeroTarjeta.length() != 16) {
            numeroTarjeta = JOptionPane.showInputDialog(null, "Ingresa un número de tarjeta válido (16 dígitos)", "Datos no válidos", JOptionPane.PLAIN_MESSAGE);
        }

        while (fechaExp.length() != 5) {
            fechaExp = JOptionPane.showInputDialog(null, "Ingresa una fecha de expiración válida (MM/YY)", "Datos no válidos", JOptionPane.PLAIN_MESSAGE);
        }

        while (ccv1.length() != 3) {
            ccv1 = JOptionPane.showInputDialog(null, "Ingresa un CCV válido (3 dígitos)", "Datos no válidos", JOptionPane.PLAIN_MESSAGE);
        }

        ArrayList<Carrito> aux = new ArrayList<>(Util.getCarritoArrayList());
        Util.pedidoArrayList.add(new Pedido(random.nextInt(999),aux,new Cliente(nombre,apellidoPat,apellidoMat,nEdad,direccion,nTelefono,ine)));
        Util.carritoArrayList.clear();

        clear();
        setVisible(false);
        new PrincipalGUI(new ImagenCarrito(350,0));
    }
    private void clear(){
        txtNombre.setText("");
        txtApellidoP.setText("");
        txtApellidoM.setText("");
        txtEdad.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtIne.setText("");
        txtTarjeta.setText("");
        txtExpiracion.setText("");
        txtCcv.setText("");
    }
}
