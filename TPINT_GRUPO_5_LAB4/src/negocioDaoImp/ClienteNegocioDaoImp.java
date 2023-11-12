package negocioDaoImp;

import java.sql.SQLException;
import java.util.List;

import dao.IClienteDao;
import daoImp.ClienteDaoImp;
import entidad.Cliente;
import entidad.Usuario;
import negocioDao.IClienteNegocioDao;

public class ClienteNegocioDaoImp implements IClienteNegocioDao {
	
	private IClienteDao clienteDao= new ClienteDaoImp();
	
	public ClienteNegocioDaoImp() {
		
	}
	
	public ClienteNegocioDaoImp(IClienteDao clienteDao) {
		this.clienteDao=clienteDao;
	}
	
	
	@Override
	public boolean insertar(Cliente cliente) {
		return clienteDao.insertar(cliente);
	}

	@Override
	public boolean editar(Cliente cliente) {
		return clienteDao.editar(cliente);
	}

	@Override
	public boolean borrar(int id) {
		return clienteDao.borrar(id);
	}

	@Override
	public List<Cliente> obtenerTodos() {
		return (List<Cliente>)clienteDao.obtenerTodos();
	}

	@Override
	public Cliente obtenerUno(int id) {
		return clienteDao.obtenerUno(id);
	}

	
	@Override
	public Cliente obtenerCliente(int dni) {
		return clienteDao.obtenerCliente(dni);
	}
	
	@Override
	public Cliente obtenerClientePorCBU(int cbu) {
		return clienteDao.obtenerClientePorCBU(cbu);
	}
	
	//CLIENTE DNI
	public boolean existeDni(int dni) {
		List<Cliente> clientes=(List<Cliente>)clienteDao.obtenerTodos();
		boolean existe=false;
		for(Cliente cliente : clientes) {
			if(cliente.getDni()==dni) {
				existe=true;
			}
		}
		return existe;
	}

	/***************** USUARIO EXISTE ********************/
	public boolean existeUsuario(String usuario, String contrasena) throws SQLException {

		boolean existe = false;
		
		if(!usuario.trim().isEmpty() & !contrasena.trim().isEmpty()) {
		
			try {
				existe = clienteDao.existeUsuario(usuario, contrasena);
			} catch (Exception e) {
				throw e;
			}
			
		}
		
		return existe;
	}
	
	/***************** OBTENER USUARIO POR USUARIO ********************/
	public Usuario obtenerUsuarioPorUsuario(String usuario) throws SQLException{
		
		if(!usuario.trim().isEmpty()) {			
			try {
				return clienteDao.obtenerUsuarioPorUsuario(usuario);
			} catch (Exception e) {
				throw e;
			}	
		}
		
		return null;
	}


}
