package principal;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import principal.herramientas.CargadorRecursos;
import principal.sonido.Sonido;

public class Constantes {
    
    public static float VOLUMEN = (float) 0.03;

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
    public static String RUTA_PERSONAJE = "/imagenes/hojas_Personajes/Santana.png";
    public static String RUTA_PERSONAJEPODER = "/imagenes/hojas_Personajes/SantanaPoder.png";
    public static String RUTA_PERSONAJEMEGAPODER = "/imagenes/hojas_Personajes/SantanaMegaPoder.png";
    public static String RUTA_PERSONAJEDISPARO = "/imagenes/hojas_Personajes/SantanaDisparo.png";
    public static String RUTA_ZOMBIE = "/imagenes/hojas_de_enemigos/";
    public static String RUTA_ICONO_VENTANA = "/imagenes/iconos/iconV.png";
    public static String RUTA_LOGOTIPO = "/imagenes/iconos/logo.png";
    public static String RUTA_OBJETOS = "/imagenes/hojas_de_objetos/1.png";
    public static String RUTA_ARMAS = "/imagenes/hojas_de_objetos/arma.png";
    public static Font FUENTE_PIXEL = CargadorRecursos.cargarFuente("/fuentes/Crumbled-Pixels.ttf");

    public static Color COLOR_VERDE_CLARO = new Color(0x2ff40a);
    public static Color COLOR_VERDE_OSCURO = new Color(0x2d572c);

    public static final long SPAWNING_TIME = 200;
    public static final long FLICKER_TIME = 20;

    public static final Sonido BOTON = new Sonido("Resourses/sonidos/boton.wav");
    public static final Sonido CANCION1 = new Sonido("Resourses/sonidos/MusicaIntro.wav");
//    public static final Sonido CANCION2 = new Sonido("Resourses/sonidos/MusicaIntro2.wav");
//    public static final Sonido CANCION3 = new Sonido("Resourses/sonidos/MusicaIntro3.wav");

    public static final String BOTONPAUSA = "/imagenes/menu/pausa.png";
    public static final String BOTONPAUSACONMOUSE = "/imagenes/menu/pausaConMouse.png";
    public static final String BOTONSALIR = "/imagenes/menu/salir.png";
    public static final String BOTONSALIRCONMOUSE = "/imagenes/menu/salirConMouse.png";
    
    public static final BufferedImage MUJER = CargadorRecursos.cargarImagenCompatibleTranslucida("/imagenes/Mujer.png");
    
