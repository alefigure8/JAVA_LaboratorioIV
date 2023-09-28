package ejercicio3;

public class Polideportivo extends Edificio implements InstalacionDeportiva {
	
	// atributos
	private String nombre;
	
	
	//constructores
	public Polideportivo() {
		super();
		this.nombre = "Sin Nombre";
	}
	
	public Polideportivo(String nombre, double superficie) {
		super(superficie);
		this.nombre = nombre;
	}

	
	// setters & getters
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}


	// getTipoDeInstalacion
	@Override
	public String getTipoDeInstalacion() {
		return "Instalación deportiva";
	}

	
	// toString
	@Override
	public String toString() {
		return "Polideportivo - Nombre: " + this.nombre + " - Superficie: " + this.getSuperficieEdificio() + " - Tipo de Instalación: " + this.getTipoDeInstalacion();
	}
	
}
