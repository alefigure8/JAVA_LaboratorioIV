package negocioDaoImp;

import java.sql.SQLException;
import java.util.List;

import dao.IClienteDao;
import daoImp.ClienteDaoImp;
import entidad.Cliente;
import entidad.Usuario;
import excepciones.CorreoException;
import excepciones.ErrorInternoException;
import excepciones.UsuarioIncorrectoException;
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
	public boolean activar(int id) {
		return clienteDao.activar(id);
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
	public Cliente obtenerClientePorCBU(String cbu) {
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
	public boolean existeUsuario(String usuario, String contrasena) throws ErrorInternoException, UsuarioIncorrectoException {

		boolean existe = false;
		
		if(!usuario.trim().isEmpty() & !contrasena.trim().isEmpty()) {
			try {
				
				existe = clienteDao.existeUsuario(usuario, contrasena);
				
				if(!existe) {
					throw new UsuarioIncorrectoException();
				}
				
			} catch ( SQLException e) {
				throw new ErrorInternoException();
			}
			
		}
		
		return existe;
	}
	
	/***************** OBTENER USUARIO POR USUARIO ********************/
	public Usuario obtenerUsuarioPorUsuario(String usuario) throws ErrorInternoException, UsuarioIncorrectoException{
		
		if(!usuario.trim().isEmpty()) {			
			try {
				Usuario usuarioEncontrado = clienteDao.obtenerUsuarioPorUsuario(usuario);
				
				if(usuarioEncontrado == null) {
					throw new UsuarioIncorrectoException();
				}
				
				return clienteDao.obtenerUsuarioPorUsuario(usuario);
			} catch (Exception e) {
				throw new ErrorInternoException();
			}	
		}
		
		return null;
	}
	
	@Override
	public boolean existeCorreo(String correo)throws CorreoException, ErrorInternoException{
		boolean existe = false;

	    if (!correo.trim().isEmpty()) {

	    	try {
	    	    existe = clienteDao.existeCorreo(correo);
	    	   
	    	} catch (CorreoException e) {
	    	    throw new CorreoException(e.getMessage());
	    	} catch (Exception e) {
	    		throw new ErrorInternoException();
	    	}
	    }

	    return existe;
	}

	
	@Override
	public int clientesPorFecha(String anio, String mes) throws SQLException{
	    return clienteDao.clientesPorFecha(anio, mes);	
	}
	
	@Override
	 public int clientesPorAnio(String anio) throws SQLException{
		    return clienteDao.clientesPorAnio(anio);
	}

	@Override
	public Boolean existeSoloUsuario(String usuario) {
		boolean existe=false;
		try {
			existe= clienteDao.existeSoloUsuario(usuario);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return existe;
	}

}
