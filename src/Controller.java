import com.UI.InicioClientes;

import javax.swing.*;

public class Controller {
    public static void main(String[] args) {
        JFrame ventanaClientes = new InicioClientes();
        ventanaClientes.setSize(1200, 900);
        ventanaClientes.setTitle("Inicio clientes");
        ventanaClientes.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        ventanaClientes.setVisible(true);
    }
}