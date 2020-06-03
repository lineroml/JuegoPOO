package principal.maquinaestado.estado.menuinicial.itemsMenu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import principal.Constantes;
import principal.ElementosPrincipales;
import principal.GestorPrincipal;
import principal.entes.RegistroEnemigos;
import principal.graficos.SuperficieDibujo;
import principal.herramientas.CargadorRecursos;
import principal.herramientas.DibujoOpciones;
import principal.herramientas.EscaladorElementos;
import principal.inventario.RegistroObjetos;
import principal.maquinaestado.EstadoJuego;
import principal.sonido.Sonido;

public class Ajustes implements EstadoJuego {

    private final SuperficieDibujo sd;

    private final BufferedImage mujer = Constantes.MUJER;
    private final BufferedImage logo = CargadorRecursos.cargarImagenCompatibleTranslucida("/imagenes/iconos/logo.png");

    private final BufferedImage imagenFondo = Constantes.FONDO;
    private final BufferedImage dificultad = Constantes.DIFICULTAD;
    private final BufferedImage dificultadConMouse = Constantes.DIFICULTADCONMOUSE;
    private final BufferedImage musica = Constantes.MUSICA;
    private final BufferedImage musicaConMouse = Constantes.MUSICACONMOUSE;
    private final BufferedImage creditos = Constantes.CREDITOS;
    private final BufferedImage creditosConMouse = Constantes.CREDITOSCONMOUSE;
    private final BufferedImage sonido = Constantes.SONIDO;
    private final BufferedImage idioma = Constantes.IDIOMA;
    private final BufferedImage volver = Constantes.VOLVER;
    private final BufferedImage volverConMouse = Constantes.VOLVERCONMOUSE;

    private BufferedImage dificultadActual;
    private BufferedImage musicaActual;
    private BufferedImage creditosActual;
    private BufferedImage volverActual;

    private final Rectangle dificultadR;
    private final Rectangle musicaR;
    private final Rectangle creditosR;
    private Rectangle volverR;

    private Rectangle r;

    private final Sonido boton = Constantes.BOTON;

    private BufferedImage imagenDificultad;
    private Rectangle dificultadFacil;
    private Rectangle dificultadIntermedia;
    private Rectangle dificultadProfesional;
    private Rectangle dificultadExperto;
    private boolean newDificultad;

    private int tiempoEspera;
    private final Rectangle volverNormalR = new Rectangle(2, Constantes.ALTO_JUEGO - volver.getHeight() - 2, volver.getWidth(), volver.getHeight());

    public Ajustes(final SuperficieDibujo sd) {
        this.sd = sd;

        dificultadR = new Rectangle(Constantes.CENTRO_VENTANA_X - dificultad.getWidth() / 2, 40, dificultad.getWidth(), dificultad.getHeight());
        musicaR = new Rectangle(dificultadR.x, dificultadR.y + 60, musica.getWidth(), musica.getHeight());
        creditosR = new Rectangle(musicaR.x, musicaR.y + 180, creditos.getWidth(), creditos.getHeight());
        volverR = volverNormalR;

        dificultadActual = dificultad;
        musicaActual = musica;
        creditosActual = creditos;
        volverActual = volver;

        dificultadFacil = new Rectangle();
        dificultadIntermedia = new Rectangle();
        dificultadProfesional = new Rectangle();
        dificultadExperto = new Rectangle();
        newDificultad = false;

        tiempoEspera = 0;
    }

