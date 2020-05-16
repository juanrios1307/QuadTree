package quadTree;

import java.awt.*;

public class Nodo {

	private Rectangulo rect;
	private Color color;
	private Nodo nw, ne, se, sw, padre;
	private boolean hoja;

	// -----------------------------------------------------------------------------------------------------

	public Nodo getPadre() {
		return padre;
	}

	public Nodo(Rectangulo rect, Color color) {
		super();
		this.rect = rect;
		this.color = color;
		this.hoja=true;
	}

	// -----------------------------------------------------------------------------------------------------

	public boolean isHoja() {
		return hoja;
	}

	public void setHoja(boolean hoja) {
		this.hoja = hoja;
	}

	public Nodo(Rectangulo rect) {
		super();
		this.rect = rect;
	}

	public void setPadre(Nodo padre) {
		this.padre = padre;
	}

	public Rectangulo getRect() {
		return rect;
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

	public void setRect(Rectangulo rect) {
		this.rect = rect;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	// -----------------------------------------------------------------------------------------------------
	/*
	 * public int altura() { return altura(this); }
	 * 
	 * public int altura(Nodo n) { if (n == null) return -1;
	 * 
	 * if (n instanceof NodoPadre) { NodoPadre aux = (NodoPadre) n;
	 * 
	 * int nw = (aux.getNw() == null ? 0 : 1 + altura(aux.getNw())); int ne =
	 * (aux.getNe() == null ? 0 : 1 + altura(aux.getNe())); int sw = (aux.getSw() ==
	 * null ? 0 : 1 + altura(aux.getSw())); int se = (aux.getSe() == null ? 0 : 1 +
	 * altura(aux.getSe()));
	 * 
	 * return Math.max(Math.max(nw, ne), Math.max(sw, se)); } else return 0; }
	 */
	// -----------------------------------------------------------------------------------------------------

	public int nivelNodo(Nodo e) {
		int nivel = 0;
		Nodo hijo = e;

		while (hijo.getPadre() != null) {
			nivel++;
			hijo = hijo.getPadre();
		}

		return nivel;
	}

	// -----------------------------------------------------------------------------------------------------

}
