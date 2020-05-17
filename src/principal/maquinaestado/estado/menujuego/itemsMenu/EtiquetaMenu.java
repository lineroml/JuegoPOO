package principal.maquinaestado.estado.menujuego.itemsMenu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import principal.Constantes;
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
        DibujoOpciones.dibujarRectRelleno(g, etiqueta, Color.BLACK);
        DibujoOpciones.dibujarString(g, nombreEtiqueta, etiqueta.x + 15, etiqueta.y + 12, Color.WHITE);
    }

    public void dibujarEtiquetaActiva(final Graphics g) {
        DibujoOpciones.dibujarRectRelleno(g, etiqueta, Color.BLACK);
        final Rectangle activo = new Rectangle(etiqueta.x, etiqueta.y, 5, etiqueta.height);
        DibujoOpciones.dibujarRectRelleno(g, activo, new Color(0x2ff40a));

        DibujoOpciones.dibujarString(g, nombreEtiqueta, etiqueta.x + 15, etiqueta.y + 12, Color.WHITE);
    }

    public void dibujarEtiquetaInactivaResaltada(final Graphics g) {
        DibujoOpciones.dibujarRectRelleno(g, etiqueta, Color.LIGHT_GRAY);
        DibujoOpciones.dibujarRectRelleno(g, new Rectangle(etiqueta.x, etiqueta.y - 1, etiqueta.width, 1), new Color(0x2ff40a));
        DibujoOpciones.dibujarRectRelleno(g, new Rectangle(etiqueta.x + etiqueta.width - 1, etiqueta.y, 1, etiqueta.height), new Color(0x2ff40a));
        DibujoOpciones.dibujarString(g, nombreEtiqueta, etiqueta.x + 15, etiqueta.y + 12, Color.BLACK);
    }

    public void dibujarEtiquetaActivaResaltada(final Graphics g) {
        DibujoOpciones.dibujarRectRelleno(g, etiqueta, Color.LIGHT_GRAY);
        final Rectangle activo = new Rectangle(etiqueta.x, etiqueta.y, 5, etiqueta.height);
        DibujoOpciones.dibujarRectRelleno(g, activo, new Color(0x2ff40a));
        DibujoOpciones.dibujarRectRelleno(g, new Rectangle(etiqueta.x, etiqueta.y - 1, etiqueta.width, 1), new Color(0x2ff40a));
        DibujoOpciones.dibujarRectRelleno(g, new Rectangle(etiqueta.x + etiqueta.width - 1, etiqueta.y, 1, etiqueta.height), new Color(0x2ff40a));
        DibujoOpciones.dibujarString(g, nombreEtiqueta, etiqueta.x + 15, etiqueta.y + 12, Color.BLACK);
    }

    public String getNombreEtiqueta() {
        return nombreEtiqueta;
    }

    public Rectangle getEtiqueta() {
        return etiqueta;
    }

    public Rectangle getEtiquetaTamañoReal() {
        return new Rectangle(etiqueta.x * (int) Constantes.FACTOR_ESCALADO_X, etiqueta.y * (int) Constantes.FACTOR_ESCALADO_Y, etiqueta.width * (int) Constantes.FACTOR_ESCALADO_X, etiqueta.height * (int) Constantes.FACTOR_ESCALADO_Y);
    }
}
