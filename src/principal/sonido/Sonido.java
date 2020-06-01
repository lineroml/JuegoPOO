package principal.sonido;

import javax.sound.sampled.Clip;
import principal.herramientas.CargadorRecursos;

public class Sonido {

    private final Clip sonido;

    public Sonido(final String ruta) {
        sonido = CargadorRecursos.cargarSonido(ruta);
    }

    public void reproducir() {
        sonido.stop();
        //Liberar memoria
        sonido.flush();
        //Ubicar el sonido al principio
        sonido.setMicrosecondPosition(0);
        sonido.start();
    }

    public void reproducirBucle() {
        sonido.stop();
        //Liberar memoria
        sonido.flush();
        //Ubicar el sonido al principio
        sonido.setMicrosecondPosition(0);
        //Repetir infinitamente
        sonido.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void detenerSonido() {
        sonido.stop();
        sonido.flush();
    }

    public Long getDuracion() {
        return sonido.getMicrosecondLength();
    }

}
