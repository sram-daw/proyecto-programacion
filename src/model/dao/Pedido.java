package model.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class Pedido {

    private int idPedido;
    private Cesta pedido;
    private Timestamp fecha;
    private float precio;

    private Cliente cliente;

    public Pedido(int idPedido, Cesta pedido, Timestamp fecha, float precio, Cliente cliente) {
        this.idPedido = idPedido;
        this.pedido = pedido;
        this.fecha = fecha;
        this.precio = precio;
        this.cliente = cliente;
    }

    public Pedido() {
    }

    public ArrayList<DetallesProducto> mostrarPedido() {
        return null;
    }

    public Cesta getPedido() {
        return pedido;
    }

    public void setPedido(Cesta pedido) {
        this.pedido = pedido;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }
}


