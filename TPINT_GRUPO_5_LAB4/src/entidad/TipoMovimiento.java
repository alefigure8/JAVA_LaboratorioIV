package entidad;

public class TipoMovimiento {

	private int id;
	private int descripcion;
	
	// CONSTRUCTORES
	
	public TipoMovimiento() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	// GETTERS & SETTERS
	
	public TipoMovimiento(int id, int descripcion) {
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
	public int getDescripcion() {
		return this.descripcion;
	}
	public void setDescripcion(int descripcion) {
		this.descripcion = descripcion;
	}
	
	// TO STRING

	@Override
	public String toString() {
		return "TipoMovimiento [id=" + id + ", descripcion=" + descripcion + "]";
	}
	
	

	
	
}
