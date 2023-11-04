package daoImp;
import dao.Conexion;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.IPrestamosDao;
import entidad.CuotaPrestamo;
import entidad.Estado;
import entidad.EstadoCuota;
import entidad.Prestamo;
import entidad.TipoTasa;


public class PrestamosImpl implements dao.IPrestamosDao {

	private static final String obtenertodos = "select ID,MontoPedido,MontoConIntereses,IdTasaxCuotas,MontoXmes,IdEstados,Cancelado,FechaPrestamo,IdCliente,Numerocuenta from Prestamos"; 
	private static final String obtenertodosxcliente = "select ID,MontoPedido,MontoConIntereses,IdTasaxCuotas,MontoXmes,IdEstados,Cancelado,FechaPrestamo,Numerocuenta from Prestamos where IdCliente = ? ";
	private static final String obtenertodosxcuenta = "select ID,MontoPedido,MontoConIntereses,IdTasaxCuotas,MontoXmes,IdEstados,Cancelado,FechaPrestamo,Numerocuenta from Prestamos where IdCuenta = ? ";
	private static final String obtenertipotasa = "select CantCuotas,TasaInteres from TipoTasa where IdTipoInteres=?";  // HACER UN JOIN EN OBTENERTODOS
	private static final String obtenertodostipostasas = "select IdTipoInteres,CantCuotas,TasaInteres from TipoTasa";
	private static final String obtenerestado = "select Descripcion from Estados where IdEstados = ?";
	private static final String obtenerunoxidprestamo = "select MontoPedido,MontoConIntereses,IdTasaxCuotas,MontoXmes,IdEstados,Cancelado,FechaPrestamo,IdCliente,Numerocuenta from Prestamos where ID=?";;
	private static final String obtenercuotasxprestamo = "select ID,IdPrestamo,NumeroCuota,MontoCuota,FechaVencimiento,Estado,FechaPagoCuota from CuotasPrestamo where IdPrestamo = ?"; 
	private static final String obtenerunacuota = "select ID,IdPrestamo,NumeroCuota,MontoCuota,FechaVencimiento,Estado,FechaPagoCuota from CuotasPrestamo where ID = ? and IdPrestamo = ?"; // NO TESTEADO
	private static final String insertarprestamo = "Insert into Prestamos (MontoPedido, MontoConIntereses,IdTasaxCuotas,MontoXmes,IdEstados,FechaPrestamo,IdCliente,NumeroCuenta) values (?,?, ?, ?, ?, ?, ?,?)";
	private static final String insertarcuotas = "Insert into CuotasPrestamo (IDPrestamo,NumeroCuota,MontoCuota,FechaVencimiento,Estado) values (?, ?, ?, ?, ?)";
	private static final String setcancelado = "Update Prestamos set Cancelado = 1 where ID = ? ";
	private static final String setcuotapagada = "Update CuotasPrestamo set Estado = 'Pagado', FechaPagoCuota= ? where IdPrestamo = ? and ID = ?  ";	

	public List<Prestamo> obtenerTodos() { 
		
		 PreparedStatement statement;
	        ResultSet resultSet; 
	        ArrayList<Prestamo> listado = new ArrayList<Prestamo>();
	        Conexion conexion = Conexion.getConexion();
		    try 
	        {
	            statement = conexion.getSQLConexion().prepareStatement(obtenertodos);
	            resultSet = statement.executeQuery();
	            while(resultSet.next())
	            {
	            
	                Prestamo prestamoaux = new Prestamo();	  	           	               
	            	prestamoaux.setId(resultSet.getInt("ID"));
	            	prestamoaux.setMontoPedido(resultSet.getDouble("MontoPedido"));
	             	prestamoaux.setMontoConIntereses(resultSet.getDouble("MontoConIntereses"));
	             	prestamoaux.setTipoTasa(obtenertipotasa(resultSet.getInt("IdTasaxCuotas"), conexion)); 
	             	prestamoaux.setMontoxMes(resultSet.getDouble("MontoXmes"));
	             	prestamoaux.setEstado(obtenerestado(resultSet.getInt("IdEstados"),conexion));
	             	prestamoaux.setCancelado(resultSet.getBoolean("Cancelado"));	
	               	prestamoaux.setFechaPrestamo(resultSet.getDate("FechaPrestamo").toLocalDate());
	             	prestamoaux.setIdCliente(resultSet.getInt("IdCliente"));	 
	             	prestamoaux.setNumeroCuenta(resultSet.getInt("NumeroCuenta"));
	       
	            	listado.add(prestamoaux);

	            }
	        } 
	        catch (SQLException e) 
	        {
	            e.printStackTrace();
	        }
	        return listado;
		
	}

