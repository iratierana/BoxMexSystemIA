package General;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import Conversion.JSONtoObject;
import WebClients.WebServiceClients;

public class Principal{

	Espacio espacio;
	Campo campo;
	BuscadorCaminoMasCorto buscador;
	Ruta rutaMasCorta;
	BuscarCaminoPredeterminado caminoPredeterminado;
	String productos;
	
	public ArrayList<Integer> product = new ArrayList<Integer>();
	ArrayList<Estanteria> estanterias = new ArrayList<Estanteria>();
	ArrayList<ArrayList<Integer>> rutaDeRutas = new ArrayList<ArrayList<Integer>>();
	ArrayList<Integer> distancias = new ArrayList<Integer>();
	Combinador combinador;
	Random random = new Random();
	
	public Principal(){
		espacio = new Espacio();
		buscador = new BuscadorCaminoMasCorto(espacio);
		caminoPredeterminado = new BuscarCaminoPredeterminado();
	}
	
	public void buscarCaminoMasCorto(){
		
		combinador = new Combinador(product);
		System.out.println("Product2"+product);
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
		
		buscador.espacio = espacio;
		Ruta rutaAux = buscador.buscarCaminoMasCorto();
		campo.verRutaEnEspacio(rutaAux);
		
		// PRIMER -> HASTA ULTIMO
		
		for (int i = 0; i < ruta.size() - 1; i++) {
			espacio.origen.setCoordenadaX(estanterias.get(ruta.get(i)).puntoDeRecojida.punto.coordenadaX);
			espacio.origen.setCoordenadaY(estanterias.get(ruta.get(i)).puntoDeRecojida.punto.coordenadaY);
			
			espacio.destino.setCoordenadaX(estanterias.get(ruta.get(i+1)).puntoDeRecojida.punto.coordenadaX);
			espacio.destino.setCoordenadaY(estanterias.get(ruta.get(i+1)).puntoDeRecojida.punto.coordenadaY);
			
			buscador.espacio = espacio;
			rutaAux = buscador.buscarCaminoMasCorto();
			campo.verRutaEnEspacio(rutaAux);
		}
		
		// ULTIMO -> INICIO
		
		espacio.origen.setCoordenadaX(estanterias.get(ruta.get(ruta.size() - 1)).puntoDeRecojida.punto.coordenadaX);
		espacio.origen.setCoordenadaY(estanterias.get(ruta.get(ruta.size() - 1)).puntoDeRecojida.punto.coordenadaY);
		
		espacio.destino.setCoordenadaX(1);
		espacio.destino.setCoordenadaY(1);
		
		buscador.espacio = espacio;
		rutaAux = buscador.buscarCaminoMasCorto();
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
		buscador.espacio = espacio;
		Ruta rutaAux = buscador.buscarCaminoMasCorto();
		sumaRuta = sumaRuta + rutaAux.recorrido;
		
		// PRIMER -> HASTA ULTIMO
		
		for (int i = 0; i < ruta.size() - 1; i++) {
			
			espacio.origen.setCoordenadaX(estanterias.get(ruta.get(i)).puntoDeRecojida.punto.coordenadaX);
			espacio.origen.setCoordenadaY(estanterias.get(ruta.get(i)).puntoDeRecojida.punto.coordenadaY);
			
			espacio.destino.setCoordenadaX(estanterias.get(ruta.get(i+1)).puntoDeRecojida.punto.coordenadaX);
			espacio.destino.setCoordenadaY(estanterias.get(ruta.get(i+1)).puntoDeRecojida.punto.coordenadaY);
			System.out.println("Step2");
			buscador.espacio = espacio;
			rutaAux = buscador.buscarCaminoMasCorto();
			sumaRuta = sumaRuta + rutaAux.recorrido;
		}
		
		// ULTIMO -> INICIO
		
		espacio.origen.setCoordenadaX(estanterias.get(ruta.get(ruta.size() - 1)).puntoDeRecojida.punto.coordenadaX);
		espacio.origen.setCoordenadaY(estanterias.get(ruta.get(ruta.size() - 1)).puntoDeRecojida.punto.coordenadaY);
		
		espacio.destino.setCoordenadaX(1);
		espacio.destino.setCoordenadaY(1);
		System.out.println("Step3");
		buscador.espacio = espacio;
		rutaAux = buscador.buscarCaminoMasCorto();
		sumaRuta = sumaRuta + rutaAux.recorrido;
		
		System.out.println("-----");

		return sumaRuta;
	}
	
	public void caminoPredetermindado(List product){
		caminoPredeterminado.caminoPredeterminado(product);
	}
	
	public void inicializarEstanterias() {
		
		estanterias.add(new Estanteria(2, 2, 2, 3, 1, "Estanteria1"));
		estanterias.add(new Estanteria(2, 4, 2, 5, 2, "Estanteria2"));
		estanterias.add(new Estanteria(2, 6, 2, 7, 3, "Estanteria3"));
	
		estanterias.add(new Estanteria(4, 2, 4, 3, 4, "Estanteria4"));
		estanterias.add(new Estanteria(4, 4, 4, 5, 5, "Estanteria5"));
		estanterias.add(new Estanteria(4, 6, 4, 7, 6, "Estanteria6"));
		
		estanterias.add(new Estanteria(6, 2, 6, 3, 7, "Estanteria7"));
		estanterias.add(new Estanteria(6, 4, 6, 5, 8, "Estanteria8"));
		estanterias.add(new Estanteria(6, 6, 6, 7, 9, "Estanteria9"));
		
		while (true) {
			try {
				
				productos = WebServiceClients.paketiaJasoZerbitzaritik();
				
				if(productos != null){
					
					product = (ArrayList<Integer>) JSONtoObject.conversion(productos);
					buscarCaminoMasCorto();
					
					if(WebServiceClients.iaAktibatutaEdoEz()){
						buscarCaminoMasCorto();
					}else{
						caminoPredetermindado(product);
					}
				}
				
				Thread.sleep(5000);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}
		
		
	}
	
	public static void main(String[] args){
		Principal programa = new Principal();
		long instanteInicial = System.currentTimeMillis();
		
		programa.inicializarEstanterias();

		
		long instanteFinal = System.currentTimeMillis();
		System.out.println("Tiempo utilizado:  "+ (instanteFinal - instanteInicial));
	
	}

	/*@Override
	public void run() {
		boolean piztuta = true;
		
		while (piztuta == true) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			productos = WebServiceClients.paketiaJasoZerbitzaritik();
			System.out.println(productos);
			
		
			product = (ArrayList<Integer>) JSONtoObject.conversion(productos);
			System.out.println(product);
			
			
			if(WebServiceClients.iaAktibatutaEdoEz()){
				buscarCaminoMasCorto();
			}else{
				caminoPredetermindado(product);
			}
			
		}
		
	}*/

}
