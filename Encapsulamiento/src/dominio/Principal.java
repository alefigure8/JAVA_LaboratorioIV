package dominio;
import java.util.Arrays;

public class Principal {

	public static void main(String[] args) {
		
		Articulo[] articulos = new Articulo[3];
		articulos[0] = new Articulo("Silla");
		articulos[1] = new Articulo("Escritorio");
		articulos[2] = new Articulo("Cama");
		
			
		for (Articulo articulo : articulos) {
			System.out.println(articulo);
		}
				
		System.out.println(Articulo.proximoId());
	}
}
