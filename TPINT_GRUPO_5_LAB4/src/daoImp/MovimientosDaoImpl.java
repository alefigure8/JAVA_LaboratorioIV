package daoImp;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dao.Conexion;
import dao.IMovimientosDao;
import entidad.Cuenta;
import entidad.Destinatario;
import entidad.Estado;
import entidad.Movimiento;
import entidad.Operacion;
import entidad.TipoCuenta;
import entidad.TipoMovimiento;

public class MovimientosDaoImpl implements IMovimientosDao{
	
	/**** MOVIMIENTOS ****/
	
	//QUERIES
	private static final String selectAll = 	"select *,TC.descripcion as tipoCuentaDescripcion, TM.descripcion as tipoMovimientoDescripcion, E.descripcion as estadoDescripcion from Movimientos M " +
												"inner join Estados E " +
												"on M.IdEstados=E.IdEstados " + 
												"inner join TiposMovimiento TM " +
												"on TM.IdTipoMovimiento=M.IdTipoMovimiento " +
												"inner join Cuentas C " +
												"on C.CBU=M.CBU "+ 
												"inner join TiposCuenta TC " +
												"on TC.IdTipoCuenta=C.IdTipoCuenta ";
	
	private static final String selectOne = 	"select *,TC.descripcion as tipoCuentaDescripcion, TM.descripcion as tipoMovimientoDescripcion, E.descripcion as estadoDescripcion from Movimientos M " +
												"inner join Estados E " +
												"on M.IdEstados=E.IdEstados " + 
												"inner join TiposMovimiento TM " +
												"on TM.IdTipoMovimiento=M.IdTipoMovimiento " +
												"inner join Cuentas C " +
												"on C.CBU=M.CBU " +
												"inner join TiposCuenta TC " +
												"on TC.IdTipoCuenta=C.IdTipoCuenta "+
												"where ID = ?";
			
	private static final String selectPorNumeroReferencia = "select *,TC.descripcion as tipoCuentaDescripcion, TM.descripcion as tipoMovimientoDescripcion, E.descripcion as estadoDescripcion from Movimientos M " +
																"inner join Estados E " +
																"on M.IdEstados=E.IdEstados " + 
																"inner join TiposMovimiento TM " +
																"on TM.IdTipoMovimiento=M.IdTipoMovimiento " +
																"inner join Cuentas C " +
																"on C.CBU=M.CBU " +
																"inner join TiposCuenta TC " +
																"on TC.IdTipoCuenta=C.IdTipoCuenta "+
																"where NumeroReferencia = ?";
			
	private static final String selectPorNumeroCuenta =	"select *,TC.descripcion as tipoCuentaDescripcion, TM.descripcion as tipoMovimientoDescripcion, E.descripcion as estadoDescripcion from Movimientos M " +
														"inner join Estados E " +
														"on M.IdEstados=E.IdEstados " + 
														"inner join TiposMovimiento TM " +
														"on TM.IdTipoMovimiento=M.IdTipoMovimiento " +
														"inner join Cuentas C " +
														"on C.CBU=M.CBU " +
														"inner join TiposCuenta TC " +
														"on TC.IdTipoCuenta=C.IdTipoCuenta "+
														"where M.CBU = ?";

	private static final String selectPorNumeroCliente =	"select *,TC.descripcion as tipoCuentaDescripcion, TM.descripcion as tipoMovimientoDescripcion, E.descripcion as estadoDescripcion from Movimientos M " +
															"inner join Estados E " +
															"on M.IdEstados=E.IdEstados " + 
															"inner join TiposMovimiento TM " +
															"on TM.IdTipoMovimiento=M.IdTipoMovimiento " +
															"inner join Cuentas C " +
															"on C.CBU=M.CBU " +
															"inner join TiposCuenta TC " +
															"on TC.IdTipoCuenta=C.IdTipoCuenta "+
															"where C.IdCliente = ?";

	private static final String selectTransferenciaPorNumeroCliente =	"select *,TC.descripcion as tipoCuentaDescripcion, TM.descripcion as tipoMovimientoDescripcion, E.descripcion as estadoDescripcion from Movimientos M " +
																		"inner join Estados E " +
																		"on M.IdEstados=E.IdEstados " + 
																		"inner join TiposMovimiento TM " +
																		"on TM.IdTipoMovimiento=M.IdTipoMovimiento " +
																		"inner join Cuentas C " +
																		"on C.CBU=M.CBU " +
																		"inner join TiposCuenta TC " +
																		"on TC.IdTipoCuenta=C.IdTipoCuenta "+
																		"where TM.IdTipoMovimiento = 4 " + 
																		"and C.IdCliente = ?";
	
