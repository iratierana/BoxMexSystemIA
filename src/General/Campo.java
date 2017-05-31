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

				/*String coordenadaX = String.valueOf(posicion.coordenadaX);
				String coordenadaY = String.valueOf(posicion.coordenadaY);

				String concatenado = coordenadaX + coordenadaY;

				int resultado = Integer.parseInt(concatenado);

				switch (resultado) {
				case 11:
					System.out.println("Estanteria 1");
					for (int i = 0; i < principal.product.size(); i++) {
						if(principal.product.get(i).equals(1)){
							principal.product.remove(i);
							System.out.println("Elemento 1 removido de robot");
						}
					}
					
					break;

				case 52:
					System.out.println("Estanteria 2");
					for (int i = 0; i < principal.product.size(); i++) {
						if(principal.product.get(i).equals(2)){
							principal.product.remove(i);
							System.out.println("Elemento 2 removido de robot");
						}
					}

					break;

					
				case 72:
					System.out.println("Estanteria 3");
					for (int i = 0; i < principal.product.size(); i++) {
						if(principal.product.get(i).equals(3)){
							principal.product.remove(i);
							System.out.println("Elemento 3 removido de robot");
						}
					}

					break;
					
				case 34:
					System.out.println("Estanteria 4");
					for (int i = 0; i < principal.product.size(); i++) {
						if(principal.product.get(i).equals(4)){
							principal.product.remove(i);
							System.out.println("Elemento 4 removido de robot");
						}
					}

					break;
					
				case 54:
					System.out.println("Estanteria 5");
					for (int i = 0; i < principal.product.size(); i++) {
						if(principal.product.get(i).equals(5)){
							principal.product.remove(i);
							System.out.println("Elemento 5 removido de robot");
						}
					}

					break;
					
				case 74:
					System.out.println("Estanteria 6");
					for (int i = 0; i < principal.product.size(); i++) {
						if(principal.product.get(i).equals(6)){
							principal.product.remove(i);
							System.out.println("Elemento 6 removido de robot");
						}
					}

					break;
					
				case 36:
					System.out.println("Estanteria 7");
					for (int i = 0; i < principal.product.size(); i++) {
						if(principal.product.get(i).equals(7)){
							principal.product.remove(i);
							System.out.println("Elemento 7 removido de robot");
						}
					}

					break;
					
				case 56:
					System.out.println("Estanteria 8");
					for (int i = 0; i < principal.product.size(); i++) {
						if(principal.product.get(i).equals(8)){
							principal.product.remove(i);
							System.out.println("Elemento 8 removido de robot");
						}
					}

					break;
					
				case 76:
					System.out.println("Estanteria 9");
					for (int i = 0; i < principal.product.size(); i++) {
						if(principal.product.get(i).equals(9)){
							principal.product.remove(i);
							System.out.println("Elemento 9 removido de robot");
						}
					}

					break;
					
				default:
					System.out.println("No existe ese punto de recogida" + resultado);
					break;
				}*/
			}
		}

		this.verEspacio();
	}
}
