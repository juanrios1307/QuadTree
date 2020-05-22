package quadTree;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class ProcesosArbolAux extends JFrame{
	
	Graphics G;
	int lado=1;
	BufferedImage imagen;
	
	public ProcesosArbolAux(Graphics G) {
		this.G=G;
	}
	
	public ProcesosArbolAux() {
	}
	
	public QuadTree imageToArbol(BufferedImage img) {
		lado=img.getHeight();
		
		return new QuadTree(imagenToArbol(img));
	}
	
	public Nodo imagenToArbol(BufferedImage img) {
		Nodo root = new Nodo(new Rectangulo(1, 1, img.getHeight()));
		
		return imagenToArbol(img, root);
	}
	
	public Nodo imagenToArbol(BufferedImage img, Nodo r){	
		if(img.getWidth()==16) {
			System.out.println("entro al condicional!");
			Nodo hoja = new Nodo(new Color(img.getRGB(img.getMinX(),img.getMinY())));
			return hoja;
		}
		else {
			System.out.println(img.getHeight()*img.getTileWidth() + " pixeles y lado "+img.getWidth()+" NW ");
			r.setNw(imagenToArbol(nw(img)));
			
			System.out.println(img.getHeight()*img.getTileWidth() + " pixeles y lado "+img.getWidth()+" NE ");
			r.setNe(imagenToArbol(ne(img)));
			
			System.out.println(img.getHeight()*img.getTileWidth() + " pixeles y lado "+img.getWidth()+" SE ");
			r.setSe(imagenToArbol(se(img)));
			
			System.out.println(img.getHeight()*img.getTileWidth() + " pixeles y lado "+img.getWidth()+" SW ");
			r.setSw(imagenToArbol(sw(img)));
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
	
	public BufferedImage aToI(QuadTree arbol) {
		
		imagen = new BufferedImage(lado, lado, BufferedImage.TYPE_INT_RGB);
		System.out.println(imagen.getHeight());
		
		Graphics2D g=imagen.createGraphics();
		
		return arbolToImagen(arbol.getRoot(),g, lado,0,0);
	}

	
	public BufferedImage arbolToImagen(Nodo n, Graphics2D g, int res,int x,int y) {
		if (n.isHoja()) {
			Nodo padreHojas= n.getPadre();
			pintar(x,y,res/2,n.getColor());
		}
		else {
			BufferedImage nw=arbolToImagen(n.getNw(),g,res/2,x,y);
			
			BufferedImage ne=arbolToImagen(n.getNe(),g,res/2,x,y);
			
			BufferedImage se=arbolToImagen(n.getSe(),g,res/2,x,y);
			
			BufferedImage sw=arbolToImagen(n.getSw(),g,res/2,x,y);
			
//			g.drawImage(nw, 0, 0, null);
//			g.drawImage(ne, nw.getWidth(), 0, null);
//			g.drawImage(se, nw.getWidth(), nw.getHeight(), null);
//			g.drawImage(sw, 0, nw.getHeight(), null);
		}
		return null;
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
	
	
	
	
	public void asignarPadres(Nodo b){
		if(b==null) {	
		}
		else if(b.getNe()!=null) {
			b.getNw().setPadre(b);
			b.getNe().setPadre(b);
			b.getSe().setPadre(b);
			b.getSw().setPadre(b);
		}
		else {
			asignarPadres(b.getNw());
			asignarPadres(b.getNe());
			asignarPadres(b.getSe());
			asignarPadres(b.getSw());
		}
		System.out.println("asignoPadres");
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
