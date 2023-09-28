package ejercicio3;

public abstract class Edificio {

	// atributos
	private double superficieEdificio;
	
	
	// constructores
	public Edificio() {
		this.superficieEdificio = 0.0;
	}
	
	public Edificio(double superficieEdificio) {
		this.superficieEdificio = superficieEdificio;
	}

	// setters & getters
	public void setSuperficieEdificio(double superficieEdificio) {
		this.superficieEdificio = superficieEdificio;
	}
	
	public double getSuperficieEdificio() {
		return superficieEdificio;
	}
}
