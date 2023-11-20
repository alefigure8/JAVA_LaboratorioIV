package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidad.Cliente;
import entidad.Usuario;
import excepciones.CorreoException;


public interface IClienteDao {
	 public boolean insertar(Cliente cliente);
	 public boolean editar(Cliente cliente );
	 public boolean borrar(int id);
	 public boolean activar(int idCliente);
	 public List<Cliente> obtenerTodos();
	 public Cliente obtenerUno(int id);
	 public Cliente obtenerCliente(int dni);
	 public Cliente obtenerClientePorCBU(String cbu);
	 public boolean existeUsuario(String usuario, String contrasena) throws SQLException;
	 public Usuario obtenerUsuarioPorUsuario(String usuario) throws SQLException;
	 public boolean existeCorreo(String correo) throws CorreoException, SQLException;
	 public int clientesPorFecha(String anio, String mes) throws SQLException;
	 public int clientesPorAnio(String anio) throws SQLException;
	 public Boolean existeSoloUsuario(String usuario) throws SQLException;
}
