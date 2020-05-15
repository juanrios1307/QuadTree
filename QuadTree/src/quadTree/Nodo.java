package quadTree;

public class Nodo {

	private Rectangulo rectan;
	private int altura;

	public Nodo(Rectangulo rectan, int altura) {
		super();
		this.rectan = rectan;
		this.altura = altura;
	}

	public Rectangulo getRectan() {
		return rectan;
	}

	public void setRectan(Rectangulo rectan) {
		this.rectan = rectan;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

}
