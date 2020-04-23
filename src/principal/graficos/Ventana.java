
package principal.graficos;

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class Ventana extends JFrame{
    private final String TITULO;

    public Ventana(final String TITULO, final SuperficieDibujo sd) {
        this.TITULO = TITULO;
    
        configurarVetana(sd);
    }

    private void configurarVetana(final SuperficieDibujo sd) {
        setTitle(TITULO);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        //setIconImage(icono.getImage()); // icono de la ventana
        setLayout(new BorderLayout());
        add(sd, BorderLayout.CENTER); //añadir el canvas a la ventana
        //setUndecorated(true);
        pack(); // la ventana se autorrellenara con los elementos dentro de ella
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
