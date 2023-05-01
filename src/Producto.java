public abstract class Producto {

    private String idProducto;
    private float precio;
    private int categoriaID;
    private String categoriaNombre;

    public Producto(String idProducto, float precio, int categoriaID, String categoriaNombre) {
        this.idProducto = idProducto;
        this.precio = precio;
        this.categoriaID = categoriaID;
        this.categoriaNombre = categoriaNombre;
    }

    public Producto() {
    }
}
