package model.dao;

import java.util.ArrayList;

/**
 * Clase Lista clientes.
 */
public class ListaClientes {

    /**
     * Lista de clientes.
     */
    ArrayList<Cliente> listaClientes;

    /**
     * Constructor vacio de Lista clientes.
     */
    public ListaClientes() {
    }

    /**
     * Constructor de Lista clientes.
     *
     * @param listaClientes es de lista clientes
     */
    public ListaClientes(ArrayList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    /**
     * Gets lista clientes.
     *
     * @return la lista clientes
     */
    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    /**
     * Sets lista clientes.
     *
     * @param listaClientes de lista clientes
     */
    public void setListaClientes(ArrayList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

}
