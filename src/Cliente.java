public class Cliente extends Usuarios {

    private String direccion;
    private String numTelf;
    private String email;
    private String cp;
    private String ciudad;
    private String nombre;
    private String apellido;
    private String dni;
    //añadir arraylist de clase pedidos


    public Cliente() {
    }

    public Cliente(String idUsuario, Boolean isAdmin, String nombreUsuario, String pwd, String direccion, String numTelf, String email, String cp, String ciudad, String nombre, String apellido, String dni) {
        super(idUsuario, isAdmin, nombreUsuario, pwd);
        this.direccion = direccion;
        this.numTelf = numTelf;
        this.email = email;
        this.cp = cp;
        this.ciudad = ciudad;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }

    @Override
    public Boolean login(String nombreUsuario, String pwd) {
        return null;
    }

    @Override
    public Boolean signIn(String nombreUsuario, String pwd, Boolean isAdmin) {
        return null;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNumTelf() {
        return numTelf;
    }

    public void setNumTelf(String numTelf) {
        this.numTelf = numTelf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
//añadir metodo mostrarHistorialPedidos
}
