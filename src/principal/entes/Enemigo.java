package principal.entes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import principal.Constantes;
import principal.ElementosPrincipales;
import principal.herramientas.CalcularDistancia;
import principal.herramientas.DibujoOpciones;
import principal.sonido.Sonido;

public abstract class Enemigo {

    private final Sonido lamento;
    private final long duracionLamento;
    private long lamentoSiguinete = 0;

    private final int id;
    private double posicionX;
    private double posicionY;
    private final String nombre;
    private final int vidaMaxima;
    private int vidaActual;
    private final double radioDistancia;

    protected boolean enMovimiento;
    protected int direccion;

    protected double velocidad = 0.5;

    protected int ataqueMin;
    protected int ataqueMax;

    public Enemigo(int id, String nombre, int vidaMaxima, final String rutaLamento, final double radioDistancia) {
        this.id = id;
        this.nombre = nombre;
        this.vidaMaxima = vidaMaxima;
        this.vidaActual = vidaMaxima;
        this.posicionX = 0;
        this.posicionY = 0;
        this.radioDistancia = radioDistancia;

        this.lamento = new Sonido(rutaLamento);
        this.duracionLamento = lamento.getDuracion();

        enMovimiento = false;
        direccion = 0;

        ataqueMin = 20;
        ataqueMax = 60;
    }

    public void actualizar(ArrayList<Enemigo> enemigos) {
        if (lamentoSiguinete > 0) {
            lamentoSiguinete -= 1000000 / 60;
        }
        double distancia = CalcularDistancia.getDistanciaEntrePuntos(new Point(ElementosPrincipales.jugador.getPosicionXINT(), ElementosPrincipales.jugador.getPosicionYINT()),
                new Point((int) this.getPosicionX(), (int) this.getPosicionY()));
        if (distancia < radioDistancia) {
            moverANodoSiguiente(enemigos);
        } else {
            enMovimiento = false;
        }
    }

    private void moverANodoSiguiente(ArrayList<Enemigo> enemigos) {
        int ultimaDireccion = -1;
        //Mirar si un enemigo no atasque otro enemigo
        for (Enemigo enemigo : enemigos) {
            if (enemigo.equals(this)) {
                continue;
            }
            if (enemigo.getArea().intersects(this.getAreaFutura())
                    || (enemigo.getArea().intersects(ElementosPrincipales.jugador.getArea()) && this.getAreaFutura().intersects(ElementosPrincipales.jugador.getArea()))) {
                ultimaDireccion = direccion;
                switch (ultimaDireccion) {
                    case 0:
                        posicionY -= velocidad;
                        break;
                    case 1:
                        posicionY += velocidad;
                        break;
                    case 2:
                        posicionX -= velocidad;
                        break;
                    case 3:
                        posicionX += velocidad;
                        break;
                }
            }
        }
        int xNodoSigueinte = ElementosPrincipales.jugador.getPosicionXINT();
        int yNodoSigueinte = ElementosPrincipales.jugador.getPosicionYINT();

        if (posicionX < xNodoSigueinte && !enColisionArriba(-1)) {
            posicionX += velocidad;
            direccion = 2;
        } else if (posicionX > xNodoSigueinte && !enColisionAbajo(1)) {
            posicionX -= velocidad;
            direccion = 3;
        } else if (posicionY < yNodoSigueinte && !enColisionIzquierda(-1)) {
            posicionY += velocidad;
            direccion = 0;
        } else if (posicionY > yNodoSigueinte && !enColisionDerecha(1)) {
            posicionY -= velocidad;
            direccion = 1;
        }
        enMovimiento = true;
    }

    private boolean enColisionArriba(int velocidadY) {

        for (int r = 0; r < ElementosPrincipales.mapa.getAreasColisionJugador().size(); r++) {
            final Rectangle area = ElementosPrincipales.mapa.getAreasColisionJugador().get(r);

            int origenX = area.x;
            int origenY = area.y + velocidadY * (int) (velocidad + 0.6) + 3 * (int) (velocidad + 0.6);

            Rectangle areaFutura = new Rectangle(origenX, origenY, area.width, area.height);

            Rectangle LIMITE_ARRIBA = new Rectangle(getAreaDisparo().x, getAreaDisparo().y + 16, 16, 1);
            if (LIMITE_ARRIBA.intersects(areaFutura)) {
                return true;
            }
        }
        return false;
    }

    private boolean enColisionAbajo(int velocidadY) {

        for (int r = 0; r < ElementosPrincipales.mapa.getAreasColisionJugador().size(); r++) {
            final Rectangle area = ElementosPrincipales.mapa.getAreasColisionJugador().get(r);

            int origenX = area.x;
            int origenY = area.y + velocidadY * (int) (velocidad + 0.6) - 3 * (int) (velocidad + 0.6);

            Rectangle areaFutura = new Rectangle(origenX, origenY, area.width, area.height);

            Rectangle LIMITE_ABAJO = new Rectangle(getAreaDisparo().x, getAreaDisparo().y + Constantes.LADO_SPRITE, 16, 1);
            if (LIMITE_ABAJO.intersects(areaFutura)) {
                return true;
            }
        }
        return false;
    }

