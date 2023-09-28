package ejercicio1;

public class Principal {

	public static void main(String[] args) {
		//Instancia empleados
		Empleado [] empleados = new Empleado[5];	
		String [] nombreEmpleado= {"Ramón", "Pedro", null, "Ana", null};
		int [] edadEmpleado= {30,19,0,25,0};
	
		for(int x=0; x<empleados.length; x++) {
			if(nombreEmpleado[x]==null) {
				empleados[x]=new Empleado();
			}
			else {
				empleados[x]=new Empleado(nombreEmpleado[x], edadEmpleado[x]);
			}
			//mostramos c/empleado instanciado y el siguiente ID
			System.out.println(empleados[x].toString());
			System.out.println("El próximo ID será el " + Empleado.devuelveProximoID());
		}
	}
}
