package dao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import entidad.CuotaPrestamo;
import entidad.Estado;
import entidad.Prestamo;
import entidad.TipoTasa;

public interface IPrestamosDao {
	public boolean setcuotapagadaxidcuota (int idcuota, LocalDate fechapago);
	public int obtenerUltimoIdPrestamo() throws SQLException;
	public List<Prestamo> obtenerTodos();
	public List<Prestamo> obtenerTodosxcliente(int idcliente);
	public List<Prestamo> obtenerTodosxcuenta(int nrocuenta);
	public List<CuotaPrestamo> obtenerCuotasxprestamo(int idprestamo);
	public List<TipoTasa> obtenerTodosTiposTasas();
	public TipoTasa obtenertipotasa(int idtipotasa, Conexion conexion); // HACER UN JOIN EN OBTENERTODOS
	public Estado obtenerestado(int idestado, Conexion conexion);
	public CuotaPrestamo obtenerUnaCuota(int idCuota, int idprestamo);
	public CuotaPrestamo obtenerUnaCuotaxIdCuota(int idCuota);
	public Prestamo obteneruno(int idprestamo);
	public boolean insertarcuotas(Prestamo prestamo);
	public boolean insertarprestamo (Prestamo prestamo);
	public boolean setcancelado (int idprestamo);
	public boolean setcuotapagada (int idprestamo, int idcuota);
	public boolean rechazar(int idPrestamo);
	public boolean aceptar(int idPrestamo);
	public CuotaPrestamo obtenerUltimaCuota(Prestamo prestamo);
	public List<CuotaPrestamo> obtenertodas();
	public int cantidadPrestamosAnioCancelados(String anio)throws SQLException;
	public int cantidadPrestamosAnioYMesCancelados(String anio, String mes)throws SQLException;
	public int cantidadPrestamosAnio(String anio)throws SQLException;
	public int cantidadPrestamosAnioYMes(String anio, String mes)throws SQLException;
}
