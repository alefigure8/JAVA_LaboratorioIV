package entidad;

public class TipoTasa {

	private int id;
	private int cantCuotas;
	private double tasaInteres;
	
	// CONSTRUCTORES
	
	public TipoTasa() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TipoTasa(int id, int cantCuotas, double tasaInteres) {
		super();
		this.id = id;
		this.cantCuotas = cantCuotas;
		this.tasaInteres = tasaInteres;
	}

	// GETTERS & SETTERS
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getCantCuotas() {
		return cantCuotas;
	}


	public void setCantCuotas(int cantCuotas) {
		this.cantCuotas = cantCuotas;
	}


	public double getTasaInteres() {
		return tasaInteres;
	}


	public void setTasaInteres(double tasaInteres) {
		this.tasaInteres = tasaInteres;
	}

// TO STRING
	
	@Override
	public String toString() {
		return "TipoTasa [id=" + id + ", cantCuotas=" + cantCuotas + ", tasaInteres=" + tasaInteres + "]";
	}
	    
	
}
