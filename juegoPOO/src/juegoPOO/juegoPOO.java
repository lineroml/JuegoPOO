/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegoPOO;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author luisf
 */
public class juegoPOO extends Canvas implements Runnable{

    private static final int ANCHO = 800;
    private static final int ALTO = 600;

    private static final String NOMBRE = "Forgotten History";

    // con la palabra volatile esta variable no puede ser utilizada al mismo timepo por los dos hilos(thread)
    private volatile static boolean running = false;
    
    private static JFrame ventana;
    private static Thread thread;
    
    private juegoPOO() {
        setPreferredSize(new Dimension(ANCHO, ALTO));

        ventana = new JFrame(NOMBRE);
        ventana.setTitle(NOMBRE);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        ventana.setLayout(new BorderLayout());
        ventana.add(this, BorderLayout.CENTER); //añadir el canvas a la ventana
        ventana.pack(); // la ventana se autorrellenara con los elementos dentro de ella
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

    public static void main(String[] args) {
        juegoPOO app = new juegoPOO();
        app.start();
    }
    
    // con synchronized los dos metodos no seran capaces de ejecutarse al mismo timepo
    private synchronized void start() {
        running = true;
        
        thread = new Thread(this, "Graficos");
        thread.start();
    }
    
    private void stop() {
        running = false;
    
        try {
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    @Override
    public void run() {
        // Este metodo sera llamado cada vez que un nuevo hilo sea creado
        while(running) {
            
        }
    }
    
}
