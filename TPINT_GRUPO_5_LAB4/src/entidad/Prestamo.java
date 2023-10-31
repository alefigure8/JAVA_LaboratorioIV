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
		private int plazoPago;
		private ArrayList<CuotaPrestamo> cuotasPrestamo;
		private double montoxMes;
	    private Estado estado;
	    private boolean activo;
	    private LocalDate fechaPrestamo;    
	    
	
}
