package excepciones;

public class UsuarioIncorrectoException extends Exception{
	public UsuarioIncorrectoException() {
		super("El usuario no existe.!Pruebe nuevamente!");
	}
}
