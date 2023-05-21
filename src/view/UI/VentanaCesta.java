package view.UI;

import controller.Controller;
import model.dao.Cesta;
import model.dao.DetallesProducto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class VentanaCesta extends JFrame {
    private JButton volverAtrásButton;
    private JPanel container;
    private JButton finalizarCompraButton;
    private JPanel panelTabla;
    private JScrollPane scrollPanelTabla;
    private JLabel precioLabel;
    private JLabel vacioLabel;
    private JPanel panelVacioLabel;
    private JButton vaciarCestaButton;

    static VentanaCesta ventanaCesta = new VentanaCesta();

    public VentanaCesta() {
        //Botón para retroceder a la página principal de la tienda
        volverAtrásButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PaginaPrincipalClientes.crearVentanaPaginaPrincipalCliente();
                    ventanaCesta.dispose();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Ha habido un error al volver a la tienda.", "Error", JOptionPane.ERROR_MESSAGE);
                    throw new RuntimeException(ex);
                }
            }
        });
        //Botón para finalizar la compra
        finalizarCompraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isFinalizarCompraOk = false;
                isFinalizarCompraOk = Controller.finalizarCompra();
                if (isFinalizarCompraOk) {
                    JOptionPane.showMessageDialog(null, "¡Gracias por su compra!", "Compra realizada", JOptionPane.INFORMATION_MESSAGE);
                    Controller.cesta = new Cesta(); //se vacía la cesta al finalizar la compra
                    crearVentanaCesta();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al finalizar la compra. Vuelva a intentarlo.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        vaciarCestaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.cesta = new Cesta(); //se vacía la cesta actual
                JOptionPane.showMessageDialog(null, "Se ha vaciado su cesta", "Cesta vaciada", JOptionPane.INFORMATION_MESSAGE);
                ventanaCesta.dispose();
                ventanaCesta.crearVentanaCesta();
            }
        });
    }

    public static void crearVentanaCesta() {
        //creacion de la ventana
        ventanaCesta.setContentPane(ventanaCesta.container);
        ventanaCesta.setTitle("Cesta");
        ventanaCesta.setBounds(630, 250, 650, 600);
        ventanaCesta.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaCesta.setVisible(true);

        //creacion de la tabla de productos que se encuentran en la cesta
        String titulosEncabezado[] = {"ID", "Nombre", "Precio", "Cantidad"};
        JTable table = new JTable();
        //se necesita un DefaultTableModel para usar la función addRow, pero se toma el modelo que tiene la propia JTable para no modificar nada (si se deja modelo= new DefaultTableModel no se muestra la tabla)
        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
        modelo.setColumnIdentifiers(titulosEncabezado);
        if (!Controller.cesta.getCesta().isEmpty()) { //si no hay cesta creada no entra al bucle para evitar nullpointerexception
            ventanaCesta.vacioLabel.setText(null);
            ventanaCesta.vaciarCestaButton.setEnabled(true);
            ArrayList<DetallesProducto> detallesProductos = Controller.cesta.getCesta();
            int i = 0;
            //se obtienen los atributos de cada objeto DetallesProducto del arraylist para añadirlos como filas a la tabla
            for (DetallesProducto e : detallesProductos) {
                String[] data = {String.valueOf(detallesProductos.get(i).getIdProducto()), detallesProductos.get(i).getNombre(), String.valueOf(detallesProductos.get(i).getPrecio()), String.valueOf(detallesProductos.get(i).getCantidad())};
                modelo.addRow(data);
                i++;
            }
            ventanaCesta.scrollPanelTabla.setViewportView(table);
            ventanaCesta.precioLabel.setText(String.format("%.2f€", Controller.cesta.getPrecio())); //solo mostrará 2 decimales
        } else if (Controller.cesta.getCesta().isEmpty()) {
            modelo.setRowCount(0); //se resetea la tabla para que, en caso de haber ejecutado un borrado de productos, elimine los productos que había
            ventanaCesta.vaciarCestaButton.setEnabled(false);
            ventanaCesta.vacioLabel.setText("Tu cesta está vacía");
            ventanaCesta.scrollPanelTabla.setViewportView(table);
            ventanaCesta.precioLabel.setText(String.format("%.2f€", 0.0));
        }

    }

}
