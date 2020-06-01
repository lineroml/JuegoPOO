package principal.inventario.consumible;

import principal.Constantes;
import principal.inventario.Objeto;
import principal.sprites.HojaSprites;
import principal.sprites.Sprite;

public class Consumible extends Objeto {

    public static HojaSprites hojaConsumibles = new HojaSprites(Constantes.RUTA_OBJETOS, 32, false);

    public Consumible(int id, String nombre, String descripcion) {
        super(id, nombre, descripcion);
    }

    public Consumible(int id, String nombre, String descripcion, int cantidad) {
        super(id, nombre, descripcion, cantidad);
    }

    @Override
    public Sprite getSprite() {
        return hojaConsumibles.getSprite(id);
    }

}
