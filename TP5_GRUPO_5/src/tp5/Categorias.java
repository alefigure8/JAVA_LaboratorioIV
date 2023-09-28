package tp5;

public class Categorias {

	private static int cont = 0;
	private String genero;
	private final int ID;
	
	public Categorias(String Genero) {
		super();
		genero = Genero;
		ID = cont;
		cont++;
	}
		
	public int getId() {
		return ID;
	}
	
	public String getGenero() {
		return genero;
	}
	
	public void setGenero(String Genero) {
		genero = Genero;
	}
	
}
