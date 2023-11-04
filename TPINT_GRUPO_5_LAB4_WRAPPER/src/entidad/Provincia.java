package entidad;

import java.util.ArrayList;

public class Provincia {

    private int idProvincia;
    private String nombre;
    
    // CONSTRUCTORES
    
	public Provincia() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Provincia(int idProvincia, String nombre) {
		super();
		this.idProvincia = idProvincia;
		this.nombre = nombre;
	}

	// GETTERS & SETTERS
	
	public int getIdProvincia() {
		return this.idProvincia;
	}

	public void setIdProvincia(int idProvincia) {
		this.idProvincia = idProvincia;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	// TO STRING
	
	@Override
	public String toString() {
		return "Provincia [idProvincia=" + idProvincia + ", nombre=" + nombre + "]";
	}

	
	
}