	private static final String obtenerDestinatariosTransferenciasPorNumeroCliente = 	"SELECT MO.NumeroReferencia, CU.CBU, CLI.Nombre, CLI.Apellido, CU.NumeroCuenta " +
																						"FROM Movimientos MO " +
																						"INNER JOIN Cuentas CU " +
																						"ON MO.CBU = CU.CBU " +
																						"INNER JOIN Clientes CLI " +
																						"ON CLI.Id = CU.IdCliente " +
																						"Where NumeroReferencia in " +
																						"(SELECT MO.NumeroReferencia " +
																						"FROM Movimientos MO " +
																						"INNER JOIN Cuentas CU " +
																						"ON MO.CBU = CU.CBU " +
																						"INNER JOIN Clientes CLI " +
																						"ON CLI.Id = CU.IdCliente " +
																						"WHERE CLI.Id = ?) " +
																						"AND CLI.ID <> ?; ";

	private static final String insert = "INSERT INTO Movimientos (idTipoMovimiento, NumeroReferencia, CBU, " +
            "Monto, Operacion, FechaMovimiento, IdEstados, Concepto) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final String update = "UPDATE Movimientos " +
								            "SET idTipoMovimiento = ?, NumeroReferencia = ?, CBU = ?, Monto = ?, " +
								            "Operacion = ?, FechaMovimiento = ?, idEstado = ?, Concepto = ? " +
								            "WHERE ID = ?";
	
	private static final String delete = "DELETE FROM movimientos WHERE ID = ?";
	
	private static final String obtenerUltimoIdMovimiento="select MAX(ID) as MaxId from movimientos";
		
	
	private static final String TotalTransferenciasAnio = "SELECT COUNT(DISTINCT NumeroReferencia) AS TotalTransferencias\r\n" + 
			"FROM Movimientos\r\n" + 
			"WHERE IdTipoMovimiento = (SELECT IdTipoMovimiento FROM TiposMovimiento WHERE descripcion = 'Transferencia')\r\n" + 
			"    AND YEAR(FechaMovimiento) = ?" ;
			
	private static final String TotalTransferenciasAnioMes = "SELECT COUNT(DISTINCT NumeroReferencia) AS TotalTransferencias " + 
			"FROM Movimientos " + 
			"WHERE IdTipoMovimiento = (SELECT IdTipoMovimiento FROM TiposMovimiento WHERE descripcion = 'Transferencia') " + 
			"AND YEAR(FechaMovimiento) = ? " + 
			"AND MONTH(FechaMovimiento) = ?";
	
	private static final String montoTransferenciasAnio = "SELECT COALESCE(SUM(Monto), 0) AS TotalMontoTransferencias\r\n" + 
			"FROM (\r\n" + 
			"    SELECT DISTINCT NumeroReferencia, Monto\r\n" + 
			"    FROM Movimientos\r\n" + 
			"    WHERE IdTipoMovimiento = (SELECT IdTipoMovimiento FROM TiposMovimiento WHERE descripcion = 'Transferencia')\r\n" + 
			"        AND YEAR(FechaMovimiento) = ?\r\n" + 
			") AS TransferenciasUnicas;";
	
	private static final String montoTransferenciasAnioMes = "SELECT COALESCE(SUM(Monto), 0) AS TotalMontoTransferencias\r\n" + 
			"FROM (\r\n" + 
			"    SELECT DISTINCT NumeroReferencia, Monto\r\n" + 
			"    FROM Movimientos\r\n" + 
			"    WHERE IdTipoMovimiento = (SELECT IdTipoMovimiento FROM TiposMovimiento WHERE descripcion = 'Transferencia')\r\n" + 
			"        AND YEAR(FechaMovimiento) = ?\r\n" + 
			"        AND MONTH(FechaMovimiento) = ?\r\n" + 
			") AS TransferenciasUnicas;";
	
