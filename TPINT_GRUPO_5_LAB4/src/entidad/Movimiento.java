package entidad;

import java.time.LocalDate;

public class Movimiento {

    private int id;
    private TipoMovimiento tipoMovimiento;
    private int numeroReferencia;
    private String cbudestino;
    private double monto;
    private String operacion;
    //Operacion ENUM('Entrada', 'Salida') NOT NULL,
    private LocalDate fechaMovimiento; 
    private Estado estado;
    private String concepto;  
	
	
}
