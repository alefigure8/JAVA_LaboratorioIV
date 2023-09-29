package dominio;

public class Usuario {
	private int id;
	private String dni;
	private String nombre;
	private String apellido;
	
	public Usuario() {}
	
	
	public Usuario(String dni, String nombre, String apellido) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public String getDni() {
		return dni;
	}


	public void setDni(String dni) {
		this.dni = dni;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + "]";
	}
}