    @Override
    public void actualizar() {
        r = new Rectangle(EscaladorElementos.escalarPuntoAbajo(sd.getRaton().getPosicion()).x,
                EscaladorElementos.escalarPuntoAbajo(sd.getRaton().getPosicion()).y, 1, 1);

        if (newDificultad) {
            if (sd.getRaton().isClickIzquierdo()) {
                if (r.intersects(dificultadFacil)) {
                    setDificultad(0, 0, 0);
                }
                if (r.intersects(dificultadIntermedia)) {
                    setDificultad(60, 0, 100);
                }
                if (r.intersects(dificultadProfesional)) {
                    setDificultad(100, 4, 200);
                }
                if (r.intersects(dificultadExperto)) {
                    setDificultad(160, 5, 300);
                }
            }
            if (r.intersects(volverR)) {
                volverActual = volverConMouse;
                if (sd.getRaton().isClickIzquierdo()) {
                    boton.reproducir();
                    tiempoEspera = 5;
                    newDificultad = false;
                    volverR = volverNormalR;
                }
            } else {
                volverActual = volver;
            }
            return;
        }
        if (tiempoEspera > 0) {
            tiempoEspera--;
            return;
        }
        if (r.intersects(dificultadR)) {
            dificultadActual = dificultadConMouse;
            if (sd.getRaton().isClickIzquierdo()) {
                boton.reproducir();
                mensajeDificultad();
            }
        } else {
            dificultadActual = dificultad;
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
        if (r.intersects(creditosR)) {
            creditosActual = creditosConMouse;
            if (sd.getRaton().isClickIzquierdo()) {
                boton.reproducir();
                setCreditos();
            }
        } else {
            creditosActual = creditos;
        }
        if (r.intersects(volverR)) {
            volverActual = volverConMouse;
            if (sd.getRaton().isClickIzquierdo()) {
                boton.reproducir();
                GestorPrincipal.ge.cambiarEstadoActual(0);
            }
        } else {
            volverActual = volver;
        }
    }

    @Override
    public void dibujar(Graphics g) {
        DibujoOpciones.dibujarImagen(g, imagenFondo, new Point(0, 0));
        DibujoOpciones.dibujarImagen(g, dificultadActual, new Point(dificultadR.x, dificultadR.y));
        DibujoOpciones.dibujarImagen(g, musicaActual, new Point(musicaR.x, musicaR.y));
        DibujoOpciones.dibujarImagen(g, idioma, new Point(musicaR.x, musicaR.y + idioma.getHeight() + 25));
        DibujoOpciones.dibujarImagen(g, sonido, new Point(musicaR.x, musicaR.y + sonido.getHeight() + 85));
        DibujoOpciones.dibujarImagen(g, creditosActual, new Point(creditosR.x, creditosR.y));

        DibujoOpciones.dibujarImagen(g, mujer, Constantes.ANCHO_JUEGO - mujer.getWidth(), 0);
        DibujoOpciones.dibujarImagen(g, logo, 5, 5);

        if (newDificultad) {
            DibujoOpciones.dibujarImagen(g, imagenDificultad, Constantes.CENTRO_VENTANA_X - imagenDificultad.getWidth() / 2,
                    Constantes.CENTRO_VENTANA_Y - imagenDificultad.getHeight() / 2);
            DibujoOpciones.dibujarRectBorde(g, dificultadFacil, Color.red);
            DibujoOpciones.dibujarRectBorde(g, dificultadIntermedia, Color.red);
            DibujoOpciones.dibujarRectBorde(g, dificultadProfesional, Color.red);
            DibujoOpciones.dibujarRectBorde(g, dificultadExperto, Color.red);
            DibujoOpciones.dibujarImagen(g, volverActual, dificultadExperto.x + dificultadExperto.width / 2 - volver.getWidth() / 2,
                    dificultadExperto.y + 20);
            return;
        }

        DibujoOpciones.dibujarImagen(g, volverActual, 2, Constantes.ALTO_JUEGO - volver.getHeight() - 2);
    }

    private void mensajeDificultad() {
        newDificultad = true;
        imagenDificultad = Constantes.QUIERESALIR;
        dificultadFacil = new Rectangle(Constantes.CENTRO_VENTANA_X - imagenDificultad.getWidth() / 2 + 20,
                Constantes.CENTRO_VENTANA_Y - imagenDificultad.getHeight() / 2 + 40, imagenDificultad.getWidth() - 40, 15);
        dificultadIntermedia = new Rectangle(dificultadFacil.x, dificultadFacil.y + 20, imagenDificultad.getWidth() - 40, 15);
        dificultadProfesional = new Rectangle(dificultadIntermedia.x, dificultadIntermedia.y + 20, imagenDificultad.getWidth() - 40, 15);
        dificultadExperto = new Rectangle(dificultadProfesional.x, dificultadProfesional.y + 20, imagenDificultad.getWidth() - 40, 15);
        volverR = new Rectangle(dificultadExperto.x + dificultadExperto.width / 2 - volver.getWidth() / 2,
                dificultadExperto.y + 20, volver.getWidth(), volver.getHeight());
    }

    private void setDificultad(final int vidaZombies, final int dañoPoder, final int cantidadZombies) {
        RegistroEnemigos.setDificultad(vidaZombies);
        RegistroObjetos.setDificultad(dañoPoder);
        ElementosPrincipales.mapa.setDificultad(cantidadZombies);
    }

    private void setMusica() {

    }

    private void setCreditos() {

    }

}
