package model.dao;

import java.util.ArrayList;

/**
 * Clase Catalogo.
 */
public class Catalogo {

    private ArrayList<ProductoEnStock> catalogo;

    /**
     * Constructor Catalogo.
     *
     * @param catalogo es catalogo
     */
    public Catalogo(ArrayList<ProductoEnStock> catalogo) {
        this.catalogo = catalogo;
    }

    /**
     * Constructor vacio Catalogo.
     */
    public Catalogo() {
    }

    /**
     * Mostrar catalogo array list.
     *
     * @return el array list
     */
    public ArrayList<ProductoEnStock> mostrarCatalogo() {
        return null;
    }

    /**
     * Mostrar catalogo por categoria array list.
     *
     * @param idProducto es id producto
     * @return el array list
     */
    public ArrayList<ProductoEnStock> mostrarCatalogoPorCategoria(String idProducto) {
        return null;
    }

    /**
     * Gets catalogo.
     *
     * @return el catalogo
     */
    public ArrayList<ProductoEnStock> getCatalogo() {
        return catalogo;
    }

    /**
     * Sets catalogo.
     *
     * @param catalogo el catalogo
     */
    public void setCatalogo(ArrayList<ProductoEnStock> catalogo) {
        this.catalogo = catalogo;
    }
}
