package principal.entes;

public class RegistroEnemigos {

    private static int dificultad = 1;

    public static Enemigo getEnemigo(final int idEnemigo) {
        Enemigo enemigo = null;

        switch (idEnemigo) {
            case 1:
                enemigo = new Zombie(idEnemigo, "Zombie", 40 * dificultad, "Resourses/sonidos/Zombie.wav");
                break;
            default:
                System.out.println("No existe el enemigo");
                break;
        }

        return enemigo;
    }

    public void setDificultad(int i) {
        dificultad = i;
    }
}
