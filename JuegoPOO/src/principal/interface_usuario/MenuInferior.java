package principal.interface_usuario;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import principal.Constantes;
import principal.entes.Jugador;
import principal.herramientas.DibujoOpciones;

public class MenuInferior {

    private Rectangle areaInventario;
    private Rectangle bordeSeparacion;
    private Color grisOscuro;
    private Color rojoClaro;
    private Color rojoOscuro;
    private Color azulClaro;
    private Color azulOscuro;

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
}
