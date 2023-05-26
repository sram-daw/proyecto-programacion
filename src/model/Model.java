package model;

import controller.Controller;
import model.dao.*;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Observable;

public class Model extends Observable {

    public static Connection conexion; //se establece como atributo para poder usarlo en distintos métodos


    public Model() {

    }

    public void establecerConexionBd() {
        conexion = null;
        String url = "jdbc:mysql://localhost:3306/lurpiazon";
        String usuario = "root";
        String passwd = "root";
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
    public static boolean registrarCliente(Cliente nuevoCliente) {
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

    //Método para obtener el id del cliente en la bd y poder asignárselo al objeto Cliente clienteLogado (el id se genera de forma autoincremental en la bd)
    public static int getIdCliente(Cliente clienteLogado) {
        int idCliente = 0;
        Statement consulta = null;
        String consultaId = "SELECT id_usuario FROM usuarios WHERE nombre_usuario=" +
                "'" + clienteLogado.getNombreUsuario() + "'";
        try {
            consulta = conexion.createStatement();
            ResultSet resultadoInicioSes = consulta.executeQuery(consultaId);
            if (resultadoInicioSes.next()) {
                idCliente = resultadoInicioSes.getInt("id_usuario");
            }
            consulta.close();
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }

        return idCliente;
    }

    //método para comprobar que existe el suficiente stock de un producto para añadirlo a la cesta
    public static int comprobarStock(int idProducto) {
        int stock = 0;
        Statement consulta = null;
        String consultaStock = "SELECT stock FROM productos_almacen WHERE id_producto=" +
                "'" + idProducto + "'";
        try {
            consulta = conexion.createStatement();
            ResultSet resultadoStock = consulta.executeQuery(consultaStock);
            if (resultadoStock.next()) {
                stock = resultadoStock.getInt("stock");
            }
            consulta.close();
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }

        return stock;
    }

    //Método para añadir el nuevo pedido a la tabla pedidos de la bd
    public static boolean almacenarPedidosBd(Pedido pedido) {
        Boolean isAddOk = false;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fechaSql = dateFormat.format(pedido.getFecha());

        Statement consulta = null;
        String insertIntoPedidos = "INSERT INTO pedidos (id_usuario, fecha, total_precio) " +
                "VALUES (" +
                "'" + pedido.getCliente().getIdUsuario() + "', " +
                "'" + fechaSql + "', " +
                "'" + pedido.getPrecio() + "')";

        try {
            consulta = conexion.createStatement();
            consulta.executeUpdate(insertIntoPedidos);
            isAddOk = true;

            consulta.close();
        } catch (SQLException e) {
            System.out.println("Error al procesar el pedido");
            System.out.println(e.getLocalizedMessage());
            isAddOk = false;
        }
        return isAddOk;
    }

    //Método para añadir los productos del pedido a la tabla detalles_pedidos de la bd
    public static void almacenarDetallesPedidosBd(DetallesProducto producto, int id_pedido) { // (DetallesProducto producto)

        Statement consulta = null;
        String insertIntoPedidos = "INSERT INTO detalles_pedidos (id_pedido, id_producto, cantidad) " +
                "VALUES (" +
                "'" + id_pedido + "', " +
                "'" + producto.getIdProducto() + "', " +
                "'" + producto.getCantidad() + "')";
        try {
            consulta = conexion.createStatement();
            consulta.executeUpdate(insertIntoPedidos);
            consulta.close();

        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    //Método para tomar el id del pedido generado autoincrementalmente en la bd para settearlo en el objeto pedido
    public static int getIdPedido(Pedido pedido) {
        int idPedido = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fechaSql = dateFormat.format(pedido.getFecha());
        Statement consulta = null;
        String consultaId = "SELECT id_pedido FROM pedidos WHERE fecha=" +
                "'" + fechaSql + "'";
        try {
            consulta = conexion.createStatement();
            ResultSet resultadoGetId = consulta.executeQuery(consultaId);
            if (resultadoGetId.next()) {
                idPedido = resultadoGetId.getInt("id_pedido");
            }
            consulta.close();
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }

        return idPedido;
    }

    //Método para restar del stock los productos tras efectuar un pedido
    public static boolean restarStock(Cesta pedido) {
        boolean isRestarOk = false;
        Statement consulta = null;
        String actualizarStock = "UPDATE productos_almacen SET stock = ? WHERE id_producto = ?";
        try {
            PreparedStatement pstmt = conexion.prepareStatement(actualizarStock); //este tipo de objeto se usa para consultas múltiples
            for (DetallesProducto producto : pedido.getCesta()) {
                int idProducto = producto.getIdProducto();
                int cantidad = producto.getCantidad();
                int stock = comprobarStock(idProducto);
                pstmt.setInt(1, stock - cantidad);
                pstmt.setInt(2, idProducto);
                pstmt.executeUpdate();
            }
            isRestarOk = true;
            pstmt.close();
        } catch (SQLException e) {
            isRestarOk = false;
            System.out.println(e.getLocalizedMessage());
        }
        return isRestarOk;
    }

    //Método para comprobar si los datos introducidos por el usuario en el login existen en la base de datos
    public static boolean comprobarDatosLoginOk(String nombreUsuario, String pwd) {
        Statement consulta = null;
        boolean userExiste = false;
        String consultaUsrPwd = "SELECT * FROM usuarios WHERE nombre_usuario=" +
                "'" + nombreUsuario + "'" +
                "AND pwd=" +
                "'" + pwd + "'";
        try {
            consulta = conexion.createStatement();
            ResultSet resultadoInicioSes = consulta.executeQuery(consultaUsrPwd);
            if (resultadoInicioSes.next()) { //si hay resultados quiere decir que existe el usuario en la bd y que la contraseña coincide con la introducida
                userExiste = true;
            }
            consulta.close();
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return userExiste;
    }

    //Función para comprobar si los datos de inicio de sesión pertenecen a un admin o a un cliente
    public static boolean comprobarSiAdmin(String nombreUsuario, String pwd) {
        Statement consulta = null;
        boolean isAdmin = false;
        String consultaUsrPwd = "SELECT * FROM usuarios WHERE nombre_usuario=" +
                "'" + nombreUsuario + "'" +
                "AND pwd=" +
                "'" + pwd + "'";
        try {
            consulta = conexion.createStatement();
            ResultSet resultadoInicioSes = consulta.executeQuery(consultaUsrPwd);
            while (resultadoInicioSes.next()) { //importante usar el bucle con .next() ya que ResulSet está pensado para traer varios registros, aunque en este caso solo sea uno. Si no se pone da error ResultSet exception: before start of result set
                if (resultadoInicioSes.getInt("is_admin") == 1) {
                    isAdmin = true;
                } else {
                    isAdmin = false;
                }
            }
            consulta.close();
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return isAdmin;
    }

    //Método que devuelve un objeto administrador consultando los datos del login en la bd
    public static Administrador getAdministradorLogado(String nombreUsuario, String pwd) {
        Administrador admin = new Administrador();

        Statement consulta = null;
        String consultaUsrPwd = "SELECT * FROM usuarios WHERE nombre_usuario=" +
                "'" + nombreUsuario + "'" +
                "AND pwd=" +
                "'" + pwd + "'";
        try {
            consulta = conexion.createStatement();
            ResultSet resultadoInicioSes = consulta.executeQuery(consultaUsrPwd);
            while (resultadoInicioSes.next()) { //importante usar el bucle con .next() ya que ResulSet está pensado para traer varios registros, aunque en este caso solo sea uno. Si no se pone da error ResultSet exception: before start of result set
                //se establecen los atributos del nuevo administrador con los datos obtenidos de la bd
                admin.setNombreUsuario(resultadoInicioSes.getString("nombre_usuario"));
                admin.setPwd(resultadoInicioSes.getString("pwd"));
            }
            consulta.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return admin;
    }

    //Método que devuelve un objeto Cliente consultando los datos del login en la bd
    public static Cliente getClienteLogado(String nombreUsuario, String pwd) {
        Cliente cliente = new Cliente();

        Statement consulta = null;
        String consultaUsrPwd = "SELECT * FROM usuarios WHERE nombre_usuario=" +
                "'" + nombreUsuario + "'" +
                "AND pwd=" +
                "'" + pwd + "'";
        try {
            consulta = conexion.createStatement();
            ResultSet resultadoInicioSes = consulta.executeQuery(consultaUsrPwd);
            while (resultadoInicioSes.next()) {//importante usar el bucle con .next() ya que ResulSet está pensado para traer varios registros, aunque en este caso solo sea uno. Si no se pone da error ResultSet exception: before start of result set
                //se establecen los atributos del nuevo cliente con los datos obtenidos de la bd
                cliente.setNombreUsuario(resultadoInicioSes.getString("nombre_usuario"));
                cliente.setNombre(resultadoInicioSes.getString("nombre"));
                cliente.setApellido(resultadoInicioSes.getString("apellido"));
                cliente.setPwd(resultadoInicioSes.getString("pwd"));
                cliente.setDireccion(resultadoInicioSes.getString("direccion"));
                cliente.setNumTelf(resultadoInicioSes.getString("num_telf"));
                cliente.setEmail(resultadoInicioSes.getString("email"));
                cliente.setCp(resultadoInicioSes.getString("cp"));
            }
            consulta.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cliente;
    }

    //funcion para mostrar los datos de la tabla productos_almacen en paginaPrincipalAdmin
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

    //Función para realizar el filtrado por categorías para mostrar la tabla de productos para el cliente
    public static Catalogo getCategoria(int categoria) {
        Catalogo catalogoFiltrado = new Catalogo();
        ArrayList<ProductoEnStock> listaFiltrada = new ArrayList<>();

        for (ProductoEnStock p : Controller.catalogo.getCatalogo()) {
            if (p.getCategoriaID() == categoria) {
                listaFiltrada.add(p);
            }
        }
        catalogoFiltrado.setCatalogo(listaFiltrada);

        return catalogoFiltrado;
    }


    //Metodo para obtener los datos de los clientes en paginaPrincipalAdmin
    public static ListaClientes obtenerDatosCliente() throws SQLException {
        ListaClientes listaCliente = new ListaClientes();
        ArrayList<Cliente> lista = new ArrayList<>();
        try (Statement statement = conexion.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM usuarios where is_admin=0")) { //recoge solo los clientes
            while (resultSet.next()) {
                Cliente nuevoCliente = new Cliente();
                nuevoCliente.setIdUsuario(resultSet.getInt("id_usuario"));
                nuevoCliente.setNombreUsuario(resultSet.getString("nombre_usuario"));
                nuevoCliente.setNombre(resultSet.getString("nombre"));
                nuevoCliente.setApellido(resultSet.getString("apellido"));
                nuevoCliente.setPwd(resultSet.getString("pwd"));
                nuevoCliente.setDireccion(resultSet.getString("direccion"));
                nuevoCliente.setNumTelf(resultSet.getString("num_telf"));
                nuevoCliente.setEmail(resultSet.getString("email"));
                nuevoCliente.setCp(resultSet.getString("cp"));
                lista.add(nuevoCliente);
            }
            listaCliente.setListaClientes(lista);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al intentar recuperar los datos de los clientes.");
            System.out.println(e.getLocalizedMessage());
        }
        return listaCliente;
    }

    //Metodo para obtener los datos de los pedidos en paginaPrincipalAdmin
    public static HistorialPedidosTotal obtenerDatosPedidos() throws SQLException {
        HistorialPedidosTotal historialPedidosTotal = new HistorialPedidosTotal();
        ArrayList<Pedido> listaPedidos = new ArrayList<>();
        try (Statement statement = conexion.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM pedidos")) {
            while (resultSet.next()) {
                Pedido nuevoPedido = new Pedido();
                nuevoPedido.setIdPedido(resultSet.getInt("id_pedido"));
                nuevoPedido.setIdUsuario(resultSet.getInt("id_usuario"));
                nuevoPedido.setFecha(resultSet.getTimestamp("fecha"));
                nuevoPedido.setPrecio(resultSet.getFloat("total_precio"));
                listaPedidos.add(nuevoPedido);
            }
            historialPedidosTotal.setTotalPedidos(listaPedidos);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al intentar recuperar los datos de los pedidos.");
            System.out.println(e.getLocalizedMessage());
        }
        return historialPedidosTotal;
    }


    //Metodo para actualizar el stock en paginaPrincipalAdmin
    public static Boolean actualizarStockProducto(int idProducto, int nuevoStock) throws SQLException {
        boolean isActualizadoOK = false;
        //ProductoEnStock stockActualizado = new ProductoEnStock();
        Statement consulta = null;
        int stock = comprobarStock(idProducto);
        String updateStock = "UPDATE productos_almacen SET stock = " +
                "'" + (stock + nuevoStock) + "' WHERE id_producto = " +
                "'" + idProducto + "'";

        try {
            consulta = conexion.createStatement();
            consulta.executeUpdate(updateStock);
            consulta.close();
            isActualizadoOK = true;

        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
            isActualizadoOK = false;
        }
        return isActualizadoOK;
    }

    //metodo para ordenar la tabla almacen por stock ascendente
    public static ResultSet ordenarStock()throws SQLException {
        Statement consulta = null;
        String consultaStockOrdenado = "SELECT * FROM productos_almacen ORDER BY stock ASC";
        ResultSet resultadoStock = null;
        try {
            consulta = conexion.createStatement();
            resultadoStock = consulta.executeQuery(consultaStockOrdenado);
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return resultadoStock;

    }

    //Metodo para usar el patron observer, envia mensaje cuando el stock es igual o inferior a 10
    public boolean limiteStock() throws SQLException {
        boolean stockIsOk=false;
        int datoStock = 0;
        Statement consulta = null;
        String consultaStockInferior = "SELECT stock FROM productos_almacen WHERE stock <= 20 ";
        try {
            consulta = conexion.createStatement();
            ResultSet resultadoStock = consulta.executeQuery(consultaStockInferior);
            if (resultadoStock.next()) {
                datoStock = resultadoStock.getInt("stock");
                if (datoStock <= 20) {
                    String mensaje = "El stock de uno de los productos está a punto de agotarse";
                    String[] opciones = {"Cerrar"};
                    int seleccion = JOptionPane.showOptionDialog(null, mensaje, "Aviso de stock bajo",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                            null, opciones, opciones[0]);
                }
            }
            consulta.close();
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        setChanged();
        notifyObservers(datoStock);
        return stockIsOk;
    }
}
