package principal.interface_usuario;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import principal.Constantes;
import principal.entes.Jugador;
import principal.herramientas.DibujoOpciones;

public class MenuInferior {

    private final Rectangle areaInventario;
    private final Rectangle bordeSeparacion;
    private final Color grisOscuro;
    private final Color rojoClaro;
    private final Color rojoOscuro;
    private final Color azulClaro;
    private final Color azulOscuro;

    public MenuInferior(final Jugador jugador) {

        int altoMenu = 55;
        grisOscuro = new Color(24, 23, 28);
        rojoClaro = new Color(255, 0, 0);
        rojoOscuro = new Color(150, 0, 0);
        azulClaro = new Color(105, 255, 255);
        azulOscuro = new Color(93, 193, 185);
        areaInventario = new Rectangle(0, Constantes.ALTO_JUEGO - altoMenu, Constantes.ANCHO_JUEGO, altoMenu);
        bordeSeparacion = new Rectangle(areaInventario.x, areaInventario.y - 1, areaInventario.width, 1);
    }

    public void dibujar(final Graphics g, final Jugador jugador) {

        dibujarAreaInventario(g);
        dibujarBarraVida(g);
        dibujarBarraResistencia(g, jugador.resistencia);
        dibujarRanurasObjetos(g);
    }

    private void dibujarAreaInventario(final Graphics g) {

        DibujoOpciones.dibujarRectRelleno(g, areaInventario, grisOscuro);
        DibujoOpciones.dibujarRectRelleno(g, bordeSeparacion, Color.WHITE);
    }

    private void dibujarBarraVida(final Graphics g) {

        final int medidaVertical = 5;
        final int anchoTotal = 100;

        DibujoOpciones.dibujarRectRelleno(g, areaInventario.x + 35, areaInventario.y + medidaVertical, anchoTotal, medidaVertical, rojoClaro);
        DibujoOpciones.dibujarRectRelleno(g, areaInventario.x + 35, areaInventario.y + medidaVertical * 2, anchoTotal, medidaVertical, rojoOscuro);

        g.setColor(Color.WHITE);
        DibujoOpciones.dibujarString(g, "VIDA", areaInventario.x + 12, areaInventario.y + medidaVertical * 3 - 2);
        DibujoOpciones.dibujarString(g, "1000", areaInventario.x + anchoTotal + 45, areaInventario.y + medidaVertical * 3 - 2);
    }

    private void dibujarBarraResistencia(final Graphics g, final int resistencia) {

        final int medidaVertical = 5;
        final int anchoTotal = 100;
        final int ancho = 100 * resistencia / 300;

        DibujoOpciones.dibujarRectRelleno(g, areaInventario.x + 35, areaInventario.y + medidaVertical * 4, ancho, medidaVertical, azulClaro);
        DibujoOpciones.dibujarRectRelleno(g, areaInventario.x + 35, areaInventario.y + medidaVertical * 5, ancho, medidaVertical, azulOscuro);

        g.setColor(Color.WHITE);
        DibujoOpciones.dibujarString(g, "RES", areaInventario.x + 15, areaInventario.y + medidaVertical * 6 - 2);
        DibujoOpciones.dibujarString(g, resistencia + "", areaInventario.x + anchoTotal + 45, areaInventario.y + medidaVertical * 6 - 2);
    }

    private void dibujarRanurasObjetos(final Graphics g) {

        final int anchoRanura = 32;
        final int numeroRanuras = 10;
        final int espacioEntreRanuras = 10;
        final int anchoTotal = anchoRanura * numeroRanuras + espacioEntreRanuras * numeroRanuras;
        final int xInicial = Constantes.ANCHO_JUEGO - anchoTotal;
        final int anchoRanurasYespacio = anchoRanura + espacioEntreRanuras;

        g.setColor(Color.WHITE);

        for (int i = 0; i < numeroRanuras; i++) {

            int xActual = xInicial + anchoRanurasYespacio * i;
            Rectangle ranura = new Rectangle(xActual, areaInventario.y + 4, anchoRanura, anchoRanura);
            DibujoOpciones.dibujarRectRelleno(g, ranura);
            DibujoOpciones.dibujarString(g, (i + 1) + "", xActual + 13, areaInventario.y + 48);
        }
    }
}
