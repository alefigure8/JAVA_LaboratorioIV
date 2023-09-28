package ejercicio2;


public class Producto {

	 private String fechadecaducidad; 
	 private int lote;
	 

	public Producto(String fechadecaducidad, int lote) {
		this.fechadecaducidad = fechadecaducidad;
		this.lote = lote;
	}
	
	public Producto() {
		this.fechadecaducidad = "no posee";
		this.lote = 0;
	}
	public String getFechadecaducidad() {
		return fechadecaducidad;
	}

	public void setFechadecaducidad(String fechadecaducidad) {
		this.fechadecaducidad = fechadecaducidad;
	}

	public int getLote() {
		return lote;
	}

	public void setLote(int lote) {
		this.lote = lote;
	}

	@Override
	public String toString() {
		return "Producto Lote: "+ lote+ ", fecha de caducidad: " + fechadecaducidad;
	}
	 
	 
	
}
