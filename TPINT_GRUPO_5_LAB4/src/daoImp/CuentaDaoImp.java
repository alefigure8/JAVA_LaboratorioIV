package daoImp;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.Conexion;
import dao.ICuentaDao;
import entidad.Cuenta;
import entidad.Estado;
import entidad.TipoCuenta;

public class CuentaDaoImp implements ICuentaDao{
	
	private static final String insertCuenta = "Insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) values (?, ?, ?, ?, ?, ?)";
	private static final String updateCuenta = "Update Cuentas set NumeroCuenta = ?, CBU = ?, Saldo = ?, IdTipoCuenta = ?, IdCliente = ?, fechaCreacion = ?, Activo = ? where NumeroCuenta = ?";
	private static final String deleteCuenta = "Update Cuentas set Activo = 0 where NumeroCuenta = ?";
	private static final String readAllCuentas = "select * from Cuentas c \r\n" + 
			"inner join TiposCuenta tc on c.IdTipoCuenta = tc.IdTipoCuenta ";
			/*"inner join estados e on c.IdEstados = e.IdEstados";*/
	private static final String readAllCuentasPorCliente = "select * from Cuentas c \r\n" + 
			"inner join TiposCuenta tc on c.IdTipoCuenta = tc.IdTipoCuenta \r\n" + 
			 "where c.IdCliente = ?";
	/*"inner join estados e on c.IdEstados = e.IdEstados \r\n"*/
	private static final String readOnePorNroCuenta = "select * from Cuentas c \r\n" + 
			"inner join TiposCuenta tc on c.IdTipoCuenta = tc.IdTipoCuenta \r\n" + 
			"where c.NumeroCuenta = ?";
	private static final String readOnePorCBU = "select * from Cuentas c \r\n" + 
			"inner join TiposCuenta tc on c.IdTipoCuenta = tc.IdTipoCuenta \r\n" + 
			"where c.CBU = ?";
	/*"inner join estados e on c.IdEstados = e.IdEstados \r\n"*/
	private static final String countCuentasPorCliente = "select count(*) as 'Total' from Cuentas where IdCliente = ? and Activo=1";
	private static final String readAllTiposCuenta = "select * from TiposCuenta";
	private static final String countCbu="select count(*) as Count from Cuentas where Cbu = ?";
	private static final String ultimaCuentaInsertada="select max(NumeroCuenta) as NumeroCuenta from Cuentas c inner join Clientes ct on ct.Id=c.IdCliente where c.IdCliente = ? ";
	private static final String obtenerDescripcion="select descripcion from TiposCuenta where IdTipoCuenta = ?";

	// INSERTAR CUENTA
	@Override
	public boolean insertar(Cuenta cuenta) throws SQLException{
		PreparedStatement pStatement;
		Connection connection = Conexion.getConexion().getSQLConexion();
		boolean  insertExitoso=false;
		
		try {
	        pStatement = connection.prepareStatement(insertCuenta);
	       /* pStatement.setInt(1, cuenta.getNumeroCuenta());*/
	        pStatement.setString(1, cuenta.getCbu());
	        pStatement.setDouble(2, cuenta.getSaldo());
	        pStatement.setInt(3, (cuenta.getTipoCuenta()).getId());
	        pStatement.setInt(4, cuenta.getIdCliente());
			java.sql.Date fechaCreacionSQL = java.sql.Date.valueOf(cuenta.getFechaCreacion()); 
			pStatement.setDate(5, fechaCreacionSQL);
			/*pStatement.setInt(7, (cuenta.getEstado()).getIdEstado());*/
			pStatement.setBoolean(6,  cuenta.isActivo());
			
			if (pStatement.executeUpdate() > 0) {
	                insertExitoso = true;
	                connection.commit();
	        }

	    } catch (SQLException ex) {
	            throw ex;
	        }
	        	
		return insertExitoso;
	}

