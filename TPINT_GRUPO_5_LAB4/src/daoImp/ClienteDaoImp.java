package daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.If;

import com.sun.org.apache.xpath.internal.operations.Bool;

import dao.Conexion;
import dao.IClienteDao;
import entidad.Cliente;
import entidad.Direccion;
import entidad.Localidad;
import entidad.Provincia;
import entidad.TipoAcceso;
import entidad.TipoDireccion;
import entidad.Usuario;


public class ClienteDaoImp implements IClienteDao{

	
	private static final String insertUsuarios = "Insert into Usuarios (Nombre, Apellido, Usuario, Contrasena, TipoAcceso, Fechaalta) values (?, ?, ?, ?, ?, ?)";
	private static final String insertDirecciones = "Insert into Direcciones (IdLocalidad, CodigoPostal, Calle, Numero, TipoDireccion, NumeroDepartamento) values (?, ?, ?, ?, ?, ?)";
	private static final String insertClientes = "Insert into Clientes (Id, Dni, Cuil, Sexo, Nacionalidad, FechaNacimiento, Correo, Telefono, IDDomicilio) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final String updateUsuarios = "Update Usuarios set Nombre = ?, Apellido = ?, Usuario = ?, Contrasena = ?, TipoAcceso = ?, Fechaalta = ? where Id = ?";
	private static final String updateDirecciones = "Update Direcciones set IdLocalidad = ?, CodigoPostal = ?, Calle = ?, Numero = ?, TipoDireccion = ?, NumeroDepartamento = ? where IdDireccion = ?";
	private static final String updateClientes = "Update Clientes set Dni = ?, Cuil = ?, Sexo = ?, Nacionalidad = ?, FechaNacimiento = ?, Correo = ?, Telefono = ? where Id = ?";
	
