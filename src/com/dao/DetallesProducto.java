package com.dao;

public class DetallesProducto extends Producto {
    private int cantidad;

    public DetallesProducto(String idProducto, float precio, int categoriaID, String categoriaNombre) {
        super(idProducto, precio, categoriaID, categoriaNombre);
    }

    public DetallesProducto() {
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
