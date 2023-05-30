package controller;

import view.UI.PaginaPrincipalAdmin;

import java.sql.SQLException;
import java.util.Observable;
import java.util.Observer;

/**
 * Clase Observer stock.
 */
public class ObserverStock implements Observer {
    private boolean mensajeMostrado = false;
    /**
     * Este metodo es llamado cuando el objeto observer se cambia.
     * La aplicacion llama al objeto {@code Observable}
     * el metodo {@code notifyObservers} tiene todos los objetos
     * el observer notifica el cambio.
     *
     * @param o   del observable object.
     * @param arg el argumento pasa al metodo {@code notifyObservers}.
     *
     */
    @Override
    public void update(Observable o, Object arg) {
        int datoStock = (int) arg;
        try {
            if (arg instanceof Boolean) {
                boolean isStockBajo = (Boolean) arg;
                if (isStockBajo) {
                    PaginaPrincipalAdmin.mostrarMensajeStock();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
