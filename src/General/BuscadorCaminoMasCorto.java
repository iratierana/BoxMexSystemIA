package General;

import java.util.ArrayList;
import java.util.List;


public class BuscadorCaminoMasCorto {

	ListaRutas listaRutas;
	Espacio espacio;
	Punto origen, destino;
	Ruta rutaMasCorta;
	
	ArrayList<Integer> distancias = new ArrayList<Integer>();
	ArrayList<Estanteria> estanterias;
	Campo campo;
	
	public BuscadorCaminoMasCorto(Espacio espacio, ArrayList<Estanteria> estanterias){
		this.espacio = espacio;
		this.origen = espacio.getOrigen();
		this.destino = espacio.getDestino();
		this.estanterias = estanterias;
		
	}

	private int manhattan(Punto punto1, Punto punto2) {
		
		return Math.abs(punto1.getCoordenadaX() - punto2.getCoordenadaX()) + Math.abs(punto1.getCoordenadaY() - punto2.getCoordenadaY());
	}
	
	public Ruta buscarCaminoMasCorto(){
		listaRutas = new ListaRutas();
		listaRutas.add(new Ruta(origen,this.manhattan(origen,destino),0));
		
		while (!listaRutas.vacia()){
			Ruta ruta = listaRutas.take();
			
			Nodo nodo = ruta.getLastNodo();
			if (nodo.getPunto().equals(destino)){
				rutaMasCorta = ruta;
				break;
			}
			
			List<Nodo> adyacentes = espacio.getNodosAdyacentes (nodo);
			for (Nodo n : adyacentes){
				n.setDistancia(this.manhattan(n.getPunto(), destino));
			}
			
			listaRutas.addRutas(ruta,adyacentes);
		}
		
		return rutaMasCorta;
	}
	
	
	public void hacerCaminoMasCorto(ArrayList<Integer> listaIdProductos, String accion) throws InterruptedException{
		Combinador combinador;
		ArrayList<ArrayList<Integer>> rutaDeRutas = new ArrayList<ArrayList<Integer>>();
		
		combinador = new Combinador(listaIdProductos);
		System.out.println("Product2"+listaIdProductos);
		combinador.buscarCombinaciones();
		combinador.printCombinaciones();
		rutaDeRutas = combinador.getCombinaciones();
		System.out.println("RutaDeRutas"+rutaDeRutas.size());
		
		for (int i = 0; i < rutaDeRutas.size(); i++){
			System.out.println("i"+i);
			distancias.add(calcularDistanciaDeCadaRuta(rutaDeRutas.get(i)));
		}
		System.out.println("SELECCIONAR");
		int indice = seleccionarRutaMasCorta();
		
		realizarRuta(rutaDeRutas.get(indice), listaIdProductos, accion);
	}
	
	private void realizarRuta(ArrayList<Integer> ruta, ArrayList<Integer> listaIdProductos, String accion) throws InterruptedException {
		System.out.println("REALIZAR");
		campo = new Campo();
		
		//INICIO -> PRIMER PUNTO
		espacio.origen.setCoordenadaX(1);
		espacio.origen.setCoordenadaY(1);

		espacio.destino.setCoordenadaX(estanterias.get(ruta.get(0)-1).puntoDeRecojida.punto.coordenadaX);
		espacio.destino.setCoordenadaY(estanterias.get(ruta.get(0)-1).puntoDeRecojida.punto.coordenadaY);
		
		if (accion.equals("sacar")) {
			sacarProductos(estanterias.get(ruta.get(0)-1).puntoDeRecojida.punto.coordenadaX, estanterias.get(ruta.get(0)-1).puntoDeRecojida.punto.coordenadaY, listaIdProductos);

		}else if (accion.equals("colocar")) {
			colocarProductos(estanterias.get(ruta.get(0)-1).puntoDeRecojida.punto.coordenadaX, estanterias.get(ruta.get(0)-1).puntoDeRecojida.punto.coordenadaY, listaIdProductos);
		}
		
		Ruta rutaAux = buscarCaminoMasCorto();
		campo.verRutaEnEspacio(rutaAux);
		
		// PRIMER -> HASTA ULTIMO
		
		for (int i = 0; i < ruta.size() - 1; i++) {
			espacio.origen.setCoordenadaX(estanterias.get(ruta.get(i)-1).puntoDeRecojida.punto.coordenadaX);
			espacio.origen.setCoordenadaY(estanterias.get(ruta.get(i)-1).puntoDeRecojida.punto.coordenadaY);
			
			espacio.destino.setCoordenadaX(estanterias.get(ruta.get(i+1)-1).puntoDeRecojida.punto.coordenadaX);
			espacio.destino.setCoordenadaY(estanterias.get(ruta.get(i+1)-1).puntoDeRecojida.punto.coordenadaY);
			
			if (accion.equals("sacar")) {
				sacarProductos(estanterias.get(ruta.get(i+1)-1).puntoDeRecojida.punto.coordenadaX, estanterias.get(ruta.get(i+1)-1).puntoDeRecojida.punto.coordenadaY, listaIdProductos);

			}else if (accion.equals("colocar")) {
				colocarProductos(estanterias.get(ruta.get(i+1)-1).puntoDeRecojida.punto.coordenadaX, estanterias.get(ruta.get(i+1)-1).puntoDeRecojida.punto.coordenadaY, listaIdProductos);
			}
			rutaAux = buscarCaminoMasCorto();
			campo.verRutaEnEspacio(rutaAux);
		}
		
		// ULTIMO -> INICIO
		
		espacio.origen.setCoordenadaX(estanterias.get(ruta.get(ruta.size() - 1)).puntoDeRecojida.punto.coordenadaX);
		espacio.origen.setCoordenadaY(estanterias.get(ruta.get(ruta.size() - 1)).puntoDeRecojida.punto.coordenadaY);
		
		espacio.destino.setCoordenadaX(1);
		espacio.destino.setCoordenadaY(1);
		
		rutaAux = buscarCaminoMasCorto();
		campo.verRutaEnEspacio(rutaAux);
		
	}
	
