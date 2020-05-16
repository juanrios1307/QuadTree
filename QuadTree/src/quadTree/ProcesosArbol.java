package quadTree;


import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import interfaz.InterfazFigura;

public class ProcesosArbol extends JFrame{
	
	Graphics G;
	static QuadTree arbol;
	
	public ProcesosArbol(Graphics G) {
		this.G=G;
	}
	
	public ProcesosArbol() {
	}
	
	public QuadTree imagenToArbol(BufferedImage img) {
		
		NodoPadre nodito = new NodoPadre();
		QuadTree Arbol = new QuadTree(null);
	}
	
	public QuadTree imagenToArbol(BufferedImage img, QuadTree Arbol) {
		
	}

	public void arbolToImagen(QuadTree arbol) throws ExceptionNodo {
		/*int px=(int) Math.pow(2, arbol.getAltura());
		
		arbolToImagen(arbol.getRoot(),arbol.getAltura());*/
	}
	
	public void arbolToImagen(Nodo r,int h) throws ExceptionNodo{
		
		//int lado=(int)Math.pow(2, r.getAltura())*20;
		if(!r.isHoja()){

			dividir(r.getRect().getX(), r.getRect().getY(), r.getRect().getLado());
			//dividir(r.getRect().getX(), r.getRect().getY(), lado);
			
			Nodo aux=r;
			if(aux.getNw() != null){
				arbolToImagen(aux.getNw(),h);
				
			}
			if(aux.getNe() != null){
				arbolToImagen(aux.getNe(), h);
				
			}
			if(aux.getSe() != null){
				arbolToImagen(aux.getSe(),h);
				
			}
			if(aux.getSw() != null){
				arbolToImagen(aux.getSw(),h);
				
			}
		}else{
			Nodo aux=r;
			pintar(aux.getRect().getX(),aux.getRect().getY(),aux.getRect().getLado(),aux.getColor());
			
		}
	}
	
	
	public Graphics pintar(int x,int y,int l,Color color) {
		super.paint(G);
		G.setColor(color);
		G.fillRect(x, y, l, l);
		
		return G;
	}
	
	public Graphics dividir(int x,int y,int l){
		//se va a poner cuadricula de la imagen
		super.paint(G);
		G.setColor(Color.black.darker());
		//G.drawRect(x, y, l, l);
		
		G.drawLine(x, y+l/2, x+l, y+l/2);
		
		G.drawLine(x+l/2, y, x+l/2, y+l);
		
		return G;
	}

	public static QuadTree crearArbolPrueba() {
		

		
		Nodo h00=new Nodo(new Rectangulo(140, 100, 20), new Color(250,0,0));
		Nodo h01=new Nodo(new Rectangulo(160, 100, 20), Color.black);
		Nodo h02=new Nodo(new Rectangulo(160, 120, 20), Color.green);
		Nodo h03=new Nodo(new Rectangulo(140, 120, 20), Color.PINK);
		
		Nodo h10=new Nodo(new Rectangulo(100, 100, 40),Color.green);
		//Nodo h11=new Nodo(new Rectangulo(140, 100, 40), h00, h01, h02, h03);
		
		Nodo h12=new Nodo(new Rectangulo(140, 140, 40),Color.red);
		Nodo h13=new Nodo(new Rectangulo(100, 140, 40),Color.pink);
		
		//Nodo h20=new Nodo(new Rectangulo(100, 100, 80), h10, h11, h12, h13);
		
		Nodo h21=new Nodo(new Rectangulo(180,100,80),Color.pink);
		
		Nodo h14=new Nodo(new Rectangulo(180, 180, 40),Color.green);
		Nodo h15=new Nodo(new Rectangulo(220, 180, 40),Color.red);
		Nodo h16=new Nodo(new Rectangulo(220, 220, 40),Color.green);
		Nodo h17=new Nodo(new Rectangulo(180, 220, 40),Color.red);
		
		//Nodo h22=new Nodo(new Rectangulo(180, 180, 80), h14, h15, h16, h17);
		
		Nodo h23=new Nodo(new Rectangulo(100,180,80),Color.cyan);
		
		//Nodo h30=new Nodo(new Rectangulo(100, 100, 160),h20,h21,h22,h23);
		
		QuadTree arbol=new QuadTree(h23);
		
		
		
		return arbol;
		
	}

	public static void main (String[]Args) {
		
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("C:\\Users\\Decol\\Desktop\\download.JPG"));
		} catch (IOException e) {
		}
		System.out.println(img.getHeight()+", "+img.getWidth());
	}
	
}
