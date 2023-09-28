package dominio;

public class Perro extends Animal{
	
	private String raza;
	
	public Perro(String nombre, String raza)
	{
		super(nombre);
		this.raza = raza;
	}
	
	public Perro()
	{
		super();
		this.raza = "Sin raza";
	}

	//Getters & Setters
	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	@Override
	public String toString() {
		return "Perro [raza=" + raza + " nombre="+ this.getNombre() +"]";
	}
	
	@Override
	public String Sonido()
	{
		return "Woooof";
	}
}
