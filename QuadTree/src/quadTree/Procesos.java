package quadTree;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Procesos extends JFrame {

	
	int lado = 1;
	BufferedImage imagen;

	public Procesos() {
	
	}
	
	public QuadTree imageToArbol(BufferedImage img) {
		lado = img.getHeight();
		return new QuadTree(imagenToArbol(img));
	}
	public Nodo imagenToArbol(BufferedImage img) {
		Nodo root = new Nodo();
		return imagenToArbol(img, root);
	}
	public Nodo imagenToArbol(BufferedImage img, Nodo r) {
		if (img.getWidth() == 1) {
			Nodo hoja = new Nodo(new Color(img.getRGB(0, 0)));
			return hoja;
		} else {
			if (img.getHeight() <= lado*0.03125) {
				
//				Color nw = promedio(nw(img));
//				Color ne = promedio(ne(img));
//				Color se = promedio(sw(img));
//				Color sw = promedio(sw(img));

//				Color nw = puntoMedio(nw(img));
//				Color ne = puntoMedio(ne(img));
//				Color se = puntoMedio(sw(img));
//				Color sw = puntoMedio(sw(img));

				Color nw = puntosAzar(nw(img));
				Color ne = puntosAzar(ne(img));
				Color se = puntosAzar(sw(img));
				Color sw = puntosAzar(sw(img));

				if (nw.getRGB() == ne.getRGB() && ne.getRGB() == se.getRGB() && se.getRGB() == sw.getRGB()) {
					return new Nodo(nw);
					
				} else {
					r.setNw(imagenToArbol(nw(img)));
					r.setNe(imagenToArbol(ne(img)));
					r.setSe(imagenToArbol(se(img)));
					r.setSw(imagenToArbol(sw(img)));
				}
			} else {
				r.setNw(imagenToArbol(nw(img)));
				r.setNe(imagenToArbol(ne(img)));
				r.setSe(imagenToArbol(se(img)));
				r.setSw(imagenToArbol(sw(img)));
			}
		}
		return r;
	}
	
	public Color puntoMedio(BufferedImage img) {
		return new Color(img.getRGB(img.getHeight() / 2, img.getWidth() / 2));
	}
	
	public Color puntosAzar(BufferedImage img) {
		int r = 0, g = 0, b = 0;
		for (int i = 0; i < img.getHeight(); i++) {
			int x = (int) (Math.random() * img.getHeight());
			int y = (int) (Math.random() * img.getHeight());
			r += new Color(img.getRGB(x, y)).getRed();
			b += new Color(img.getRGB(x, y)).getBlue();
			g += new Color(img.getRGB(x, y)).getGreen();
		}
		//return new Color(r /5, g /5, b /5);
		return new Color(r/img.getHeight(), g/img.getHeight() , b/img.getHeight());
	}
	
	public Color promedio(BufferedImage img) {
		int r = 0, g = 0, b = 0;
		int l=img.getHeight();
		for (int i = 0; i < l; i++) {
			for (int j = 0; j < l; j++) {
				r += new Color(img.getRGB(i, j)).getRed();
				b += new Color(img.getRGB(i, j)).getBlue();
				g += new Color(img.getRGB(i, j)).getGreen();
			}
		}
		return new Color(r / (int)Math.pow(l, 2), g /(int)Math.pow(l, 2), b /(int)Math.pow(l, 2));
	}

	public BufferedImage nw(BufferedImage img) {
		return img.getSubimage(0, 0, img.getWidth() / 2, img.getHeight() / 2);
	}
	public BufferedImage ne(BufferedImage img) {
		return img.getSubimage(img.getWidth() / 2, 0, img.getWidth() / 2, img.getHeight() / 2);
	}
	public BufferedImage se(BufferedImage img) {
		return img.getSubimage(img.getWidth() / 2, img.getHeight() / 2, img.getWidth() / 2, img.getHeight() / 2);
	}
	public BufferedImage sw(BufferedImage img) {
		return img.getSubimage(0, img.getHeight() / 2, img.getWidth() / 2, img.getHeight() / 2);
	}

	public BufferedImage arbolToImagen(QuadTree arbol) {
		imagen = new BufferedImage(lado, lado, BufferedImage.TYPE_INT_RGB);
		return arbolToImagen(arbol.getRoot(), lado, 0, 0);
	}
	
	public BufferedImage arbolToImagen(Nodo n, int res, int x, int y) {
		if (n.isHoja()) {
			pintar(x, y, res, n.getColor());
		} else {
			arbolToImagen(n.getNw(), res / 2, x, y);
			x += res / 2;
			arbolToImagen(n.getNe(), res / 2, x, y);
			y += res / 2;
			arbolToImagen(n.getSe(), res / 2, x, y);
			x -= res / 2;
			arbolToImagen(n.getSw(), res / 2, x, y);
		}
		return imagen;
	}

	public void pintar(int x, int y, int lado, Color color) {
		try {
			if (lado > 1) {
				for (int i = 0; i < lado; i++) {
					for (int j = 0; j < lado; j++) {
						imagen.setRGB(x + i, y + j, color.getRGB());
					}
				}
			} else
				imagen.setRGB(x, y, color.getRGB());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
