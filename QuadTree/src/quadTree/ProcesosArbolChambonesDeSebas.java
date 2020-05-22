package quadTree;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class ProcesosArbol extends JFrame{
	
	Graphics G;
	static QuadTree arbol;
	
	public ProcesosArbol(Graphics G) {
		this.G=G;
	}
	
	public ProcesosArbol() {
	}
	
	public Nodo imagenToArbol(BufferedImage img) {
		
		Nodo root = new Nodo(new Rectangulo(1, 1, img.getHeight()));
		//try {
//			arbol.recorrer();
//		} catch (ExceptionNodo e) {
//			e.printStackTrace();
//		}
		
		return imagenToArbol(img, root);
	}
	public Nodo imagenToArbol(BufferedImage img, Nodo r){	
		if(img.getWidth()==64) {
			System.out.println("entro al condicional!");
			Nodo lolo = new Nodo( new Color(img.getRGB(img.getMinX(),img.getMinY())));
			return lolo;
		}
		else {
			System.out.println(img.getHeight()*img.getTileWidth() + " pixeles y lado "+img.getWidth());
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
	
	public BufferedImage aToI(QuadTree gay) {
//		int altura = gay.getRoot().altura();
//		int lado = (int) Math.pow(2, altura);
		BufferedImage img = new BufferedImage((int)Math.pow(2, gay.getRoot().altura()), (int)Math.pow(2, gay.getRoot().altura()), BufferedImage.TYPE_INT_RGB);
		Graphics2D g = img.createGraphics();
		return aToI(g, gay.getRoot());
	}
	public BufferedImage aToI(Nodo n) {
		
	}
	public BufferedImage aToI(Nodo n, Graphics2D g, int res) {
		if (n.isHoja()) {
			Nodo padreHojas= n.getPadre();
			BufferedImage img1 = new BufferedImage(128, 128, BufferedImage.TYPE_INT_RGB);
		}
		else {
			BufferedImage nw=aToI(n.getNw());
			BufferedImage ne=aToI(n.getNe());
			BufferedImage se=aToI(n.getSe());
			BufferedImage sw=aToI(n.getSw());
			
			g.drawImage(nw, 0, 0, null);
			g.drawImage(ne, nw.getWidth(), 0, null);
			g.drawImage(se, nw.getWidth(), nw.getHeight(), null);
			g.drawImage(sw, 0, nw.getHeight(), null);
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
	
	public double alturaNodosky(Nodo b) {
		double i=0;
		while(b.getPadre()!=null)i++;
		return i;
	}
	
//	public BufferedImage nw(BufferedImage img){
//		return img.getSubimage(1, 1, img.getWidth()/2, img.getHeight()/2);
//	}
//	public BufferedImage ne(BufferedImage img){		
//		return img.getSubimage(img.getWidth()/2, 1, img.getWidth()/2, img.getHeight()/2);
//	}
//	public BufferedImage nw(){
//		
//	}
//	public BufferedImage se(BufferedImage img){
//		return img.getSubimage(img.getWidth()/2, img.getHeight()/2, img.getWidth()/2, img.getHeight()/2);
//		
//	}
//	public BufferedImage sw(BufferedImage img){
//		return img.getSubimage(1, img.getHeight()/2, img.getWidth()/2, img.getHeight()/2);
//		
//	}
	
	
	
	public void arbolToImagen(QuadTree arbol) throws ExceptionNodo {
		//int px=(int) Math.pow(2, arbol.getAltura());
		
		arbolToImagen(arbol.getRoot(),2);
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
