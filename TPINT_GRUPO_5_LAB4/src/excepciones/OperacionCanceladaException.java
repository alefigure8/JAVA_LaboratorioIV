package excepciones;

public class OperacionCanceladaException extends Exception{
	private static final long serialVersionUID = 1L;

	public OperacionCanceladaException() {
		super("Operacion cancelada. Intente nuevamente más tarde");
	}
}
