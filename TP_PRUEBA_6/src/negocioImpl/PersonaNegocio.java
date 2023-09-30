package negocioImpl;

import java.util.List;

import dao.IPersonaDao;
import daoImpl.PersonaDao;
import entidad.Persona;
import negocio.IPersonaNegocio;

public class PersonaNegocio implements IPersonaNegocio {
	IPersonaDao personaDao = new PersonaDao();

	@Override
	public boolean insert(Persona persona) {
		
		if(personaDao.readOne(persona.getDni()) != null ) {
			return false;
		}
		
		if(
			persona.getDni().trim().isEmpty() || persona.getDni() == null ||
			persona.getNombre().trim().isEmpty() || persona.getNombre() == null ||
			persona.getApellido().trim().isEmpty() || persona.getApellido() == null
		) {
			return false;
		}
		
		return personaDao.insert(persona);
	}

	@Override
	public boolean delete(String dni) {
		
		if(dni.trim().isEmpty() || dni == null) {
			return false;
		}
		
		return personaDao.delete(dni);
	}

	@Override
	public boolean update(Persona persona_a_modificar) {
		
		if(personaDao.readOne(persona_a_modificar.getDni()) == null ) {
			return false;
		}
		
		if(
			persona_a_modificar.getDni().trim().isEmpty() || persona_a_modificar.getDni() == null ||
			persona_a_modificar.getNombre().trim().isEmpty() || persona_a_modificar.getNombre() == null ||
			persona_a_modificar.getApellido().trim().isEmpty() || persona_a_modificar.getApellido() == null
		) {
			return false;
		}
		
		return personaDao.update(persona_a_modificar);
	}

	@Override
	public List<Persona> readAll() {
		return personaDao.readAll();
	}
	
}
