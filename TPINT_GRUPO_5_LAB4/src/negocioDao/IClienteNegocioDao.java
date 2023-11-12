package negocioDao;

import java.sql.SQLException;
import java.util.List;

import entidad.Cliente;
import entidad.Usuario;

public interface IClienteNegocioDao {
	public boolean insertar(Cliente cliente);
	 public boolean editar(Cliente cliente );
	 public boolean borrar(int id);
	 public List<Cliente> obtenerTodos();
	 public Cliente obtenerUno(int id);
	 public Cliente obtenerCliente(int dni);
	 public Cliente obtenerClientePorCBU(int cbu);
	 public Usuario obtenerUsuarioPorUsuario(String usuario) throws SQLException;
	 public boolean existeUsuario(String usuario, String contrasena) throws SQLException;
}
