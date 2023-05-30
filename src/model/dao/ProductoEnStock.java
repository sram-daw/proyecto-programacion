package model.dao;
//Objeto producto que se almacena en el catálogo

/**
 * Clase Producto en stock.
 */
public class ProductoEnStock extends Producto{

    private int stock;

    /**
     * Constructor de Producto en stock.
     *
     * @param idProducto      es el id producto
     * @param nombre          es el nombre
     * @param precio          es el precio
     * @param categoriaID     es la categoria id
     * @param categoriaNombre es el categoria nombre
     * @param stock           es el stock
     */
    public ProductoEnStock(int idProducto, String nombre, float precio, int categoriaID, String categoriaNombre, int stock) {
        super(idProducto, nombre, precio, categoriaID, categoriaNombre);
        this.stock = stock;
    }

    /**
     * Constructor vacio de Producto en stock.
     */
    public ProductoEnStock() {
    }

    /**
     * Gets stock.
     *
     * @return el stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * Sets stock.
     *
     * @param stock es el stock
     */
    public void setStock(int stock) {
        this.stock = stock;
    }
}
