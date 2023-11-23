package pruebas;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import daoImp.MovimientosDaoImpl;
import entidad.Cliente;
import entidad.Cuenta;
import entidad.CuotaPrestamo;
import entidad.Estado;
import entidad.Movimiento;
import entidad.Operacion;
import entidad.Prestamo;
import entidad.TipoMovimiento;
import entidad.TipoTasa;
import excepciones.OperacionCanceladaException;
import excepciones.SaldoInsuficienteException;
import negocioDao.IMovimientoNegocioDao;
import negocioDaoImp.ClienteNegocioDaoImp;
import negocioDaoImp.CuentaNegocioDaoImp;
import negocioDaoImp.MovimientoNegocioDaoImp;
import negocioDaoImp.PrestamosNegocioDaoImpl;

public class PruebaDbEntrega {
	
	public static void main(String[] args) throws SaldoInsuficienteException, OperacionCanceladaException, Exception {
	ClienteNegocioDaoImp cliNegocio = new ClienteNegocioDaoImp();
	MovimientoNegocioDaoImp movNegocio = new MovimientoNegocioDaoImp(); 
	CuentaNegocioDaoImp cueNegocio = new CuentaNegocioDaoImp();
	PrestamosNegocioDaoImpl presNegocio = new PrestamosNegocioDaoImpl();
	List<Cliente> listadoClientes = cliNegocio.obtenerTodos();
	//List<Cuenta> listadoCuentas = cueNegocio.obtenerTodas();
			
	// INSERTAR TODOS LOS MOVIMIENTOS DE ALTA DE CUENTAS
	insertarmovimientosAltasCuentas(movNegocio, new CuentaNegocioDaoImp());
	// INSERTAR TRANSFERENCIAS VARIAS + 1 ALTA PRESTAMO POR CLIENTE + 1 CUOTA PAGADA
	insertarMovimientosporCliente (listadoClientes, movNegocio,cueNegocio, presNegocio);



	
}

public static void 	insertarMovimientosporCliente (List<Cliente> listadoClientes, MovimientoNegocioDaoImp movNegocio, CuentaNegocioDaoImp cueNegocio, PrestamosNegocioDaoImpl presNegocio) throws SaldoInsuficienteException, OperacionCanceladaException, Exception {
	
// AGREGAR MAS TRANSFERENCIAS A CLIENTE 1

	for (Cliente c : listadoClientes) {
		System.out.println(listadoClientes.size());
		// SI EL ID CLIENTE
		if(c.getId()<listadoClientes.get(listadoClientes.size()-1).getId()) {
		
		System.out.println(c.getId());	
		List <Cuenta> cuentasCliente = cueNegocio.obtenerCuentasActivasCliente(c.getId());
		List <Cuenta> cuentasCliente2 = cueNegocio.obtenerCuentasActivasCliente(c.getId()+1);
		System.out.println(cuentasCliente2.size());	
		
		// INSERTAR PRIMER TRANSFERENCIA INTERNA
		if(movNegocio.insertarTransferenciaAnterior(cuentasCliente.get(0).getCbu(),cuentasCliente.get(1).getCbu(), 4000.00, "Varios", cuentasCliente.get(0).getFechaCreacion().plusDays(1))) {
			
			System.out.println("Transferencia cuenta 1 a 2 insertada.");
			
					
		}
		 
		//INSERTAR PRESTAMO
		insertarPrestamoyunPago(c,cuentasCliente, movNegocio, presNegocio, cueNegocio);
		
		
		// INSERTAR SEGUNDA TRANSFERENCIA INTERNA
		if(movNegocio.insertarTransferenciaAnterior(cuentasCliente.get(1).getCbu(),cuentasCliente.get(0).getCbu(), 3000.00, "Varios", cuentasCliente.get(0).getFechaCreacion().plusDays(1))) {
			
			System.out.println("Transferencia cuenta 2 a 1 insertada.");
					
		}
		
	
		// INSERTAR TRANSFERENCIA A TERCERO
		if(movNegocio.insertarTransferenciaAnterior(cuentasCliente.get(0).getCbu(),cuentasCliente2.get(0).getCbu(), 1000.00, "Varios",cuentasCliente.get(0).getFechaCreacion().plusDays(1))) {
				
				System.out.println("Transferencia a Tercero");
						
		}			
	
		
	}	
	
	
}

}
	public static void insertarPrestamoyunPago(Cliente cliente, List<Cuenta> cuentasCliente, MovimientoNegocioDaoImp movNegocio, PrestamosNegocioDaoImpl presNegocio, CuentaNegocioDaoImp cueNegocio) throws Exception {
		
		
		
		
		List<TipoTasa> tasas = presNegocio.obtenerTodosTiposTasas();

		
			
		   Random random = new Random();
			
			Estado estado = new Estado();
			estado.setIdEstado(1);
			estado.setDescripcion("Aprobado");
			
			
			TipoMovimiento tpaltaprestamo = movNegocio.obtenerTipoMovimientoPorId(2);
			
			
			Prestamo prestamo = new Prestamo();			
			prestamo.setMontoPedido((random.nextInt(9) + 1)*1000);
			prestamo.setTipoTasa(tasas.get(random.nextInt(3)));
			System.out.println(prestamo.getMontoPedido());
			prestamo.setMontoConIntereses(prestamo.getMontoPedido()+prestamo.getMontoPedido()*prestamo.getTipoTasa().getTasaInteres()/100);;
			System.out.println(prestamo.getMontoConIntereses());
			prestamo.setMontoxMes(prestamo.getMontoConIntereses()/prestamo.getTipoTasa().getCantCuotas());
			prestamo.setNumeroCuenta(cuentasCliente.get(0).getNumeroCuenta());
			prestamo.setEstado(estado);
			prestamo.setFechaPrestamo(cuentasCliente.get(0).getFechaCreacion().plusDays(1));
			prestamo.setIdCliente(cliente.getId());
			
						
			
			if(presNegocio.insertarprestamo(prestamo)) {
				
				System.out.println("Prestamo Insertado");
				
				Movimiento mov = new Movimiento();
				
				mov.setFechaMovimiento(prestamo.getFechaPrestamo());
				mov.setConcepto(tpaltaprestamo.getDescripcion());
				mov.setCuenta(cuentasCliente.get(0));
				mov.setEstado(estado);
				mov.setTipoMovimiento(tpaltaprestamo);
				mov.setMonto(prestamo.getMontoPedido());
				mov.setOperacion(Operacion.Entrada);
				mov.setNumeroReferencia(presNegocio.obtenerUltimoIdPrestamo());
								
				cueNegocio.actualizarSaldo(cuentasCliente.get(0).getCbu(), prestamo.getMontoPedido()); 

				System.out.println(cuentasCliente.get(0).getSaldo());
				movNegocio.insertar(mov);
				System.out.println("Movimiento Alta Prestamo insertado");
				
				int ultimoid = presNegocio.obtenerUltimoIdPrestamo();
				Prestamo prestamoinsertado = presNegocio.obteneruno(ultimoid);
				if(presNegocio.insertarcuotas(prestamoinsertado)) {
					
					System.out.println("CuotasInsertadas");
					
				}
				prestamoinsertado.setCuotasPrestamo((ArrayList<CuotaPrestamo>) presNegocio.obtenerCuotasxprestamo(ultimoid));
				
				if(presNegocio.setcuotapagadaxidcuota(prestamoinsertado.getCuotasPrestamo().get(0).getId(),prestamo.getFechaPrestamo().plusDays(1))) {
					
					TipoMovimiento tppagocuota = movNegocio.obtenerTipoMovimientoPorId(3);
					
					mov.setFechaMovimiento(prestamo.getFechaPrestamo().plusDays(1));
					mov.setConcepto(tppagocuota.getDescripcion());
					mov.setCuenta(cuentasCliente.get(0));
					mov.setEstado(estado);
					mov.setTipoMovimiento(tppagocuota);
					mov.setMonto(prestamoinsertado.getCuotasPrestamo().get(0).getMontoCuota());
					mov.setOperacion(Operacion.Salida);
					mov.setNumeroReferencia(prestamoinsertado.getCuotasPrestamo().get(0).getId());
					
					if(movNegocio.insertar(mov)) {
					cueNegocio.actualizarSaldo(cuentasCliente.get(0).getCbu(), (prestamoinsertado.getCuotasPrestamo().get(0).getMontoCuota())*-1);
					}
					
					System.out.println("Cuotas1pagadas");
				};
			};
			
			/*
			
			statement.setInt(5, prestamo.getEstado().getIdEstado());
			java.sql.Date fechaprestamoSQL = java.sql.Date.valueOf(prestamo.getFechaPrestamo());
			statement.setDate(6, fechaprestamoSQL);
			statement.setInt(7, prestamo.getIdCliente());
			statement.setInt(8,prestamo.getNumeroCuenta());
			
			*/
		
			
		
		
	
	
	}
	
public static void insertarmovimientosAltasCuentas(MovimientoNegocioDaoImp mDaoImpl, CuentaNegocioDaoImp cDaoImpl) throws SQLException {
		
		List<Cuenta> cuentascargadasenDB =null;
		
		try {
			cuentascargadasenDB = cDaoImpl.obtenerTodas();
						
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		for (Cuenta c : cuentascargadasenDB) {
			
			if(c.isActivo()) {
			Movimiento movimiento = new Movimiento();
			
			Estado estado = new Estado();
			estado.setDescripcion("Aprobado");
			
			estado.setIdEstado(1);
			movimiento.setCuenta(c);
			movimiento.setEstado(estado);
			movimiento.setFechaMovimiento(c.getFechaCreacion());
			movimiento.setMonto(10000.00);
			movimiento.setNumeroReferencia(c.getNumeroCuenta());				
			movimiento.setOperacion(Operacion.Entrada);
			
			TipoMovimiento tipoMovimiento = new TipoMovimiento();
			tipoMovimiento.setDescripcion("Alta de cuenta");
			tipoMovimiento.setId(1);
			movimiento.setTipoMovimiento(tipoMovimiento);
			
			mDaoImpl.insertar(movimiento);
			}
		}
		
		
	}
	
}
