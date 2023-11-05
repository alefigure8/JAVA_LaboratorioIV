package entidad;

import java.time.LocalDate;
import java.util.List;

public class Cuenta {
	
	private int numeroCuenta;
	private String cbu;
	private double saldo; 
	private TipoCuenta tipoCuenta;
	private int idCliente;
    private LocalDate fechaCreacion;   
    /*private Estado estado;*/
    private boolean activo;
//    private List<Movimiento> movimientosCuenta;
    
    // CONSTRUCTORES
    
	public Cuenta() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cuenta(int numeroCuenta, String cbu, double saldo, TipoCuenta tipoCuenta, int idCliente,
			LocalDate fechaCreacion, /*Estado estado,*/ boolean activo/*, List<Movimiento> movimientosCuenta*/) {
		super();
		this.numeroCuenta = numeroCuenta;
		this.cbu = cbu;
		this.saldo = saldo;
		this.idCliente = idCliente;
		this.tipoCuenta = tipoCuenta;
		this.fechaCreacion = fechaCreacion;
		/*this.estado = estado;*/
		this.activo = activo;
//		this.movimientosCuenta = movimientosCuenta;
	}

	// GETTERS & SETTERS
	
	public int getIdCliente() {
		return this.idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getNumeroCuenta() {
		return this.numeroCuenta;
	}

	public void setNumeroCuenta(int numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getCbu() {
		return this.cbu;
	}

	public void setCbu(String cbu) {
		this.cbu = cbu;
	}

	public double getSaldo() {
		return this.saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public TipoCuenta getTipoCuenta() {
		return this.tipoCuenta;
	}

	public void setTipoCuenta(TipoCuenta tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public LocalDate getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	/*public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}*/

	public boolean isActivo() {
		return this.activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	/*
	public List<Movimiento> getMovimientosCuenta() {
		return this.movimientosCuenta;
	}

	public void setMovimientosCuenta(List<Movimiento> movimientosCuenta) {
		this.movimientosCuenta = movimientosCuenta;
	}*/

	// TOSTRING
	
	@Override
	public String toString() {
		return "Cuenta [idCliente=" + idCliente + ", numeroCuenta=" + numeroCuenta + ", cbu=" + cbu + ", saldo=" + saldo
				+ ", tipoCuenta=" + tipoCuenta + ", fechaCreacion=" + fechaCreacion + /*", estado=" + estado + */",activo="
				+ activo /*+ ", movimientosCuenta=" + movimientosCuenta + "]"*/;
	} 
	

}
