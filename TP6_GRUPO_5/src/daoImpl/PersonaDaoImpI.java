package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.IPersonaDao;
import entidad.Persona;

public class PersonaDaoImpI implements IPersonaDao{

			private static final String insert="insert into Personas"
			+ "(dni,nombre,apellido)values(?,?,?)";
			private static final String update="update Personas set nombre=?, apellido=? where dni=?";
			private static final String delete="delete from Personas where dni=?";
			private static final String readAll="select * from Personas";
			private static final String count="SELECT COUNT(*) FROM Personas WHERE dni = ?";
			
			
			//INSERT
			@Override
			public boolean insert(Persona persona) {
				PreparedStatement pStatement;
				Connection connection= Conexion.getConexion().getSQLConexion();
				boolean insertExitoso=false;
				try {
					pStatement=connection.prepareStatement(insert);
					pStatement.setString(1, persona.getDni());
					pStatement.setString(2, persona.getNombre());
					pStatement.setString(3, persona.getApellido());
					if(pStatement.executeUpdate()>0) {
						connection.commit();
						insertExitoso=true;
					}
					
				} catch (SQLException e1) {
					System.out.println(e1.toString());
					// e1.printStackTrace();
					try {
						connection.rollback();
					} catch (SQLException e2) {
						System.out.println(e2.toString());
					}
				}
				return insertExitoso;
			}
			

			//UPDATE 
			@Override
			public boolean update(Persona persona_a_modificar) {
				PreparedStatement pStatement;
				Connection connection= Conexion.getConexion().getSQLConexion();
				boolean updateExitoso=false;
				try {
					pStatement=connection.prepareStatement(update);
					pStatement.setString(1, persona_a_modificar.getNombre());
					pStatement.setString(2, persona_a_modificar.getApellido());
					pStatement.setString(3, persona_a_modificar.getDni());
					if(pStatement.executeUpdate()>0) {
						connection.commit();
						updateExitoso=true;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				return updateExitoso;
			}
			
			
			//DELETE
			@Override
			public boolean delete(Persona persona_a_eliminar) {
				PreparedStatement pStatement;
				Connection connection= Conexion.getConexion().getSQLConexion();
				boolean deleteExitoso=false;
				try {
					pStatement=connection.prepareStatement(delete);
					pStatement.setString(1, persona_a_eliminar.getDni());
					
					if(pStatement.executeUpdate()>0) {
						connection.commit();
						deleteExitoso=true;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				return deleteExitoso;
			}
			
			//READALL
			@Override
			public List<Persona> readAll() {
				PreparedStatement pStatement;
				ResultSet rSet; // guardamos rdo de la consulta
				ArrayList<Persona> personas=new ArrayList<Persona>();
				Conexion conexion= Conexion.getConexion();
				
				try {
					pStatement=conexion.getSQLConexion().prepareStatement(readAll);
					rSet=pStatement.executeQuery();
					while(rSet.next()) {
						personas.add(getPersona(rSet));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				return personas;
			}
			
			//GET PERSONA CON EL RESULTSET
			private Persona getPersona(ResultSet rSet) throws SQLException {
				String dni= rSet.getString("dni");
				String nombre=rSet.getString("nombre");
				String apellido=rSet.getString("apellido");
				return new Persona(dni,nombre,apellido);
			}
			
			// VERIFICAR SI LA PK EXISTE
			public int existe(String dni) {
			    Connection connection = null;
			    PreparedStatement pstm = null;
			    ResultSet rs = null;
			    int total = 0;

			    try {
			        connection = Conexion.getConexion().getSQLConexion();
			        pstm = connection.prepareStatement(count);
			        pstm.setString(1, dni);

			        rs = pstm.executeQuery();
			        rs.next();
			        total = rs.getInt(1);
			    } catch (SQLException e) {
			        e.printStackTrace();
			    }
			    return total;
			}


}
