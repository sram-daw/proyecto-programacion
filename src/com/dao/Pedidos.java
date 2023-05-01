package com.dao;

import java.util.ArrayList;
import java.util.Date;

public class Pedidos {

    private ArrayList<Cesta> pedido;
    private Date fecha;

    public Pedidos(ArrayList<Cesta> pedido, Date fecha) {
        this.pedido = pedido;
        this.fecha = fecha;
    }

    public Pedidos() {
    }

    public ArrayList<DetallesProducto> mostrarPedido() {
        return null;
    }
}
