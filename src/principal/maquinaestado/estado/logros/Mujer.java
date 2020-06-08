package principal.maquinaestado.estado.logros;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import principal.Constantes;
import principal.GestorPrincipal;
import principal.control.GestorControles;
import principal.graficos.SuperficieDibujo;
import principal.herramientas.DibujoOpciones;
import principal.herramientas.EscaladorElementos;
import principal.maquinaestado.EstadoJuego;
import principal.sonido.GestorSonido;

public class Mujer implements EstadoJuego {

    private final SuperficieDibujo sd;
    private final GestorSonido boton = Constantes.BOTON;

    private BufferedImage imagenBiografia;

    private final BufferedImage volver = Constantes.VOLVERGRANDE;
    private final BufferedImage volverConMouse = Constantes.VOLVERCONMOUSEGRANDE;
    private BufferedImage volverActual;
    private final Rectangle volverR;

    private Rectangle r;

    public Mujer(final SuperficieDibujo sd) {
        this.sd = sd;

        volverR = new Rectangle(2, Constantes.ALTO_JUEGO - volver.getHeight() - 2, volver.getWidth(), volver.getHeight());
    }

    @Override
    public void actualizar() {
        r = new Rectangle(EscaladorElementos.escalarPuntoAbajo(sd.getRaton().getPosicion()).x,
                EscaladorElementos.escalarPuntoAbajo(sd.getRaton().getPosicion()).y, 1, 1);

        if (r.intersects(volverR)) {
            volverActual = volverConMouse;
            if (sd.getRaton().isClickIzquierdo()) {
                boton.reproducir();
                GestorPrincipal.ge.cambiarEstadoActual(6);
                GestorControles.teclado.num1 = false;
            }
        } else {
            volverActual = volver;
        }
        if (!GestorControles.teclado.num1) {
            GestorPrincipal.ge.cambiarEstadoActual(1);
            GestorControles.teclado.num1 = false;
        }
    }

    @Override
    public void dibujar(Graphics g) {
        DibujoOpciones.dibujarImagen(g, imagenBiografia, new Point(0, 0));
        DibujoOpciones.dibujarImagen(g, volverActual, 2, Constantes.ALTO_JUEGO - volver.getHeight() - 2);
    }

    public void setImagenBiografia(final BufferedImage imagenBiografia) {
        this.imagenBiografia = imagenBiografia;
    }

}
