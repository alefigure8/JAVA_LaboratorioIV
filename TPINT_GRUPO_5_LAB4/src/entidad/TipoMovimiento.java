package entidad;

public class TipoMovimiento {

	private int id;
	private String descripcion;
	
	// CONSTRUCTORES
	
	public TipoMovimiento() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	// GETTERS & SETTERS
	
	public TipoMovimiento(int id, String descripcion) {
		super();
		this.id = id;
		this.descripcion = descripcion;
	}
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescripcion() {
		return this.descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	// TO STRING

	@Override
	public String toString() {
		return "TipoMovimiento [id=" + id + ", descripcion=" + descripcion + "]";
	}
	
	

	
	
}
