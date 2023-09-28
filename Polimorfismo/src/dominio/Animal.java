package dominio;

public abstract class Animal {
	private String Nombre;
	
	//Constructor
	public Animal()
	{
		this.Nombre = "Sin Nombre";
	}
	
	public Animal(String nombre)
	{
		this.Nombre = nombre;
	}
	
	//Getters & Setters
	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	@Override
	public String toString() {
		return "Animal [Nombre= " + Nombre + " ]";
	}	
	
	public abstract String Sonido();
}
