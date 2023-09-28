package dominio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;;

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

}
