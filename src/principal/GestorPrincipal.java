package principal;

import java.awt.Graphics;
import principal.graficos.SuperficieDibujo;
import principal.graficos.Ventana;
import principal.maquinaEstado.GestorEstado;

public class GestorPrincipal {
    private boolean enFuncionamiento = false;
    private String TITULO;
    private int ancho;
    private int alto;
    
    private SuperficieDibujo sd;
    private Ventana ventana;
    private GestorEstado ge;
    
    private GestorPrincipal(final String TITULo, final int ancho, final int alto) {
        this.TITULO = TITULO;
        this.alto = alto;
        this.ancho = ancho;
    }
    
    public static void main(String[] args) {
        GestorPrincipal gp = new GestorPrincipal("ForgottenHistory", 640, 360);
        
        gp.iniciarJuego();
        gp.inciciarBuclePrincipal();
    }

    private void iniciarJuego() {
        enFuncionamiento = true;
        inicializar();
    }
    
    private void inicializar() {
        sd = new SuperficieDibujo();
        ventana = new Ventana(TITULO, sd);
        ge = new GestorEstado();
    }

    private void inciciarBuclePrincipal() {
        int aps = 0;
        int fps = 0;
        
        final int NS_POR_SEGUNDO = 1000000000;
        final int APS_OBJETIVO = 60;
        final double NS_POR_ACTUALIZACION = NS_POR_SEGUNDO / APS_OBJETIVO;
        
        long referenciaActualizacion = System.nanoTime();
        long referenciaContador = System.nanoTime();
        
        double tiempoTranscurrido;
        double delta = 0;
        
        while (enFuncionamiento) {
            final long inicioBucle = System.nanoTime();
            
            tiempoTranscurrido = inicioBucle - referenciaActualizacion;
            referenciaActualizacion = inicioBucle;
            
            delta += tiempoTranscurrido / NS_POR_ACTUALIZACION;
            
            while (delta >= 1) {
                actualizar();
                aps++;
                delta--;
            }
            
            dibujar();
            fps++;
            
            if (System.nanoTime() - referenciaContador > NS_POR_SEGUNDO) {
                System.out.println("FPS: " + fps + " APS: " + aps);
                
                aps = 0;
                fps = 0;
                referenciaContador = System.nanoTime();
            }
        }
    }
    
    private void actualizar() {
        //ge.actualizar();
    }
    
    private void dibujar() {
        //ge.dibujar(g);
    }
}
