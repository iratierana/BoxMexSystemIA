package General;

public class Principal {

	Espacio espacio;
	BuscadorCaminoMasCorto buscador;
	Ruta rutaMasCorta;
	
	public Principal(){
		espacio = new Espacio();
		espacio.setOrigen(new Punto(1,1));
		espacio.setDestino(new Punto (6,5));
		buscador = new BuscadorCaminoMasCorto(espacio);
	}
	public void buscarCaminoMasCorto(){
		rutaMasCorta = buscador.buscarCaminoMasCorto();
		
	}
	private void mostrarCaminoMasCorto() {
		espacio.verRutaEnEspacio(rutaMasCorta);
		
		
	}
	public static void main(String[] args) {
		Principal programa = new Principal();
		long instanteInicial = System.currentTimeMillis();
		programa.buscarCaminoMasCorto();
		long instanteFinal = System.currentTimeMillis();
		System.out.println("Tiempo utilizado:  "+ (instanteFinal - instanteInicial));
		programa.mostrarCaminoMasCorto();
	}
	

}
