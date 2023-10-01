package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.IPersonaDao;
import entidad.Persona;

public class PersonaDao implements IPersonaDao{
	
	private static final String insert = "INSERT INTO Personas(Dni, Nombre, Apellido) VALUES(?, ?, ?)";
	private static final String delete = "DELETE FROM Personas WHERE Dni=?";
	private static final String update = "UPDATE Personas SET Nombre=?, Apellido=? WHERE Dni=?";
	private static final String readall = "SELECT * FROM Personas";
	private static final String readOne = "SELECT * FROM Personas WHERE Dni =?";

	@Override
	public boolean insert(Persona persona) throws Exception {
		Connection connection = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		
		try {
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, persona.getDni());
			statement.setString(2, persona.getNombre());
			statement.setString(3, persona.getApellido());
			
			if(statement.executeUpdate() >= 1) {
				connection.commit();
				isInsertExitoso = true;
			}
		} catch (Exception e) {
			throw e;
		}
		
		return isInsertExitoso;
	}

	@Override
	public boolean delete(String dni) throws Exception{
		Connection connection = Conexion.getConexion().getSQLConexion();
		boolean isDeleteExitoso = false;
		
		try {
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, dni);
			
			if(statement.executeUpdate() >= 1) {
				connection.commit();
				isDeleteExitoso = true;
			}
		} catch (Exception e) {
			throw e;
		}
		
		return isDeleteExitoso;
	}

	@Override
	public boolean update(Persona persona_a_modificar) throws Exception {
		Connection connection = Conexion.getConexion().getSQLConexion();
		boolean isUpdateExitoso = false;

		try {
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, persona_a_modificar.getNombre());
			statement.setString(2, persona_a_modificar.getApellido());
			statement.setString(3, persona_a_modificar.getDni());

			if(statement.executeUpdate() >= 1) {
				connection.commit();
				System.out.println("Entra");
				isUpdateExitoso = true;
			}
		} catch (Exception e) {
			throw e;
		}
		
		return isUpdateExitoso;
	}

	@Override
	public List<Persona> readAll() throws Exception{
		Connection connection = Conexion.getConexion().getSQLConexion();
		List<Persona> personas = new ArrayList<Persona>();
		
		try {
			PreparedStatement statement = connection.prepareStatement(readall);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				personas.add(getPersona(resultSet));
			}
		} catch (Exception e) {
			throw e;
		}
		
		return personas;
	}
	
	private Persona getPersona(ResultSet resultSet) throws SQLException
	{
		try {
			String dni = resultSet.getString("Dni");
			String nombre = resultSet.getString("Nombre");
			String apellido = resultSet.getString("Apellido");
			return new Persona(dni, nombre, apellido);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Boolean existePersona(String dni) throws Exception {
		Connection connection = Conexion.getConexion().getSQLConexion();
		PreparedStatement statement;
		ResultSet resultSet;
				
		try {
			statement = connection.prepareStatement(readOne);
			statement.setString(1, dni);			
			resultSet = statement.executeQuery();

			return resultSet.next();

		} catch (Exception e) {
			throw e;
		}
	}


}
