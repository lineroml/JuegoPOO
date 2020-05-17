//Se dibujara todo lo del juego aqui
package principal.graficos;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import principal.Constantes;
import principal.GestorPrincipal;
import principal.control.GestorControles;
import principal.control.Raton;
import principal.herramientas.DatoOpcion;
import principal.herramientas.DibujoOpciones;
import principal.maquinaestado.GestorEstado;

public class SuperficieDibujo extends Canvas {

    private static final long serialVersionUID = -6227038142688953660L;

    private int ancho;
    private int alto;

    private Raton raton;

    public SuperficieDibujo(final int ancho, final int alto) {

        this.ancho = ancho;
        this.alto = alto;

        this.raton = new Raton(this);

        setCursor(raton.getCursor());

        //Java no intente obligar a dibujarse el canvas, para versiones anteriores
        setIgnoreRepaint(true);
        setPreferredSize(new Dimension(ancho, alto));
        addKeyListener(GestorControles.teclado);
        setFocusable(true);
        requestFocus();
    }

    public void actualizar() {

        raton.actualizar(this);
    }

    public void dibujar(final GestorEstado ge) {

        final BufferStrategy buffer = getBufferStrategy();

        if (buffer == null) {

            createBufferStrategy(4);
            return;
        }

        final Graphics2D g = (Graphics2D) buffer.getDrawGraphics();

        DibujoOpciones.reiniciarObjetosDibujados();

        g.setFont(Constantes.FUENTE_PIXEL);

        DibujoOpciones.dibujarRectRelleno(g, 0, 0, Constantes.ANCHO_PANTALLA_COMPLETA, Constantes.ALTO_PANTALLA_COMPLETA, Color.BLACK);

        //Hacer pantalla completa
        if (Constantes.FACTOR_ESCALADO_X != 1.0 && Constantes.FACTOR_ESCALADO_Y != 1.0) {
            g.scale(Constantes.FACTOR_ESCALADO_X, Constantes.FACTOR_ESCALADO_Y);
        }

        ge.dibujar(g);

        g.setColor(Color.GRAY);
        DatoOpcion.enviarDato("APS  " + GestorPrincipal.getAps());
        DatoOpcion.enviarDato("FPS  " + GestorPrincipal.getFps());

        if (GestorControles.teclado.opciones) {
            DatoOpcion.dibujarDatos(g);
        } else {
            DatoOpcion.vaciar();
        }

        raton.dibujar(g);

        //Java intenta que todo vaya mas fluido
        Toolkit.getDefaultToolkit().sync();

        g.dispose();

        buffer.show();
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }

    public Raton getRaton() {
        return raton;
    }
}
