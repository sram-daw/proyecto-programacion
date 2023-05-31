package model.dao;

import java.util.ArrayList;

/**
 * clase Cliente.
 */
public class Cliente extends Usuario {

    private String direccion;
    private String numTelf;
    private String email;
    private String cp;
    private String nombre;
    private String apellido;


    /**
     * Constructor vacio de Cliente.
     */
    public Cliente() {
    }

    /**
     * Constructor de Cliente.
     *
     * @param idUsuario     es el id usuario
     * @param isAdmin       es is admin
     * @param nombreUsuario es el nombre usuario
     * @param pwd           es la pwd
     * @param direccion     es la direccion
     * @param numTelf       es el num telf
     * @param email         es el email
     * @param cp            es el cp
     * @param nombre        es el nombre
     * @param apellido      es el apellido
     */
    public Cliente(int idUsuario, int isAdmin, String nombreUsuario, String pwd, String direccion, String numTelf, String email, String cp, String nombre, String apellido) {
        super(idUsuario, isAdmin, nombreUsuario, pwd);
        this.direccion = direccion;
        this.numTelf = numTelf;
        this.email = email;
        this.cp = cp;
        this.nombre = nombre;
        this.apellido = apellido;
    }


    /**
     * Gets direccion.
     *
     * @return la direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Sets direccion.
     *
     * @param direccion la direccion
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Gets num telf.
     *
     * @return el num telf
     */
    public String getNumTelf() {
        return numTelf;
    }

    /**
     * Sets num telf.
     *
     * @param numTelf el num telf
     */
    public void setNumTelf(String numTelf) {
        this.numTelf = numTelf;
    }

    /**
     * Gets email.
     *
     * @return el email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email el email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets cp.
     *
     * @return el cp
     */
    public String getCp() {
        return cp;
    }

    /**
     * Sets cp.
     *
     * @param cp el cp
     */
    public void setCp(String cp) {
        this.cp = cp;
    }

    /**
     * Gets nombre.
     *
     * @return el nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets nombre.
     *
     * @param nombre el nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Gets apellido.
     *
     * @return el apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Sets apellido.
     *
     * @param apellido el apellido
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }


}
