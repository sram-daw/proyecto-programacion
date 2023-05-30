package view.UI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Clase Pagina inicio.
 */
public class PaginaInicio extends JFrame { //para poder usar los métodos de Jframe en el método crearVentanaInicial necesita extender de la interfaz Jframe
    private JPanel container;
    private JButton registrarseButton;
    private JButton iniciarSesiónButton;
    private JLabel logoLabel;
    /**
     * Instanciar la Pagina inicio.
     */
    static PaginaInicio paginaInicio = new PaginaInicio();

    /**
     * Constructor Pagina inicio.
     */
//Botón registrarse
    public PaginaInicio() {
        registrarseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Registro.crearVentanaRegistro();
                paginaInicio.dispose(); //se cierra la ventana tras pulsar el botón
            }
        });

        //Botón iniciar sesión
        iniciarSesiónButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InicioSesion.crearVentanaInicioSesion();
                paginaInicio.dispose();
            }
        });
    }

    /**
     * Crear ventana inicial.
     */
    public static void crearVentanaInicial() {
        paginaInicio.setContentPane(paginaInicio.container);
        paginaInicio.setTitle("Lurpiazon");
        paginaInicio.setBounds(630, 250, 600, 500);
        paginaInicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        paginaInicio.setVisible(true);
        //logo
        try {
            BufferedImage img = ImageIO.read(new File("resources/logo_lurpiazon_1.png"));
            Image scaledImg = img.getScaledInstance(210, 70, Image.SCALE_AREA_AVERAGING);
            paginaInicio.logoLabel.setIcon(new ImageIcon(scaledImg));
        } catch (IOException ex) {
            ex.getMessage();
        }

        //miniatura de ventana
        ImageIcon logo= new ImageIcon("./././resources/logo_lurpiazon_2.png");
        paginaInicio.setIconImage(logo.getImage()); //thumbnail del programa
    }
}
