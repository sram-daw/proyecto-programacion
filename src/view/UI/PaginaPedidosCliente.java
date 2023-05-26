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

public class PaginaPedidosCliente extends JFrame {
    private JPanel panelLabel;
    private JPanel panelTabla;
    private JScrollPane scrollPaneTabla;
    private JPanel container;
    private JButton volverAtrásButton;
    private JButton generarFacturaButton;
    private JPanel panelBotonAtras;
    private JPanel panelBotonFactura;
    static JTable table;
    static PaginaPedidosCliente paginaPedidosCliente = new PaginaPedidosCliente();

    public PaginaPedidosCliente() {
        volverAtrásButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paginaPedidosCliente.dispose();
                try {
                    PaginaPrincipalClientes.crearVentanaPaginaPrincipalCliente();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        generarFacturaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idPedido = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
                try {
                    GeneradorPdf.generarFactura(idPedido);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    //Método para crear la ventana de pedidos del cliente
    public static void crearVentanaPaginaPedidosCliente() {
        //añadimos el contenindo a la ventana
        paginaPedidosCliente.setContentPane(paginaPedidosCliente.container);
        paginaPedidosCliente.setBounds(630, 250, 1000, 700);
        paginaPedidosCliente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        paginaPedidosCliente.scrollPaneTabla.setViewportView(table);

    }


}
