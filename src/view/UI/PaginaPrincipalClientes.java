package view.UI;

import controller.Controller;
import model.dao.Catalogo;
import model.dao.Cesta;
import model.dao.ProductoEnStock;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaginaPrincipalClientes extends JFrame {//extendemos de JFrame para trabajar con la ventana
    private JPanel PanelBotonesSuperiores;
    private JButton ropaButton;
    private JButton hogarButton;
    private JButton alimentacionButton;
    private JButton mascotasButton;
    private JPanel Usuario;
    private JPanel CategoriasPanel;


    /*  private JMenu ClienteMenu;
      private JMenuItem ItemCuenta;
      private JMenuItem ItemPedidos;
      private JMenuBar Barramenu;*/
    private JButton botonMiCuenta;
    private JScrollPane scrollPanelTabla;
    private JPanel container;
    private JButton cestaButton;
    private JSpinner spinner1;
    private JButton addToCestaButton;
    private JPanel panelTabla;
    private JPanel panelInferiorSpiner;
    private JButton todoButton;
    private JPanel panelTodo;
    private JLabel labelTodo;
    private JPanel panelRopa;
    private JLabel labelRopa;
    private JPanel panelHogar;
    private JLabel labelHogar;
    private JPanel panelAlimentacion;
    private JLabel labelAlimentacion;
    private JPanel panelMascotas;
    private JLabel labelMascotas;
    private JButton salirButton;
    private JButton botonPresionadoActual;
    static PaginaPrincipalClientes paginaPrincipalClientes = new PaginaPrincipalClientes();

    //declaración global de la tabla para poder usarla en distintos métodos
    static JTable table;


    public PaginaPrincipalClientes() {

        addToCestaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(null, "Selecciona un producto para añadirlo a la cesta.", "Ningún producto seleccionado.", JOptionPane.ERROR_MESSAGE);
                } else {
                    //se recogen los datos de la fila seleccionada
                    int id = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
                    String nombre = table.getValueAt(table.getSelectedRow(), 1).toString();
                    float precio = Float.parseFloat(table.getValueAt(table.getSelectedRow(), 2).toString());
                    String categoria = table.getValueAt(table.getSelectedRow(), 3).toString();
                    int cantidad = (int) spinner1.getValue();
                    Cesta cestaAux = Controller.addProductoToCesta(id, nombre, precio, categoria, cantidad);
                    if (cestaAux != null) { //ese método devuelve null si no hay suficiente stock
                        JOptionPane.showMessageDialog(null, "Artículo añadido correctamente a la cesta.", "Artículo añadido.", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        int stock = Controller.devolverStock(id);
                        JOptionPane.showMessageDialog(null, "Lo sentimos, no hay suficiente stock. Actualmente disponemos de " + stock + " unidades del producto seleccionado.", "Problema de stock", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });
        cestaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                VentanaCesta.crearVentanaCesta();
                paginaPrincipalClientes.dispose();
            }
        });
        ropaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                String titulosEncabezado[] = {"ID", "Nombre", "Precio", "Categoría"}; //a los clientes solo se les muestra estos campos
                table = new JTable();
                //se necesita un DefaultTableModel para usar la función addRow, pero se toma el modelo que tiene la propia JTable para no modificar nada (si se deja modelo= new DefaultTableModel no se muestra la tabla)
                DefaultTableModel modelo = (DefaultTableModel) table.getModel();
                modelo.setColumnIdentifiers(titulosEncabezado);
                Catalogo catalogo = Controller.filtrarCat(1);
                ArrayList<ProductoEnStock> productoEnStocks = catalogo.getCatalogo();
                //se van añadiendo las filas de productos a la tabla
                int i = 0;
                for (ProductoEnStock e : productoEnStocks) {
                    String[] data = {String.valueOf(productoEnStocks.get(i).getIdProducto()), productoEnStocks.get(i).getNombre(), String.valueOf(productoEnStocks.get(i).getPrecio()), productoEnStocks.get(i).getCategoriaNombre()};
                    modelo.addRow(data);
                    i++;
                }
                paginaPrincipalClientes.scrollPanelTabla.setViewportView(table);

                //hacemos que el boton cambie de color cuando este seleccionado
                if (botonPresionadoActual != null) {
                    botonPresionadoActual.setBackground(UIManager.getColor("Button.background"));
                }
                botonPresionadoActual = ropaButton;
                botonPresionadoActual.setBackground(new Color(168, 239, 208));

                //Hacemos que solo sea visible el panelRopa en este boton
                panelTodo.setVisible(false);
                panelRopa.setVisible(true);
                panelHogar.setVisible(false);
                panelAlimentacion.setVisible(false);
                panelMascotas.setVisible(false);
            }
        });

        hogarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                String titulosEncabezado[] = {"ID", "Nombre", "Precio", "Categoría"}; //a los clientes solo se les muestra estos campos
                table = new JTable();
                //se necesita un DefaultTableModel para usar la función addRow, pero se toma el modelo que tiene la propia JTable para no modificar nada (si se deja modelo= new DefaultTableModel no se muestra la tabla)
                DefaultTableModel modelo = (DefaultTableModel) table.getModel();
                modelo.setColumnIdentifiers(titulosEncabezado);
                Catalogo catalogo = Controller.filtrarCat(2);
                ArrayList<ProductoEnStock> productoEnStocks = catalogo.getCatalogo();
                //se van añadiendo las filas de productos a la tabla
                int i = 0;
                for (ProductoEnStock e : productoEnStocks) {
                    String[] data = {String.valueOf(productoEnStocks.get(i).getIdProducto()), productoEnStocks.get(i).getNombre(), String.valueOf(productoEnStocks.get(i).getPrecio()), productoEnStocks.get(i).getCategoriaNombre()};
                    modelo.addRow(data);
                    i++;
                }
                paginaPrincipalClientes.scrollPanelTabla.setViewportView(table);

                //hacemos que el boton cambie de color cuando este seleccionado
                if (botonPresionadoActual != null) {
                    botonPresionadoActual.setBackground(UIManager.getColor("Button.background"));
                }
                botonPresionadoActual = hogarButton;
                botonPresionadoActual.setBackground(new Color(168, 239, 208));

                //Hacemos que solo sea visible el panelHogar en este boton
                panelTodo.setVisible(false);
                panelRopa.setVisible(false);
                panelHogar.setVisible(true);
                panelAlimentacion.setVisible(false);
                panelMascotas.setVisible(false);
            }
        });

        alimentacionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                String titulosEncabezado[] = {"ID", "Nombre", "Precio", "Categoría"}; //a los clientes solo se les muestra estos campos
                table = new JTable();
                //se necesita un DefaultTableModel para usar la función addRow, pero se toma el modelo que tiene la propia JTable para no modificar nada (si se deja modelo= new DefaultTableModel no se muestra la tabla)
                DefaultTableModel modelo = (DefaultTableModel) table.getModel();
                modelo.setColumnIdentifiers(titulosEncabezado);
                Catalogo catalogo = Controller.filtrarCat(3);
                ArrayList<ProductoEnStock> productoEnStocks = catalogo.getCatalogo();
                //se van añadiendo las filas de productos a la tabla
                int i = 0;
                for (ProductoEnStock e : productoEnStocks) {
                    String[] data = {String.valueOf(productoEnStocks.get(i).getIdProducto()), productoEnStocks.get(i).getNombre(), String.valueOf(productoEnStocks.get(i).getPrecio()), productoEnStocks.get(i).getCategoriaNombre()};
                    modelo.addRow(data);
                    i++;
                }
                paginaPrincipalClientes.scrollPanelTabla.setViewportView(table);

                //hacemos que el boton cambie de color cuando este seleccionado
                if (botonPresionadoActual != null) {
                    botonPresionadoActual.setBackground(UIManager.getColor("Button.background"));
                }
                botonPresionadoActual = alimentacionButton;
                botonPresionadoActual.setBackground(new Color(168, 239, 208));

                //Hacemos que solo sea visible el panelAlimentacion en este boton
                panelTodo.setVisible(false);
                panelRopa.setVisible(false);
                panelHogar.setVisible(false);
                panelAlimentacion.setVisible(true);
                panelMascotas.setVisible(false);
            }
        });

        mascotasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                String titulosEncabezado[] = {"ID", "Nombre", "Precio", "Categoría"}; //a los clientes solo se les muestra estos campos
                table = new JTable();
                //se necesita un DefaultTableModel para usar la función addRow, pero se toma el modelo que tiene la propia JTable para no modificar nada (si se deja modelo= new DefaultTableModel no se muestra la tabla)
                DefaultTableModel modelo = (DefaultTableModel) table.getModel();
                modelo.setColumnIdentifiers(titulosEncabezado);
                Catalogo catalogo = Controller.filtrarCat(4);
                ArrayList<ProductoEnStock> productoEnStocks = catalogo.getCatalogo();
                //se van añadiendo las filas de productos a la tabla
                int i = 0;
                for (ProductoEnStock e : productoEnStocks) {
                    String[] data = {String.valueOf(productoEnStocks.get(i).getIdProducto()), productoEnStocks.get(i).getNombre(), String.valueOf(productoEnStocks.get(i).getPrecio()), productoEnStocks.get(i).getCategoriaNombre()};
                    modelo.addRow(data);
                    i++;
                }
                paginaPrincipalClientes.scrollPanelTabla.setViewportView(table);

                //hacemos que el boton cambie de color cuando este seleccionado
                if (botonPresionadoActual != null) {
                    botonPresionadoActual.setBackground(UIManager.getColor("Button.background"));
                }
                botonPresionadoActual = mascotasButton;
                botonPresionadoActual.setBackground(new Color(168, 239, 208));

                //Hacemos que solo sea visible el panelMascotas en este boton
                panelTodo.setVisible(false);
                panelRopa.setVisible(false);
                panelHogar.setVisible(false);
                panelAlimentacion.setVisible(false);
                panelMascotas.setVisible(true);
            }
        });


        todoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {

                String titulosEncabezado[] = {"ID", "Nombre", "Precio", "Categoría"}; //a los clientes solo se les muestra estos campos
                table = new JTable();
                //se necesita un DefaultTableModel para usar la función addRow, pero se toma el modelo que tiene la propia JTable para no modificar nada (si se deja modelo= new DefaultTableModel no se muestra la tabla)
                DefaultTableModel modelo = (DefaultTableModel) table.getModel();
                modelo.setColumnIdentifiers(titulosEncabezado);
                Catalogo catalogo = Controller.catalogo; //se toma directamente el objeto catálogo del controller, que contiene todos los productos de la bd
                ArrayList<ProductoEnStock> productoEnStocks = catalogo.getCatalogo();
                //se van añadiendo las filas de productos a la tabla
                int i = 0;
                for (ProductoEnStock e : productoEnStocks) {
                    String[] data = {String.valueOf(productoEnStocks.get(i).getIdProducto()), productoEnStocks.get(i).getNombre(), String.valueOf(productoEnStocks.get(i).getPrecio()), productoEnStocks.get(i).getCategoriaNombre()};
                    modelo.addRow(data);
                    i++;
                }
                paginaPrincipalClientes.scrollPanelTabla.setViewportView(table);

                //hacemos que el boton cambie de color cuando este seleccionado
                if (botonPresionadoActual != null) {
                    botonPresionadoActual.setBackground(UIManager.getColor("Button.background"));
                }
                botonPresionadoActual = todoButton;
                botonPresionadoActual.setBackground(new Color(168, 239, 208));

                //Hacemos que solo sea visible el panelTodo en este boton
                panelTodo.setVisible(true);
                panelRopa.setVisible(false);
                panelHogar.setVisible(false);
                panelAlimentacion.setVisible(false);
                panelMascotas.setVisible(false);
            }
        });

        botonMiCuenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    VentanaCuentaCliente.crearVentanaCuentaCliente();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                paginaPrincipalClientes.dispose();
            }
        });
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paginaPrincipalClientes.dispose();
            }
        });
    }

    public static void crearVentanaPaginaPrincipalCliente() throws SQLException {
        //añadimos el contenindo a la ventana
        paginaPrincipalClientes.setContentPane(paginaPrincipalClientes.container);
        paginaPrincipalClientes.setBounds(300, 90, 1000, 700);
        paginaPrincipalClientes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        paginaPrincipalClientes.setTitle("Página principal");
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
        paginaPrincipalClientes.spinner1.setModel(new SpinnerNumberModel(1, 1, 100, 1));
        paginaPrincipalClientes.scrollPanelTabla.setViewportView(table);

        //Poniendo el actionListener en null logramos que nos muestre el contenido del todoButton al crear la ventana
        ActionListener todoButtonActionListener = paginaPrincipalClientes.todoButton.getActionListeners()[0];
        // Llamamos al ActionListener del botón "todoButton"
        todoButtonActionListener.actionPerformed(null);
        //añadimos imagen al boton salir
        try {
            BufferedImage img = ImageIO.read(new File("resources/cerrar_sesion.png"));
            Image scaledImg = img.getScaledInstance(30, 30, Image.SCALE_AREA_AVERAGING);
            paginaPrincipalClientes.salirButton.setIcon(new ImageIcon(scaledImg));
        } catch (IOException ex) {
            System.err.println("Error al cargar la imagen: " + ex.getMessage());
        }
        //miniatura de ventana
        ImageIcon logo= new ImageIcon("./././resources/logo_lurpiazon_2.png");
        paginaPrincipalClientes.setIconImage(logo.getImage()); //thumbnail del programa
        //Hacemos que solo sea visible el panelTodo en el todoButton y al crear la ventana
        paginaPrincipalClientes.panelTodo.setVisible(true);
        paginaPrincipalClientes.panelRopa.setVisible(false);
        paginaPrincipalClientes.panelHogar.setVisible(false);
        paginaPrincipalClientes.panelAlimentacion.setVisible(false);
        paginaPrincipalClientes.panelMascotas.setVisible(false);


    }


}
