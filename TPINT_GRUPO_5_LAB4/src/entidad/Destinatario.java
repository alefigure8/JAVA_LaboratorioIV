package entidad;

public class Destinatario {
	private String nombre;
	private String apellido;
	private String cbu;
	private int numeroCuenta;
	
	public Destinatario () {}
	
	public Destinatario(String nombre, String apellido, String cbu, int numeroCuenta) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.cbu = cbu;
		this.numeroCuenta = numeroCuenta;
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
	
	public int getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(int numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	
}
