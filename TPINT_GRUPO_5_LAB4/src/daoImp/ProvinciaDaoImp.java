package daoImp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.Conexion;
import dao.IProvinciaDao;
import entidad.Provincia;

public class ProvinciaDaoImp implements IProvinciaDao{
	private static final String leerTodas= "Select * from Provincias";
	private static final String leerUna= "Select * from Provincias where IDProvincia = ?";

	@Override
	public List<Provincia> obtenerTodas() throws SQLException {
		PreparedStatement pStatement;
		ResultSet rSet;
		List<Provincia> provincias= new ArrayList<Provincia>();
		Conexion conexion= Conexion.getConexion();
		try {
			pStatement=conexion.getSQLConexion().prepareStatement(leerTodas);
			rSet=pStatement.executeQuery();
			
			while (rSet.next()) {
				provincias.add(getProvincia(rSet));
			}
			
		} catch (Exception e) {
			throw e;
		}
		return provincias;
	}

	@Override
	public Provincia obtenerUna(int id) throws SQLException {
		PreparedStatement pStatement;
		ResultSet rSet;
		Provincia provincia= new Provincia();
		Conexion conexion= Conexion.getConexion();
		try {
			pStatement=conexion.getSQLConexion().prepareStatement(leerUna);
			pStatement.setInt(1, id);
			rSet=pStatement.executeQuery();
			
			while (rSet.next()) {
				provincia=getProvincia(rSet);
			}
			
		} catch (Exception e) {
			throw e;
		}
		
		return provincia;
	}
	
	
	/*****GET PROVINCIAS******/
	private Provincia getProvincia(ResultSet rSet) throws SQLException{
		int idProvincia=rSet.getInt("IDProvincia");
		String nombreProvincia=rSet.getString("NombreProvincia");
		return new Provincia(idProvincia, nombreProvincia);
		
	}
	

	

}
