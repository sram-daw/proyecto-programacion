package model.dao;

import java.util.ArrayList;

/**
 * Clase Cesta.
 */
public class Cesta {

    private ArrayList<DetallesProducto> cesta;
    private float precio;

    /**
     * Constructor de Cesta.
     *
     * @param cesta  es la cesta
     * @param precio es el precio
     */
    public Cesta(ArrayList<DetallesProducto> cesta, float precio) {
        this.cesta = cesta;
        this.precio = precio;
    }

    /**
     * Constructor de Cesta.
     */
    public Cesta() {
        this.precio = 0;
        this.cesta = new ArrayList<DetallesProducto>();
    }

    /**
     * Finalizar compra boolean.
     *
     * @param cesta la cesta
     * @return un boolean
     */
    public boolean finalizarCompra(ArrayList<DetallesProducto> cesta) {
        return true;
    }

    /**
     * Gets cesta.
     *
     * @return la cesta
     */
    public ArrayList<DetallesProducto> getCesta() {
        return cesta;
    }

    /**
     * Sets cesta.
     *
     * @param cesta es cesta
     */
    public void setCesta(ArrayList<DetallesProducto> cesta) {
        this.cesta = cesta;
    }

    /**
     * Gets precio.
     *
     * @return el precio
     */
    public float getPrecio() {
        return precio;
    }

    /**
     * Sets precio.
     *
     * @param precio el precio
     */
    public void setPrecio(float precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Cesta{" +
                "cesta=" + cesta +
                ", precio=" + precio +
                '}';
    }
}