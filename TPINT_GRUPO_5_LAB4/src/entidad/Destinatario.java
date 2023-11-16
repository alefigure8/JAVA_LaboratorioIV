package entidad;

public class Destinatario {
	private String nombre;
	private String apellido;
	private String cbu;
	
	public Destinatario () {}
	
	public Destinatario(String nombre, String apellido, String cbu) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.cbu = cbu;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCbu() {
		return cbu;
	}

	public void setCbu(String cbu) {
		this.cbu = cbu;
	}
	
}
