package interfaz;

import java.awt.EventQueue;

import quadTree.ProcesosArbol;

public class Principal {
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	InterfazEntrada guiEntrada=new InterfazEntrada();
                	//InterfazFigura guiEntrada=new InterfazFigura(new ProcesosArbol().crearArbolPrueba());
            
                	guiEntrada.setVisible(true);   
            		
            		
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
		
	}
	
}
