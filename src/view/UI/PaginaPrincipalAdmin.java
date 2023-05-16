package view.UI;

import controller.Controller;
import model.dao.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaginaPrincipalAdmin extends JFrame {
    private JPanel PanelPrincipalAdmin;
    private JMenu menuSalirAdmin;
    private JButton botonSalir;
    private JButton botonPedidos;
    private JButton botonClientes;
    private JButton botonAlmacen;
    private JPanel PanelMenuAdmin;
    private JPanel panelTabla;
    CardLayout cardLayout = new CardLayout();
    static JMenuItem itemStockAScendente = new JMenuItem("Stock ascendente");
    static JSpinner spinnerStock;
    static JTable tablaAlmacenAdmin;
    static DefaultTableModel modeloAlmacen;
    static ScrollPane scrollAlmacen;
    static PaginaPrincipalAdmin paginaPrincipalAdmin = new PaginaPrincipalAdmin();

    public PaginaPrincipalAdmin() {
        botonPedidos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelTabla, "Pedidos");
            }
        });
        botonClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelTabla, "Clientes");
            }
        });
        botonAlmacen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelTabla, "Almacen");
            }
        });
        botonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
                System.out.println("El boton salir funciona para el admin");
            }
        });

        itemStockAScendente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "La version del programa es la 1.0");
                System.out.println("El item version funciona");
            }
        });


    }

    //Este metodo tiene que dividirse en 3 para que nos muestre las distintas tablas en funcion del boton que seleccionemos
    public static void crearVentanaPaginaPrincipalAdmin() throws SQLException {
        paginaPrincipalAdmin.setContentPane(paginaPrincipalAdmin.PanelPrincipalAdmin);
        paginaPrincipalAdmin.setTitle("Pagina principal del administrador");
        paginaPrincipalAdmin.setBounds(630, 250, 1200, 900);
        paginaPrincipalAdmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        paginaPrincipalAdmin.setVisible(true);

        //modificamos el tipo de layout para el panelTabla y le damos a cada uno su nombre identificador
        paginaPrincipalAdmin.panelTabla.setLayout(paginaPrincipalAdmin.cardLayout);
        JPanel panelPedidos = new JPanel();
        JPanel panelClientes = new JPanel();
        JPanel panelAlmacen = new JPanel();
        String nombrePanelPedidos = "Pedidos";
        String nombrePanelClientes = "Clientes";
        String nombrePanelAlmacen = "Almacen";

        paginaPrincipalAdmin.panelTabla.add(panelPedidos, "Pedidos");
        paginaPrincipalAdmin.panelTabla.add(panelClientes, "Clientes");
        paginaPrincipalAdmin.panelTabla.add(panelAlmacen, "Almacen");

        //Tabla que visualiza el administrador al pulsar en Pedidos del menú
        ScrollPane scrollPedidosAdmin = new ScrollPane();
        scrollPedidosAdmin.setPreferredSize(new Dimension(800, 600));
        String titulosEncabezadoPedidos[] = {"ID Pedido", "ID Usuario", "Fecha", "Precio Total"};
        JTable tablaPedidosAdmin = new JTable();
        DefaultTableModel modeloPedidos = (DefaultTableModel) tablaPedidosAdmin.getModel();
        modeloPedidos.setColumnIdentifiers(titulosEncabezadoPedidos);
        HistorialPedidosTotal listaPedidos = Controller.agregartTablaPedidos();
        ArrayList<Pedidos> conjuntoPedidos = listaPedidos.getTotalPedidos();

        int y = 0;
        for (Pedidos p : conjuntoPedidos) {
            String[] dataPeidos = {String.valueOf(conjuntoPedidos.get(y).getIdPedido()), String.valueOf(conjuntoPedidos.get(y).getIdUsuario()), String.valueOf(conjuntoPedidos.get(y).getFecha()), String.valueOf(conjuntoPedidos.get(y).getPrecio())};
            modeloPedidos.addRow(dataPeidos);
            y++;
        }

        scrollPedidosAdmin.add(tablaPedidosAdmin.getTableHeader());
        scrollPedidosAdmin.add(tablaPedidosAdmin);
        panelPedidos.add(scrollPedidosAdmin);

        //Tabla que visualiza el administrador al pulsar en Clientes del menú
        ScrollPane scrollClientesAdmin = new ScrollPane();
        scrollClientesAdmin.setPreferredSize(new Dimension(800, 600));
        String titulosEncabezadoCliente[] = {"ID Usuario", "Nombre Usuario", "Nombre", "Apellido", "Contraseña", "Direccion", "Telefono", "Email", "CP"};
        JTable tablaClientesAdmin = new JTable();
        DefaultTableModel modeloClientes = (DefaultTableModel) tablaClientesAdmin.getModel();
        modeloClientes.setColumnIdentifiers(titulosEncabezadoCliente);
        ListaClientes listaClientes = Controller.agregarTablaCliente();
        ArrayList<Cliente> grupoClientes = listaClientes.getListaClientes();

        int j = 0;
        for (Cliente c : grupoClientes) {
            String[] dataCliente = {String.valueOf(grupoClientes.get(j).getIdUsuario()), grupoClientes.get(j).getNombreUsuario(), grupoClientes.get(j).getNombre(), grupoClientes.get(j).getApellido(), grupoClientes.get(j).getPwd(), grupoClientes.get(j).getDireccion(), grupoClientes.get(j).getNumTelf(), grupoClientes.get(j).getEmail(), grupoClientes.get(j).getCp()};
            modeloClientes.addRow(dataCliente);
            j++;
        }

        scrollClientesAdmin.add(tablaClientesAdmin.getTableHeader());
        scrollClientesAdmin.add(tablaClientesAdmin);
        panelClientes.add(scrollClientesAdmin);
        //paginaPrincipalAdmin.scrollAlmacenAdmin.setViewportView(tablaClientesAdmin);


        //Tabla que visualiza el administrador al pulsar en Almacen del menú
        ScrollPane scrollAlmacenAdmin = new ScrollPane();
        scrollAlmacenAdmin.setPreferredSize(new Dimension(800, 600));
        String titulosEncabezadoAlmacen[] = {"ID Producto", "Nombre", "Precio", "ID Categoría", "Categoría", "Stock"};
        tablaAlmacenAdmin = new JTable();
        modeloAlmacen = (DefaultTableModel) tablaAlmacenAdmin.getModel();
        modeloAlmacen.setColumnIdentifiers(titulosEncabezadoAlmacen);
        Catalogo catalogo = Controller.agregarTablaAlmacen();
        ArrayList<ProductoEnStock> productoEnStocks = catalogo.getCatalogo();

        int i = 0;
        for (ProductoEnStock e : productoEnStocks) {
            String[] dataAlmacen = {String.valueOf(productoEnStocks.get(i).getIdProducto()), productoEnStocks.get(i).getNombre(), String.valueOf(productoEnStocks.get(i).getPrecio()), String.valueOf(productoEnStocks.get(i).getCategoriaID()), productoEnStocks.get(i).getCategoriaNombre(), String.valueOf(productoEnStocks.get(i).getStock())};
            modeloAlmacen.addRow(dataAlmacen);
            i++;
        }

        //menu desplegable para poder ordenar la tabla
        JPanel panelMenu = new JPanel();
        JMenuBar menuBar = new JMenuBar();
        JMenu menuOrdenar = new JMenu("Ordenar por...");
        panelAlmacen.add(panelMenu);
        panelMenu.add(menuBar);
        menuBar.add(menuOrdenar);
        menuOrdenar.add(itemStockAScendente);

        //Spinner para poder añadir stock a la tablaAlmacen, en desarrollo
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(10, 10, 50, 10);
        spinnerStock=new JSpinner(spinnerModel);
        JPanel panelStock=new JPanel();
        panelStock.add(new JLabel("Stock: "));
        panelStock.add(spinnerStock);
        JButton aumentarStock=new JButton("Añadir stock");
        panelStock.add(aumentarStock);


        scrollAlmacenAdmin.add(tablaAlmacenAdmin.getTableHeader());
        scrollAlmacenAdmin.add(tablaAlmacenAdmin);
        panelAlmacen.add(scrollAlmacenAdmin);
        panelAlmacen.add(panelStock);



        //paginaPrincipalAdmin.scrollAlmacen.setViewportView(tablaAlmacenAdmin);


    }


}
