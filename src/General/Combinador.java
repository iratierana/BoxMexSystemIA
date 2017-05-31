package General;

import java.util.ArrayList;
import java.util.ListIterator;

public class Combinador {

	ArrayList<Integer> paradas;
	ArrayList<ArrayList<Integer>> combinacionesParadas;
	
	public Combinador(ArrayList<Integer> paradas) {
		super();
		this.paradas = paradas;
		combinacionesParadas = new ArrayList<ArrayList<Integer>>();
	}
	
	public void buscarCombinaciones () {
		int n = paradas.size();
		int r = paradas.size();
		
		ArrayList<Integer> act = new ArrayList<Integer>();
		
		perm(paradas, act, n, r);
	}
	
	public void perm (ArrayList<Integer> elem, ArrayList<Integer> act, int n, int r) {
		if (n == 0) {
			combinacionesParadas.add(act);
		} else {
			for (int i = 0; i < r; i++) {
				if (!act.contains(elem.get(i))) {	/* CONTROLA QUE NO HAYA REPETICIONES */
					ArrayList<Integer> actAux = new ArrayList<Integer>();
					for (int t = 0; t < act.size(); t++) {
						actAux.add(act.get(t));
					}
					actAux.add(elem.get(i));
					perm(elem, actAux, n - 1, r);
				}
			}
		}
	}
	
	public void printCombinaciones () {
		for (int i = 0; i < combinacionesParadas.size(); i++) {
			System.out.print("-> " + i + ": ");
			
			for (int n = 0; n < combinacionesParadas.get(i).size(); n++) {
				System.out.print(combinacionesParadas.get(i).get(n) + " ");
			}
			
			System.out.println("");
		}
	}

	public ArrayList<ArrayList<Integer>> getCombinaciones() {
		return combinacionesParadas;
		
	}
}
