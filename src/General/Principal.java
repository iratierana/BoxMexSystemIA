package General;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import Hilos.HiloColocarPaquetes;
import Hilos.HiloSacarPaquetes;

public class Principal{
	Lock lock;
	ArrayList<Estanteria> estanterias = new ArrayList<Estanteria>();
	
	
	public Principal(){
		
		this.lock = new ReentrantLock();
		
		estanterias.add(new Estanteria(2, 2, 2, 3, 1, "Estanteria1"));
		estanterias.add(new Estanteria(2, 4, 2, 5, 2, "Estanteria2"));
		estanterias.add(new Estanteria(2, 6, 2, 7, 3, "Estanteria3"));
	
		estanterias.add(new Estanteria(4, 2, 4, 3, 4, "Estanteria4"));
		estanterias.add(new Estanteria(4, 4, 4, 5, 5, "Estanteria5"));
		estanterias.add(new Estanteria(4, 6, 4, 7, 6, "Estanteria6"));
		
		estanterias.add(new Estanteria(6, 2, 6, 3, 7, "Estanteria7"));
		estanterias.add(new Estanteria(6, 4, 6, 5, 8, "Estanteria8"));
		estanterias.add(new Estanteria(6, 6, 6, 7, 9, "Estanteria9"));
		
	}
	
	private void iniciarSimulador() {


		ExecutorService threadPool = Executors.newFixedThreadPool(5);

		threadPool.submit(new HiloColocarPaquetes(estanterias, lock));
		threadPool.submit(new HiloSacarPaquetes(estanterias, lock));
		
		threadPool.shutdown();
		
	}
	
	
	public static void main(String[] args){
		Principal programa = new Principal();
		
		programa.iniciarSimulador();
			
	}






}
