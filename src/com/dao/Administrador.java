package com.dao;

import java.util.ArrayList;

public class Administrador extends Usuarios {
    public Administrador() {
    }

    public Administrador(String idUsuario, Boolean isAdmin, String nombreUsuario, String pwd) {
        super(idUsuario, isAdmin, nombreUsuario, pwd);
    }


    @Override
    public Boolean login(String nombreUsuario, String pwd) {
        return null;
    }

    @Override
    public Boolean signIn(String nombreUsuario, String pwd, Boolean isAdmin) {
        return null;
    }

    public ArrayList<Pedidos> mostrarHistorialTotalPedidos(){
        return null;
    }
    public ListaUsers mostrarListaUsuarios(){
        return null;
    }

    public void reponerStock(){

    }
    public Catalogo comprobarStock(){
        return null;
    }
}
