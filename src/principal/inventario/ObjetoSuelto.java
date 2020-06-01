package principal.inventario;

import java.awt.Point;

public class ObjetoSuelto {

    private Point posicion;
    private Objeto objeto;

    public ObjetoSuelto(Point posicion, Objeto objeto) {
        this.posicion = posicion;
        this.objeto = objeto;
    }

    public Point getPosicion() {
        return posicion;
    }

    public Objeto getObjeto() {
        return objeto;
    }

}
