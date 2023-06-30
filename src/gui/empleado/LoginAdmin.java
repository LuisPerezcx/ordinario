package gui.empleado;

import gui.PrincipalGUI;
import gui.imagen.ImagenCarrito;
import gui.imagen.MiImagen;
import pojo.Empleado;
import util.Util;

import javax.swing.*;
import java.awt.*;

public class LoginAdmin extends JFrame {
    private JPanel panelContenedor, panelLogin;
    private final MiImagen imagen;
    private JButton btnRegresar, btnAceptar;
    private JLabel usuario,contrasena;
    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    public  LoginAdmin(MiImagen imagen){
        initComponents();
        initFrame();
        this.imagen = imagen;
        setPanelContainer();
        addActionListeners();
        add(panelContenedor);
    }
    private void initComponents(){
        panelContenedor = new JPanel();
        btnRegresar = new JButton("Regresar");
        btnAceptar = new JButton("Aceptar");
        panelLogin = new JPanel();

        usuario = new JLabel("Usuario: ");
        contrasena = new JLabel("Contrasena: ");
        txtUsuario = new JTextField();
        txtContrasena = new JPasswordField();
    }
    private void initFrame(){
        setTitle("inicio");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(500,360);
        setVisible(true);
        setLocationRelativeTo(null);
    }
    private void setPanelContainer(){
        panelContenedor.setLayout(new GridLayout(2,1));
        panelContenedor.setPreferredSize(new Dimension(300,300));
        panelContenedor.add(imagen);

        panelLogin.setLayout(new GridLayout(4,2));
        panelLogin.add(usuario);
        panelLogin.add(txtUsuario);
        panelLogin.add(contrasena);
        panelLogin.add(txtContrasena);

        panelLogin.add(new JLabel());
        panelLogin.add(new JLabel());

        panelLogin.add(btnRegresar);
        panelLogin.add(btnAceptar);

        panelContenedor.add(panelLogin);
    }
    private void addActionListeners(){
        btnRegresar.addActionListener(e -> {
            setVisible(false);
            new PrincipalGUI(new ImagenCarrito(350,0));
        });
        btnAceptar.addActionListener(e -> {
            validar();
        });
    }
    public void validar(){

        String Cingresada = txtContrasena.getText();
        String Uingresado = txtUsuario.getText();
        int totalEmpleados = Util.empleadoArrayList.size();
        int cont=0;


        for (Empleado empleado: Util.empleadoArrayList) {
            if(Uingresado.equals(empleado.getCuenta()) && Cingresada.equals(empleado.getContrasena())){
                String tipoUsuario = empleado.getNombreCargo();
                if (tipoUsuario.equals("Administrador")){
                    setVisible(false);
                    new PanelAdministrador(new MiImagen(350,0));
                    cont=-1;
                } else if (tipoUsuario.equals("Repartidor")) {
                    setVisible(false);
                    new Repartidor(new MiImagen(350,0));
                    cont=-1;
                }else {
                    JOptionPane.showMessageDialog(this,"Error");
                }
            }
            cont++;
        }
        if (cont==totalEmpleados){
            JOptionPane.showMessageDialog(this,"Credenciales Invalidas");
        }
    }
}