	private static final String leerTodos = "select * from Clientes C inner join Usuarios U on U.Id = C.Id \r\n" + 
			"inner join Direcciones D on D.IdDireccion = C.IDDomicilio \r\n" + 
			"inner join Localidades L on L.IdLocalidad = D.IdLocalidad \r\n" + 
			"inner join Provincias P on P.IdProvincia = L.IDProvincia";
	
	
	/***************** INSERTAR ********************/
	@Override
	public boolean insertar(Cliente cliente) {
		// TODO Auto-generated method stub
		PreparedStatement pStatement;
		Connection connection=Conexion.getConexion().getSQLConexion();
		boolean  insertExitoso=false;
		PreparedStatement usuarioStatement = null;
	    PreparedStatement direccionStatement = null;
	    PreparedStatement clienteStatement = null;
		
		try {
			
			connection = Conexion.getConexion().getSQLConexion();

	        // usuarios
	        usuarioStatement = connection.prepareStatement(insertUsuarios, Statement.RETURN_GENERATED_KEYS);
	        usuarioStatement.setString(1, cliente.getNombre());
	        usuarioStatement.setString(2, cliente.getApellido());
	        usuarioStatement.setString(3, cliente.getUsuario());
	        usuarioStatement.setString(4, cliente.getContrasenia());
	        usuarioStatement.setString(5, cliente.getTipoAcceso().name());
			java.sql.Date fechaAltaSQL = java.sql.Date.valueOf(cliente.getFechaAlta()); // pasa de localDate a java.sql.date para la bd
			usuarioStatement.setDate(6, fechaAltaSQL);

	        int filasInsertadasUsuarios = usuarioStatement.executeUpdate();
	        if (filasInsertadasUsuarios > 0) {
	            ResultSet generatedKeysUsuarios = usuarioStatement.getGeneratedKeys();
	            int idUsuario = -1;
	            if (generatedKeysUsuarios.next()) {
	                idUsuario = generatedKeysUsuarios.getInt(1);
	            }

	            
	        // direccion
	        direccionStatement = connection.prepareStatement(insertDirecciones, Statement.RETURN_GENERATED_KEYS);
	        direccionStatement.setInt(1, cliente.getDireccion().getLocalidad().getIdLocalidad());
	        direccionStatement.setInt(2, cliente.getDireccion().getCodigoPostal());
	        direccionStatement.setString(3, cliente.getDireccion().getCalle());
	        direccionStatement.setInt(4, cliente.getDireccion().getNumero());
	        direccionStatement.setString(5, cliente.getDireccion().getTipoDireccion().name());
	        direccionStatement.setString(6, cliente.getDireccion().getNumeroDepartamento());

	        int filasInsertadasDirecciones = direccionStatement.executeUpdate();
	        if (filasInsertadasDirecciones > 0) {
	            ResultSet generatedKeysDirecciones = direccionStatement.getGeneratedKeys();
	            int idDireccion = -1;
	            if (generatedKeysDirecciones.next()) {
	                idDireccion = generatedKeysDirecciones.getInt(1);
	            }

	            
		    // cliente
		    clienteStatement = connection.prepareStatement(insertClientes);
		    clienteStatement.setInt(1, idUsuario);
		    clienteStatement.setInt(2, cliente.getDni());
		    clienteStatement.setInt(3, cliente.getCuil());
		    clienteStatement.setString(4, cliente.getSexo());
		    clienteStatement.setString(5, cliente.getNacionalidad());
	    	java.sql.Date fechaNacSQL = java.sql.Date.valueOf(cliente.getNacimiento()); // pasa de localDate a java.sql.date para la bd
	    	clienteStatement.setDate(6, fechaNacSQL);
	    	clienteStatement.setString(7, cliente.getEmail());
	    	clienteStatement.setInt(8, cliente.getTelefono());
	    	clienteStatement.setInt(9, idDireccion);
	           
	        int filasInsertadasClientes = clienteStatement.executeUpdate();
	            if (filasInsertadasClientes > 0) {
	                insertExitoso = true;
	                    connection.commit(); //realiza el commit solo si todas las tablas se pudieron insertar
	                }
	            }
	        }
	        
	    } catch (Exception e) {
	        try {
	            if (connection != null) {
	                connection.rollback(); // rollback caso contrario
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	        e.printStackTrace();
	    } 
	
			
		return insertExitoso;
	}

	/***************** EDITAR ********************/
	@Override
	public boolean editar(Cliente cliente) {
	PreparedStatement pStatement;
	Connection connection=Conexion.getConexion().getSQLConexion();
	boolean updateExitoso=false;
	
	PreparedStatement pStatementUsuarios;
	PreparedStatement pStatementDirecciones;
	PreparedStatement pStatementClientes;
		
		try {
			
			// usuarios
	        pStatementUsuarios = connection.prepareStatement(updateUsuarios);
	        pStatementUsuarios.setString(1, cliente.getNombre());
	        pStatementUsuarios.setString(2, cliente.getApellido());
	        pStatementUsuarios.setString(3, cliente.getUsuario());
	        pStatementUsuarios.setString(4, cliente.getContrasenia());
	        pStatementUsuarios.setString(5, cliente.getTipoAcceso().name());
	        java.sql.Date fechaAltaSQL = java.sql.Date.valueOf(cliente.getFechaAlta());
	        pStatementUsuarios.setDate(6, fechaAltaSQL);
	        pStatementUsuarios.setInt(7, cliente.getId());
	        
	        int filasUsuarios = pStatementUsuarios.executeUpdate();

	        // direccion
	        pStatementDirecciones = connection.prepareStatement(updateDirecciones);
	        pStatementDirecciones.setInt(1, cliente.getDireccion().getLocalidad().getIdLocalidad());
	        pStatementDirecciones.setInt(2, cliente.getDireccion().getCodigoPostal());
	        pStatementDirecciones.setString(3, cliente.getDireccion().getCalle());
	        pStatementDirecciones.setInt(4, cliente.getDireccion().getNumero());
	        pStatementDirecciones.setString(5, cliente.getDireccion().getTipoDireccion().name());
	        pStatementDirecciones.setString(6, cliente.getDireccion().getNumeroDepartamento());
	        pStatementDirecciones.setInt(7, cliente.getDireccion().getId());

	        int filasDirecciones = pStatementDirecciones.executeUpdate();

	        // cliente
	        pStatementClientes = connection.prepareStatement(updateClientes);
	        pStatementClientes.setInt(1, cliente.getDni());
	        pStatementClientes.setInt(2, cliente.getCuil());
	        pStatementClientes.setString(3, cliente.getSexo());
	        pStatementClientes.setString(4, cliente.getNacionalidad());
	        java.sql.Date fechaNacSQL = java.sql.Date.valueOf(cliente.getNacimiento());
	        pStatementClientes.setDate(5, fechaNacSQL);
	        pStatementClientes.setString(6, cliente.getEmail());
	        pStatementClientes.setInt(7, cliente.getTelefono());
	        pStatementClientes.setInt(8, cliente.getId());

	       
	        int filasClientes = pStatementClientes.executeUpdate();

	        
	        if (filasUsuarios > 0 && filasDirecciones > 0 && filasClientes > 0) {
	            updateExitoso = true;
	            connection.commit();//commit solo si las 3 tablas se modificaron
	        }
			
		} catch (Exception e) {
			// TODO: handle exception
			 try {
		            if (connection != null) {
		                connection.rollback(); // rollback caso contrario
		            }
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
			e.printStackTrace();
		}
	
		return updateExitoso;
	}

	
	
	/***************** BORRAR ********************/
	@Override
	public boolean borrar(int idCliente) {
	    Connection connection = null;
	    PreparedStatement pStatementUsuarios = null;
	    boolean deleteExitoso = false;

	    try {
	        connection = Conexion.getConexion().getSQLConexion();

	        // Desactivar el usuario
	        String desactivarUsuario = "UPDATE Usuarios SET Activo = 0 WHERE Id = ?";
	        pStatementUsuarios = connection.prepareStatement(desactivarUsuario);
	        pStatementUsuarios.setInt(1, idCliente);

	        int filasAfectadas = pStatementUsuarios.executeUpdate();

	        if (filasAfectadas > 0) {
	            deleteExitoso = true;
	            connection.commit();
	        }
	    } catch (Exception e) {
	        try {
	            if (connection != null) {
	                connection.rollback();
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	        e.printStackTrace();
	    } finally {
	        // Cerrar recursos
	        if (pStatementUsuarios != null) {
	            try {
	                pStatementUsuarios.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (connection != null) {
	            try {
	                connection.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    return deleteExitoso;
	}

	
	
	/***************** OBTENER TODOS ********************/
	@Override
	public List<Cliente> obtenerTodos() {
		// TODO Auto-generated method stub
		PreparedStatement pStatement;
		ResultSet rSet;
		List<Cliente> clientes = new ArrayList<Cliente>();
		Conexion conexion= Conexion.getConexion();
		
		try {
			pStatement=conexion.getSQLConexion().prepareStatement(leerTodos);
			rSet=pStatement.executeQuery();
			
			while(rSet.next()) {
				clientes.add(getCliente(rSet));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return clientes;
	}
	
	
	//GET CON RESULTSET
		private Cliente getCliente(ResultSet rSet) throws SQLException{
			//Atributos usuario
			int id=rSet.getInt("Id");
			String nombre=rSet.getString("Nombre");
			String apellido=rSet.getString("Apellido");
			String usuario=rSet.getString("Usuario");
			String contrasena=rSet.getString("Contrasena");
			TipoAcceso tipoAcceso=TipoAcceso.valueOf(rSet.getString("TipoAcceso"));
			LocalDate fechaAlta=rSet.getDate("Fechaalta").toLocalDate();
		    Boolean activo =rSet.getBoolean("Activo");
		    
			//Atributos cliente
			int dni=rSet.getInt("Dni");
			int cuil=rSet.getInt("Cuil");
			String sexo=rSet.getString("Sexo");
			String nacionalidad=rSet.getString("Nacionalidad");
			LocalDate fechaNac=rSet.getDate("FechaNacimiento").toLocalDate();
			String correo=rSet.getString("Correo");
			int telefono=rSet.getInt("Telefono");
			
			//Atributos Direccion
			int idDomicilio=rSet.getInt("IdDireccion");
			String calle=rSet.getString("Calle");
			int numero=rSet.getInt("Numero");
			TipoDireccion tipoDireccion=TipoDireccion.valueOf(rSet.getString("TipoDireccion"));
			String numeroDepartamento=rSet.getString("NumeroDepartamento");
			int codigoPostal=rSet.getInt("CodigoPostal");
			
			//Atributos localidad y provincia
			int idLocalidad=rSet.getInt("IDLocalidad");
			int idProvincia=rSet.getInt("IDProvincia");
			String nombreLocalidad=rSet.getString("NombreLocalidad");
			String nombreProvincia=rSet.getString("NombreProvincia");
			Localidad localidad=new Localidad(idLocalidad, nombreLocalidad, idProvincia);
			Provincia provincia=new Provincia(idProvincia, nombreProvincia);
			
			Direccion direccion=new Direccion(idDomicilio, calle, numero, tipoDireccion, numeroDepartamento, codigoPostal, localidad, provincia);

			return new Cliente(usuario, id, nombre, apellido, contrasena, fechaAlta, activo, tipoAcceso, dni, cuil, 
					nacionalidad, sexo, fechaNac, direccion, correo, telefono);
			
		}
		
		
		
/***************** OBTENER UNO ********************/
		@Override
		public Cliente obtenerUno(int id) {
			// TODO Auto-generated method stub
			return null;
		}
		
/***************** USUARIO EXISTE ********************/
String existeUsuario = "select count(*) as existe from Usuarios where Usuario = ? and Contrasena = ?";

public boolean existeUsuario(String usuario, String contrasena) throws SQLException {
	
	boolean existe = false;
	
	PreparedStatement pStatement;
	ResultSet rSet;

	Conexion conexion= Conexion.getConexion();
	
	try {
		pStatement=conexion.getSQLConexion().prepareStatement(existeUsuario);
		pStatement.setString(1, usuario);
		pStatement.setString(2, contrasena);
		rSet=pStatement.executeQuery();
		
		rSet.next();
		
		existe = Boolean.valueOf(rSet.getBoolean("existe"));
		
	} catch (Exception e) {
		throw e;
	}
	
	return existe;
}

/***************** OBTENER USUARIO POR USUARIO ********************/
	String obtenerUsuarioPorUsuario = "select * from Usuarios where Usuario = ?";
	
	public Usuario obtenerUsuarioPorUsuario(String usuario) throws SQLException {
		
		PreparedStatement pStatement;
		ResultSet rSet;
	
		Conexion conexion= Conexion.getConexion();
		
		try {
			pStatement=conexion.getSQLConexion().prepareStatement(obtenerUsuarioPorUsuario);
			pStatement.setString(1, usuario);
			rSet=pStatement.executeQuery();
			
			if(rSet.next()) {
				int id=rSet.getInt("Id");
				String nombre=rSet.getString("Nombre");
				String apellido=rSet.getString("Apellido");
				String contrasena=rSet.getString("Contrasena");
				TipoAcceso tipoAcceso=TipoAcceso.valueOf(rSet.getString("TipoAcceso"));
				LocalDate fechaAlta=rSet.getDate("Fechaalta").toLocalDate();
			    Boolean activo =rSet.getBoolean("Activo");
				
			    return new Usuario(usuario, id, nombre, apellido, contrasena, fechaAlta, activo, tipoAcceso );
			}
		} catch (Exception e) {
			throw e;
		}
		
		return null;
	}
}
