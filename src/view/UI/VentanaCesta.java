package view.UI;

import model.dao.Cesta;
import model.dao.DetallesProducto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class VentanaCesta extends JFrame {
    private JButton volverAtrásButton;
    private JPanel container;
    private JButton finalizarCompraButton;
    private JPanel panelTabla;
    private JScrollPane scrollPanelTabla;
    private JLabel precioLabel;

    static VentanaCesta ventanaCesta = new VentanaCesta();

    public static void crearVentanaCesta(Cesta cesta) {
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
        ArrayList<DetallesProducto> detallesProductos = cesta.getCesta();
        int i = 0;
        //se obtienen los atributos de cada objeto DetallesProducto del arraylist para añadirlos como filas a la tabla
        for (DetallesProducto e : detallesProductos) {
            String[] data = {String.valueOf(detallesProductos.get(i).getIdProducto()), detallesProductos.get(i).getNombre(), String.valueOf(detallesProductos.get(i).getPrecio()), String.valueOf(detallesProductos.get(i).getCantidad())};
            modelo.addRow(data);
            i++;
        }
        ventanaCesta.scrollPanelTabla.setViewportView(table);
        ventanaCesta.precioLabel.setText(String.valueOf(cesta.getPrecio())+"€");


    }

}
