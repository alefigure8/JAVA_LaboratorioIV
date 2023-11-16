package entidad;

import java.awt.List;
import java.time.LocalDate;
import java.util.ArrayList;

public class Prestamo {

		private int id;
		private int idCliente;
		private double montoPedido;
		private double montoConIntereses;
		private TipoTasa tipoTasa;
		private ArrayList<CuotaPrestamo> cuotasPrestamo;
		private double montoxMes;
	    private Estado estado;
	    private int NumeroCuenta;   
	  	private LocalDate fechaPrestamo;
	    private boolean cancelado;
	    
	    // CONSTRUCTORES
	    
		public Prestamo() {
			super();
			// TODO Auto-generated constructor stub
		}
		

		
		public Prestamo(int id, int idCliente, double montoPedido, double montoConIntereses, TipoTasa tipoTasa,
				ArrayList<CuotaPrestamo> cuotasPrestamo, double montoxMes, Estado estado, int numeroCuenta,
				LocalDate fechaPrestamo, boolean cancelado) {
			super();
			this.id = id;
			this.idCliente = idCliente;
			this.montoPedido = montoPedido;
			this.montoConIntereses = montoConIntereses;
			this.tipoTasa = tipoTasa;
			this.cuotasPrestamo = cuotasPrestamo;
			this.montoxMes = montoxMes;
			this.estado = estado;
			this.NumeroCuenta = numeroCuenta;
			this.fechaPrestamo = fechaPrestamo;
			this.cancelado = cancelado;
		}
		
		
		
		
		// GETTERS & SETTERS

		public int getId() {
			return this.id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getIdCliente() {
			return this.idCliente;
		}

		public void setIdCliente(int idCliente) {
			this.idCliente = idCliente;
		}

		public double getMontoPedido() {
			return this.montoPedido;
		}

		public void setMontoPedido(double montoPedido) {
			this.montoPedido = montoPedido;
		}

		public double getMontoConIntereses() {
			return this.montoConIntereses;
		}

		public void setMontoConIntereses(double montoConIntereses) {
			this.montoConIntereses = montoConIntereses;
		}

		public TipoTasa getTipoTasa() {
			return this.tipoTasa;
		}

		public void setTipoTasa(TipoTasa tipoTasa) {
			this.tipoTasa = tipoTasa;
		}

	
		public ArrayList<CuotaPrestamo> getCuotasPrestamo() {
			return this.cuotasPrestamo;
		}

		public void setCuotasPrestamo(ArrayList<CuotaPrestamo> cuotasPrestamo) {
			this.cuotasPrestamo = cuotasPrestamo;
		}

		public double getMontoxMes() {
			return this.montoxMes;
		}

		public void setMontoxMes(double montoxMes) {
			this.montoxMes = montoxMes;
		}

		public Estado getEstado() {
			return this.estado;
		}

		public void setEstado(Estado estado) {
			this.estado = estado;
		}

		public LocalDate getFechaPrestamo() {
			return this.fechaPrestamo;
		}

		public void setFechaPrestamo(LocalDate fechaPrestamo) {
			this.fechaPrestamo = fechaPrestamo;
		}

		
		public boolean isCancelado() {
			return cancelado;
		}

		  public int getNumeroCuenta() {
				return NumeroCuenta;
			}

			public void setNumeroCuenta(int numeroCuenta) {
				this.NumeroCuenta = numeroCuenta;
			}

		
		public void setCancelado(boolean cancelado) {
			this.cancelado = cancelado;
		}



		@Override
		public String toString() {
			return "Prestamo [id=" + id + ", idCliente=" + idCliente + ", montoPedido=" + montoPedido
					+ ", montoConIntereses=" + montoConIntereses + ", tipoTasa=" + tipoTasa + ", cuotasPrestamo="
					+ cuotasPrestamo + ", montoxMes=" + montoxMes + ", estado=" + estado + ", NumeroCuenta="
					+ NumeroCuenta + ", fechaPrestamo=" + fechaPrestamo + ", cancelado=" + cancelado + "]";
		}

	
		
		// TO STRING
	
		
	    
	    
	
}
