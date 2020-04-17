
package graficos;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class HojaSprite {
    private final int ANCHO;
    private final int ALTO;
    public final int[] pixeles;
    
    // collecion hojas de sprites
        public static HojaSprite DESIERTO = new HojaSprite("/texturas/desierto.png", 320, 320);
        public static HojaSprite LOGAN = new HojaSprite("/personajes/logan.png", 320, 320);
    // fin de la coleccion   
        
    public HojaSprite(final String ruta, final int ANCHO, final int ALTO) {
        this.ANCHO = ANCHO;
        this.ALTO = ALTO;
        this.pixeles = new int[ANCHO * ALTO];
        
        try {
            BufferedImage image = ImageIO.read(HojaSprite.class.getResource(ruta));
            image.getRGB(0, 0, ANCHO, ALTO, pixeles, 0, ANCHO);
        } catch (Exception e) {
            Logger.getLogger(HojaSprite.class.getName()).log(Level.SEVERE, null, e);
        }
    }
            
    public int getANCHO() {
        return ANCHO;
    }
}
