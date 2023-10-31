package entidad;

import java.time.LocalDate;

public class Usuario {
	  			    	    	
		private String usuario;
		private int ID;
	 	private int DNI;
		private String Nombre;
		private String Apellido;
		private String contrasena;
		private LocalDate fechaAlta;
		private Boolean activo;
		private int tipoAcceso;
		//TipoAcceso ENUM('1', '2') NOT NULL,
}
