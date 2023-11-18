package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	public static Conexion instancia;
	private Connection connection;
	private String host ="jdbc:mysql://localhost:3306/bdbancos?serverTimezone=UTC";
	private String user = "root";
	private String pass = "root";
	//private String pass = "1234";
	//private String pass = "Gato21226";


	//private String dbName = "bdBancos";
	
	private Conexion()
	{
		
		try
		{
			//Class.forName("com.mysql.cj.jdbc.Driver");
			Class.forName("com.mysql.jdbc.Driver");
			this.connection = DriverManager.getConnection(host,user,pass);
			this.connection.setAutoCommit(false);
		}
		catch(Exception e)
		{
			System.out.println("ERRORES " + e.getMessage());
			//e.printStackTrace();
		}
	}
	
	public static Conexion getConexion()   
	{								
		if(instancia == null)
		{
			instancia = new Conexion();
		}
		return instancia;
	}

	public Connection getSQLConexion() 
	{
		return this.connection;
	}
	
	public void cerrarConexion()
	{
		try 
		{
			this.connection.close();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		instancia = null;
	}
}
