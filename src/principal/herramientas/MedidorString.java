package principal.herramientas;

//Medir el alto y ancho de un String en pixeles
import java.awt.FontMetrics;
import java.awt.Graphics;

public class MedidorString {

    //Obtener el ancho de la fuente y la palabra
    public static int medirAnchoPixeles(final Graphics g, String palabra) {
        FontMetrics fm = g.getFontMetrics();
        return fm.stringWidth(palabra);
    }

    //Obtener el alto de la fuente y la palabra
    public static int medirAltoPixeles(final Graphics g, String palabra) {
        FontMetrics fm = g.getFontMetrics();
        return (int) (fm.getLineMetrics(palabra, g).getHeight());
    }
}
