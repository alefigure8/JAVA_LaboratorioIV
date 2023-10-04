package negocioImpl;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import dao.IPersonaDao;
import daoImpl.PersonaDaoImpI;
import entidad.Persona;
import negocio.IPersonaNegocio;

public class PersonaNegocioImpl implements IPersonaNegocio {

	IPersonaDao pdao = new PersonaDaoImpI();
	
	
	
	@Override
	public boolean insert(Persona persona) {
		try {
			if(persona.getNombre().isEmpty() || persona.getApellido().isEmpty() || pdao.existe(persona.getDni())!=0) {
				return false;
			}
			return pdao.insert(persona);
		}
		catch(Exception e) {
			System.out.println(e.toString());
			return false;
		}
		
	}

	@Override
	public boolean delete(Persona persona_a_eliminar) {
		try {
			if(pdao.existe(persona_a_eliminar.getDni())==0) {
				return false;
			}
			return pdao.delete(persona_a_eliminar);
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Persona persona_a_modificar) {
		String nombre = persona_a_modificar.getNombre();
		String apellido = persona_a_modificar.getApellido();
		
		try {
			if(
				!nombre.isEmpty() && 
				!apellido.isEmpty() 
			) {
				return pdao.update(persona_a_modificar);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	

	@Override
	public List<Persona> readAll() {
		try {
			return pdao.readAll(); 
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Persona>();
		}
	}
	

}
