package controller;

import model.Model;
import model.dao.Catalogo;
import model.dao.ListaClientes;
import model.dao.Cesta;
import model.dao.DetallesProducto;
import view.View;
import model.dao.Cliente;

import java.sql.SQLException;
import java.util.HashMap;

public class Controller {

    static Cesta cesta = new Cesta(); //se instancia globalmente un objeto cesta para usarlo repetidas veces si se añaden varios productos

    //Método para registrar clientes
    static public boolean registrarse(String nombreUsuario, String pwd, String direccion, String tlf, String cp, String email, String nombre, String apellido) {
        Cliente nuevoCliente = new Cliente(0, nombreUsuario, pwd, direccion, tlf, email, cp, nombre, apellido); //indicamos directamente que isadmin es false (0 porque es de tipo tinyint en la bd) porque los admins se dan de alta solo en la propia bd, no desde la app
        return Model.addClienteBd(nuevoCliente);
    }

    //Método para iniciar sesión
    static public HashMap<String, Boolean> iniciarSesion(String nombreUsuario, String pwd) {
        return Model.comprobarInicioSesOk(nombreUsuario, pwd);
    }

    //Metodo para agregar el modelo a la tabla Almacen
    static public Catalogo agregarTablaAlmacen() throws SQLException {
        return Model.obtenerDatosAlmacen();
    }
    //Método para agregar el modelo a la tabla de la lista de clientes
    static public ListaClientes agregarTablaCliente() throws SQLException{
        return Model.obtenerDatosCliente();
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