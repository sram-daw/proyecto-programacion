package model.dao;

import view.UI.PaginaPrincipalAdmin;

import java.sql.SQLException;
import java.util.Observable;
import java.util.Observer;

public class ObserverStock implements Observer {
    private boolean mensajeMostrado = false;
    /**
     * This method is called whenever the observed object is changed. An
     * application calls an {@code Observable} object's
     * {@code notifyObservers} method to have all the object's
     * observers notified of the change.
     *
     * @param o   the observable object.
     * @param arg an argument passed to the {@code notifyObservers}
     *            method.
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
