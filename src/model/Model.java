package model;

import model.dao.Catalogo;
import model.dao.Cliente;
import model.dao.Producto;
import model.dao.ProductoEnStock;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Model {

    public static Connection conexion; //se establece como atributo para poder usarlo en distintos métodos

    public Model() {

    }

    public void establecerConexionBd() {
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

    //Función para añadir el cliente que se acaba de registrar a la bd
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
            consulta.close();
        } catch (SQLException e) {
            System.out.println("Error al añadir el usuario");
            System.out.println(e.getLocalizedMessage());
            isAddOk = false;
        }
        return isAddOk;
    }

    //Función para comprobar si los datos de inicio de sesión son correctos

    //modificar este metodo para que devuelva un objeto cliente
    public static HashMap<String, Boolean> comprobarInicioSesOk(String nombreUsuario, String pwd) {
        HashMap<String, Boolean> resultadoDevuelto = new HashMap<>();
        Boolean isInicioSesionOk = false;
        Boolean isAdmin = false;
        Statement consulta = null;
        String texto_consulta = "SELECT * FROM usuarios WHERE nombre_usuario=" +
                "'" + nombreUsuario + "'" +
                "AND pwd=" +
                "'" + pwd + "'";
        String texto_comprobacion_admin = "SELECT * FROM usuarios WHERE nombre_usuario=" +
                "'" + nombreUsuario + "'" +
                "AND is_admin=" +
                "1";
        try {
            consulta = conexion.createStatement();
            ResultSet resultado = consulta.executeQuery(texto_consulta);
            if (resultado.next()) { //si existen resultados para la consulta, significa que existe un el usuario y su contraseña es correcta
                ResultSet resultadoAdmin = consulta.executeQuery(texto_comprobacion_admin);
                if (resultadoAdmin.next()) {//comprueba si la consulta tiene resultados (si es admin)
                    System.out.println("Sesión iniciada correctamente como admin.");
                    isAdmin = true;
                } else {
                    System.out.println("Sesión iniciada correctamente como cliente.");
                    isAdmin = false;
                }
                isInicioSesionOk = true;
            } else {
                JOptionPane.showMessageDialog(null, "Datos incorrectos. Vuelte a intentarlo.");
                isInicioSesionOk = false;
            }
            resultado.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al intentar iniciar sesión. Vuelte a intentarlo.");
            System.out.println(e.getLocalizedMessage());
            isInicioSesionOk = false;
        }
        resultadoDevuelto.put("isInicioSesionOk", isInicioSesionOk);
        resultadoDevuelto.put("isAdmin", isAdmin);
        return resultadoDevuelto;
    }

    //funcion para mostrar los datos de la tabla productos_almacen
    public static Catalogo obtenerDatosAlmacen() throws SQLException {
        Catalogo catalogo = new Catalogo();
        ArrayList<ProductoEnStock> listaProductos = new ArrayList<>();

        try (Statement statement = conexion.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM productos_almacen")) {
            while (resultSet.next()) {
                ProductoEnStock nuevoProducto = new ProductoEnStock();
                nuevoProducto.setIdProducto(resultSet.getInt("id_producto"));
                nuevoProducto.setNombre(resultSet.getString("nombre_producto"));
                nuevoProducto.setPrecio(resultSet.getFloat("precio"));
                nuevoProducto.setCategoriaID(resultSet.getInt("id_categoria"));
                nuevoProducto.setCategoriaNombre(resultSet.getString("nombre_categoria"));
                nuevoProducto.setStock(resultSet.getInt("stock"));
                listaProductos.add(nuevoProducto);
            }
            catalogo.setCatalogo(listaProductos);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al intentar recuperar los productos del almacén.");
            System.out.println(e.getLocalizedMessage());
        }
        return catalogo;
    }


}
