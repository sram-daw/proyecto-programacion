package view.UI;

import controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Clase Inicio sesion.
 */
public class InicioSesion extends JFrame {
    private JLabel titulo;
    private JTextField nameTextField;
    private JButton iniciarSesionButton;
    private JPanel container;
    private JLabel nameLabel;
    private JLabel pwdLabel;
    private JPasswordField pwdField;
    private JPanel panelBoton;

    /**
     * Instanciamos Inicio sesion.
     */
    static InicioSesion inicioSesion = new InicioSesion();


    /**
     * Constructor de Inicio sesion.
     */
//Botón iniciar sesión
    public InicioSesion() {
        iniciarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pwd = new String(pwdField.getPassword());
                boolean userExiste = Controller.comprobarDatosLogin(nameTextField.getText(), pwd); //primero se comprueba si los datos introducidos por el usuario en el login son correctos
                if (userExiste) {
                    boolean isAdmin = Controller.iniciarSesion(nameTextField.getText(), pwd); //se obtienen los datos introducidos por el usuario en los textField/pwdfield y se mandan como parámetro al método iniciarSesion. Este devuelve un boolean para saber si es o no admin
                    if (!isAdmin) {
                        try {
                            //si no es admin se muestra la ventana principal de cliente
                            JOptionPane.showMessageDialog(null, "Sesión iniciada correctamente como cliente.");
                            PaginaPrincipalClientes.crearVentanaPaginaPrincipalCliente();
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al mostrar la página principal del cliente.");
                            throw new RuntimeException(ex);
                        }
                        //si es admin se muestra la ventana principal de admin
                    } else if (isAdmin) {
                        try {
                            JOptionPane.showMessageDialog(null, "Sesión iniciada correctamente como administrador.");
                            PaginaPrincipalAdmin.crearVentanaPaginaPrincipalAdmin();
                            PaginaPrincipalAdmin.mostrarMensajeStock(); //lanza un mensaje usando el observer para avisar de que el stock es menor que 10 unidades en algún producto
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

    /**
     * Crear ventana inicio sesion.
     */
    public static void crearVentanaInicioSesion() {
        inicioSesion.setContentPane(inicioSesion.container);
        inicioSesion.setTitle("Inicio de sesión");
        inicioSesion.setBounds(630, 250, 600, 500);
        inicioSesion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //miniatura de ventana
        ImageIcon logo= new ImageIcon("./././resources/logo_lurpiazon_2.png");
        inicioSesion.setIconImage(logo.getImage()); //thumbnail del programa
        inicioSesion.setVisible(true);
    }
}


