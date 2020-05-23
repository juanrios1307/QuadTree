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
	
	public QuadTree imageToArbol(BufferedImage img) {
		lado=img.getHeight();
		QuadTree arbol=new QuadTree(imagenToArbol(img));
		
		//optimizacion(arbol.getRoot());
		
		return arbol;
	}
	public Nodo imagenToArbol(BufferedImage img) {
		Nodo root = new Nodo();
		
		return imagenToArbol(img, root);
	}
	public Nodo imagenToArbol(BufferedImage img, Nodo r){	
		if(img.getWidth()==1) {
			
			//Nodo hoja = new Nodo((promedio(img)));
			Nodo hoja=new Nodo(new Color(img.getRGB(0, 0)));
			return hoja;
		}
		else {
			if(img.getHeight()==8) {
				Color nw = promedio(nw(img));
				Color ne = promedio(ne(img));
				Color se = promedio(sw(img));
				Color sw = promedio(sw(img));
				
				if(nw==ne&&ne==se&&se==sw){
					return new Nodo(nw);
				}
				else {
					r.setNw(imagenToArbol(nw(img)));
					r.setNe(imagenToArbol(ne(img)));
					r.setSe(imagenToArbol(se(img)));
					r.setSw(imagenToArbol(sw(img)));
				}
			}
			else {
			r.setNw(imagenToArbol(nw(img)));
			r.setNe(imagenToArbol(ne(img)));
			r.setSe(imagenToArbol(se(img)));
			r.setSw(imagenToArbol(sw(img)));
			}
		}
		return r;	
	}
	
	public void optimizacion(Nodo r) {
		if(r.isHoja()) {
			Nodo padre=r.getPadre();
			if(iguales(padre)) {
				padre.setColor(r.getColor());
			}
			
		}else {
			optimizacion(r.getNw());
			optimizacion(r.getNe());
			optimizacion(r.getSe());
			optimizacion(r.getSw());
			
		}
	}
	
	public Color promedio(BufferedImage img) {
		int r=0,g=0,b=0;
		for(int i=1;i<3;i++) {
			for(int j=1;j<3;j++) {
				r+=new Color(img.getRGB(i, j)).getRed();
				b+=new Color(img.getRGB(i, j)).getBlue();
				g+=new Color(img.getRGB(i, j)).getGreen();
			}
		}
		
		return new Color(r/4,g/4,b/4);
	}
	
	public boolean iguales(Nodo r) {
		Nodo nw=r.getNw();
		Nodo ne=r.getNe();
		Nodo sw=r.getSw();
		Nodo se=r.getSe();
		if((nw.isHoja())&& ne.isHoja() && se.isHoja() && sw.isHoja()&&
			nw.getColor().equals(ne.getColor()) &&
			nw.getColor().equals(se.getColor()) &&
			nw.getColor().equals(sw.getColor()) &&
			ne.getColor().equals(se.getColor()) &&
			ne.getColor().equals(sw.getColor()) &&
			se.getColor().equals(sw.getColor()) ) {
			return true;
		}else
			return false;
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
			pintar(x,y,res,n.getColor());
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
				
//				int[] rgbs=new int[lado];				
//				for(int i=0;i<lado;i++) {
//					rgbs[i]=color.getRGB();
//				}
//				imagen.setRGB(x, y, lado, lado, rgbs, 0, 0);
				
				for (int i = 0; i < lado; i++) {
					for (int j = 0; j < lado; j++) {
						imagen.setRGB(x+i, y+j, color.getRGB());
					}
				}
				
				
			}else
				imagen.setRGB(x, y, color.getRGB());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
}
