package dominio;

public class Principal {

	public static void main(String[] args) {
		
		UsuarioDao usuarioDao = new UsuarioDao();
		Usuario usuario = new Usuario("12345678", "Jerry", "Seindfeld");
		int filas = usuarioDao.agregarUsuario(usuario);
		
		if(filas == 1) {
			System.out.println("Se Agrego usuario");
		}else {
			System.out.println("No se pudo agregar");
		}
	}

}
