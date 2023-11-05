package negocioDao;

import java.sql.SQLException;
import java.util.List;

import entidad.Cuenta;
import entidad.TipoCuenta;

public interface ICuentaNegocioDao {
	 public boolean insertar(Cuenta cuenta) throws SQLException;
	 public boolean editar(Cuenta cuenta) throws SQLException;
	 public boolean borrar(int nroCuenta) throws SQLException; // baja logica
	 public List<Cuenta> obtenerTodas() throws SQLException;
	 public List<Cuenta> obtenerCuentasCliente(int idCliente) throws SQLException;
	 public Cuenta obtenerUna(int nroCuenta) throws SQLException;
	 public int cantidadCuentas(int idCliente) throws SQLException; // devuelve la cantidad de cuentas existentes del cliente
	 public List<TipoCuenta> listarTiposCuenta() throws SQLException;
	 public boolean cbuExiste(String cbu) throws SQLException;
	 public int obtenerUltimaInsertada(int idCliente)throws SQLException;
	 public String obtenerDescripcion(int id)throws SQLException;
}
