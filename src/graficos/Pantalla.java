
package graficos;

public class Pantalla {
    private final int ANCHO;
    private final int ALTO;
    
    public final int[] pixeles;

     // temporal
    private final static int SPRITE_SIZE = 32;
    private final static int SPRITE_MASK = SPRITE_SIZE-1;
    // fin temporal
    
    public Pantalla(int ANCHO, int ALTO) {
        this.ANCHO = ANCHO;
        this.ALTO = ALTO;
        
        this.pixeles = new int[ANCHO * ALTO];
    }
    
    public void limpiar() {
        for (int i = 0; i < pixeles.length; i++) {
            pixeles[i] = 0;
        }
    }
    
    // las compensaciones x e son el movimiento(x, y) del jugador
    public void mostrar(final int compensacionX, final int  compensacionY) {
        for (int y = 0; y < ALTO; y++) {
                int posicionY = y + compensacionY;
            
            if( posicionY < 0 || posicionY >= ALTO) {
                continue;
            }
            
            for (int x = 0; x < ANCHO; x ++) {
                int positionX = x + compensacionX;
            
                if( positionX < 0 || positionX >= ANCHO) {
                    continue;
                }
                // temporal
                pixeles[positionX + posicionY * ANCHO] = Sprite.asfalto.pixeles[(x & SPRITE_MASK) + (y & SPRITE_MASK) * SPRITE_SIZE];
            }
        }
    }
} 
