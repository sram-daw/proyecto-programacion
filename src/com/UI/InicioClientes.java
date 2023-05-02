package com.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InicioClientes extends JFrame {//extendemos de JFrame para trabajar con la ventana
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


    public InicioClientes() {

        //añadimos el contenindo a la ventana
        setContentPane(PanelPrincipal);
        //Añadimos los items al menu
        ClienteMenu.add(ItemCuenta);
        ClienteMenu.add(ItemPedidos);
        //Mostramos mensaje por consola de la funcionalidad que tendra el boton
        logoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Este boton llevara al usuario a la pagina de inicio");
            }
        });

    }

    public static void crearVentana() {

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
