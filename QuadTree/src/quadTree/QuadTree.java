package quadTree;

public class QuadTree {

	private Nodo root=new Nodo();
	
	public Nodo getRoot() {	
		return root;
	}
	
	public void setRoot(Nodo root) {
		this.root=root;
	}
	
	public void recorrer(Nodo r) throws ExceptionNodo{
		if(r instanceof NodoPadre){
			
			NodoPadre aux=(NodoPadre)r;
			if(aux.getNW() != null){
				recorrer(aux.getNW());
			}
			if(aux.getNE() != null){
				recorrer(aux.getNE());
			}
			if(aux.getSE() != null){
				recorrer(aux.getSE());
			}
			if(aux.getSW() != null){
				recorrer(aux.getSW());
			}
		}else{
			System.out.println(((NodoHoja) r).getColor());
		
		}
	}
	
	public void insertar(Nodo r) throws ExceptionNodo{

	}

	public void eliminar(Nodo r) throws ExceptionNodo{

	}

	public int getAltura() {
		return 0;
	}
	
	public static void main(String[] args) {
		
	}
	
}
