package view.UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.Controller;

public class Registro extends JFrame {
    private JPanel container;
    private JPanel containerForm;
    private JTextField nombreUsuario;
    private JTextField direccion;
    private JTextField tlf;
    private JTextField cp;
    private JTextField email;
    private JTextField nombre;
    private JTextField apellido;
    private JButton registrarseButton;
    private JTextField pwd;

    private static Registro registro; //instancia de la propia clase para poder usarla en distintos métodos

    public Registro() {
        registrarseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Se llama al método registrarse del Controller
                Boolean isAddOk = Controller.registrarse(nombreUsuario.getText(), pwd.getText(), direccion.getText(), tlf.getText(), cp.getText(), email.getText(), nombre.getText(), apellido.getText());
                if (isAddOk) {
                    JOptionPane.showMessageDialog(null, "Registro correcto.");
                } else {
                    JOptionPane.showMessageDialog(null, "Ha habido un error en el registro.");
                }
                registro.dispose();
            }
        });
    }

    public static void crearVentanaRegistro() {
        registro = new Registro();
        registro.setContentPane(registro.container);
        registro.setTitle("Registro");
        registro.setBounds(630, 250, 650, 600);
        registro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        registro.setVisible(true);
    }
}