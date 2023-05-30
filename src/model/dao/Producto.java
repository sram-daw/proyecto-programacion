package model.dao;

/**
 * Clase abstracta Producto.
 */
public abstract class Producto {

    private int idProducto;
    private String nombre;
    private float precio;
    private int categoriaID;
    private String categoriaNombre;

    /**
     * Constructor Producto.
     *
     * @param idProducto      es el id producto
     * @param nombre          es el nombre
     * @param precio          es el precio
     * @param categoriaID     es la categoria id
     * @param categoriaNombre es la categoria nombre
     */
    public Producto(int idProducto, String nombre, float precio, int categoriaID, String categoriaNombre) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.categoriaID = categoriaID;
        this.categoriaNombre = categoriaNombre;
    }

    /**
     * Constructor vacio de Producto.
     */
    public Producto() {
    }

    /**
     * Gets id producto.
     *
     * @return el id producto
     */
    public int getIdProducto() {
        return idProducto;
    }

    /**
     * Sets id producto.
     *
     * @param idProducto es el id producto
     */
    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    /**
     * Gets nombre.
     *
     * @return el nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets nombre.
     *
     * @param nombre es el nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
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
     * @param precio es el precio
     */
    public void setPrecio(float precio) {
        this.precio = precio;
    }

    /**
     * Gets categoria id.
     *
     * @return la categoria id
     */
    public int getCategoriaID() {
        return categoriaID;
    }

    /**
     * Sets categoria id.
     *
     * @param categoriaID es la categoria id
     */
    public void setCategoriaID(int categoriaID) {
        this.categoriaID = categoriaID;
    }

    /**
     * Gets categoria nombre.
     *
     * @return la categoria nombre
     */
    public String getCategoriaNombre() {
        return categoriaNombre;
    }

    /**
     * Sets categoria nombre.
     *
     * @param categoriaNombre es la categoria nombre
     */
    public void setCategoriaNombre(String categoriaNombre) {
        this.categoriaNombre = categoriaNombre;
    }
}
