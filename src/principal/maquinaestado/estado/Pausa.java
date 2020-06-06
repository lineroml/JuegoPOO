package principal.maquinaestado.estado;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import principal.Constantes;
import principal.GestorPrincipal;
import principal.graficos.SuperficieDibujo;
import principal.herramientas.CargadorRecursos;
import principal.herramientas.DibujoOpciones;
import principal.herramientas.EscaladorElementos;
import principal.maquinaestado.EstadoJuego;
import principal.sonido.Sonido;

public class Pausa implements EstadoJuego {

    private final SuperficieDibujo sd;

    private final BufferedImage mujer = Constantes.MUJER;
    private final BufferedImage logo = CargadorRecursos.cargarImagenCompatibleTranslucida("/imagenes/iconos/logo.png");

    private final BufferedImage imagenFondo = Constantes.FONDO;
    private final BufferedImage logros = Constantes.DIFICULTAD;
    private final BufferedImage logrosConMouse = Constantes.DIFICULTADCONMOUSE;
    private final BufferedImage musica = Constantes.MUSICA;
    private final BufferedImage musicaConMouse = Constantes.MUSICACONMOUSE;
    private final BufferedImage volver = Constantes.VOLVER;
    private final BufferedImage volverConMouse = Constantes.VOLVERCONMOUSE;

    private BufferedImage logroActual;
    private BufferedImage musicaActual;
    private BufferedImage volverActual;

    private final Rectangle logroR;
    private final Rectangle musicaR;
    private Rectangle volverR;

    private Rectangle r;

    private final Sonido boton = Constantes.BOTON;

    private int tiempoEspera;
    private final Rectangle volverNormalR = new Rectangle(2, Constantes.ALTO_JUEGO - volver.getHeight() - 2, volver.getWidth(), volver.getHeight());

    public Pausa(final SuperficieDibujo sd) {
        this.sd = sd;

        logroR = new Rectangle(Constantes.CENTRO_VENTANA_X - logros.getWidth() / 2, 40, logros.getWidth(), logros.getHeight());
        musicaR = new Rectangle(logroR.x, logroR.y + 60, musica.getWidth(), musica.getHeight());
        volverR = volverNormalR;

        logroActual = logros;
        musicaActual = musica;
        volverActual = volver;

        tiempoEspera = 0;
    }

    @Override
    public void actualizar() {
        r = new Rectangle(EscaladorElementos.escalarPuntoAbajo(sd.getRaton().getPosicion()).x,
                EscaladorElementos.escalarPuntoAbajo(sd.getRaton().getPosicion()).y, 1, 1);

//        if (newDificultad) {
//            if (sd.getRaton().isClickIzquierdo()) {
//                if (r.intersects(dificultadFacil)) {
//                    setDificultad(0, 0, 0);
//                }
//                if (r.intersects(dificultadIntermedia)) {
//                    setDificultad(60, 0, 100);
//                }
//                if (r.intersects(dificultadProfesional)) {
//                    setDificultad(100, 4, 200);
//                }
//                if (r.intersects(dificultadExperto)) {
//                    setDificultad(160, 5, 300);
//                }
//            }
//            if (r.intersects(volverR)) {
//                volverActual = volverConMouse;
//                if (sd.getRaton().isClickIzquierdo()) {
//                    boton.reproducir();
//                    tiempoEspera = 5;
//                    newDificultad = false;
//                    volverR = volverNormalR;
//                }
//            } else {
//                volverActual = volver;
//            }
//            return;
//        }
        if (tiempoEspera > 0) {
            tiempoEspera--;
            return;
        }
        if (r.intersects(logroR)) {
            logroActual = logrosConMouse;
            if (sd.getRaton().isClickIzquierdo()) {
                boton.reproducir();
                GestorPrincipal.ge.cambiarEstadoActual(6);
            }
        } else {
            logroActual = logros;
        }
        if (r.intersects(musicaR)) {
            musicaActual = musicaConMouse;
            if (sd.getRaton().isClickIzquierdo()) {
                boton.reproducir();
                setMusica();
            }
        } else {
            musicaActual = musica;
        }
        if (r.intersects(volverR)) {
            volverActual = volverConMouse;
            if (sd.getRaton().isClickIzquierdo()) {
                boton.reproducir();
                GestorPrincipal.ge.cambiarEstadoActual(1);
            }
        } else {
            volverActual = volver;
        }
    }

    @Override
    public void dibujar(Graphics g) {
        DibujoOpciones.dibujarImagen(g, imagenFondo, new Point(0, 0));
        DibujoOpciones.dibujarImagen(g, logroActual, new Point(logroR.x, logroR.y));
        DibujoOpciones.dibujarImagen(g, musicaActual, new Point(musicaR.x, musicaR.y));

        DibujoOpciones.dibujarImagen(g, mujer, Constantes.ANCHO_JUEGO - mujer.getWidth(), 0);
        DibujoOpciones.dibujarImagen(g, logo, 5, 5);

//        if (newDificultad) {
//            DibujoOpciones.dibujarImagen(g, imagenLogro, Constantes.CENTRO_VENTANA_X - imagenLogro.getWidth() / 2,
//                    Constantes.CENTRO_VENTANA_Y - imagenLogro.getHeight() / 2);
//            DibujoOpciones.dibujarRectBorde(g, dificultadFacil, Color.red);
//            DibujoOpciones.dibujarRectBorde(g, dificultadIntermedia, Color.red);
//            DibujoOpciones.dibujarRectBorde(g, dificultadProfesional, Color.red);
//            DibujoOpciones.dibujarRectBorde(g, dificultadExperto, Color.red);
//            DibujoOpciones.dibujarImagen(g, volverActual, dificultadExperto.x + dificultadExperto.width / 2 - volver.getWidth() / 2,
//                    dificultadExperto.y + 20);
//            return;
//        }

        DibujoOpciones.dibujarImagen(g, volverActual, 2, Constantes.ALTO_JUEGO - volver.getHeight() - 2);
    }

    private void setMusica() {

    }
}
