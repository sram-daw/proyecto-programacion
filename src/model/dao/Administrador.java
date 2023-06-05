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

}
