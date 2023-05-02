package view.UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaginaPrincipalClientes extends JFrame {//extendemos de JFrame para trabajar con la ventana
    private JPanel PanelPrincipal;
    private JButton logoButton;
    private JButton ropaButton;
    private JButton hogarButton;
    private JButton alimentacionButton;
    private JButton mascotasButton;
    private JPanel Usuario;
    private JPanel CategoriasPanel;
    private JMenu ClienteMenu;
    private JMenuItem ItemCuenta;
    private JMenuItem ItemPedidos;
    private JMenuBar Barramenu;

    static PaginaPrincipalClientes paginaPrincipalClientes =new PaginaPrincipalClientes();

    public PaginaPrincipalClientes() {


        //Mostramos mensaje por consola de la funcionalidad que tendra el boton
        logoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Este boton llevara al usuario a la pagina de inicio");
            }
        });

    }

    public static void crearVentanaPaginaPrincipalCliente() {
        //añadimos el contenindo a la ventana
        paginaPrincipalClientes.setContentPane(paginaPrincipalClientes.PanelPrincipal);
        //Añadimos los items al menu
        paginaPrincipalClientes.ClienteMenu.add(paginaPrincipalClientes.ItemCuenta);
        paginaPrincipalClientes.ClienteMenu.add(paginaPrincipalClientes.ItemPedidos);
        paginaPrincipalClientes.setBounds(630, 250, 1000, 700);
        paginaPrincipalClientes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        paginaPrincipalClientes.setVisible(true);
    }

}
