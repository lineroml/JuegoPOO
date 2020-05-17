package principal.maquinaestado.estado.menujuego.itemsMenu;

import java.awt.Graphics;
import java.awt.Rectangle;
import principal.maquinaestado.EstadoJuego;

public class GestorMenu implements EstadoJuego {

    private final FormaMenu inventario;

    private final MenuEtiqueta[] etiquetas;
    private final MenuEtiqueta etiquetaActual;

    public GestorMenu() {
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
            if (etiquetaActual == etiquetas[i]) {
                etiquetas[i].dibujarEtiquetaActiva(g);
            } else {
                etiquetas[i].dibujarEtiquetaInactiva(g);
            }
        }
    }

}
