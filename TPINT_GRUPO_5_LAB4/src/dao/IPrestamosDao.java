package dao;

import java.util.List;

import entidad.CuotaPrestamo;
import entidad.Estado;
import entidad.Prestamo;
import entidad.TipoTasa;

public interface IPrestamosDao {

	public List<Prestamo> obtenerTodos();
	public List<Prestamo> obtenerTodosxcliente(int idcliente);
	public List<Prestamo> obtenerTodosxcuenta(int nrocuenta);
	public List<CuotaPrestamo> obtenerCuotasxprestamo(int idprestamo);
	public List<TipoTasa> obtenerTodosTiposTasas();
	public TipoTasa obtenertipotasa(int idtipotasa, Conexion conexion); // HACER UN JOIN EN OBTENERTODOS
	public Estado obtenerestado(int idestado, Conexion conexion);
	public CuotaPrestamo obtenerUnaCuota(int idCuota, int idprestamo);
	public Prestamo obteneruno(int idprestamo);
	public boolean insertarcuotas(Prestamo prestamo);
	public boolean insertarprestamo (Prestamo prestamo);
	public boolean setcancelado (int idprestamo);
	public boolean setcuotapagada (int idprestamo, int idcuota);
	public boolean rechazar(int idPrestamo);
	public boolean aceptar(int idPrestamo);
		
}
