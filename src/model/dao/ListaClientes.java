package model.dao;

import java.util.ArrayList;

public class ListaClientes {
    ArrayList<Cliente> listaClientes;

    public ListaClientes() {
    }

    public ListaClientes(ArrayList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(ArrayList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }
}
