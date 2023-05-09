package view.UI;

import controller.Controller;
import model.dao.Catalogo;
import model.dao.Cesta;
import model.dao.ProductoEnStock;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaginaPrincipalClientes extends JFrame {//extendemos de JFrame para trabajar con la ventana
    private JPanel PanelBotonesSuperiores;
    private JButton logoButton;
    private JButton ropaButton;
    private JButton hogarButton;
    private JButton alimentacionButton;
    private JButton mascotasButton;
    private JPanel Usuario;
    private JPanel CategoriasPanel;
    private JMenu ClienteMenu;
    private JMenuItem ItemCuenta;
    private JMenuItem ItemPedidos;
    private JMenuBar Barramenu;
    private JScrollPane scrollPanelTabla;
    private JPanel container;
    private JButton cestaButton;
    private JSpinner spinner1;
    private JButton addToCestaButton;
    private JPanel panelTabla;
    private JPanel panelInferiorSpiner;

    static PaginaPrincipalClientes paginaPrincipalClientes = new PaginaPrincipalClientes();

    //declaración global de la tabla para poder usarla en distintos métodos
    static JTable table;
    //declaración global de la cesta para poder usarla en distintos métodos
    static Cesta cesta;

    public PaginaPrincipalClientes() {


        //Mostramos mensaje por consola de la funcionalidad que tendra el boton
        logoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Este boton llevara al usuario a la pagina de inicio");
            }
        });

        ropaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        addToCestaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //se recogen los datos de la fila seleccionada
                int id = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
                String nombre = table.getValueAt(table.getSelectedRow(), 1).toString();
                float precio = Float.parseFloat(table.getValueAt(table.getSelectedRow(), 2).toString());
                String categoria = table.getValueAt(table.getSelectedRow(), 3).toString();
                int cantidad = (int) spinner1.getValue();
                //la variable global cesta se iguala al return del método addProductoCesta para añadir el producto y cantidad que ha seleccionado el usuario
                cesta = Controller.addProductoToCesta(id, nombre, precio, categoria, cantidad);
                JOptionPane.showMessageDialog(null, "Artículo añadido a la cesta.");
            }
        });
        cestaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaCesta.crearVentanaCesta(cesta);
                paginaPrincipalClientes.dispose();
            }
        });
    }

    public static void crearVentanaPaginaPrincipalCliente() throws SQLException {
        //añadimos el contenindo a la ventana
        paginaPrincipalClientes.setContentPane(paginaPrincipalClientes.container);
        //Añadimos los items al menu
        paginaPrincipalClientes.ClienteMenu.add(paginaPrincipalClientes.ItemCuenta);
        paginaPrincipalClientes.ClienteMenu.add(paginaPrincipalClientes.ItemPedidos);
        paginaPrincipalClientes.setBounds(630, 250, 1000, 700);
        paginaPrincipalClientes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        paginaPrincipalClientes.setVisible(true);

        //tabla de productos completa, previo filtrado
        String titulosEncabezado[] = {"ID", "Nombre", "Precio", "Categoría"}; //a los clientes solo se les muestra estos campos
        table = new JTable();
        //se necesita un DefaultTableModel para usar la función addRow, pero se toma el modelo que tiene la propia JTable para no modificar nada (si se deja modelo= new DefaultTableModel no se muestra la tabla)
        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
        modelo.setColumnIdentifiers(titulosEncabezado);
        Catalogo catalogo = Controller.agregarTablaAlmacen();
        ArrayList<ProductoEnStock> productoEnStocks = catalogo.getCatalogo();
        //se van añadiendo las filas de productos a la tabla
        int i = 0;
        for (ProductoEnStock e : productoEnStocks) {
            String[] data = {String.valueOf(productoEnStocks.get(i).getIdProducto()), productoEnStocks.get(i).getNombre(), String.valueOf(productoEnStocks.get(i).getPrecio()), productoEnStocks.get(i).getCategoriaNombre()};
            modelo.addRow(data);
            i++;
        }
        paginaPrincipalClientes.scrollPanelTabla.setViewportView(table);
    }


}
