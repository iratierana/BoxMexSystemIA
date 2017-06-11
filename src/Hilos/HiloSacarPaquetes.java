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
 * The Class HiloSacarPaquetes.
 */
public class HiloSacarPaquetes implements Runnable {

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

	/** The Constant SLEEP. */
	private static final int SLEEP = 5000;

	/**
	 * Instantiates a new hilo sacar paquetes.
	 *
	 * @param estanterias
	 *            the estanterias
	 * @param lock
	 *            the lock
	 */
	public HiloSacarPaquetes(final ArrayList<Estanteria> estanterias, final Lock lock) {
		this.buscador = new BuscadorCaminoMasCorto(new Espacio(), estanterias);
		caminoPredeterminado = new BuscarCaminoPredeterminado();
		this.lock = lock;
	}

	/**
	 * Este hilo se encarg de sacar los paquetes del amacaen.
	 */
	@Override
	public void run() {

		while (true) {
			try {

				lock.lock();
				paketeEnString = WebServiceClients.paketiaJasoZerbitzaritikKanporaAteratzeko();
				lock.unlock();

				if (paketeEnString != null) {

					listaIdProductos = (ArrayList<Integer>) JSONtoObject.conversion(paketeEnString);
					System.out.println("ListaIdProductos SACAR PAQUETES" + listaIdProductos);

					lock.lock();
					boolean ia = WebServiceClients.iaAktibatutaEdoEz();
					lock.unlock();

					if (ia) {
						buscador.hacerCaminoMasCorto(listaIdProductos, "sacar");
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
