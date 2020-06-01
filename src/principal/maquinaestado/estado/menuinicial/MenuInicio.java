package principal.maquinaestado.estado.menuinicial;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import principal.herramientas.CargadorRecursos;
import principal.herramientas.DibujoOpciones;
import principal.maquinaestado.EstadoJuego;

public class MenuInicio implements EstadoJuego {

    private final BufferedImage imagen = CargadorRecursos.cargarImagenCompatibleOpaca("/imagenes/iconos/MenuInicial.png");

    @Override
    public void actualizar() {

    }

    @Override
    public void dibujar(Graphics g) {
        DibujoOpciones.dibujarImagen(g, imagen, new Point(0, 0));
    }

}
