package principal.mapas;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import principal.Constantes;
import principal.ElementosPrincipales;
import principal.control.GestorControles;
import principal.entes.Enemigo;
import principal.entes.RegistroEnemigos;
import principal.herramientas.CargadorRecursos;
import principal.herramientas.DibujoOpciones;
import principal.inventario.ContenedorObjetos;
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
    public ArrayList<ContenedorObjetos> objetosMapa;
    public final ArrayList<Enemigo> enemigos;

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

        if (partes.length > 7) {
            String infoContenedorObjetos = partes[8];
            objetosMapa = informacionObjetos(infoContenedorObjetos);
        }

        String infoEnemigos = partes[9];
        enemigos = asignarEnemigos(infoEnemigos);
    }

    private ArrayList<Enemigo> asignarEnemigos(final String infoEnemigos) {
        ArrayList<Enemigo> enemigosTemp = new ArrayList();
        String[] enem = infoEnemigos.split("#");
        for (int i = 0; i < enem.length; i++) {
            String[] separado = enem[i].split(":");
            String[] coordenada = separado[0].split(",");
            String idEnemigo = separado[1];

            Enemigo enemigo = RegistroEnemigos.getEnemigo(Integer.parseInt(idEnemigo));
            enemigo.setPosicion(Double.parseDouble(coordenada[0]), Double.parseDouble(coordenada[1]));

            enemigosTemp.add(enemigo);
        }

        return enemigosTemp;
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

    private ArrayList<ContenedorObjetos> informacionObjetos(final String infoContenedorObjetos) {
        final ArrayList<ContenedorObjetos> objetos = new ArrayList();
        final String[] contenedores = infoContenedorObjetos.split("#");
        for (String contenedor : contenedores) {
            final ArrayList<Integer> idObjetos = new ArrayList();
            final ArrayList<Integer> cantidades = new ArrayList();

            final String[] info = contenedor.split(":");
            final String[] coordenada = info[0].split(",");
            final int x = Integer.parseInt(coordenada[0]);
            final int y = Integer.parseInt(coordenada[1]);
            final String[] ob = info[1].split("/");
            for (String o : ob) {
                final String[] datosObjeto = o.split("-");
                idObjetos.add(Integer.parseInt(datosObjeto[0]));
                cantidades.add(Integer.parseInt(datosObjeto[1]));
            }

            final int[] idOb = new int[idObjetos.size()];
            final int[] cantiOb = new int[cantidades.size()];

            for (int i = 0; i < idOb.length; i++) {
                idOb[i] = idObjetos.get(i);
                cantiOb[i] = cantidades.get(i);
            }
            objetos.add(new ContenedorObjetos(new Point(x, y), idOb, cantiOb));
        }
        return objetos;
    }

    public void actualizar() {

        actualizarAreasColision();
        actualizarZonaSalida();
        actualizarObjetosRecogidos();
    }

    private void actualizarAreasColision() {

        if (!areasColision.isEmpty()) {
            areasColision.clear();
        }
        for (int y = 0; y < this.ALTO; y++) {
            for (int x = 0; x < this.ANCHO; x++) {
                int puntoX = x * Constantes.LADO_SPRITE - ElementosPrincipales.jugador.getPosicionXINT() + margenX;
                int puntoY = y * Constantes.LADO_SPRITE - ElementosPrincipales.jugador.getPosicionYINT() + margenY;

                if (COLISIONES[x + y * this.ANCHO]) {
                    final Rectangle r = new Rectangle(puntoX, puntoY, Constantes.LADO_SPRITE, Constantes.LADO_SPRITE);
                    areasColision.add(r);
                }
            }
        }
    }

    private void actualizarZonaSalida() {

        int puntoX = ((int) coordenadaSalida.getX()) * Constantes.LADO_SPRITE - ElementosPrincipales.jugador.getPosicionXINT() + margenX;
        int puntoY = ((int) coordenadaSalida.getY()) * Constantes.LADO_SPRITE - ElementosPrincipales.jugador.getPosicionYINT() + margenY;

        ZonaSalida = new Rectangle(puntoX, puntoY, Constantes.LADO_SPRITE, Constantes.LADO_SPRITE);
    }

    private void actualizarObjetosRecogidos() {
        //Saber su en el mapa hay objetos por recoger
        if (!objetosMapa.isEmpty()) {
            //rectangulos que rodenan al contenedor y al jugador
            final Rectangle areaJugador = new Rectangle(ElementosPrincipales.jugador.getPosicionXINT(), ElementosPrincipales.jugador.getPosicionYINT(), Constantes.LADO_SPRITE, Constantes.LADO_SPRITE);
            for (int i = 0; i < objetosMapa.size(); i++) {
                final ContenedorObjetos contenedor = objetosMapa.get(i);
                final Rectangle posicionContenedor = new Rectangle(contenedor.getPosicion().x * Constantes.LADO_SPRITE,
                        contenedor.getPosicion().y * Constantes.LADO_SPRITE, Constantes.LADO_SPRITE / 2, Constantes.LADO_SPRITE / 2);

                if (areaJugador.intersects(posicionContenedor) && GestorControles.teclado.recoger) {
                    ElementosPrincipales.inventario.recogerObjetos(contenedor);
                    objetosMapa.get(i).setCofre();
                    GestorControles.teclado.recoger = false;
                }
            }
        }
    }

    public void dibujar(Graphics g) {

        for (int y = 0; y < this.ALTO; y++) {
            for (int x = 0; x < this.ANCHO; x++) {

                BufferedImage imagen = this.PALETA[this.SPRITES[x + y * this.ANCHO]].getImagen();

                int puntoX = x * Constantes.LADO_SPRITE - ElementosPrincipales.jugador.getPosicionXINT() + margenX;
                int puntoY = y * Constantes.LADO_SPRITE - ElementosPrincipales.jugador.getPosicionYINT() + margenY;

                DibujoOpciones.dibujarImagen(g, imagen, puntoX, puntoY);
            }
        }

        //dibujar los cofres o los contenedores de objetos
        if (!objetosMapa.isEmpty()) {
            for (ContenedorObjetos contenedorObjetos : objetosMapa) {
                final int puntoX = contenedorObjetos.getPosicion().x * Constantes.LADO_SPRITE - ElementosPrincipales.jugador.getPosicionXINT() + margenX;
                final int puntoY = contenedorObjetos.getPosicion().y * Constantes.LADO_SPRITE - ElementosPrincipales.jugador.getPosicionYINT() + margenY;

                contenedorObjetos.dibujar(g, puntoX + 8, puntoY + 8);
            }
        }

        if (!enemigos.isEmpty()) {
            for (Enemigo enemigo : enemigos) {
                final int puntoX = (int) enemigo.getPosicionX() * Constantes.LADO_SPRITE - ElementosPrincipales.jugador.getPosicionXINT() + margenX;
                final int puntoY = (int) enemigo.getPosicionY() * Constantes.LADO_SPRITE - ElementosPrincipales.jugador.getPosicionYINT() + margenY;
                enemigo.dibujar(g, puntoX, puntoY);
            }
        }
    }

    public Rectangle getBordes(final int posicionX, final int posicionY) {

        int x = margenX - posicionX + ElementosPrincipales.jugador.getAnchoJugador();
        int y = margenY - posicionY + ElementosPrincipales.jugador.getAltoJugador();

        int anchoRectangulo = this.ANCHO * Constantes.LADO_SPRITE - ElementosPrincipales.jugador.getAnchoJugador() * 2;
        int altoRectangulo = this.ALTO * Constantes.LADO_SPRITE - ElementosPrincipales.jugador.getAltoJugador() * 2;

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
