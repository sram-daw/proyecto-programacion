package model;

import dao.Cliente;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class Model {

    public static Connection conexion;

    public Model() {

    }

    public static boolean addClienteBd(Cliente nuevoCliente) {
        Boolean isAddOk = false;
        Statement consulta = null;
        String texto_consulta = "INSERT INTO usuarios (is_admin, nombre_usuario, nombre, apellido, pwd, direccion, num_telf, email, cp) " +
                "VALUES (" +
                nuevoCliente.getIsAdmin() + ", " +
                "'" + nuevoCliente.getNombreUsuario() + "', " +
                "'" + nuevoCliente.getNombre() + "', " +
                "'" + nuevoCliente.getApellido() + "', " +
                "'" + nuevoCliente.getPwd() + "', " +
                "'" + nuevoCliente.getDireccion() + "', " +
                "'" + nuevoCliente.getNumTelf() + "', " +
                "'" + nuevoCliente.getEmail() + "', " +
                "'" + nuevoCliente.getCp() + "')";
        try {
            consulta = conexion.createStatement();
            consulta.executeUpdate(texto_consulta);
            System.out.println("Usuario añadido correctamente");
            isAddOk = true;

        } catch (SQLException e) {
            System.out.println("Error al añadir el usuario");
            System.out.println(e.getLocalizedMessage());
            isAddOk = false;
        }
        return isAddOk;
    }

    public void establecerConexionBbdd() {
        conexion = null;
        String url = "jdbc:mysql://localhost:3306/lurpiazon";
        String usuario = "root";
        String passwd = "1234";
        try {
            conexion = DriverManager.getConnection(url, usuario, passwd);
            System.out.println("Conexión correcta");
        } catch (SQLException e) {
            System.out.println("Error en la conexión con MySQL.");
            System.out.println("Revisa que todo esté bien escrito.");
            System.out.println(e.getLocalizedMessage());
        }
    }
}
