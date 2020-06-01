//Que estado estamos y que se dibuje a si mismo (que mapa estamos)
package principal.maquinaestado;

import java.awt.Graphics;
import principal.graficos.SuperficieDibujo;
import principal.maquinaestado.estado.juego.GestorJuego;
import principal.maquinaestado.estado.menuinicial.MenuInicio;
import principal.maquinaestado.estado.menujuego.GestorMenu;

public class GestorEstado {

    private EstadoJuego[] estados;
    private EstadoJuego estadoActual;

    public GestorEstado(final SuperficieDibujo sd) {

        iniciarEstados(sd);
        iniciarEstadoActual();
    }

    private void iniciarEstados(final SuperficieDibujo sd) {

        estados = new EstadoJuego[3];
        estados[0] = new MenuInicio(sd);
        estados[1] = new GestorJuego();
        estados[2] = new GestorMenu(sd);
        //Añadir e iniciar los demas estados a medida que los creemos
    }

    private void iniciarEstadoActual() {
        estadoActual = estados[0];
    }

    public void actualizar() {

        estadoActual.actualizar();
    }

    public void dibujar(final Graphics g) {

        estadoActual.dibujar(g);
    }

    public void cambiarEstadoActual(final int nuevoEstado) {

        estadoActual = estados[nuevoEstado];
    }

    public EstadoJuego getEstadoActual() {

        return estadoActual;
    }
}
