package pruebas;

import java.io.Console;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import daoImp.CuentaDaoImp;
import entidad.Cuenta;
import entidad.Estado;
import entidad.TipoCuenta;

public class PruebaCuentas {

	public static void main(String[] args) throws SQLException {
		CuentaDaoImp ctaDao = new CuentaDaoImp();
		
		List<Cuenta> listaCuentas = new ArrayList<Cuenta>();
		
		listaCuentas =  ctaDao.obtenerCuentasCliente(17);
	
		Cuenta cuenta = listaCuentas.get(0);
		
		long numero = cuenta.getNumeroCuenta(); // Reemplaza esto con tu número de 10 cifras
		String stringnumero = String.valueOf(numero);

        StringBuilder nuevoFormato = new StringBuilder();
        nuevoFormato.append(stringnumero, 0, 2).append("-").append(stringnumero, 2, 9).append("/").append(stringnumero.charAt(9));
        // Imprime el resultado
        System.out.println(nuevoFormato.toString());


		
/*
		System.out.println("PRUEBA LISTAR TODAS LAS CUENTAS DE UN CLIENTE");
		try {
			listaCuentas = ctaDao.obtenerCuentasCliente(1) ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (Cuenta cuenta : listaCuentas) {
			System.out.println(cuenta.toString());
		}
		
		
		// PRUEBA INSERTAR
		System.out.println("PRUEBA INSERTAR CUENTA");
		int numeroCuenta = 1002035735;
		String cbu = "19892199619662021";
		double saldo = 200.000; 
		TipoCuenta tipoCuenta = new TipoCuenta(1, "cajita");
		int idCliente = 2;
		LocalDate fechaCreacion = LocalDate.now();
	   /* Estado estado = new Estado(1, "Pendiente");*/
		
	    //boolean activo = true;
		
	//	Cuenta cuenta1 = new Cuenta(numeroCuenta, cbu, saldo, tipoCuenta, idCliente, fechaCreacion, /*estado, */activo);
		/*
		try {
			ctaDao.insertar(cuenta1);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		*/
	/*	
		System.out.println("PRUEBA LISTAR TODAS LAS CUENTAS");
		
		try {
			listaCuentas = ctaDao.obtenerTodas();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (Cuenta cuenta : listaCuentas) {
			System.out.println(cuenta.toString());
		}
		
		
		System.out.println("PRUEBA BAJA LOGICA CUENTA");

		try {
		    int numeroCuentaAEliminar = cuenta1.getNumeroCuenta(); // Obtiene el n�mero de cuenta a eliminar
		    ctaDao.borrar(numeroCuentaAEliminar);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		System.out.println("PRUEBA CONTAR CUENTAS X CLIENTE");
		try {
			int cantidadCuentas = ctaDao.cantidadCuentas(1);
			System.out.println("Cantidad cuentas: " + cantidadCuentas);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		cuenta1.setActivo(true);
		System.out.println("PRUEBA ACTUALIZAR CUENTAS X CLIENTE");
		try {
			ctaDao.editar(cuenta1);
			System.out.println("Modificacion: " + (ctaDao.editar(cuenta1)) );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("PRUEBA BUSCAR X NROCUENTA");
		try {
			int nroCuenta = cuenta1.getNumeroCuenta();
			System.out.println("Modificacion: " + (ctaDao.obtenerUna(nroCuenta)).getNumeroCuenta());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// tipos cuenta
		
		List<TipoCuenta> listaTiposCuenta = new ArrayList<TipoCuenta>();
		try {
			listaTiposCuenta = ctaDao.listarTiposCuenta();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(TipoCuenta tipo : listaTiposCuenta) {
			System.out.println(tipo.toString());
		}
*/
	}
	
}
