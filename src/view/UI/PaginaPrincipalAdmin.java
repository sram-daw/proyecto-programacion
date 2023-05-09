package view.UI;

import controller.Controller;
import model.dao.Catalogo;
import model.dao.Cliente;
import model.dao.ListaClientes;
import model.dao.ProductoEnStock;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
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
    /*private JPanel panelAlmacenAdmin;
    private JPanel panelPedidosAdmin;
    private JPanel panelclientesAdmin;*/

    static PaginaPrincipalAdmin paginaPrincipalAdmin = new PaginaPrincipalAdmin();

    public PaginaPrincipalAdmin() {
      /* menuPedidosAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardPedidos=(CardLayout) panelPedidosAdmin.getLayout();
                cardPedidos.show(panelPedidosAdmin,"Pedidos");
            }
        });
        menuClientesAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardClientes=(CardLayout) panelclientesAdmin.getLayout();
                cardClientes.show(panelclientesAdmin,"Clientes");
            }
        });
        menuAlmacenAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardAlmacen=(CardLayout) panelAlmacenAdmin.getLayout();
                cardAlmacen.show(panelAlmacenAdmin,"Almacen");
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
        paginaPrincipalAdmin.setContentPane(paginaPrincipalAdmin.PanelPrincipalAdmin);
        paginaPrincipalAdmin.setTitle("Pagina principal del administrador");
        paginaPrincipalAdmin.setBounds(630, 250, 1000, 700);
        paginaPrincipalAdmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        paginaPrincipalAdmin.setVisible(true);

        //Tabla que visualiza el administrador al pulsar en Pedidos del menú
        JTable tablaPedidosAdmin=new JTable();
        DefaultTableModel modeloPedidos=new DefaultTableModel();


        //Tabla que visualiza el administrador al pulsar en Clientes del menú
        String titulosEncabezadoCliente[]={"ID Usuiario","Nombre Usuario","Nombre","Apellido","Contraseña","Direccion","Telefono","Email","CP"};
        JTable tablaClientesAdmin=new JTable();
        DefaultTableModel modeloClientes= (DefaultTableModel)tablaClientesAdmin.getModel();
        modeloClientes.setColumnIdentifiers(titulosEncabezadoCliente);
        ListaClientes listaClientes=Controller.agregarTablaCliente();
        ArrayList<Cliente> grupoClientes=listaClientes.getListaClientes();

        int j=0;
        for (Cliente c :grupoClientes){
            String[] dataCliente={String.valueOf(grupoClientes.get(j).getIdUsuario()),grupoClientes.get(j).getNombreUsuario(),grupoClientes.get(j).getNombre(),grupoClientes.get(j).getApellido(),grupoClientes.get(j).getPwd(),grupoClientes.get(j).getDireccion(),grupoClientes.get(j).getNumTelf(),grupoClientes.get(j).getEmail(),grupoClientes.get(j).getCp()};
            modeloClientes.addRow(dataCliente);
            j++;
        }
        paginaPrincipalAdmin.scrollAlmacenAdmin.setViewportView(tablaClientesAdmin);


        //Tabla que visualiza el administrador al pulsar en Almacen del menú
        String titulosEncabezadoAlmacen[] = {"ID Producto", "Nombre", "Precio", "ID Categoría", "Categoría", "Stock"};
        JTable tablaAlmacenAdmin=new JTable();
        DefaultTableModel modeloAlmacen=(DefaultTableModel) tablaAlmacenAdmin.getModel();
        modeloAlmacen.setColumnIdentifiers(titulosEncabezadoAlmacen);
        Catalogo catalogo= Controller.agregarTablaAlmacen();
        ArrayList<ProductoEnStock> productoEnStocks=catalogo.getCatalogo();

        int i=0;
        for (ProductoEnStock e : productoEnStocks){
            String[] dataAlmacen= {String.valueOf(productoEnStocks.get(i).getIdProducto()), productoEnStocks.get(i).getNombre(), String.valueOf(productoEnStocks.get(i).getPrecio()), String.valueOf(productoEnStocks.get(i).getCategoriaID()), productoEnStocks.get(i).getCategoriaNombre(), String.valueOf(productoEnStocks.get(i).getStock())};
            modeloAlmacen.addRow(dataAlmacen);
            i++;
        }
        //paginaPrincipalAdmin.scrollAlmacenAdmin.setViewportView(tablaAlmacenAdmin);

    }


}