	//OBTENER TODOS
	public List<Movimiento> obtenerTodos() throws SQLException {
		PreparedStatement pStatement;
		ResultSet rSet;
		ArrayList<Movimiento> movimientos=new ArrayList<Movimiento>();
		Conexion conexion = Conexion.getConexion();
		
		try {
			pStatement=conexion.getSQLConexion().prepareStatement(selectAll);
			rSet=pStatement.executeQuery();
			
			while(rSet.next()) {
				movimientos.add(getMovimiento(rSet));
			}
			
		} catch (SQLException e) {
			throw e;
		}
		
		return movimientos;
	}

	//OBTENER POR NUMERO DE REFERENCIA
	public List<Movimiento> obtenerPorNumeroDeReferencia(int numeroReferencia) throws SQLException {
		PreparedStatement pStatement;
		ResultSet rSet;
		List<Movimiento> movimientos = new ArrayList<Movimiento>();
		Conexion conexion = Conexion.getConexion();
		
		try {
			pStatement=conexion.getSQLConexion().prepareStatement(selectPorNumeroReferencia);
			pStatement.setInt(1, numeroReferencia);
			rSet=pStatement.executeQuery();
			
			while(rSet.next()) {
				movimientos.add(getMovimiento(rSet));
			}
			
		} catch (SQLException e) {
			throw e;
		}
		
		return movimientos;
	}

	//OBTENER POR ID CLIENTE
	public List<Movimiento> obtenerPorCliente(int cliente) throws SQLException {
		PreparedStatement pStatement;
		ResultSet rSet;
		List<Movimiento> movimientos = new ArrayList<Movimiento>();
		Conexion conexion = Conexion.getConexion();
		
		try {
			pStatement=conexion.getSQLConexion().prepareStatement(selectPorNumeroCliente);
			pStatement.setInt(1, cliente);
			rSet=pStatement.executeQuery();
			
			while(rSet.next()) {
				movimientos.add(getMovimiento(rSet));
			}
			
		} catch (SQLException e) {
			throw e;
		}
		
		return movimientos;
	}
	
	//OBTENER TRANSFERENCIAS POR NUMERO DE CLIENTE
	public List<Movimiento> obtenerTransferenciasPorCliente(int cliente) throws SQLException {
		PreparedStatement pStatement;
		ResultSet rSet;
		List<Movimiento> movimientos = new ArrayList<Movimiento>();
		Conexion conexion = Conexion.getConexion();
		
		try {
			pStatement=conexion.getSQLConexion().prepareStatement(selectTransferenciaPorNumeroCliente);
			pStatement.setInt(1, cliente);
			rSet=pStatement.executeQuery();
			
			while(rSet.next()) {
				movimientos.add(getMovimiento(rSet));
			}
			
		} catch (SQLException e) {
			throw e;
		}
		
		return movimientos;
	}

	//OBTENER POR CBU
	public List<Movimiento> obtenerPorCBU(String CBU) throws SQLException {
		PreparedStatement pStatement;
		ResultSet rSet;
		List<Movimiento> movimientos=new ArrayList<Movimiento>();
		Conexion conexion = Conexion.getConexion();
		
		try {
			pStatement=conexion.getSQLConexion().prepareStatement(selectPorNumeroCuenta);
			pStatement.setString(1, CBU);
			rSet=pStatement.executeQuery();
			
			while(rSet.next()) {
				movimientos.add(getMovimiento(rSet));
			}
			
		} catch (SQLException e) {
			throw e;
		}
		
		return movimientos;
	}

	//OBTENER NUMERO REFERENCIA Y NOMBRE DE DESTINATARIO POR ID CLIENTE ORIGEN
	public HashMap<Integer, Destinatario> obtenerDestinatariosTransferenciasPorNumeroCliente(int numeroCliente) throws SQLException {
			
		PreparedStatement pStatement;
		ResultSet rSet;
		HashMap<Integer, Destinatario> destinatarios = new HashMap<Integer, Destinatario>();
		Conexion conexion = Conexion.getConexion();
		
		try {
			pStatement=conexion.getSQLConexion().prepareStatement(obtenerDestinatariosTransferenciasPorNumeroCliente);
			pStatement.setInt(1, numeroCliente);
			pStatement.setInt(2, numeroCliente);
			rSet=pStatement.executeQuery();
			
			while(rSet.next()) {
				int numeroReferencia = rSet.getInt("NumeroReferencia");
				String nombre = rSet.getString("Nombre");
				String apellido = rSet.getString("Apellido");
				String cbu = rSet.getString("CBU");
				int numeroCuenta = rSet.getInt("NumeroCuenta");
				
				Destinatario destinatario = new Destinatario(nombre, apellido, cbu, numeroCuenta);
				destinatarios.put(numeroReferencia, destinatario);
			}
			
		} catch (SQLException e) {
			throw e;
		}
		
		return destinatarios;
	}


