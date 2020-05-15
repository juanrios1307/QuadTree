package quadTree;


import java.awt.*;
import java.awt.image.*;

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
		int px=img.getHeight();
		
		for (int i = 0; i < px/4; i+=4) {
			for (int j = 0; j < px/4; j+=4) {
				img.getSubimage(i, j, 4, 4).getRGB(i+2, j+2);
			}
		}
	
		
		return null;
	}

	public void arbolToImagen(QuadTree arbol) throws ExceptionNodo {
		int px=(int) Math.pow(2, arbol.getAltura());
		
		arbolToImagen(arbol.getRoot(),arbol.getAltura());
	}
	
	public void arbolToImagen(Nodo r,int h) throws ExceptionNodo{
		
		int lado=(int)Math.pow(2, r.getAltura())*20;
		if(r instanceof NodoPadre){

			dividir(r.getRect().getX(), r.getRect().getY(), r.getRect().getLado());
			//dividir(r.getRect().getX(), r.getRect().getY(), lado);
			
			NodoPadre aux=(NodoPadre)r;
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
			NodoHoja aux=(NodoHoja)r;
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
		

		
		NodoHoja h00=new NodoHoja(new Rectangulo(140, 100, 20), new Color(250,0,0));
		NodoHoja h01=new NodoHoja(new Rectangulo(160, 100, 20), Color.black);
		NodoHoja h02=new NodoHoja(new Rectangulo(160, 120, 20), Color.green);
		NodoHoja h03=new NodoHoja(new Rectangulo(140, 120, 20), Color.PINK);
		
		NodoHoja h10=new NodoHoja(new Rectangulo(100, 100, 40),Color.green);
		NodoPadre h11=new NodoPadre(new Rectangulo(140, 100, 40), h00, h01, h02, h03);
		
		NodoHoja h12=new NodoHoja(new Rectangulo(140, 140, 40),Color.red);
		NodoHoja h13=new NodoHoja(new Rectangulo(100, 140, 40),Color.pink);
		
		NodoPadre h20=new NodoPadre(new Rectangulo(100, 100, 80), h10, h11, h12, h13);
		
		NodoHoja h21=new NodoHoja(new Rectangulo(180,100,80),Color.pink);
		
		NodoHoja h14=new NodoHoja(new Rectangulo(180, 180, 40),Color.green);
		NodoHoja h15=new NodoHoja(new Rectangulo(220, 180, 40),Color.red);
		NodoHoja h16=new NodoHoja(new Rectangulo(220, 220, 40),Color.green);
		NodoHoja h17=new NodoHoja(new Rectangulo(180, 220, 40),Color.red);
		
		NodoPadre h22=new NodoPadre(new Rectangulo(180, 180, 80), h14, h15, h16, h17);
		
		NodoHoja h23=new NodoHoja(new Rectangulo(100,180,80),Color.cyan);
		
		NodoPadre h30=new NodoPadre(new Rectangulo(100, 100, 160),h20,h21,h22,h23);
		
		QuadTree arbol=new QuadTree(h30);
		
		System.out.println(h30.getAltura());
		System.out.println(h20.getAltura());
		System.out.println(h11.getAltura());
		System.out.println(h22.getAltura());
		
		return arbol;
		
	}

	
	
}
