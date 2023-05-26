package model.dao;

import java.util.ArrayList;

public class HistorialPedidosTotal {

    private ArrayList<Pedido> totalPedidos;

    public HistorialPedidosTotal() {
    }

        public ArrayList<Pedido> getTotalPedidos() {
        return totalPedidos;
    }

    public void setTotalPedidos(ArrayList<Pedido> totalPedidos) {
        this.totalPedidos = totalPedidos;
    }
}
