package interfaz;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.swing.border.*;

import quadTree.*;

public class InterfazFigura extends JFrame {
	private JPanel contentPane;

    BufferedImage img;
 
    public InterfazFigura(BufferedImage img) {
    	 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         contentPane = new JPanel();
         contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
         setContentPane(contentPane);
         contentPane.setLayout(null);
         setBounds(400,100,img.getWidth()+40,img.getHeight()+65);
         
         ImageIcon imageIcon = new ImageIcon(img);
         JLabel jLabel = new JLabel();
         jLabel.setIcon(imageIcon);
         jLabel.setBounds(20, 20, img.getWidth(), img.getHeight());
         contentPane.add(jLabel, BorderLayout.CENTER);
         
         
         //this.img=img;
         //img.createGraphics();

    }
    
//    public void paint (Graphics g){
//        super.paint(g);
//    	
//    	g.drawImage((Image)img, img.getWidth(),img.getHeight(),null);
//            
//    }
    
	
}
