package General;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * The Class BuscarCaminoPredeterminado.
 */
public class BuscarCaminoPredeterminado {

	/** The nombrefichero. */
	final String NOMBREFICHERO = "files/espacio.txt";

	/** The espacio. */
	int[][]espacio;

	/** The dimension. */
	final int DIMENSION = 9;

	/** The destino. */
	Punto origen, destino;

	/**
	 * Instantiates a new buscar camino predeterminado.
	 */
	public BuscarCaminoPredeterminado() {
		espacio = new int[DIMENSION][DIMENSION];
		leerEspacioFichero();
	}

	/**
	 * Leer espacio fichero.
	 */
	private void leerEspacioFichero() {
		try (BufferedReader in = new BufferedReader(new FileReader(NOMBREFICHERO))) {
			String linea;
			int fila = 0;
			while ((linea = in.readLine()) != null) {
				String[]valores = linea.split("[ ]");
				for (int i = 0; i < valores.length; i++) {
					espacio[fila][i] = Integer.valueOf(valores[i]);
				}
				fila++;
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Camino predeterminado.
	 *
	 * @param productos
	 *            the productos
	 */
	public void caminoPredeterminado(final List productos) {
		espacio[1][1] = 8;
		espacio[1][2] = 8;
		espacio[1][3] = 8;
		espacio[2][3] = 8;

		for (int i = 0; i < productos.size(); i++) {
			if (productos.get(i).equals(1)) {
				productos.remove(i);
				System.out.println("Elemento 1 removido de robot");
			}
		}

		espacio[3][3] = 8;
		espacio[4][3] = 8;

		for (int i = 0; i < productos.size(); i++) {
			if (productos.get(i).equals(4)) {
				productos.remove(i);
				System.out.println("Elemento 4 removido de robot");
			}
		}

		espacio[5][3] = 8;
		espacio[6][3] = 8;

		for (int i = 0; i < productos.size(); i++) {
			if (productos.get(i).equals(7)) {
				productos.remove(i);
				System.out.println("Elemento 7 removido de robot");
			}
		}

		espacio[7][3] = 8;
		espacio[7][4] = 8;
		espacio[7][5] = 8;
		espacio[6][5] = 8;

		for (int i = 0; i < productos.size(); i++) {
			if (productos.get(i).equals(8)) {
				productos.remove(i);
				System.out.println("Elemento 8 removido de robot");
			}
		}

		espacio[5][5] = 8;
		espacio[4][5] = 8;

		for (int i = 0; i < productos.size(); i++) {
			if (productos.get(i).equals(5)) {
				productos.remove(i);
				System.out.println("Elemento 5 removido de robot");
			}
		}

		espacio[3][5] = 8;
		espacio[2][5] = 8;

		for (int i = 0; i < productos.size(); i++) {
			if (productos.get(i).equals(2)) {
				productos.remove(i);
				System.out.println("Elemento 2 removido de robot");
			}
		}

		espacio[1][5] = 8;
		espacio[1][6] = 8;
		espacio[1][7] = 8;
		espacio[2][7] = 8;

		for (int i = 0; i < productos.size(); i++) {
			if (productos.get(i).equals(3)) {
				productos.remove(i);
				System.out.println("Elemento 3 removido de robot");
			}
		}

		espacio[3][7] = 8;
		espacio[4][7] = 8;

		for (int i = 0; i < productos.size(); i++) {
			if (productos.get(i).equals(6)) {
				productos.remove(i);
				System.out.println("Elemento 6 removido de robot");
			}
		}

		espacio[5][7] = 8;
		espacio[6][7] = 8;

		for (int i = 0; i < productos.size(); i++) {
			if (productos.get(i).equals(9)) {
				productos.remove(i);
				System.out.println("Elemento 9 removido de robot");
			}
		}

		espacio[7][7] = 8;
		this.verEspacio();
	}

	/**
	 * Ver espacio.
	 */
	public void verEspacio() {
		for (int i = 0; i < DIMENSION; i++) {
			for (int j = 0; j < DIMENSION; j++) {
				System.out.print(espacio[i][j] + " ");
			}
			System.out.println();
		}
	}
}
