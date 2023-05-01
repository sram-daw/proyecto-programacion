package com.dao;

import java.util.ArrayList;

public class ListaUsers {

    public ListaUsers() {
    }

    public ListaUsers(ArrayList<Cliente> listaUser) {
        ListaUser = listaUser;
    }

    public ArrayList<Cliente> getListaUser() {
        return ListaUser;
    }

    public void setListaUser(ArrayList<Cliente> listaUser) {
        ListaUser = listaUser;
    }

    private ArrayList<Cliente> ListaUser;
}
