package principal.maquinaestado.estado.menuinicial.itemsMenu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import principal.Constantes;
import principal.ElementosPrincipales;
import principal.GestorPrincipal;
import principal.control.GestorControles;
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
    private final BufferedImage sonidoConMouse = Constantes.SONIDOCONMOUSE;
    private final BufferedImage idioma = Constantes.IDIOMA;
    private final BufferedImage volver = Constantes.VOLVER;
    private final BufferedImage volverConMouse = Constantes.VOLVERCONMOUSE;
    private final BufferedImage volverGrande = Constantes.VOLVERGRANDE;
    private final BufferedImage volverConMouseGrande = Constantes.VOLVERCONMOUSEGRANDE;

    private BufferedImage dificultadActual;
    private BufferedImage musicaActual;
    private BufferedImage sonidoActual;
    private BufferedImage creditosActual;
    private BufferedImage volverActual;
    private BufferedImage volverGrandeActual;

    private final Rectangle dificultadR;
    private final Rectangle musicaR;
    private final Rectangle sonidoR;
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

    private BufferedImage menuSonido;
    private BufferedImage flechaAbajo;
    private BufferedImage flechaAbajoConMouse;
    private BufferedImage flechaArriba;
    private BufferedImage flechaArribaConMouse;
    private BufferedImage flechaAbajoActual;
    private BufferedImage flechaArribaActual;
    private Rectangle flechaArribaR;
    private Rectangle flechaAbajoR;
    private boolean cambioSonido;
    
    private boolean cambioCancion;

    private int tiempoEspera;
    private final Rectangle volverNormalR = new Rectangle(2, Constantes.ALTO_JUEGO - volverGrande.getHeight() - 2, volverGrande.getWidth(), volverGrande.getHeight());

    public Ajustes(final SuperficieDibujo sd) {
        this.sd = sd;

        dificultadR = new Rectangle(Constantes.CENTRO_VENTANA_X - dificultad.getWidth() / 2, 40, dificultad.getWidth(), dificultad.getHeight());
        musicaR = new Rectangle(dificultadR.x, dificultadR.y + 60, musica.getWidth(), musica.getHeight());
        sonidoR = new Rectangle(musicaR.x, musicaR.y + 120, sonido.getWidth(), sonido.getHeight());
        creditosR = new Rectangle(sonidoR.x, sonidoR.y + 60, creditos.getWidth(), creditos.getHeight());
        volverR = volverNormalR;

        dificultadActual = dificultad;
        musicaActual = musica;
        sonidoActual = sonido;
        creditosActual = creditos;
        volverActual = volver;

        dificultadFacil = new Rectangle();
        dificultadIntermedia = new Rectangle();
        dificultadProfesional = new Rectangle();
        dificultadExperto = new Rectangle();
        newDificultad = false;

        flechaArribaR = new Rectangle();
        flechaAbajoR = new Rectangle();
        cambioSonido = false;
        
        cambioCancion = false;

        tiempoEspera = 0;
    }

    @Override
    public void actualizar() {
        r = new Rectangle(EscaladorElementos.escalarPuntoAbajo(sd.getRaton().getPosicion()).x,
                EscaladorElementos.escalarPuntoAbajo(sd.getRaton().getPosicion()).y, 1, 1);

        if (tiempoEspera > 0) {
            tiempoEspera--;
            return;
        }

        if (cambioCancion) {
            if (sd.getRaton().isClickIzquierdo()) {
                if (r.intersects(dificultadFacil)) {
                    GestorPrincipal.setCancion(Constantes.CANCION1);
                }
                if (r.intersects(dificultadIntermedia)) {
//                    GestorPrincipal.setCancion(Constantes.CANCION2);
                }
                if (r.intersects(dificultadProfesional)) {
//                    GestorPrincipal.setCancion(Constantes.CANCION3);
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
        if (cambioSonido) {
            if (r.intersects(flechaAbajoR)) {
                flechaAbajoActual = flechaAbajoConMouse;
                if (sd.getRaton().isClickIzquierdo()) {
                    boton.reproducir();
                    tiempoEspera = 3;
                    GestorPrincipal.setVolumen((float) -0.01);
                    System.out.println("entra");
                }
            } else {
                flechaAbajoActual = flechaAbajo;
            }
            if (r.intersects(flechaArribaR)) {
                flechaArribaActual = flechaArribaConMouse;
                if (sd.getRaton().isClickIzquierdo()) {
                    boton.reproducir();
                    tiempoEspera = 3;
                    GestorPrincipal.setVolumen((float) 0.01);
                }
            } else {
                flechaArribaActual = flechaArriba;
            }
            if (r.intersects(volverR)) {
                volverActual = volverConMouse;
                if (sd.getRaton().isClickIzquierdo()) {
                    boton.reproducir();
                    tiempoEspera = 5;
                    cambioSonido = false;
                    volverR = volverNormalR;
                }
            } else {
                volverActual = volver;
            }
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
            volverGrandeActual = volverConMouseGrande;
            if (sd.getRaton().isClickIzquierdo()) {
                boton.reproducir();
                GestorPrincipal.ge.cambiarEstadoActual(0);
            }
        } else {
            volverGrandeActual = volverGrande;
        }
        if (r.intersects(sonidoR)) {
            sonidoActual = sonidoConMouse;
            if (sd.getRaton().isClickIzquierdo()) {
                boton.reproducir();
                tiempoEspera = 5;
                setSonido();
            }
        } else {
            sonidoActual = sonido;
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

        if (newDificultad || cambioCancion) {
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
        if (cambioSonido) {
            DibujoOpciones.dibujarImagen(g, menuSonido, Constantes.CENTRO_VENTANA_X - menuSonido.getWidth() / 2,
                    Constantes.CENTRO_VENTANA_Y - menuSonido.getHeight() / 2);
            DibujoOpciones.dibujarImagen(g, flechaAbajoActual, Constantes.CENTRO_VENTANA_X - flechaAbajo.getWidth() * 2 + 20,
                    Constantes.CENTRO_VENTANA_Y - flechaAbajo.getHeight() / 2 + 10);
            DibujoOpciones.dibujarImagen(g, flechaArribaActual, Constantes.CENTRO_VENTANA_X + flechaArriba.getWidth() - 20,
                    Constantes.CENTRO_VENTANA_Y - flechaArriba.getHeight() / 2 + 10);
            DibujoOpciones.dibujarImagen(g, volverActual, Constantes.CENTRO_VENTANA_X - volver.getWidth() / 2, Constantes.CENTRO_VENTANA_Y + menuSonido.getHeight() / 2);

            return;
        }

        DibujoOpciones.dibujarImagen(g, volverGrandeActual, 2, Constantes.ALTO_JUEGO - volverGrande.getHeight() - 2);
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
        cambioCancion = true;
        imagenDificultad = Constantes.QUIERESALIR;
        dificultadFacil = new Rectangle(Constantes.CENTRO_VENTANA_X - imagenDificultad.getWidth() / 2 + 20,
                Constantes.CENTRO_VENTANA_Y - imagenDificultad.getHeight() / 2 + 40, imagenDificultad.getWidth() - 40, 15);
        dificultadIntermedia = new Rectangle(dificultadFacil.x, dificultadFacil.y + 20, imagenDificultad.getWidth() - 40, 15);
        dificultadProfesional = new Rectangle(dificultadIntermedia.x, dificultadIntermedia.y + 20, imagenDificultad.getWidth() - 40, 15);
        dificultadExperto = new Rectangle(dificultadProfesional.x, dificultadProfesional.y + 20, imagenDificultad.getWidth() - 40, 15);
        volverR = new Rectangle(dificultadExperto.x + dificultadExperto.width / 2 - volver.getWidth() / 2,
                dificultadExperto.y + 20, volver.getWidth(), volver.getHeight());
    }

    private void setSonido() {
        cambioSonido = true;
        menuSonido = Constantes.MENUSONIDO;
        flechaAbajo = Constantes.FlECHAABAJO;
        flechaAbajoConMouse = Constantes.FlECHAABAJOCONMOUSE;
        flechaArriba = Constantes.FlECHAARRIBA;
        flechaArribaConMouse = Constantes.FlECHAARRIBACONMOUSE;
        flechaAbajoActual = flechaAbajo;
        flechaArribaActual = flechaArriba;

        flechaAbajoR = new Rectangle(Constantes.CENTRO_VENTANA_X - flechaAbajo.getWidth() * 2 + 20,
                Constantes.CENTRO_VENTANA_Y - flechaAbajo.getHeight() / 2 + 10, flechaArriba.getWidth(), flechaArriba.getHeight());
        flechaArribaR = new Rectangle(Constantes.CENTRO_VENTANA_X + flechaArriba.getWidth() - 20,
                Constantes.CENTRO_VENTANA_Y - flechaArriba.getHeight() / 2 + 10, flechaAbajo.getWidth(), flechaAbajo.getHeight());

        volverR = new Rectangle(Constantes.CENTRO_VENTANA_X - volver.getWidth() / 2, Constantes.CENTRO_VENTANA_Y + menuSonido.getHeight() / 2, volver.getWidth(), volver.getHeight());
    }

    private void setCreditos() {

    }

}
