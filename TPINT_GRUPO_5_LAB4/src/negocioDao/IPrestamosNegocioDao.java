package negocioDao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import entidad.CuotaPrestamo;
import entidad.Prestamo;
import entidad.TipoTasa;

public interface IPrestamosNegocioDao {
	public List<Prestamo> obtenerTodosxcliente(int idcliente);
	public Prestamo obteneruno(int idprestamo);
	public List<CuotaPrestamo> obtenerCuotasxprestamo(int idprestamo);
	public List<Prestamo> obtenerTodos();
	public boolean rechazar(int idPrestamo);
	public boolean aceptar(int idPrestamo);
	public boolean insertarcuotas(Prestamo prestamo);
	public List<TipoTasa> obtenerTodosTiposTasas();

	public boolean insertarprestamo (Prestamo prestamo);
	public CuotaPrestamo obtenerUnaCuota(int idCuota, int idprestamo);
	public CuotaPrestamo obtenerUnaCuotaxidcuota(int idCuota);
	//public TipoTasa obtenerTipoTasa(int idPrestamo);
	public boolean setcuotapagada (int idprestamo, int idcuota);
	public CuotaPrestamo obtenerUltimaCuota(Prestamo prestamo);
	public int obtenerUltimoIdPrestamo() throws SQLException;
	public boolean setcuotapagadaxidcuota (int idcuota, LocalDate fechapago);
	public List<CuotaPrestamo> obtenertodas();
	public boolean prestamoSaldado(int idprestamo);
	public boolean setcancelado (int idprestamo);
	public int cantidadPrestamosAnioCancelados(String anio)throws SQLException;
	public int cantidadPrestamosAnioYMesCancelados(String anio, String mes)throws SQLException;
	public int cantidadPrestamosAnio(String anio)throws SQLException;
	public int cantidadPrestamosAnioYMes(String anio, String mes)throws SQLException;
}
