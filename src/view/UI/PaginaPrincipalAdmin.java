package view.UI;

import controller.Controller;
import model.dao.Catalogo;
import model.dao.ProductoEnStock;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;


public class PaginaPrincipalAdmin extends JFrame {
    private JPanel PanelPrincipalAdmin;
    private JPanel PanelTitle;
    private JPanel containerTabla;
    private JScrollPane scrollPanelTabla;

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
    }

    public static void crearVentanaPaginaPrincipalAdmin() throws SQLException {
        //creación de la ventana
        paginaPrincipalAdmin.setContentPane(paginaPrincipalAdmin.PanelPrincipalAdmin);
        paginaPrincipalAdmin.setTitle("Página principal de administrador");
        paginaPrincipalAdmin.setBounds(630, 250, 1000, 700);
        paginaPrincipalAdmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        paginaPrincipalAdmin.setVisible(true);

        //creación de la tabla
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
        paginaPrincipalAdmin.scrollPanelTabla.setViewportView(table);


    }


}