	//INSERTAR
	public boolean insertar(Movimiento movimiento) throws SQLException {
		PreparedStatement pStatement;
		Connection connection = Conexion.getConexion().getSQLConexion();
		boolean insertExitoso = false;
		
		try {
			pStatement = connection.prepareStatement(insert);
			pStatement.setInt(1, movimiento.getTipoMovimiento().getId());
			pStatement.setInt(2, movimiento.getNumeroReferencia());
			pStatement.setString(3, movimiento.getCuenta().getCbu());//GET CUENTA GET CBU
			pStatement.setDouble(4, movimiento.getMonto());
			pStatement.setString(5, movimiento.getOperacion());
			pStatement.setDate(6, Date.valueOf(movimiento.getFechaMovimiento()));
			pStatement.setInt(7, movimiento.getEstado().getIdEstado());
			pStatement.setString(8, movimiento.getConcepto());
			
			if(pStatement.executeUpdate() > 0) {
				connection.commit();
				insertExitoso = true;
			}
		}catch(SQLException e) {
			throw e;
		}
		
		return insertExitoso;
	}

	//EDITAR
	public boolean editar(Movimiento movimiento) throws SQLException {
		PreparedStatement pStatement;
		Connection connection = Conexion.getConexion().getSQLConexion();
		boolean updateExitoso = false;
		
		try {
			pStatement = connection.prepareStatement(update);
			
			pStatement.setInt(1, movimiento.getTipoMovimiento().getId());
			pStatement.setString(3, movimiento.getCuenta().getCbu());//GET CUENTA GET CBU
			pStatement.setDouble(4, movimiento.getMonto());
			pStatement.setString(5, movimiento.getOperacion());
			pStatement.setDate(6, Date.valueOf(movimiento.getFechaMovimiento()));
			pStatement.setInt(7, movimiento.getEstado().getIdEstado());
			pStatement.setString(8, movimiento.getConcepto());
			pStatement.setInt(9, movimiento.getId());
			
			if(pStatement.executeUpdate() > 0) {
				connection.commit();
				updateExitoso = true;
			}
		}catch(SQLException e) {
			throw e;
		}
		
		return updateExitoso;
	}

	//BORRAR
	public boolean borrar(int id) throws SQLException {
		PreparedStatement pStatement;
		Connection connection = Conexion.getConexion().getSQLConexion();
		boolean deleteExitoso = false;
		
		try {
			pStatement = connection.prepareStatement(delete);
			pStatement.setInt(1, id);
			
			if(pStatement.executeUpdate() > 0) {
				connection.commit();
				deleteExitoso = true;
			}
		}catch(SQLException e) {
			throw e;
		}
		
		return deleteExitoso;
		
	}
	
	//GET MOVIMIENTO
	private Movimiento getMovimiento(ResultSet rSet) throws SQLException {
		//MOVIMIENTO
		Movimiento movimiento = new Movimiento();
		movimiento.setId(rSet.getInt("ID"));
		movimiento.setNumeroReferencia(rSet.getInt("NumeroReferencia"));
		
		//REFERENTE A CUENTA
		int numeroCuenta=rSet.getInt("NumeroCuenta");
		String cbu= rSet.getString("CBU");
		double saldo=rSet.getDouble("Saldo");
		TipoCuenta tipoCuenta=new TipoCuenta();
		tipoCuenta.setId(rSet.getInt("IdTipoCuenta"));
		tipoCuenta.setDescripcion(rSet.getString("tipoCuentaDescripcion"));
		int idCliente=rSet.getInt("IdCliente");
		LocalDate fechaCreacion=rSet.getDate("fechaCreacion").toLocalDate();
		boolean activa=rSet.getBoolean("Activo");
		
		Cuenta cuenta=new Cuenta(numeroCuenta, cbu, saldo, tipoCuenta, idCliente, fechaCreacion, activa);
		//
		//movimiento.setCbudestino(rSet.getString("CBU"));
		movimiento.setCuenta(cuenta);
		
		
		movimiento.setMonto(rSet.getDouble("Monto"));
		movimiento.setOperacion(Operacion.valueOf(rSet.getString("Operacion")));
		movimiento.setFechaMovimiento(rSet.getDate("FechaMovimiento").toLocalDate());
		movimiento.setConcepto(rSet.getString("Concepto"));
		
		//ESTADO
		Estado estado = new Estado();
		estado.setIdEstado(rSet.getInt("IdEstados"));
		//estado.setDescripcion(rSet.getNString("estadoDescripcion"));
		estado.setDescripcion(rSet.getString("estadoDescripcion"));
		movimiento.setEstado(estado);
				
		//TIPOMOVIMIENTO
		TipoMovimiento tipoMovimiento = new TipoMovimiento();
		tipoMovimiento.setId(rSet.getInt("IdTipoMovimiento"));
		tipoMovimiento.setDescripcion(rSet.getString("tipoMovimientoDescripcion"));
		movimiento.setTipoMovimiento(tipoMovimiento);
		
		return movimiento;
	}
	
