package model.dao;

import java.util.ArrayList;

/**
 * Clase Historial pedidos total.
 */
public class HistorialPedidosTotal {

    private ArrayList<Pedido> totalPedidos;

    /**
     * Constructor Historial pedidos total.
     */
    public HistorialPedidosTotal() {
    }

    /**
     * Gets total pedidos.
     *
     * @return el total pedidos
     */
    public ArrayList<Pedido> getTotalPedidos() {
        return totalPedidos;
    }

    /**
     * Sets total pedidos.
     *
     * @param totalPedidos el total pedidos
     */
    public void setTotalPedidos(ArrayList<Pedido> totalPedidos) {
        this.totalPedidos = totalPedidos;
    }
}
