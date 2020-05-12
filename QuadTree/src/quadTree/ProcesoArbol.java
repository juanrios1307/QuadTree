package quadTree;

import java.awt.image.BufferedImage;

public class ProcesoArbol {

	
	public static void procesarImagen() {
		
	}
	
	public static void procesarArbol(QuadTree arbol) throws ExceptionNodo {
		int px=(int) Math.pow(2, arbol.getAltura());
		BufferedImage img=new BufferedImage(px, px, 1);
		
		recorrerArbol(0,0,img,arbol.getRoot(),arbol.getAltura());
	}
	
	public static void recorrerArbol(int x,int y,BufferedImage img,Nodo r,int h) throws ExceptionNodo{
		
		int lado=(int)Math.pow(2, h-r.getAltura());
		if(r instanceof NodoPadre){
			
			
			dividir(img.getSubimage(x, y, lado, lado));
			
			NodoPadre aux=(NodoPadre)r;
			if(aux.getNW() != null){
				recorrerArbol(x,y,img,aux.getNW(),h);
				x+=4;
			}
			if(aux.getNE() != null){
				recorrerArbol(x,y,img,aux.getNE(), h);
				y+=4;
			}
			if(aux.getSE() != null){
				recorrerArbol(x,y,img,aux.getSE(),h);
				x-=4;
			}
			if(aux.getSW() != null){
				recorrerArbol(x,y,img,aux.getSW(),h);
				
			}
		}else{
			pintar(img.getSubimage(x, y, lado, lado),(NodoHoja) r);
		}
	}
	
	
	public static void pintar(BufferedImage img,NodoHoja r) {
		//este metodo va a pintar de un color el cuadro correspondiente
		
		
		img.setRGB(0, 0, r.getColor());
	}
	
	public static void dividir(BufferedImage img){
		//se va a poner cuadricula de la imagen
	}

	
	
}
