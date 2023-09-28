package desktop;

public class Libro {
	private int capitulos;
	private String titulo;
	
	public Libro(int capitulos, String titulo) {
		
		this.capitulos = capitulos;
		this.titulo = titulo;

	}
	
	@Override
	public String toString() {
		
		return "Libro: " + this.titulo + " - Capitulos: " + this.capitulos;
	}
}
