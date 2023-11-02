package dao;

import java.sql.SQLException;
import java.util.List;

import entidad.Movimiento;
import entidad.TipoMovimiento;

public interface IMovimientosDao {
	public List<Movimiento> obtenerTodos() throws SQLException;
	public List<Movimiento> obtenerPorNumeroDeReferencia(int numeroReferencia) throws SQLException;
	public List<Movimiento> obtenerPorCBU(String CBU) throws SQLException;
	public boolean insertar(Movimiento movimiento) throws SQLException;
	public boolean editar(Movimiento movimiento) throws SQLException;
	public boolean borrar(int id) throws SQLException;
	TipoMovimiento obtenerTipoMovimientoPorId(int id) throws SQLException;
	public List<TipoMovimiento> obtenerTipoMovimientos() throws SQLException;
}