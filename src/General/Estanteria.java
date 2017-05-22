package General;

public class Estanteria {
	
	Punto punto;
	PuntoDeRecojida puntoDeRecojida;
	int estanteriaId;
	String nombre;

	
	public Estanteria(int xEstanteria, int yEstanteria, int xPuntoRecojida, int yPuntoRecojida){
		punto = new Punto(xEstanteria, yEstanteria);
		puntoDeRecojida = new PuntoDeRecojida(xPuntoRecojida, yPuntoRecojida);
	}
}
