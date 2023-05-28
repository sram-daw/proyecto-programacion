package view.UI;

import controller.Controller;
import model.dao.Cliente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

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
    static VentanaCuentaCliente ventanaCuentaCliente = new VentanaCuentaCliente();


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
            }
        });
        botonPedidosCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                panelDatosCliente.setVisible(false);

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
    public static void crearVentanaCuentaCliente() throws SQLException {
        ventanaCuentaCliente.setContentPane(ventanaCuentaCliente.panelPrincipalCuenta);
        ventanaCuentaCliente.setTitle("Mi cuenta");
        ventanaCuentaCliente.setBounds(430, 80, 800, 700);
        ventanaCuentaCliente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaCuentaCliente.setVisible(true);

        //añadimos los datos del cliente para que pueda verlos al acceder a la ventana
        String nombreUsuario = Controller.clienteLogado.getNombreUsuario();
        String pwd = Controller.clienteLogado.getPwd();
        Cliente cliente = Controller.clienteLogado;
        ventanaCuentaCliente.txtNombre.setText(cliente.getNombre());
        ventanaCuentaCliente.txtApellido.setText(cliente.getApellido());
        ventanaCuentaCliente.txtDireccion.setText(cliente.getDireccion());
        ventanaCuentaCliente.txtTelefono.setText(cliente.getNumTelf());
        ventanaCuentaCliente.txtEmail.setText(cliente.getEmail());
        ventanaCuentaCliente.txtCP.setText(cliente.getCp());
        ventanaCuentaCliente.txtNombreUsuario.setText(cliente.getNombreUsuario());
        ventanaCuentaCliente.txtPwd.setText(cliente.getPwd());


        ventanaCuentaCliente.txtNombre.setEnabled(false);
        ventanaCuentaCliente.txtApellido.setEnabled(false);
        ventanaCuentaCliente.txtDireccion.setEnabled(false);
        ventanaCuentaCliente.txtTelefono.setEnabled(false);
        ventanaCuentaCliente.txtEmail.setEnabled(false);
        ventanaCuentaCliente.txtCP.setEnabled(false);
        ventanaCuentaCliente.txtNombreUsuario.setEnabled(false);
        ventanaCuentaCliente.txtPwd.setEnabled(false);
        //hacemos que el botonGuardarCambios solo sea visible al pulsar el botonEditarPerfil
        ventanaCuentaCliente.botonGuardarCambios.setVisible(false);

    }
}
