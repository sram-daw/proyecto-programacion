package view.UI;

import controller.Controller;
import model.dao.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaginaPrincipalAdmin extends JFrame {
    private JPanel PanelPrincipalAdmin;
    private JPanel PanelMenuAdmin;
    private JButton botonSalir;
    private JButton botonPedidos;
    private JButton botonClientes;
    private JButton botonAlmacen;
    private JPanel panelTabla;
    private JScrollPane scrollTablas;
    private JPanel panelAlmacen;
    private JPanel panelOrdenar;
    private JPanel panelStock;
    private JSpinner spinnerStock;
    private JButton aumentarStock;
    private JButton ordenAscendente;
    private JLabel labelStock;
    private JPanel panelClientesAdmin;
    private JTextField txtTelefono;
    private JButton botonBuscarTelefono;
    private JLabel labelIntroduceTelefono;
    private JPanel panelPedidosAdmin;
    private JTextField txtIdPedido;
    private JButton botonBuscarID;
    private JLabel labelIntroducirIdPedido;
    private JPanel panelLabelPedidos;
    private JLabel labelPedidos;
    private JPanel panelBuscador;
    private JPanel panelBuscadorClientes;
    private JPanel panelLabelClientes;
    private JLabel labelClientes;
    private JPanel panelAnadirOrdenar;
    private JPanel panelLabelAlmacen;
    private JLabel labelAlmacen;
    static JTable tablaPedidosAdmin;
    static JTable tablaClientesAdmin;
    static JTable tablaAlmacenAdmin;
    private JButton botonPresionadoActual;
    static PaginaPrincipalAdmin paginaPrincipalAdmin = new PaginaPrincipalAdmin();
    static Controller controller = new Controller();
    private static boolean mensajeMostrado = false;

    public PaginaPrincipalAdmin() {

        botonPedidos.setBackground(UIManager.getColor("Button.background"));
        botonClientes.setBackground(UIManager.getColor("Button.background"));
        botonAlmacen.setBackground(UIManager.getColor("Button.background"));
        botonPedidos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titulosEncabezadoPedidos[] = {"ID Pedido", "ID Usuario", "Fecha", "Precio Total"};
                tablaPedidosAdmin = new JTable();
                DefaultTableModel modeloPedidos = (DefaultTableModel) tablaPedidosAdmin.getModel();
                modeloPedidos.setColumnIdentifiers(titulosEncabezadoPedidos);
                HistorialPedidosTotal listaPedidos = null;
                try {
                    listaPedidos = Controller.agregarTablaPedidos();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error al intentar recuperar los pedidos.");
                    System.out.println(ex.getLocalizedMessage());
                    throw new RuntimeException(ex);
                }
                ArrayList<Pedido> conjuntoPedidos = listaPedidos.getTotalPedidos();

                int y = 0;
                for (Pedido p : conjuntoPedidos) {
                    String[] dataPeidos = {String.valueOf(conjuntoPedidos.get(y).getIdPedido()), String.valueOf(conjuntoPedidos.get(y).getCliente().getIdUsuario()), String.valueOf(conjuntoPedidos.get(y).getFecha()), String.valueOf(conjuntoPedidos.get(y).getPrecio())};
                    modeloPedidos.addRow(dataPeidos);
                    y++;
                }
                paginaPrincipalAdmin.scrollTablas.setViewportView(tablaPedidosAdmin);
                paginaPrincipalAdmin.panelAlmacen.setVisible(false);
                paginaPrincipalAdmin.scrollTablas.setVisible(true);
                //Hacemos que el panelClientesAdmin no sea visible al pulsar este boton
                paginaPrincipalAdmin.panelClientesAdmin.setVisible(false);
                //Hacemos que el panelPedidosAdmin sea visible al pulsar este boton
                paginaPrincipalAdmin.panelPedidosAdmin.setVisible(true);

                //hacemos que el boton cambie de color cuando este seleccionado
                if (botonPresionadoActual != null) {
                    botonPresionadoActual.setBackground(UIManager.getColor("Button.background"));
                }
                botonPresionadoActual = botonPedidos;
                botonPresionadoActual.setBackground(new Color(168, 239, 208));

            }
        });
        botonClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titulosEncabezadoCliente[] = {"ID Usuario", "Nombre Usuario", "Nombre", "Apellido", "Contraseña", "Direccion", "Telefono", "Email", "CP"};
                tablaClientesAdmin = new JTable();
                DefaultTableModel modeloClientes = (DefaultTableModel) tablaClientesAdmin.getModel();
                modeloClientes.setColumnIdentifiers(titulosEncabezadoCliente);
                ListaClientes listaClientes = null;
                try {
                    listaClientes = Controller.agregarTablaCliente();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error al intentar recuperar los datos de los clientes.");
                    System.out.println(ex.getLocalizedMessage());
                    throw new RuntimeException(ex);
                }
                ArrayList<Cliente> grupoClientes = listaClientes.getListaClientes();

                int j = 0;
                for (Cliente c : grupoClientes) {
                    String[] dataCliente = {String.valueOf(grupoClientes.get(j).getIdUsuario()), grupoClientes.get(j).getNombreUsuario(), grupoClientes.get(j).getNombre(), grupoClientes.get(j).getApellido(), grupoClientes.get(j).getPwd(), grupoClientes.get(j).getDireccion(), grupoClientes.get(j).getNumTelf(), grupoClientes.get(j).getEmail(), grupoClientes.get(j).getCp()};
                    modeloClientes.addRow(dataCliente);
                    j++;
                }
                paginaPrincipalAdmin.scrollTablas.setViewportView(tablaClientesAdmin);
                paginaPrincipalAdmin.panelAlmacen.setVisible(false);
                paginaPrincipalAdmin.scrollTablas.setVisible(true);
                //Hacemos que el panelClientesAdmin sea visible al pulsar este boton
                paginaPrincipalAdmin.panelClientesAdmin.setVisible(true);
                //Hacemos que el panelPedidosAdmin no sea visible al pulsar este boton
                paginaPrincipalAdmin.panelPedidosAdmin.setVisible(false);

                //hacemos que el boton cambie de color cuando este seleccionado
                if (botonPresionadoActual != null) {
                    botonPresionadoActual.setBackground(UIManager.getColor("Button.background"));
                }
                botonPresionadoActual = botonClientes;
                botonPresionadoActual.setBackground(new Color(168, 239, 208));

            }
        });
        botonAlmacen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titulosEncabezadoAlmacen[] = {"ID Producto", "Nombre", "Precio", "ID Categoría", "Categoría", "Stock"};
                tablaAlmacenAdmin = new JTable();
                DefaultTableModel modeloAlmacen = (DefaultTableModel) tablaAlmacenAdmin.getModel();
                modeloAlmacen.setColumnIdentifiers(titulosEncabezadoAlmacen);
                Catalogo catalogo = null;
                try {
                    catalogo = Controller.agregarTablaAlmacen();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error al intentar recuperar los productos del almacén.");
                    System.out.println(ex.getLocalizedMessage());
                    throw new RuntimeException(ex);
                }
                ArrayList<ProductoEnStock> productoEnStocks = catalogo.getCatalogo();

                int i = 0;
                for (ProductoEnStock a : productoEnStocks) {
                    String[] dataAlmacen = {String.valueOf(productoEnStocks.get(i).getIdProducto()), productoEnStocks.get(i).getNombre(), String.valueOf(productoEnStocks.get(i).getPrecio()), String.valueOf(productoEnStocks.get(i).getCategoriaID()), productoEnStocks.get(i).getCategoriaNombre(), String.valueOf(productoEnStocks.get(i).getStock())};
                    modeloAlmacen.addRow(dataAlmacen);
                    i++;
                }
                paginaPrincipalAdmin.scrollTablas.setViewportView(tablaAlmacenAdmin);

                //Spinner para poder añadir stock a la tablaAlmacen
                SpinnerNumberModel spinnerModel = (SpinnerNumberModel) paginaPrincipalAdmin.spinnerStock.getModel();
                spinnerModel.setMaximum(50); // Establecer los valores mínimo y máximo
                spinnerModel.setMinimum(10);
                spinnerModel.setStepSize(10); // Establecer el incremento
                spinnerModel.setValue(10);//Valor inicial
                paginaPrincipalAdmin.panelAlmacen.setVisible(true);

                paginaPrincipalAdmin.scrollTablas.setVisible(true);
                //Hacemos que el panelClientesAdmin no sea visible al pulsar este boton
                paginaPrincipalAdmin.panelClientesAdmin.setVisible(false);
                //Hacemos que el panelPedidosAdmin no sea visible al pulsar este boton
                paginaPrincipalAdmin.panelPedidosAdmin.setVisible(false);

                //hacemos que el boton cambie de color cuando este seleccionado
                if (botonPresionadoActual != null) {
                    botonPresionadoActual.setBackground(UIManager.getColor("Button.background"));
                }
                botonPresionadoActual = botonAlmacen;
                botonPresionadoActual.setBackground(new Color(168, 239, 208));
            }
        });
        botonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);
            }
        });

        ordenAscendente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ResultSet resultadoStock = Controller.ordenAscendente();

                    DefaultTableModel modeloAlmacen = (DefaultTableModel) tablaAlmacenAdmin.getModel();

                    // Limpiar el modelo de la tabla
                    modeloAlmacen.setRowCount(0);

                    // Obtener el número de columnas en el ResultSet
                    ResultSetMetaData metaData = resultadoStock.getMetaData();
                    int columnCount = metaData.getColumnCount();

                    // Iterar sobre los resultados y agregarlos a la tabla
                    while (resultadoStock.next()) {
                        Object[] rowData = new Object[columnCount];
                        for (int i = 1; i <= columnCount; i++) {
                            rowData[i - 1] = resultadoStock.getObject(i);
                        }
                        modeloAlmacen.addRow(rowData);
                    }

                    // Cerrar el ResultSet después de usarlo
                    resultadoStock.close();

                    // Actualizar la vista de la tabla
                    tablaAlmacenAdmin.repaint();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                paginaPrincipalAdmin.scrollTablas.setVisible(true);
            }
        });

        aumentarStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idProducto = Integer.parseInt(tablaAlmacenAdmin.getValueAt(tablaAlmacenAdmin.getSelectedRow(), 0).toString());
                int nuevoStock = (int) spinnerStock.getValue();
                boolean isActulizarOK = false;
                try {
                    isActulizarOK = Controller.actulizarStock(idProducto, nuevoStock);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                if (isActulizarOK) {
                    JOptionPane.showMessageDialog(null, "Stock actualizado correctamente.", "Stock actualizado.", JOptionPane.INFORMATION_MESSAGE);
                    DefaultTableModel modeloAlmacen = (DefaultTableModel) tablaAlmacenAdmin.getModel();
                    int filaSeleccionada = tablaAlmacenAdmin.getSelectedRow();
                    int stockActual = Integer.parseInt(modeloAlmacen.getValueAt(filaSeleccionada, 5).toString());
                    int nuevoStockTotal = stockActual + nuevoStock;
                    modeloAlmacen.setValueAt(nuevoStockTotal, filaSeleccionada, 5);
                } else {
                    JOptionPane.showMessageDialog(null, "El stock no se actualizo correctamente.", "Stock no pudo ser actualizado.", JOptionPane.ERROR_MESSAGE);
                }
                paginaPrincipalAdmin.scrollTablas.setVisible(true);
            }
        });

        botonBuscarTelefono.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    int numTelf = Integer.parseInt(txtTelefono.getText());
                    ResultSet resultadoClienteTelefono = Controller.clienteFiltrado(numTelf);
                    DefaultTableModel modeloClientes = (DefaultTableModel) tablaClientesAdmin.getModel();

                    // Limpiar el modelo de la tabla
                    modeloClientes.setRowCount(0);

                    // Obtener el número de columnas en el ResultSet
                    ResultSetMetaData metaData = resultadoClienteTelefono.getMetaData();
                    int columnCount = metaData.getColumnCount();

                    // Verificar si hay resultados en el ResultSet
                    if (!resultadoClienteTelefono.isBeforeFirst()) {
                        // No hay resultados, mostrar mensaje de error
                        JOptionPane.showMessageDialog(null, "No se encontraron clientes con ese número de teléfono.");
                    } else {
                        // Iterar sobre los resultados y agregarlos a la tabla
                        while (resultadoClienteTelefono.next()) {
                            Object[] rowData = new Object[columnCount];
                            for (int i = 1; i <= columnCount; i++) {
                                rowData[i - 1] = resultadoClienteTelefono.getObject(i);
                            }
                            modeloClientes.addRow(rowData);
                        }
                        // Actualizar la vista de la tabla
                        tablaClientesAdmin.repaint();
                    }

                    // Cerrar el ResultSet después de usarlo
                    resultadoClienteTelefono.close();

                    // Actualizar la vista de la tabla
                    tablaClientesAdmin.repaint();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingresa un número de teléfono válido.");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error al buscar el cliente.");
                    ex.printStackTrace();
                }
            }
        });
        botonBuscarID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    int idPedido = Integer.parseInt(txtIdPedido.getText());
                    ResultSet resultadoPedidoId = Controller.pedidoFlitrado(idPedido);
                    DefaultTableModel modeloPedidos = (DefaultTableModel) tablaPedidosAdmin.getModel();

                    // Limpiar el modelo de la tabla
                    modeloPedidos.setRowCount(0);

                    // Obtener el número de columnas en el ResultSet
                    ResultSetMetaData metaData = resultadoPedidoId.getMetaData();
                    int columnCount = metaData.getColumnCount();

                    // Verificar si hay resultados en el ResultSet
                    if (!resultadoPedidoId.isBeforeFirst()) {
                        // No hay resultados, mostrar mensaje de error
                        JOptionPane.showMessageDialog(null, "No se encontraron pedidos con ese ID.");
                    } else {
                        // Iterar sobre los resultados y agregarlos a la tabla
                        while (resultadoPedidoId.next()) {
                            Object[] rowData = new Object[columnCount];
                            for (int i = 1; i <= columnCount; i++) {
                                rowData[i - 1] = resultadoPedidoId.getObject(i);
                            }
                            modeloPedidos.addRow(rowData);
                        }
                        // Actualizar la vista de la tabla
                        tablaPedidosAdmin.repaint();
                    }

                    // Cerrar el ResultSet después de usarlo
                    resultadoPedidoId.close();

                    // Actualizar la vista de la tabla
                    tablaPedidosAdmin.repaint();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingresa un ID válido (número entero).");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error al buscar el pedido.");
                    ex.printStackTrace();
                }
            }
        });

    }

    public static void mostrarMensajeStock() throws SQLException {
        //llamamos al metodo para lanzar el aviso en caso de stock bajo cuando entre el administrador
        boolean stockIsOk = false;
        if (!stockIsOk) {
            String mensaje = "El stock de alguno de los productos está a punto de agotarse";
            String[] opciones = {"Cerrar"};
            int seleccion = JOptionPane.showOptionDialog(null, mensaje, "Aviso de stock bajo",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                    null, opciones, opciones[0]);
        }
        controller.avisoStock();
    }

    //Metodo para la creacion de la ventana
    public static void crearVentanaPaginaPrincipalAdmin() throws SQLException {
        paginaPrincipalAdmin.setContentPane(paginaPrincipalAdmin.PanelPrincipalAdmin);
        paginaPrincipalAdmin.setTitle("Pagina principal del administrador");
        paginaPrincipalAdmin.setBounds(230, 15, 1200, 800);
        paginaPrincipalAdmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        paginaPrincipalAdmin.setVisible(true);
        paginaPrincipalAdmin.panelAlmacen.setVisible(false);

        //Poniendo el actionListener en null logramos que nos muestre el contenido del boton pedidos al crear la ventana
        ActionListener pedidosActionListener = paginaPrincipalAdmin.botonPedidos.getActionListeners()[0];
        // Llamamos al ActionListener del botón "Pedidos"
        pedidosActionListener.actionPerformed(null);


        //añadimos imagen al boton salir
        try {
            BufferedImage img = ImageIO.read(new File("resources/cerrar_sesion.png"));
            Image scaledImg = img.getScaledInstance(30, 30, Image.SCALE_AREA_AVERAGING);
            paginaPrincipalAdmin.botonSalir.setIcon(new ImageIcon(scaledImg));
        } catch (IOException ex) {
            System.err.println("Error al cargar la imagen: " + ex.getMessage());
        }
        //miniatura de ventana
        ImageIcon logo= new ImageIcon("./././resources/logo_lurpiazon_2.png");
        paginaPrincipalAdmin.setIconImage(logo.getImage()); //thumbnail del programa
        //hacemos que el panelAlmacen solo sea visible cuando el usuario pulse el boton almacen
        paginaPrincipalAdmin.panelAlmacen.setVisible(false);
        //hacemos que el panelClientesAdmin solo no sea visible cuando la ventana se cree
        paginaPrincipalAdmin.panelClientesAdmin.setVisible(false);


    }
}