
package graficos;

public final class Sprite {
    private final int lado;
    
    private int x;
    private int y;
    
    public int[] pixeles;
    private final HojaSprite hoja;
    
   // coleccion Sprites
    public static Sprite asfalto = new Sprite(32, 0, 0, HojaSprite.desierto);
    
   //fin de la coleccion
    
    public Sprite(final int lado, final int col, final int fil, final HojaSprite hoja) {
        this.lado = lado;
        
        this.pixeles = new int[lado * lado];
        
        this.x = col * lado;
        this.y = fil * lado;
        this.hoja = hoja;
        
        for (int y = 0; y < lado; y++) {
            for (int x = 0; x < lado; x++) {
                pixeles[x + y * lado] = hoja.pixeles[(x + this.x) + (y + this.y) * hoja.getANCHO()];
            }
        }
    }
}
