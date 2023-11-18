package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/servletAutenticacion")
public class servletAutenticacion extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public servletAutenticacion() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("sesion")!=null) {
			String value = request.getParameter("sesion");

			//Cerrar sesion
			HttpSession session = request.getSession();
			if(value.compareTo("cerrar")==0) {
				session.invalidate();
				request.setAttribute("tipo", "exito");
				request.setAttribute("titulo", "Sesion Cerrada");
				request.setAttribute("mensaje", "Sesion Cerrada correctamente");
				RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
				rd.forward(request, response);	
			}
			
			//No tiene credenciales
			if(value.compareTo("error") == 0) {
				System.out.println("entro ERROR");
				session.invalidate();
				request.setAttribute("tipo", "error");
				request.setAttribute("titulo", "No tiene permiso");
				request.setAttribute("mensaje", "No tiene permiso para acceder a la página");
				RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
				rd.forward(request, response);	
			}
			
			//No tiene Permisos suficientes
			if(value.compareTo("permiso") == 0) {
				System.out.println("entro PERMISO");
				session.invalidate();
				request.setAttribute("tipo", "error");
				request.setAttribute("titulo", "Permiso Insuficiente");
				request.setAttribute("mensaje", "No tiene los suficientes provilegios para acceder a esta página");
				RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
				rd.forward(request, response);	
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
