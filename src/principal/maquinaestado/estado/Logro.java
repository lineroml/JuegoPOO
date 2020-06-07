package principal.maquinaestado.estado;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import principal.Constantes;
import principal.GestorPrincipal;
import principal.control.GestorControles;
import principal.graficos.SuperficieDibujo;
import principal.herramientas.CargadorRecursos;
import principal.herramientas.DibujoOpciones;
import principal.herramientas.EscaladorElementos;
import principal.maquinaestado.EstadoJuego;
import principal.sonido.Sonido;

public class Logro implements EstadoJuego {

    private final SuperficieDibujo sd;

    private final BufferedImage logo = CargadorRecursos.cargarImagenCompatibleTranslucida("/imagenes/iconos/logo.png");

    private final BufferedImage imagenFondo = Constantes.IMAGENFONDOPAUSA;
    private final BufferedImage volver = Constantes.VOLVER;
    private final BufferedImage volverConMouse = Constantes.VOLVERCONMOUSE;

    private BufferedImage volverActual;

    private Rectangle r;

    private Rectangle volverR;
    private Rectangle mujer1R;
    private Rectangle mujer2R;
    private Rectangle mujer3R;
    private Rectangle mujer4R;
    private Rectangle mujer5R;

    private final Sonido boton = Constantes.BOTON;

    public Logro(final SuperficieDibujo sd) {
        this.sd = sd;

        volverR = new Rectangle(2, Constantes.ALTO_JUEGO - volver.getHeight() - 2, volver.getWidth(), volver.getHeight());
        mujer1R = new Rectangle(30, 100, 64, 64);
        mujer2R = new Rectangle(mujer1R.x + mujer1R.width + 20, 100, 64, 64);
        mujer3R = new Rectangle(mujer2R.x + mujer2R.width + 20, 100, 64, 64);
        mujer4R = new Rectangle(mujer1R.x, mujer1R.y + mujer1R.height + 20, 64, 64);
        mujer5R = new Rectangle(mujer4R.x + mujer4R.width + 20, mujer4R.y, 64, 64);

        volverActual = volver;
    }

    @Override
    public void actualizar() {

        r = new Rectangle(EscaladorElementos.escalarPuntoAbajo(sd.getRaton().getPosicion()).x,
                EscaladorElementos.escalarPuntoAbajo(sd.getRaton().getPosicion()).y, 1, 1);

        if (r.intersects(volverR)) {
            volverActual = volverConMouse;
            if (sd.getRaton().isClickIzquierdo()) {
                boton.reproducir();
                GestorPrincipal.ge.cambiarEstadoActual(5);
            }
        } else {
            volverActual = volver;
        }
    }

    @Override
    public void dibujar(Graphics g) {

        DibujoOpciones.dibujarImagen(g, imagenFondo, new Point(0, 0));
        DibujoOpciones.dibujarImagen(g, logo, 5, 5);
        DibujoOpciones.dibujarImagen(g, volverActual, 2, Constantes.ALTO_JUEGO - volver.getHeight() - 2);
        DibujoOpciones.dibujarRectBorde(g, mujer1R, Color.red);
        DibujoOpciones.dibujarRectBorde(g, mujer2R, Color.red);
        DibujoOpciones.dibujarRectBorde(g, mujer3R, Color.red);
        DibujoOpciones.dibujarRectBorde(g, mujer4R, Color.red);
        DibujoOpciones.dibujarRectBorde(g, mujer5R, Color.red);
    }

}
