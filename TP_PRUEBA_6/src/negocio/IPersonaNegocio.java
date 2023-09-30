package negocio;

import java.util.List;
import entidad.Persona;

public interface IPersonaNegocio {
	public boolean insert(Persona persona);
	public boolean delete(String dni);
	public boolean update(Persona persona_a_modificar);
	public List<Persona> readAll();
}
