package daoImp;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.Conexion;
import dao.IMovimientosDao;
import entidad.Cuenta;
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
			
	private static final String selectPorNumeroCuenta = "select *,TC.descripcion as tipoCuentaDescripcion, TM.descripcion as tipoMovimientoDescripcion, E.descripcion as estadoDescripcion from Movimientos M " +
															"inner join Estados E " +
															"on M.IdEstados=E.IdEstados " + 
															"inner join TiposMovimiento TM " +
															"on TM.IdTipoMovimiento=M.IdTipoMovimiento " +
															"inner join Cuentas C " +
															"on C.CBU=M.CBU " +
															"inner join TiposCuenta TC " +
															"on TC.IdTipoCuenta=C.IdTipoCuenta "+
															"where CBU = ?";
	
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
	
	private static final String insert = "INSERT INTO Movimientos (idTipoMovimiento, NumeroReferencia, CBU, " +
            "Monto, Operacion, FechaMovimiento, IdEstados, Concepto) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final String update = "UPDATE Movimientos " +
								            "SET idTipoMovimiento = ?, NumeroReferencia = ?, CBU = ?, Monto = ?, " +
								            "Operacion = ?, FechaMovimiento = ?, idEstado = ?, Concepto = ? " +
								            "WHERE ID = ?";
	
	private static final String delete = "DELETE FROM movimientos WHERE ID = ?";
	
	private static final String obtenerUltimoIdMovimiento="select MAX(ID) as MaxId from movimientos";
	
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
	
	//OBTENER POR NUMERO DE REFERENCIA
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
		estado.setDescripcion(rSet.getNString("estadoDescripcion"));
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

}
