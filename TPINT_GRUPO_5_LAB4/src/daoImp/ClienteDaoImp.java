package daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.xpath.internal.operations.Bool;

import dao.Conexion;
import dao.IClienteDao;
import entidad.Cliente;
import entidad.Cuenta;
import entidad.Direccion;
import entidad.Localidad;
import entidad.Provincia;
import entidad.TipoAcceso;
import entidad.TipoDireccion;
import entidad.Usuario;
import excepciones.CorreoException;


public class ClienteDaoImp implements IClienteDao{

	
	private static final String insertUsuarios = "Insert into Usuarios (Usuario, Contrasena, TipoAcceso, Fechaalta) values (?, ?, ?, ?)";
	private static final String insertDirecciones = "Insert into Direcciones (IdLocalidad, CodigoPostal, Calle, Numero, TipoDireccion, NumeroDepartamento) values (?, ?, ?, ?, ?, ?)";
	private static final String insertClientes = "Insert into Clientes (Nombre, Apellido, Id, Dni, Cuil, Sexo, Nacionalidad, FechaNacimiento, Correo, Telefono, IDDomicilio) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final String updateUsuarios = "Update Usuarios set  Usuario = ?, Contrasena = ?, TipoAcceso = ?, Fechaalta = ? where Id = ?";
	private static final String updateDirecciones = "Update Direcciones set IdLocalidad = ?, CodigoPostal = ?, Calle = ?, Numero = ?, TipoDireccion = ?, NumeroDepartamento = ? where IdDireccion = ?";
	private static final String updateClientes = "Update Clientes set Nombre = ?, Apellido = ?, Dni = ?, Cuil = ?, Sexo = ?, Nacionalidad = ?, FechaNacimiento = ?, Correo = ?, Telefono = ? where Id = ?";
	
	private static final String leerTodos = "select * from Clientes C inner join Usuarios U on U.Id = C.Id \r\n" + 
			"inner join Direcciones D on D.IdDireccion = C.IDDomicilio \r\n" + 
			"inner join Localidades L on L.IdLocalidad = D.IdLocalidad \r\n" + 
			"inner join Provincias P on P.IdProvincia = L.IDProvincia";
	
	private static final String leerUno="select * from Clientes C inner join Usuarios U on U.Id = C.Id \r\n" + 
			"inner join Direcciones D on D.IdDireccion = C.IDDomicilio \r\n" + 
			"inner join Localidades L on L.IdLocalidad = D.IdLocalidad \r\n" + 
			"inner join Provincias P on P.IdProvincia = L.IDProvincia \r\n" +
			"where C.id=?";
	
	private static final String obtenerClientePorDni="select * from Clientes C inner join Usuarios U on U.Id = C.Id \r\n" + 
			"inner join Direcciones D on D.IdDireccion = C.IDDomicilio \r\n" + 
			"inner join Localidades L on L.IdLocalidad = D.IdLocalidad \r\n" + 
			"inner join Provincias P on P.IdProvincia = L.IDProvincia \r\n" +
			"where C.dni=?";

	private static final String obtenerClientePorCBU="select * from Clientes C inner join Usuarios U on U.Id = C.Id \r\n" + 
														"inner join Direcciones D on D.IdDireccion = C.IDDomicilio \r\n" + 
														"inner join Localidades L on L.IdLocalidad = D.IdLocalidad \r\n" + 
														"inner join Provincias P on P.IdProvincia = L.IDProvincia \r\n" +
														"inner join Cuentas CU on CU.IdCliente = C.Id \r\n" +
														"where CU.CBU=?";
	
