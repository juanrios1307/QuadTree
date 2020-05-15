package quadTree;

import java.awt.Color;

public class nodoHoja extends Nodo {

	Color color;

	public nodoHoja(Rectangulo rectan, int altura, Color color) {
		super(rectan, altura);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}