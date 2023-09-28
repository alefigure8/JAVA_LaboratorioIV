package ejercicio2;

public class ProductoFresco extends Producto  {

	private String fechaenvasado;
	private String paisdeorigen;

	public ProductoFresco(String fechadecaducidad, int lote, String fechaenvasado, String paisdeorigen) {
		super(fechadecaducidad, lote);
		this.fechaenvasado = fechaenvasado;		
		this.paisdeorigen = paisdeorigen;
	}
	
	public ProductoFresco() {
		super();
		this.fechaenvasado = "no tiene";		
		this.paisdeorigen = "default";
	}

	public String getFechaenvasado() {
		return fechaenvasado;
	}

	public void setFechaenvasado(String fechaenvasado) {
		this.fechaenvasado = fechaenvasado;
	}

	public String getPaisdeorigen() {
		return paisdeorigen;
	}

	public void setPaisdeorigen(String paisdeorigen) {
		this.paisdeorigen = paisdeorigen;
	}

	@Override
	public String toString() {
		return super.toString() + ", Producto fresco, Fecha de envasado=" + fechaenvasado + ", Pais de origen: " + paisdeorigen;
	}
	
	
}
