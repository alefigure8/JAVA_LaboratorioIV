package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Seguro;

@WebServlet("/servletAgregarSeguro")
public class servletAgregarSeguro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public servletAgregarSeguro() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("btnAceptar") != null) {
			
			String id = request.getParameter("id");
			String descripcion = request.getParameter("descripcion");
			String tipoSeguro = request.getParameter("selectTipoSeguro");
			String costoContratacion = request.getParameter("contratacion");
			String costoMaximo = request.getParameter("asegurado");
					
			//SE CREA INSTANCIA
			Seguro seguro = new Seguro(Integer.parseInt(id), descripcion, Integer.parseInt(tipoSeguro), Double.parseDouble(costoContratacion), Double.parseDouble(costoMaximo));
			
			//INSERTAR EN DB
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
