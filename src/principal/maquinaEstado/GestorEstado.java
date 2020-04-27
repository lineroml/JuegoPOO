package principal.maquinaEstado;

import java.awt.Graphics;
import principal.maquinaestado.estado.juego.GestorJuego;

public class GestorEstado {

    private EstadoJuego[] estados;
    private EstadoJuego estadoActual;

    public GestorEstado() {
        iniciarEstado();
        iniciarEstadoActual();
    }

    private void iniciarEstado() {
        estados = new EstadoJuego[1];
        estados[0] = new GestorJuego();
        // añadir e iniciar los demas estados a medida que los creemos
    }

    private void iniciarEstadoActual() {
        estadoActual = estados[0];
    }

    public void actualizar() {
        estadoActual.actualizar();
    }

    public void dibujar(Graphics g) {
        estadoActual.dibujar(g);
    }

    private void cambiarEstadoActual(final int nuevoEstado) {
        estadoActual = estados[nuevoEstado];
    }

    public EstadoJuego getEstadoActual() {
        return estadoActual;
    }

}
