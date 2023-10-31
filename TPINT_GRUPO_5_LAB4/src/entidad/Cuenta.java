package entidad;

import java.time.LocalDate;

public class Cuenta {

	private int idCliente;
	private int numeroCuenta;
	private String cbu;
	private double saldo; 
	private TipoCuenta tipoCuenta;
    private LocalDate fechaCreacion;   
    private Estado estado;
    private boolean activo;   
   
	
}
