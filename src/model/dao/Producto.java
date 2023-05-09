package model.dao;

public abstract class Producto {

    private int idProducto;
    private String nombre;
    private float precio;
    private int categoriaID;
    private String categoriaNombre;

    public Producto(int idProducto, String nombre, float precio, int categoriaID, String categoriaNombre) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.categoriaID = categoriaID;
        this.categoriaNombre = categoriaNombre;
    }

    public Producto() {
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getCategoriaID() {
        return categoriaID;
    }

    public void setCategoriaID(int categoriaID) {
        this.categoriaID = categoriaID;
    }

    public String getCategoriaNombre() {
        return categoriaNombre;
    }

    public void setCategoriaNombre(String categoriaNombre) {
        this.categoriaNombre = categoriaNombre;
    }
}
