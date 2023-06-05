package view.UI;

import controller.Controller;
import controller.GeneradorPdf;
import model.dao.HistorialPedidosTotal;
import model.dao.Pedido;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Clase Pagina pedidos cliente.
 */
public class PaginaPedidosCliente extends JFrame {
    private JPanel panelLabel;
    private JPanel panelTabla;
    private JScrollPane scrollPaneTabla;
    private JPanel container;
    private JButton volverAtrásButton;
    private JButton generarFacturaButton;
    private JPanel panelBotonAtras;
    private JPanel panelBotonFactura;
    /**
     * Tabla table
     */
    static JTable table;
    /**
     * Pagina pedidos cliente.
     */
    static PaginaPedidosCliente paginaPedidosCliente = new PaginaPedidosCliente();

    /**
     * Constructor Pagina pedidos cliente.
     */
    public PaginaPedidosCliente() {
        volverAtrásButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    VentanaCuentaCliente.crearVentanaCuentaCliente();
                    paginaPedidosCliente.dispose();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        generarFacturaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(null, "Selecciona un pedido para generar la factura.", "Ningún pedido seleccionado.", JOptionPane.ERROR_MESSAGE);
                } else {
                    int idPedido = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
                    try {
                        GeneradorPdf.generarFactura(idPedido);
                    } catch (IOException | SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }

    /**
     * Crear ventana pagina pedidos cliente.
     *
     * @throws SQLException la excepcion sql
     */
//Método para crear la ventana de pedidos del cliente
    public static void crearVentanaPaginaPedidosCliente() throws SQLException {
        //añadimos el contenindo a la ventana
        paginaPedidosCliente.setContentPane(paginaPedidosCliente.container);
        paginaPedidosCliente.setBounds(330, 60, 1000, 700);
        paginaPedidosCliente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        paginaPedidosCliente.setTitle("Pedidos");
        paginaPedidosCliente.setVisible(true);

        //creación de la tabla de pedidos
        String titulosEncabezado[] = {"ID", "Fecha", "Precio"};
        table = new JTable();
        //se necesita un DefaultTableModel para usar la función addRow, pero se toma el modelo que tiene la propia JTable para no modificar nada (si se deja modelo= new DefaultTableModel no se muestra la tabla)
        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
        modelo.setColumnIdentifiers(titulosEncabezado);
        HistorialPedidosTotal pedidos = Controller.agregarTablaPedidos();
        ArrayList<Pedido> listaPedidos = pedidos.getTotalPedidos();
        //se van añadiendo las filas de productos a la tabla
        int i = 0;
        for (Pedido p : listaPedidos) {
            if (p.getCliente().getIdUsuario() == Controller.clienteLogado.getIdUsuario()) { //solo toma los pedidos del usuario logado
                String[] data = {String.valueOf(listaPedidos.get(i).getIdPedido()), String.valueOf(listaPedidos.get(i).getFecha()), String.valueOf(listaPedidos.get(i).getPrecio())};
                modelo.addRow(data);
            }
            i++;
        }
        //miniatura de ventana
        ImageIcon logo= new ImageIcon("./././resources/logo_lurpiazon_2.png");
        paginaPedidosCliente.setIconImage(logo.getImage()); //thumbnail del programa
        paginaPedidosCliente.scrollPaneTabla.setViewportView(table);

    }


}
