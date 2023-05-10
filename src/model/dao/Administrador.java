package model.dao;

import java.util.ArrayList;

public class Administrador extends Usuario {
    public Administrador() {
    }

    public Administrador(int isAdmin, String nombreUsuario, String pwd) {
        super(isAdmin, nombreUsuario, pwd);
    }

    public ArrayList<Pedidos> mostrarHistorialTotalPedidos(){
        return null;
    }
    /*public ListaUsers mostrarListaUsuarios(){
        return null;
    }*/

    public void reponerStock(){

    }
    public Catalogo comprobarStock(){
        return null;
    }
}
