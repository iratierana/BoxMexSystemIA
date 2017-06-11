package Hilos;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;

import Conversion.JSONtoObject;
import General.BuscadorCaminoMasCorto;
import General.BuscarCaminoPredeterminado;
import General.Espacio;
import General.Estanteria;
import WebClients.WebServiceClients;

/**
 * The Class HiloColocarPaquetes.
 */
public class HiloColocarPaquetes implements Runnable {

	/** The sleep. */
	private static final int SLEEP = 5000;

	/** The lock. */
	Lock lock;

	/** The pakete en string. */
	String paketeEnString;

	/** The camino predeterminado. */
	BuscarCaminoPredeterminado caminoPredeterminado;

	/** The lista id productos. */
	public ArrayList<Integer> listaIdProductos = new ArrayList<Integer>();

	/** The buscador. */
	BuscadorCaminoMasCorto buscador;

	/**
	 * Instantiates a new hilo colocar paquetes.
	 *
	 * @param estanterias
	 *            the estanterias
	 * @param lock
	 *            the lock
	 */
	public HiloColocarPaquetes(final ArrayList<Estanteria> estanterias, final Lock lock) {
		this.buscador = new BuscadorCaminoMasCorto(new Espacio(), estanterias);
		caminoPredeterminado = new BuscarCaminoPredeterminado();
		this.lock = lock;
	}

	/**
	 * Este hilo se encarda de meter paketes en el almacen.
	 */
	@Override
	public void run() {

		while (true) {
			try {
				lock.lock();
				paketeEnString = WebServiceClients.paketiaJasoZerbitzaritik();
				lock.unlock();

				if (paketeEnString != null) {

					listaIdProductos = (ArrayList<Integer>) JSONtoObject.conversion(paketeEnString);
					System.out.println("ListaIdProductos COLOCAR PAQUETES" + listaIdProductos);

					lock.lock();
					boolean ia = WebServiceClients.iaAktibatutaEdoEz();
					lock.unlock();

					if (ia) {
						buscador.hacerCaminoMasCorto(listaIdProductos, "colocar");
					} else {
						caminoPredeterminado.caminoPredeterminado(listaIdProductos);
					}
				}

				Thread.sleep(SLEEP);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
