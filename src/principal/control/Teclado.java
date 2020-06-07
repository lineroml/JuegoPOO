package principal.control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Gestiona la información de las 
 * teclas que se presionan en el teclado
 */
public class Teclado implements KeyListener {

    public Tecla up = new Tecla();
    public Tecla down = new Tecla();
    public Tecla left = new Tecla();
    public Tecla right = new Tecla();

    public boolean recoger = false;
    public boolean run = false;
    public boolean opciones = false;
    public boolean poder = false;
    public boolean inventario = false;
    public boolean menuPausa = false;

    public boolean ataque = false;
    
    /**
     * se lanza cada vez que se mateniene presionada una tecla 
     * @param e variable la cual permite obtener 
     * información del evento de la tecla que está siendo presionada
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                up.teclaPulsada();
                break;
            case KeyEvent.VK_S:
                down.teclaPulsada();
                break;
            case KeyEvent.VK_A:
                left.teclaPulsada();
                break;
            case KeyEvent.VK_D:
                right.teclaPulsada();
                break;
            case KeyEvent.VK_SHIFT:
                run = true;
                break;
            case KeyEvent.VK_E:
                recoger = true;
                break;
            case KeyEvent.VK_SPACE:
                ataque = true;
                break;
            case KeyEvent.VK_O:
                opciones = !opciones;
                break;
            case KeyEvent.VK_ENTER:
                poder = !poder;
                break;
            case KeyEvent.VK_I:
                inventario = !inventario;
                break;
            case KeyEvent.VK_ESCAPE:
                menuPausa = !menuPausa;
                break;
        }
    }

    /**
     * Se lanza cada vez que se deja de presionar una tecla
     * @param e variable la cual permite obtener 
     * información del evento de la que fue tecla presionada
     */
    @Override
    public void keyReleased(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                up.teclaLiberada();
                break;
            case KeyEvent.VK_S:
                down.teclaLiberada();
                break;
            case KeyEvent.VK_A:
                left.teclaLiberada();
                break;
            case KeyEvent.VK_D:
                right.teclaLiberada();
                break;
            case KeyEvent.VK_SHIFT:
                run = false;
                break;
            case KeyEvent.VK_E:
                recoger = false;
                break;
            case KeyEvent.VK_SPACE:
                ataque = false;
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
