package quadTree;

import java.awt.*;

public class Nodo {
	
	

//	@Override
//	public String toString() {
//		return hoja?"color: "+color.getRGB():"padre: nivel: "+nivelNodo(); 
//	}
	
	@Override
	public String toString() {
		return hoja?"Color: "+color.getRGB():"Padre nivel: "+nivelNodo();
	}

	private Color color;
	private Nodo nw, ne, se, sw, padre;
	private boolean hoja;

	// -----------------------------------------------------------------------------------------------------

	public Nodo getPadre() {
		return padre;
	}
	
	public Nodo(Color color) {
		super();
		this.color = color;
		this.hoja = true;
	}
	
	public Nodo() {
		super();
	}

	// -----------------------------------------------------------------------------------------------------

	public boolean isHoja() {
		return hoja;
	}

	public void setHoja(boolean hoja) {
		this.hoja = hoja;
	}

	public void setPadre(Nodo padre) {
		this.padre = padre;
	}

	public Nodo getNw() {
		return nw;
	}

	public void setNw(Nodo nw) {
		nw.setPadre(this);
		this.nw = nw;
	}

	public Nodo getNe() {
		return ne;
	}

	public void setNe(Nodo ne) {
		ne.setPadre(this);
		this.ne = ne;
	}

	public Nodo getSe() {
		return se;
	}

	public void setSe(Nodo se) {
		se.setPadre(this);
		this.se = se;
	}

	public Nodo getSw() {
		return sw;
	}

	public void setSw(Nodo sw) {
		sw.setPadre(this);
		this.sw = sw;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
		hoja=true;
	}

	public int altura() {
		return altura(this);
	}

	public int altura(Nodo n) {
		if (n == null)
			return -1;

		Nodo aux = n;
		int nw = (aux.getNw() == null ? 0 : 1 + altura(aux.getNw()));
		int ne = (aux.getNe() == null ? 0 : 1 + altura(aux.getNe()));
		int sw = (aux.getSw() == null ? 0 : 1 + altura(aux.getSw()));
		int se = (aux.getSe() == null ? 0 : 1 + altura(aux.getSe()));
		return Math.max(Math.max(nw, ne), Math.max(sw, se));
	}

	public int nivelNodo() {
		int nivel = 1;
		Nodo hijo = this;

		while (hijo.getPadre() != null) {
			nivel++;
			hijo = hijo.getPadre();
		}

		return nivel;
	}
	
	public static void main(String[] args) {
		Nodo n1=new Nodo(Color.RED);
		
		System.out.println(n1.toString());
	}
}
