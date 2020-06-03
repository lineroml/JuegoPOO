//Que estado estamos y que se dibuje a si mismo (que mapa estamos)
package principal.maquinaestado;

import java.awt.Graphics;
import principal.Constantes;
import principal.graficos.SuperficieDibujo;
import principal.maquinaestado.estado.GameOver;
import principal.maquinaestado.estado.juego.GestorJuego;
import principal.maquinaestado.estado.menuinicial.MenuInicio;
import principal.maquinaestado.estado.menuinicial.itemsMenu.Ajustes;
import principal.maquinaestado.estado.menujuego.GestorMenu;

public class GestorEstado {

    private EstadoJuego[] estados;
    private EstadoJuego estadoActual;

    public GestorEstado(final SuperficieDibujo sd) {

        iniciarEstados(sd);
        iniciarEstadoActual();
    }

    private void iniciarEstados(final SuperficieDibujo sd) {

        estados = new EstadoJuego[5];
        estados[0] = new MenuInicio(sd);
        estados[1] = new GestorJuego();
        estados[2] = new GestorMenu(sd);
        estados[3] = new GameOver(sd);
        estados[4] = new Ajustes(sd);
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
        if (estadoActual instanceof GameOver) {
            if (estados[nuevoEstado] instanceof MenuInicio) {
                MenuInicio mi = (MenuInicio) estados[nuevoEstado];
                mi.setTiempoEspera(5);
                mi.setCancion(Constantes.CANCION1);
            }
            if (estadoActual instanceof Ajustes) {
                MenuInicio mi = (MenuInicio) estados[nuevoEstado];
                mi.setTiempoEspera(5);
            }
        }
        estadoActual = estados[nuevoEstado];
    }

    public EstadoJuego getEstadoActual() {

        return estadoActual;
    }

}
