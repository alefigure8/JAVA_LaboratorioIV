package entidad;

import java.time.LocalDate;

public class Usuario {
	  			    	    	
		private String usuario;
		private int id;
		
		private String contrasenia;
		private LocalDate fechaAlta;
		private Boolean activo;
		private TipoAcceso tipoAcceso;
		
		// CONSTRUCTORES
		public Usuario() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Usuario(String usuario, int iD,  String contrasenia, LocalDate fechaAlta,
				Boolean activo, TipoAcceso tipoAcceso) {
			super();
			this.usuario = usuario;
			this.id = iD;
			this.contrasenia = contrasenia;
			this.fechaAlta = fechaAlta;
			this.activo = activo;
			this.tipoAcceso = tipoAcceso;
		}

		// GETTERS & SETTERS
		
		public String getUsuario() {
			return this.usuario;
		}

		public void setUsuario(String usuario) {
			this.usuario = usuario;
		}

		public int getId() {
			return this.id;
		}

		public void setId(int id) {
			this.id = id;
		}

		
		public String getContrasenia() {
			return this.contrasenia;
		}

		public void setContrasenia(String contrasenia) {
			this.contrasenia = contrasenia;
		}

		public LocalDate getFechaAlta() {
			return this.fechaAlta;
		}

		public void setFechaAlta(LocalDate fechaAlta) {
			this.fechaAlta = fechaAlta;
		}

		public Boolean getActivo() {
			return this.activo;
		}

		public void setActivo(Boolean activo) {
			this.activo = activo;
		}

		public TipoAcceso getTipoAcceso() {
			return this.tipoAcceso;
		}

		public void setTipoAcceso(TipoAcceso tipoAcceso) {
			this.tipoAcceso = tipoAcceso;
		}
		
		
		// TOSTRING
		@Override
		public String toString() {
			return "Usuario [usuario=" + usuario + ", id=" + id + ", contrasenia=" + contrasenia + ", fechaAlta=" + fechaAlta + ", activo=" + activo
					+ ", tipoAcceso=" + tipoAcceso + "]";
		} 

		
}
