package negocioDaoImp;

import java.sql.SQLException;
import java.util.List;



import dao.ILocalidadDao;
import daoImp.LocalidadDaoImp;
import entidad.Localidad;
import negocioDao.ILocalidadNegocioDao;

public class LocalidadNegocioDaoImp implements ILocalidadNegocioDao{

	private ILocalidadDao localidadDao=new LocalidadDaoImp();
	
	@Override
	public List<Localidad> obtenerTodas() throws SQLException {
		return localidadDao.obtenerTodas();
	}

	@Override
	public Localidad obtenerUna(int id) throws SQLException {
		return localidadDao.obtenerUna(id);
	}

	@Override
	public List<Localidad> localidadesPorProvincia(int idProvincia) throws SQLException {
		return localidadDao.localidadesPorProvincia(idProvincia);
	}

}
