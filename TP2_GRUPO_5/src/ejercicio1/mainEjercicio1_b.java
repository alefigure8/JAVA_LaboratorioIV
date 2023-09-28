package ejercicio1;

import java.util.Iterator;
import java.util.TreeSet;

public class mainEjercicio1_b {
	
	public static void main(String[] args) {
		
		
		// creo los profesores
		Profesor p1 = new Profesor("Lucia", 25, "Profesor", 4 );
		Profesor p2 = new Profesor("Lucia", 25, "Profesor", 4 );
		Profesor p3 = new Profesor("Iara", 26, "Profesor", 6 );
		Profesor p4 = new Profesor("Claudio", 45, "Profesor", 15 );
		Profesor p5 = new Profesor("Mel", 27, "Profesor", 8 );
		
		// creo el treeset y agrego los profesores
		TreeSet<Profesor> listaProfesores = new TreeSet<Profesor>();
		listaProfesores.add(p1);
		listaProfesores.add(p2);
		listaProfesores.add(p3);
		listaProfesores.add(p4);
		listaProfesores.add(p5);
		
		// itero el treeset
		
		Iterator<Profesor> it = listaProfesores.iterator();
		while(it.hasNext()){
			Profesor profeprofesor = it.next();
			System.out.println(profeprofesor.toString());
		}
		
		
		//Punto 7 - Creamos los profesores
		Profesor profesor1=new Profesor("Jorge Perez", 40, "Profesor titular", 10);
		Profesor profesor2=new Profesor("Jorge Perez", 40, "Profesor titular", 10);
		
		//Punto 7 - Comparamos con equals
		if (profesor1.equals(profesor2)) {
			System.out.println("Es el mismo profesor");
		}
		else {
			System.out.println("No es el mismo profesor");
		}
		
	}
}
