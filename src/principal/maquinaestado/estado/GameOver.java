package principal.maquinaestado.estado;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import principal.ElementosPrincipales;
import principal.GestorPrincipal;
import principal.herramientas.CargadorRecursos;
import principal.herramientas.DibujoOpciones;
import principal.maquinaestado.EstadoJuego;

public class GameOver implements EstadoJuego {

    private final BufferedImage gameOver = CargadorRecursos.cargarImagenCompatibleOpaca("/imagenes/iconos/GameOver.png");
    private int num;

    public GameOver() {
        num = 700;
    }

    @Override
    public void dibujar(Graphics g) {
        DibujoOpciones.dibujarImagen(g, gameOver, new Point(0, 0));
    }

    @Override
    public void actualizar() {
        num--;
        if (num == 0) {
            ElementosPrincipales.jugador.renacer();
            GestorPrincipal.ge.cambiarEstadoActual(1);
        }
    }
}
