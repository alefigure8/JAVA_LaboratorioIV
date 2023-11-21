package entidad;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario{
	
	private String nombre;
	private String apellido;
	private int dni;
	private long cuil;
	private String nacionalidad;
	private String sexo;
	private LocalDate nacimiento;	
	private Direccion direccion;
	private String email;
	private long telefono;
	
	
	// CONSTRUCTORES
	
	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Cliente(String usuario, int iD, String nombre, String apellido, String contrasenia, LocalDate fechaAlta,
			Boolean activo, TipoAcceso tipoAcceso, int dni, long cuil, String nacionalidad, String sexo, LocalDate nacimiento, Direccion direccion, String email,
			long telefono) {
		super(usuario, iD, contrasenia, fechaAlta, activo, tipoAcceso);
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.cuil = cuil;
		this.nacionalidad = nacionalidad;
		this.sexo = sexo;
		this.nacimiento = nacimiento;
		this.direccion = direccion;
		this.email = email;
		this.telefono = telefono;
		
	}
	
	// GETTERS & SETTERS 
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
	

	public int getDni() {
		return this.dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	public long getCuil() {
		return this.cuil;
	}
	public void setCuil(long cuil) {
		this.cuil = cuil;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public String getSexo() {
		return this.sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public LocalDate getNacimiento() {
		return this.nacimiento;
	}
	public void setNacimiento(LocalDate nacimiento) {
		this.nacimiento = nacimiento;
	}
	public Direccion getDireccion() {
		return this.direccion;
	}
	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getTelefono() {
		return this.telefono;
	}
	public void setTelefono(long telefono) {
		this.telefono = telefono;
	}
	
	
	// TOSTRING

	@Override
	public String toString() {
		return super.toString() + "Cliente [nombre=" + nombre + ", apellido=" + apellido + dni + ", cuil=" + cuil + ", sexo=" + sexo + ", nacimiento=" + nacimiento
				+ ", direccion=" + direccion + ", email=" + email + ", telefono=" + telefono + "]";
	}



	
}
