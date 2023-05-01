package dao;

import java.util.ArrayList;

public class Cesta {

    private ArrayList<DetallesProducto> cesta;
    private String idUsuario;

    private float precio;

    public Cesta(ArrayList<DetallesProducto> cesta, String idUsuario, float precio) {
        this.cesta = cesta;
        this.idUsuario = idUsuario;
        this.precio = precio;
    }

    public Cesta() {
    }

    public boolean finalizarCompra(ArrayList<DetallesProducto> cesta) {
        return true;
    }
}
