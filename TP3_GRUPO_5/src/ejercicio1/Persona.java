package ejercicio1;

public class Persona implements Comparable<Persona>{
	private String nombre;
	private String apellido;
	private String dni;
	
	public Persona() {
		super();
		this.nombre = "";
		this.apellido = "";
		this.dni = "";
	}
	
	public Persona(String nombre, String apellido, String dni) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
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
		return "Persona. " + "Apellido: " + apellido + ", Nombre: " + nombre + ", Dni: " + dni;
	}

	@Override
	public int compareTo(Persona p) {
	    int comparacionApellido = this.apellido.compareToIgnoreCase(p.apellido);

	    
	    if (comparacionApellido == 0) {
	        int comparacionNombre = this.nombre.compareToIgnoreCase(p.nombre);

	        if (comparacionNombre == 0) {
	            return this.dni.compareTo(p.dni);
	        }

	        return comparacionNombre;
	    }

	    return comparacionApellido;
	}

	
	
	

}
