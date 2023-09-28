package dominio;

public class Principal {

	public static void main(String[] args) {
		Perro perro = new Perro("Barry", "Callejero");
		Gato gato = new Gato("Pepe");
		
		System.out.println(perro.toString() + " " + perro.Sonido());
		System.out.println(gato.toString() + " " + gato.Sonido());
		
		Animal gatos[] = new Animal[5];
		gatos[0] = new Gato("Rocko");
		gatos[1] = new Perro("Rocko 2", "Callejero");
		gatos[2] = new Gato("Rocko 3");
		gatos[3] = new Perro("Rocko 4", "Boxer");
		gatos[4] = new Gato("Rocko 5");
		
		for(Animal g : gatos)
		{
			System.out.println(g.toString());
			System.out.println(g.Sonido());
		}
		
	}
}
