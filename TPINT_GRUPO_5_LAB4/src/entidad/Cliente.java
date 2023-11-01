package entidad;

import java.time.LocalDate;
import java.util.List;

public class Cliente extends Usuario{
	private int dni;
	private int cuil;
	private String nacionalidad;
	private String sexo;
	private LocalDate nacimiento;		
	private Direccion direccion;
	private String email;
	private int telefono;
	private boolean estado;
	List<Cuenta> listaCuentas;
	List<Prestamo> listaPrestamos;
	
	// CONSTRUCTORES
	
	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Cliente(String usuario, int iD, String nombre, String apellido, String contrasenia, LocalDate fechaAlta,
			Boolean activo, TipoAcceso tipoAcceso, int idCliente, int dni, int cuil, String nacionalidad, String sexo, LocalDate nacimiento, Direccion direccion, String email,
			int telefono, boolean estado, List<Cuenta> listaCuentas, List<Prestamo> listaPrestamos) {
		super(usuario, iD, nombre, apellido, contrasenia, fechaAlta, activo, tipoAcceso);
		this.dni = dni;
		this.cuil = cuil;
		this.nacionalidad = nacionalidad;
		this.sexo = sexo;
		this.nacimiento = nacimiento;
		this.direccion = direccion;
		this.email = email;
		this.telefono = telefono;
		this.estado = estado;
		this.listaCuentas = listaCuentas;
		this.listaPrestamos = listaPrestamos;
	}
	
	// GETTERS & SETTERS 

	public int getDni() {
		return this.dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	public int getCuil() {
		return this.cuil;
	}
	public void setCuil(int cuil) {
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
	public int getTelefono() {
		return this.telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public boolean isEstado() {
		return this.estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public List<Cuenta> getListaCuentas() {
		return this.listaCuentas;
	}
	public void setListaCuentas(List<Cuenta> listaCuentas) {
		this.listaCuentas = listaCuentas;
	}
	public List<Prestamo> getListaPrestamos() {
		return this.listaPrestamos;
	}
	public void setListaPrestamos(List<Prestamo> listaPrestamos) {
		this.listaPrestamos = listaPrestamos;
	}
	
	// TOSTRING

	@Override
	public String toString() {
		return super.toString() + "Cliente [dni=" + dni + ", cuil=" + cuil + ", sexo=" + sexo + ", nacimiento=" + nacimiento
				+ ", direccion=" + direccion + ", email=" + email + ", telefono=" + telefono + ", estado=" + estado
				+ ", listaCuentas=" + listaCuentas + ", listaPrestamos=" + listaPrestamos + "]";
	}



	
}
