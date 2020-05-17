package principal.maquinaestado.estado.menujuego;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import principal.Constantes;
import principal.herramientas.DibujoOpciones;

public class FormaMenu {

    public final Color COLOR_SUPERIOR;
    public final Color COLOR_LATERAL;
    public final Color COLOR_CENTRAL;

    public final Rectangle SUPERIOR;
    public final Rectangle LATERAL;
    public final Rectangle CENTRAL;

    public FormaMenu() {

        COLOR_SUPERIOR = Color.ORANGE;
        COLOR_LATERAL = Color.DARK_GRAY;
        COLOR_CENTRAL = Color.LIGHT_GRAY;

        SUPERIOR = new Rectangle(0, 0, Constantes.ANCHO_JUEGO, 20);
        LATERAL = new Rectangle(0, SUPERIOR.height, 140, Constantes.ALTO_JUEGO - SUPERIOR.height);
        CENTRAL = new Rectangle(LATERAL.width, SUPERIOR.height, Constantes.ANCHO_JUEGO - LATERAL.width, Constantes.ALTO_JUEGO - SUPERIOR.height);
    }

    public void actualizar() {

    }

    public void dibujar(final Graphics g) {

        DibujoOpciones.dibujarRectRelleno(g, SUPERIOR, COLOR_SUPERIOR);
        DibujoOpciones.dibujarRectRelleno(g, LATERAL, COLOR_LATERAL);
        DibujoOpciones.dibujarRectRelleno(g, CENTRAL, COLOR_CENTRAL);
    }
}
