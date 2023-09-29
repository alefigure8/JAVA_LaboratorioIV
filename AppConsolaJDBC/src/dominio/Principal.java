package dominio;

import java.util.List;

public class Principal {

	public static void main(String[] args) {
		
		UsuarioDao usuarioDao = new UsuarioDao();
		/*
		Usuario usuario = new Usuario("12345676", "Elaine", "Benes");
		int filas = usuarioDao.agregarUsuario(usuario);
		
		if(filas == 1) {
			System.out.println("Se Agrego usuario");
		}else {
			System.out.println("No se pudo agregar");
		}
		*/
		
		/*
		Listar un solo usuario
		Usuario usuarioPrint = usuarioDao.obtenerUsuario("12345678");
		System.out.println(usuarioPrint.toString());
		*/
		
		//Listar todos
		/*List<Usuario> lista = usuarioDao.obtenerTodosUsuario();
		
		for(Usuario aux : lista) {
			System.out.println(aux.toString());
		}*/
		
		//Procedimientos almancenados
		Usuario usuarioAux = new Usuario("1234564", "Larry", "David");
		usuarioDao.ejecutarProcedimientoAlmacenado(usuarioAux);
		Usuario resultado = usuarioDao.obtenerUsuario("1234564");
		System.out.println(resultado.toString());
		
	}

}
