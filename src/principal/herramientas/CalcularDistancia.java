package principal.herramientas;

import java.awt.Point;

public class CalcularDistancia {

    public static double getDistanciaEntrePuntos(final Point punto1, final Point punto2) {
        return Math.sqrt(Math.pow((punto2.getX() - punto1.getX()), 2) + Math.pow((punto2.getY() - punto1.getY()), 2));
    }
}
