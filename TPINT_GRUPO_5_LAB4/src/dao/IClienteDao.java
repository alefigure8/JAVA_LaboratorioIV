package dao;

import java.util.ArrayList;
import java.util.List;

import entidad.Cliente;


public interface IClienteDao {
	 public boolean insertar(Cliente cliente);
	 public boolean editar(Cliente cliente );
	 public boolean borrar(int id);
	 public List<Cliente> obtenerTodos();
	 public Cliente obtenerUno(int id);
}
