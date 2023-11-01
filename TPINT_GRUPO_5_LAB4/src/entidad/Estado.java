package entidad;

public class Estado {
	
	private int idEstado;
	private String descripcion;
	
	// CONSTRUCTORES 
	
	public Estado() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Estado(int idEstado, String descripcion) {
		super();
		this.idEstado = idEstado;
		this.descripcion = descripcion;
	}
	
	// GETTERS & SETTERS

	public int getIdEstado() {
		return this.idEstado;
	}

	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
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
		return "Estado [idEstado=" + idEstado + ", descripcion=" + descripcion + "]";
	}
	

}
