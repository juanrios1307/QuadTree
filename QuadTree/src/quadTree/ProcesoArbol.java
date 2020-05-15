package quadTree;


import java.awt.*;
import java.awt.image.*;

import javax.swing.JFrame;

public class ProcesoArbol extends JFrame{
	
	Graphics G;
	
	public ProcesoArbol(Graphics G) {
		this.G=G;
		
	}
	
	
	public void procesarImagen(BufferedImage img) {
		int px=img.getHeight();
		
		
		
		for (int i = 0; i < px/4; i+=4) {
			for (int j = 0; j < px/4; j+=4) {
				img.getSubimage(i, j, 4, 4).getRGB(i+2, j+2);
			}
		}
	}
	
	public void procesarArbol(QuadTree arbol) throws ExceptionNodo {
		int px=(int) Math.pow(2, arbol.getAltura());
		
		recorrerArbol(arbol.getRoot(),arbol.getAltura());
	}
	
	public void recorrerArbol(Nodo r,int h) throws ExceptionNodo{
		
		int lado=(int)Math.pow(2, r.getAltura());
		if(r instanceof NodoPadre){

			dividir(r.getX(), r.getY(), lado);
			
			NodoPadre aux=(NodoPadre)r;
			if(aux.getNW() != null){
				recorrerArbol(aux.getNW(),h);
				
			}
			if(aux.getNE() != null){
				recorrerArbol(aux.getNE(), h);
				
			}
			if(aux.getSE() != null){
				recorrerArbol(aux.getSE(),h);
				
			}
			if(aux.getSW() != null){
				recorrerArbol(aux.getSW(),h);
				
			}
		}else{
			NodoHoja aux=(NodoHoja)r;
			pintar(aux.getX(),aux.getY(),lado/2,aux.getColor());
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
		G.setColor(Color.black);
		G.drawRect(x, y, l, l);
		
		G.drawLine(x, y+l/2, x+l, y+l/2);
		
		G.drawLine(x+l/2, y, x+l/2, y+l);
		
		return G;
	}

	

	
	
}
