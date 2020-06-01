package principal.control;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.SwingUtilities;
import principal.Constantes;
import principal.graficos.SuperficieDibujo;
import principal.herramientas.CargadorRecursos;
import principal.herramientas.DatoOpcion;

public class Raton extends MouseAdapter {

    private final Cursor cursor;
    private Point posicion;
    private boolean clickIzquierdo;
    private boolean clickDerecho;

    public Raton(final SuperficieDibujo sd) {

        Toolkit configuracion = Toolkit.getDefaultToolkit();

        BufferedImage icono = CargadorRecursos.cargarImagenCompatibleTranslicida(Constantes.RUTA_ICONO_RATON);

        Point punta = new Point(0, 0);

        this.cursor = configuracion.createCustomCursor(icono, punta, "Cursor por defecto");

        this.posicion = new Point();
        actualizarPosicion(sd);

        clickIzquierdo = false;
        clickDerecho = false;
        Constantes.LADO_CURSOR = icono.getHeight();
    }

    public void actualizar(final SuperficieDibujo sd) {

        actualizarPosicion(sd);
    }

    public void dibujar(final Graphics g) {

        DatoOpcion.enviarDato((int) posicion.getX() + "");
        DatoOpcion.enviarDato((int) posicion.getY() + "");
    }

    public Cursor getCursor() {

        return this.cursor;
    }

    //Detectar la posicion del raton
    private void actualizarPosicion(final SuperficieDibujo sd) {

        //Detectar la localizacion del raton
        final Point posicionRatonInicial = MouseInfo.getPointerInfo().getLocation();
        //Poder tener la posicion exacta del raton en cualquier tamaño de la ventana
        SwingUtilities.convertPointFromScreen(posicionRatonInicial, sd);
        //Darle a posicion el valor de la posicion del raton
        posicion.setLocation(posicionRatonInicial.getX(), posicionRatonInicial.getY());
    }

    public Rectangle getPosicionRectangulo() {
        final Rectangle area = new Rectangle(posicion.x, posicion.y, 1, 1);
        return area;
    }

    public Point getPosicion() {
        return posicion;
    }

    //Cuando se ha clicado el raton
    @Override
    public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            clickIzquierdo = true;
        } else if (SwingUtilities.isRightMouseButton(e)) {
            clickDerecho = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            clickIzquierdo = false;
        } else if (SwingUtilities.isRightMouseButton(e)) {
            clickDerecho = false;
        }
    }

    public boolean isClickIzquierdo() {
        return clickIzquierdo;
    }

    public boolean isClickDerecho() {
        return clickDerecho;
    }

}