	// MODIFICAR CUENTA
	@Override
	public boolean editar(Cuenta cuenta) throws SQLException {
			PreparedStatement pStatement;
			Connection connection = Conexion.getConexion().getSQLConexion();
			boolean updateExitoso = false;
			
			try {
				pStatement = connection.prepareStatement(updateCuenta);
		        pStatement.setInt(1, cuenta.getNumeroCuenta());
		        pStatement.setString(2, cuenta.getCbu());
		        pStatement.setDouble(3, cuenta.getSaldo());
		        pStatement.setInt(4, (cuenta.getTipoCuenta()).getId());
		        pStatement.setInt(5, cuenta.getIdCliente());
				java.sql.Date fechaCreacionSQL = java.sql.Date.valueOf(cuenta.getFechaCreacion()); 
				pStatement.setDate(6, fechaCreacionSQL);
				/*pStatement.setInt(7, (cuenta.getEstado()).getIdEstado());*/
				pStatement.setBoolean(7,cuenta.isActivo());
				pStatement.setInt(8, cuenta.getNumeroCuenta());
				
				if(pStatement.executeUpdate() > 0) {
					connection.commit();
					updateExitoso = true;
				}
			}catch(SQLException e) {
				throw e;
			}
			
			return updateExitoso;
	}

	// BAJA LOGICA DE CUENTA
	@Override
	public boolean borrar(int nroCuenta) throws SQLException{
		PreparedStatement pStatement;
		Connection connection = Conexion.getConexion().getSQLConexion();
		boolean updateExitoso = false;
		
		try {
			pStatement = connection.prepareStatement(deleteCuenta);
	        pStatement.setInt(1, nroCuenta);
			
			if(pStatement.executeUpdate() > 0) {
				connection.commit();
				updateExitoso = true;
			}
		}catch(SQLException e) {
			throw e;
		}
		
		return updateExitoso;
	}

	// OBTENER TODAS LAS CUENTAS EXISTENTES
	@Override
	public List<Cuenta> obtenerTodas() throws SQLException{
		PreparedStatement pStatement;
		ResultSet rSet;
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		Conexion conexion= Conexion.getConexion();
		
		try {
			pStatement=conexion.getSQLConexion().prepareStatement(readAllCuentas);
			rSet=pStatement.executeQuery();
			
			while(rSet.next()) {
				cuentas.add(getCuenta(rSet));
			}
			
		} catch (Exception e) {
			throw e;
		}
		
		return cuentas;
	}
	
	// OBTENER TODAS LAS CUENTAS DE UN CLIENTE
	@Override
	public List<Cuenta> obtenerCuentasCliente(int idCliente) throws SQLException{
		PreparedStatement pStatement;
		ResultSet rSet;
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		Conexion conexion= Conexion.getConexion();
		
		try {
			pStatement=conexion.getSQLConexion().prepareStatement(readAllCuentasPorCliente);
			pStatement.setInt(1, idCliente);
			rSet=pStatement.executeQuery();
			
			while(rSet.next()) {
				cuentas.add(getCuenta(rSet));
			}
			
		} catch (Exception e) {
			throw e;
		}
		
		return cuentas;
	}

	// OBTENER UNA CUENTA ESPECIFICA DE UN CLIENTE
	@Override
	public Cuenta obtenerUna(int nroCuenta) throws SQLException{
		PreparedStatement pStatement;
		ResultSet rSet;
		Cuenta cuenta = new Cuenta();
		Conexion conexion= Conexion.getConexion();
		
		try {
			pStatement=conexion.getSQLConexion().prepareStatement(readOnePorNroCuenta);
			pStatement.setInt(1, nroCuenta);
			rSet=pStatement.executeQuery();
			
			while(rSet.next()) {
				cuenta = getCuenta(rSet);
			}
			
		} catch (Exception e) {
			throw e;
		}
		
		return cuenta;
	}
	
	//OBTENER UNA CUENTA POR CBU
	@Override
	public Cuenta obtenerUnaPorCBU(String cbu) throws SQLException{
		PreparedStatement pStatement;
		ResultSet rSet;
		Cuenta cuenta = new Cuenta();
		Conexion conexion= Conexion.getConexion();
		
		try {
			pStatement=conexion.getSQLConexion().prepareStatement(readOnePorNroCuenta);
			pStatement.setString(1, cbu);
			rSet=pStatement.executeQuery();
			
			while(rSet.next()) {
				cuenta = getCuenta(rSet);
			}
			
		} catch (Exception e) {
			throw e;
		}
		
		return cuenta;
	}
	

