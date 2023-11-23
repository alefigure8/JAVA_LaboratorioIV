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
import excepciones.OperacionCanceladaException;
import excepciones.SaldoInsuficienteException;
import negocioDao.IMovimientoNegocioDao;
import entidad.Cliente;
import entidad.Cuenta;
import entidad.Destinatario;
import entidad.Estado;
import entidad.Operacion;
import javafx.util.converter.LocalDateStringConverter;

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
	public List<Movimiento> obtenerTransferenciasPorCliente(int cliente) throws SQLException {
		return (List<Movimiento>)movimientoNegocio.obtenerTransferenciasPorCliente(cliente);
	}

	@Override
	public HashMap<Integer, Destinatario> obtenerDestinatariosTransferenciasPorNumeroCliente(int numeroCliente) throws SQLException {
		return movimientoNegocio.obtenerDestinatariosTransferenciasPorNumeroCliente(numeroCliente);
	}

	@Override
	public List<Movimiento> obtenerPorCliente(int cliente) throws SQLException {
		return (List<Movimiento>)movimientoNegocio.obtenerPorCliente(cliente);
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
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
		return movimientoNegocio.obtenerUltimoIdMovimiento();
	}
	
	//TRANSFERENCIA
	public boolean insertarTransferencia(String cbuCliente, String cbuDestinatario, Double monto, String concepto) throws Exception, SaldoInsuficienteException, OperacionCanceladaException {
		
		//Variables
		boolean transferenciaRealizada = false;
		int numeroReferencia = generarNumeroReferencia();
		
		//Chequear saldo
		CuentaNegocioDaoImp cuentaNegocioDaoImp = new CuentaNegocioDaoImp();
		Cuenta cuenta = cuentaNegocioDaoImp.obtenerUnaPorCBU(cbuCliente);
		
		if(cuenta.getSaldo() < monto) {
			//Lanzar error de saldo insuficiente
			throw new SaldoInsuficienteException();
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
			
			throw new OperacionCanceladaException();
		}
		
		return transferenciaRealizada;
	}

	//TRANSFERENCIA
		public boolean insertarTransferenciaAnterior(String cbuCliente, String cbuDestinatario, Double monto, String concepto,LocalDate fecha) throws Exception, SaldoInsuficienteException, OperacionCanceladaException {
			
			//Variables
			boolean transferenciaRealizada = false;
			int numeroReferencia = generarNumeroReferencia();
			
			//Chequear saldo
			CuentaNegocioDaoImp cuentaNegocioDaoImp = new CuentaNegocioDaoImp();
			Cuenta cuenta = cuentaNegocioDaoImp.obtenerUnaPorCBU(cbuCliente);
			
			if(cuenta.getSaldo() < monto) {
				//Lanzar error de saldo insuficiente
			//	throw new SaldoInsuficienteException();
			}
			
			//Movimiento Entrada a Destinatario
			Movimiento movimiento = new Movimiento();
			movimiento.setMonto(monto);
			movimiento.setConcepto(concepto);
			movimiento.setEstado(new Estado(1, "Aprobado"));
			movimiento.setFechaMovimiento(fecha);
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
				
				throw new OperacionCanceladaException();
			}
			
			return transferenciaRealizada;
		}
	
	@Override
	public Movimiento obtenerUnoPorId(int id) throws SQLException {

		return movimientoNegocio.obtenerUnoPorId(id);
	}
	
	@Override
	public int totalTransferenciasAnio(String anio)throws SQLException{
		 
		 

		    return movimientoNegocio.totalTransferenciasAnio(anio);
		 
		 }
	@Override
	public int totalTransferenciasAnioMes(String anio,String mes)throws SQLException{
		 
		 

		    return movimientoNegocio.totalTransferenciasAnioMes(anio, mes);
		 
		 }
	@Override
	public double MontoTransferenciaAnio(String anio)throws SQLException{
		 
		 

		    return movimientoNegocio.MontoTransferenciaAnio(anio);
		 
		 }
	@Override
	public double MontoTransferenciaAnioMes(String anio,String mes)throws SQLException{
		 
		 

		    return movimientoNegocio.MontoTransferenciaAnioMes(anio, mes);
		 
		 }

	@Override
	public List<Movimiento> obtenerUltimosTresMovimientos(String CBU) throws SQLException {
		return movimientoNegocio.obtenerUltimosTresMovimientos(CBU);
	}
	
}
