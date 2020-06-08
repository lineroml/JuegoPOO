package principal.inventario.elementosMujeres;

import principal.Constantes;
import principal.GestorPrincipal;
import principal.inventario.Objeto;
import principal.maquinaestado.estado.logros.Mujer;
import principal.sprites.HojaSprites;
import principal.sprites.Sprite;

public class Elemento extends Objeto {

    public static HojaSprites hojaConsumibles = new HojaSprites(Constantes.RUTA_OBJETOS, 32, false);

    public Elemento(int id, String nombre, String descripcion) {
        super(id, nombre, descripcion);
    }

    public void asignarMujer(final Mujer mujer) {
        if (nombre.equals("Rosalind Franklin")) {
            mujer.setImagenBiografia(Constantes.IMAGENFONDO);
        }
        if (nombre.equals("Jocelyn Bell")) {
            mujer.setImagenBiografia(Constantes.IMAGENFONDO);
        }
        if (nombre.equals("Equipo Computadoras Humanas")) {
            mujer.setImagenBiografia(Constantes.IMAGENFONDO);
        }
        if (nombre.equals("Ada Lovelace")) {
            mujer.setImagenBiografia(Constantes.BIOGRAFIAADA_LOVELACE);
        }
        if (nombre.equals("Huelga en Islandia")) {
            mujer.setImagenBiografia(Constantes.IMAGENFONDO);
        }
       
    }

    public void cambiarEstado() {
        GestorPrincipal.ge.cambiarEstadoActual(7);
    }

    @Override
    public Sprite getSprite() {
        return hojaConsumibles.getSprite(id);
    }
}
