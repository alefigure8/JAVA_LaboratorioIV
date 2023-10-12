package dominio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoSeguroDao {
	private static final String readAll="select * from tipoSeguros";
	
	//READALL
	public List<TipoSeguro> readAll() {
		PreparedStatement pStatement;
		ResultSet rSet; // guardamos rdo de la consulta
		ArrayList<TipoSeguro> seguros=new ArrayList<TipoSeguro>();
		Conexion conexion= Conexion.getConexion();
		
		try {
			pStatement=conexion.getSQLConexion().prepareStatement(readAll);
			rSet=pStatement.executeQuery();
			while(rSet.next()) {
				seguros.add(getTipoSeguro(rSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return seguros;
	}
	
	
	//GET SEGURO CON EL RESULTSET
	private TipoSeguro getTipoSeguro(ResultSet rSet) throws SQLException {
		String descripcion = rSet.getString("descripcion");
		int idTipo = rSet.getInt("idTipo");
		return new TipoSeguro(idTipo, descripcion);
	}
}
