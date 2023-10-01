package negocio;

import java.util.List;
import entidad.Persona;

public interface IPersonaNegocio {
	public boolean insert(Persona persona) throws Exception;
	public boolean delete(String dni) throws Exception;
	public boolean update(Persona persona_a_modificar) throws Exception;
	public List<Persona> readAll() throws Exception;
}
