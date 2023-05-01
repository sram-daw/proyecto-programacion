<<<<<<<< HEAD:src/model/dao/DetallesProducto.java
package model.dao;
========
package com.dao;
>>>>>>>> c3a7b76 (Reorganizacion para mvc):src/com/dao/DetallesProducto.java

public class DetallesProducto extends Producto {
    private int cantidad;

    public DetallesProducto(int idProducto, String nombre, float precio, int categoriaID, String categoriaNombre, int cantidad) {
        super(idProducto, nombre, precio, categoriaID, categoriaNombre);
        this.cantidad = cantidad;
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
