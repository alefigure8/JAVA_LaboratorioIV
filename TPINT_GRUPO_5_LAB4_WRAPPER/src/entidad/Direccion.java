package entidad;

public class Direccion {
    
	private int id;
	private String calle;
	private int numero;	
	private TipoDireccion tipoDireccion;
	private String numeroDepartamento;
	private int codigoPostal;
	private Localidad localidad;
	private Provincia provincia;
	
	// CONSTRUCTORES
	
	public Direccion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Direccion(int id, String calle, int numero, TipoDireccion tipoDireccion, String numeroDepartamento,
			int codigoPostal, Localidad localidad, Provincia provincia) {
		super();
		this.id = id;
		this.calle = calle;
		this.numero = numero;
		this.tipoDireccion = tipoDireccion;
		this.numeroDepartamento = numeroDepartamento;
		this.codigoPostal = codigoPostal;
		this.localidad = localidad;
		this.provincia = provincia;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCalle() {
		return this.calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public int getNumero() {
		return this.numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public TipoDireccion getTipoDireccion() {
		return this.tipoDireccion;
	}

	public void setTipoDireccion(TipoDireccion tipoDireccion) {
		this.tipoDireccion = tipoDireccion;
	}

	public String getNumeroDepartamento() {
		return this.numeroDepartamento;
	}

	public void setNumeroDepartamento(String numeroDepartamento) {
		this.numeroDepartamento = numeroDepartamento;
	}

	public int getCodigoPostal() {
		return this.codigoPostal;
	}

	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public Localidad getLocalidad() {
		return this.localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	public Provincia getProvincia() {
		return this.provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	
	// TOSTRING

	@Override
	public String toString() {
		return "Direccion [id=" + id + ", calle=" + calle + ", numero=" + numero + ", tipoDireccion=" + tipoDireccion
				+ ", numeroDepartamento=" + numeroDepartamento + ", codigoPostal=" + codigoPostal + ", localidad="
				+ localidad + ", provincia=" + provincia + "]";
	}
	
	
	
}
