package model.dao;

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
