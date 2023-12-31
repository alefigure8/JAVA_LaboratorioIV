package servlets;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

import dominio.Seguro;
import dominio.SeguroDao;
import dominio.TipoSeguroDao;

@WebServlet("/servletListarSeguro")
public class servletListarSeguro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public servletListarSeguro() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("btnFiltrar") != null) {
        	
            String idTipoSeguro = request.getParameter("selectTipoSeguro").toString();
            SeguroDao seguroDao = new SeguroDao();

            List<Seguro> listaSeguro;

            if (!idTipoSeguro.equals("todos")) {
                // LISTAR POR TIPO DE SEGURO
                int tipoSeguroId = Integer.parseInt(idTipoSeguro);
                listaSeguro = seguroDao.obtenerSegurosPorTipo(tipoSeguroId);
                request.setAttribute("opcionselect", tipoSeguroId);
            } else {
                // LISTAR TODOS
                listaSeguro = seguroDao.obtenerTodosLosSeguros();
            }
           
            
            request.setAttribute("listaSeguros", listaSeguro);

            RequestDispatcher rd = request.getRequestDispatcher("/ListarSeguro.jsp");
            rd.forward(request, response);
        }
    }



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
