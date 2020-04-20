
package principal.herramientas;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class CargadorRecursos {
    public static BufferedImage cargarImagenCompatible(final String ruta) {
        Image imagen = null;
    
        try {
            imagen = ImageIO.read(ClassLoader.class.getResource(ruta));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        /*
        obtener la mejor configuracion posible para la BufferedImage, para la tarjeta grafica y monitor
        en los cuales se ejecuta el juego, para evitar problemas de incompatibilidad
        **/
        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
                .getDefaultConfiguration();
        
        // Creamos una imagen vacia, con el mismo tamaño de la imagen que cargamos
        BufferedImage imagenAcelerada = gc.createCompatibleImage(imagen.getWidth(null), imagen.getHeight(null));
        
        Graphics g = imagenAcelerada.getGraphics();
        
        return null; 
    }
}
