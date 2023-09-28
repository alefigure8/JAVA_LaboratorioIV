package dominio;

public class Articulo {
	
	//Atributos
	private int id;
	private String name;
	private static int cont = 0;
	
	public Articulo(String name)
	{
		contador();
		this.id = cont;
		this.name = name;
	}
	;
	public Articulo()
	{
		contador();
		this.id = cont;
		this.name = "";
	}
	
	//Getter and Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString()
	{
		return "Articulo " + this.name + " Id: " + this.id;
	}
	
	static void contador()
	{
		cont++;
	}
	
	public static int proximoId()
	{
		return cont + 1;
	}
}
