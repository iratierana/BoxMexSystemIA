package General;

public class Estanteria {
	
	Punto punto;
	PuntoDeRecojida puntoDeRecojida;
	int estanteriaId;
	String nombre;

	
	public Estanteria(int xEstanteria, int yEstanteria, int xPuntoRecojida, int yPuntoRecojida,int estanteriaId, String nombre){
		punto = new Punto(xEstanteria, yEstanteria);
		puntoDeRecojida = new PuntoDeRecojida(xPuntoRecojida, yPuntoRecojida);
		this.nombre = nombre;
		this.estanteriaId = estanteriaId;
	}
}
