package negocioDaoImp;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import dao.IMovimientosDao;
import daoImp.MovimientosDaoImpl;
import entidad.Movimiento;
import entidad.TipoMovimiento;
import negocioDao.IMovimientoNegocioDao;

public class MovimientoNegocioDaoImp implements IMovimientoNegocioDao{
	
	private IMovimientosDao movimientoNegocio= new MovimientosDaoImpl();
	
	public MovimientoNegocioDaoImp() {
		
	}
	
	public MovimientoNegocioDaoImp(IMovimientosDao movimientoNegocio) {
		this.movimientoNegocio=movimientoNegocio;
	}
	
	
	@Override
	public List<Movimiento> obtenerTodos() throws SQLException {
		return (List<Movimiento>)movimientoNegocio.obtenerTodos();
	}

	@Override
	public List<Movimiento> obtenerPorNumeroDeReferencia(int numeroReferencia) throws SQLException {
		return (List<Movimiento>)movimientoNegocio.obtenerPorNumeroDeReferencia(numeroReferencia);
	}

	@Override
	public List<Movimiento> obtenerPorCBU(String CBU) throws SQLException {
		return (List<Movimiento>)movimientoNegocio.obtenerPorCBU(CBU);
	}

	@Override
	public boolean insertar(Movimiento movimiento) throws SQLException {
		return movimientoNegocio.insertar(movimiento);
	}

	@Override
	public boolean editar(Movimiento movimiento) throws SQLException {
		return movimientoNegocio.editar(movimiento);
	}

	@Override
	public boolean borrar(int id) throws SQLException {
		return movimientoNegocio.borrar(id);
	}

	@Override
	public TipoMovimiento obtenerTipoMovimientoPorId(int id) throws SQLException {
		return (TipoMovimiento)movimientoNegocio.obtenerTipoMovimientoPorId(id);
	}

	@Override
	public List<TipoMovimiento> obtenerTipoMovimientos() throws SQLException {
		return (List<TipoMovimiento>)movimientoNegocio.obtenerTipoMovimientos();
	}
	
	//GENERAR NUMERO DE REFERENCIA ALEATORIO
	public static int generarNumeroReferencia(int idMovimiento) {
		
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String fechaFormateada = fechaHoraActual.format(formatter);
        String numeroReferencia = fechaFormateada+ idMovimiento;
        return Integer.parseInt(numeroReferencia);
	}
	
	//OBTENER ULTIMO ID DE MOVIMIENTO PARA AGREGAR AL NUMERO DE REFERENCIA
	@Override
	public int obtenerUltimoIdMovimiento() throws SQLException {
		return movimientoNegocio.obtenerUltimoIdMovimiento();
	}
	
	
	
	
	
}
