package interfaz;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

import quadTree.ProcesoArbol;

public class InterfazFigura extends JFrame {
	private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    InterfazFigura frame = new InterfazFigura();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

 
    public InterfazFigura() {
    	 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setBounds(100, 100, 450, 300);
         contentPane = new JPanel();
         contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
         setContentPane(contentPane);
         contentPane.setLayout(null);
         setBounds(0,0,800,600);
        
       /*;*/
    }
    
    public void paint (Graphics g)
    {
  
        ProcesoArbol figura=new ProcesoArbol(contentPane.getGraphics());
        figura.dividir(100, 100, 80);
        figura.dividir(100, 100, 40);
        
        figura.pintar(100, 100, 20, Color.green);
        figura.dividir(120, 100, 20);
        
        figura.pintar(120, 100, 10, Color.red);
        figura.pintar(130, 100, 10, Color.black);
        figura.pintar(130, 110, 10, Color.green);
        figura.pintar(120, 110, 10, Color.pink);
        
        figura.pintar(120, 120, 20, Color.red);
        figura.pintar(100, 120, 20, Color.PINK);
        
        figura.pintar(140, 100, 40, Color.pink);
        
        figura.dividir(140, 140, 40);
        figura.pintar(140, 140, 20, Color.green);
        figura.pintar(160, 140, 20, Color.red);
        figura.pintar(160, 160, 20, Color.green);
        figura.pintar(140, 160, 20, Color.red);
        
        figura.pintar(100, 140, 40, Color.CYAN);
        
      
    }
    
	
}