	private static final String clientePorFecha = "SELECT COUNT(Id) AS TotalClientesNuevo FROM Usuarios WHERE TipoAcceso = 'Cliente' AND YEAR(Fechaalta) = ? AND MONTH(Fechaalta) = ?";
	private static final String clientePorAnio = "SELECT COUNT(Id) AS TotalClientesNuevo FROM Usuarios WHERE TipoAcceso = 'Cliente' AND YEAR(Fechaalta) = ?";
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
	        usuarioStatement.setString(1, cliente.getUsuario());
	        usuarioStatement.setString(2, cliente.getContrasenia());
	        usuarioStatement.setString(3, cliente.getTipoAcceso().name());
			java.sql.Date fechaAltaSQL = java.sql.Date.valueOf(cliente.getFechaAlta()); // pasa de localDate a java.sql.date para la bd
			usuarioStatement.setDate(4, fechaAltaSQL);

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
			    clienteStatement.setString(1, cliente.getNombre());
			    clienteStatement.setString(2, cliente.getApellido());
			    clienteStatement.setInt(3, idUsuario);
			    clienteStatement.setInt(4, cliente.getDni());
			    clienteStatement.setLong(5, cliente.getCuil());
			    clienteStatement.setString(6, cliente.getSexo());
			    clienteStatement.setString(7, cliente.getNacionalidad());
		    	java.sql.Date fechaNacSQL = java.sql.Date.valueOf(cliente.getNacimiento()); // pasa de localDate a java.sql.date para la bd
		    	clienteStatement.setDate(8, fechaNacSQL);
		    	clienteStatement.setString(9, cliente.getEmail());
		    	clienteStatement.setLong(10, cliente.getTelefono());
		    	clienteStatement.setInt(11, idDireccion);
	           
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
	        pStatementUsuarios.setString(1, cliente.getUsuario());
	        pStatementUsuarios.setString(2, cliente.getContrasenia());
	        pStatementUsuarios.setString(3, cliente.getTipoAcceso().name());
	        java.sql.Date fechaAltaSQL = java.sql.Date.valueOf(cliente.getFechaAlta());
	        pStatementUsuarios.setDate(4, fechaAltaSQL);
	        pStatementUsuarios.setInt(5, cliente.getId());
	        
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
	        pStatementClientes.setString(1, cliente.getNombre());
	        pStatementClientes.setString(2, cliente.getApellido());
	        pStatementClientes.setInt(3, cliente.getDni());
	        pStatementClientes.setLong(4, cliente.getCuil());
	        pStatementClientes.setString(5, cliente.getSexo());
	        pStatementClientes.setString(6, cliente.getNacionalidad());
	        java.sql.Date fechaNacSQL = java.sql.Date.valueOf(cliente.getNacimiento());
	        pStatementClientes.setDate(7, fechaNacSQL);
	        pStatementClientes.setString(8, cliente.getEmail());
	        pStatementClientes.setLong(9, cliente.getTelefono());
	        pStatementClientes.setInt(10, cliente.getId());

	       
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
		PreparedStatement pStatement;
		Connection connection=Conexion.getConexion().getSQLConexion();
	    boolean deleteExitoso = false;

	    try {

	        // Desactivar el usuario
	        String desactivarUsuario = "UPDATE Usuarios SET Activo = 0 WHERE Id = ?";
	        pStatement = connection.prepareStatement(desactivarUsuario);
	        pStatement.setInt(1, idCliente);

	        int filasAfectadas = pStatement.executeUpdate();

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
	    }

	    return deleteExitoso;
	}

	/***************** ACTIVAR ********************/
	@Override
	public boolean activar(int idCliente) {
		PreparedStatement pStatement;
		Connection connection=Conexion.getConexion().getSQLConexion();
	    boolean deleteExitoso = false;

	    try {

	        // Desactivar el usuario
	        String desactivarUsuario = "UPDATE Usuarios SET Activo = 1 WHERE Id = ?";
	        pStatement = connection.prepareStatement(desactivarUsuario);
	        pStatement.setInt(1, idCliente);

	        int filasAfectadas = pStatement.executeUpdate();

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
	    }

	    return deleteExitoso;
	}
	
	/***************** OBTENER TODOS ********************/
	@Override
	public List<Cliente> obtenerTodos() {
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
			String usuario=rSet.getString("Usuario");
			String contrasena=rSet.getString("Contrasena");
			TipoAcceso tipoAcceso=TipoAcceso.valueOf(rSet.getString("TipoAcceso"));
			LocalDate fechaAlta=rSet.getDate("Fechaalta").toLocalDate();
		    Boolean activo =rSet.getBoolean("Activo");
		    
			//Atributos cliente
		    String nombre=rSet.getString("Nombre");
			String apellido=rSet.getString("Apellido");
			int dni=rSet.getInt("Dni");
			long cuil=rSet.getLong("Cuil");
			String sexo=rSet.getString("Sexo");
			String nacionalidad=rSet.getString("Nacionalidad");
			LocalDate fechaNac=rSet.getDate("FechaNacimiento").toLocalDate();
			String correo=rSet.getString("Correo");
			long telefono=rSet.getLong("Telefono");
			
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
			PreparedStatement pStatement;
			ResultSet rSet;
			Cliente cliente = new Cliente();
			Conexion conexion= Conexion.getConexion();
			
			try {
				pStatement=conexion.getSQLConexion().prepareStatement(leerUno);
				pStatement.setInt(1, id);
				rSet=pStatement.executeQuery();
				
				while(rSet.next()) {
					cliente = getCliente(rSet);
				}
				
			} catch (Exception e) {
				e.printStackTrace();;
			}
			
			return cliente;
		}

		
