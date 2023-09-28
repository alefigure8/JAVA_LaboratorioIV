package ejercicio1;

public class Empleado {
	private final int ID;
	private	String nombre;
	private	int edad;

	//CONTADOR
	private static int cont = 1000;

	//CONSTRUCTORES
	public Empleado(){
		this.edad = 99;
		this.nombre = "Sin Nombre";
		this.ID = cont;
		cont++;
	}
	
	public Empleado(String nombre, int edad){
		this.edad = edad;
		this.nombre = nombre;
		this.ID = cont;
		cont++;
	}

	//GETTERS Y SETTERS
	public int getId() {
		return ID;
	}

	public String getNombre() {
	return nombre;
	}

	public void setNombre(String nombre) {
	this.nombre = nombre;
	}

	public int getEdad() {
	return edad;
	}

	public void setEdad(int edad) {
	this.edad = edad;
	}
	
	//METODOS
	public static int devuelveProximoID()
	{
		return cont;
	}
	
	@Override
	public String toString() {
		return "EMPLEADO ID=" + ID + ", NOMBRE=" + nombre + ", EDAD=" + edad;
	}

	
	
}
