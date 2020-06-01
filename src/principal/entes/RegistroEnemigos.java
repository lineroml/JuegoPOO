package principal.entes;

public class RegistroEnemigos {

    public static Enemigo getEnemigo(final int idEnemigo) {
        Enemigo enemigo = null;

        switch (idEnemigo) {
            case 1:
                enemigo = new Zombie(idEnemigo, "Zombie", 80, "Resourses/sonidos/Zombie.wav");
                break;
            default:
                System.out.println("No existe el enemigo");
                break;
        }

        return enemigo;
    }
}
