package model.dao;

import java.util.ArrayList;

public class Cesta {

    private ArrayList<DetallesProducto> cesta;
    private float precio;

    public Cesta(ArrayList<DetallesProducto> cesta, float precio) {
        this.cesta = cesta;
        this.precio = precio;
    }

    public Cesta() {
        this.precio = 0;
        this.cesta = new ArrayList<DetallesProducto>();
    }

    public boolean finalizarCompra(ArrayList<DetallesProducto> cesta) {
        return true;
    }

    public ArrayList<DetallesProducto> getCesta() {
        return cesta;
    }

    public void setCesta(ArrayList<DetallesProducto> cesta) {
        this.cesta = cesta;
    }

    public float getPrecio() {
        return precio;
    }

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