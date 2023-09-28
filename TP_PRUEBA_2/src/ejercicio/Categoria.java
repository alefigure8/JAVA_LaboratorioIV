package ejercicio;

public class Categoria {
	private String nombre;

	// CONSTRUCTORES
	public Categoria() {
	}

	public Categoria(String nombre) {
		this.nombre = nombre;
	}

	// GETTERS & SETTERS

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "GENERO: " + this.nombre;
	}

}
