package controller;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;

import static com.itextpdf.layout.borders.Border.NO_BORDER;

import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import model.dao.DetallesProducto;
import model.dao.HistorialPedidosTotal;
import model.dao.Pedido;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Model;


public class GeneradorPdf {
    public static void generarFactura(int idPedido) throws IOException {
        HistorialPedidosTotal pedidos = Controller.agregarTablaPedidos();
        ArrayList<Pedido> listaPedidos = pedidos.getTotalPedidos();
        Pedido pedido = new Pedido();
        for (Pedido p : listaPedidos) {
            if (p.getIdPedido() == idPedido) { //
                pedido = p;
            }
        }
        ArrayList<DetallesProducto> productos = new ArrayList<>();
        productos = Model.obtenerDetallesPedido(idPedido);

        try {

            // Se crea el PdfDocument
            PdfWriter writer = new PdfWriter("factura_" + idPedido + ".pdf");
            PdfDocument pdf = new PdfDocument(writer);

            // Se crea un nuevo document
            Document document = new Document(pdf);

            // Se añaden los datos del cliente
            Paragraph nombre = new Paragraph("Nombre: " + Controller.clienteLogado.getNombre());
            document.add(nombre);

            Paragraph apellido = new Paragraph("Apellido: " + Controller.clienteLogado.getApellido());
            document.add(apellido);

            Paragraph direccion = new Paragraph("Dirección: " + Controller.clienteLogado.getDireccion());
            document.add(direccion);

            Paragraph cp = new Paragraph("Código Postal: " + Controller.clienteLogado.getCp());
            document.add(cp);

            Paragraph tlf = new Paragraph("Teléfono: " + Controller.clienteLogado.getNumTelf());
            document.add(tlf);

            Paragraph email = new Paragraph("Email: " + Controller.clienteLogado.getEmail());
            document.add(email);

            Paragraph fecha = new Paragraph("Fecha del pedido: " + pedido.getFecha());
            document.add(fecha);

            // Se añaden los detalles del pedido
            Paragraph numPedido = new Paragraph("Número de pedido: " + idPedido);
            document.add(numPedido);
            Paragraph empty = new Paragraph("");

            // Create a table with 3 columns
            Table table = new Table(new float[]{4, 1.5f, 1.5f, 2.5f});
            table.setHorizontalAlignment(HorizontalAlignment.CENTER);
            table.setWidth(UnitValue.createPercentValue(80));
            table.setMarginTop(20);
            // Se añaden los encabezados de la tabla
            List<String> headers = Arrays.asList("Producto", "Unidades", "Precio por unidad", "Precio total");
            for (String header : headers) {
                table.addHeaderCell(new Paragraph(header).setTextAlignment(TextAlignment.CENTER));
            }

            // Se añaden las filas con los detalles de cada producto
            for (DetallesProducto p : productos) {
                table.addCell(new Cell().add(new Paragraph(p.getNombre())));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(p.getCantidad()))));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(p.getPrecio()))));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(p.getPrecio() * p.getCantidad()))));
            }

            // Se añade la tabla al documento
            document.add(table);

            // Se crea otra tabla para el total
            Table totalTable = new Table(1);
            totalTable.setHorizontalAlignment(HorizontalAlignment.RIGHT);
            totalTable.setMarginRight(52);
            Cell totalCell = new Cell().add(new Paragraph("TOTAL: " + pedido.getPrecio()));
            totalCell.setBorder(new SolidBorder(1));
            totalCell.setTextAlignment(TextAlignment.CENTER);
            totalTable.addCell(totalCell);
            document.add(totalTable);

            // Close the document
            document.close();

            JOptionPane.showMessageDialog(null, "Factura descargada con éxito.", "Factura descargada.", JOptionPane.INFORMATION_MESSAGE);

        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        // Abre el pdf automáticamente
        if (Desktop.isDesktopSupported()) {
            try {
                File file = new File("factura_" + idPedido + ".pdf");
                Desktop.getDesktop().open(file);
            } catch (IOException ex) {
                System.out.println("Error al abrir el archivo.");
                ex.printStackTrace();
            }
        }
    }
}
