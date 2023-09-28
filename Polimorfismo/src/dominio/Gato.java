package dominio;

public class Gato extends Animal {
	
	public Gato()
	{
		super();
	}
	
	public Gato(String nombre)
	{
		super(nombre);
	}

	@Override
	public String toString() {
		return "Gato [getNombre()=" + getNombre() + "]";
	}
	
	@Override
	public String Sonido()
	{
		return "Meoooow";
	}
}
