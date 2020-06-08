package principal.herramientas;

import java.awt.Font;
import java.awt.image.BufferedImage;
import javax.sound.sampled.Clip;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import principal.Constantes;

/**
 *
 * @author Luis Evilla
 */
public class CargadorRecursosTest {
    
    public CargadorRecursosTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of cargarImagenCompatibleOpaca method, of class CargadorRecursos.
     */
    @Test
    public void testCargarImagenCompatibleOpaca() {
        System.out.println("cargarImagenCompatibleOpaca");
        
        String ruta = "/imagenes/hojas_Personajes/Santana.png";
        String ruta2 = "/imagenes/hojas_Personajes/SantanaDisparo.png";
        BufferedImage expResult = CargadorRecursos.cargarImagenCompatibleOpaca(ruta);
        BufferedImage result = CargadorRecursos.cargarImagenCompatibleOpaca(ruta);

        float similitud;
        similitud = ComparadorRecursos.compararImagenes(expResult, result);
        assertEquals("Error, deberia devolver la misma imagen", 100, (int) (similitud));
    
        result = CargadorRecursos.cargarImagenCompatibleOpaca(ruta2);
        similitud = ComparadorRecursos.compararImagenes(result, expResult);
        assertNotEquals("Error, las imagenes deberian ser distintas", 100, (int) (similitud));
    }

    /**
     * Test of cargarImagenCompatibleTranslucida method, of class CargadorRecursos.
     */

    @Test
    public void testCargarImagenCompatibleTranslucida() {
        System.out.println("cargarImagenCompatibleTranslucida");
        String ruta = Constantes.RUTA_ICONO_RATON;
        String ruta2 = Constantes.RUTA_ICONO_VENTANA;
        
        BufferedImage expResult = CargadorRecursos.cargarImagenCompatibleTranslucida(ruta);
        BufferedImage result = CargadorRecursos.cargarImagenCompatibleTranslucida(ruta);
        
        float similitud;
        similitud = ComparadorRecursos.compararImagenes(expResult, result);
        assertEquals("Error, deberia devolver la misma imagen", 100, (int) (similitud));
        
        result = CargadorRecursos.cargarImagenCompatibleOpaca(ruta2);
        similitud = ComparadorRecursos.compararImagenes(result, expResult);
        assertNotEquals("Error, las imagenes deberian ser distintas", 100, (int) (similitud));
    }

    /**
     * Test of leerArchivoTexto method, of class CargadorRecursos.
     */
    @Test
    public void testLeerArchivoTexto() {
        System.out.println("leerArchivoTexto");
        String ruta = "/resources/texto/texto.txt";
        
        String expResult = CargadorRecursos.leerArchivoTexto(ruta);
        String result = CargadorRecursos.leerArchivoTexto(ruta);
        assertEquals("Los textos deberian ser iguales", expResult, result);
    }

    /**
     * Test of cargarFuente method, of class CargadorRecursos.
     */
    @Test
    public void testCargarFuente() {
        System.out.println("cargarFuente");
        String ruta = "/fuentes/Crumbled-Pixels.ttf";
        
        Font expResult = CargadorRecursos.cargarFuente(ruta);
        Font result = CargadorRecursos.cargarFuente(ruta);
        assertNotNull("Error la fuente no deberia ser nula", result);
        
        boolean iguales = ComparadorRecursos.compararFuentes(expResult, result);
        assertTrue("Error las fuentes deberian ser iguales", iguales);
    }

    /**
     * Test of cargarSonido method, of class CargadorRecursos.
     */
    @Test
    public void testCargarSonido() {
        System.out.println("cargarSonido");
        String ruta = "Resourses/sonidos/Golpe.wav";
        
        Clip expResult = CargadorRecursos.cargarSonido(ruta);
        Clip result = CargadorRecursos.cargarSonido(ruta);
        assertNotNull("Error el audio no deberia ser nulo", result);
        
        boolean iguales = ComparadorRecursos.compararAudios(expResult, result);
        assertTrue("Error los audios deberian ser iguales", iguales);
    }
    
}
