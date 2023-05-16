package model.dao;

import java.util.ArrayList;

public class HistorialPedidosTotal {
    public HistorialPedidosTotal() {
    }

    public HistorialPedidosTotal(ArrayList<Pedido> totalPedidos) {
        this.totalPedidos = totalPedidos;
    }

    private ArrayList<Pedido> totalPedidos;

    public ArrayList<Pedido> getTotalPedidos() {
        return totalPedidos;
    }

    public void setTotalPedidos(ArrayList<Pedido> totalPedidos) {
        this.totalPedidos = totalPedidos;
    }
}
