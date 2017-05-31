package Hilos;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;

import Conversion.JSONtoObject;
import General.BuscadorCaminoMasCorto;
import General.BuscarCaminoPredeterminado;
import General.Espacio;
import General.Estanteria;
import WebClients.WebServiceClients;

public class HiloSacarPaquetes implements Runnable {
	
	Lock lock;
	String paketeEnString;
	BuscarCaminoPredeterminado caminoPredeterminado;
	public ArrayList<Integer> listaIdProductos = new ArrayList<Integer>();
	BuscadorCaminoMasCorto buscador;
	

	public HiloSacarPaquetes(ArrayList<Estanteria> estanterias, Lock lock) {
		this.buscador = new BuscadorCaminoMasCorto(new Espacio(), estanterias);
		caminoPredeterminado = new BuscarCaminoPredeterminado();
		this.lock = lock;
	}

	@Override
	public void run() {

		while (true) {
			try {
				
				lock.lock();
				paketeEnString = WebServiceClients.paketiaJasoZerbitzaritikKanporaAteratzeko();
				lock.unlock();
				
				if(paketeEnString != null){
					
					listaIdProductos = (ArrayList<Integer>) JSONtoObject.conversion(paketeEnString);
					buscador.hacerCaminoMasCorto(listaIdProductos);
					
					lock.lock();
					boolean ia =WebServiceClients.iaAktibatutaEdoEz();
					lock.unlock();
					
					if(ia){
						buscador.hacerCaminoMasCorto(listaIdProductos);
					}else{
						caminoPredeterminado.caminoPredeterminado(listaIdProductos);
					}
				}
				
				Thread.sleep(5000);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}
		
	}

}