    private boolean enColisionIzquierda(int velocidadX) {

        for (int r = 0; r < ElementosPrincipales.mapa.getAreasColisionJugador().size(); r++) {
            final Rectangle area = ElementosPrincipales.mapa.getAreasColisionJugador().get(r);

            int origenX = area.x + velocidadX * (int) (velocidad + 0.6) + 3 * (int) (velocidad + 0.6);
            int origenY = area.y;

            Rectangle areaFutura = new Rectangle(origenX, origenY, area.width, area.height);

            Rectangle LIMITE_IZQUIERDA = new Rectangle(getAreaDisparo().x, getAreaDisparo().y + 16, 1, Constantes.LADO_SPRITE / 2);
            if (LIMITE_IZQUIERDA.intersects(areaFutura)) {
                return true;
            }
        }
        return false;
    }

    private boolean enColisionDerecha(int velocidadX) {

        for (int r = 0; r < ElementosPrincipales.mapa.getAreasColisionJugador().size(); r++) {
            final Rectangle area = ElementosPrincipales.mapa.getAreasColisionJugador().get(r);

            int origenX = area.x + velocidadX * (int) (velocidad + 0.6) - 3 * (int) (velocidad + 0.6);
            int origenY = area.y;

            Rectangle areaFutura = new Rectangle(origenX, origenY, area.width, area.height);

            Rectangle LIMITE_DERECHA = new Rectangle(getAreaDisparo().x + 16, getAreaDisparo().y + 16, 1, Constantes.LADO_SPRITE / 2);
            if (LIMITE_DERECHA.intersects(areaFutura)) {
                return true;
            }
        }
        return false;
    }

    public void dibujar(final Graphics g, final int puntoX, final int puntoY) {
        if (vidaActual > 0) {
            dibujarBarraVida(g, puntoX, puntoY);
//            DibujoOpciones.dibujarRectBorde(g, getArea(), Color.RED);
//            DibujoOpciones.dibujarRectBorde(g, getAreaFutura(), Color.RED);
//            DibujoOpciones.dibujarRectBorde(g, getAreaDisparo(), Color.RED);
//            DibujoOpciones.dibujarRectBorde(g, new Rectangle(getAreaDisparo().x, getAreaDisparo().y + 16, 16, 1), Color.RED);
//            DibujoOpciones.dibujarRectBorde(g, new Rectangle(getAreaDisparo().x, getAreaDisparo().y + Constantes.LADO_SPRITE, 16, 1), Color.RED);
//            DibujoOpciones.dibujarRectBorde(g, new Rectangle(getAreaDisparo().x, getAreaDisparo().y + 16, 1, Constantes.LADO_SPRITE / 2), Color.RED);
//            DibujoOpciones.dibujarRectBorde(g, new Rectangle(getAreaDisparo().x + 16, getAreaDisparo().y + 16, 1, Constantes.LADO_SPRITE / 2), Color.RED);
//            dibujarDistancia(g, puntoX, puntoY);
        }
    }

    private void dibujarBarraVida(final Graphics g, final int puntoX, final int puntoY) {
        DibujoOpciones.dibujarRectRelleno(g, puntoX + 4, puntoY - 5, (Constantes.LADO_SPRITE - 8) * (int) vidaActual / vidaMaxima, 2, Color.red);
    }

    private void dibujarDistancia(final Graphics g, final int puntoX, final int puntoY) {
        Point puntojugador = new Point(ElementosPrincipales.jugador.getPosicionXINT(),
                ElementosPrincipales.jugador.getPosicionYINT());
        Point puntoEnemigo = new Point((int) posicionX, (int) posicionY);

        double distancia = CalcularDistancia.getDistanciaEntrePuntos(puntojugador, puntoEnemigo);
        DibujoOpciones.dibujarString(g, String.format("%.2f", distancia), puntoX, puntoY - 8);
    }

    public void quitarVida(float ataqueDado) {
        //Reproducir Sonido
        if (lamentoSiguinete <= 0) {
            lamento.reproducir();
            lamentoSiguinete = duracionLamento * 3;
        }

        if (vidaActual - ataqueDado <= 0) {
            vidaActual = 0;
        } else {
            vidaActual -= ataqueDado;
        }
    }

    public void setPosicion(final double posicionX, final double posicionY) {
        this.posicionX = posicionX;
        this.posicionY = posicionY;
    }

    public double getPosicionX() {
        return posicionX;
    }

    public double getPosicionY() {
        return posicionY;
    }

    public int getId() {
        return id;
    }

    public int getVidaActual() {
        return vidaActual;
    }

    public Rectangle getArea() {
        final int puntoX = (int) posicionX - ElementosPrincipales.jugador.getPosicionXINT() + Constantes.MARGEN_X;
        final int puntoY = (int) posicionY - ElementosPrincipales.jugador.getPosicionYINT() + Constantes.MARGEN_Y;
        return new Rectangle(puntoX + 8, puntoY, Constantes.LADO_SPRITE / 2, Constantes.LADO_SPRITE);
    }

    public Rectangle getAreaFutura() {
        return new Rectangle(getArea().x - 6, getArea().y - 6, getArea().width + 12, getArea().height + 10);
    }

    public Rectangle getAreaDisparo() {
        return new Rectangle(getArea().x, getArea().y, getArea().width, getArea().height);
    }

}
