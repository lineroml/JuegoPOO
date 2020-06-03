package principal.inventario;

import principal.inventario.armas.DesArmado;
import principal.inventario.armas.Pistola;
import principal.inventario.consumible.Consumible;

public class RegistroObjetos {

    private static int dificultad = 0;

    public static Objeto getObjeto(int idObjeto) {
        Objeto objeto = null;
        switch (idObjeto) {
            //Entre el 0 y el 499 son objetos consumibles
            //  De 500 en adelanete son armas
            // Esto anterior es por su id

            //Consumibles
            case 0:
                objeto = new Consumible(idObjeto, "Manzana roja", "Sin descripcion");
                break;
            case 1:
                objeto = new Consumible(idObjeto, "Manzana Amarilla", "Sin descripcion");
                break;
            case 2:
                objeto = new Consumible(idObjeto, "Zanahoria", "Sin descripcion");
                break;
            case 3:
                objeto = new Consumible(idObjeto, "Galleta", "Sin descripcion");
                break;
            case 4:
                objeto = new Consumible(idObjeto, "Bola dragon", "Sin descripcion");
                break;
            case 5:
                objeto = new Consumible(idObjeto, "Bola verde", "Sin descripcion");
                break;
            //Armas
            case 500:
                objeto = new Pistola(idObjeto, "Pistola", "Sin descripcion", 12 - dificultad, 20 - dificultad, false, true, 0.7);
                break;
            case 599:
                objeto = new DesArmado(idObjeto, "manos", "Sin descripcion", 1, 1, false, false, 0);
                break;
        }
        return objeto;
    }

    public static void setDificultad(final int i) {
        dificultad = i;
    }
}
