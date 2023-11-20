package negocioDao;

import java.sql.SQLException;
import java.util.List;

import entidad.Cliente;
import entidad.Usuario;
import excepciones.CorreoException;
import excepciones.ErrorInternoException;
import excepciones.UsuarioIncorrectoException;

public interface IClienteNegocioDao {
	public boolean insertar(Cliente cliente);
	 public boolean editar(Cliente cliente );
	 public boolean borrar(int id);
	 public boolean activar(int id);
	 public List<Cliente> obtenerTodos();
	 public Cliente obtenerUno(int id);
	 public Cliente obtenerCliente(int dni);
	 public Cliente obtenerClientePorCBU(String cbu);
	 public Usuario obtenerUsuarioPorUsuario(String usuario) throws ErrorInternoException, UsuarioIncorrectoException;
	 public boolean existeUsuario(String usuario, String contrasena) throws ErrorInternoException, UsuarioIncorrectoException;
	 public boolean existeCorreo(String correo) throws CorreoException, ErrorInternoException;
	 public int clientesPorFecha(String anio, String mes) throws SQLException;
	 public int clientesPorAnio(String anio) throws SQLException;
	 public Boolean existeSoloUsuario(String usuario) ;
}
