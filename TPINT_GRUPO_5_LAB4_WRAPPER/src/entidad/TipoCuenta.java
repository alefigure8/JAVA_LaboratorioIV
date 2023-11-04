package entidad;

public class TipoCuenta {

	 private int id;
	 private String descripcion;

	 // CONSTRUCTORES
	 
	public TipoCuenta() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TipoCuenta(int id, String descripcion) {
		super();
		this.id = id;
		this.descripcion = descripcion;
	}

	// GETTERS & SETTERS
	
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
		return "TipoCuenta [id=" + id + ", descripcion=" + descripcion + "]";
	}
	 

}