	public List<Prestamo> obtenerTodosxcliente(int idcliente) {
		
		 PreparedStatement statement;
	        ResultSet resultSet; 
	        ArrayList<Prestamo> listado = new ArrayList<Prestamo>();
	        Conexion conexion = Conexion.getConexion();
		    try 
	        {
	            statement = conexion.getSQLConexion().prepareStatement(obtenertodosxcliente);
	            statement.setInt(1, idcliente);
	            resultSet = statement.executeQuery();
	            while(resultSet.next())
	            {
	            	
	                Prestamo prestamoaux = new Prestamo();	  	           	               
	            	prestamoaux.setId(resultSet.getInt("ID"));
	            	prestamoaux.setMontoPedido(resultSet.getDouble("MontoPedido"));
	             	prestamoaux.setMontoConIntereses(resultSet.getDouble("MontoConIntereses"));
	             	prestamoaux.setTipoTasa(obtenertipotasa(resultSet.getInt("IdTasaxCuotas"), conexion)); 
	             	prestamoaux.setMontoxMes(resultSet.getDouble("MontoXmes"));
	             	prestamoaux.setEstado(obtenerestado(resultSet.getInt("IdEstados"),conexion));
	             	prestamoaux.setCancelado(resultSet.getBoolean("Cancelado"));	
	               	prestamoaux.setFechaPrestamo(resultSet.getDate("FechaPrestamo").toLocalDate());
	             	prestamoaux.setIdCliente(idcliente);   	 
	             	listado.add(prestamoaux);

	            }
	        } 
	        catch (SQLException e) 
	        {
	            e.printStackTrace();
	        }
	        return listado;
		
	}
	
	public List<Prestamo> obtenerTodosxcuenta(int nrocuenta) {
		 PreparedStatement statement;
	        ResultSet resultSet; 
	        ArrayList<Prestamo> listado = new ArrayList<Prestamo>();
	        Conexion conexion = Conexion.getConexion();
		    try 
	        {
	            statement = conexion.getSQLConexion().prepareStatement(obtenertodosxcuenta);
	            statement.setInt(1, nrocuenta);
	            resultSet = statement.executeQuery();
	            while(resultSet.next())
	            {
	            	
	                Prestamo prestamoaux = new Prestamo();	  	           	               
	            	prestamoaux.setId(resultSet.getInt("ID"));
	            	prestamoaux.setMontoPedido(resultSet.getDouble("MontoPedido"));
	             	prestamoaux.setMontoConIntereses(resultSet.getDouble("MontoConIntereses"));
	             	prestamoaux.setTipoTasa(obtenertipotasa(resultSet.getInt("IdTasaxCuotas"), conexion)); 
	             	prestamoaux.setMontoxMes(resultSet.getDouble("MontoXmes"));
	             	prestamoaux.setEstado(obtenerestado(resultSet.getInt("IdEstado"),conexion));
	             	prestamoaux.setCancelado(resultSet.getBoolean("Cancelado"));	
	               	prestamoaux.setFechaPrestamo(resultSet.getDate("FechaPrestamo").toLocalDate());
	               	prestamoaux.setNumeroCuenta(nrocuenta);
	             	prestamoaux.setIdCliente(resultSet.getInt("IdCliente"));
	      	 
	             	listado.add(prestamoaux);

	            }
	        } 
	        catch (SQLException e) 
	        {
	            e.printStackTrace();
	        }
	        return listado;
		
	}

