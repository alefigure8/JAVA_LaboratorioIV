package dao;

import java.sql.SQLException;
import java.util.List;

import com.sun.xml.internal.bind.v2.model.core.ID;

import entidad.Cuenta;
import entidad.TipoCuenta;

public interface ICuentaDao {
	 public List<Cuenta> obtenerActivas() throws SQLException;
	 public boolean insertar(Cuenta cuenta) throws SQLException;
	 public boolean editar(Cuenta cuenta) throws SQLException;
	 public boolean borrar(int nroCuenta) throws SQLException; // baja logica
	 public List<Cuenta> obtenerTodas() throws SQLException;

	 public List<Cuenta> obtenerCuentasCliente(int idCliente) throws SQLException;
	 public List<Cuenta> obtenerCuentasActivasCliente(int idCliente) throws SQLException;
	 public Cuenta obtenerUna(int nroCuenta) throws SQLException;
	 public Cuenta obtenerUnaPorCBU(String cbu) throws SQLException;
	 public int cantidadCuentas(int idCliente) throws SQLException; // devuelve la cantidad de cuentas existentes del cliente
	 public List<TipoCuenta> listarTiposCuenta() throws SQLException;
	 public boolean cbuExiste(String cbu) throws SQLException;
	 public int obtenerUltimaInsertada(int idCliente)throws SQLException;
	 public String obtenerDescripcion(int id)throws SQLException;
	 public Cuenta obtenerPorMovimientoYreferencia(int tipoMovimiento, int numeroReferencia);
	 public int obtenerTotalSaldoCajaAhorro() throws SQLException;;
	 public int obtenerTotalSaldoCuentaCorriente() throws SQLException;;
	 public int obtenerTotalSaldoCuentas() throws SQLException;
	 public int obtenerTotalCuentasPorAnio(String anio) throws SQLException;
	 public int obtenerTotalCuentasPorAnioYMes(String anio, String mes) throws SQLException;
	 public int obtenerTotalCuentasPorAnioCaja(String anio) throws SQLException;
	 public int obtenerTotalCuentasPorAnioYMesCaja(String anio, String mes) throws SQLException;
	 public int obtenerTotalCuentasPorAnioCorriente(String anio) throws SQLException;
	 public int obtenerTotalCuentasPorAnioYMesCorriente(String anio, String mes) throws SQLException;
	 
}