	private void colocarProductos(int x, int y, ArrayList<Integer> listaIdProductos) throws InterruptedException{
				
		String coordenadaX = String.valueOf(x);
		System.out.println("CoordenadaX" + coordenadaX);
		String coordenadaY = String.valueOf(y);
		System.out.println("CoordenadaX" + coordenadaY);

		
		String concatenado = coordenadaX + coordenadaY;
		System.out.println("Coordenadas concatenadas -->" + concatenado);

		switch (concatenado) {
		case "23":
			Thread.sleep(2000);
			System.out.println("Estanteria 1");
			for (int i = 0; i < listaIdProductos.size(); i++) {
				if(listaIdProductos.get(i).equals(1)){
					listaIdProductos.remove(i);
					System.out.println("Elemento 1 removido de robot");
				}
			}
			
			break;

		case "25":
			Thread.sleep(2000);
			System.out.println("Estanteria 2");
			for (int i = 0; i < listaIdProductos.size(); i++) {
				if(listaIdProductos.get(i).equals(2)){
					listaIdProductos.remove(i);
					System.out.println("Elemento 2 removido de robot");
				}
			}

			break;

			
		case "27":
			Thread.sleep(2000);
			System.out.println("Estanteria 3");
			for (int i = 0; i < listaIdProductos.size(); i++) {
				if(listaIdProductos.get(i).equals(3)){
					listaIdProductos.remove(i);
					System.out.println("Elemento 3 removido de robot");
				}
			}

			break;
			
		case "43":
			Thread.sleep(2000);
			System.out.println("Estanteria 4");
			for (int i = 0; i < listaIdProductos.size(); i++) {
				if(listaIdProductos.get(i).equals(4)){
					listaIdProductos.remove(i);
					System.out.println("Elemento 4 removido de robot");
				}
			}

			break;
			
		case "45":
			Thread.sleep(2000);
			System.out.println("Estanteria 5");
			for (int i = 0; i < listaIdProductos.size(); i++) {
				if(listaIdProductos.get(i).equals(5)){
					listaIdProductos.remove(i);
					System.out.println("Elemento 5 removido de robot");
				}
			}

			break;
			
		case "47":
			Thread.sleep(2000);
			System.out.println("Estanteria 6");
			for (int i = 0; i < listaIdProductos.size(); i++) {
				if(listaIdProductos.get(i).equals(6)){
					listaIdProductos.remove(i);
					System.out.println("Elemento 6 removido de robot");
				}
			}

			break;
			
		case "63":
			Thread.sleep(2000);
			System.out.println("Estanteria 7");
			for (int i = 0; i < listaIdProductos.size(); i++) {
				if(listaIdProductos.get(i).equals(7)){
					listaIdProductos.remove(i);
					System.out.println("Elemento 7 removido de robot");
				}
			}

			break;
			
		case "65":
			Thread.sleep(2000);
			System.out.println("Estanteria 8");
			for (int i = 0; i < listaIdProductos.size(); i++) {
				if(listaIdProductos.get(i).equals(8)){
					listaIdProductos.remove(i);
					System.out.println("Elemento 8 removido de robot");
				}
			}

			break;
			
		case "67":
			Thread.sleep(2000);
			System.out.println("Estanteria 9");
			for (int i = 0; i < listaIdProductos.size(); i++) {
				if(listaIdProductos.get(i).equals(9)){
					listaIdProductos.remove(i);
					System.out.println("Elemento 9 removido de robot");
				}
			}

			break;
			
		default:
			System.out.println("No existe ese punto de recogida" + concatenado);
			break;
		}
	}
	
