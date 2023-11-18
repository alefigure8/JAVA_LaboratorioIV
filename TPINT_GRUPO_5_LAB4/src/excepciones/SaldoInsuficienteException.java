package excepciones;

public class SaldoInsuficienteException extends Exception{
	private static final long serialVersionUID = 1L;

	public SaldoInsuficienteException() {
		super("No tiene suficiente saldo para realizar la operación");
	}
}
