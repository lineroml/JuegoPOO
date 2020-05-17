package principal.maquinaestado.estado.menujuego;

import java.awt.Graphics;
import principal.maquinaestado.EstadoJuego;

public class GestorMenu implements EstadoJuego {

    private final FormaMenu inventario;

    public GestorMenu() {

        inventario = new FormaMenu();
    }

    @Override
    public void actualizar() {

    }

    @Override
    public void dibujar(final Graphics g) {

        inventario.dibujar(g);
    }

}
