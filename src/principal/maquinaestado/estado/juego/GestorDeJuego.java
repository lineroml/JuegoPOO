package principal.maquinaestado.estado.juego;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import principal.Constantes;
import principal.entes.Jugador;
import principal.herramientas.CargadorRecursos;
import principal.herramientas.DatoOpcion;
import principal.herramientas.DibujoOpciones;
import principal.interface_usuario.MenuInferior;
import principal.mapas.Mapa;
import principal.maquinaestado.EstadoJuego;

public class GestorDeJuego implements EstadoJuego {

    Mapa mapa;
    Jugador jugador;
    BufferedImage Logotipo;
    MenuInferior menuInferior;

    public GestorDeJuego() {

        iniciarMapa("/mapas/mapa1");
        iniciarJugador();
        menuInferior = new MenuInferior(jugador);
        Logotipo = CargadorRecursos.cargarImagenCompatibleTranslicida("/imagenes/iconos/Logotipo.png");
    }

    private void recargarJuego() {

        //Darle la nueva ruta del nuevo mapa para que aparezca el jugador
        final String ruta = "/mapas/mapa" + mapa.getMapaSiguiente();
        iniciarMapa(ruta);
        recargarJugador(mapa.getMapaSiguiente());
    }

    private void iniciarMapa(final String ruta) {

        mapa = new Mapa(ruta);
    }

    private void iniciarJugador() {

        jugador = new Jugador(mapa);
    }

    private void recargarJugador(final String jugadorNum) {

        int jugadorNumFinal = Integer.parseInt(jugadorNum) + 1;
        final String ruta = "/imagenes/hojas_personajes/Personaje" + jugadorNumFinal + ".png";
        jugador = new Jugador(mapa, ruta);
    }

    @Override
    public void actualizar() {

        //Detectar si colisiono con el rectangulo de la salida
        if (jugador.getLIMITE_ARRIBA().intersects(mapa.getZonaSalida())) {
            recargarJuego();
        }
        jugador.actualizar();
        mapa.actualizar((int) jugador.getPosicionX(), (int) jugador.getPosicionY());
    }

    @Override
    public void dibujar(Graphics g) {

        mapa.dibujar(g, (int) jugador.getPosicionX(), (int) jugador.getPosicionY());
        jugador.dibujar(g);
        menuInferior.dibujar(g, jugador);
        DibujoOpciones.dibujarImagen(g, Logotipo, Constantes.ANCHO_JUEGO - Logotipo.getWidth() - 5, 5);

        DatoOpcion.enviarDato((int) jugador.getPosicionX() + " ");
        DatoOpcion.enviarDato((int) jugador.getPosicionY() + " ");
    }
}
