package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Persona;
import dominio.PersonaDaoImpI;

@WebServlet("/servletUsuario")
public class servletUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public servletUsuario() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean resultado = false;
		
			if(request.getParameter("btn") != null){
			String nombre = request.getParameter("nombre");
			String apellido = request.getParameter("apellido");
			String dni = request.getParameter("dni");	
			Persona p = new Persona(dni, nombre, apellido);
			
			//SERVIDOR
			PersonaDaoImpI pdi = new PersonaDaoImpI();
			resultado = pdi.insert(p);
		}
		
		request.setAttribute("resultado", resultado);
		RequestDispatcher rd = request.getRequestDispatcher("ejemplo1.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		doGet(request, response);
	}

}
