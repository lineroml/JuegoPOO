package principal.inventario.poderes;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;
import principal.Constantes;
import principal.entes.Enemigo;
import principal.entes.Jugador;
import principal.inventario.Objeto;
import principal.sonido.GestorSonido;
import principal.sprites.HojaSprites;
import principal.sprites.Sprite;

public abstract class Cetro extends Objeto {

    public static HojaSprites hojaArmas = new HojaSprites(Constantes.RUTA_ARMAS, 32, false);

    public GestorSonido disparo;

    protected int ataqueMin;
    protected int ataqueMax;

    protected boolean automatica;
    //Saber si solo puede mata a un enemigo con una bala o a mas
    protected boolean penetrante;
    protected double ataquePorSegundo;
    //Indica cuantas actualizaciones por segundo deben pasara para poder disparar de nuevo
    protected int tiempoProximoAtaque;

    public Cetro(int id, String nombre, String descripcion, int ataqueMin, int ataqueMax, final boolean automatica, final boolean penetrante,
            final double ataquePorSegundo, final String rutaDisparo) {
        super(id, nombre, descripcion);

        this.ataqueMin = ataqueMin;
        this.ataqueMax = ataqueMax;
        this.automatica = automatica;
        this.penetrante = penetrante;
        this.ataquePorSegundo = ataquePorSegundo;
        tiempoProximoAtaque = 0;

        disparo = new GestorSonido(rutaDisparo);
    }

    public abstract ArrayList<Rectangle> getAlcance(final Jugador jugador);

    public void actualizar() {
        if (tiempoProximoAtaque > 0) {
            tiempoProximoAtaque--;
        }
    }

    public void atacar(final Enemigo enemigo) {
        if (tiempoProximoAtaque > 0) {
            return;
        }
        tiempoProximoAtaque = (int) (ataquePorSegundo * 60);

        float ataqueAtual = getAtaqueMedio();

        enemigo.quitarVida(ataqueAtual);
    }

    @Override
    public Sprite getSprite() {
        return hojaArmas.getSprite(id - 500);
    }

    protected int getAtaqueMedio() {
        Random r = new Random();
        return r.nextInt(ataqueMax - ataqueMin) + ataqueMin;
    }

    public boolean isAutomatica() {
        return automatica;
    }

    public boolean isPenetrante() {
        return penetrante;
    }

    public void sonidoDisparo() {
        disparo.reproducir();
    }

}
