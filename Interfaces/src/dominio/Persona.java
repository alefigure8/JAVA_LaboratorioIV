package dominio;

public class Persona implements Comparable<Persona>{
	
	private String nombre;
	private String apellido;
	
	public Persona()
	{
		this.apellido = "No tiene apellido";
		this.nombre = "No tiene apellido";
	}
	
	public Persona(String nombre, String apellido)
	{
		super();
		this.apellido = apellido;
		this.nombre = nombre;
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
	
	@Override
	public String toString()
	{
		return "El nombre es " + this.nombre + ". El apellido es " + this.apellido + ".";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellido == null) ? 0 : apellido.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		if (apellido == null) {
			if (other.apellido != null)
				return false;
		} else if (!apellido.equals(other.apellido))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	@Override
	public int compareTo(Persona o) {
		if(o.nombre == this.nombre)
			return 0;
		
		for(int i = 0; i < this.nombre.toCharArray().length; i++)
		{
			if(this.nombre.toCharArray()[i] != o.nombre.toCharArray()[i])
				return -1;
		}
		
		return 1;
	}
	
	
	
}