	/**** TIPO DE MOVIMIENTOS ****/
	
	//QUERIES
	private static final String selectOneTipoMovimiento = "SELECT * FROM TiposMovimiento WHERE IdTipoMovimiento = ?";
	private static final String selectAllTipoMovimientos = "SELECT * FROM TiposMovimiento";
	
	//TIPOS MOVIMIENTOS POR ID
	public TipoMovimiento obtenerTipoMovimientoPorId(int id) throws SQLException{
		PreparedStatement pStatement;
		ResultSet rSet;
		TipoMovimiento tipoMovimiento = new TipoMovimiento();
		Conexion conexion = Conexion.getConexion();
		
		try {
			pStatement=conexion.getSQLConexion().prepareStatement(selectOneTipoMovimiento);
			pStatement.setInt(1, id);
			rSet=pStatement.executeQuery();
			
			rSet.next();
				
			tipoMovimiento = getTipoMovimiento(rSet);
			
		} catch (SQLException e) {
			throw e;
		}
		
		return tipoMovimiento;
	}
	
	@Override
	public Movimiento obtenerUnoPorId(int id) throws SQLException {
		
		PreparedStatement pStatement;
		ResultSet rSet;
		Movimiento movimiento=new Movimiento();
		Conexion conexion = Conexion.getConexion();
		
		try {
			pStatement=conexion.getSQLConexion().prepareStatement(selectOne);
			pStatement.setInt(1, id);
			rSet=pStatement.executeQuery();
			
			while(rSet.next()) {
				
				movimiento= getMovimiento(rSet);
			}
			
		} catch (SQLException e) {
			throw e;
		}
		
		return movimiento;
		
		
	}
	
	// OBTENER LISTA DE TIPOS DE MOVIMIENTOS
	public List<TipoMovimiento> obtenerTipoMovimientos() throws SQLException {
		List<TipoMovimiento> tipoMovimientos = new ArrayList<TipoMovimiento>();
		PreparedStatement pStatement;
		ResultSet rSet;
		Conexion conexion = Conexion.getConexion();
		
		try {
			pStatement=conexion.getSQLConexion().prepareStatement(selectAllTipoMovimientos);
			rSet=pStatement.executeQuery();
			
			while(rSet.next()) {
				tipoMovimientos.add(getTipoMovimiento(rSet));
			}
			
		} catch (SQLException e) {
			throw e;
		}
		
		return tipoMovimientos;
		
	}
	
	//GET TIPOMOVIMIENTO
	private TipoMovimiento getTipoMovimiento(ResultSet rSet) throws SQLException {
		//MOVIMIENTO
		TipoMovimiento tipoMovimiento = new TipoMovimiento();
		tipoMovimiento.setId(rSet.getInt("IdTipoMovimiento"));
		tipoMovimiento.setDescripcion(rSet.getString("descripcion"));
						
		return tipoMovimiento;
	}

	@Override
	public int obtenerUltimoIdMovimiento() throws SQLException {
		PreparedStatement pStatement;
		ResultSet rSet;
		int ultimoId = 0;
		Conexion conexion= Conexion.getConexion();
		
		try {
			pStatement=conexion.getSQLConexion().prepareStatement(obtenerUltimoIdMovimiento);
			rSet=pStatement.executeQuery();
			
			while(rSet.next()) {
				ultimoId = rSet.getInt("MaxId");
			}
			
		} catch (Exception e) {
			throw e;
		}
		
		return ultimoId;
	}

