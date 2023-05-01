package dao;

import java.util.ArrayList;
import java.util.Date;

public class Pedidos {

    private ArrayList<Cesta> pedido;
    private Date fecha;

    private int idPedido;

    private float precio;


    public Pedidos(ArrayList<Cesta> pedido, Date fecha, int idPedido, float precio) {
        this.pedido = pedido;
        this.fecha = fecha;
        this.idPedido = idPedido;
        this.precio = precio;
    }

    public Pedidos() {
    }

    public ArrayList<DetallesProducto> mostrarPedido() {
        return null;
    }

    public ArrayList<Cesta> getPedido() {
        return pedido;
    }

    public void setPedido(ArrayList<Cesta> pedido) {
        this.pedido = pedido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }
}