	// RSET PARA ARMAR LOS OBJETOS CUENTA
	private Cuenta getCuenta(ResultSet rSet) throws SQLException{
		
		//Atributos de la cuenta
		int nroCuenta  = rSet.getInt("NumeroCuenta");
		String cbu = rSet.getString("CBU");
		double saldo = rSet.getDouble("Saldo");
		
			// armo el tipo cuenta
			int idTipoCuenta = rSet.getInt("IdTipoCuenta");
			String descripcionTipoCuenta = rSet.getString("descripcion");
		TipoCuenta tipoCuenta = new TipoCuenta(idTipoCuenta, descripcionTipoCuenta); // creo el objeto tipo cuenta
		
		int idCliente = rSet.getInt("IdCliente");
		LocalDate fechaCreacion = rSet.getDate("fechaCreacion").toLocalDate();
		
			// armo el estado
			/*int idEstado = rSet.getInt("IdEstados");*/
			String descripcionEstado = rSet.getString("Descripcion");
		/*Estado estadoCuenta = new Estado(idEstado, descripcionEstado); // creo el objeto estado*/
		
	    boolean activo = rSet.getBoolean("Activo");
	    
	    return new Cuenta(nroCuenta, cbu, saldo, tipoCuenta, idCliente, fechaCreacion, /*estadoCuenta, */activo);
		
	}
	

	// OBETENER LA CANTIDAD DE CUENTAS DE UN CLIENTE
	@Override
	public int cantidadCuentas(int idCliente) throws SQLException{
		PreparedStatement pStatement;
		ResultSet rSet;
		int totalCuentas = 0;
		Conexion conexion= Conexion.getConexion();
		
		try {
			pStatement=conexion.getSQLConexion().prepareStatement(countCuentasPorCliente);
			pStatement.setInt(1, idCliente);
			rSet=pStatement.executeQuery();
			
			while(rSet.next()) {
				totalCuentas = rSet.getInt("total");
			}
			
		} catch (Exception e) {
			throw e;
		}
		
		return totalCuentas;
	}

	// OBTENER LISTA DE TIPOS DE CUENTAA
	@Override
	public List<TipoCuenta> listarTiposCuenta() throws SQLException{
		PreparedStatement pStatement;
		ResultSet rSet;
		List<TipoCuenta> listaTiposCuenta = new ArrayList<TipoCuenta>();
		Conexion conexion= Conexion.getConexion();
		
		try {
			pStatement=conexion.getSQLConexion().prepareStatement(readAllTiposCuenta);
			rSet=pStatement.executeQuery();
			
			while(rSet.next()) {
				listaTiposCuenta.add(getTipoCuenta(rSet));
			}
			
		} catch (Exception e) {
			throw e;
		}
		
		return listaTiposCuenta;
	}
	
	// ARMAR LOS TIPOS DE CUENTA
	private TipoCuenta getTipoCuenta(ResultSet rSet) throws SQLException{
		int idTipoCuenta  = rSet.getInt("IdTipoCuenta");
		String descripcion = rSet.getString("descripcion");
	    return new TipoCuenta(idTipoCuenta, descripcion);
	}

	@Override
	public boolean cbuExiste(String cbu) throws SQLException{
		
		PreparedStatement pStatement;
		ResultSet rSet;
		Conexion conexion= Conexion.getConexion();
		
		try {
			pStatement=conexion.getSQLConexion().prepareStatement(countCbu);
			pStatement.setString(1, cbu);
			rSet=pStatement.executeQuery();
			
			if(rSet.next()) {
				int count = rSet.getInt("Count");
				return count>0;
			}
			
		} catch (Exception e) {
			throw e;
		}
		
		
		return false;
	}
	
	//ULTIMO NRO. CUENTA INSERTADA SEGUN UN ID CLIENTE
	@Override
	public int obtenerUltimaInsertada(int idCliente) throws SQLException {
		PreparedStatement pStatement;
		ResultSet rSet;
		int cuenta = 0;
		Conexion conexion= Conexion.getConexion();
		
		try {
			pStatement=conexion.getSQLConexion().prepareStatement(ultimaCuentaInsertada);
			pStatement.setInt(1, idCliente);
			rSet=pStatement.executeQuery();
			
			while(rSet.next()) {
				cuenta = rSet.getInt("NumeroCuenta");
			}
			
		} catch (Exception e) {
			throw e;
		}
		
		return cuenta;
		
	}

	@Override
	public String obtenerDescripcion(int id) throws SQLException {
		PreparedStatement pStatement;
		ResultSet rSet;
		Conexion conexion= Conexion.getConexion();
		String descripcion="";
		try {
			pStatement=conexion.getSQLConexion().prepareStatement(obtenerDescripcion);
			pStatement.setInt(1, id);
			rSet=pStatement.executeQuery();
			
			if(rSet.next()) {
				descripcion= rSet.getString("descripcion");
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
			
		return descripcion;
		
	}
	

}
