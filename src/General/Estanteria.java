package General;

public class Estanteria {
	
	Punto punto;
	PuntoDeRecojida puntoDeRecojida;
	int estanteriaId;
	String nombre;
	String producto;

	
	public Estanteria(int xEstanteria, int yEstanteria, int xPuntoRecojida, int yPuntoRecojida,int estanteriaId, String nombre, String producto){
		punto = new Punto(xEstanteria, yEstanteria);
		puntoDeRecojida = new PuntoDeRecojida(xPuntoRecojida, yPuntoRecojida);
		this.nombre = nombre;
		this.producto = producto;
		this.estanteriaId = estanteriaId;
	}
}
