package view.UI;

import controller.Controller;
import model.dao.Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Clase Ventana cuenta cliente.
 */
public class VentanaCuentaCliente extends JFrame {
    private JPanel panelPrincipalCuenta;
    private JPanel panelMenuCuenta;
    private JPanel panelDatosCliente;
    private JButton botonEditarPerfil;
    private JButton botonPedidosCliente;
    private JButton botonVolverAtras;
    private JButton botonSalir;
    private JPanel panelEditarDatos;
    private JTextField txtNombreUsuario;
    private JTextField txtNombre;
    private JLabel labelNombre;
    private JTextField txtApellido;
    private JLabel labelApellido;
    private JTextField txtDireccion;
    private JLabel labelDireccion;
    private JTextField txtTelefono;
    private JLabel labelTelefono;
    private JTextField txtEmail;
    private JLabel labelEmail;
    private JTextField txtCP;
    private JLabel labelCP;
    private JLabel labelNombreUsuario;
    private JTextField txtPwd;
    private JLabel labelContraseña;
    private JButton botonGuardarCambios;
    private JButton verPerfilBoton;
    private JPanel panelPerfil;
    private JLabel labelPerfil;
    private JPanel panelEditarPerfil;
    private JLabel labelEditarPerfil;

    private JButton botonPresionadoActual;
    /**
     * Instancia Ventana cuenta cliente.
     */
    static VentanaCuentaCliente ventanaCuentaCliente = new VentanaCuentaCliente();


