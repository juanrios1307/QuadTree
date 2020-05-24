package interfaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import quadTree.*;

public class InterfazEntrada extends JFrame{
	
	private JPanel contentPane;
	private final JFileChooser selector = new JFileChooser();
	private File imagenSeleccionada;
	BufferedImage imagen;
	

	//Constructor
	public InterfazEntrada() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setBounds(400,100,510,350);
		
        JLabel imagenO = new JLabel("");
		imagenO.setVerticalAlignment(SwingConstants.TOP);
		
		JLabel lblAltura = new JLabel("Altura:");
		JLabel lblPixeles = new JLabel("Tamaño:");
		JLabel lblPotencia = new JLabel("Potencia:");
		
		JButton btnConvertir = new JButton("Convertir imagen");
		btnConvertir.setEnabled(false);
		btnConvertir.setBounds(326, 24, 139, 25);
		btnConvertir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					convertir();
			}
		});
		
		
		JButton btnVerificar = new JButton("Verificar imagen");
		btnVerificar.setEnabled(false);
		btnVerificar.setBounds(175, 24, 140, 25);
		btnVerificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(isCuadrada(imagen) && potenciaDos(imagen)==true) {
					btnConvertir.setEnabled(true);
					JOptionPane.showMessageDialog(null, "La imagen sí cumple", "OK", JOptionPane.INFORMATION_MESSAGE);
				}else {
					btnConvertir.setEnabled(false);
					JOptionPane.showMessageDialog(null, "La imagen no es cuadrada o no es potencia de 2, por favor ingrese una imagen válida",
							"Error", JOptionPane.ERROR_MESSAGE);
					}
			}
		});
		
		

		JButton btnInsertar = new JButton("Insertar imagen");
		btnInsertar.setBounds(24, 24, 139, 25);
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					abrirImagen();
					imagenO.setIcon(new ImageIcon(imagen));
					imagenO.setBounds(20, 80, imagen.getWidth(), imagen.getHeight());
					setBounds(400,100,imagen.getWidth()+370,imagen.getHeight()+180);
					btnConvertir.setEnabled(false);
					btnVerificar.setEnabled(true);
					lblAltura.setText("Altura: "+imagen.getHeight()+" píxeles");
					lblPixeles.setText("Tamaño: "+(imagen.getHeight()*imagen.getWidth())+" píxeles");
					lblPotencia.setText("Potencia de 2: "+ log(imagen.getHeight(),2));
					lblAltura.setBounds(imagen.getWidth()+50, 80, 207, 35);
					lblPixeles.setBounds(imagen.getWidth()+50, 128, 207, 25);
					lblPotencia.setBounds(imagen.getWidth()+50, 166, 207, 25);
					contentPane.add(lblAltura);
					contentPane.add(lblPixeles);
					contentPane.add(lblPotencia);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		contentPane.add(btnConvertir);
		contentPane.add(btnInsertar); 
		contentPane.add(btnVerificar);
		contentPane.add(imagenO, BorderLayout.CENTER);	
		contentPane.setLayout(null);
	}
        

	//Metodo recibirImagen
	
	public void abrirImagen() throws IOException {
		selector.setDialogTitle("Selecciona la imagen");
		FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("JPG" , " PNG", "jpg", "png" );
		selector.setFileFilter(filtroImagen);
		int flag = selector.showOpenDialog(null);
		
		if(flag == JFileChooser.APPROVE_OPTION) {	
				imagenSeleccionada = selector.getSelectedFile();
				imagen = ImageIO.read(imagenSeleccionada);
		}
	}
	
	//Metodo verificar imagen cuadrada
	
	public boolean isCuadrada(BufferedImage img) {
		if(img.getWidth()==img.getHeight()) {
			return true;
		}
		return false;
	}
	
	//Metodo verificar que los bits de la imagen sean potencia de 2
	
	public boolean potenciaDos(BufferedImage img) {
		int x = img.getHeight();
		return (x != 0) && ((x & (x - 1)) == 0);
	}
	
	//Metodo para sacar Log en base n
	
	private static Double log(double num, int base) {
	      return (Math.log10(num) / Math.log10(base));
	}
	
	
	private void convertir(){
		Procesos process=new Procesos();
		
		long inicio=System.currentTimeMillis();
		QuadTree arbol=process.imageToArbol(imagen);
		long fin=System.currentTimeMillis();
		
		System.out.println("time: "+(fin-inicio));

		BufferedImage img=process.arbolToImagen(arbol);
		
		InterfazFigura figura=new InterfazFigura(img);
		figura.setVisible(true);
		//this.setVisible(false);
	}	
	
}
