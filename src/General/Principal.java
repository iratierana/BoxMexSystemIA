package General;

import javax.jws.WebService;

import WebClients.WebServiceClients;

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
		Estanteria estanteria1 = new Estanteria(2, 2, 2, 3, "Estanteria1", "patatas");
		Estanteria estanteria2 = new Estanteria(2, 4, 2, 5, "Estanteria2", "tomates");
		Estanteria estanteria3 = new Estanteria(2, 6, 2, 7, "Estanteria3", "lechugas");
		
		Estanteria estanteria4 = new Estanteria(4, 2, 4, 3, "Estanteria4", "almendras");
		Estanteria estanteria5 = new Estanteria(4, 4, 4, 5, "Estanteria5", "melocotones");
		Estanteria estanteria6 = new Estanteria(4, 6, 4, 7, "Estanteria6", "platanos");
		
		Estanteria estanteria7 = new Estanteria(6, 2, 6, 3, "Estanteria7", "ajos");
		Estanteria estanteria8 = new Estanteria(6, 4, 6, 5, "Estanteria8", "pepinillos");
		Estanteria estanteria9 = new Estanteria(6, 6, 6, 7, "Estanteria9", "cacahuetes");
		
	}
	
	public static void main(String[] args) {
		Principal programa = new Principal();
		long instanteInicial = System.currentTimeMillis();
		programa.inicializarEstanterias();
		
		if(WebServiceClients.iaAktibatutaEdoEz()){
			programa.buscarCaminoMasCorto();
		}else {
			programa.caminoPredetermindado();
		}
		
		long instanteFinal = System.currentTimeMillis();
		System.out.println("Tiempo utilizado:  "+ (instanteFinal - instanteInicial));
		programa.mostrarCaminoMasCorto();
	}
	
	

}
