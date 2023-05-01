import java.util.ArrayList;

public class Cesta {

    private ArrayList<DetallesProducto> cesta;
    private String idUsuario;

    public Cesta(ArrayList<DetallesProducto> cesta, String idUsuario) {
        this.cesta = cesta;
        this.idUsuario = idUsuario;
    }

    public Cesta() {
    }

    public boolean finalizarCompra (ArrayList<DetallesProducto> cesta){
        return true;
    }
}
