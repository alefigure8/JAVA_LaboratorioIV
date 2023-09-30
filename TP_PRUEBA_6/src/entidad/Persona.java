package entidad;

public class Persona 
{
	private String Dni;
	private String nombre;
	private String apellido;

	public Persona()
	{
		
	}
					
	public Persona(String Dni, String nombre, String apellido)
	{
		this.Dni = Dni;
		this.nombre = nombre;
		this.apellido = apellido;
	}


	public String getDni() {
		return Dni;
	}


	public void setDni(String dni) {
		Dni = dni;
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
	
}
