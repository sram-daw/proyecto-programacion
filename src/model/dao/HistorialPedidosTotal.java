package model.dao;

import java.util.ArrayList;

public class HistorialPedidosTotal {
    public HistorialPedidosTotal() {
    }

    public HistorialPedidosTotal(ArrayList<Pedidos> totalPedidos) {
        this.totalPedidos = totalPedidos;
    }

    private ArrayList<Pedidos> totalPedidos;

    public ArrayList<Pedidos> getTotalPedidos() {
        return totalPedidos;
    }

    public void setTotalPedidos(ArrayList<Pedidos> totalPedidos) {
        this.totalPedidos = totalPedidos;
    }
}
