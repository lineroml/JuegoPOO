package principal.maquinaestado.estado.menujuego;

import java.awt.Graphics;
import java.awt.Rectangle;
import principal.graficos.SuperficieDibujo;
import principal.maquinaestado.EstadoJuego;
import principal.maquinaestado.estado.menujuego.itemsMenu.FormaMenu;
import principal.maquinaestado.estado.menujuego.itemsMenu.MenuEquipo;
import principal.maquinaestado.estado.menujuego.itemsMenu.MenuInventario;
import principal.maquinaestado.estado.menujuego.itemsMenu.PlantillaMenu;

public class GestorMenu implements EstadoJuego {

    private final SuperficieDibujo sd;

    private final FormaMenu inventario;

    private final PlantillaMenu[] etiquetas;
    private PlantillaMenu etiquetaActual;

    public GestorMenu(final SuperficieDibujo sd) {
        this.sd = sd;

        inventario = new FormaMenu();

        etiquetas = new PlantillaMenu[2];

        final Rectangle etiquetaInventario = new Rectangle(inventario.LATERAL.x + inventario.MARGEN_HORIZONTAL, inventario.LATERAL.y + inventario.MARGEN_VERTICAL, inventario.ANCHO, inventario.ALTO);
        etiquetas[0] = new MenuInventario("Inventario", etiquetaInventario, inventario);

        final Rectangle etiquetaEquipo = new Rectangle(inventario.LATERAL.x + inventario.MARGEN_HORIZONTAL, etiquetaInventario.y + etiquetaInventario.height + inventario.MARGEN_VERTICAL, inventario.ANCHO, inventario.ALTO);
        etiquetas[1] = new MenuEquipo("Equipo", etiquetaEquipo, inventario);

        etiquetaActual = etiquetas[0];
    }

    @Override
    public void actualizar() {
        for (int i = 0; i < etiquetas.length; i++) {
            //Saber si el mouse hizo click
            if (sd.getRaton().isClickIzquierdo()) {
                //Detectar si el mouse hizo el click sobre la etiqueta
                if (sd.getRaton().getPosicionRectangulo().intersects(etiquetas[i].getEtiquetaTamanhoReal())) {
                    if (etiquetaActual instanceof MenuEquipo) {
                        MenuEquipo temp = (MenuEquipo) etiquetaActual;
                        if (temp.getObjetoSeleccionado() != null) {
                            temp.setObjetoSeleccionado();
                        }
                    }
                    etiquetaActual = etiquetas[i];
                }
            }
        }
        etiquetaActual.actualizar();
    }

    @Override
    public void dibujar(final Graphics g) {

        inventario.dibujar(g);
        for (int i = 0; i < etiquetas.length; i++) {

            if (sd.getRaton().getPosicionRectangulo().intersects(etiquetas[i].getEtiquetaTamanhoReal())) {
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

        etiquetaActual.dibujar(g, sd);
    }

}
