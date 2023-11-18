package Helper;

import javax.servlet.http.HttpServletRequest;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

public class GUI {
	
	public static HttpServletRequest mensajes(HttpServletRequest request, String tipo, String titulo, String mensaje) {
		request.setAttribute("tipo", tipo);
		request.setAttribute("titulo", titulo);
		request.setAttribute("mensaje", mensaje);
		
		return request;
	}
	
	public static HttpServletRequest confirmar(HttpServletRequest request, String tipo, String mensaje) {
		
		request.setAttribute("confirmar", tipo);
		request.setAttribute("mensaje", mensaje);
		
		return request;
	}

}
