package model.dao;

import java.util.ArrayList;
import java.util.Date;

public class Pedidos {

    private ArrayList<Cesta> pedido;
    private Date fecha;
    private int idPedido;
    private int idUsuario;
    private float precio;


    public Pedidos(ArrayList<Cesta> pedido, Date fecha, int idPedido, int idUsuario, float precio) {
        this.pedido = pedido;
        this.fecha = fecha;
        this.idPedido = idPedido;
        this.idUsuario = idUsuario;
        this.precio = precio;
    }

    public Pedidos(Date fecha, int idPedido, int idUsuario, float precio) {
        this.fecha = fecha;
        this.idPedido = idPedido;
        this.idUsuario = idUsuario;
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

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }
}

