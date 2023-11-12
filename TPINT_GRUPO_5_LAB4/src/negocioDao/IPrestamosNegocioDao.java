package negocioDao;

import java.util.List;

import entidad.CuotaPrestamo;
import entidad.Prestamo;

public interface IPrestamosNegocioDao {
	public List<Prestamo> obtenerTodosxcliente(int idcliente);
	public Prestamo obteneruno(int idprestamo);
	public List<CuotaPrestamo> obtenerCuotasxprestamo(int idprestamo);
	public List<Prestamo> obtenerTodos();
	public boolean rechazar(int idPrestamo);
	public boolean aceptar(int idPrestamo);
	public boolean insertarcuotas(Prestamo prestamo);

}
