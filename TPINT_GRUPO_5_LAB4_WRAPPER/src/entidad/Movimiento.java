package entidad;

import java.time.LocalDate;

public class Movimiento {

    private int id;
    private TipoMovimiento tipoMovimiento;
    private int numeroReferencia;
    private String cbudestino;
    private double monto;
    private Operacion operacion;
    private LocalDate fechaMovimiento; 
    private Estado estado;
    private String concepto;
    
    // CONSTRUCTORES
    
	public Movimiento() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Movimiento(int id, TipoMovimiento tipoMovimiento, int numeroReferencia, String cbudestino, double monto,
			Operacion operacion, LocalDate fechaMovimiento, Estado estado, String concepto) {
		super();
		this.id = id;
		this.tipoMovimiento = tipoMovimiento;
		this.numeroReferencia = numeroReferencia;
		this.cbudestino = cbudestino;
		this.monto = monto;
		this.operacion = operacion;
		this.fechaMovimiento = fechaMovimiento;
		this.estado = estado;
		this.concepto = concepto;
	}
	
	// GETTERS & SETTERS

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TipoMovimiento getTipoMovimiento() {
		return this.tipoMovimiento;
	}

	public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public int getNumeroReferencia() {
		return this.numeroReferencia;
	}

	public void setNumeroReferencia(int numeroReferencia) {
		this.numeroReferencia = numeroReferencia;
	}

	public String getCbudestino() {
		return this.cbudestino;
	}

	public void setCbudestino(String cbudestino) {
		this.cbudestino = cbudestino;
	}

	public double getMonto() {
		return this.monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public String getOperacion() {
		return this.operacion.name();
	}

	public void setOperacion(Operacion operacion) {
		this.operacion = operacion;
	}

	public LocalDate getFechaMovimiento() {
		return this.fechaMovimiento;
	}

	public void setFechaMovimiento(LocalDate fechaMovimiento) {
		this.fechaMovimiento = fechaMovimiento;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String getConcepto() {
		return this.concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	
	// TO STRING

	@Override
	public String toString() {
		return "Movimiento [id=" + id + ", tipoMovimiento=" + tipoMovimiento + ", numeroReferencia=" + numeroReferencia
				+ ", cbudestino=" + cbudestino + ", monto=" + monto + ", operacion=" + operacion + ", fechaMovimiento="
				+ fechaMovimiento + ", estado=" + estado + ", concepto=" + concepto + "]";
	}  

	

	
	
}
