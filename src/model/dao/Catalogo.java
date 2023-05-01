<<<<<<<< HEAD:src/model/dao/Catalogo.java
<<<<<<<< HEAD:src/model/dao/Catalogo.java
package model.dao;
========
package com.dao;
>>>>>>>> c3a7b76 (Reorganizacion para mvc):src/com/dao/Catalogo.java
========
package dao;
>>>>>>>> e77302a (ventanas y código de registro añadidos, cambio de estructura en carpetas):src/dao/Catalogo.java

import java.util.ArrayList;

public class Catalogo {

    private ArrayList<ProductoEnStock> catalogo;

    public Catalogo(ArrayList<ProductoEnStock> catalogo) {
        this.catalogo = catalogo;
    }

    public Catalogo() {
    }

    public ArrayList<ProductoEnStock> mostrarCatalogo() {
        return null;
    }

    public ArrayList<ProductoEnStock> mostrarCatalogoPorCategoria(String idProducto) {
        return null;
    }

    public ArrayList<ProductoEnStock> getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(ArrayList<ProductoEnStock> catalogo) {
        this.catalogo = catalogo;
    }
}
