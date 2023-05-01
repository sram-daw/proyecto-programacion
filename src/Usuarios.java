public abstract class Usuarios {
    private String idUsuario;
    private Boolean isAdmin;
    private String nombreUsuario;
    private String pwd;

    public Usuarios() {
    }

    public Usuarios(String idUsuario, Boolean isAdmin, String nombreUsuario, String pwd) {
        this.idUsuario = idUsuario;
        this.isAdmin = isAdmin;
        this.nombreUsuario = nombreUsuario;
        this.pwd = pwd;
    }

    //metodos abstracto
    public abstract Boolean login(String nombreUsuario, String pwd);

    public abstract Boolean signIn(String nombreUsuario,String pwd, Boolean isAdmin);
}
