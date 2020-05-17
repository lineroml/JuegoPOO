package principal.maquinaestado;

import java.awt.Graphics;

public interface EstadoJuego {

    void actualizar();

    //Con lo que se va a dibujar el graphics
    void dibujar(final Graphics g);
}
