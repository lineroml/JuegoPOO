
package mapa;

import graficos.Pantalla;
import mapa.cuadro.Cuadro;


public class Mapa {
    protected int ancho;
    protected int alto;
    
    protected int[] cuadros;

    public Mapa(int ancho, int alto) {
        this.ancho = ancho;
        this.alto = alto;
    
        this.cuadros = new int[ancho * alto];
        generarMapa();
    }
    
    public Mapa(String ruta) {
        cargarMapa(ruta);
    }
    
    // genera un mapa aleatorio
    protected void generarMapa() {
        
    }
    
    // carga un mapa ya predefinido
    public void cargarMapa(String ruta) {
        
    }
    
    public void actualizar() {
        
    }
    
    public void dibujar(int compensacionX, int compensacionY, Pantalla pantalla) {
        // norte, sur, este, oeste
        int n = compensacionY >> 5; // "/32" bit shifting
        int s = (compensacionY + pantalla.getALTO());
        int o = compensacionX >> 5; // "/32" bit shifting
        int e = (compensacionX + pantalla.getANCHO())/32;
    }
    
    public Cuadro getCuadro(final int x, final int y) {
        switch (cuadros[x + y * ancho]) {
            case 0:
                return Cuadro.ASFALTO;
            default:
                return null;
        }
    }
}
