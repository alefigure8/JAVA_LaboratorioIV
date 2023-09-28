package tp5;

public class Peliculas {

	
	private String nombre;
	private Categorias categoria;
	private final int ID;
	public static int cont = 1;
	
	public Peliculas() {
		super();
		this.ID = cont;
		cont++;
	}

	public Peliculas(String nombre, Categorias categoria) {
		super();
		this.nombre = nombre;
		this.categoria = categoria;
		this.ID = cont;
		cont++;
	}

	public String getGenero() {
		return categoria.getGenero();
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Categorias getCategoria() {
		return categoria;
	}

	public void setCategoria(Categorias categoria) {
		this.categoria = categoria;
	}	
	
	public static int getContador() {
		return cont;
	}

	@Override
	public String toString() {
		return ID + "-" + nombre.toUpperCase() + "-"+ categoria.getGenero().toUpperCase();
	}
	
}
