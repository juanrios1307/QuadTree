package quadTree;

public class nodoPadre extends Nodo{
	
	private Nodo nw,ne,se,sw;

	public nodoPadre(Rectangulo rectan, int altura, Nodo nw, Nodo ne, Nodo se, Nodo sw) {
		super(rectan, altura);
		this.nw = nw;
		this.ne = ne;
		this.se = se;
		this.sw = sw;
	}

	public Nodo getNw() {
		return nw;
	}

	public void setNw(Nodo nw) {
		this.nw = nw;
	}

	public Nodo getNe() {
		return ne;
	}

	public void setNe(Nodo ne) {
		this.ne = ne;
	}

	public Nodo getSe() {
		return se;
	}

	public void setSe(Nodo se) {
		this.se = se;
	}

	public Nodo getSw() {
		return sw;
	}

	public void setSw(Nodo sw) {
		this.sw = sw;
	}
	
	

}
