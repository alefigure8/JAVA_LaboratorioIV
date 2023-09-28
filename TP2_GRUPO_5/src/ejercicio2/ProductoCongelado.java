package ejercicio2;

public class ProductoCongelado extends Producto {

	private int temperaturarecomendada;	
	
	public ProductoCongelado(String fechadecaducidad, int lote, int temperaturarecomendada) {
		super(fechadecaducidad, lote);
		this.temperaturarecomendada = temperaturarecomendada;
	}

	public ProductoCongelado() {
		super();
		this.temperaturarecomendada = 0;
	}
	
	public int getTemperaturarecomendada() {
		return temperaturarecomendada;
	}

	public void setTemperaturarecomendada(int temperaturarecomendada) {
		this.temperaturarecomendada = temperaturarecomendada;
	}

	@Override
	public String toString() {
		return super.toString() + ", Producto congelado, Temperatura recomendada: " + temperaturarecomendada;
	}
	
}