    public final static BufferedImage IMAGENFONDO = CargadorRecursos.cargarImagenCompatibleOpaca("/imagenes/menu/menuInicial.png");
    public final static BufferedImage IMAGENFONDOPAUSA = CargadorRecursos.cargarImagenCompatibleOpaca("/imagenes/menu/fondoPausa.png");
    public final static BufferedImage FONDO = CargadorRecursos.cargarImagenCompatibleOpaca("/imagenes/menu/fondo.png");
    public final static BufferedImage INICIOPARTIDA = CargadorRecursos.cargarImagenCompatibleTranslucida("/imagenes/menu/inicioPartida.png");
    public final static BufferedImage INICIOPARTIDACONMOUSE = CargadorRecursos.cargarImagenCompatibleTranslucida("/imagenes/menu/inicioPartidaConMouse.png");
    public final static BufferedImage CARGARPARTIDA = CargadorRecursos.cargarImagenCompatibleTranslucida("/imagenes/menu/cargarPartida.png");
    public final static BufferedImage CARGARPARTIDACONMOUSE = CargadorRecursos.cargarImagenCompatibleTranslucida("/imagenes/menu/cargarPartidaConMouse.png");
    public final static BufferedImage CARGARSONPARTIDA = CargadorRecursos.cargarImagenCompatibleTranslucida("/imagenes/menu/cargarSinPartida.png");
    public final static BufferedImage CONFIGURACION = CargadorRecursos.cargarImagenCompatibleTranslucida("/imagenes/menu/configuracion.png");
    public final static BufferedImage CONFIGURACIONCONMOUSE = CargadorRecursos.cargarImagenCompatibleTranslucida("/imagenes/menu/configuracionConMouse.png");
    public final static BufferedImage SALIRMENU = CargadorRecursos.cargarImagenCompatibleTranslucida("/imagenes/menu/salirMenu.png");
    public final static BufferedImage SALIRMENUCONMOUSE = CargadorRecursos.cargarImagenCompatibleTranslucida("/imagenes/menu/salirMenuConMouse.png");
    public final static BufferedImage QUIERESALIR = CargadorRecursos.cargarImagenCompatibleTranslucida("/imagenes/menu/quiereSalir.png");
    public final static BufferedImage QUIERESALIRSI = CargadorRecursos.cargarImagenCompatibleTranslucida("/imagenes/menu/si.png");
    public final static BufferedImage QUIERESALIRNO = CargadorRecursos.cargarImagenCompatibleTranslucida("/imagenes/menu/no.png");
    public final static BufferedImage QUIERESALIRSICONMOUSE = CargadorRecursos.cargarImagenCompatibleTranslucida("/imagenes/menu/siConMouse.png");
    public final static BufferedImage QUIERESALIRNOCONMOUSE = CargadorRecursos.cargarImagenCompatibleTranslucida("/imagenes/menu/noConMouse.png");
    public final static BufferedImage GAMEOVER = CargadorRecursos.cargarImagenCompatibleOpaca("/imagenes/iconos/GameOver.png");
    public final static BufferedImage VOLVERALJUEGO = CargadorRecursos.cargarImagenCompatibleTranslucida("/imagenes/menu/volverAlJuego.png");
    public final static BufferedImage VOLVERALJUEGOCONMOUSE = CargadorRecursos.cargarImagenCompatibleTranslucida("/imagenes/menu/volverAlJuegoConMouse.png");
    public final static BufferedImage IDIOMA = CargadorRecursos.cargarImagenCompatibleTranslucida("/imagenes/menu/idioma.png");
    public final static BufferedImage SONIDO = CargadorRecursos.cargarImagenCompatibleTranslucida("/imagenes/menu/sonido.png");
    public final static BufferedImage SONIDOCONMOUSE = CargadorRecursos.cargarImagenCompatibleTranslucida("/imagenes/menu/sonidoConMouse.png");
    public final static BufferedImage DIFICULTAD = CargadorRecursos.cargarImagenCompatibleTranslucida("/imagenes/menu/dificultad.png");
    public final static BufferedImage DIFICULTADCONMOUSE = CargadorRecursos.cargarImagenCompatibleTranslucida("/imagenes/menu/dificultadConMouse.png");
    public final static BufferedImage CREDITOS = CargadorRecursos.cargarImagenCompatibleTranslucida("/imagenes/menu/creditos.png");
    public final static BufferedImage CREDITOSCONMOUSE = CargadorRecursos.cargarImagenCompatibleTranslucida("/imagenes/menu/creditosConMouse.png");
    public final static BufferedImage MUSICA = CargadorRecursos.cargarImagenCompatibleTranslucida("/imagenes/menu/musica.png");
    public final static BufferedImage MUSICACONMOUSE = CargadorRecursos.cargarImagenCompatibleTranslucida("/imagenes/menu/musicaConMouse.png");
    public final static BufferedImage VOLVER = CargadorRecursos.cargarImagenCompatibleTranslucida("/imagenes/iconos/volver.png");
    public final static BufferedImage VOLVERCONMOUSE = CargadorRecursos.cargarImagenCompatibleTranslucida("/imagenes/iconos/volverConMouse.png");
    public final static BufferedImage LOGRO = CargadorRecursos.cargarImagenCompatibleTranslucida("/imagenes/menu/logro.png");
    public final static BufferedImage LOGROCONMOUSE = CargadorRecursos.cargarImagenCompatibleTranslucida("/imagenes/menu/logroConMouse.png");
    public final static BufferedImage MENUSONIDO = CargadorRecursos.cargarImagenCompatibleTranslucida("/imagenes/menu/configurarVolumen.png");
    public final static BufferedImage FlECHAABAJO = CargadorRecursos.cargarImagenCompatibleTranslucida("/imagenes/iconos/flechaAbajo.png");
    public final static BufferedImage FlECHAABAJOCONMOUSE = CargadorRecursos.cargarImagenCompatibleTranslucida("/imagenes/iconos/flechaAbajoConMouse.png");
    public final static BufferedImage FlECHAARRIBA = CargadorRecursos.cargarImagenCompatibleTranslucida("/imagenes/iconos/flechaArriba.png");
    public final static BufferedImage FlECHAARRIBACONMOUSE = CargadorRecursos.cargarImagenCompatibleTranslucida("/imagenes/iconos/flechaArribaConMouse.png");
    public final static BufferedImage VOLVERGRANDE = CargadorRecursos.cargarImagenCompatibleTranslucida("/imagenes/menu/volver.png");
    public final static BufferedImage VOLVERCONMOUSEGRANDE = CargadorRecursos.cargarImagenCompatibleTranslucida("/imagenes/menu/volverConMouse.png");
}
