package ejercicio1;

public class Dni {

	//VERIFICAR DNI NUM�RICO
	public static boolean verificarDniInvalido (String dni) throws DniInvalido {
		boolean soloNumeros=(dni!=null && dni.matches("^[0-9]+$"));
		//Manera abreviada: dni.matches("\\d+")
		//Ver si agregar validaci�n de 6 a 8 d�gitos:  dni.matches("^\\d{6,8}$") 
		if(!soloNumeros) {
			throw new DniInvalido();
		}
		else {
			return true;
		}
	}
	
}
