package model.dao;

import java.util.ArrayList;

public class HistorialPedidosTotal {
    private ArrayList<Pedidos> totalPedidos;

    public HistorialPedidosTotal() {
    }

    public HistorialPedidosTotal(ArrayList<Pedidos> totalPedidos) {
        this.totalPedidos = totalPedidos;
    }

    public ArrayList<Pedidos> getTotalPedidos() {
        return totalPedidos;
    }

    public void setTotalPedidos(ArrayList<Pedidos> totalPedidos) {
        this.totalPedidos = totalPedidos;
    }
}
