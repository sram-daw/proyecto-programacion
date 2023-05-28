package controller;

import model.Model;
import model.dao.*;
import model.dao.ObserverStock;
import view.View;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Controller {

    public static Cesta cesta = new Cesta(); //se instancian globalmente un objeto cesta para usarlo repetidas veces si se añaden varios productos

    //los objetos cliente y administrador se instancian globalmente para poder hacer uso de ellos más adelante cuando queramos comprobar sus datos en el historial de pedidos o en los datos personales
    public static Cliente clienteLogado = new Cliente(); //es público para poder usarlo en el método getIdCliente del modelo
    static Administrador administradorLogado = new Administrador();
    public static Catalogo catalogo = new Catalogo();

    //hay que instanciar de manera estática el model para poder hacer uso de él en el observer
    static Model model = new Model();
    //instanciamos el observer
    ObserverStock observerStock = new ObserverStock();


    //Método para registrar clientes
    static public boolean registrarse(String nombreUsuario, String pwd, String direccion, String tlf, String cp, String email, String nombre, String apellido) {
        Cliente nuevoCliente = new Cliente();
        nuevoCliente.setIsAdmin(0); //indicamos directamente que isadmin es false (0 porque es de tipo tinyint en la bd) porque los admins se dan de alta solo en la propia bd, no desde la app
        nuevoCliente.setNombreUsuario(nombreUsuario);
        nuevoCliente.setPwd(pwd);
        nuevoCliente.setDireccion(direccion);
        nuevoCliente.setNumTelf(tlf);
        nuevoCliente.setCp(cp);
        nuevoCliente.setEmail(email);
        nuevoCliente.setNombre(nombre);
        nuevoCliente.setApellido(apellido);
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
            /*clienteLogado.setIdUsuario(Model.getIdCliente(clienteLogado));*/
        }
        return isAdmin;
    }

    //Metodo para asignar a catalogo los productos según la query a la bd (se ejecuta nada más entrar a la pantalla principal de cliente para mostrar todos los productos)
    static public Catalogo agregarTablaAlmacen() throws SQLException {
        return catalogo = Model.obtenerDatosAlmacen();
    }

    //Metodo para obtener el catálogo según el filtrado
    public static Catalogo filtrarCat(int categoria) {
        return Model.getCategoria(categoria);
    }

    //Método para agregar el modelo a la tabla de la lista de clientes
    static public ListaClientes agregarTablaCliente() throws SQLException {
        return Model.obtenerListaClientes();
    }

    //método para agregar la tabla de pedidos a la ventana PaginaPedidosCliente
    public static HistorialPedidosTotal agregarTablaPedidos() {
        return Model.obtenerDatosPedidos();
    }


    //Método para agregar productos al arraylist de DetallesProducto que contiene Cesta
    public static Cesta addProductoToCesta(int id, String nombre, float precio, String categoria, int cantidad) {
        //se obtiene el precio actual de la cesta
        Float precioTotal = cesta.getPrecio();
        if (Model.comprobarStock(id) >= cantidad) { //se comprueba si existen suficientes existencias del producto en el almacen
            if (!Model.comprobarProductoEnCesta(id)) { //se comprueba si ese producto está ya en la cesta
                DetallesProducto nuevoProducto = new DetallesProducto();
                nuevoProducto.setIdProducto(id);
                nuevoProducto.setNombre(nombre);
                nuevoProducto.setPrecio(precio);
                nuevoProducto.setCategoriaNombre(categoria);
                nuevoProducto.setCantidad(cantidad);
                cesta.getCesta().add(nuevoProducto);
                //se suma el precio del producto añadido por la cantidad
                precioTotal += precio * cantidad;
                cesta.setPrecio(precioTotal);
                return cesta;
            } else {//si el producto ya está en la cesta, se actualiza la cantidad para que no añada dos productos iguales
                for (DetallesProducto p : cesta.getCesta()) {
                    if (p.getIdProducto() == id) {
                        p.setCantidad(p.getCantidad() + cantidad);
                        //se suma el precio de las unidades extra del producto añadidas a posteriori
                        precioTotal += precio * cantidad;
                        cesta.setPrecio(precioTotal);
                    }
                }
                return cesta;
            }
        } else {
            return null;
        }
    }

    //método para actualizar el stock cuando el admin añade nuevas unidades
    public static boolean actulizarStock(int idProducto, int nuevoStock) throws SQLException {
        return Model.actualizarStockProducto(idProducto, nuevoStock);
    }

    //Método que devuelve el stock de un producto, para usarlo en la vista
    public static int devolverStock(int idProducto) {
        int stock = Model.comprobarStock(idProducto);
        return stock;
    }

    //Método para finalizar la compra. Crea un objeto pedido y lo almacena en las tablas de pedidos y detalles_pedidos de la bd. Tb llama al método de restar stock
    public static boolean finalizarCompra() {
        boolean isFinalizarCompraOk = false;
        Pedido pedido = new Pedido();
        pedido.setPedido(cesta);
        pedido.setPrecio(cesta.getPrecio());
        pedido.setCliente(clienteLogado);
        pedido.setFecha(new Timestamp(System.currentTimeMillis()));
        isFinalizarCompraOk = Model.almacenarPedidosBd(pedido); //se almacena el pedido en la tabla pedidos
        isFinalizarCompraOk = Model.almacenarPedidosBd(pedido); //se almacena el pedido en la tabla pedidos
        pedido.setIdPedido(Model.getIdPedido(pedido)); //se settea el id del objeto pedido después de añadir el pedido a bd para que se genere el id en la bd de forma autoincremental, por lo que tiene que tomarlo a posteriori
        //Se añaden con un bucle todos los productos de la cesta de pedido a la tabla detalles_pedidos de la bd
        for (int i = 0; i < pedido.getPedido().getCesta().size(); i++) {
            Model.almacenarDetallesPedidosBd(pedido.getPedido().getCesta().get(i), pedido.getIdPedido());
        }
        restarStock();

        return isFinalizarCompraOk;
    }

    //Método que devuelve el resultado de la operación de restar del stock los productos comprados
    public static boolean restarStock() {
        boolean isRestarOk = false;
        isRestarOk = Model.restarStock(cesta);
        return isRestarOk;
    }

    //Metodo que devuelve la tabla ordenada por stock ascendente
    public static ResultSet ordenAscendente() throws SQLException {
        return Model.ordenarStock();
    }

    //metodo que recoge el aviso de stock bajo usando el patron observer
    public boolean avisoStock() throws SQLException {
        model.addObserver(observerStock);
        return model.avisarLimiteStock();
    }

    public static boolean datosClienteActualizados(Cliente cliente) throws SQLException {
        return Model.actualizarDatosCliente(cliente);
    }

    public static void actualizarObjetoCliente() {
        clienteLogado = Model.getDatosCliente(clienteLogado.getIdUsuario());
    }

    public static void main(String[] args) {
        Model model = new Model();
        //Instancia de view.View. El constructor contiene la llamada al método que crea la primera ventana
        View view = new View();
        //Conexión a la bbdd
        model.establecerConexionBd(); //se establece la conexión con la bd antes de nada
    }


}