import com.UI.InicioClientes;

import javax.swing.*;

public class Controller {
    public static void main(String[] args) {
        //pantalla de inicio de clientes
        JFrame ventanaClientes = new InicioClientes();
        //damos tamaño a la pantalla
        ventanaClientes.setSize(1200, 900);
        //titulo de la pantalla
        ventanaClientes.setTitle("Inicio clientes");
        ventanaClientes.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //indicamos mediante valor booleano que la pantalla sea visible
        ventanaClientes.setVisible(true);
    }
}