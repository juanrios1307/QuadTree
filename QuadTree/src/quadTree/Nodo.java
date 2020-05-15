package quadTree;


public class Nodo {

	private Rectangulo rect;
	private int altura;

	public Nodo(Rectangulo rectan) {
		super();
		this.rect = rectan;
		
	}

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
	
	public int altura (Nodo n){
		if (n== null) 
			return -1;
		
		if(n instanceof NodoPadre) {
		NodoPadre aux=(NodoPadre)n;
		
		int nw = (aux.getNw() == null? 0:1 + altura (aux.getNw()));
		int ne = (aux.getNe() == null? 0:1 + altura (aux.getNe()));
		int sw = (aux.getSw() == null? 0:1 + altura (aux.getSw()));
		int se = (aux.getSe() == null? 0:1 + altura (aux.getSe()));
		
		return Math.max(Math.max(nw,ne),Math.max(sw, se));
		}else
			return 0;
	}
	
	public int altura(){
		return altura(this);
	}
	

}
