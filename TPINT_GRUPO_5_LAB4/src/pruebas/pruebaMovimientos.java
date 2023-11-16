package pruebas;

import java.time.LocalDate;
import java.util.List;

import daoImp.MovimientosDaoImpl;
import entidad.Cuenta;
import entidad.Estado;
import entidad.Movimiento;
import entidad.Operacion;
import entidad.TipoMovimiento;

public class pruebaMovimientos {

	public static void main(String[] args) {
		//listarTipoMovimientos(new MovimientosDaoImpl()); -- OK
		//obtenerTipoMovimientoPorId(new MovimientosDaoImpl()); -- OK
		insertar(new MovimientosDaoImpl());
		//obtenerPorCBU(new MovimientosDaoImpl()); -- OK
		//obtenerPorNumeroDeReferencia(new MovimientosDaoImpl()); --OK
		//listarMovimientos(new MovimientosDaoImpl()); -- OK
		
		/* 
		 	--INSERT EN BASE DE DATOS PARA QUE COINCIDAN LOS DATOS
		 	
			-- Insertar Estados
			insert into Estados (descripcion) values ("Aprobado"), ("Pendiente"), ("Rechazado");
			
			-- Insertar Tipo Movimientos
			insert into TiposMovimiento (descripcion) values ("Alta de cuenta"),("Alta de un prestamo"),("Pago prestamo"),("Transferencia");
			
			-- InsertCuenta
			insert into TiposCuenta (Descripcion) values ("Cuenta Ahorro"), ("Cuenta Corriente");
			
			-- Insert Cuenta
			insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, IdEstados, Activo) 
			values ("123456789", 10000.00, 1, 2, NOW(), 1, 1),
			("987654321", 10000.00, 2, 3, NOW(), 1, 1);
			
		 */
	}
	
	public static void insertar(MovimientosDaoImpl mDaoImpl) {
		
		System.out.println("INSERTAR");
		
		Movimiento movimiento = new Movimiento();
		//movimiento.setCbudestino("123456789");
		Cuenta cuenta=new Cuenta();
		cuenta.setCbu("4969975451108597159766");
		movimiento.setCuenta(cuenta);
		movimiento.setConcepto("Pago de Cuota");
		
		Estado estado = new Estado();
		estado.setDescripcion("Aprobado");
		estado.setIdEstado(1);
		
		movimiento.setEstado(estado);
		movimiento.setFechaMovimiento(LocalDate.now());
		movimiento.setMonto(102.00);
		movimiento.setNumeroReferencia(1777);
			
		movimiento.setOperacion(Operacion.Entrada);
		
		TipoMovimiento tipoMovimiento = new TipoMovimiento();
		tipoMovimiento.setDescripcion("Pago de Cuota");
		tipoMovimiento.setId(3);
		movimiento.setTipoMovimiento(tipoMovimiento);
		
		try {
			mDaoImpl.insertar(movimiento);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void listarTipoMovimientos(MovimientosDaoImpl mDaoImpl) {
		System.out.println("TIPO MOVIMIENTOS");
		
		 try {
			 List<TipoMovimiento> tipoMovimientos = mDaoImpl.obtenerTipoMovimientos();
			 
			 for(TipoMovimiento tipoMovimiento : tipoMovimientos) {
				 System.out.println(tipoMovimiento.getDescripcion());
			 }
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
	}
	
	public static void obtenerTipoMovimientoPorId(MovimientosDaoImpl mDaoImpl) {
		System.out.println("TIPO MOVIMIENTOS POR CBU");
		
		 try {
			TipoMovimiento tipoMovimiento = mDaoImpl.obtenerTipoMovimientoPorId(1);
			 
			System.out.println(tipoMovimiento.getDescripcion());
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void obtenerPorCBU(MovimientosDaoImpl mDaoImpl) {
		System.out.println("TIPO MOVIMIENTOS POR CBU");
		
		 try {
			 List<Movimiento> movimientos = mDaoImpl.obtenerPorCBU("123456789");
			 
			 for(Movimiento tipoMovimiento : movimientos) {
				 System.out.println(tipoMovimiento.getNumeroReferencia());
			 }
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void obtenerPorNumeroDeReferencia(MovimientosDaoImpl mDaoImpl) {
		System.out.println("TIPO MOVIMIENTOS POR REFERENCIA");
		
		 try {
			 List<Movimiento> movimientos = mDaoImpl.obtenerPorNumeroDeReferencia(123456);
			 
			 for(Movimiento movimiento : movimientos) {
				 System.out.println(movimiento.getNumeroReferencia());
			 }
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void listarMovimientos(MovimientosDaoImpl mDaoImpl) {
		System.out.println("MOVIMIENTOS");
		
		 try {
			 List<Movimiento> movimientos = mDaoImpl.obtenerTodos();
			 
			 for(Movimiento movimiento : movimientos) {
				 System.out.println(movimiento.getMonto());
			 }
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
