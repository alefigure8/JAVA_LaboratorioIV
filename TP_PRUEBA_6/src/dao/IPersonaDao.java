package dao;

import java.util.List;

import entidad.Persona;


public interface IPersonaDao 
{
	public boolean insert(Persona persona) throws Exception;
	public boolean delete(String dni) throws Exception;
	public boolean update(Persona persona_a_modificar) throws Exception;
	public List<Persona> readAll() throws Exception;
	public Boolean existePersona (String dni) throws Exception;
}
