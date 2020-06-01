package principal.entes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import principal.Constantes;
import principal.ElementosPrincipales;
import principal.control.GestorControles;
import principal.herramientas.DibujoOpciones;
import principal.sonido.Sonido;
import principal.sprites.HojaSprites;

public class Zombie extends Enemigo {

    private static HojaSprites hojaZombie;

    private int animacion;
    private static int sonidoContador;
    private int a;
    private int d;

    Sonido ataque;

    private int anchoZombie = 16;
    private int altoZombie = 32;

    private BufferedImage imagenActual;

    public Zombie(int id, String nombre, int vidaMaxima, final String rutaLamento) {
        super(id, nombre, vidaMaxima, rutaLamento, 6 * Constantes.LADO_SPRITE);

        if (hojaZombie == null) {
            hojaZombie = new HojaSprites(Constantes.RUTA_ZOMBIE + id + ".png", Constantes.LADO_SPRITE, false);
        }

        ataque = new Sonido("Resourses/sonidos/Golpe.wav");

        animacion = 0;
        sonidoContador = 0;
        a = 0;
        d = 4;

        imagenActual = hojaZombie.getSprite(direccion, 0).getImagen();
    }

    @Override
    public void actualizar(ArrayList<Enemigo> enemigos) {
        super.actualizar(enemigos);
        if (animacion < 32767) {
            animacion++;
        } else {
            animacion = 0;
        }
        for (Enemigo enemigo : enemigos) {
            if (enemigo.getArea().intersects(ElementosPrincipales.jugador.getArea())) {
                if (sonidoContador % 300 == 0) {
                    Random r = new Random();
                    int num = r.nextInt(ataqueMax - ataqueMin) + ataqueMin;
                    ElementosPrincipales.jugador.setVida(num);
                    ataque.reproducir();
                }
                sonidoContador++;
            }
        }
        if (sonidoContador == Integer.MAX_VALUE - 1) {
            sonidoContador = 0;
        }
        animar();
    }

    @Override
    public void dibujar(final Graphics g, final int puntoX, final int puntoY) {
        super.dibujar(g, puntoX, puntoY);
        DibujoOpciones.dibujarImagen(g, imagenActual, puntoX, puntoY);
    }

    private void animar() {

        if (enMovimiento) {
            GestorControles.teclado.dance = false;
            if (animacion % 10 == 0) {
                a++;
                if (a >= 4) {
                    a = 0;
                }
            }
            switch (a) {
                case 0:
                    imagenActual = hojaZombie.getSprite(direccion, 1).getImagen();
                    break;
                case 1:
                    imagenActual = hojaZombie.getSprite(direccion, 0).getImagen();
                    break;
                case 2:
                    imagenActual = hojaZombie.getSprite(direccion, 2).getImagen();
                    break;
                case 3:
                    imagenActual = hojaZombie.getSprite(direccion, 0).getImagen();
                    break;
            }
        } else {
            imagenActual = hojaZombie.getSprite(direccion, 0).getImagen();
        }
    }

}
