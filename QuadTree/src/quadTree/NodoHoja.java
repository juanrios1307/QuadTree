package quadTree;

import java.awt.Color;

public class NodoHoja extends Nodo {

	private Color color;

	public NodoHoja(Rectangulo rect, int altura, NodoPadre padre, Color color) {
		super(rect, altura, padre);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}


}