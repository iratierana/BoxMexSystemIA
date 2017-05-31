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
	
	
	public void hacerCaminoMasCorto(ArrayList<Integer> listaIdProductos){
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
		
		realizarRuta(rutaDeRutas.get(indice));
	}
	
	private void realizarRuta(ArrayList<Integer> ruta) {
		System.out.println("REALIZAR");
		campo = new Campo();
		
		//INICIO -> PRIMER PUNTO
		espacio.origen.setCoordenadaX(1);
		espacio.origen.setCoordenadaY(1);

		espacio.destino.setCoordenadaX(estanterias.get(ruta.get(0)).puntoDeRecojida.punto.coordenadaX);
		espacio.destino.setCoordenadaY(estanterias.get(ruta.get(0)).puntoDeRecojida.punto.coordenadaY);
		
		Ruta rutaAux = buscarCaminoMasCorto();
		campo.verRutaEnEspacio(rutaAux);
		
		// PRIMER -> HASTA ULTIMO
		
		for (int i = 0; i < ruta.size() - 1; i++) {
			espacio.origen.setCoordenadaX(estanterias.get(ruta.get(i)).puntoDeRecojida.punto.coordenadaX);
			espacio.origen.setCoordenadaY(estanterias.get(ruta.get(i)).puntoDeRecojida.punto.coordenadaY);
			
			espacio.destino.setCoordenadaX(estanterias.get(ruta.get(i+1)).puntoDeRecojida.punto.coordenadaX);
			espacio.destino.setCoordenadaY(estanterias.get(ruta.get(i+1)).puntoDeRecojida.punto.coordenadaY);
			
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
