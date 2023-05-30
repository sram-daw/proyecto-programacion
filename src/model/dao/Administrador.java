package model.dao;

import java.util.ArrayList;

/**
 * Clase Administrador.
 */
public class Administrador extends Usuario {
    /**
     * Constructor vacio Administrador.
     */
    public Administrador() {
    }

    /**
     * Instantiates a new Administrador.
     *
     * @param idUsuario      es id usuario
     * @param isAdmin        es is admin
     * @param nombreUsuario  es nombre usuario
     * @param pwd            es pwd
     */
    public Administrador(int idUsuario, int isAdmin, String nombreUsuario, String pwd) {
        super(idUsuario, isAdmin, nombreUsuario, pwd);
    }

    /**
     * Mostrar historial total pedidos array list.
     *
     * @return array list
     */
    public ArrayList<Pedido> mostrarHistorialTotalPedidos(){
        return null;
    }
    /*public ListaUsers mostrarListaUsuarios(){
        return null;
    }*/

    /**
     * Reponer stock.
     */
    public void reponerStock(){

    }

    /**
     * Comprobar stock catalogo.
     *
     * @return el catalogo
     */
    public Catalogo comprobarStock(){
        return null;
    }
}
