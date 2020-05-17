package principal.maquinaestado.estado.menujuego.itemsMenu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import principal.herramientas.DibujoOpciones;

public abstract class EtiquetaMenu {

    protected final String nombreEtiqueta;
    protected final Rectangle etiqueta;

    public EtiquetaMenu(final String nombreEtiqueta, final Rectangle etiqueta) {

        this.nombreEtiqueta = nombreEtiqueta;
        this.etiqueta = etiqueta;
    }

    public abstract void actualizar();

    public abstract void dibujar(final Graphics g);

    public void dibujarEtiquetaInactiva(final Graphics g) {
        DibujoOpciones.dibujarRectRelleno(g, etiqueta, Color.WHITE);
        DibujoOpciones.dibujarString(g, nombreEtiqueta, etiqueta.x + 15, etiqueta.y + 12, Color.DARK_GRAY);
    }

    public void dibujarEtiquetaActiva(final Graphics g) {
        DibujoOpciones.dibujarRectRelleno(g, etiqueta, Color.WHITE);
        final Rectangle activo = new Rectangle(etiqueta.x, etiqueta.y, 5, etiqueta.height);
        DibujoOpciones.dibujarRectRelleno(g, activo, Color.ORANGE);

        DibujoOpciones.dibujarString(g, nombreEtiqueta, etiqueta.x + 15, etiqueta.y + 12, Color.DARK_GRAY);
    }

    public String getNombreEtiqueta() {
        return nombreEtiqueta;
    }
}
