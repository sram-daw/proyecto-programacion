package view.UI;

import controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class InicioSesion extends JFrame {
    private JLabel titulo;
    private JTextField nameTextField;
    private JTextField pwdTextField;
    private JButton iniciarSesionButton;
    private JPanel container;
    private JLabel nameLabel;
    private JLabel pwdLabel;

    static InicioSesion inicioSesion = new InicioSesion();


    //Botón iniciar sesión
    public InicioSesion() {
        iniciarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Se llama al método iniciar sesión del Controller
                HashMap<String, Boolean> resultadoLogin = Controller.iniciarSesion(nameTextField.getText(), pwdTextField.getText());
                if (resultadoLogin.get("isInicioSesionOk")) {
                    if (!resultadoLogin.get("isAdmin")) {
                        //añadir aquí la pantalla principal que vería el usuario al iniciar sesión.
                        PaginaPrincipalClientes.crearVentanaPaginaPrincipalCliente();
                    } else {
                        //página inicial que verán los admins

                    }
                    inicioSesion.dispose();
                } else {
                    crearVentanaInicioSesion(); //si es incorrecto el inicio de sesión se vuelve a mostrar la ventana de inicio de sesion
                }
            }
        });
    }

    public static void crearVentanaInicioSesion() {
        inicioSesion.setContentPane(inicioSesion.container);
        inicioSesion.setTitle("Inicio de sesión");
        inicioSesion.setBounds(630, 250, 600, 500);
        inicioSesion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inicioSesion.setVisible(true);
    }
}

