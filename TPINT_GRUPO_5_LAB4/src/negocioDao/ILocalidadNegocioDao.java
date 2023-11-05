package negocioDao;

import java.sql.SQLException;
import java.util.List;

import entidad.Localidad;

public interface ILocalidadNegocioDao {
	public List<Localidad> obtenerTodas()throws SQLException;
	public Localidad obtenerUna(int id)throws SQLException;
	public List<Localidad> localidadesPorProvincia(int idProvincia) throws SQLException;

}
