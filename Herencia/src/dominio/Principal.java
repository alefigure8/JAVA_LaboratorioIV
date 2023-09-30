package dominio;

public class Principal {

	public static void main(String[] args) {
		ArticuloGimnasio artGim = new ArticuloGimnasio("Pesas", 150);

		System.out.println(artGim.toString());
		System.out.println(artGim.getName());
		System.out.println(artGim.getId());

	}

}
