package negocioDaoImp;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

import dao.IMovimientosDao;
import daoImp.MovimientosDaoImpl;
import entidad.Cliente;
import entidad.Cuenta;
import entidad.Estado;
import entidad.Movimiento;
import entidad.Operacion;
import entidad.TipoMovimiento;
import javafx.util.converter.LocalDateStringConverter;
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
	public List<Movimiento> obtenerPorCliente(int cliente) throws SQLException {
		return (List<Movimiento>)movimientoNegocio.obtenerPorCliente(cliente);
	}
	
	@Override
	public List<Movimiento> obtenerTransferenciasPorCliente(int cliente) throws SQLException {
		return (List<Movimiento>)movimientoNegocio.obtenerPorCliente(cliente);
	}
	
	@Override
	public HashMap<Integer, String> obtenerDestinatariosTransferenciasPorNumeroCliente(int numeroCliente) throws SQLException {
		return movimientoNegocio.obtenerDestinatariosTransferenciasPorNumeroCliente(numeroCliente);
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
	
	//GENERAR NUMERO DE REFERENCIA ALEATORIO
	public static int generarNumeroReferencia() {
		
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddhhmmss");
        String fechaFormateada = fechaHoraActual.format(formatter);
        return Integer.parseInt(fechaFormateada);
	}
	
	//OBTENER ULTIMO ID DE MOVIMIENTO PARA AGREGAR AL NUMERO DE REFERENCIA
	@Override
	public int obtenerUltimoIdMovimiento() throws SQLException {
		return movimientoNegocio.obtenerUltimoIdMovimiento();
	}
	
	//TRANSFERENCIA
	public boolean insertarTransferencia(String cbuCliente, String cbuDestinatario, Double monto, String concepto) throws SQLException {
		boolean transferenciaRealizada = false;
		int numeroReferencia = generarNumeroReferencia();
		
		//Movimiento Salida
		Movimiento movimiento = new Movimiento();
		movimiento.setMonto(monto);
		movimiento.setConcepto(concepto);
		movimiento.setEstado(new Estado(1, "Aprobado"));
		movimiento.setFechaMovimiento(LocalDate.now());
		movimiento.setOperacion(Operacion.Salida);
		movimiento.setTipoMovimiento(new TipoMovimiento(1, "Transferencia"));
		movimiento.setNumeroReferencia(numeroReferencia);
		Cuenta cuentaCliente = new Cuenta();
		cuentaCliente.setCbu(cbuCliente);
		movimiento.setCuenta(cuentaCliente);
		
		boolean esAprobada = this.insertar(movimiento);
		
		//Movimiento Entrada
		if(esAprobada) {
			movimiento.setOperacion(Operacion.Entrada);
			Cuenta cuentaDestinatario = new Cuenta();
			cuentaDestinatario.setCbu(cbuDestinatario);
			movimiento.setCuenta(cuentaDestinatario);
			transferenciaRealizada = this.insertar(movimiento);
		}
		
		return transferenciaRealizada;
	}
	
	
	
	
}
