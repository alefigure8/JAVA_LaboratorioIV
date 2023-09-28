package ejercicio2;

import java.util.ArrayList;
import java.util.ListIterator;

public class mainEjercicio2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		Producto productoCong =  new ProductoCongelado("27/10/2026", 1, -10);
		Producto productoFres =  new ProductoFresco("20/8/2023", 2, "1/5/2023","Argentina");
		Producto productoRefri =  new ProductoRefrigerado("5/6/2024", 3, "111111");
		
		ArrayList<Producto> productos = new ArrayList<Producto>();
		
		productos.add(productoCong);
		productos.add(productoFres);
		productos.add(productoRefri);
		
		 ListIterator<Producto> iterador = productos.listIterator();

	        while (iterador.hasNext()) {
	            Producto producto = iterador.next();
	            System.out.println(producto.toString());
	        }
		
	}

}
