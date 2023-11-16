package excepciones;

public class UsuarioNoEncontradoException extends Exception{
	private static final long serialVersionUID = 1L;

	public UsuarioNoEncontradoException() {
		super("Su usuario no tiene suficientes privilegios para acceder");
	}
}
