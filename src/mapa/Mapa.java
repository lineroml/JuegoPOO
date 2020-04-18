
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
    
    public void mostrar(int compensacionX, int compensacionY, Pantalla pantalla) {
        
        pantalla.establecerDiferencia(compensacionX, compensacionY);

        // norte, sur, este, oeste
        int n = compensacionY >> 5; // "/32" bit shifting
        int s = (compensacionY + pantalla.getALTO() + Cuadro.LADO) >> 5;
        int o = compensacionX >> 5; // "/32" bit shifting
        int e = (compensacionX + pantalla.getANCHO() + Cuadro.LADO) >> 5;
    
        for(int y = n; y < s; y++) {
            for (int x = o; x < e; x++) {
                getCuadro(x, y).mostrar(x,y, pantalla);
            }
        }
    }
    
    public Cuadro getCuadro(final int x, final int y) {
        if (x < 0 || x >= ancho || y < 0 || y >= alto) {
            return Cuadro.VACIO;
        }
        switch (cuadros[x + y * ancho]) {
            case 0:
                return Cuadro.ASFALTO;
            case 1:
            case 2:
            case 3:
                
            default:
                return Cuadro.VACIO;
        }
    }
}
