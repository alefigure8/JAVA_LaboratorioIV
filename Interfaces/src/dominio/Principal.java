package dominio;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.TreeSet;

public class Principal {

	public static void main(String[] args) {
		Cantante cantante = new Cantante("Roberto", "Carlos", true);		
		Pajaro pajaro = new Pajaro(15);
		Pajaro pajaro2 = pajaro;
		
		//INTERFAZ
		ICantar[] cantantes = new ICantar[2];
		cantantes[0] = cantante;
		cantantes[1] = pajaro;
		
		for(ICantar cant : cantantes)
		{
			cant.cantar();
			System.out.println(cant.toString());
		}
		
		//EQUALS
		if(pajaro.equals(pajaro2))
		{
			System.out.println("Son iguales");
		}
		else 
		{
			System.out.println("no son iguales");
		}
		
		System.out.println(pajaro.hashCode());
		System.out.println(pajaro2.hashCode());
	
		String uno = "Uno";
		String unoDos = "Uno";
		 
		System.out.println(uno.hashCode() + " " + unoDos.hashCode());
		
		Persona per = new Persona("Pedro", "Votefor");
		Persona per2 = new Persona("Pedro", "Votefor");
		
		System.out.println(per.hashCode() + " " + per2.hashCode());
		
		//LIST
		ArrayList<ICantar> cantantes2 = new ArrayList<ICantar>();
		LinkedList<ICantar> cantantes3 = new LinkedList<ICantar>();
		
		//SET
		HashSet<ICantar> cantantes4 = new HashSet<ICantar>();
		TreeSet<ICantar> cantantes5 = new TreeSet<ICantar>();
		
		ListIterator<ICantar> iterator = cantantes2.listIterator();
		
		while(iterator.hasNext())
		{
			System.out.println(iterator.toString());
		}
	}
}
