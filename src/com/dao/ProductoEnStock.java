package com.dao;

public class ProductoEnStock extends Producto{

    private int stock;

    public ProductoEnStock(String idProducto, float precio, int categoriaID, String categoriaNombre, int stock) {
        super(idProducto, precio, categoriaID, categoriaNombre);
        this.stock = stock;
    }

    public ProductoEnStock() {
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