	private void sacarProductos(int x, int y, ArrayList<Integer> listaIdProductos){
		
		String coordenadaX = String.valueOf(x);
		String coordenadaY = String.valueOf(y);

		String concatenado = coordenadaX + coordenadaY;

		switch (concatenado) {
		case "23":
			System.out.println("Estanteria 1");
			for (int i = 0; i < listaIdProductos.size(); i++) {
				if(listaIdProductos.get(i).equals(1)){
					listaIdProductos.remove(i);
					System.out.println("Elemento 1 removido de la estanteria");
				}
			}
			
			break;

		case "25":
			System.out.println("Estanteria 2");
			for (int i = 0; i < listaIdProductos.size(); i++) {
				if(listaIdProductos.get(i).equals(2)){
					listaIdProductos.remove(i);
					System.out.println("Elemento 2 removido de la estanteria");
				}
			}

			break;

			
		case "27":
			System.out.println("Estanteria 3");
			for (int i = 0; i < listaIdProductos.size(); i++) {
				if(listaIdProductos.get(i).equals(3)){
					listaIdProductos.remove(i);
					System.out.println("Elemento 3 removido de la estanteria");
				}
			}

			break;
			
		case "43":
			System.out.println("Estanteria 4");
			for (int i = 0; i < listaIdProductos.size(); i++) {
				if(listaIdProductos.get(i).equals(4)){
					listaIdProductos.remove(i);
					System.out.println("Elemento 4 removido de la estanteria");
				}
			}

			break;
			
		case "45":
			System.out.println("Estanteria 5");
			for (int i = 0; i < listaIdProductos.size(); i++) {
				if(listaIdProductos.get(i).equals(5)){
					listaIdProductos.remove(i);
					System.out.println("Elemento 5 removido de la estanteria");
				}
			}

			break;
			
		case "47":
			System.out.println("Estanteria 6");
			for (int i = 0; i < listaIdProductos.size(); i++) {
				if(listaIdProductos.get(i).equals(6)){
					listaIdProductos.remove(i);
					System.out.println("Elemento 6 removido de la estanteria");
				}
			}

			break;
			
		case "63":
			System.out.println("Estanteria 7");
			for (int i = 0; i < listaIdProductos.size(); i++) {
				if(listaIdProductos.get(i).equals(7)){
					listaIdProductos.remove(i);
					System.out.println("Elemento 7 removido de la estanteria");
				}
			}

			break;
			
		case "65":
			System.out.println("Estanteria 8");
			for (int i = 0; i < listaIdProductos.size(); i++) {
				if(listaIdProductos.get(i).equals(8)){
					listaIdProductos.remove(i);
					System.out.println("Elemento 8 removido de la estanteria");
				}
			}

			break;
			
		case "67":
			System.out.println("Estanteria 9");
			for (int i = 0; i < listaIdProductos.size(); i++) {
				if(listaIdProductos.get(i).equals(9)){
					listaIdProductos.remove(i);
					System.out.println("Elemento 9 removido de la estanteria");
				}
			}

			break;
			
		default:
			System.out.println("No existe ese punto de recogida" + concatenado);
			break;
		}
	}

	private int seleccionarRutaMasCorta() {
		
		int distanciaMenor = distancias.get(0);
		int indice = 0;
		
		for (int i = 1; i < distancias.size(); i++) {
			if(distanciaMenor > distancias.get(i)){
				distanciaMenor = distancias.get(i);
				indice = i;
			}
		}
		
		return indice;
	}
	
	public int calcularDistanciaDeCadaRuta(ArrayList<Integer> ruta) {
		int sumaRuta = 0;
		
		//INICIO -> PRIMER PUNTO
		espacio.origen.setCoordenadaX(1);
		espacio.origen.setCoordenadaY(1);
		System.out.println("Step0");

		espacio.destino.setCoordenadaX(estanterias.get(ruta.get(0)).puntoDeRecojida.punto.coordenadaX);
		espacio.destino.setCoordenadaY(estanterias.get(ruta.get(0)).puntoDeRecojida.punto.coordenadaY);
		System.out.println("Step1");
		Ruta rutaAux = buscarCaminoMasCorto();
		sumaRuta = sumaRuta + rutaAux.recorrido;
		
		// PRIMER -> HASTA ULTIMO
		
		for (int i = 0; i < ruta.size() - 1; i++) {
			
			espacio.origen.setCoordenadaX(estanterias.get(ruta.get(i)).puntoDeRecojida.punto.coordenadaX);
			espacio.origen.setCoordenadaY(estanterias.get(ruta.get(i)).puntoDeRecojida.punto.coordenadaY);
			
			espacio.destino.setCoordenadaX(estanterias.get(ruta.get(i+1)).puntoDeRecojida.punto.coordenadaX);
			espacio.destino.setCoordenadaY(estanterias.get(ruta.get(i+1)).puntoDeRecojida.punto.coordenadaY);
			System.out.println("Step2");
			rutaAux = buscarCaminoMasCorto();
			sumaRuta = sumaRuta + rutaAux.recorrido;
		}
		
		// ULTIMO -> INICIO
		
		espacio.origen.setCoordenadaX(estanterias.get(ruta.get(ruta.size() - 1)).puntoDeRecojida.punto.coordenadaX);
		espacio.origen.setCoordenadaY(estanterias.get(ruta.get(ruta.size() - 1)).puntoDeRecojida.punto.coordenadaY);
		
		espacio.destino.setCoordenadaX(1);
		espacio.destino.setCoordenadaY(1);
		System.out.println("Step3");
		rutaAux = buscarCaminoMasCorto();
		sumaRuta = sumaRuta + rutaAux.recorrido;
		
		System.out.println("-----");

		return sumaRuta;
	}
}
