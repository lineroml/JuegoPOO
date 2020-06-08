package principal.sonido;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import principal.Constantes;
import principal.herramientas.CargadorRecursos;

public final class GestorSonido {

    private final Clip sonido;
    public static float volumenMusica = Constantes.VOLUMENMUSICA;
    public static float volumenJuego = Constantes.VOLUMENJUEGO;
    public static boolean musica = true;

    public GestorSonido(final String ruta) {
        sonido = CargadorRecursos.cargarSonido(ruta);
        setVolumen(volumenMusica);
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
        if (dB < (-80)) {
            dB = (float) (-80);
            if (dB > 6) {
                dB = (float) (6);
            }
        }
        
        gain.setValue(dB);
    }

    public void detenerSonido() {
        sonido.stop();
        sonido.flush();
    }

    public Long getDuracion() {
        return sonido.getMicrosecondLength();
    }

    public float getVolumenMusica() {
        return volumenMusica;
    }

    public float getVolumenJuego() {
        return volumenJuego;
    }

    public void aumentarVolumenMusica(float volumen) {
        if (volumen < 0) {
            GestorSonido.volumenMusica = 0;
        } else {
            GestorSonido.volumenMusica = volumen;
        }
        setVolumen(GestorSonido.volumenMusica);
    }

    public void aumentarVolumenJuego(float volumen) {
        if (volumen < 0) {
            GestorSonido.volumenJuego = 0;
        } else {
            GestorSonido.volumenJuego = volumen;
        }
        setVolumen(GestorSonido.volumenJuego);
    }
}
