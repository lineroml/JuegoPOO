package principal.entes;

public class RegistroEnemigos {

    private static int dificultad = 0;

    public static Enemigo getEnemigo(final int idEnemigo) {
        Enemigo enemigo = null;

        switch (idEnemigo) {
            case 1:
                enemigo = new Guardian(idEnemigo, "Guardian", 40 + dificultad, "Resources/sonidos/Enemigo.wav");
                break;
            default:
                System.out.println("No existe el enemigo");
                break;
        }

        return enemigo;
    }

    public static void setDificultad(int i) {
        dificultad = i;
    }
}
