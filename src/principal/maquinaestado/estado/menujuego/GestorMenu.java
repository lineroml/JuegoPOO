package principal.maquinaestado.estado.menujuego;

import java.awt.Graphics;
import java.awt.Rectangle;
import principal.graficos.SuperficieDibujo;
import principal.maquinaestado.EstadoJuego;
import principal.maquinaestado.estado.menujuego.itemsMenu.FormaMenu;
import principal.maquinaestado.estado.menujuego.itemsMenu.MenuEtiqueta;

public class GestorMenu implements EstadoJuego {

    private final SuperficieDibujo sd;

    private final FormaMenu inventario;

    private final MenuEtiqueta[] etiquetas;
    private final MenuEtiqueta etiquetaActual;

    public GestorMenu(final SuperficieDibujo sd) {
        this.sd = sd;

        inventario = new FormaMenu();

        etiquetas = new MenuEtiqueta[2];

        final Rectangle etiquetaInventario = new Rectangle(inventario.LATERAL.x + inventario.MARGEN_HORIZONTAL, inventario.LATERAL.y + inventario.MARGEN_VERTICAL, inventario.ANCHO, inventario.ALTO);
        etiquetas[0] = new MenuEtiqueta("Inventario", etiquetaInventario);

        final Rectangle etiquetaEquipo = new Rectangle(inventario.LATERAL.x + inventario.MARGEN_HORIZONTAL, etiquetaInventario.y + etiquetaInventario.height + inventario.MARGEN_VERTICAL, inventario.ANCHO, inventario.ALTO);
        etiquetas[1] = new MenuEtiqueta("Equipo", etiquetaEquipo);

        etiquetaActual = etiquetas[0];
    }

    @Override
    public void actualizar() {
    }

    @Override
    public void dibujar(final Graphics g) {

        inventario.dibujar(g);
        for (int i = 0; i < etiquetas.length; i++) {

            if (sd.getRaton().getPosicionRectangulo().intersects(etiquetas[i].getEtiquetaTamañoReal())) {
                if (etiquetaActual == etiquetas[i]) {
                    etiquetas[i].dibujarEtiquetaActivaResaltada(g);
                } else {
                    etiquetas[i].dibujarEtiquetaInactivaResaltada(g);
                }
            } else {
                if (etiquetaActual == etiquetas[i]) {
                    etiquetas[i].dibujarEtiquetaActiva(g);
                } else {
                    etiquetas[i].dibujarEtiquetaInactiva(g);
                }
            }
        }
    }

}
