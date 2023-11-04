package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidad.Usuario;
import negocioImpl.ClienteNegocio;

@WebServlet("/servletLogin")
public class servletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public servletLogin() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		if(request.getParameter("btnAceptar") != null) {
			String usuario = request.getParameter("usuario");
			String contrasena = request.getParameter("pass");
			boolean existeCliente = false;
			ClienteNegocio clienteNegocio = new ClienteNegocio();

			try {
				existeCliente = clienteNegocio.existeUsuario(usuario, contrasena);
			} catch (Exception e) {
				request.setAttribute("error", e.getMessage());
				RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
				rd.forward(request, response);
			}
			
			if(existeCliente) {
				
				try {
					Usuario usuarioEncontrado = clienteNegocio.obtenerUsuarioPorUsuario(usuario);
										
					//Guardamos en Session
					HttpSession session = request.getSession(true);
					session.setAttribute("usuario", usuarioEncontrado);
					
					//Redirigimos a perfil banco
					RequestDispatcher rd = request.getRequestDispatcher("PerfilBanco.jsp");
					rd.forward(request, response);	
					
				} catch (Exception e) {
					//MANDAR MENSAJE ERROR
					request.setAttribute("error", e.getMessage());
					RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
					rd.forward(request, response);	
				}
				
			}
			
			request.setAttribute("existeCliente", existeCliente);
			RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
			rd.forward(request, response);		
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
