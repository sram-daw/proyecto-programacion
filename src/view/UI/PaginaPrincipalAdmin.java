package view.UI;

import controller.Controller;
import model.dao.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
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
import java.util.Comparator;
import java.util.List;

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
                listaPedidos = Controller.agregarTablaPedidos();
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

                //hacemos que el boton cambie de color cuando este seleccionado
                if (botonPresionadoActual != null) {
                    botonPresionadoActual.setBackground(UIManager.getColor("Button.background"));
                }
                botonPresionadoActual = botonPedidos;
                botonPresionadoActual.setBackground(new Color(185, 206, 172));

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

                //hacemos que el boton cambie de color cuando este seleccionado
                if (botonPresionadoActual != null) {
                    botonPresionadoActual.setBackground(UIManager.getColor("Button.background"));
                }
                botonPresionadoActual = botonClientes;
                botonPresionadoActual.setBackground(new Color(185, 206, 172));

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
                spinnerModel.setStepSize(10); // Establecer el incremento/decremento
                spinnerModel.setValue(10);
                paginaPrincipalAdmin.panelAlmacen.setVisible(true);

                paginaPrincipalAdmin.scrollTablas.setVisible(true);

                //hacemos que el boton cambie de color cuando este seleccionado
                if (botonPresionadoActual != null) {
                    botonPresionadoActual.setBackground(UIManager.getColor("Button.background"));
                }
                botonPresionadoActual = botonAlmacen;
                botonPresionadoActual.setBackground(new Color(185, 206, 172));
            }
        });
        botonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);
                System.out.println("El boton salir funciona para el admin");
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

    }

    public static void mostrarMensajeStock() throws SQLException{
        //llamamos al metodo para lanzar el aviso en caso de stock bajo cuando entre el administrador
        controller.avisoStock();
    }

    //Metodo para la creacion de la ventana
    public static void crearVentanaPaginaPrincipalAdmin() throws SQLException {
        paginaPrincipalAdmin.setContentPane(paginaPrincipalAdmin.PanelPrincipalAdmin);
        paginaPrincipalAdmin.setTitle("Pagina principal del administrador");
        paginaPrincipalAdmin.setBounds(630, 250, 1200, 900);
        paginaPrincipalAdmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        paginaPrincipalAdmin.setVisible(true);
        paginaPrincipalAdmin.panelAlmacen.setVisible(false);


        SwingUtilities.invokeLater(() -> {
            paginaPrincipalAdmin.botonPedidos.setSelected(true);
        });

        //añadimos imagen al boton salir
        try {
            BufferedImage img = ImageIO.read(new File("resources/cerrar_sesion.png"));
            Image scaledImg = img.getScaledInstance(30, 30, Image.SCALE_AREA_AVERAGING);
            paginaPrincipalAdmin.botonSalir.setIcon(new ImageIcon(scaledImg));
        } catch (IOException ex) {
            System.err.println("Error al cargar la imagen: " + ex.getMessage());
        }

        //hacemos que el panelAlmacen solo sea visible cuando el usuario pulse el boton almacen
        paginaPrincipalAdmin.panelAlmacen.setVisible(false);



    }

}