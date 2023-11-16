package entidad;

import java.time.LocalDate;

public class CuotaPrestamo {

	
	private int id;
	private int idPrestamo;
	private int numeroCuota;
    private double montoCuota;
    private EstadoCuota estado;
    private LocalDate fechaVencimiento;
    private LocalDate fechaPago;
    
    //Agregamos nro.Cuenta
 
    
    // CONSTRUCTORES
    
	public EstadoCuota getEstado() {
		return estado;
	}

	public void setEstado(EstadoCuota estado) {
		this.estado = estado;
	}

	public CuotaPrestamo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CuotaPrestamo(int id, int idPrestamo, int numeroCuota, double montoCuota, LocalDate fechaVencimiento,
			LocalDate fechaPago, Boolean estado) {
		super();
		this.id = id;
		this.idPrestamo = idPrestamo;
		this.numeroCuota = numeroCuota;
		this.montoCuota = montoCuota;
		this.fechaVencimiento = fechaVencimiento;
		this.fechaPago = fechaPago;

	}

	// GETTERS & SETTERS
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdPrestamo() {
		return this.idPrestamo;
	}

	public void setIdPrestamo(int idPrestamo) {
		this.idPrestamo = idPrestamo;
	}

	public int getNumeroCuota() {
		return this.numeroCuota;
	}

	public void setNumeroCuota(int numeroCuota) {
		this.numeroCuota = numeroCuota;
	}

	public double getMontoCuota() {
		return this.montoCuota;
	}

	public void setMontoCuota(double montoCuota) {
		this.montoCuota = montoCuota;
	}

	public LocalDate getFechaVencimiento() {
		return this.fechaVencimiento;
	}

	public void setFechaVencimiento(LocalDate fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public LocalDate getFechaPago() {
		return this.fechaPago;
	}

	public void setFechaPago(LocalDate fechaPago) {
		this.fechaPago = fechaPago;
	}


	// TO STRING
	
	@Override
	public String toString() {
		return "CuotaPrestamo [id=" + id + ", idPrestamo=" + idPrestamo + ", numeroCuota=" + numeroCuota
				+ ", montoCuota=" + montoCuota + ", fechaVencimiento=" + fechaVencimiento + ", fechaPago=" + fechaPago
				+ ", estado=" + estado + "]";
	}
	
	
	
	
	
    
    
}
