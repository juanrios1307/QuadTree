package quadTree;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Procesos extends JFrame{
	
	Graphics G;
	int lado=1;
	BufferedImage imagen;
	
	public Procesos(Graphics G) {
		this.G=G;
	}
	
	public Procesos() {
	}
	
	public QuadTree imagenToArbol(BufferedImage img) {
		lado=img.getHeight();
		
		return new QuadTree(imgToArbol(img));
	}
	
	public Nodo imgToArbol(BufferedImage img) {
		Nodo root = new Nodo();
		
		return imgToArbol(img, root);
	}
	
	public Nodo imgToArbol(BufferedImage img, Nodo r){	
		if(img.getWidth()==1) {
			
			Nodo hoja = new Nodo(new Color(img.getRGB(img.getMinX(),img.getMinY())));
			return hoja;
		}
		else {
			r.setNw(imgToArbol(nw(img)));
			r.setNe(imgToArbol(ne(img)));
			r.setSe(imgToArbol(se(img)));
			r.setSw(imgToArbol(sw(img)));
		}
		return r;	
	}
	
	public void optimizacion(Nodo r) {
		
	}

	public BufferedImage nw(BufferedImage img){
		return img.getSubimage(0, 0, img.getWidth()/2, img.getHeight()/2);
	}
	public BufferedImage ne(BufferedImage img){		
		return img.getSubimage(img.getWidth()/2, 0, img.getWidth()/2, img.getHeight()/2);
	}
	public BufferedImage se(BufferedImage img){
		return img.getSubimage(img.getWidth()/2, img.getHeight()/2, img.getWidth()/2, img.getHeight()/2);	
	}
	public BufferedImage sw(BufferedImage img){
		return img.getSubimage(0, img.getHeight()/2, img.getWidth()/2, img.getHeight()/2);	
	}
	
	public BufferedImage arbolToImagen(QuadTree arbol) {
		
		imagen = new BufferedImage(lado, lado, BufferedImage.TYPE_INT_RGB);
		Graphics2D g=imagen.createGraphics();
		return arbolToImagen(arbol.getRoot(),g, lado,0,0);
	}

	
	public BufferedImage arbolToImagen(Nodo n, Graphics2D g, int res,int x,int y) {
		if (n.isHoja()) {
			pintar(x,y,res/2,n.getColor());
		}else {
			
			arbolToImagen(n.getNw(),g,res/2,x,y);
			
			x+=res/2;
			arbolToImagen(n.getNe(),g,res/2,x,y);
			
			y+=res/2;
			arbolToImagen(n.getSe(),g,res/2,x,y);
			
			x-=res/2;
			arbolToImagen(n.getSw(),g,res/2,x,y);
			
		}
		return imagen;
	}
	
	public void pintar(int x,int y,int lado,Color color) {
		try {
			if(lado>1) {	
				int[] rgbs=new int[lado];
				
				for(int i=0;i<lado;i++) {
					rgbs[i]=color.getRGB();
				}
				
				imagen.setRGB(x, y, lado, lado, rgbs, 0, 0);
				
			}else
				imagen.setRGB(x, y, color.getRGB());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
}
