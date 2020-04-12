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
public class juegoPOO extends Canvas{

    private static final int ANCHO = 800;
    private static final int ALTO = 600;

    private static final String NOMBRE = "Forgotten History";

    private static JFrame ventana;

    public juegoPOO() {
        ventana.setPreferredSize(new Dimension(ANCHO, ALTO));

        ventana.setTitle(NOMBRE);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        ventana.setLayout(new BorderLayout());
        ventana.add(this, BorderLayout.CENTER);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

    public static void main(String[] args) {
        juegoPOO app = new juegoPOO();
    }
}
