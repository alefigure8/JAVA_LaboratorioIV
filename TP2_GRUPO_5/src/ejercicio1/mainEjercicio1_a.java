package ejercicio1;

import java.util.ArrayList;
import java.util.ListIterator;

public class mainEjercicio1_a {

	public static void main(String[] args) {
		
		//CREACION OBJETOS
		Profesor prof_1 = new Profesor("Pedro", 35, "Profesor", 5 );
		Profesor prof_2 = new Profesor("Pablo", 20, "Profesor", 5 );
		Profesor prof_3 = new Profesor("Oscar", 45, "Profesor", 5 );
		Profesor prof_4 = new Profesor("Ramón", 50, "Profesor", 5 );
		Profesor prof_5 = new Profesor("Carlos", 65, "Profesor", 5 );
		
		//ARRAY LIST
		ArrayList<Profesor> profesores = new ArrayList<Profesor>();
		profesores.add(prof_1);
		profesores.add(prof_2);
		profesores.add(prof_3);
		profesores.add(prof_4);
		profesores.add(prof_5);
		
		//ITERADOR
		ListIterator<Profesor> profesoresIterator = profesores.listIterator();
		
		while(profesoresIterator.hasNext())
		{
			Profesor profesor = profesoresIterator.next();
			System.out.println(profesor.toString());
		}
	}
}
