package model.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 * Clase Pedido.
 */
public class Pedido {

    private int idPedido;
    private Cesta pedido;
    private Timestamp fecha;
    private float precio;
    private Cliente cliente;

    /**
     * Constructor de Pedido.
     *
     * @param idPedido es el id pedido
     * @param pedido   es el pedido
     * @param fecha    es la fecha
     * @param precio   es el precio
     * @param cliente  es el cliente
     */
    public Pedido(int idPedido, Cesta pedido, Timestamp fecha, float precio, Cliente cliente) {
        this.idPedido = idPedido;
        this.pedido = pedido;
        this.fecha = fecha;
        this.precio = precio;
        this.cliente = cliente;
    }

    /**
     * Constructor vacio de Pedido.
     */
    public Pedido() {
    }

    /**
     * Mostrar pedido array list.
     *
     * @return el array list
     */
    public ArrayList<DetallesProducto> mostrarPedido() {
        return null;
    }

    /**
     * Gets pedido.
     *
     * @return el pedido
     */
    public Cesta getPedido() {
        return pedido;
    }

    /**
     * Sets pedido.
     *
     * @param pedido es el pedido
     */
    public void setPedido(Cesta pedido) {
        this.pedido = pedido;
    }

    /**
     * Gets fecha.
     *
     * @return la fecha
     */
    public Timestamp getFecha() {
        return fecha;
    }

    /**
     * Sets fecha.
     *
     * @param fecha es la fecha
     */
    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
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
     * Gets cliente.
     *
     * @return el cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Sets cliente.
     *
     * @param cliente es el cliente
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Gets id pedido.
     *
     * @return el id pedido
     */
    public int getIdPedido() {
        return idPedido;
    }

    /**
     * Sets id pedido.
     *
     * @param idPedido de id pedido
     */
    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }
}


