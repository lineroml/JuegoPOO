//Que estado estamos y que se dibuje a si mismo (que mapa estamos)
package principal.maquinaestado;

import java.awt.Graphics;
import principal.ElementosPrincipales;
import principal.control.GestorControles;
import principal.graficos.SuperficieDibujo;
import principal.inventario.elementosMujeres.Elemento;
import principal.maquinaestado.estado.GameOver;
import principal.maquinaestado.estado.Instrucciones;
import principal.maquinaestado.estado.logros.Logro;
import principal.maquinaestado.estado.Pausa;
import principal.maquinaestado.estado.juego.GestorJuego;
import principal.maquinaestado.estado.logros.Mujer;
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

        estados = new EstadoJuego[9];
        estados[0] = new MenuInicio(sd);
        estados[1] = new GestorJuego();
        estados[2] = new GestorMenu(sd);
        estados[3] = new GameOver(sd);
        estados[4] = new Ajustes(sd);
        estados[5] = new Pausa(sd);
        estados[6] = new Logro(sd);
        estados[7] = new Mujer(sd);
        estados[8] = new Instrucciones();
        //Añadir e iniciar los demas estados a medida que los creemos
    }

    private void iniciarEstadoActual() {
        estadoActual = estados[0];
    }

    public void actualizar() {
        estadoActual.actualizar();
        if (estadoActual instanceof GestorJuego || estadoActual instanceof Logro) {
            if (!ElementosPrincipales.inventario.getObjetosElementos().isEmpty()) {
                if (GestorControles.teclado.num1 && ElementosPrincipales.inventario.getObjetosElementos().size() >= 1) {
                    Elemento e = (Elemento) ElementosPrincipales.inventario.getObjetosElementos().get(0);
                    e.asignarMujer((Mujer) estados[7]);
                    GestorControles.teclado.num1 = true;
                    estadoActual = estados[7];
                }
                if (GestorControles.teclado.num2 && ElementosPrincipales.inventario.getObjetosElementos().size() >= 2) {
                    Elemento e = (Elemento) ElementosPrincipales.inventario.getObjetosElementos().get(1);
                    e.asignarMujer((Mujer) estados[7]);
                    GestorControles.teclado.num2 = true;
                    estadoActual = estados[7];
                }
                if (GestorControles.teclado.num3 && ElementosPrincipales.inventario.getObjetosElementos().size() >= 3) {
                    Elemento e = (Elemento) ElementosPrincipales.inventario.getObjetosElementos().get(2);
                    e.asignarMujer((Mujer) estados[7]);
                    GestorControles.teclado.num3 = true;
                    estadoActual = estados[7];
                }
                if (GestorControles.teclado.num4 && ElementosPrincipales.inventario.getObjetosElementos().size() >= 4) {
                    Elemento e = (Elemento) ElementosPrincipales.inventario.getObjetosElementos().get(3);
                    e.asignarMujer((Mujer) estados[7]);
                    GestorControles.teclado.num4 = true;
                    estadoActual = estados[7];
                }
                if (GestorControles.teclado.num5 && ElementosPrincipales.inventario.getObjetosElementos().size() >= 5) {
                    Elemento e = (Elemento) ElementosPrincipales.inventario.getObjetosElementos().get(4);
                    e.asignarMujer((Mujer) estados[7]);
                    GestorControles.teclado.num5 = true;
                    estadoActual = estados[7];
                }
            }
        }
    }

    public void dibujar(final Graphics g) {

        estadoActual.dibujar(g);
    }

    public void cambiarEstadoActual(final int nuevoEstado) {
        if (estadoActual instanceof GameOver || estadoActual instanceof Ajustes) {
            if (estados[nuevoEstado] instanceof MenuInicio) {
                MenuInicio mi = (MenuInicio) estados[nuevoEstado];
                mi.setTiempoEspera(5);
            }
        }
        if (estadoActual instanceof Logro) {
            if (estados[nuevoEstado] instanceof Pausa) {
                Pausa p = (Pausa) estados[nuevoEstado];
                p.setTiempoEspera();
            }
        }
        estadoActual = estados[nuevoEstado];
    }

    public EstadoJuego getEstadoActual() {
        return estadoActual;
    }

    public EstadoJuego getEstado(final int i) {
        return estados[i];
    }

}
