package principal.inventario.poderes;

import java.awt.Rectangle;
import java.util.ArrayList;
import principal.entes.Jugador;

public class DesArmado extends Cetro {

    public DesArmado(int id, String nombre, String descripcion, int ataqueMin, int ataqueMax, boolean automatica, boolean penetrante, double ataquePorSegundo) {
        super(id, nombre, descripcion, ataqueMin, ataqueMax, automatica, penetrante, ataquePorSegundo, "/sonidos/Pelea.wav");
    }

    @Override
    public ArrayList<Rectangle> getAlcance(final Jugador jugador) {

        return null;
    }

}
