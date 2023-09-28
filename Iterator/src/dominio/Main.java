package dominio;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import org.omg.CosNaming._BindingIteratorImplBase;

public class Main {

	public static void main(String[] args) {
		
		Persona persona_1 = new Persona("Pedro", "Sanchez", 35);
		Persona persona_2 = new Persona("Pablo", "Gomez", 35);
		Persona persona_3 = new Persona("Javier", "Milei", 35);
		Persona persona_4 = new Persona("Ramón", "Rallo", 35);
		Persona persona_5 = new Persona("Pedro", "VoteFor", 35);
		
		//LINKED LIST
		LinkedList<Persona> personas = new LinkedList<Persona>();
		personas.add(persona_1);
		personas.add(persona_2);
		personas.add(persona_3);
		personas.add(persona_4);
		personas.add(persona_5);
		
		//HASHSET
		Set<Persona> personasHash = new HashSet<Persona>();
		personasHash.add(persona_1);
		personasHash.add(persona_2);
		personasHash.add(persona_3);
		personasHash.add(persona_4);
		personasHash.add(persona_5);
		
				
		Iterator<Persona> it;
		it = personasHash.iterator();
		
		while(it.hasNext())
		{
			Persona persona = it.next();
			System.out.println(persona.getNombre());
			
			if(persona.getNombre().equals("Pedro"))
			{
				it.remove();				
			}
		}
		
		System.out.println("*******************************");
		
		ArrayList<Persona> personasList = new ArrayList<Persona>();
		personasList.add(persona_1);
		personasList.add(persona_2);
		personasList.add(persona_3);
		personasList.add(persona_4);
		personasList.add(persona_5);
		
		Boolean estaPersona = false;
		
		if(!personasList.isEmpty())
		{
			estaPersona = personasList.contains(persona_1);	
		}
		
		if(estaPersona)
		{
			int index = personasList.indexOf(persona_1);
			System.out.println(personasList.get(index));
		}

	}

}
