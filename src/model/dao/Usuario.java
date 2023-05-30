package model.dao;

/**
 * Clase abstracta Usuario.
 */
public abstract class Usuario {
    private int idUsuario;
    private int isAdmin;
    private String nombreUsuario;
    private String pwd;

    /**
     * Constructor Usuario.
     */
    public Usuario() {
    }

    /**
     * Constructor Usuario.
     *
     * @param idUsuario     es el id usuario
     * @param isAdmin       es is admin
     * @param nombreUsuario es el nombre usuario
     * @param pwd           es la pwd
     */
    public Usuario(int idUsuario,int isAdmin, String nombreUsuario, String pwd) {
        this.idUsuario = idUsuario;
        this.isAdmin = isAdmin;
        this.nombreUsuario = nombreUsuario;
        this.pwd = pwd;
    }

    /**
     * Gets id usuario.
     *
     * @return el id usuario
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * Sets id usuario.
     *
     * @param idUsuario es el id usuario
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * Gets is admin.
     *
     * @return el is admin
     */
    public int getIsAdmin() {
        return isAdmin;
    }

    /**
     * Sets is admin.
     *
     * @param isAdmin es el is admin
     */
    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    /**
     * Gets nombre usuario.
     *
     * @return el nombre usuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * Sets nombre usuario.
     *
     * @param nombreUsuario es el nombre usuario
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * Gets pwd.
     *
     * @return la pwd
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * Sets pwd.
     *
     * @param pwd es la pwd
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
