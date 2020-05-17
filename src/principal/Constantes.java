package principal;

import java.awt.Font;
import principal.herramientas.CargadorRecursos;

public class Constantes {

    public static final int LADO_SPRITE = 32;
    public static final int LADO_TILE = 32;

    public static final int ANCHO_PANTALLA_COMPLETA = 1366;
    public static final int ALTO_PANTALLA_COMPLETA = 768;

    public static final int ANCHO_JUEGO = ANCHO_PANTALLA_COMPLETA / 2;
    public static final int ALTO_JUEGO = ALTO_PANTALLA_COMPLETA / 2;

    public static final double FACTOR_ESCALADO_X = ANCHO_PANTALLA_COMPLETA / ANCHO_JUEGO;
    public static final double FACTOR_ESCALADO_Y = ALTO_PANTALLA_COMPLETA / ALTO_JUEGO;

    public static final int CENTRO_VENTANA_X = ANCHO_JUEGO / 2;
    public static final int CENTRO_VENTANA_Y = ALTO_JUEGO / 2;

    public static final Font FUENTE_PIXEL = CargadorRecursos.cargarFuente("/fuentes/Crumbled-Pixels.ttf");
}
