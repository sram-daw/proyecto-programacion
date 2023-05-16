package model.dao;

public abstract class Usuario {
    private int idUsuario;
    private int isAdmin;
    private String nombreUsuario;
    private String pwd;

    public Usuario() {
    }

    public Usuario(int idUsuario,int isAdmin, String nombreUsuario, String pwd) {
        this.idUsuario = idUsuario;
        this.isAdmin = isAdmin;
        this.nombreUsuario = nombreUsuario;
        this.pwd = pwd;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
