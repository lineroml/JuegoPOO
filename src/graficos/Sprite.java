
package graficos;

public final class Sprite {
    private final int lado;
    
    private int x;
    private int y;
    
    public int[] pixeles;
    private final HojaSprite hoja;
    
   // coleccion Sprites
    public static final Sprite VACIO = new Sprite(32,0);
    public static Sprite ASFALTO = new Sprite(32, 0, 0, HojaSprite.DESIERTO);
    public static Sprite LOGAN = new Sprite(32, 0, 0, HojaSprite.LOGAN);
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
    
    public Sprite(final int lado, final int color) {
        this.lado = lado;
        this.hoja = null;
        
        pixeles = new int[lado * lado];
        
        for (int i = 0; i < pixeles.length; i++) {
            pixeles[i] = color;
        }
    }

    public int getLado() {
        return lado;
    }
}
