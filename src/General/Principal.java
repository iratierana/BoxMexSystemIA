package General;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.json.simple.parser.ParseException;

import Conversion.JSONtoObject;
import WebClients.WebServiceClients;

public class Principal  implements Runnable{

	Espacio espacio;
	BuscadorCaminoMasCorto buscador;
	Ruta rutaMasCorta;
	BuscarCaminoPredeterminado caminoPredeterminado;
	String productos;
	public ArrayList<Integer> product = new ArrayList<Integer>();
	ArrayList<Estanteria> estanterias = new ArrayList<Estanteria>();
	ArrayList<ArrayList<Integer>> rutaDeRutas = new ArrayList<ArrayList<Integer>>();
	ArrayList<Integer> distancias = new ArrayList<Integer>();
	Random random = new Random();
	
	public Principal(){
		espacio = new Espacio();
		caminoPredeterminado = new BuscarCaminoPredeterminado();
	}
	
	public void buscarCaminoMasCorto(){
		
		crearRutaDeRutas();
		
		for (int i = 0; i < rutaDeRutas.size(); i++){
			distancias.add(calcularDistanciaDeCadaRuta(rutaDeRutas.get(i)));
		}
		
		seleccionarRutaMasCorta();
		
	}
	
	private void seleccionarRutaMasCorta() {
		
				
	}

	public void crearRutaDeRutas () {
		int posicionAleatoria;
		int numPosibilidades = factorial(product.size());
		rutaDeRutas.add(product);
		
		while (rutaDeRutas.size() < numPosibilidades) {
			ArrayList<Integer> nuevaPruebaDeRuta = new ArrayList<Integer>();
			for (int i = 0; i < product.size(); i++) {
				do {
					posicionAleatoria = random.nextInt(product.size());
				}
				while (nuevaPruebaDeRuta.contains(posicionAleatoria));
				nuevaPruebaDeRuta.add(posicionAleatoria);
			}
			
			if (!rutaDeRutas.contains(nuevaPruebaDeRuta)) {
				rutaDeRutas.add(nuevaPruebaDeRuta);
			}
		}
	}
	
	public int factorial(int number) {
        if (number <= 1)
            return 1;
        else
            return number * factorial(number - 1);
    }
	
	public int calcularDistanciaDeCadaRuta(ArrayList<Integer> ruta) {
		/* Calcular la distancia para recorrer el arraylist en ese orden */
		
		espacio.setOrigen(puntosParada.get(posicion));
		
		espacio.setOrigen(new Punto(2,3));
		espacio.setDestino(new Punto (6,7));
		buscador = new BuscadorCaminoMasCorto(espacio);
		
		rutaMasCorta = buscador.buscarCaminoMasCorto();
	}
	
	public void mostrarCaminoMasCorto() {
		espacio.verRutaEnEspacio(rutaMasCorta);	
	}
	
	public void caminoPredetermindado(List product){
		caminoPredeterminado.caminoPredeterminado(product);
	}
	
	public void inicializarEstanterias() {
		estanterias.add(new Estanteria(2, 2, 2, 3, 1,  "Estanteria1"));
		estanterias.add(new Estanteria(2, 4, 2, 5, 2,  "Estanteria2"));
		estanterias.add(new Estanteria(2, 6, 2, 7, 3,  "Estanteria3"));
		
		estanterias.add(new Estanteria(4, 2, 4, 3, 4, "Estanteria4"));
		estanterias.add(new Estanteria(4, 4, 4, 5, 5, "Estanteria5"));
		estanterias.add(new Estanteria(4, 6, 4, 7, 6, "Estanteria6"));
		
		estanterias.add(new Estanteria(6, 2, 6, 3, 7, "Estanteria7"));
		estanterias.add(new Estanteria(6, 4, 6, 5, 8, "Estanteria8"));
		estanterias.add(new Estanteria(6, 6, 6, 7, 9, "Estanteria9"));
		
	}
	
	public static void main(String[] args){
		Principal programa = new Principal();
		long instanteInicial = System.currentTimeMillis();
		programa.inicializarEstanterias();
		
		
		Thread t = new Thread(new Principal());
        t.start();
		
		long instanteFinal = System.currentTimeMillis();
		System.out.println("Tiempo utilizado:  "+ (instanteFinal - instanteInicial));
		programa.mostrarCaminoMasCorto();
	}

	@Override
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
			
			try {
				product = (ArrayList<Integer>) JSONtoObject.conversion();
			} catch (IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(WebServiceClients.iaAktibatutaEdoEz()){
				buscarCaminoMasCorto();
			}else {
				caminoPredetermindado(product);
			}
			
		}
		
	}

}
