<<<<<<<< HEAD:src/model/dao/ProductoEnStock.java
<<<<<<<< HEAD:src/model/dao/ProductoEnStock.java
package model.dao;
========
package com.dao;
>>>>>>>> c3a7b76 (Reorganizacion para mvc):src/com/dao/ProductoEnStock.java
========
package dao;
>>>>>>>> e77302a (ventanas y código de registro añadidos, cambio de estructura en carpetas):src/dao/ProductoEnStock.java

public class ProductoEnStock extends Producto{

    private int stock;

    public ProductoEnStock(int idProducto, String nombre, float precio, int categoriaID, String categoriaNombre, int stock) {
        super(idProducto, nombre, precio, categoriaID, categoriaNombre);
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
