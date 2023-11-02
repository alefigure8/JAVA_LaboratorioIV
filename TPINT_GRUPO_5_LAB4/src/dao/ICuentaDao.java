package dao;

import java.sql.SQLException;
import java.util.List;

import entidad.Cuenta;

public interface ICuentaDao {
	 public boolean insertar(Cuenta cuenta) throws SQLException;
	 public boolean editar(Cuenta cuenta) throws SQLException;
	 public boolean borrar(int nroCuenta) throws SQLException; // baja logica
	 public List<Cuenta> obtenerTodas() throws SQLException;
	 public List<Cuenta> obtenerCuentasCliente(int idCliente) throws SQLException;
	 public Cuenta obtenerUna(int nroCuenta) throws SQLException;
	 public int cantidadCuentas(int idCliente) throws SQLException; // devuelve la cantidad de cuentas existentes del cliente
}
