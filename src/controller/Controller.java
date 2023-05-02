package controller;

import model.Model;
import view.View;
import model.dao.Cliente;

import java.util.HashMap;

public class Controller {

    //Método para registrar clientes
    static public boolean registrarse(String nombreUsuario, String pwd, String direccion, String tlf, String cp, String email, String nombre, String apellido) {
        Cliente nuevoCliente = new Cliente(0, nombreUsuario, pwd, direccion, tlf, email, cp, nombre, apellido); //indicamos directamente que isadmin es false (0 porque es de tipo tinyint en la bd) porque los admins se dan de alta solo en la propia bd, no desde la app
        return Model.addClienteBd(nuevoCliente);
    }

    //Método para iniciar sesión
    static public HashMap<String, Boolean> iniciarSesion(String nombreUsuario, String pwd) {
        return Model.comprobarInicioSesOk(nombreUsuario, pwd);
    }

    public static void main(String[] args) {
        Model model = new Model();
        //Instancia de view.View. El constructor contiene la llamada al método que crea la primera ventana
        View view = new View();
        //Conexión a la bbdd
        model.establecerConexionBd(); //se establece la conexión con la bd antes de nada


    }
}