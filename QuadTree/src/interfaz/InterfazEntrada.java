package interfaz;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import quadTree.*;

public class InterfazEntrada extends JFrame{
	JPanel contentPane;
	
	//Constructor
	public InterfazEntrada() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setBounds(400,100,600,625);
        
        
	}

	//Metodo recibirImagen
	
	//Metodo addBotonConvertir 
	
	private void convertir(BufferedImage img) throws ExceptionNodo{
		Procesos process=new Procesos();
		
		convertir(process.imageToArbol(img));
	}
	
	private void convertir(QuadTree arbol) throws ExceptionNodo {
		InterfazFigura figura=new InterfazFigura(new Procesos().arbolToImagen(arbol));
		figura.setVisible(true);
		this.setVisible(false);
	}
}
