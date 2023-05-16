package view.UI;

import controller.Controller;
import model.dao.Catalogo;
import model.dao.Cliente;
import model.dao.ListaClientes;
import model.dao.ProductoEnStock;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;


public class PaginaPrincipalAdmin extends JFrame {
    private JPanel PanelPrincipalAdmin;
    private JMenuBar menuBarAdmin;
    private JMenu menuPedidosAdmin;
    private JMenu menuClientesAdmin;
    private JMenu menuAlmacenAdmin;
    private JMenu menuSalirAdmin;
    private JPanel panelTabla;
    private JScrollPane scrollAlmacenAdmin;
    private JPanel panelBotones;


    static PaginaPrincipalAdmin paginaPrincipalAdmin = new PaginaPrincipalAdmin();

    public PaginaPrincipalAdmin() {
      /*  pedidosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Este boton nos mostrara la tabla de pedidos");
            }
        });
        clientesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Este boton nos mostrara la tabla clientes");
            }
        });
        almacenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Este boton nos mostrara la tabla stock");
            }
        });*/

        //Este boton todavia no funciona
        menuSalirAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paginaPrincipalAdmin.dispose();
                System.out.println("El boton  salir funciona");
            }
        });

    }

    //Este metodo tiene que dividirse en 3 para que nos muestre las distintas tablas en funcion del boton que seleccionemos
    public static void crearVentanaPaginaPrincipalAdmin() throws SQLException {
        //creación de la ventana
        paginaPrincipalAdmin.setContentPane(paginaPrincipalAdmin.PanelPrincipalAdmin);
        paginaPrincipalAdmin.setTitle("Página principal de administrador");
        paginaPrincipalAdmin.setBounds(630, 250, 1000, 700);
        paginaPrincipalAdmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        paginaPrincipalAdmin.setVisible(true);


        //Tabla que visualiza el administrador al pulsar en Pedidos del menú. En proceso.
        /*JTable tablaPedidosAdmin=new JTable();
        DefaultTableModel modeloPedidos=new DefaultTableModel();*/


        //Tabla que visualiza el administrador al pulsar en Clientes del menú
        String titulosEncabezadoCliente[] = {"Nombre Usuario", "Nombre", "Apellido", "Contraseña", "Direccion", "Telefono", "Email", "CP"};
        JTable tablaClientesAdmin = new JTable();
        DefaultTableModel modeloClientes = (DefaultTableModel) tablaClientesAdmin.getModel();
        modeloClientes.setColumnIdentifiers(titulosEncabezadoCliente);
        ListaClientes listaClientes = Controller.agregarTablaCliente();
        ArrayList<Cliente> grupoClientes = listaClientes.getListaClientes();

        int j = 0;
        for (Cliente c : grupoClientes) {
            String[] dataCliente = {grupoClientes.get(j).getNombreUsuario(), grupoClientes.get(j).getNombre(), grupoClientes.get(j).getApellido(), grupoClientes.get(j).getPwd(), grupoClientes.get(j).getDireccion(), grupoClientes.get(j).getNumTelf(), grupoClientes.get(j).getEmail(), grupoClientes.get(j).getCp()};
            modeloClientes.addRow(dataCliente);
            j++;
        }
        //paginaPrincipalAdmin.scrollAlmacenAdmin.setViewportView(tablaClientesAdmin);


        //creación de la tabla de productos
        String titulosEncabezado[] = {"ID Producto", "Nombre", "Precio", "ID Categoría", "Categoría", "Stock"};
        JTable table = new JTable();
        //se necesita un DefaultTableModel para usar la función addRow, pero se toma el modelo que tiene la propia JTable para no modificar nada (si se deja modelo= new DefaultTableModel no se muestra la tabla)
        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
        modelo.setColumnIdentifiers(titulosEncabezado);
        Catalogo catalogo = Controller.agregarTablaAlmacen();
        ArrayList<ProductoEnStock> productoEnStocks = catalogo.getCatalogo();
        //se van añadiendo las filas de productos a la tabla
        int i = 0;
        for (ProductoEnStock e : productoEnStocks) {
            String[] data = {String.valueOf(productoEnStocks.get(i).getIdProducto()), productoEnStocks.get(i).getNombre(), String.valueOf(productoEnStocks.get(i).getPrecio()), String.valueOf(productoEnStocks.get(i).getCategoriaID()), productoEnStocks.get(i).getCategoriaNombre(), String.valueOf(productoEnStocks.get(i).getStock())};
            modelo.addRow(data);
            i++;
        }
        paginaPrincipalAdmin.scrollAlmacenAdmin.setViewportView(table);


    }


}
