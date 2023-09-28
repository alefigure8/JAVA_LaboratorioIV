package ejercicio3;

public class EdificioOficinas extends Edificio{
	
	// atributos
	private int cantidadOficinas;

	
	// constructores
	public EdificioOficinas() {
		super();
		this.cantidadOficinas = 0;
	}
	
	public EdificioOficinas(double superficie, int cantidadOficinas) {
		super(superficie);
		this.cantidadOficinas = cantidadOficinas;
	}

	
	// setters & getters
	public int getCantidadOficinas() {
		return cantidadOficinas;
	}

	public void setCantidadOficinas(int cantidadOficinas) {
		this.cantidadOficinas = cantidadOficinas;
	}

	
	//toString
	@Override
	public String toString() {
		return "Edificio de Oficinas - Cantidad de oficinas: " + cantidadOficinas + " - Superficie: " + this.getSuperficieEdificio();
	}
	
}
