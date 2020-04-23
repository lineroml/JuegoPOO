//Que estado estamos y que se dibuje a si mismo (que mapa estamos)
package principal.maquinaestado;

import java.awt.Graphics;
import principal.maquinaestado.estado.juego.GestorJuego;

public class GestorEstado {

    private EstadoJuego[] estados;
    private EstadoJuego estadoActual;
    
    public GestorEstado(){
        
        iniciarEstados();
        iniciarEstadoActual();
    }

    private void iniciarEstados() {
        
        estados = new EstadoJuego[1];
        estados[0] = new GestorJuego();
        //Añadir e iniciar los demas estados a medida que los creemos
    }

    private void iniciarEstadoActual() {
        
        estadoActual = estados[0];
    }
    
    public void actualizar(){
        
        estadoActual.actualizar();
    }
    
    public void dibujar(final Graphics g){
        
        estadoActual.dibujar(g);
    }
    
    public void cambiarEstadoActual(final int nuevoEstado){
        
        estadoActual = estados[nuevoEstado];
    }
    
    public EstadoJuego getEstadoActual(){
        
        return estadoActual;
    }
}
