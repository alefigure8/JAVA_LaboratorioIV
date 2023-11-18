package excepciones;

public class ErrorInternoException extends Exception{
	public ErrorInternoException() {
		super("Se ha producido un error interno. Intente nuevamente.");
	}
}
