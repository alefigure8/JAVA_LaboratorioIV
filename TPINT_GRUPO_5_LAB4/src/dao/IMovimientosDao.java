package dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import entidad.Destinatario;
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
	public int obtenerUltimoIdMovimiento() throws SQLException;
	public List<Movimiento> obtenerPorCliente(int cliente) throws SQLException;
	public List<Movimiento> obtenerTransferenciasPorCliente(int cliente) throws SQLException;
	public HashMap<Integer, Destinatario> obtenerDestinatariosTransferenciasPorNumeroCliente(int numeroCliente) throws SQLException;
	public Movimiento obtenerUnoPorId (int id) throws SQLException;
	public int totalTransferenciasAnio(String anio)throws SQLException;
	public int totalTransferenciasAnioMes(String anio,String mes)throws SQLException;
	public double MontoTransferenciaAnio(String anio)throws SQLException;
	public double MontoTransferenciaAnioMes(String anio,String Mes)throws SQLException;
	public List<Movimiento> obtenerUltimosTresMovimientos(String CBU) throws SQLException;
}