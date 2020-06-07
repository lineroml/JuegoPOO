package principal.sonido;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import principal.Constantes;
import principal.herramientas.CargadorRecursos;

public final class Sonido {

    private final Clip sonido;
    public static float volumen = Constantes.VOLUMEN;

    public Sonido(final String ruta) {
        sonido = CargadorRecursos.cargarSonido(ruta);
        setVolumen(volumen);
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

    public void setVolumen(double vol) {
        FloatControl gain = (FloatControl) sonido.getControl(FloatControl.Type.MASTER_GAIN);
        float dB = (float) (Math.log(vol) / Math.log(10) * 20);
        gain.setValue(dB);
    }

    public void detenerSonido() {
        sonido.stop();
        sonido.flush();
    }

    public Long getDuracion() {
        return sonido.getMicrosecondLength();
    }

    public float getVolumen() {
        return volumen;
    }

    public void aumentarVolumen(float volumen) {
        if (volumen < 0) {
            Sonido.volumen = 0;
        } else {
            Sonido.volumen = volumen;
        }
        setVolumen(volumen);
    }

}
