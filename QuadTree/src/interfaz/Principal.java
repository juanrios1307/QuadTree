package interfaz;

import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import quadTree.*;

public class Principal {
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	InterfazEntrada window = new InterfazEntrada();
                	
                	window.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
	}
	
}
