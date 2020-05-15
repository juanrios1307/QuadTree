package interfaz;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

import quadTree.*;

public class InterfazFigura extends JFrame {
	private JPanel contentPane;

    QuadTree arbol;
 
    public InterfazFigura(QuadTree arbol) {
    	 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         contentPane = new JPanel();
         contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
         setContentPane(contentPane);
         contentPane.setLayout(null);
         setBounds(400,100,600,625);
         
         this.arbol=arbol;

    }
    
    public void paint (Graphics g){
        ProcesosArbol figura=new ProcesosArbol(contentPane.getGraphics());
        try {
			figura.arbolToImagen(arbol);
		} catch (ExceptionNodo e) {
			e.printStackTrace();
		}        
    }
    
	
}
