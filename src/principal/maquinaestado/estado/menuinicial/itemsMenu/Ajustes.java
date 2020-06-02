package principal.maquinaestado.estado.menuinicial.itemsMenu;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import principal.Constantes;
import principal.graficos.SuperficieDibujo;
import principal.herramientas.DibujoOpciones;
import principal.herramientas.EscaladorElementos;
import principal.maquinaestado.EstadoJuego;
import principal.sonido.Sonido;

public class Ajustes implements EstadoJuego {

    private final SuperficieDibujo sd;

    private final BufferedImage imagenFondo = Constantes.FONDO;
    private final BufferedImage dificultad = Constantes.DIFICULTAD;
    private final BufferedImage dificultadConMouse = Constantes.DIFICULTADCONMOUSE;
    private final BufferedImage musica = Constantes.MUSICA;
    private final BufferedImage musicaConMouse = Constantes.MUSICACONMOUSE;
    private final BufferedImage creditos = Constantes.CREDITOS;
    private final BufferedImage creditosConMouse = Constantes.CREDITOSCONMOUSE;
    private final BufferedImage sonido = Constantes.SONIDO;
    private final BufferedImage idioma = Constantes.IDIOMA;

    private BufferedImage dificultadActual;
    private BufferedImage musicaActual;
    private BufferedImage creditosActual;

    private final Rectangle dificultadR;
    private final Rectangle musicaR;
    private final Rectangle creditosR;

    private Rectangle r;

    private final Sonido boton = Constantes.BOTON;
    private long duracion = boton.getDuracion();
    private boolean reproduciendo = false;

    public Ajustes(final SuperficieDibujo sd) {
        this.sd = sd;

        dificultadR = new Rectangle(Constantes.CENTRO_VENTANA_X - dificultad.getWidth() / 2, 140, dificultad.getWidth(), dificultad.getHeight());
        musicaR = new Rectangle(dificultadR.x, dificultadR.y + 40, musica.getWidth(), musica.getHeight());
        creditosR = new Rectangle(musicaR.x, musicaR.y + 40, creditos.getWidth(), creditos.getHeight());

        dificultadActual = dificultad;
        musicaActual = musica;
        creditosActual = creditos;
    }

    @Override
    public void actualizar() {
        r = new Rectangle(EscaladorElementos.escalarPuntoAbajo(sd.getRaton().getPosicion()).x,
                EscaladorElementos.escalarPuntoAbajo(sd.getRaton().getPosicion()).y, 1, 1);

        if (r.intersects(dificultadR)) {
            reproducirSonido();
            dificultadActual = dificultadConMouse;
            if (sd.getRaton().isClickIzquierdo()) {
                setDificultad();
            }
        } else {
            dificultadActual = dificultad;
        }
        if (r.intersects(musicaR)) {
            reproducirSonido();
            musicaActual = musicaConMouse;
            if (sd.getRaton().isClickIzquierdo()) {
                setMusica();
            }
        } else {
            musicaActual = musica;
        }
        if (r.intersects(creditosR)) {
            reproducirSonido();
            creditosActual = creditosConMouse;
            if (sd.getRaton().isClickIzquierdo()) {
                setCreditos();
            }
        } else {
            creditosActual = creditos;
        }
        if (reproduciendo) {
            duracion--;
            if (duracion == 0) {
                reproduciendo = false;
                duracion = boton.getDuracion();
            }
        }
    }

    private void reproducirSonido() {
        if (duracion == boton.getDuracion()) {
            reproduciendo = true;
            boton.reproducir();
        }
    }

    @Override
    public void dibujar(Graphics g) {

        DibujoOpciones.dibujarImagen(g, imagenFondo, new Point(0, 0));
        DibujoOpciones.dibujarImagen(g, dificultadActual, new Point(dificultadR.x, dificultadR.y));
        DibujoOpciones.dibujarImagen(g, musicaActual, new Point(musicaR.x, musicaR.y));
        DibujoOpciones.dibujarImagen(g, creditosActual, new Point(creditosR.x, creditosR.y));
        DibujoOpciones.dibujarImagen(g, idioma, new Point(creditosR.x, creditosR.y + idioma.getHeight() + 5));
        DibujoOpciones.dibujarImagen(g, sonido, new Point(creditosR.x, creditosR.y + sonido.getHeight() + 45));
    }

    private void setDificultad() {

    }

    private void setMusica() {

    }

    private void setCreditos() {

    }

}
