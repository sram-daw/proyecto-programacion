package model.dao;

import java.util.ArrayList;
import java.util.Date;

public class Pedidos {

    private ArrayList<Cesta> pedido;
    private Date fecha;

    private float precio;

    private Cliente cliente;

    public Pedidos(ArrayList<Cesta> pedido, Date fecha, float precio, Cliente cliente) {
        this.pedido = pedido;
        this.fecha = fecha;
        this.precio = precio;
        this.cliente = cliente;
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
}

