package principal.maquinaestado;

import java.awt.Graphics;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import principal.graficos.SuperficieDibujo;
import principal.graficos.Ventana;

/**
 *
 * @author Luis Evilla
 */
public class GestorEstadoTest {

    private GestorEstado ge;
    private SuperficieDibujo sd;
    private Ventana ventana;

    public GestorEstadoTest() {

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
     * Test of cambiarEstadoActual method, of class GestorEstado.
     */
    @Test
    public void testCambiarEstadoActual() {
        sd = new SuperficieDibujo(720, 360);
        System.out.println("Superficie creada");
        ge = new GestorEstado(sd);
        ventana = new Ventana("test", sd);
        System.out.println("Gestor creado");
        ge.cambiarEstadoActual(2);
    }

}
