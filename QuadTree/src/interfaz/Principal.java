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
                	//InterfazEntrada guiEntrada=new InterfazEntrada();
                	BufferedImage img = null;
        		    img = ImageIO.read(new File("/home/juan-rios/git/QuadTree/QuadTree/images/download5.png" ));
        		    
        		    Procesos process=new Procesos();
        		 // ProcesosArbol process=new ProcesosArbol();
        		    long inicio=System.currentTimeMillis();
        		    
        		    
        		    QuadTree arbol=process.imagenToArbol(img);
        		   
        		    long fin=System.currentTimeMillis();
                	
                	System.out.println("Tiempo: "+(fin-inicio));
        		    
                	System.out.println("Hojas:"+arbol.cantHojas());
                	
                	InterfazFigura guiEntrada=new InterfazFigura(process.arbolToImagen(arbol));
                	
                	
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
