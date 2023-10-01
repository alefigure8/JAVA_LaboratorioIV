package negocioImpl;

import java.util.List;

import dao.IPersonaDao;
import daoImpl.PersonaDao;
import entidad.Persona;
import negocio.IPersonaNegocio;

public class PersonaNegocio implements IPersonaNegocio {
	IPersonaDao personaDao = new PersonaDao();

	@Override
	public boolean insert(Persona persona) throws Exception {
		
		try {
			//VERIFICAR SI EXISTE
			Boolean existe = personaDao.existePersona(persona.getDni());
			if(existe) {
				throw new Exception("El usaurio ya existe");
			}
			
			//VERIFICAR CAMPOS VACIOS
			if(
				persona.getDni().trim().isEmpty() || persona.getDni() == null ||
				persona.getNombre().trim().isEmpty() || persona.getNombre() == null ||
				persona.getApellido().trim().isEmpty() || persona.getApellido() == null
			) {
				throw new Exception("Todos los campos son obligatorios");
			}
			
			return personaDao.insert(persona);
			
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public boolean delete(String dni) throws Exception{
		try {

			if(dni.trim().isEmpty() || dni == null) {
				return false;
			}
			
			return personaDao.delete(dni);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public boolean update(Persona persona_a_modificar) throws Exception {
		
		try {
			if(!personaDao.existePersona(persona_a_modificar.getDni())) {
				throw new Exception("El usuario no existe");
			}
			
			if(
				persona_a_modificar.getNombre().trim().isEmpty() || persona_a_modificar.getNombre() == null ||
				persona_a_modificar.getApellido().trim().isEmpty() || persona_a_modificar.getApellido() == null
			) {
				throw new Exception("Todos los campos son obligatorios");
			}
			
			return personaDao.update(persona_a_modificar);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<Persona> readAll() throws Exception{
		try {
			return personaDao.readAll();
		} catch (Exception e) {
			throw e;
		}
	}
	
}
