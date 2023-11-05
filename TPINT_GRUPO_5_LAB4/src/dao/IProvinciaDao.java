package dao;

import java.sql.SQLException;
import java.util.List;

import entidad.Provincia;

public interface IProvinciaDao {
	public List<Provincia> obtenerTodas()throws SQLException;
	public Provincia obtenerUna(int id)throws SQLException;
}
