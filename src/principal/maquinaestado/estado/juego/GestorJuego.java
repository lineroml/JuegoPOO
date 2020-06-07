package principal.maquinaestado.estado.juego;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import principal.Constantes;
import principal.ElementosPrincipales;
import static principal.GestorPrincipal.ge;
import principal.control.GestorControles;
import principal.herramientas.CargadorRecursos;
import principal.herramientas.DatoOpcion;
import principal.herramientas.DibujoOpciones;
import principal.interface_usuario.MenuInferior;
import principal.maquinaestado.EstadoJuego;

public class GestorJuego implements EstadoJuego {

    BufferedImage logotipo;
    MenuInferior menuInferior;

    public GestorJuego() {

        menuInferior = new MenuInferior();
        logotipo = CargadorRecursos.cargarImagenCompatibleTranslucida(Constantes.RUTA_LOGOTIPO);
    }

//    private void recargarJuego() {
//
//        //Darle la nueva ruta del nuevo mapa para que aparezca el jugador
//        final String rutaMapa = "/mapas/mapa" + ElementosPrincipales.mapa.getMapaSiguiente();
//        ElementosPrincipales.mapa = new Mapa(rutaMapa);
//        ElementosPrincipales.jugador.setPosicionX(ElementosPrincipales.mapa.getCoordenadaInicial().x);
//        ElementosPrincipales.jugador.setPosicionY(ElementosPrincipales.mapa.getCoordenadaInicial().y);
////        final String rutaJugador = "/imagenes/hojas_personajes/" + ElementosPrincipales.mapa.getMapaSiguiente() + ".png";
////        ElementosPrincipales.jugador = new Jugador(rutaJugador);
//    }
    @Override
    public void actualizar() {

        //Detectar si colisiono con el rectangulo de la salida
//        if (ElementosPrincipales.jugador.getLIMITE_ARRIBA().intersects(ElementosPrincipales.mapa.getZonaSalida())) {
//            recargarJuego();
//        }
        ElementosPrincipales.jugador.actualizar();
        ElementosPrincipales.mapa.actualizar();

        if (GestorControles.teclado.inventario) {
            ge.cambiarEstadoActual(2);
        }
        if (GestorControles.teclado.menuPausa) {
            ge.cambiarEstadoActual(5);
            GestorControles.teclado.menuPausa = true;
        }
    }

    @Override
    public void dibujar(Graphics g) {

        ElementosPrincipales.mapa.dibujar(g);
        ElementosPrincipales.jugador.dibujar(g);
        menuInferior.dibujar(g);
        DibujoOpciones.dibujarImagen(g, logotipo, Constantes.ANCHO_JUEGO - logotipo.getWidth() - 5, 5);

        DatoOpcion.enviarDato((int) ElementosPrincipales.jugador.getPosicionXINT() + " ");
        DatoOpcion.enviarDato((int) ElementosPrincipales.jugador.getPosicionYINT() + " ");
    }
}
