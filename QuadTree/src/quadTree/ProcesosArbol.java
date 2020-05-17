package quadTree;


import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import interfaz.InterfazFigura;

public class ProcesosArbol extends JFrame{
	
	BufferedImage imagen;
	
	public ProcesosArbol() {
	}
	
public QuadTree imagenToArbol(BufferedImage img) {
		
		Nodo root = new Nodo(new Rectangulo(1, 1, img.getHeight()));
		imagenToArbol(img, root,0,0,1,1);
		QuadTree arbol = new QuadTree(root);
		//arbol=crearArbolPrueba();
	
		
//		try {
//			arbol.recorrer();
//		} catch (ExceptionNodo e) {
//			e.printStackTrace();
//		}
		
		return arbol;
	}
	
	public void imagenToArbol(BufferedImage img, Nodo r,int x,int y,int xI,int yI) {
		if(img.getWidth()==2) {
			r.setNw(new Nodo(new Rectangulo(x,y,1), new Color(img.getRGB(img.getMinX(),img.getMinY()))));
			r.setNe(new Nodo(new Rectangulo(1,y,1), new Color(img.getRGB(img.getMinX(),img.getMinY()))));
			r.setSe(new Nodo(new Rectangulo(1,1,1), new Color(img.getRGB(img.getMinX(),img.getMinY()))));
			r.setSw(new Nodo(new Rectangulo(x,1,1), new Color(img.getRGB(img.getMinX(),img.getMinY()))));
			
//			r.setNw(new Nodo(new Rectangulo(x,y,1), new Color(img.getRGB(xI+2,yI+2))));
//			r.setNe(new Nodo(new Rectangulo(1,y,1), new Color(img.getRGB(xI+2,yI+2))));
//			r.setSe(new Nodo(new Rectangulo(1,1,1), new Color(img.getRGB(xI+2,yI+2))));
//			r.setSw(new Nodo(new Rectangulo(x,1,1), new Color(img.getRGB(xI+2,yI+2))));
			
//			r.setNw(new Nodo(new Rectangulo(x,y,img.getWidth()/2), Color.blue));
//			r.setNe(new Nodo(new Rectangulo(x+img.getWidth()/2,y,img.getWidth()/2), Color.red));
//			r.setSe(new Nodo(new Rectangulo(x+img.getWidth()/2,y+img.getWidth()/2,img.getWidth()/2), Color.green));
//			r.setSw(new Nodo(new Rectangulo(x,y+img.getWidth()/2,img.getWidth()/2), Color.CYAN));
			
		}else {
			try {
				r.setNw(new Nodo(new Rectangulo(x, y, img.getWidth()/2)));
				r.setNe(new Nodo(new Rectangulo(x+img.getWidth()/2, y, img.getWidth()/2)));
				r.setSe(new Nodo(new Rectangulo(x+img.getWidth()/2, y+img.getHeight()/2, img.getWidth()/2)));
				r.setSw(new Nodo(new Rectangulo(x, y+img.getHeight()/2, img.getWidth()/2)));
				
				xI=1;
				yI=1;
				imagenToArbol(img.getSubimage(xI, yI, img.getWidth()/2, img.getHeight()/2), r.getNw(),x,y,xI,yI);
				x+=img.getWidth()/2;
				xI=img.getWidth()/2;
				
				imagenToArbol(img.getSubimage(xI, yI, img.getWidth()/2, img.getHeight()/2), r.getNe(),x,y,xI,yI);
				y+=img.getWidth()/2;
				yI=img.getWidth()/2;
				
				imagenToArbol(img.getSubimage(xI, yI, img.getWidth()/2, img.getHeight()/2), r.getSe(),x,y,xI,yI);
				x-=img.getWidth()/2-1;
				xI-=img.getWidth()/2-1;
				
				imagenToArbol(img.getSubimage(xI, yI, img.getWidth()/2, img.getHeight()/2), r.getSw(),x,y,xI,yI);
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}	
	}
	
	public BufferedImage arbolToImagen(QuadTree arbol) throws ExceptionNodo {
		imagen=new BufferedImage(arbol.getPx(), arbol.getPx(), BufferedImage.TYPE_3BYTE_BGR);
		
		return arbolToImagen(arbol.getRoot(),2);
		
	}
	public BufferedImage arbolToImagen(Nodo r,int h) throws ExceptionNodo{
		
		if(!r.isHoja()){
			//dividir(r.getRect().getX(), r.getRect().getY(), r.getRect().getLado());

			if(r.getNw() != null){
				arbolToImagen(r.getNw(),h);
				
			}
			if(r.getNe() != null){
				arbolToImagen(r.getNe(), h);
				
			}
			if(r.getSe() != null){
				arbolToImagen(r.getSe(),h);
				
			}
			if(r.getSw() != null){
				arbolToImagen(r.getSw(),h);
				
			}
		}else{
			pintar(r);
			
		}
		return imagen;
	}
	
	
	public void pintar(Nodo r) {
		if(r.getRect().getLado()>1) {	
			for (int i = 0; i < r.getRect().getLado(); i++) {
				for (int j = 0; j < r.getRect().getLado(); j++) {
					imagen.setRGB(r.getRect().getX()+i, r.getRect().getY()+j, r.getColor().getRGB());
				}
			}
		}else
			imagen.setRGB(r.getRect().getX(), r.getRect().getY(), r.getColor().getRGB());
	}
	
//	public void paint (Graphics g){
//        super.paint(g);
//    	
//    	g.drawImage((Image)imagen, imagen.getWidth(),imagen.getHeight(),this);
//            
//    }
	
//	public Graphics dividir(int x,int y,int l){
//		//se va a poner cuadricula de la imagen
//		super.paint(G);
//		G.setColor(Color.black.darker());
//		//G.drawRect(x, y, l, l);
//		
//		G.drawLine(x, y+l/2, x+l, y+l/2);
//		
//		G.drawLine(x+l/2, y, x+l/2, y+l);
//		
//		return G;
//	}

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
