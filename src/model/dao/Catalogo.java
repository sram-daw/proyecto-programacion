package model.dao;

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
