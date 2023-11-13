package negocioDaoImp;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import dao.ICuentaDao;
import daoImp.CuentaDaoImp;
import entidad.Cuenta;
import entidad.TipoCuenta;
import negocioDao.ICuentaNegocioDao;

public class CuentaNegocioDaoImp implements ICuentaNegocioDao {
	
	private ICuentaDao cuentaDao= new CuentaDaoImp();
	
	public CuentaNegocioDaoImp() {
		// TODO Auto-generated constructor stub
	}
	
	public CuentaNegocioDaoImp(ICuentaDao cuentaDao) {
		this.cuentaDao=cuentaDao;
	}	
	
	@Override
	public boolean insertar(Cuenta cuenta) throws SQLException {
		
		return  cuentaDao.insertar(cuenta);
	}

	@Override
	public boolean editar(Cuenta cuenta) throws SQLException {
		
		return cuentaDao.editar(cuenta);
	}

	@Override
	public boolean borrar(int nroCuenta) throws SQLException {
		
		return cuentaDao.borrar(nroCuenta);
	}

	@Override
	public List<Cuenta> obtenerTodas() throws SQLException {
		
		return (List<Cuenta>)cuentaDao.obtenerTodas();
	}

	@Override
	public List<Cuenta> obtenerCuentasCliente(int idCliente) throws SQLException {
		
		return (List<Cuenta>)cuentaDao.obtenerCuentasCliente(idCliente);
	}

	@Override
	public Cuenta obtenerUna(int nroCuenta) throws SQLException {
		
		return cuentaDao.obtenerUna(nroCuenta);
	}
	
	@Override
	public Cuenta obtenerUnaPorCBU(String cbu) throws SQLException {
		
		return cuentaDao.obtenerUnaPorCBU(cbu);
	}

	@Override
	public int cantidadCuentas(int idCliente) throws SQLException {
		
		return cuentaDao.cantidadCuentas(idCliente);
	}

	@Override
	public List<TipoCuenta> listarTiposCuenta() throws SQLException {
		
		return (List<TipoCuenta>)cuentaDao.listarTiposCuenta();
	}
	
	
	
	//VERIFICAR QUE CBU NO EXISTA
	@Override
	public boolean cbuExiste(String cbu) throws SQLException {
		return cuentaDao.cbuExiste(cbu);
	}
	
	//GENERAR CBU AL AZAR
		public String generarCbu() {
			Random random=new Random();
			StringBuilder cbu= new StringBuilder();
			int numero;
			for(int x=0;x<22;x++) {
				numero=random.nextInt(10);
				cbu.append(numero);
			}
			
			return cbu.toString();
		}
		
	//GENERAR CBU UNICO
	public String generarCbuUnico() throws SQLException {
		String cbu;
		do {
			cbu=generarCbu();
		}
		while(cbuExiste(cbu));
		return cbu;
	}

	@Override
	public int obtenerUltimaInsertada(int idCliente) throws SQLException {
		
		return cuentaDao.obtenerUltimaInsertada(idCliente);
	}

	@Override
	public String obtenerDescripcion(int id) throws SQLException {
		
		return cuentaDao.obtenerDescripcion(id);
	}
	

}
