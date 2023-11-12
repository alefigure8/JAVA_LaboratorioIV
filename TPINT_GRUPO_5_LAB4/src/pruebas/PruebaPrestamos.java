package pruebas;
import java.io.Console;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.IPrestamosDao;
import entidad.Prestamo;
import entidad.CuotaPrestamo;
import entidad.Estado;
import entidad.TipoTasa;
import daoImp.PrestamosImpl;


public class PruebaPrestamos {

	public static void main(String[] args) {

			IPrestamosDao prestamos = new PrestamosImpl();
			Prestamo prestamo = prestamos.obteneruno(2);
			ArrayList<Prestamo> listado ;
			ArrayList<CuotaPrestamo> listadocuotas ;
			ArrayList<TipoTasa> tasas = new ArrayList<TipoTasa>(); 
		/*	
		 * Prestamo prestamo = new Prestamo();
			TipoTasa tasa = new TipoTasa();
			Estado estado = new Estado();
			
			//Lo seteamos pendiente
			estado.setIdEstado(2);
			//estado.setDescripcion("Bien");
			
			tasa.setId(1);
			tasa.setCantCuotas(12);
			tasa.setTasaInteres(10);
			
			prestamo.setIdCliente(1);
			prestamo.setFechaPrestamo(LocalDate.now());
			prestamo.setMontoPedido(1000);
			prestamo.setMontoConIntereses(prestamo.getMontoPedido()*tasa.getTasaInteres());
			prestamo.setEstado(estado);
			prestamo.setTipoTasa(tasa);
			prestamo.setNumeroCuenta(1000000004);
			prestamo.setMontoxMes(prestamo.getMontoPedido()/tasa.getCantCuotas());
			System.out.println("hola?");
			prestamos.insertarprestamo(prestamo);
			
			*/
			/*
			listado = (ArrayList<Prestamo>) prestamos.obtenerTodosxcuenta(1000000004);
			
			for (Prestamo p : listado) {
				
			
				System.out.println(p.toString());
			}
			
			*/
			/*
			tasas = (ArrayList<TipoTasa>) prestamos.obtenerTodosTiposTasas();
			
			for (TipoTasa p : tasas) {
				
				
				System.out.println(p.toString());
			}
			*/
			
			///prestamos.insertarcuotas(prestamo);
			
			//listadocuotas = (ArrayList<CuotaPrestamo>) prestamos.obtenerCuotasxprestamo(2);
			
			//CuotaPrestamo cuota = prestamos.obtenerUnaCuota(1, 2);
			
			//System.out.println(cuota.toString());
			/*
			for (CuotaPrestamo p : listadocuotas) {
				
				
				System.out.println(cuota.toString());
			}
			*/
			
			prestamos.setcuotapagada(2, 1);
	}

}
