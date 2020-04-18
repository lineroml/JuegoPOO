
package mapa.cuadro;

import graficos.Pantalla;
import graficos.Sprite;

public class Cuadro {
    public int ancho;
    public int alto;
    
    public static final int LADO = 32;
    
    public Sprite sprite;
    
    // coleccion de cuadros
    public static final Cuadro VACIO = new CuadroVacio(Sprite.VACIO);
    public static final Cuadro ASFALTO = new CuadroAsfalto(Sprite.ASFALTO);
    
    // fin de la coleccion
    
    public Cuadro(Sprite sprite) {
        this.sprite = sprite;
    }
    
    public void mostrar(int x, int y, Pantalla pantalla) {
        pantalla.mostrarCuadro(x << 5, y << 5, this);
    }
    
    public boolean solid() {
        return false;
    }
}
