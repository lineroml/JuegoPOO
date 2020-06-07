package principal.maquinaestado.estado;

import java.awt.Color;
import java.awt.Font;
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
import principal.herramientas.GeneradorComentario;
import principal.inventario.RegistroObjetos;
import principal.maquinaestado.EstadoJuego;
import principal.sonido.Sonido;

public class Pausa implements EstadoJuego {

    private final SuperficieDibujo sd;

    private final BufferedImage logo = CargadorRecursos.cargarImagenCompatibleTranslucida("/imagenes/iconos/logo.png");

    private final BufferedImage imagenFondo = Constantes.IMAGENFONDOPAUSA;
    private final BufferedImage logros = Constantes.DIFICULTAD;
    private final BufferedImage logrosConMouse = Constantes.DIFICULTADCONMOUSE;
    private final BufferedImage musica = Constantes.MUSICA;
    private final BufferedImage musicaConMouse = Constantes.MUSICACONMOUSE;
    private final BufferedImage salir = Constantes.SALIRMENU;
    private final BufferedImage salirConMouse = Constantes.SALIRMENUCONMOUSE;
    private final BufferedImage salirNo = Constantes.QUIERESALIRNO;
    private final BufferedImage salirNoConMouse = Constantes.QUIERESALIRNOCONMOUSE;
    private final BufferedImage salirSi = Constantes.QUIERESALIRSI;
    private final BufferedImage salirSiConMouse = Constantes.QUIERESALIRSICONMOUSE;
    private final BufferedImage quiereSalir = Constantes.QUIERESALIR;
    private final BufferedImage volver = Constantes.VOLVER;
    private final BufferedImage volverConMouse = Constantes.VOLVERCONMOUSE;

    private BufferedImage logroActual;
    private BufferedImage musicaActual;
    private BufferedImage salirActual;
    private BufferedImage volverActual;

    private final Rectangle logroR;
    private final Rectangle musicaR;
    private final Rectangle salirR;
    private BufferedImage siActual;
    private BufferedImage noActual;

    private Rectangle volverR;

    private boolean seguroSalir = false;
    private Rectangle si = null;
    private Rectangle no = null;

    private Rectangle r;

    private final Sonido boton = Constantes.BOTON;

    private int tiempoEspera;
    private final Rectangle volverNormalR = new Rectangle(2, Constantes.ALTO_JUEGO - volver.getHeight() - 2, volver.getWidth(), volver.getHeight());
    private boolean mostrarMensaje;

    public Pausa(final SuperficieDibujo sd) {
        this.sd = sd;

        logroR = new Rectangle(Constantes.CENTRO_VENTANA_X / 2 - logros.getWidth() / 2, 100, logros.getWidth(), logros.getHeight());
        musicaR = new Rectangle(logroR.x, logroR.y + 60, musica.getWidth(), musica.getHeight());
        salirR = new Rectangle(musicaR.x, musicaR.y + 60, salir.getWidth(), salir.getHeight());
        volverR = new Rectangle(2, Constantes.ALTO_JUEGO - volver.getHeight() - 2, volver.getWidth(), volver.getHeight());

        logroActual = logros;
        musicaActual = musicaConMouse;
        salirActual = salir;
        volverActual = volver;

        tiempoEspera = 0;
        mostrarMensaje = false;
    }

    @Override
    public void actualizar() {
        r = new Rectangle(EscaladorElementos.escalarPuntoAbajo(sd.getRaton().getPosicion()).x,
                EscaladorElementos.escalarPuntoAbajo(sd.getRaton().getPosicion()).y, 1, 1);
        if (seguroSalir) {
            if (r.intersects(no)) {
                noActual = salirNoConMouse;
                if (sd.getRaton().isClickIzquierdo()) {
                    boton.reproducir();
                    tiempoEspera = 5;
                    seguroSalir = false;
                }
            } else {
                noActual = salirNo;
            }
            if (r.intersects(si)) {
                siActual = salirSiConMouse;
                if (sd.getRaton().isClickIzquierdo()) {
                    boton.reproducir();
                    System.exit(0);
                }
            } else {
                siActual = salirSi;
            }
            return;
        }
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
            mostrarMensaje = true;
            if (sd.getRaton().isClickIzquierdo()) {
                if (musicaActual == musicaConMouse) {
                    musicaActual = musica;
                    GestorPrincipal.detenerCancion();
                    boton.reproducir();
                    tiempoEspera = 5;
                } else {
                    musicaActual = musicaConMouse;
                    GestorPrincipal.reproducirCancion();
                    boton.reproducir();
                    tiempoEspera = 5;
                }
            }
        } else {
            musicaActual = musica;
            mostrarMensaje = false;

        }
        
        if (r.intersects(salirR)) {
            salirActual = salirConMouse;
            if (sd.getRaton().isClickIzquierdo()) {
                boton.reproducir();
                seguroSalir = true;
                asignarSeguroSalir();
            }
        } else {
            salirActual = salir;
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
        if (GestorControles.teclado.menuPausa) {
            GestorPrincipal.ge.cambiarEstadoActual(1);
        }
    }

    @Override
    public void dibujar(Graphics g) {
        DibujoOpciones.dibujarImagen(g, imagenFondo, new Point(0, 0));
        DibujoOpciones.dibujarImagen(g, logroActual, new Point(logroR.x, logroR.y));
        DibujoOpciones.dibujarImagen(g, musicaActual, new Point(musicaR.x, musicaR.y));
        DibujoOpciones.dibujarImagen(g, salirActual, new Point(salirR.x, salirR.y));

        DibujoOpciones.dibujarImagen(g, logo, 5, 5);

        if (seguroSalir) {
            DibujoOpciones.dibujarImagen(g, quiereSalir, Constantes.CENTRO_VENTANA_X - quiereSalir.getWidth() / 2,
                    Constantes.CENTRO_VENTANA_Y - quiereSalir.getHeight() / 2);
            DibujoOpciones.dibujarImagen(g, siActual, Constantes.CENTRO_VENTANA_X - quiereSalir.getWidth() / 2 + 40,
                    Constantes.CENTRO_VENTANA_Y - quiereSalir.getHeight() / 2 + 50);
            DibujoOpciones.dibujarImagen(g, noActual, Constantes.CENTRO_VENTANA_X - quiereSalir.getWidth() / 2 + 90,
                    Constantes.CENTRO_VENTANA_Y - quiereSalir.getHeight() / 2 + 50);
        }

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

        if (mostrarMensaje) {
            Font font = new Font("Agency FB", Font.BOLD, 7);
            g.setFont(font);
            if (musicaActual == musicaConMouse) {
                GeneradorComentario.dibujarComentario(g, sd, "Quitar la musica");
            } else {
                GeneradorComentario.dibujarComentario(g, sd, "Poner la musica");
            }
        }
    }

    private void asignarSeguroSalir() {
        si = new Rectangle(Constantes.CENTRO_VENTANA_X - quiereSalir.getWidth() / 2 + 40, Constantes.CENTRO_VENTANA_Y - quiereSalir.getHeight() / 2 + 50,
                salirSi.getWidth(), salirSi.getHeight());
        no = new Rectangle(Constantes.CENTRO_VENTANA_X - quiereSalir.getWidth() / 2 + 90, Constantes.CENTRO_VENTANA_Y - quiereSalir.getHeight() / 2 + 50,
                salirNo.getWidth(), salirNo.getHeight());
    }

    public void setTiempoEspera() {
        tiempoEspera = 5;
    }
}