    /**
     * Constructor de Ventana cuenta cliente.
     */
    public VentanaCuentaCliente() {
        botonVolverAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PaginaPrincipalClientes.crearVentanaPaginaPrincipalCliente();
                    ventanaCuentaCliente.dispose();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Ha habido un error al volver a la tienda.", "Error", JOptionPane.ERROR_MESSAGE);
                    throw new RuntimeException(ex);
                }
            }
        });

        verPerfilBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //añadimos los datos del cliente para que pueda verlos al acceder a la ventana y cuando pulse verPerfilBoton
                String nombreUsuario = Controller.clienteLogado.getNombreUsuario();
                String pwd = Controller.clienteLogado.getPwd();
                Cliente cliente = Controller.clienteLogado;
                txtNombre.setText(cliente.getNombre());
                txtApellido.setText(cliente.getApellido());
                txtDireccion.setText(cliente.getDireccion());
                txtTelefono.setText(cliente.getNumTelf());
                txtEmail.setText(cliente.getEmail());
                txtCP.setText(cliente.getCp());
                txtNombreUsuario.setText(cliente.getNombreUsuario());
                txtPwd.setText(cliente.getPwd());

                //hacemos que los JTextField no esten habilitados para que el usuario sepa que aqui no se pueden realizar cambios
                txtNombre.setEnabled(false);
                txtApellido.setEnabled(false);
                txtDireccion.setEnabled(false);
                txtTelefono.setEnabled(false);
                txtEmail.setEnabled(false);
                txtCP.setEnabled(false);
                txtNombreUsuario.setEnabled(false);
                txtPwd.setEnabled(false);

                //hacemos que el botonGuardarCambios no este visible cuando el usuario pulse este boton
                ventanaCuentaCliente.botonGuardarCambios.setVisible(false);

                //hacemos que el boton cambie de color cuando este seleccionado
                if (botonPresionadoActual != null) {
                    botonPresionadoActual.setBackground(UIManager.getColor("Button.background"));
                }
                botonPresionadoActual = verPerfilBoton;
                botonPresionadoActual.setBackground(new Color(185, 206, 172));

                //hacemos que el panelPerfil solo sea visible en este boton
                panelEditarPerfil.setVisible(false);
                panelPerfil.setVisible(true);
            }
        });
        botonEditarPerfil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nombreUsuario =Controller.clienteLogado.getNombreUsuario();
                String pwd = Controller.clienteLogado.getPwd();
                Cliente cliente = Controller.clienteLogado;
                txtNombre.setText(cliente.getNombre());
                txtApellido.setText(cliente.getApellido());
                txtDireccion.setText(cliente.getDireccion());
                txtTelefono.setText(cliente.getNumTelf());
                txtEmail.setText(cliente.getEmail());
                txtCP.setText(cliente.getCp());
                txtNombreUsuario.setText(cliente.getNombreUsuario());
                txtPwd.setText(cliente.getPwd());

                ventanaCuentaCliente.txtNombre.setEnabled(true);
                ventanaCuentaCliente.txtApellido.setEnabled(true);
                ventanaCuentaCliente.txtDireccion.setEnabled(true);
                ventanaCuentaCliente.txtTelefono.setEnabled(true);
                ventanaCuentaCliente.txtEmail.setEnabled(true);
                ventanaCuentaCliente.txtCP.setEnabled(true);
                ventanaCuentaCliente.txtNombreUsuario.setEnabled(true);
                ventanaCuentaCliente.txtPwd.setEnabled(true);

                panelDatosCliente.setVisible(true);
                ventanaCuentaCliente.botonGuardarCambios.setVisible(true);

                //hacemos que el boton cambie de color cuando este seleccionado
                if (botonPresionadoActual != null) {
                    botonPresionadoActual.setBackground(UIManager.getColor("Button.background"));
                }
                botonPresionadoActual = botonEditarPerfil;
                botonPresionadoActual.setBackground(new Color(185, 206, 172));

                //hacemos que el panelEditarPerfil solo sea visible en este boton
                panelEditarPerfil.setVisible(true);
                panelPerfil.setVisible(false);
            }
        });
        botonPedidosCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PaginaPedidosCliente.crearVentanaPaginaPedidosCliente();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                ventanaCuentaCliente.dispose();

            }
        });
        botonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
                System.out.println("El boton salir funciona para la cuenta del cliente");
            }
        });
        botonGuardarCambios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cliente cliente=Controller.clienteLogado;
                // Obtenemos los datos modificados del cliente desde los campos de texto
                String nuevoNombre = txtNombre.getText();
                String nuevoApellido = txtApellido.getText();
                String nuevaDireccion = txtDireccion.getText();
                String nuevoTelefono = txtTelefono.getText();
                String nuevoEmail = txtEmail.getText();
                String nuevoCP = txtCP.getText();
                String nuevoNombreUsuario = txtNombreUsuario.getText();
                String nuevaContraseña = txtPwd.getText();

                // Actualizamos los datos del cliente con los nuevos valores
                cliente.setNombre(nuevoNombre);
                cliente.setApellido(nuevoApellido);
                cliente.setDireccion(nuevaDireccion);
                cliente.setNumTelf(nuevoTelefono);
                cliente.setEmail(nuevoEmail);
                cliente.setCp(nuevoCP);
                cliente.setNombreUsuario(nuevoNombreUsuario);
                cliente.setPwd(nuevaContraseña);

                try {
                    // Actualizamos los datos del cliente en la base de datos
                    boolean actualizarDatosIsOk = Controller.datosClienteActualizados(cliente);

                    if (actualizarDatosIsOk) {
                        JOptionPane.showMessageDialog(null, "Los datos del cliente se han actualizado correctamente.");
                        //actulizar el objeto clienteLogado
                        Controller.actualizarObjetoCliente();
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo actualizar los datos del cliente.", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    ventanaCuentaCliente.dispose();
                    crearVentanaCuentaCliente();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

        });

    }

    /**
     * Crear ventana cuenta cliente.
     *
     * @throws SQLException la excepción sql
     */
    public static void crearVentanaCuentaCliente() throws SQLException {
        ventanaCuentaCliente.setContentPane(ventanaCuentaCliente.panelPrincipalCuenta);
        ventanaCuentaCliente.setTitle("Mi cuenta");
        ventanaCuentaCliente.setBounds(430, 10, 800, 800);
        ventanaCuentaCliente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaCuentaCliente.setVisible(true);

        //hacemos que el botonGuardarCambios solo sea visible al pulsar el botonEditarPerfil
        ventanaCuentaCliente.botonGuardarCambios.setVisible(false);

        //Poniendo el actionListener en null logramos que nos muestre el contenido del boton ver perfil al crear la ventana
        ActionListener verPerfilBotonActionListener = ventanaCuentaCliente.verPerfilBoton.getActionListeners()[0];
        // Llamamos al ActionListener del botón "verPerfilBoton"
        verPerfilBotonActionListener.actionPerformed(null);

        //hacemos que el panelEditarPerfil solo sea visible al crear la ventana
        ventanaCuentaCliente.panelEditarPerfil.setVisible(true);
        ventanaCuentaCliente.panelPerfil.setVisible(false);

    }
}