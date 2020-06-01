package principal.maquinaestado.estado.menuinicial;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import principal.Constantes;
import principal.GestorPrincipal;
import principal.graficos.SuperficieDibujo;
import principal.herramientas.CargadorRecursos;
import principal.herramientas.DibujoOpciones;
import principal.herramientas.EscaladorElementos;
import principal.maquinaestado.EstadoJuego;
import principal.maquinaestado.estado.menuinicial.itemsMenu.CargarPartida;

public class MenuInicio implements EstadoJuego {

    private final BufferedImage imagenFondo = CargadorRecursos.cargarImagenCompatibleOpaca("/imagenes/menu/menuInicial.png");
    private final BufferedImage inicioPartida = CargadorRecursos.cargarImagenCompatibleTranslucida("/imagenes/menu/inicioPartida.png");
    private final BufferedImage inicioPartidaConMouse = CargadorRecursos.cargarImagenCompatibleTranslucida("/imagenes/menu/inicioPartidaConMouse.png");
    private final BufferedImage cargarPartida = CargadorRecursos.cargarImagenCompatibleTranslucida("/imagenes/menu/cargarPartida.png");
    private final BufferedImage cargarPartidaConMouse = CargadorRecursos.cargarImagenCompatibleTranslucida("/imagenes/menu/cargarPartidaConMouse.png");
    private final BufferedImage cargarSinPartida = CargadorRecursos.cargarImagenCompatibleTranslucida("/imagenes/menu/cargarSinPartida.png");
    private final BufferedImage configuracion = CargadorRecursos.cargarImagenCompatibleTranslucida("/imagenes/menu/configuracion.png");
    private final BufferedImage configuracionConMouse = CargadorRecursos.cargarImagenCompatibleTranslucida("/imagenes/menu/configuracionConMouse.png");

    private final Rectangle inicioPartidaR;
    private final Rectangle cargarPartidaR;
    private final Rectangle configuracionR;

    final SuperficieDibujo sd;

    private BufferedImage inicioPartidaActual;
    private BufferedImage cargarSinPartidaActual;
    private BufferedImage configuracionActual;

    private CargarPartida partida;

    public MenuInicio(final SuperficieDibujo sd) {
        this.sd = sd;
        this.partida = new CargarPartida();

        inicioPartidaR = new Rectangle(Constantes.CENTRO_VENTANA_X - inicioPartida.getWidth() / 2, 200, inicioPartida.getWidth(), inicioPartida.getHeight());
        cargarPartidaR = new Rectangle(inicioPartidaR.x, inicioPartidaR.y + 40, cargarPartida.getWidth(), cargarPartida.getHeight());
        configuracionR = new Rectangle(cargarPartidaR.x, cargarPartidaR.y + 40, configuracion.getWidth(), configuracion.getHeight());

        inicioPartidaActual = inicioPartida;
        cargarSinPartidaActual = cargarSinPartida;
        configuracionActual = configuracion;
    }

    @Override
    public void actualizar() {
        Rectangle r = new Rectangle(EscaladorElementos.escalarPuntoAbajo(sd.getRaton().getPosicion()).x,
                EscaladorElementos.escalarPuntoAbajo(sd.getRaton().getPosicion()).y, 1, 1);

        if (r.intersects(inicioPartidaR)) {
            inicioPartidaActual = inicioPartidaConMouse;
            if (sd.getRaton().isClickIzquierdo()) {
                GestorPrincipal.ge.cambiarEstadoActual(1);
            }
        } else {
            inicioPartidaActual = inicioPartida;
        }
        if (partida.getPartidas().isEmpty()) {
            cargarSinPartidaActual = cargarSinPartida;
        } else if (r.intersects(cargarPartidaR)) {
            cargarSinPartidaActual = cargarPartidaConMouse;
        } else {
            cargarSinPartidaActual = cargarPartida;
        }
        if (r.intersects(configuracionR)) {
            configuracionActual = configuracionConMouse;
        } else {
            configuracionActual = configuracion;
        }
    }

    @Override
    public void dibujar(Graphics g) {

        DibujoOpciones.dibujarImagen(g, imagenFondo, new Point(0, 0));
        DibujoOpciones.dibujarImagen(g, inicioPartidaActual, new Point(Constantes.CENTRO_VENTANA_X - inicioPartida.getWidth() / 2, 200));
        DibujoOpciones.dibujarImagen(g, cargarSinPartidaActual, new Point(Constantes.CENTRO_VENTANA_X - inicioPartida.getWidth() / 2, 240));
        DibujoOpciones.dibujarImagen(g, configuracionActual, new Point(Constantes.CENTRO_VENTANA_X - inicioPartida.getWidth() / 2, 280));

//        DibujoOpciones.dibujarRectBorde(g, inicioPartidaR, Color.red);
//        DibujoOpciones.dibujarRectBorde(g, cargarPartidaR, Color.red);
//        DibujoOpciones.dibujarRectBorde(g, configuracionR, Color.red);
    }

}
