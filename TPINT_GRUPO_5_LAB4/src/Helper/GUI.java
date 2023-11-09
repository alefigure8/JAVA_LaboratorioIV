package Helper;

import javax.servlet.http.HttpServletRequest;

public class GUI {
	
	public static HttpServletRequest mensajes(HttpServletRequest request, String tipo, String titulo, String mensaje) {
		request.setAttribute("tipo", tipo);
		request.setAttribute("titulo", titulo);
		request.setAttribute("mensaje", mensaje);
		
		return request;
	}

}
