package entidad;

import java.time.LocalDate;

public class Usuario {
	  			    	    	
		private String usuario;
		private int id;
		private String nombre;
		private String apellido;
		private String contrasenia;
		private LocalDate fechaAlta;
		private Boolean activo;
		private TipoAcceso tipoAcceso;
		
		// CONSTRUCTORES
		public Usuario() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Usuario(String usuario, int iD, String nombre, String apellido, String contrasenia, LocalDate fechaAlta,
				Boolean activo, TipoAcceso tipoAcceso) {
			super();
			this.usuario = usuario;
			this.id = iD;
			this.nombre = nombre;
			this.apellido = apellido;
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

		public String getNombre() {
			return this.nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public String getApellido() {
			return this.apellido;
		}

		public void setApellido(String apellido) {
			this.apellido = apellido;
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
			return "Usuario [usuario=" + usuario + ", id=" + id + ", nombre=" + nombre + ", apellido=" + apellido
					+ ", contrasenia=" + contrasenia + ", fechaAlta=" + fechaAlta + ", activo=" + activo
					+ ", tipoAcceso=" + tipoAcceso + "]";
		} 

		
}
