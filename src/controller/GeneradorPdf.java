package controller;


import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;

import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;


import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.property.VerticalAlignment;
import model.dao.DetallesProducto;
import model.dao.HistorialPedidosTotal;
import model.dao.Pedido;

import javax.swing.*;
import java.io.IOException;
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

            // Se añade el logo
            String rutaImagen = "././resources/logo-placeholder.jpg";
            ImageData imageData = ImageDataFactory.create(rutaImagen);
            com.itextpdf.layout.element.Image image = new com.itextpdf.layout.element.Image(imageData);
            image.setHeight(80);
            image.setRelativePosition(400, 2, 30, 30);
            image.setMarginBottom(10);
            document.add(image);


            //Creación de la tabla para los datos del cliente
            Table infoTable = new Table(new float[]{1, 1, 1});
            infoTable.setWidth(UnitValue.createPercentValue(80));
            infoTable.setHorizontalAlignment(HorizontalAlignment.CENTER);

            Text tituloNombre = new Text("Nombre: ").setBold();
            // Se añaden los datos del cliente
            Paragraph nombre = new Paragraph();
            nombre.add(tituloNombre)
                    .add(Controller.clienteLogado.getNombre());

            Text tituloApellido = new Text("Apellido: ").setBold();
            Paragraph apellido = new Paragraph();
            apellido.add(tituloApellido)
                    .add(Controller.clienteLogado.getApellido());

            Text tituloDireccion = new Text("Dirección: ").setBold();
            Paragraph direccion = new Paragraph();
            direccion.add(tituloDireccion)
                    .add(Controller.clienteLogado.getDireccion());

            Text tituloCp = new Text("Código Postal: ").setBold();
            Paragraph cp = new Paragraph();
            cp.add(tituloCp)
                    .add(Controller.clienteLogado.getCp());

            Text tituloTlf = new Text("Teléfono: ").setBold();
            Paragraph tlf = new Paragraph();
            tlf.add(tituloTlf)
                    .add(Controller.clienteLogado.getNumTelf());

            Text tituloEmail = new Text("Email: ").setBold();
            Paragraph email = new Paragraph();
            email.add(tituloEmail)
                    .add(Controller.clienteLogado.getEmail());

            Text tituloFecha = new Text("Fecha: ").setBold();
            Paragraph fecha = new Paragraph();
            fecha.add(tituloFecha)
                    .add(String.valueOf(pedido.getFecha()));


            // Se añaden los detalles del pedido
            Text tituloPedido = new Text("Número de pedido: ").setBold();
            Paragraph numPedido = new Paragraph();
            numPedido.add(tituloPedido)
                    .add(String.valueOf(idPedido));


            Cell cell1 = new Cell().add(nombre);
            Cell cell2 = new Cell().add(apellido);
            Cell cell3 = new Cell().add(direccion);
            Cell cell4 = new Cell().add(cp);
            Cell cell5 = new Cell().add(tlf);
            Cell cell6 = new Cell().add(email);
            Cell cell7 = new Cell().add(fecha);
            Cell cell8 = new Cell().add(numPedido);

            infoTable.addCell(cell1);
            infoTable.addCell(cell2);
            infoTable.addCell(cell3);
            infoTable.addCell(cell4);
            infoTable.addCell(cell5);
            infoTable.addCell(cell6);
            infoTable.addCell(cell7);
            infoTable.addCell(cell8);

            document.add(infoTable);


            //Creación de la tabla del desglose del pedido
            Table table = new Table(new float[]{4, 1.5f, 1.5f, 2.5f});
            table.setHorizontalAlignment(HorizontalAlignment.CENTER);
            table.setWidth(UnitValue.createPercentValue(80));
            table.setMarginTop(50);
            // Se añaden los encabezados de la tabla
            List<String> headers = Arrays.asList("Producto", "Unidades", "Precio por unidad", "Precio total");

            for (String header : headers) {
                Cell headerCell = new Cell();
                headerCell.add(new Paragraph(header).setTextAlignment(TextAlignment.CENTER)).setBackgroundColor(new DeviceRgb(34, 255, 58));
                table.addHeaderCell(headerCell);
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