	public Prestamo obteneruno(int idprestamo) {
		
		PreparedStatement statement;
	    ResultSet resultSet; 
    	Prestamo prestamoaux = new Prestamo();	 
	    Conexion conexion = Conexion.getConexion();


	    try {
	 
	        statement = conexion.getSQLConexion().prepareStatement(obtenerunoxidprestamo);
	        statement.setInt(1, idprestamo);

	        resultSet = statement.executeQuery();
	 
	        if (resultSet.next()) {
	        	
	        	prestamoaux.setId(idprestamo);
	           	prestamoaux.setMontoPedido(resultSet.getDouble("MontoPedido"));
             	prestamoaux.setMontoConIntereses(resultSet.getDouble("MontoConIntereses"));
             	prestamoaux.setTipoTasa(obtenertipotasa(resultSet.getInt("IdTasaxCuotas"), conexion)); 
             	prestamoaux.setMontoxMes(resultSet.getDouble("MontoXmes"));
             	prestamoaux.setEstado(obtenerestado(resultSet.getInt("IdEstados"),conexion));
             	prestamoaux.setCancelado(resultSet.getBoolean("Cancelado"));	
               	prestamoaux.setFechaPrestamo(resultSet.getDate("FechaPrestamo").toLocalDate());
             	prestamoaux.setIdCliente(resultSet.getInt("IdCliente"));
             	prestamoaux.setNumeroCuenta(resultSet.getInt("Numerocuenta"));
	
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return prestamoaux;
	}
			
	public List<CuotaPrestamo> obtenerCuotasxprestamo(int idPrestamo) {
		
		 PreparedStatement statement;
	      ResultSet resultSet; 
	      CuotaPrestamo cuotaaux = new CuotaPrestamo();
	      ArrayList<CuotaPrestamo> listadoCuotas = new ArrayList<CuotaPrestamo>();
	      Conexion conexion = Conexion.getConexion();
	      
	      try 
	      {
	          statement = conexion.getSQLConexion().prepareStatement(obtenercuotasxprestamo);
	          statement.setInt(1, idPrestamo);
	          resultSet = statement.executeQuery();
	          while(resultSet.next())
	          {
	      
	        	cuotaaux.setId(resultSet.getInt("ID"));
	        	cuotaaux.setIdPrestamo(resultSet.getInt("IDPrestamo"));
	        	cuotaaux.setNumeroCuota(resultSet.getInt("NumeroCuota"));
	        	cuotaaux.setMontoCuota(resultSet.getDouble("MontoCuota"));
	        	cuotaaux.setFechaVencimiento(resultSet.getDate("FechaVencimiento").toLocalDate());
	        	cuotaaux.setEstado(EstadoCuota.valueOf(resultSet.getString("Estado")))	        	;
	        	if(resultSet.getDate("FechaPagoCuota")!=null) {
	        	cuotaaux.setFechaPago(resultSet.getDate("FechaPagoCuota").toLocalDate());
	        	}
	        	listadoCuotas.add(cuotaaux);
	        	}
	      } 
	      catch (SQLException e) 
	      {
	          e.printStackTrace();
	      }
	      
	      return listadoCuotas;				
		
	}
	
	public CuotaPrestamo obtenerUnaCuota(int idCuota, int idprestamo) {
		
		 PreparedStatement statement;
	      ResultSet resultSet; 
	      CuotaPrestamo cuotaaux = new CuotaPrestamo();
	      Conexion conexion = Conexion.getConexion();

	      try 
	      {
	          statement = conexion.getSQLConexion().prepareStatement(obtenerunacuota);
	          statement.setInt(1, idCuota);
	          statement.setInt(2, idprestamo);
	          resultSet = statement.executeQuery();
	          while(resultSet.next())
	          {
	      
	        	cuotaaux.setId(resultSet.getInt("ID"));
	        	cuotaaux.setIdPrestamo(resultSet.getInt("IDPrestamo"));
	        	cuotaaux.setNumeroCuota(resultSet.getInt("NumeroCuota"));
	        	cuotaaux.setMontoCuota(resultSet.getDouble("MontoCuota"));
	        	cuotaaux.setFechaVencimiento(resultSet.getDate("FechaVencimiento").toLocalDate());	   
	     
	        	}
	      } 
	      catch (SQLException e) 
	      {
	          e.printStackTrace();
	      }
	      
	      return cuotaaux;				
		
	}

	public boolean insertarprestamo(Prestamo prestamo) {
		
		PreparedStatement statement;
		Connection connection = Conexion.getConexion().getSQLConexion();
		boolean insertExitoso = false;
		
		try {
	
			statement = connection.prepareStatement(insertarprestamo);			
				
			statement.setDouble(1, prestamo.getMontoPedido());
			statement.setDouble(2,prestamo.getMontoConIntereses());
			statement.setInt(3, prestamo.getTipoTasa().getId());
			statement.setDouble(4, prestamo.getMontoxMes());
			statement.setInt(5, prestamo.getEstado().getIdEstado());
			java.sql.Date fechaprestamoSQL = java.sql.Date.valueOf(prestamo.getFechaPrestamo());
			statement.setDate(6, fechaprestamoSQL);
			statement.setInt(7, prestamo.getIdCliente());
			statement.setInt(8,prestamo.getNumeroCuenta());
	
		if(statement.executeUpdate()>0) {
				
				connection.commit();
				insertExitoso = true;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			}catch(SQLException e2) {
				e2.printStackTrace();
			}
		}
		return insertExitoso;
	}
	
	public TipoTasa obtenertipotasa(int idTasa, Conexion conexion) {

				
		  PreparedStatement statement;
	      ResultSet resultSet; 
	      TipoTasa tipotasa = new TipoTasa();
	    
	      try 
	      {
	    	  
	          statement = conexion.getSQLConexion().prepareStatement(obtenertipotasa);
	          statement.setInt(1, idTasa);
	          
	          resultSet = statement.executeQuery();
	          while(resultSet.next())
	          {
	      
	        	tipotasa.setCantCuotas(resultSet.getInt("CantCuotas"));
	        	tipotasa.setId(idTasa);
	        	tipotasa.setTasaInteres(resultSet.getDouble("TasaInteres"));
	        	}
	      } 
	      catch (SQLException e) 
	      {
	          e.printStackTrace();
	      }
	      return tipotasa;	
			
		
	}
	
	@Override
	public Estado obtenerestado(int idestado, Conexion conexion) {
		
		  PreparedStatement statement;
	      ResultSet resultSet; 
	      Estado estado = new Estado ();
	      try 
	      {

	          statement = conexion.getSQLConexion().prepareStatement(obtenerestado);
	          statement.setInt(1, idestado);
	          resultSet = statement.executeQuery();
	          while(resultSet.next())
	          {
	        	estado.setIdEstado(idestado);
	        	estado.setDescripcion(resultSet.getString("Descripcion"));
	        	}
	      } 
	      catch (SQLException e) 
	      {
	          e.printStackTrace();
	      }
	      return estado;	
	}
	
	@Override
	public List<TipoTasa> obtenerTodosTiposTasas() {
		
		  PreparedStatement statement;
	      ResultSet resultSet; 
	      Conexion conexion = Conexion.getConexion();
	      TipoTasa tipotasa = new TipoTasa();
	      ArrayList<TipoTasa> listadotipostasas = new ArrayList<TipoTasa>();
	      
	      try 
	      {
	    	  
	          statement = conexion.getSQLConexion().prepareStatement(obtenertodostipostasas);
	                 
	          resultSet = statement.executeQuery();
	          while(resultSet.next())
	          {
	      
	        	tipotasa.setCantCuotas(resultSet.getInt("CantCuotas"));
	        	tipotasa.setId(resultSet.getInt("IdTipoInteres"));
	        	tipotasa.setTasaInteres(resultSet.getDouble("TasaInteres"));
	        	listadotipostasas.add(tipotasa);
	        	}
	      } 
	      catch (SQLException e) 
	      {
	          e.printStackTrace();
	      }
	      return listadotipostasas;	
		
		
	}

	public boolean insertarcuotas(Prestamo prestamo) {
		
		
		
	    boolean insertExitoso = false;
	    PreparedStatement statement;
	    Connection connection = Conexion.getConexion().getSQLConexion();
	    LocalDate fechadevencimiento = prestamo.getFechaPrestamo();
	    int cuotasInsertadas = 0; 

	    for (int i = 0; i < prestamo.getTipoTasa().getCantCuotas(); i++) {
	        try {
	            fechadevencimiento = fechadevencimiento.plusMonths(1);
	            statement = connection.prepareStatement(insertarcuotas);
	            statement.setInt(1, prestamo.getId());
	            statement.setInt(2, i + 1);
	            statement.setDouble(3, prestamo.getMontoxMes());
	            statement.setDate(4, java.sql.Date.valueOf(fechadevencimiento));
	            statement.setString(5, EstadoCuota.Pendiente.toString());
	            if (statement.executeUpdate() > 0) {
	                cuotasInsertadas++;
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            try {
	                connection.rollback();
	            } catch (SQLException e2) {
	                e2.printStackTrace();
	            }
	        }
	    }

	    if (cuotasInsertadas == prestamo.getTipoTasa().getCantCuotas()) {
	    
	        insertExitoso = true;
	        try {
	            connection.commit();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return insertExitoso;
	}


	@Override
	public boolean setcancelado(int idprestamo) {
		
		PreparedStatement statement;
		Connection connection = Conexion.getConexion().getSQLConexion();
		boolean isupdateExitoso = false;
	    try 
	      {

	          statement = connection.prepareStatement(setcancelado);
	          statement.setInt(1, idprestamo);
	  
	          
	          if(statement.executeUpdate()>0) {
					
					connection.commit();
					isupdateExitoso = true;
				}
				
			}catch(SQLException e) {
				e.printStackTrace();
				try {
					connection.rollback();
				}catch(SQLException e2) {
					e2.printStackTrace();
				}
			}
			return isupdateExitoso;
		
			
		
		
	}

	@Override
	public boolean setcuotapagada(int idprestamo, int idcuota) {
	
		PreparedStatement statement;
		Connection connection = Conexion.getConexion().getSQLConexion();
		boolean isupdateExitoso = false;
	    try 
	      {

	          statement = connection.prepareStatement(setcuotapagada);
	          statement.setDate(1,java.sql.Date.valueOf(LocalDate.now()));
	          statement.setInt(2, idprestamo);
	          statement.setInt(3, idcuota);

	          
	          if(statement.executeUpdate()>0) {
					
					connection.commit();
					isupdateExitoso = true;
				}
				
			}catch(SQLException e) {
				e.printStackTrace();
				try {
					connection.rollback();
				}catch(SQLException e2) {
					e2.printStackTrace();
				}
			}
			return isupdateExitoso;
		
		
		
	}

	
	
}
