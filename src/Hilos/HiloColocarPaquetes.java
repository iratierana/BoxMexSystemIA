package Hilos;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;

import Conversion.JSONtoObject;
import General.BuscadorCaminoMasCorto;
import General.BuscarCaminoPredeterminado;
import General.Espacio;
import General.Estanteria;
import WebClients.WebServiceClients;

public class HiloColocarPaquetes implements Runnable {
	
	Lock lock;
	String paketeEnString;
	BuscarCaminoPredeterminado caminoPredeterminado;
	public ArrayList<Integer> listaIdProductos = new ArrayList<Integer>();
	BuscadorCaminoMasCorto buscador;
	

	public HiloColocarPaquetes(ArrayList<Estanteria> estanterias, Lock lock) {
		this.buscador = new BuscadorCaminoMasCorto(new Espacio(), estanterias);
		caminoPredeterminado = new BuscarCaminoPredeterminado();
		this.lock = lock;
	}



	@Override
	public void run() {
		
		while (true) {
			try {
				lock.lock();
				paketeEnString = WebServiceClients.paketiaJasoZerbitzaritik();
				lock.unlock();
				
				if(paketeEnString != null){
					
					listaIdProductos = (ArrayList<Integer>) JSONtoObject.conversion(paketeEnString);
					System.out.println("ListaIdProductos COLOCAR PAQUETES" + listaIdProductos);
					
					lock.lock();
					boolean ia =WebServiceClients.iaAktibatutaEdoEz();
					lock.unlock();
					
					if(ia){
						long instanteInicial = System.currentTimeMillis();
						buscador.hacerCaminoMasCorto(listaIdProductos, "colocar");
						long instanteFinal = System.currentTimeMillis();
						System.out.println("Tiempo utilizado:  "+ (instanteFinal - instanteInicial));
					}else{
						long instanteInicial = System.currentTimeMillis();
						caminoPredeterminado.caminoPredeterminado(listaIdProductos);
						long instanteFinal = System.currentTimeMillis();
						System.out.println("Tiempo utilizado:  "+ (instanteFinal - instanteInicial));
					}
				}
				
				Thread.sleep(5000);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}
		
	}

}
