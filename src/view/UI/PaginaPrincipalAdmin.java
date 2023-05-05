package view.UI;

import controller.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


public class PaginaPrincipalAdmin extends JFrame {
    private JPanel PanelPrincipalAdmin;
    private JPanel PanelPedidosAdmin;
    private JPanel PanelClientesAdmin;
    private JPanel PanelAlmacenAdmin;
    private JButton pedidosButton;
    private JButton clientesButton;
    private JButton almacenButton;
    private JTable table1;
    private JTable tablaAlmacenAdmin=new JTable();
    static PaginaPrincipalAdmin paginaPrincipalAdmin = new PaginaPrincipalAdmin();

    public PaginaPrincipalAdmin() {
        pedidosButton.addActionListener(new ActionListener() {
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
        });
    }

    public static void crearVentanaPaginaPrincipalAdmin() throws SQLException {
        paginaPrincipalAdmin.setContentPane(paginaPrincipalAdmin.PanelPrincipalAdmin);
        paginaPrincipalAdmin.setBounds(630, 250, 1000, 700);
        paginaPrincipalAdmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //Creamos aqui un header cuando nos dio por primera vez el fallo de "c null" por si el problema era que no estaba cogiendo el header
        //paginaPrincipalAdmin.tablaAlmacenAdmin=new JTable(Controller.agregarTablaAlmacen());

        paginaPrincipalAdmin.PanelAlmacenAdmin.add(paginaPrincipalAdmin.tablaAlmacenAdmin);
        //JTableHeader columnas = paginaPrincipalAdmin.tablaAlmacenAdmin.getTableHeader();
        //paginaPrincipalAdmin.tablaAlmacenAdmin.setTableHeader(columnas);


        //paginaPrincipalAdmin.PanelAlmacenAdmin.add(paginaPrincipalAdmin.add(paginaPrincipalAdmin.tablaAlmacenAdmin.getTableHeader()));
        JScrollPane scrollPane=new JScrollPane(paginaPrincipalAdmin.tablaAlmacenAdmin);
        paginaPrincipalAdmin.PanelAlmacenAdmin.add(scrollPane);
        paginaPrincipalAdmin.setVisible(true);
    }



}
