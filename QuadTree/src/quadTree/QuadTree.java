package quadTree;

public class QuadTree {
	
	
	private Nodo root;
	
	public QuadTree(Nodo root) {
		this.root=root;
	}
	
	public int getAltura() {
		return root.getAltura();
	}
	
	public Nodo getRoot() {	
		return root;
	}

	public void recorrer() throws ExceptionNodo {
		recorrer(root);
	}
	
	public void recorrer(Nodo r) throws ExceptionNodo{
		if(r instanceof NodoPadre){
			
			NodoPadre aux=(NodoPadre)r;
			if(aux.getNw() != null){
				recorrer(aux.getNw());
			}
			if(aux.getNe() != null){
				recorrer(aux.getNe());
			}
			if(aux.getSe() != null){
				recorrer(aux.getSe());
			}
			if(aux.getSw() != null){
				recorrer(aux.getSw());
			}
		}else{
			System.out.println(((NodoHoja) r).getRect().getX());
		
		}
	}
	
	public void insertar(Nodo r) throws ExceptionNodo{

	}

	public void eliminar(Nodo r) throws ExceptionNodo{

	}

	

	
}
