package principal.control;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import javax.swing.SwingUtilities;
import principal.graficos.SuperficieDibujo;
import principal.herramientas.CargadorRecursos;
import principal.herramientas.DatoOpcion;

public class Raton extends MouseAdapter {

    private final Cursor cursor;
    private Point posicion;

    public Raton(final SuperficieDibujo sd) {

        Toolkit configuracion = Toolkit.getDefaultToolkit();

        BufferedImage icono = CargadorRecursos.cargarImagenCompatibleTranslicida("/imagenes/iconos/iconoCursor.png");

        Point punta = new Point(0, 0);

        this.cursor = configuracion.createCustomCursor(icono, punta, "Cursor por defecto");

        this.posicion = new Point();
        actualizarPosicion(sd);
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
}
