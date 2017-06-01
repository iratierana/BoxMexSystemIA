package General;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Campo {
	
	final int DIMENSION = 9;

	int espacio[][];

	public Campo() {
		espacio = new int[DIMENSION][DIMENSION];
		
		for (int x = 0; x < DIMENSION; x++){
			for (int y = 0; y < DIMENSION; y++){
				espacio[x][y] = new Integer(0);
			}
		}

		espacio[2][2] = 4;
		espacio[4][2] = 4;
		espacio[6][2] = 4;

		espacio[2][4] = 4;
		espacio[4][4] = 4;
		espacio[6][4] = 4;

		espacio[2][6] = 4;
		espacio[4][6] = 4;
		espacio[6][6] = 4;

	}


	public void verEspacio() {
		for (int i = 0; i < DIMENSION; i++) {
			for (int j = 0; j < DIMENSION; j++) {
				System.out.print(espacio[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();

	}

	public void verRutaEnEspacio(Ruta ruta) {
		Principal principal = new Principal();
		
		if (ruta == null) {
			System.out.println("No se ha encontrado camino");
		} else {
			List<Nodo> nodos = ruta.getListaNodos();
			for (Nodo nodo : nodos) {
				Punto posicion = nodo.getPunto();
				espacio[posicion.coordenadaX][posicion.coordenadaY] = 8;
			}
		}

		this.verEspacio();
	}
}