/***************** OBTENER UNO POR DNI ********************/
		@Override
		public Cliente obtenerCliente(int dni) {
			PreparedStatement pStatement;
			ResultSet rSet;
			Cliente cliente = new Cliente();
			Conexion conexion= Conexion.getConexion();
			
			try {
				pStatement=conexion.getSQLConexion().prepareStatement(obtenerClientePorDni);
				pStatement.setInt(1, dni);
				rSet=pStatement.executeQuery();
				
				while(rSet.next()) {
					cliente = getCliente(rSet);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return cliente;
		}

/***************** OBTENER UNO POR CBU ********************/
		@Override
		public Cliente obtenerClientePorCBU(String cbu) {
			PreparedStatement pStatement;
			ResultSet rSet;
			Cliente cliente = new Cliente();
			Conexion conexion= Conexion.getConexion();
			
			try {
				pStatement=conexion.getSQLConexion().prepareStatement(obtenerClientePorCBU);
				pStatement.setString(1, cbu);
				rSet=pStatement.executeQuery();
				
				while(rSet.next()) {
					cliente = getCliente(rSet);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return cliente;
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
				
				if(rSet.next()){
					existe = Boolean.valueOf(rSet.getBoolean("existe"));
				}
				
			} catch (SQLException e) {
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
					/*String nombre=rSet.getString("Nombre");
					String apellido=rSet.getString("Apellido");*/
					String contrasena=rSet.getString("Contrasena");
					TipoAcceso tipoAcceso=TipoAcceso.valueOf(rSet.getString("TipoAcceso"));
					LocalDate fechaAlta=rSet.getDate("Fechaalta").toLocalDate();
				    Boolean activo =rSet.getBoolean("Activo");
					
				    return new Usuario(usuario, id, contrasena, fechaAlta, activo, tipoAcceso );
				}
			} catch (SQLException e) {
				throw e;
			}
			
			return null;
		}
		
		/***************** CORREO EXISTE ********************/
		String existeCorreo = "Select count(*) as existe from Clientes where Correo = ?";
		@Override
		public boolean existeCorreo(String correo) throws CorreoException, SQLException {
			boolean existe = false;
			
			PreparedStatement pStatement;
			ResultSet rSet;

			Conexion conexion= Conexion.getConexion();
			
			try {
				pStatement=conexion.getSQLConexion().prepareStatement(existeCorreo);
				pStatement.setString(1, correo);
				
				rSet=pStatement.executeQuery();
				
				rSet.next();
				
				int count = rSet.getInt("existe");
				if(count > 0) {
					existe=true;
				}
				
				
				
				if (existe) {
		            throw new CorreoException("El correo ya se encuentra registrado");
		        }
				
			} catch (Exception e) {
				throw e;
			}
			System.out.println("EXISTE DAO: "+existe);
			return existe;
		}

		@Override
		public int clientesPorFecha(String anio, String mes) throws SQLException{
			int totalClientesNuevos = 0;
		    
		    PreparedStatement pStatement;
		    ResultSet rSet;

		    Conexion conexion = Conexion.getConexion();

		    try {
		        pStatement = conexion.getSQLConexion().prepareStatement(clientePorFecha);
		        pStatement.setString(1, anio);
		        pStatement.setString(2, mes);

		        rSet = pStatement.executeQuery();

		        if (rSet.next()) {
		            totalClientesNuevos = rSet.getInt("TotalClientesNuevo");
		        }

		    } catch (Exception e) {
		        throw e;
		    }

		    return totalClientesNuevos;
			
		}
		@Override
		 public int clientesPorAnio(String anio) throws SQLException{
			 
			 int totalClientesNuevos = 0;
			    
			    PreparedStatement pStatement;
			    ResultSet rSet;

			    Conexion conexion = Conexion.getConexion();

			    try {
			        pStatement = conexion.getSQLConexion().prepareStatement(clientePorAnio);
			        pStatement.setString(1, anio);
			       

			        rSet = pStatement.executeQuery();

			        if (rSet.next()) {
			            totalClientesNuevos = rSet.getInt("TotalClientesNuevo");
			        }

			    } catch (Exception e) {
			        throw e;
			    }

			    return totalClientesNuevos;
			 
			 
			 
			 
			 
		 }
		
		String existeSoloUsuario = "Select count(*) as existe from Usuarios where Usuario = ?";
		
		public Boolean existeSoloUsuario(String usuario) throws SQLException {
			
			boolean existe = false;
			
			PreparedStatement pStatement;
			ResultSet rSet;

			Conexion conexion= Conexion.getConexion();
			
			try {
				pStatement=conexion.getSQLConexion().prepareStatement(existeSoloUsuario);
				pStatement.setString(1, usuario);
				
				rSet=pStatement.executeQuery();
				
				rSet.next();
				
				int count = rSet.getInt("existe");
				if(count > 0) {
					existe=true;
				}
				
				
			} catch (Exception e) {
				throw e;
			}
			
			return existe;
		}




}
