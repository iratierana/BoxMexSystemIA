package General;

public class Principal {

	Espacio espacio;
	BuscadorCaminoMasCorto buscador;
	Ruta rutaMasCorta;
	BuscarCaminoPredeterminado caminoPredeterminado;
	
	public Principal(){
		espacio = new Espacio();
		espacio.setOrigen(new Punto(2,3));
		espacio.setDestino(new Punto (6,7));
		buscador = new BuscadorCaminoMasCorto(espacio);
	}
	
	public void buscarCaminoMasCorto(){
		rutaMasCorta = buscador.buscarCaminoMasCorto();
	}
	
	private void mostrarCaminoMasCorto() {
		espacio.verRutaEnEspacio(rutaMasCorta);	
	}
	
	private void caminoPredetermindado(){
		caminoPredeterminado.caminoPredeterminado();
	}
	
	private void inicializarEstanterias() {
		Estanteria estanteria = new Estanteria(2, 2, 2, 3);
		Estanteria estanteria1 = new Estanteria(2, 4, 2, 5);
		Estanteria estanteria2 = new Estanteria(2, 6, 2, 7);
		
		Estanteria estanteria3 = new Estanteria(4, 2, 4, 3);
		Estanteria estanteria4 = new Estanteria(4, 4, 4, 5);
		Estanteria estanteria5 = new Estanteria(4, 6, 4, 7);
		
		Estanteria estanteria6 = new Estanteria(6, 2, 6, 3);
		Estanteria estanteria7 = new Estanteria(6, 4, 6, 5);
		Estanteria estanteria8 = new Estanteria(6, 6, 6, 7);
		
	}
	
	public static void main(String[] args) {
		Principal programa = new Principal();
		long instanteInicial = System.currentTimeMillis();
		programa.inicializarEstanterias();
		//if(con ia?)
		//true
		programa.buscarCaminoMasCorto();
		//false
		programa.caminoPredetermindado();
		long instanteFinal = System.currentTimeMillis();
		System.out.println("Tiempo utilizado:  "+ (instanteFinal - instanteInicial));
		programa.mostrarCaminoMasCorto();
	}
	
	

}
