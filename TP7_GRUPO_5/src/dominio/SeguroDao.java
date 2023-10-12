package dominio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SeguroDao {
	
	private static final String ultimoId = "select idSeguro from seguros order by idSeguro desc limit 1";
	
	//ULTIMO ID
	public int ultimoID() {
		PreparedStatement pStatement;
		ResultSet rSet;
		Conexion conexion= Conexion.getConexion();
		int ultimo = 0;
		try {
			pStatement=conexion.getSQLConexion().prepareStatement(ultimoId);
			rSet=pStatement.executeQuery();
			if(rSet.next())
			{
				ultimo = rSet.getInt("idSeguro");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ultimo;
	}
	

}
