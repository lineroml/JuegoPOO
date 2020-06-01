package principal.herramientas;

//Escalara los tamaños de puntos y rectangulos
import java.awt.Point;
import java.awt.Rectangle;
import principal.Constantes;

public class EscaladorElementos {

    //Hacer el rectangulo mas grande
    public static Rectangle escalarRectanguloArriba(Rectangle r) {
        final Rectangle newRectangle = new Rectangle((int) (r.x * Constantes.FACTOR_ESCALADO_X), (int) (r.y * Constantes.FACTOR_ESCALADO_Y), (int) (r.width * Constantes.FACTOR_ESCALADO_X), (int) (r.height * Constantes.FACTOR_ESCALADO_Y));

        return newRectangle;
    }

    //Escalar las coordenadas de puntos
    public static Point escalarPuntoArriba(final Point p) {
        final Point newPoint = new Point((int) (p.x * Constantes.FACTOR_ESCALADO_X), (int) (p.y * Constantes.FACTOR_ESCALADO_Y));

        return newPoint;
    }

    //Escalar las coordenadas de puntos
    public static Point escalarPuntoAbajo(final Point p) {
        final Point newPoint = new Point((int) (p.x * Math.pow(Constantes.FACTOR_ESCALADO_X, -1)), (int) (p.y * Math.pow(Constantes.FACTOR_ESCALADO_Y, -1)));

        return newPoint;
    }
}
