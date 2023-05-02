import java.sql.*;

public class Model {

    public void establecerConexionBbdd() {
        Connection conexion = null;
        String url = "jdbc:mysql://localhost:3306/lurpiazon";
        String usuario = "root";
        String passwd = "root";
        try {
            conexion = DriverManager.getConnection(url,
                    usuario, passwd);
            System.out.println("Conexión correcta");
        } catch (SQLException e) {
            System.out.println("Error en la conexión con MySQL");
            System.out.println("Revisa que todo esté bien escrito y funcional");
            System.out.println(e.getLocalizedMessage());
        }

    }
}

