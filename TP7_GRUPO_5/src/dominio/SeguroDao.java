package dominio;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SeguroDao {
	
	private static final String ultimoId = "select idSeguro from seguros order by idSeguro desc limit 1";
	private static final String insert = "insert into seguros(descripcion, idTipo, CostoContratacion, costoAsegurado)"
			+ "values (?, ?, ?, ?)";
	private static final String selectAll = "select * from seguros";
	private static final String selectTipoSeguro = "select * from seguros where idTipo = ?";
	
	// OBTENER TODOS LOS SEGUROS
	public ArrayList<Seguro> obtenerTodosLosSeguros() {
		ArrayList<Seguro> listaSeguros = new ArrayList<Seguro>();
	    PreparedStatement pStatement;
	    ResultSet rSet;
	    Conexion conexion = Conexion.getConexion();

	    try {
	        pStatement = conexion.getSQLConexion().prepareStatement(selectAll);
	        rSet = pStatement.executeQuery();

	        while (rSet.next()) {
	            Seguro seguro = new Seguro();
	            seguro.setIdSeguro(rSet.getInt("idSeguro"));
	            seguro.setDescripcion(rSet.getString("descripcion"));
	            seguro.setIdTipo(rSet.getInt("idTipo"));
	            seguro.setCostoContratacion(rSet.getDouble("CostoContratacion"));
	            seguro.setCostoAsegurado(rSet.getDouble("costoAsegurado"));
	            listaSeguros.add(seguro);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return listaSeguros;
	}

	
	
	// INSERT 
	public boolean insert(Seguro seguro) {
		PreparedStatement pStatement;
		Connection connection = Conexion.getConexion().getSQLConexion();
		boolean insertExitoso = false;
		try {
			pStatement = connection.prepareStatement(insert);
			pStatement.setString(1, seguro.getDescripcion());
			pStatement.setInt(2, seguro.getIdTipo());
			pStatement.setDouble(3, seguro.getCostoContratacion());
			pStatement.setDouble(4, seguro.getCostoAsegurado());
			if(pStatement.executeUpdate()>0) {
				connection.commit();
				insertExitoso = true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			}catch(SQLException e2) {
				e2.printStackTrace();
			}
		}
		return insertExitoso;
	}
	
	//ULTIMO ID
	public int ultimoID() {
		PreparedStatement pStatement;
		ResultSet rSet;
		Conexion conexion= Conexion.getConexion();
		int ultimo = 0;
		try {
			pStatement=conexion.getSQLConexion().prepareStatement(ultimoId);
			rSet=pStatement.executeQuery();
			if(rSet.next())
			{
				ultimo = rSet.getInt("idSeguro");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ultimo;
	}



	public java.util.List<Seguro> obtenerSegurosPorTipo(int tipoSeguroId) {
		
		ArrayList<Seguro> listaSeguros = new ArrayList<Seguro>();
	    PreparedStatement pStatement;
	    ResultSet rSet;
	    Conexion conexion = Conexion.getConexion();

	    try {
	        pStatement = conexion.getSQLConexion().prepareStatement(selectTipoSeguro);
			pStatement.setInt(1, tipoSeguroId);
			rSet = pStatement.executeQuery();

	        while (rSet.next()) {
	            Seguro seguro = new Seguro();
	            seguro.setIdSeguro(rSet.getInt("idSeguro"));
	            seguro.setDescripcion(rSet.getString("descripcion"));
	            seguro.setIdTipo(rSet.getInt("idTipo"));
	            seguro.setCostoContratacion(rSet.getDouble("CostoContratacion"));
	            seguro.setCostoAsegurado(rSet.getDouble("costoAsegurado"));
	            listaSeguros.add(seguro);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return listaSeguros;	
		

	}
	

}
