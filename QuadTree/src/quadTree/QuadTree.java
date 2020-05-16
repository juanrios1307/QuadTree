package quadTree;

public class QuadTree {
	
	
	private Nodo root;
	
	public QuadTree(Nodo root) {
		this.root=root;
	}
	
	
	public Nodo getRoot() {	
		return root;
	}

	public void recorrer() throws ExceptionNodo {
		recorrer(root);
	}
	
	public void recorrer(Nodo r) throws ExceptionNodo{
		if(r.isHoja()){

			if(r.getNw() != null){
				recorrer(r.getNw());
			}
			if(r.getNe() != null){
				recorrer(r.getNe());
			}
			if(r.getSe() != null){
				recorrer(r.getSe());
			}
			if(r.getSw() != null){
				recorrer(r.getSw());
			}
		}else{
			System.out.println(r.getRect().getX());
		
		}
	}
	
	public void insertar(Nodo r) throws ExceptionNodo{

	}

	public void eliminar(Nodo r) throws ExceptionNodo{

	}

	

	
}
