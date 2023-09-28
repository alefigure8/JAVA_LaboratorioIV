package ejercicio3;

import java.util.ArrayList;
import java.util.Iterator;

public class mainEjercicio3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		//creamos 3 polideportivos y 2 edificios de oficinas 
		ArrayList<Edificio>edificios=new ArrayList<>();
		edificios.add(new Polideportivo("Poli A", 3000));
		edificios.add(new Polideportivo("Poli B", 3500));
		edificios.add(new Polideportivo("Poli C", 3000));
		edificios.add(new EdificioOficinas(1500, 10));
		edificios.add(new EdificioOficinas(1000, 5));
		
		
		//iteramos los objetos creados y mostramos por consola
		Iterator<Edificio> it=edificios.iterator();
		while(it.hasNext()) {
			Edificio edificio=it.next();
			System.out.println(edificio.toString());
			
		}
		
	}

}
