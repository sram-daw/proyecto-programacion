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

    //añadir metodo mostrarHistorialTotalPedidos
    public ListaUsers mostrarListaUsuarios(){
        return null;
    }

    public void reponerStock(){

    }
    //añadir metodo comprobarStock
}
