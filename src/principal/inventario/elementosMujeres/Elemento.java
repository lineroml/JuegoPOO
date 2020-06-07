package principal.inventario.elementosMujeres;

import principal.Constantes;
import principal.inventario.Objeto;
import principal.sprites.HojaSprites;
import principal.sprites.Sprite;

public class Elemento extends Objeto {

    public static HojaSprites hojaConsumibles = new HojaSprites(Constantes.RUTA_OBJETOS, 32, false);

    public Elemento(int id, String nombre, String descripcion) {
        super(id, nombre, descripcion);
    }

    @Override
    public Sprite getSprite() {
        return hojaConsumibles.getSprite(id);
    }
}
