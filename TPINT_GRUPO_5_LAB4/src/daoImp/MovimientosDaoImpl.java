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
import entidad.Estado;
import entidad.Movimiento;
import entidad.TipoMovimiento;

public class MovimientosDaoImpl implements IMovimientosDao{
	
	//QUERIES
	private static final String selectAll = "SELECT * FROM movimientos";
	private static final String selectOne = "SELECT * FROM movimientos WHERE ID = ?";
	private static final String selectPorNumeroReferencia = "SELECT * FROM movimientos WHERE NumeroReferencia = ?";
	private static final String selectPorNumeroCuenta= "SELECT * FROM movimientos WHERE CBU = ?";
	private static final String insert = "INSERT INTO Movimientos (ID, idTipoMovimiento, NumeroReferencia, CBU, " +
            "Monto, Operacion, FechaMovimiento, idEstado, Concepto) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"; //NUMERO REFERENCIA GENERAR DESDE NEGOCIO
	private static final String update = "UPDATE Movimientos " +
            "SET idTipoMovimiento = ?, NumeroReferencia = ?, CBU = ?, Monto = ?, " +
            "Operacion = ?, FechaMovimiento = ?, idEstado = ?, Concepto = ? " +
            "WHERE ID = ?";
	private static final String delete = "DELETE FROM movimientos WHERE ID = ?";
	private static final String selectOneTipoMovimiento = "SELECT * FROM tipoMovimiento WHERE idTipoMovimiento = ?";

	//OBTENER TODOS
	/*public List<Movimiento> obtenerTodos() throws SQLException {
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
	}*/

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
	
	public List<Movimiento> obtenerPorCBU(int CBU) throws SQLException {
		PreparedStatement pStatement;
		ResultSet rSet;
		ArrayList<Movimiento> movimientos=new ArrayList<Movimiento>();
		Conexion conexion = Conexion.getConexion();
		
		try {
			pStatement=conexion.getSQLConexion().prepareStatement(selectPorNumeroCuenta);
			pStatement.setInt(1, CBU);
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
			pStatement.setString(3, movimiento.getCbudestino());
			pStatement.setDouble(4, movimiento.getMonto());
			pStatement.setString(5, movimiento.getOperacion());
			pStatement.setDate(6, Date.valueOf(movimiento.getFechaMovimiento()));
			pStatement.setInt(7, movimiento.getEstado().getIdEstado());
			pStatement.setString(8, movimiento.getConcepto());
			pStatement.setInt(9, movimiento.getId());
			
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
			pStatement.setString(3, movimiento.getCbudestino());
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
			movimiento.setCbudestino(rSet.getString("CBU"));
			movimiento.setMonto(rSet.getDouble("Monto"));
			movimiento.setOperacion(rSet.getString("Operacion"));
			movimiento.setFechaMovimiento(rSet.getDate("FechaMovimiento").toLocalDate());
			movimiento.setConcepto(rSet.getString("Concepto"));
			
			//ESTADO
			Estado estado = new Estado();
			
			movimiento.setEstado(estado);
			
			//TIPOMOVIMIENTO
			TipoMovimiento tipoMovimiento = new TipoMovimiento();
			tipoMovimiento = ObtenerTipoMovimientoPorId(movimiento.getId());
			movimiento.setTipoMovimiento(tipoMovimiento);
			
			return movimiento;
	}
	
	//TIPOS MOVIMIENTOS POR ID
	public TipoMovimiento ObtenerTipoMovimientoPorId(int id) throws SQLException{
		PreparedStatement pStatement;
		ResultSet rSet;
		TipoMovimiento tipoMovimiento = new TipoMovimiento();
		Conexion conexion = Conexion.getConexion();
		
		try {
			pStatement=conexion.getSQLConexion().prepareStatement(selectOneTipoMovimiento);
			pStatement.setInt(1, id);
			rSet=pStatement.executeQuery();
			
			rSet.next();
				
			tipoMovimiento.setId(rSet.getInt("IdTipoMovimiento"));
			tipoMovimiento.setDescripcion(rSet.getString("descripcion"));
			

		} catch (SQLException e) {
			throw e;
		}
		
		return tipoMovimiento;
	}

}
