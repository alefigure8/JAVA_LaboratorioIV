package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Seguro;
import dominio.SeguroDao;

@WebServlet("/servletAgregarSeguro")
public class servletAgregarSeguro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public servletAgregarSeguro() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean agregado = false; 
		boolean verificarDescripcion=false; 
		boolean verificarCostos=false;
		
		if(request.getParameter("btnAceptar") != null) {
			// SE RECIBEN LOS DATOS
			String id = request.getParameter("id");
			String descripcion = request.getParameter("descripcion").trim();
			String tipoSeguro = request.getParameter("selectTipoSeguro");
			String costoContratacion = request.getParameter("contratacion").trim();
			String costoMaximo = request.getParameter("asegurado").trim();
				
			//VALIDAMOS QUE DESCRIPCION TENGA MÁXIMO 200 DE LONGITUD
				if(descripcion.length()<=200) {
					//VALIDAMOS QUE EL COSTO CONTRATADO SEA MENOR AL ASEGURADO
					if(Double.parseDouble(costoContratacion)<Double.parseDouble(costoMaximo)) {
						//SE CREA INSTANCIA
						verificarCostos=true;
						verificarDescripcion=true;
						Seguro seguro = new Seguro(Integer.parseInt(id), descripcion, Integer.parseInt(tipoSeguro), 
								Double.parseDouble(costoContratacion), Double.parseDouble(costoMaximo));
						
						SeguroDao sDao = new SeguroDao();
						agregado = sDao.insert(seguro);
						
						if(agregado) {
							request.setAttribute("agregado", true);
						}
						else {
							request.setAttribute("agregado", false);
						}	
					}
					else {
						//SI NO VALIDA RELACION DE COSTOS, SE ENVÍA MENSAJE
						request.setAttribute("verificarCostos", false);	
					}
				}
				else {
					//SI NO VALIDA LOS 200 CARACTERES, SE ENVIA MENSAJE
					request.setAttribute("verificarDescripcion", false);	
				}
				
			RequestDispatcher rDispatcher = request.getRequestDispatcher("/AgregarSeguro.jsp");
			rDispatcher.forward(request, response);
		}
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}


	
}
