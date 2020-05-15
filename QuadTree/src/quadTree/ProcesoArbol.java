package quadTree;


import java.awt.*;
import java.awt.image.*;

import javax.swing.JFrame;

import interfaz.InterfazFigura;

public class ProcesoArbol extends JFrame{
	
	Graphics G;
	static QuadTree arbol;
	
	
	public ProcesoArbol(Graphics G) {
		this.G=G;
	}
	
	/*public ProcesoArbol(BufferedImage img) {
		ProcesoArbol.arbol=procesarImagen(img);
		
		InterfazFigura frame = new InterfazFigura(arbol);
        frame.setVisible(true);
        
	}*/
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	arbol=crearArbolPrueba();
                	InterfazFigura frame = new InterfazFigura(arbol);
                    frame.setVisible(true);      
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
	}
	
	public QuadTree procesarImagen(BufferedImage img) {
		int px=img.getHeight();
		
		for (int i = 0; i < px/4; i+=4) {
			for (int j = 0; j < px/4; j+=4) {
				img.getSubimage(i, j, 4, 4).getRGB(i+2, j+2);
			}
		}
		
		return null;
	}
	
	public void procesarArbol(QuadTree arbol) throws ExceptionNodo {
		int px=(int) Math.pow(2, arbol.getAltura());
		
		recorrerArbol(arbol.getRoot(),arbol.getAltura());
	}
	
	public void recorrerArbol(Nodo r,int h) throws ExceptionNodo{
		
		//int lado=(int)Math.pow(2, r.getAltura());
		if(r instanceof NodoPadre){

			dividir(r.getRect().getX(), r.getRect().getY(), r.getRect().getLado());
			
			NodoPadre aux=(NodoPadre)r;
			if(aux.getNw() != null){
				recorrerArbol(aux.getNw(),h);
				
			}
			if(aux.getNe() != null){
				recorrerArbol(aux.getNe(), h);
				
			}
			if(aux.getSe() != null){
				recorrerArbol(aux.getSe(),h);
				
			}
			if(aux.getSw() != null){
				recorrerArbol(aux.getSw(),h);
				
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
		

		
		NodoHoja h00=new NodoHoja(new Rectangulo(140, 100, 20), Color.red);
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
		
		return arbol;
		
	}

	
	
}
