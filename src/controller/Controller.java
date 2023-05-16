package controller;

import model.Model;
import model.dao.*;
import view.View;

import java.sql.SQLException;

public class Controller<UsuarioGenerico extends Usuario> { //se usa generalidad para poder devolver un objeto cliente y administrador en el método iniciarSesion

    static Cesta cesta = new Cesta(); //se instancia globalmente un objeto cesta para usarlo repetidas veces si se añaden varios productos

    //los objetos cliente y administrador se instancian globalmente para poder hacer uso de ellos más adelante cuando queramos comprobar sus datos en el historial de pedidos o en los datos personales
    static Cliente clienteLogado = new Cliente();
    static Administrador administradorLogado = new Administrador();

    //Método para registrar clientes
    static public boolean registrarse(String nombreUsuario, String pwd, String direccion, String tlf, String cp, String email, String nombre, String apellido) {
        Cliente nuevoCliente = new Cliente(0, nombreUsuario, pwd, direccion, tlf, email, cp, nombre, apellido); //indicamos directamente que isadmin es false (0 porque es de tipo tinyint en la bd) porque los admins se dan de alta solo en la propia bd, no desde la app
        return Model.registrarCliente(nuevoCliente);
    }

    //Método para comprobar si los datos introducidos por el usuario en el login son correctos
    static public boolean comprobarDatosLogin(String nombreUsuario, String pwd) {
        boolean existeUser = false;
        existeUser = Model.comprobarDatosLoginOk(nombreUsuario, pwd);
        return existeUser;
    }

    //Método para iniciar sesión
    static public boolean iniciarSesion(String nombreUsuario, String pwd) {
        //se comprueba si los datos introducidos por el usuario pertenecen a un admin o a un cliente
        boolean isAdmin = Model.comprobarSiAdmin(nombreUsuario, pwd);
        if (isAdmin) {
            administradorLogado = Model.getAdministradorLogado(nombreUsuario, pwd); //aquí se asigna la variable global administradorLogado usando el método getAdministradorLogado, que devuelve un objeto Administrador
        } else {
            clienteLogado = Model.getClienteLogado(nombreUsuario, pwd); //aquí se asigna la variable global clienteLogado usando el método getAClienteLogado, que devuelve un objeto Cliente
        }
        return isAdmin;
    }

    //Metodo para agregar el modelo a la tabla Almacen
    static public Catalogo agregarTablaAlmacen() throws SQLException {
        return Model.obtenerDatosAlmacen();
    }

    //Método para agregar el modelo a la tabla de la lista de clientes
    static public ListaClientes agregarTablaCliente() throws SQLException {
        return Model.obtenerDatosCliente();
    }

    static public HistorialPedidosTotal agregartTablaPedidos()throws SQLException{
        return Model.obtenerDatosPedidos();
    }


    //Método para agregar productos al arraylist de DetallesProducto que contiene Cesta
    public static Cesta addProductoToCesta(int id, String nombre, float precio, String categoria, int cantidad) {
        DetallesProducto nuevoProducto = new DetallesProducto();
        nuevoProducto.setIdProducto(id);
        nuevoProducto.setNombre(nombre);
        nuevoProducto.setPrecio(precio);
        nuevoProducto.setCategoriaNombre(categoria);
        nuevoProducto.setCantidad(cantidad);
        cesta.getCesta().add(nuevoProducto);
        //se obtiene el precio actual de la cesta
        Float precioTotal = cesta.getPrecio();
        //se suma el precio del producto añadido por la cantidad
        precioTotal += precio * cantidad;
        cesta.setPrecio(precioTotal);
        return cesta;
    }

    public static void main(String[] args) {
        Model model = new Model();
        //Instancia de view.View. El constructor contiene la llamada al método que crea la primera ventana
        View view = new View();
        //Conexión a la bbdd
        model.establecerConexionBd(); //se establece la conexión con la bd antes de nada
    }
}