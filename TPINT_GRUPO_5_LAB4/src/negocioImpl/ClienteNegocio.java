package negocioImpl;

import java.sql.SQLException;

import daoImp.ClienteDaoImp;
import entidad.Usuario;

public class ClienteNegocio {
	
	ClienteDaoImp clienteDaoImp;
	
	public ClienteNegocio() {
		clienteDaoImp = new ClienteDaoImp();
	}
		
	/***************** USUARIO EXISTE ********************/
	public boolean existeUsuario(String usuario, String contrasena) throws SQLException {

		boolean existe = false;
		
		if(!usuario.trim().isEmpty() & !contrasena.trim().isEmpty()) {
		
			try {
				existe = clienteDaoImp.existeUsuario(usuario, contrasena);
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
				return clienteDaoImp.obtenerUsuarioPorUsuario(usuario);
			} catch (Exception e) {
				throw e;
			}	
		}
		
		return null;
	}
}
