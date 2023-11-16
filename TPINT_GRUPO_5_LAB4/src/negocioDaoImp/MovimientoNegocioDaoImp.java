package negocioDaoImp;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.HashMap;
import dao.IMovimientosDao;
import daoImp.MovimientosDaoImpl;
import entidad.Movimiento;
import entidad.TipoMovimiento;
import negocioDao.IMovimientoNegocioDao;
import entidad.Cliente;
import entidad.Cuenta;
import entidad.Destinatario;
import entidad.Estado;
import entidad.Operacion;
import javafx.util.converter.LocalDateStringConverter;

public class MovimientoNegocioDaoImp implements IMovimientoNegocioDao{
	
	private IMovimientosDao movimientosDao= new MovimientosDaoImpl();
	
	public MovimientoNegocioDaoImp() {
		
	}
	
	public MovimientoNegocioDaoImp(IMovimientosDao movimientoNegocio) {
		this.movimientosDao=movimientoNegocio;
	}
	
	
	@Override
	public List<Movimiento> obtenerTodos() throws SQLException {
		return (List<Movimiento>)movimientosDao.obtenerTodos();
	}

	@Override
	public List<Movimiento> obtenerPorNumeroDeReferencia(int numeroReferencia) throws SQLException {
		return (List<Movimiento>)movimientosDao.obtenerPorNumeroDeReferencia(numeroReferencia);
	}

	@Override
	public List<Movimiento> obtenerPorCBU(String CBU) throws SQLException {
		return (List<Movimiento>)movimientosDao.obtenerPorCBU(CBU);
	}

	@Override
	public List<Movimiento> obtenerTransferenciasPorCliente(int cliente) throws SQLException, Exception {
		
	
		//Validar errores
		
		return (List<Movimiento>)movimientosDao.obtenerTransferenciasPorCliente(cliente);
	}

	@Override
	public HashMap<Integer, Destinatario> obtenerDestinatariosTransferenciasPorNumeroCliente(int numeroCliente) throws SQLException {
		return movimientosDao.obtenerDestinatariosTransferenciasPorNumeroCliente(numeroCliente);
	}

	@Override
	public List<Movimiento> obtenerPorCliente(int cliente) throws SQLException {
		return (List<Movimiento>)movimientosDao.obtenerPorCliente(cliente);
	}

	@Override
	public boolean insertar(Movimiento movimiento) throws SQLException {
		return movimientosDao.insertar(movimiento);
	}

	@Override
	public boolean editar(Movimiento movimiento) throws SQLException {
		return movimientosDao.editar(movimiento);
	}

	@Override
	public boolean borrar(int id) throws SQLException {
		return movimientosDao.borrar(id);
	}

	@Override
	public TipoMovimiento obtenerTipoMovimientoPorId(int id) throws SQLException {
		return (TipoMovimiento)movimientosDao.obtenerTipoMovimientoPorId(id);
	}

	@Override
	public List<TipoMovimiento> obtenerTipoMovimientos() throws SQLException {
		return (List<TipoMovimiento>)movimientosDao.obtenerTipoMovimientos();
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
			String number = "";	
			 Random random = new Random();
	
			for (int i = 0; i < 8; i++) {
				String randomNumber = String.valueOf(random.nextInt(9) + 1);
				number += randomNumber;
			}
			return Integer.parseInt(number);
		}
		
	
	//OBTENER ULTIMO ID DE MOVIMIENTO PARA AGREGAR AL NUMERO DE REFERENCIA
	@Override
	public int obtenerUltimoIdMovimiento() throws SQLException {
		return movimientosDao.obtenerUltimoIdMovimiento();
	}
	
	//TRANSFERENCIA
	public boolean insertarTransferencia(String cbuCliente, String cbuDestinatario, Double monto, String concepto) throws Exception {
		
		//Variables
		boolean transferenciaRealizada = false;
		int numeroReferencia = generarNumeroReferencia();
		
		//Chequear saldo
		CuentaNegocioDaoImp cuentaNegocioDaoImp = new CuentaNegocioDaoImp();
		Cuenta cuenta = cuentaNegocioDaoImp.obtenerUnaPorCBU(cbuCliente);
		
		if(cuenta.getSaldo() < monto) {
			//Lanzar error de saldo insuficiente
			throw new Exception("No tiene suficiente saldo para realizar la transferencia");
		}
		
		//Movimiento Entrada a Destinatario
		Movimiento movimiento = new Movimiento();
		movimiento.setMonto(monto);
		movimiento.setConcepto(concepto);
		movimiento.setEstado(new Estado(1, "Aprobado"));
		movimiento.setFechaMovimiento(LocalDate.now());
		movimiento.setOperacion(Operacion.Entrada);
		movimiento.setTipoMovimiento(new TipoMovimiento(4, "Transferencia"));
		movimiento.setNumeroReferencia(numeroReferencia);
		Cuenta cuentaAux = new Cuenta();
		cuentaAux.setCbu(cbuDestinatario);
		movimiento.setCuenta(cuentaAux);
		
		boolean esAprobada = this.insertar(movimiento);
		
		//Movimiento Salida Cliente
		if(esAprobada) {
			movimiento.setOperacion(Operacion.Salida);
			cuentaAux.setCbu(cbuCliente);
			movimiento.setCuenta(cuentaAux);
			esAprobada = transferenciaRealizada = this.insertar(movimiento);
			
			if(esAprobada) {
				//Restar Saldo
				cuentaNegocioDaoImp.actualizarSaldo(cbuDestinatario, monto);
				//SumarSaldo
				cuentaNegocioDaoImp.actualizarSaldo(cbuCliente, (monto*-1));
			}
			
		} else { //Si no se pudo transferir queda como rechazado
			movimiento.setOperacion(Operacion.Salida);
			cuentaAux.setCbu(cbuCliente);
			movimiento.setCuenta(cuentaAux);
			movimiento.setEstado(new Estado(1, "Rechazado"));
			transferenciaRealizada = this.insertar(movimiento);
			
			throw new Exception("La transferencia no pudo ser realizada");
		}
		
		return transferenciaRealizada;
	}

	@Override
	public Movimiento obtenerUnoPorId(int id) throws SQLException {

		return movimientosDao.obtenerUnoPorId(id);
	}
	
}
