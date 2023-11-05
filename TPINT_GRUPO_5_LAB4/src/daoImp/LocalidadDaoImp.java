package daoImp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import dao.Conexion;
import dao.ILocalidadDao;
import entidad.Localidad;

public class LocalidadDaoImp implements ILocalidadDao{
	
	private static final String leerTodas= "Select * from Localidades";
	private static final String leerUna="Select * from Localidades where IDLocalidad = ?";
    private static final String leerPorProvincia = "Select * from Localidades where IDProvincia = ?";
	
	@Override
	public List<Localidad> obtenerTodas() throws SQLException{
		PreparedStatement pStatement;
		ResultSet rSet;
		List<Localidad> localidades= new ArrayList<Localidad>();
		Conexion conexion= Conexion.getConexion();
		try {
			pStatement=conexion.getSQLConexion().prepareStatement(leerTodas);
			rSet=pStatement.executeQuery();
			
			while (rSet.next()) {
				localidades.add(getLocalidad(rSet));
			}
			
		} catch (Exception e) {
			throw e;
		}
		return localidades;
	}
	
	/*****GET LOCALIDADES******/
	private Localidad getLocalidad(ResultSet rSet) throws SQLException{
	
		int idLocalidad=rSet.getInt("IDLocalidad");
		String nombreLocalidad=rSet.getString("NombreLocalidad");
		int idProvincia=rSet.getInt("IDProvincia");
		
		return new Localidad(idLocalidad, nombreLocalidad, idProvincia);
		
	}
	
	@Override
	public Localidad obtenerUna(int id) throws SQLException {
		PreparedStatement pStatement;
		ResultSet rSet;
		Localidad localidad= new Localidad();
		Conexion conexion= Conexion.getConexion();
		try {
			pStatement=conexion.getSQLConexion().prepareStatement(leerUna);
			pStatement.setInt(1, id);
			rSet=pStatement.executeQuery();
			
			while (rSet.next()) {
				localidad=getLocalidad(rSet);
			}
			
		} catch (Exception e) {
			throw e;
		}
		
		return localidad;
	}

	@Override
	public List<Localidad> localidadesPorProvincia(int idProvincia) throws SQLException {
		 PreparedStatement pStatement;
	        ResultSet rSet;
	        List<Localidad> localidades = new ArrayList<Localidad>();
	        Conexion conexion = Conexion.getConexion();
	        try {
	            pStatement = conexion.getSQLConexion().prepareStatement(leerPorProvincia);
	            pStatement.setInt(1, idProvincia);
	            rSet = pStatement.executeQuery();

	            while (rSet.next()) {
	                localidades.add(getLocalidad(rSet));
	            }

	        } catch (Exception e) {
	            throw e;
	        }
	        return localidades;
	}

}
