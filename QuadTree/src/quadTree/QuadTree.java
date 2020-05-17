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
		recorrer(root," ");
	}
	
	public void recorrer(Nodo r,String append) throws ExceptionNodo{
		if(!r.isHoja()){
			System.out.println(append+r);
			
				
			recorrer(r.getNw(),append+append);
			recorrer(r.getNe(),append+append);		
			recorrer(r.getSe(),append+append);
			recorrer(r.getSw(),append+append);
			
		}else{
			System.out.println(append+r);
		
		}
	}
	
	public void insertar(Nodo r) throws ExceptionNodo{

	}

	public void eliminar(Nodo r) throws ExceptionNodo{

	}

	

	
}
