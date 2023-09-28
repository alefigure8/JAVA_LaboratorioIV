package ejercicio;

public class Pelicula {
	private static int ID_Pelicula = 1;
	private int id;
	private String nombre;
	private Categoria categoria;

	// CONSTRUCTOR
	public Pelicula() {
	}

	public Pelicula(String nombre, Categoria categoria) {
		this.id = ID_Pelicula++;
		this.nombre = nombre;
		this.categoria = categoria;
	}

	// GETTERS & SETTERS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public static int getIdPelicula()
	{
		return ID_Pelicula;
	}

	// METODOS
	@Override
	public String toString() {
		return "ID: " + this.id + " TITULO: " + this.nombre + this.categoria;
	}

	

}
