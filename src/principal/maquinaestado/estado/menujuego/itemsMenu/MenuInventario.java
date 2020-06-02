package principal.maquinaestado.estado.menujuego.itemsMenu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import principal.Constantes;
import principal.ElementosPrincipales;
import principal.graficos.SuperficieDibujo;
import principal.herramientas.DibujoOpciones;
import principal.herramientas.EscaladorElementos;
import principal.herramientas.GeneradorComentario;
import principal.herramientas.MedidorString;
import principal.inventario.Objeto;

public class MenuInventario extends PlantillaMenu {

    //Cantidad de items total que cabe en el inventario
    private int capacidadMaxima = ElementosPrincipales.jugador.limitePeso;
    //Cantidad de items que lleva le personaje actualmente
    private int capacicadActual = ElementosPrincipales.jugador.pesoActual;

    private final Rectangle barraCapacidad;

    public MenuInventario(String nombreEtiqueta, Rectangle etiqueta, FormaMenu formaMenu) {
        super(nombreEtiqueta, etiqueta, formaMenu);

        int anchoBarra = 80;
        int altoBarra = 9;

        barraCapacidad = new Rectangle(Constantes.ANCHO_JUEGO - anchoBarra - margenGeneral + 1, formaMenu.SUPERIOR.height + margenGeneral, anchoBarra, altoBarra);
    }

    @Override
    public void actualizar() {
        actualizarPosicionesMenu();
    }

    private void actualizarPosicionesMenu() {
        if (!ElementosPrincipales.inventario.getObjetosConsumibles().isEmpty()) {
            for (int i = 0; i < ElementosPrincipales.inventario.getObjetosConsumibles().size(); i++) {
                final Point puntoInicial = new Point(formaMenu.CENTRAL.x + margenGeneral, formaMenu.CENTRAL.y + formaMenu.CENTRAL.height + margenGeneral);
                final int lado = Constantes.LADO_SPRITE;
                int idActual = ElementosPrincipales.inventario.getObjetosConsumibles().get(i).getId();
                ElementosPrincipales.inventario.getObjeto(idActual).setPosicionEnMenu(new Rectangle(puntoInicial.x + i * (lado + margenGeneral), puntoInicial.y, lado, lado));
            }
        }
    }

    @Override
    public void dibujar(Graphics g, SuperficieDibujo sd) {
        dibujarLimiteCapacidad(g);
        dibujarElementosInventario(g);
        //Es necesario escalar la barra de peso para comprobar con el raton, porque ya esta escalado
        if (sd.getRaton().getPosicionRectangulo().intersects(EscaladorElementos.escalarRectanguloArriba(barraCapacidad))) {
            //Nueva fuente para el texto porque no dejaba poner porcentaje (%)
            Font font = new Font("Agency FB", Font.BOLD, 7);
            g.setFont(font);
            GeneradorComentario.dibujarComentario(g, sd, (capacidadMaxima - capacicadActual) + "% de capacidad de inventario libre");
        }
    }

    private void dibujarLimiteCapacidad(final Graphics g) {
        //Dibujar el rectangulo que dice que capacidad tenemos
        //(capacidadMaxima/capacicadActual), esto es para detectar el porcentaje de la barra que esta llena
        final Rectangle capacidad = new Rectangle(barraCapacidad.x + 1, barraCapacidad.y + 1, barraCapacidad.width / (capacidadMaxima / capacicadActual), barraCapacidad.height - 2);

        //Dibujar la barra
        DibujoOpciones.dibujarString(g, "Capacidad", barraCapacidad.x - 35, barraCapacidad.y + 7, Color.BLACK);
        DibujoOpciones.dibujarRectRelleno(g, barraCapacidad, Color.DARK_GRAY);
        DibujoOpciones.dibujarRectRelleno(g, capacidad, Constantes.COLOR_VERDE_CLARO);
    }

    private void dibujarElementosInventario(final Graphics g) {
        if (!ElementosPrincipales.inventario.getObjetosConsumibles().isEmpty()) {
            final Point puntoInicial = new Point(formaMenu.CENTRAL.x + 16, barraCapacidad.y + barraCapacidad.height + margenGeneral);
            final int lado = Constantes.LADO_SPRITE;

            for (int i = 0; i < ElementosPrincipales.inventario.getObjetosConsumibles().size(); i++) {
                int idObjeto = ElementosPrincipales.inventario.getObjetosConsumibles().get(i).getId();
                Objeto objeto = ElementosPrincipales.inventario.getObjeto(idObjeto);

                DibujoOpciones.dibujarImagen(g, objeto.getSprite().getImagen(), puntoInicial.x + i * (32 + margenGeneral), puntoInicial.y);
                DibujoOpciones.dibujarRectRelleno(g, puntoInicial.x + i * (32 + margenGeneral) + 20, puntoInicial.y + 24, 12, 8, Color.BLACK);
                String texto = "";
                if (ElementosPrincipales.inventario.getObjetosConsumibles().get(i).getCantidad() < 10) {
                    texto = "0" + objeto.getCantidad();
                } else {
                    texto = "" + objeto.getCantidad();
                }
                DibujoOpciones.dibujarString(g, texto, puntoInicial.x + i * (lado + margenGeneral) + lado - MedidorString.medirAnchoPixeles(g, texto),
                        puntoInicial.y + 31, Color.WHITE);
            }
        }
    }

}
