package entidad;

public class Localidad {

	private int idLocalidad;
	private String nombre;	
    private int idProvincia;
    
    // CONSTRUCTORES
    
	public Localidad() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Localidad(int idLocalidad, String nombre, int idProvincia) {
		super();
		this.idLocalidad = idLocalidad;
		this.nombre = nombre;
		this.idProvincia = idProvincia;
	}
	
	// GETTERS & SETTERS

	public int getIdLocalidad() {
		return this.idLocalidad;
	}

	public void setIdLocalidad(int idLocalidad) {
		this.idLocalidad = idLocalidad;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getIdProvincia() {
		return this.idProvincia;
	}

	public void setIdProvincia(int idProvincia) {
		this.idProvincia = idProvincia;
	}

	// TO STRING
	
	@Override
	public String toString() {
		return "Localidad [idLocalidad=" + idLocalidad + ", nombre=" + nombre + ", idProvincia=" + idProvincia + "]";
	}
	 
	
}
