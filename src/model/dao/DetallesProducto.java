package model.dao;

/**
 * Clase Detalles producto.
 */
//Objeto producto que se almacena en la cesta
public class DetallesProducto extends Producto {
    private int cantidad;

    /**
     * Constructor Detalles producto.
     *
     * @param idProducto      es el id producto
     * @param nombre          es el nombre
     * @param precio          es el precio
     * @param categoriaID     es la categoria id
     * @param categoriaNombre es el categoria nombre
     * @param cantidad        es la cantidad
     */
    public DetallesProducto(int idProducto, String nombre, float precio, int categoriaID, String categoriaNombre, int cantidad) {
        super(idProducto, nombre, precio, categoriaID, categoriaNombre);
        this.cantidad = cantidad;
    }


    /**
     * Constructor vacio Detalles producto.
     */
    public DetallesProducto() {
    }

    /**
     * Gets cantidad.
     *
     * @return la cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Sets cantidad.
     *
     * @param cantidad la cantidad
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }


}
