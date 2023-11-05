package negocioDaoImp;

import java.sql.SQLException;
import java.util.List;

import dao.IProvinciaDao;
import daoImp.ProvinciaDaoImp;
import entidad.Provincia;
import negocioDao.IProvinciaNegocioDao;

public class ProvinciaNegocioDaoImp implements IProvinciaNegocioDao {
	
	IProvinciaDao provinciaDao= new ProvinciaDaoImp();
	
	@Override
	public List<Provincia> obtenerTodas() throws SQLException {
		return provinciaDao.obtenerTodas();
	}

	@Override
	public Provincia obtenerUna(int id) throws SQLException {
		return provinciaDao.obtenerUna(id);
	}

}
