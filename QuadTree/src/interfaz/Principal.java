package interfaz;

import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import quadTree.ExceptionNodo;
import quadTree.ProcesosArbol;

public class Principal {
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	//InterfazEntrada guiEntrada=new InterfazEntrada();
                	BufferedImage img = null;
        		    img = ImageIO.read(new File("/home/juan-rios/Descargas/download.png"));
                	InterfazFigura guiEntrada=new InterfazFigura(new ProcesosArbol().imagenToArbol(img));
            		System.out.println(img.getRGB(0,0));
                	guiEntrada.setVisible(true);   
            		
            		
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
		
		
		
		
		
//			BufferedImage img = null;
//		    img = ImageIO.read(new File("C:\\Users\\Decol\\Desktop\\download.PNG"));
//		   new ProcesosArbol().arbolToImagen(new ProcesosArbol().imagenToArbol(img));
//			// TODO Auto-generated catch block
//			e.printStackTrace();
	}
	
}
