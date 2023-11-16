package excepciones;

public class RangoException extends Exception {

	
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		
		return "El Rango Minimo no puede ser mayor o igual al maximo";
	}
}
