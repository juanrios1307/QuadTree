package quadTree;

import java.awt.image.BufferedImage;

public class ImagenToArbol {
	
	public QuadTree procesarImagen(BufferedImage img) {
		int px=img.getHeight();
		
		for (int i = 0; i < px/4; i+=4) {
			for (int j = 0; j < px/4; j+=4) {
				img.getSubimage(i, j, 4, 4).getRGB(i+2, j+2);
			}
		}
	
		
		return null;
	}
	
}
