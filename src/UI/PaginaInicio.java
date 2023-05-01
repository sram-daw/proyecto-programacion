package UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaginaInicio extends JFrame { //para poder usar los métodos de Jframe en el método crearVentanaInicial necesita extender de la interfaz Jframe
    private JPanel container;
    private JButton registrarseButton;
    private JButton iniciarSesiónButton;
    static PaginaInicio paginaInicio = new PaginaInicio();

    public PaginaInicio() {
        registrarseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Registro.crearVentanaRegistro();
                paginaInicio.dispose();
            }
        });
    }


    public static void crearVentanaInicial() {
        paginaInicio.setContentPane(paginaInicio.container);
        paginaInicio.setTitle("Lurpiazon");
        paginaInicio.setBounds(630, 250, 600, 500);
        paginaInicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        paginaInicio.setVisible(true);
    }
}
