package principal;

import principal.graficos.SuperficieDibujo;
import principal.graficos.Ventana;
import principal.maquinaestado.GestorEstado;
import principal.sonido.GestorSonido;

public class GestorPrincipal {

    //Indicara si el bucle principal esta funcionando
    private static boolean enFuncionamiento = false;
    //Titulo de nuestro juego
    private final String titulo;
    //Dimensiones de la ventana
    private final int ancho;
    private final int alto;
    public static SuperficieDibujo sd;

    /**
     * JFrame sobre el cual va a habitar el programa
     */
    private Ventana ventana;
    public static GestorEstado ge;

    private static int aps = 0;
    private static int fps = 0;

    private static GestorSonido sonidoIntro = Constantes.CANCION1;

    private GestorPrincipal(final String titulo, final int ancho, final int alto) {

        this.titulo = titulo;
        this.ancho = ancho;
        this.alto = alto;
    }

    public static void main(String[] args) {
        sonidoIntro.reproducirBucle();
        //Usar la tarjeta grafica en vez de el procesador para dibujar
        //Para OpenGl mac/linux
        // System.setProperty("sun.java2d.opengl", "True");

        //Para Windows
//        System.setProperty("sun.java2d.d3d", "True");
//        System.setProperty("sun.java2d.ddforcevram", "True");
        //Mejora el rendimineto con graficos transparentes
//        //System.setProperty("sun.java2d.transaccel", "True");
        //Creamos un gestor principal
        GestorPrincipal gp = new GestorPrincipal("Forgotten History", Constantes.ANCHO_PANTALLA_COMPLETA, Constantes.ALTO_PANTALLA_COMPLETA);

        gp.iniciarJuego();
        gp.iniciarBuclePrincipal();
    }

    private void iniciarJuego() {

        enFuncionamiento = true;
        inicializar();
    }

    private void inicializar() {

        sd = new SuperficieDibujo(ancho, alto);
        ventana = new Ventana(titulo, sd);
        ge = new GestorEstado(sd);
    }

    private void iniciarBuclePrincipal() {

        int actualizacionesAcumuladas = 0, framesAcumulados = 0;

        //Equivalencia nanosegundos en un segundo
        final int NS_POR_SEGUNDO = 1000000000;
        //Cuantas actualizaciones queremos hacer por segundo
        final byte APS_OBJETO = 60;
        //Cuantos nanosegundos transcurren por actualizacion
        final double NS_POR_ACTUALIZACION = NS_POR_SEGUNDO / APS_OBJETO;

        //Atribuir una cantidad de nanosegundos en tiempo exacto, de lo que ha pasado en el tiempo de inicio de juego
        long referenciaActualizacion = System.nanoTime();
        long referenciaContador = System.nanoTime();

        double tiempoTranscurrido;
        //Cantidad de tiempo que a ocurrido hasta una actualizacion
        double delta = 0;

        while (enFuncionamiento) {

            final long inicioBucle = System.nanoTime();

            //Cuanto tiempo a transcurrido
            tiempoTranscurrido = inicioBucle - referenciaActualizacion;
            referenciaActualizacion = inicioBucle;

            delta += tiempoTranscurrido / NS_POR_ACTUALIZACION;

            while (delta >= 1) {

                //Actualizar se ejecutara 60 veces por segundo
                actualizar();
                actualizacionesAcumuladas++;
                delta--;
            }

            dibujar();
            framesAcumulados++;

            if (System.nanoTime() - referenciaContador > NS_POR_SEGUNDO) {

                aps = actualizacionesAcumuladas;
                fps = framesAcumulados;
                //Escribir en pantalla los APS y FPS
                actualizacionesAcumuladas = 0;
                framesAcumulados = 0;
                referenciaContador = System.nanoTime();
            }
        }
    }

    private void actualizar() {

        ge.actualizar();
        sd.actualizar();
    }

    private void dibujar() {

        sd.dibujar(ge);
    }

    public static int getAps() {
        return aps;
    }

    public static int getFps() {
        return fps;
    }

    public static void setCancion(GestorSonido cancion) {
        sonidoIntro.detenerSonido();
        sonidoIntro = cancion;
        sonidoIntro.reproducirBucle();
    }

    public static void detenerCancion() {
        sonidoIntro.detenerSonido();
    }

    public static void reproducirCancion() {
        sonidoIntro.reproducir();
    }


    public static void setVolumen(float vol) {
        sonidoIntro.aumentarVolumenMusica(sonidoIntro.getVolumenMusica() + vol);
        Constantes.BOTON.aumentarVolumenJuego(sonidoIntro.getVolumenJuego() + vol);
    }


    public static void setVolumenMusica(float vol) {
        sonidoIntro.aumentarVolumenMusica(sonidoIntro.getVolumenMusica() + vol);
    }
    
    public static void setVolumenJuego(float vol) {
        Constantes.BOTON.aumentarVolumenJuego(sonidoIntro.getVolumenJuego() + vol);
    }

    public static boolean isEnFuncionamiento() {
        return enFuncionamiento;
    }
}



