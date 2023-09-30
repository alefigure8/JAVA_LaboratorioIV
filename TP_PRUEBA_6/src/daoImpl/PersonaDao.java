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
	
	private static final String insert = "INSERT INTO personas(dni, nombre, telefono) VALUES(?, ?, ?)";
	private static final String delete = "DELETE FROM personas WHERE dni = ?";
	private static final String update = "UPDATE personas SET nombre = ?, telefono = e? WHERE dni = ?";
	private static final String readall = "SELECT * FROM personas";
	private static final String readOne = "SELECT * FROM personas WHERE dni = ?";

	@Override
	public boolean insert(Persona persona) {
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
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return isInsertExitoso;
	}

	@Override
	public boolean delete(String dni) {
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
			e.printStackTrace();
		}
		
		return isDeleteExitoso;
	}

	@Override
	public boolean update(Persona persona_a_modificar) {
		Connection connection = Conexion.getConexion().getSQLConexion();
		boolean isUpdateExitoso = false;
		
		try {
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, persona_a_modificar.getDni());
			statement.setString(2, persona_a_modificar.getNombre());
			statement.setString(3, persona_a_modificar.getApellido());
			
			if(statement.executeUpdate() >= 1) {
				connection.commit();
				isUpdateExitoso = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return isUpdateExitoso;
	}

	@Override
	public List<Persona> readAll() {
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
			// TODO: handle exception
		}
		
		return personas;
	}
	
	private Persona getPersona(ResultSet resultSet) throws SQLException
	{
		String dni = resultSet.getString("Dni");
		String nombre = resultSet.getString("Nombre");
		String tel = resultSet.getString("Telefono");
		return new Persona(dni, nombre, tel);
	}

	@Override
	public Persona readOne(String dni) {
		Connection connection = Conexion.getConexion().getSQLConexion();
		Persona persona = new Persona();
		
		try {
			PreparedStatement statement = connection.prepareStatement(readOne);
			statement.setString(1, dni);			
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			persona = getPersona(resultSet);
			return persona;

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
