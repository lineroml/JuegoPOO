
package control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public final class Teclado implements KeyListener {
    
    private final static byte NUMEROTECLAS = 120;
    private final boolean[] teclas = new boolean[NUMEROTECLAS];
    
    public boolean arriba;
    public boolean abajo;
    public boolean izquierda;
    public boolean derecha;
    
    public void actualizar() {
        arriba = teclas[KeyEvent.VK_W];
        abajo = teclas[KeyEvent.VK_S];
        izquierda = teclas[KeyEvent.VK_A];
        derecha = teclas[KeyEvent.VK_D];
        
        /** si queremos movernos con los viejos controles de telcas: flechas arriba, abajo, izquierda, derehca
         * solo descomentamos este bloque codigo y comentamos el de arriba
         
        up = teclas[KeyEvent.VK_UP];
        down = teclas[KeyEvent.VK_DOWN];
        left = teclas[KeyEvent.VK_LEFT];
        rigth = teclas[KeyEvent.VK_RIGTH]; 
        */
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        teclas[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        teclas[e.getKeyCode()] = false; 
    }
    
}
