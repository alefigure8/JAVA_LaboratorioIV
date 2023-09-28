package dominio;

public class Cantante extends Persona implements ICantar{
	private boolean estudios;
	
	public Cantante()
	{
		this.estudios = false;
	}
	
	public Cantante(String nombre, String apellido, boolean estudio)
	{
		super(nombre, apellido);
		this. estudios = estudio;
	}

	public boolean isEstudios() {
		return estudios;
	}

	public void setEstudios(boolean estudios) {
		this.estudios = estudios;
	}

	@Override
	public String toString() {
		return "Sus estudios son " + estudios + ". " + super.toString();
	}

	@Override
	public void cantar() {
		System.out.println("Estoy cantando");
	}
	
}
