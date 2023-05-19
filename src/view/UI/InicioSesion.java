package view.UI;

import controller.Controller;
import model.dao.Administrador;
import model.dao.Cliente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
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
                boolean userExiste = Controller.comprobarDatosLogin(nameTextField.getText(), pwdTextField.getText()); //primero se comprueba si los datos introducidos por el usuario en el login son correctos
                if (userExiste) {
                    if (Controller.iniciarSesion(nameTextField.getText(), pwdTextField.getText()) == false) { //se obtienen los datos introducidos por el usuario en los textField y se mandan como parámetro al método iniciarSesion. Este devuelve un boolean para saber si es o no admin
                        try {
                            //si no es admin se muestra la ventana principal de cliente
                            JOptionPane.showMessageDialog(null, "Sesión iniciada correctamente como cliente.");
                            PaginaPrincipalClientes.crearVentanaPaginaPrincipalCliente();
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al mostrar la página principal del cliente.");
                            throw new RuntimeException(ex);
                        }
                        //si es admin se muestra la ventana principal de admin
                    } else if (Controller.iniciarSesion(nameTextField.getText(), pwdTextField.getText())) {
                        try {
                            JOptionPane.showMessageDialog(null, "Sesión iniciada correctamente como administrador.");
                            PaginaPrincipalAdmin.crearVentanaPaginaPrincipalAdmin();
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al mostrar la página principal del administrador.");
                            throw new RuntimeException(ex);
                        }
                    }
                    inicioSesion.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Los datos introducidos son incorrectos. Vuelte a intentarlo.", "Error", JOptionPane.ERROR_MESSAGE);
                    inicioSesion.dispose();
                    crearVentanaInicioSesion();
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


