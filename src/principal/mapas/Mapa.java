package principal.mapas;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import principal.Constantes;
import principal.herramientas.CargadorRecursos;
import principal.herramientas.DibujoOpciones;
import principal.sprites.HojaSprites;
import principal.sprites.Sprite;

public class Mapa {

    private final String contenido;

    private String[] partes;
    private final int ANCHO;
    private final int ALTO;

    private final Point coordenadaInicial;
    private final Point coordenadaSalida;

    private Rectangle ZonaSalida;

    private String mapaSiguiente;

    private final Sprite[] PALETA;

    private final boolean[] COLISIONES;

    public ArrayList<Rectangle> areasColision = new ArrayList<Rectangle>();

    private final int[] SPRITES;

    private final int margenX = Constantes.ANCHO_JUEGO / 2 - Constantes.LADO_SPRITE;
    private final int margenY = Constantes.ALTO_JUEGO / 2 - Constantes.LADO_SPRITE;

    public Mapa(final String ruta) {

        this.contenido = CargadorRecursos.leerArchivoTexto(ruta);

        this.partes = contenido.split("\\*");

        ANCHO = Integer.parseInt(partes[0]);
        ALTO = Integer.parseInt(partes[1]);

        String hojasUtilizadas = partes[2];
        String[] HojasSeparadas = hojasUtilizadas.split(",");

        String paletaEntera = partes[3];
        String[] partesPaleta = paletaEntera.split("#");

        PALETA = asignarSprite(partesPaleta, HojasSeparadas);

        String colisionesEnteras = partes[4];
        COLISIONES = extraerColisiones(colisionesEnteras);

        String spritesEnteros = partes[5];
        String[] cadenaSprites = spritesEnteros.split(" ");

        SPRITES = extraerSprites(cadenaSprites);

        //Obtener del archivo de texto las coordenadas de comienzo
        String posicionComienzo = partes[6];
        String[] posiciones = posicionComienzo.split("-");
        coordenadaInicial = new Point();
        coordenadaInicial.x = Integer.parseInt(posiciones[0]) * Constantes.LADO_SPRITE;
        coordenadaInicial.y = Integer.parseInt(posiciones[1]) * Constantes.LADO_SPRITE;

        String salida = partes[7];
        String[] datosSalida = salida.split("-");
        coordenadaSalida = new Point();
        coordenadaSalida.x = Integer.parseInt(datosSalida[0]);
        coordenadaSalida.y = Integer.parseInt(datosSalida[1]);
        mapaSiguiente = datosSalida[2];
        ZonaSalida = new Rectangle();
    }

    private Sprite[] asignarSprite(final String[] partesPaleta, final String[] HojasSeparadas) {

        Sprite[] paleta = new Sprite[partesPaleta.length];
        HojaSprites hoja = new HojaSprites("/imagenes/hojas_de_texturas/" + HojasSeparadas[0] + ".png", 32, false);

        for (int i = 0; i < partesPaleta.length; i++) {
            String spriteTemporal = partesPaleta[i];
            String[] partesSprite = spriteTemporal.split("-");

            int indicePaleta = Integer.parseInt(partesSprite[0]);
            int indiceSpriteHoja = Integer.parseInt(partesSprite[2]);

            paleta[indicePaleta] = hoja.getSprite(indiceSpriteHoja);
        }
        return paleta;
    }

    private boolean[] extraerColisiones(final String cadenaColisiones) {

        boolean[] colisiones = new boolean[cadenaColisiones.length()];
        for (int i = 0; i < cadenaColisiones.length(); i++) {
            if (cadenaColisiones.charAt(i) == '0') {
                colisiones[i] = false;
            } else {
                colisiones[i] = true;
            }
        }
        return colisiones;
    }

    private int[] extraerSprites(final String[] cadenaSprites) {

        ArrayList<Integer> sprites = new ArrayList<Integer>();

        for (int i = 0; i < cadenaSprites.length; i++) {
            if (cadenaSprites[i].length() == 2) {
                sprites.add(Integer.parseInt(cadenaSprites[i]));
            } else {
                String uno = "";
                String dos = "";

                String error = cadenaSprites[i];

                uno += error.charAt(0);
                uno += error.charAt(1);
                dos += error.charAt(2);
                dos += error.charAt(3);

                sprites.add(Integer.parseInt(uno));
                sprites.add(Integer.parseInt(dos));
            }
        }

        int[] vectorSprites = new int[sprites.size()];

        for (int i = 0; i < sprites.size(); i++) {
            vectorSprites[i] = sprites.get(i);
        }

        return vectorSprites;
    }

    public void actualizar(final int posicionX, final int posicionY) {

        actualizarAreasColision(posicionX, posicionY);
        actualizarZonaSalida(posicionX, posicionY);
    }

    private void actualizarAreasColision(final int posicionX, final int posicionY) {

        if (!areasColision.isEmpty()) {
            areasColision.clear();
        }
        for (int y = 0; y < this.ALTO; y++) {
            for (int x = 0; x < this.ANCHO; x++) {
                int puntoX = x * Constantes.LADO_SPRITE - posicionX + margenX;
                int puntoY = y * Constantes.LADO_SPRITE - posicionY + margenY;

                if (COLISIONES[x + y * this.ANCHO]) {
                    final Rectangle r = new Rectangle(puntoX, puntoY, Constantes.LADO_SPRITE, Constantes.LADO_SPRITE);
                    areasColision.add(r);
                }
            }
        }
    }

    private void actualizarZonaSalida(final int posicionX, final int posicionY) {

        int puntoX = ((int) coordenadaSalida.getX()) * Constantes.LADO_SPRITE - posicionX + margenX;
        int puntoY = ((int) coordenadaSalida.getY()) * Constantes.LADO_SPRITE - posicionY + margenY;

        ZonaSalida = new Rectangle(puntoX, puntoY, Constantes.LADO_SPRITE, Constantes.LADO_SPRITE);
    }

    public void dibujar(Graphics g, int posicionX, int posicionY) {

        for (int y = 0; y < this.ALTO; y++) {
            for (int x = 0; x < this.ANCHO; x++) {

                BufferedImage imagen = this.PALETA[this.SPRITES[x + y * this.ANCHO]].getImagen();

                int puntoX = x * Constantes.LADO_SPRITE - posicionX + margenX;
                int puntoY = y * Constantes.LADO_SPRITE - posicionY + margenY;

                DibujoOpciones.dibujarImagen(g, imagen, puntoX, puntoY);
            }
        }
    }

    public Rectangle getBordes(final int posicionX, final int posicionY, final int anchoJugador, final int altoJugador) {

        int x = margenX - posicionX + anchoJugador;
        int y = margenY - posicionY + altoJugador;

        int anchoRectangulo = this.ANCHO * Constantes.LADO_SPRITE - anchoJugador * 2;
        int altoRectangulo = this.ALTO * Constantes.LADO_SPRITE - altoJugador * 2;

        return new Rectangle(x, y, anchoRectangulo, altoRectangulo);
    }

    public Point getCoordenadaInicial() {
        return coordenadaInicial;
    }

    public String getMapaSiguiente() {
        return mapaSiguiente;
    }

    public Rectangle getZonaSalida() {
        return ZonaSalida;
    }
}
