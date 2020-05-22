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
		Nodo root = new Nodo(new Rectangulo(1, 1, img.getHeight()));
		
		return imgToArbol(img, root);
	}
	
	public Nodo imgToArbol(BufferedImage img, Nodo r){	
		if(img.getWidth()==4) {
			
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
		System.out.println(imagen.getHeight());
		
		Graphics2D g=imagen.createGraphics();
		
		return arbolToImagen(arbol.getRoot(),g, lado,1,1);
	}

	
	public BufferedImage arbolToImagen(Nodo n, Graphics2D g, int res,int x,int y) {
		if (n.isHoja()) {
			pintar(x,y,res/2,n.getColor());
		}
		else {
			
			arbolToImagen(n.getNw(),g,res/2,x,y);
			
			x+=res/2;
			arbolToImagen(n.getNe(),g,res/2,x,y);
			
			y+=res/2;
			arbolToImagen(n.getSe(),g,res/2,x,y);
			
			x-=res/2;
			arbolToImagen(n.getSw(),g,res/2,x,y);
			
//			g.drawImage(nw, 0, 0, null);
//			g.drawImage(ne, nw.getWidth(), 0, null);
//			g.drawImage(se, nw.getWidth(), nw.getHeight(), null);
//			g.drawImage(sw, 0, nw.getHeight(), null);
		}
		return imagen;
	}
	
	public void pintar(int x,int y,int lado,Color color) {
		try {
			if(lado>1) {	
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

	public static QuadTree crearArbolPrueba() {
		

		
		Nodo h00=new Nodo(new Rectangulo(140, 100, 20), new Color(250,0,0));
		Nodo h01=new Nodo(new Rectangulo(160, 100, 20), Color.black);
		Nodo h02=new Nodo(new Rectangulo(160, 120, 20), Color.green);
		Nodo h03=new Nodo(new Rectangulo(140, 120, 20), Color.PINK);
		
		Nodo h10=new Nodo(new Rectangulo(100, 100, 40),Color.green);
		
		Nodo h11=new Nodo(new Rectangulo(140, 100, 40));
		h11.setNw(h00);
		h11.setNe(h01);
		h11.setSe(h02);
		h11.setSw(h03);
		
		Nodo h12=new Nodo(new Rectangulo(140, 140, 40),Color.red);
		Nodo h13=new Nodo(new Rectangulo(100, 140, 40),Color.pink);
		
		Nodo h20=new Nodo(new Rectangulo(100, 100, 80));
		h20.setNw(h10);
		h20.setNe(h11);
		h20.setSe(h12);
		h20.setSw(h13);
		
		Nodo h21=new Nodo(new Rectangulo(180,100,80),Color.pink);
		
		Nodo h14=new Nodo(new Rectangulo(180, 180, 40),Color.green);
		Nodo h15=new Nodo(new Rectangulo(220, 180, 40),Color.red);
		Nodo h16=new Nodo(new Rectangulo(220, 220, 40),Color.green);
		Nodo h17=new Nodo(new Rectangulo(180, 220, 40),Color.red);
		
		Nodo h22=new Nodo(new Rectangulo(180, 180, 80));
		h22.setNw(h14);
		h22.setNe(h15);
		h22.setSe(h16);
		h22.setSw(h17);
		
		Nodo h23=new Nodo(new Rectangulo(100,180,80),Color.cyan);
		
		Nodo h30=new Nodo(new Rectangulo(100, 100, 160));
		h30.setNw(h20);
		h30.setNe(h21);
		h30.setSe(h22);
		h30.setSw(h23);
		
		
		QuadTree arbol=new QuadTree(h30);
		
		
		
		return arbol;
		
	}
	
}
