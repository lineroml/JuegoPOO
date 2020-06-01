package principal;

import java.awt.Color;
import java.awt.Font;
import principal.herramientas.CargadorRecursos;

public class Constantes {

    public static final int LADO_SPRITE = 32;
    public static final int LADO_TILE = 32;
    public static int LADO_CURSOR = 0;

    public static int ANCHO_PANTALLA_COMPLETA = 1280;
    public static int ALTO_PANTALLA_COMPLETA = 720;
//    public static int ANCHO_PANTALLA_COMPLETA = 1366;
//    public static int ALTO_PANTALLA_COMPLETA = 768;

    public static int ANCHO_JUEGO = ANCHO_PANTALLA_COMPLETA / 2;
    public static int ALTO_JUEGO = ALTO_PANTALLA_COMPLETA / 2;

    public static double FACTOR_ESCALADO_X = ANCHO_PANTALLA_COMPLETA / ANCHO_JUEGO;
    public static double FACTOR_ESCALADO_Y = ALTO_PANTALLA_COMPLETA / ALTO_JUEGO;

    public static int CENTRO_VENTANA_X = ANCHO_JUEGO / 2;
    public static int CENTRO_VENTANA_Y = ALTO_JUEGO / 2;

    //Centro del sprite del jugador
    public static int MARGEN_X = ANCHO_JUEGO / 2 - LADO_SPRITE;
    public static int MARGEN_Y = ALTO_JUEGO / 2 - LADO_SPRITE;

    public static String RUTA_MAPA = "/mapas/mapa1";
    public static String RUTA_ICONO_RATON = "/imagenes/iconos/iconoCursor.png";
    public static String RUTA_PERSONAJE = "/imagenes/hojas_Personajes/1.png";
    public static String RUTA_PERSONAJEARMADO = "/imagenes/hojas_Personajes/2.png";
    public static String RUTA_ZOMBIE = "/imagenes/hojas_de_enemigos/";
    public static String RUTA_ICONO_VENTANA = "/imagenes/iconos/iconV.png";
    public static String RUTA_LOGOTIPO = "/imagenes/iconos/logo.png";
    public static String RUTA_OBJETOS = "/imagenes/hojas_de_objetos/1.png";
    public static String RUTA_ARMAS = "/imagenes/hojas_de_objetos/armas.png";
    public static Font FUENTE_PIXEL = CargadorRecursos.cargarFuente("/fuentes/Crumbled-Pixels.ttf");

    public static Color COLOR_VERDE_CLARO = new Color(0x2ff40a);
    public static Color COLOR_VERDE_OSCURO = new Color(0x2d572c);

    public static final long SPAWNING_TIME = 300;
    public static final long FLICKER_TIME = 20;
}
