package dominio;

import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;;

public class UsuarioDao {
	
	private String host ="jdbc:mysql://localhost:33060/";
	private String user ="root";
	private String pass ="root";
	private String dbName ="bdPersonas";
	
	public UsuarioDao() {
		
	}
	
	public int agregarUsuario(Usuario usuario) {
		String query = "INSERT INTO Personas(dni, nombre, apellido) VALUES('"+usuario.getDni()+"','"+usuario.getNombre()+"','"+usuario.getApellido()+"')";
		Connection connection = null;
		int filas = 0;
		
		try {
			
			connection = DriverManager.getConnection(host+dbName,user,pass);
			Statement statement = connection.createStatement();
			filas = statement.executeUpdate(query);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return filas;
	}
	
	public Usuario obtenerUsuario(String dni) {
		Connection connection = null;
		Usuario aux = new Usuario();
		String query = "SELECT Nombre, Apellido, Dni FROM Personas WHERE DNI='"+dni+"'";
		
		try {
			connection = DriverManager.getConnection(host+dbName,user,pass);
			Statement statement = connection.createStatement();
			ResultSet resultado = statement.executeQuery(query);
			
			resultado.next();
			aux.setNombre(resultado.getString("Nombre"));
			aux.setApellido(resultado.getString("Apellido"));
			aux.setDni(resultado.getString("Dni"));			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return aux;
	}
	
	public Usuario obtenerUsuarioPst(String dni) {
		Connection connection = null;
		Usuario aux = new Usuario();
		String query = "SELECT Nombre, Apellido, Dni FROM Personas WHERE DNI=?";
		
		try {
			connection = DriverManager.getConnection(host+dbName,user,pass);
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, dni);
			ResultSet resultado = pst.executeQuery(query);
			
			resultado.next();
			aux.setNombre(resultado.getString("Nombre"));
			aux.setApellido(resultado.getString("Apellido"));
			aux.setDni(resultado.getString("Dni"));			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return aux;
	}
	
	public List<Usuario> obtenerTodosUsuario() {
		Connection connection = null;
		List<Usuario> auxList = new ArrayList<Usuario>();
		String query = "SELECT Nombre, Apellido, Dni FROM Personas";
		
		try {
			connection = DriverManager.getConnection(host+dbName,user,pass);
			Statement statement = connection.createStatement();
			ResultSet resultado = statement.executeQuery(query);
			
			while(resultado.next()) {
				Usuario aux = new Usuario();
				aux.setNombre(resultado.getString("Nombre"));
				aux.setApellido(resultado.getString("Apellido"));
				aux.setDni(resultado.getString("Dni"));	
				
				auxList.add(aux);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return auxList;
	}
	
	public void ejecutarProcedimientoAlmacenado(Usuario ususario) {
		Connection connection = null;
		
		try {
			connection = DriverManager.getConnection(host+dbName,user,pass);
			CallableStatement callableStatement = connection.prepareCall("CALL crearusuario(?,?,?)");
			callableStatement.setString(1, ususario.getDni());
			callableStatement.setString(2, ususario.getNombre());
			callableStatement.setString(3, ususario.getApellido());
			callableStatement.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
