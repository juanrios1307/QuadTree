package quadTree;

import java.awt.Color;

public class NodoHoja extends Nodo {

	Color color;

	public NodoHoja(Rectangulo rect, Color color) {
		super(rect);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}