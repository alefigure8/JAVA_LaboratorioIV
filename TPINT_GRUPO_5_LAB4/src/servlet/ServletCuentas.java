package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidad.Cliente;
import entidad.Cuenta;
import entidad.Usuario;
import negocioDao.ICuentaNegocioDao;
import negocioDaoImp.CuentaNegocioDaoImp;

/**
 * Servlet implementation class ServletCuentas
 */
@WebServlet("/ServletCuentas")
public class ServletCuentas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCuentas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Cliente cliente = new Cliente();
		ICuentaNegocioDao negocioCuenta = new CuentaNegocioDaoImp();
		ArrayList<Cuenta> cuentasCliente = new ArrayList<Cuenta>();

		HttpSession session = request.getSession(); 
		
		if(request.getParameter("Cuentas")!=null) {
			
			if(session.getAttribute("cliente")!=null) {
				cliente = (Cliente)session.getAttribute("cliente");
				
				}
				
			
			try {
				cuentasCliente = (ArrayList<Cuenta>) negocioCuenta.obtenerCuentasActivasCliente(cliente.getId());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.setAttribute("cuentasCliente", cuentasCliente);
			RequestDispatcher rd = request.getRequestDispatcher("/Cuentas.jsp"); 
	        rd.forward(request, response);
			
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
