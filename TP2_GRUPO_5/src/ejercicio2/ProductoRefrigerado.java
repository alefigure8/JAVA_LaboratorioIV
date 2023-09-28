package ejercicio2;

public class ProductoRefrigerado extends Producto  {

	private String codigosupervision;


	public ProductoRefrigerado(String fechadecaducidad, int lote, String codigosupervision) {
		super(fechadecaducidad, lote);
		this.codigosupervision = codigosupervision;
	}
	
	public ProductoRefrigerado() {
		super();
		this.codigosupervision = "no tiene codigo";
	}
	public String getCodigosupervision() {
		return codigosupervision;
	}

	public void setCodigosupervision(String codigosupervision) {
		this.codigosupervision = codigosupervision;
	}

	@Override
	public String toString() {
		return super.toString() + ", Producto refrigerado, Codigo de supervision: " + codigosupervision;
	}
	
		
}
