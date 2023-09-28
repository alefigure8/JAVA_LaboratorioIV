package ejercicio1;

public class Empleado {
	
	private final int ID;
	private	String nombre;
	private	int edad;

// contador 
	private static int cont = 1000;

// constructores
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
	
// gets y sets
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
	
	//Metodos
	public static int devuelveProximoID()
	{
		return cont;
	}
	
	//toString
	@Override
	public String toString() {
		return "EMPLEADO ID=" + ID + ", NOMBRE=" + nombre + ", EDAD=" + edad;
	}
}
