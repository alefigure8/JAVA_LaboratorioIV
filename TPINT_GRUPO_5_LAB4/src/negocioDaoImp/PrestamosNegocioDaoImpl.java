package negocioDaoImp;

import java.util.ArrayList;
import java.util.List;

import daoImp.PrestamosImpl;
import entidad.CuotaPrestamo;
import entidad.Prestamo;
import negocioDao.IPrestamosNegocioDao;

public class PrestamosNegocioDaoImpl implements IPrestamosNegocioDao{
	
	private PrestamosImpl prestamosImpl = null;
	
	public PrestamosNegocioDaoImpl() {
		prestamosImpl = new PrestamosImpl();
	}

	@Override
	public List<Prestamo> obtenerTodosxcliente(int idcliente) {
		
		List<Prestamo> listaPrestamos = new ArrayList<Prestamo>();
		
		try {
			listaPrestamos = prestamosImpl.obtenerTodosxcliente(idcliente);
			return listaPrestamos;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return listaPrestamos;
	}

	@Override
	public Prestamo obteneruno(int idprestamo) {
		Prestamo prestamo = new Prestamo();
		try {
			prestamo = prestamosImpl.obteneruno(idprestamo);
			return prestamo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return prestamo;
	}

	@Override
	public List<CuotaPrestamo> obtenerCuotasxprestamo(int idprestamo) {
		List<CuotaPrestamo> listaCuotas = new ArrayList<CuotaPrestamo>();
		
		try {
			listaCuotas = prestamosImpl.obtenerCuotasxprestamo(idprestamo);
			return listaCuotas;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return listaCuotas;
	}

	@Override
	public List<Prestamo> obtenerTodos() {
		
		return prestamosImpl.obtenerTodos();
	}

	@Override
	public boolean rechazar(int idPrestamo) {
		return prestamosImpl.rechazar(idPrestamo);
	}

	@Override
	public boolean aceptar(int idPrestamo) {
		return prestamosImpl.aceptar(idPrestamo);
	}

	@Override
	public boolean insertarcuotas(Prestamo prestamo) {
		return prestamosImpl.insertarcuotas(prestamo);
	}

}
