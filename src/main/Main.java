package main;

import gui.PrincipalGUI;
import gui.imagen.ImagenCarrito;
import util.Util;

public class Main {
    public static void main(String[] args) {
        Util.crearUsuariosDefault();
        Util.crearDepartamentosDefault();
        new PrincipalGUI(new ImagenCarrito(350,0));
    }
}
