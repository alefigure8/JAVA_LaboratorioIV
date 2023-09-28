package dominio;

public class ArticuloGimnasio extends Articulo{
	private int peso;
	private static final int gramos = 1000;
	
	public ArticuloGimnasio()
	{
		super();
	}
	
	public ArticuloGimnasio(String nombre, int peso)
	{
		super(nombre);
		this.peso = peso;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}
	
	@Override
	public String toString()
	{
		return super.toString() + ". Peso: " + this.peso;
	}
	
	public int kilosAGramos()
	{
		return this.peso * gramos;
	}
}
