package com.dao;

public abstract class Producto {

    private int idProducto;
    private String nombre;
    private float precio;
    private int categoriaID;
    private String categoriaNombre;

    public Producto(int idProducto, String nombre, float precio, int categoriaID, String categoriaNombre) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.categoriaID = categoriaID;
        this.categoriaNombre = categoriaNombre;
    }

    public Producto() {
    }
}
