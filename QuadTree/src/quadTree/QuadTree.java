package quadTree;

public class QuadTree {
	
	private Nodo root;

	public QuadTree(Nodo root) {
		this.root=root;
	}

	public Nodo getRoot() {	
		return root;
	}

	public void recorrer() {
		recorrer(root," ");
	}
	
	public void recorrer(Nodo r,String append){
		if(!r.isHoja()){
			System.out.println(append+r);
				
			recorrer(r.getNw(),append+"  ");
			recorrer(r.getNe(),append+"  ");		
			recorrer(r.getSe(),append+"  ");
			recorrer(r.getSw(),append+"  ");
			
		}else{
			System.out.println(append+r);
		
		}
	}

	public int cantHojas(){
        
        return cantHojas(root);
    
    }
    
    public int cantHojas(Nodo n){
        if (n == null) {
            return 0;
        }
        if(n.isHoja()) {
            return 1;
        }else {
            return cantHojas(n.getNw())+cantHojas(n.getNe())+cantHojas(n.getSw())+cantHojas(n.getSe());
        }     
    }
    
    public int altura() {
    	return root.altura();
    }
	
}
