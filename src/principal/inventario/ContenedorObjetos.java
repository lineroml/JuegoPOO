package principal.inventario;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import principal.herramientas.CargadorRecursos;
import principal.herramientas.DibujoOpciones;

public class ContenedorObjetos {

    private BufferedImage sprite = CargadorRecursos.cargarImagenCompatibleTranslicida("/imagenes/CofreCerrado.png");

    private Point posicion;
    private Objeto[] objetos;

    public ContenedorObjetos(final Point posicion, final int[] objetos, final int[] cantidades) {
        this.posicion = posicion;
        this.objetos = new Objeto[objetos.length];
        for (int i = 0; i < objetos.length; i++) {
            this.objetos[i] = RegistroObjetos.getObjeto(objetos[i]);
            this.objetos[i].aumentarCantidad(cantidades[i]);
        }

    }

    public void dibujar(final Graphics g, final int puntoX, final int puntoY) {
        DibujoOpciones.dibujarImagen(g, sprite, puntoX, puntoY);
    }

    public Point getPosicion() {
        return posicion;
    }

    public Objeto[] getObjetos() {
        return objetos;
    }

    public void setCofre() {
        sprite = CargadorRecursos.cargarImagenCompatibleTranslicida("/imagenes/CofreAbierto.png");
    }

}
