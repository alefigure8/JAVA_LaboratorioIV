package negocioDao;

import java.sql.SQLException;
import java.util.List;

import entidad.Cliente;
import entidad.Usuario;
import excepciones.CorreoException;
import excepciones.UsuarioNoEncontradoException;

public interface IClienteNegocioDao {
	public boolean insertar(Cliente cliente);
	 public boolean editar(Cliente cliente );
	 public boolean borrar(int id);
	 public List<Cliente> obtenerTodos();
	 public Cliente obtenerUno(int id);
	 public Cliente obtenerCliente(int dni);
	 public Cliente obtenerClientePorCBU(String cbu);
	 public Usuario obtenerUsuarioPorUsuario(String usuario) throws SQLException, UsuarioNoEncontradoException;
	 public boolean existeUsuario(String usuario, String contrasena) throws SQLException;
	 public boolean existeCorreo(String correo) throws CorreoException;
}
