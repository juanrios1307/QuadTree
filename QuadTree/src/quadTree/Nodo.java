package quadTree;

public class Nodo {

	private Rectangulo rect;
	private int altura;
	private NodoPadre padre;

	public Nodo(Rectangulo rect, int altura, NodoPadre padre) {
		super();
		this.rect = rect;
		this.altura = altura;
		this.padre = padre;
	}

	// -----------------------------------------------------------------------------------------------------

	public Rectangulo getRect() {
		return rect;
	}

	public void setRect(Rectangulo rect) {
		this.rect = rect;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public NodoPadre getPadre() {
		return padre;
	}

	public void setPadre(NodoPadre padre) {
		this.padre = padre;
	}

	// -----------------------------------------------------------------------------------------------------

	public int altura(Nodo n) {
		if (n == null)
			return -1;

		if (n instanceof NodoPadre) {
			NodoPadre aux = (NodoPadre) n;

			int nw = (aux.getNw() == null ? 0 : 1 + altura(aux.getNw()));
			int ne = (aux.getNe() == null ? 0 : 1 + altura(aux.getNe()));
			int sw = (aux.getSw() == null ? 0 : 1 + altura(aux.getSw()));
			int se = (aux.getSe() == null ? 0 : 1 + altura(aux.getSe()));

			return Math.max(Math.max(nw, ne), Math.max(sw, se));
		} else
			return 0;
	}

	public int altura() {
		return altura(this);
	}

	// -----------------------------------------------------------------------------------------------------

	public int nivelNodo(Nodo e) {
		int nivel = 0;
		Nodo hijo = e;
		if (hijo.getPadre() == null) {
			nivel = 0;
		} else {
			while (hijo.getPadre() != null) {
				nivel++;
				hijo = hijo.getPadre();
			}
		}
		return nivel;
	}
	
	// -----------------------------------------------------------------------------------------------------

}
