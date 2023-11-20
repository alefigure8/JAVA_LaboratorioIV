package negocioDaoImp;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import daoImp.PrestamosImpl;
import entidad.CuotaPrestamo;
import entidad.Prestamo;
import entidad.TipoTasa;
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

	@Override
	public List<TipoTasa> obtenerTodosTiposTasas() {
		return prestamosImpl.obtenerTodosTiposTasas();
	}

	@Override
	public boolean insertarprestamo(Prestamo prestamo) {
		return prestamosImpl.insertarprestamo(prestamo);
	}

	@Override
	public CuotaPrestamo obtenerUnaCuota(int idCuota, int idprestamo) {
		return prestamosImpl.obtenerUnaCuota(idCuota, idprestamo);
	}

	@Override
	public boolean setcuotapagada(int idprestamo, int idcuota) {
		return prestamosImpl.setcuotapagada(idprestamo, idcuota);
	}

	@Override
	public CuotaPrestamo obtenerUltimaCuota(Prestamo prestamo) {
		return prestamosImpl.obtenerUltimaCuota(prestamo);
	}

	@Override
	public List<CuotaPrestamo> obtenertodas() {
		return prestamosImpl.obtenertodas();
	}
	
	@Override
	public boolean prestamoSaldado(int idprestamo) {
		Boolean cancelado=false;
		Prestamo prestamo=prestamosImpl.obteneruno(idprestamo);
		List<CuotaPrestamo> cuotas=prestamosImpl.obtenerCuotasxprestamo(idprestamo);
		int cuotasPagas=0;
		for(int x=0;x<cuotas.size();x++) {
			if(cuotas.get(x).getFechaPago()!=null) {
				cuotasPagas++;				
			}
		}
		if(prestamo.getTipoTasa().getCantCuotas()==cuotasPagas) {
			if(prestamosImpl.setcancelado(idprestamo)) {
				cancelado=true;
			}
		}
		return cancelado;
	}

	@Override
	public boolean setcancelado(int idprestamo) {
		return prestamosImpl.setcancelado(idprestamo);
	}

	@Override
	public CuotaPrestamo obtenerUnaCuotaxidcuota(int idCuota) {
		
		return prestamosImpl.obtenerUnaCuotaxIdCuota(idCuota);
	}

	/*@Override
	public TipoTasa obtenerTipoTasa(int idPrestamo) {
		TipoTasa tipoTasaAenviar=new TipoTasa();
		Prestamo prestamo=prestamosImpl.obteneruno(idPrestamo);
		List<TipoTasa>tiposTasa=(List<TipoTasa>)prestamosImpl.obtenerTodosTiposTasas();
		for(TipoTasa tipoTasa:tiposTasa) {
			if(prestamo.getTipoTasa().getId()==tipoTasa.getId()){
				tipoTasaAenviar=tipoTasa;
			}
		}
		return tipoTasaAenviar;
	}*/

	
	@Override
	public int cantidadPrestamosAnioCancelados(String anio)throws SQLException{
		return prestamosImpl.cantidadPrestamosAnioCancelados(anio);
	}
	@Override
	public int cantidadPrestamosAnioYMesCancelados(String anio, String mes)throws SQLException{
		return prestamosImpl.cantidadPrestamosAnioYMesCancelados(anio, mes);
	}
	@Override
	public int cantidadPrestamosAnio(String anio)throws SQLException{
		return prestamosImpl.cantidadPrestamosAnio(anio);
	}
	@Override
	public int cantidadPrestamosAnioYMes(String anio, String mes)throws SQLException{
		return prestamosImpl.cantidadPrestamosAnioYMes(anio, mes);
	}

	@Override
	public boolean setcuotapagadaxidcuota(int idcuota, LocalDate fechapago) {
		return prestamosImpl.setcuotapagadaxidcuota(idcuota, fechapago);
	}

	@Override
	public int obtenerUltimoIdPrestamo() throws SQLException {
	
		return prestamosImpl.obtenerUltimoIdPrestamo();
	}
	
}