	@Override
	public int totalTransferenciasAnio(String anio)throws SQLException{
		 
		 int totalTransferencias = 0;
		    
		    PreparedStatement pStatement;
		    ResultSet rSet;

		    Conexion conexion = Conexion.getConexion();

		    try {
		        pStatement = conexion.getSQLConexion().prepareStatement(TotalTransferenciasAnio);
		        pStatement.setString(1, anio);
		       

		        rSet = pStatement.executeQuery();

		        if (rSet.next()) {
		            totalTransferencias = rSet.getInt("TotalTransferencias");
		        }

		    } catch (Exception e) {
		        throw e;
		    }

		    return totalTransferencias;
		 
		 }
	@Override
	public int totalTransferenciasAnioMes(String anio,String mes)throws SQLException{
		 
		 int totalTransferencias = 0;
		    
		    PreparedStatement pStatement;
		    ResultSet rSet;

		    Conexion conexion = Conexion.getConexion();

		    try {
		        pStatement = conexion.getSQLConexion().prepareStatement(TotalTransferenciasAnioMes);
		        pStatement.setString(1, anio);
		        pStatement.setString(2, mes);

		        rSet = pStatement.executeQuery();

		        if (rSet.next()) {
		            totalTransferencias = rSet.getInt("TotalTransferencias");
		        }

		    } catch (Exception e) {
		        throw e;
		    }

		    return totalTransferencias;
		 
		 }
	@Override
	public double MontoTransferenciaAnio(String anio)throws SQLException{
		 
		 double montoTransferencias = 0;
		    
		    PreparedStatement pStatement;
		    ResultSet rSet;

		    Conexion conexion = Conexion.getConexion();

		    try {
		        pStatement = conexion.getSQLConexion().prepareStatement(montoTransferenciasAnio);
		        pStatement.setString(1, anio);
		       

		        rSet = pStatement.executeQuery();

		        if (rSet.next()) {
		            montoTransferencias = rSet.getDouble("TotalMontoTransferencias");
		        }

		    } catch (Exception e) {
		        throw e;
		    }

		    return montoTransferencias;
		 
		 }
	@Override
	public double MontoTransferenciaAnioMes(String anio,String mes)throws SQLException{
		 
		 double montoTransferencias = 0;
		    
		    PreparedStatement pStatement;
		    ResultSet rSet;

		    Conexion conexion = Conexion.getConexion();

		    try {
		        pStatement = conexion.getSQLConexion().prepareStatement(montoTransferenciasAnioMes);
		        pStatement.setString(1, anio);
		        pStatement.setString(2, mes);

		        rSet = pStatement.executeQuery();

		        if (rSet.next()) {
		            montoTransferencias = rSet.getDouble("TotalMontoTransferencias");
		        }

		    } catch (Exception e) {
		        throw e;
		    }

		    return montoTransferencias;
		 
		 }
	
	private static final String selectUltimosMovimientos = 
	 "SELECT *,TC.descripcion as tipoCuentaDescripcion, TM.descripcion as tipoMovimientoDescripcion, E.descripcion as estadoDescripcion from Movimientos M " +
	 "inner join Estados E " + 
	 "on M.IdEstados=E.IdEstados " + 
	 "inner join TiposMovimiento TM " + 
	 "on TM.IdTipoMovimiento=M.IdTipoMovimiento " + 
	 "inner join Cuentas C " + 
	 "on C.CBU=M.CBU "+  
	 "inner join TiposCuenta TC " +
	 "on TC.IdTipoCuenta=C.IdTipoCuenta WHERE M.CBU = ? ORDER BY M.FechaMovimiento DESC, id DESC LIMIT 3";
	@Override
		public List<Movimiento> obtenerUltimosTresMovimientos(String CBU) throws SQLException {
		    PreparedStatement pStatement;
		    ResultSet rSet;
		    List<Movimiento> tresUltimosMovimientos = new ArrayList<>();
		    Conexion conexion = Conexion.getConexion();
		    
		    try {
		        pStatement = conexion.getSQLConexion().prepareStatement(selectUltimosMovimientos);
		        pStatement.setString(1, CBU);
		        rSet = pStatement.executeQuery();
		        
		        while (rSet.next()) {
		            tresUltimosMovimientos.add(getMovimiento(rSet));
		        }
		    } catch (SQLException e) {
		        throw e;
		    }
		    
		    return tresUltimosMovimientos;
		}


}
