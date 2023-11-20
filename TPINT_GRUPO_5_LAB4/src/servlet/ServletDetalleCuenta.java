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

import entidad.Movimiento;
import entidad.Cuenta;
import negocioDao.ICuentaNegocioDao;
import negocioDao.IMovimientoNegocioDao;
import negocioDaoImp.MovimientoNegocioDaoImp;
import negocioDaoImp.*;

/**
 * Servlet implementation class ServletDetalleCuenta
 */
@WebServlet("/ServletDetalleCuenta")
public class ServletDetalleCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       Cuenta cuenta=null;       
       ICuentaNegocioDao cuentaNegocio = new CuentaNegocioDaoImp();
       IMovimientoNegocioDao negocioMovimiento = new MovimientoNegocioDaoImp();
       ArrayList<Movimiento> listaMovimientos = new ArrayList<Movimiento>();
       ArrayList<Movimiento> listaMovimientosordenada = new ArrayList<Movimiento>();

      public ServletDetalleCuenta() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		if(request.getParameter("btnVerMovimientos")!=null) {

			if(request.getParameter("numeroCuenta")!=null) {
			
			
				try {
					cuenta = cuentaNegocio.obtenerUna(Integer.valueOf(request.getParameter("numeroCuenta").toString()));
				} catch (NumberFormatException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		
				//System.out.println(cuenta.toString());
			try {
				listaMovimientos.clear();
				listaMovimientosordenada.clear();
				listaMovimientos = (ArrayList<Movimiento>) negocioMovimiento.obtenerPorCBU(cuenta.getCbu());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for (int i = 0;i<=listaMovimientos.size()-1;i++) {
			
				System.out.println(i);
				
				Movimiento aux = new Movimiento();
				aux = listaMovimientos.get(i);	
			listaMovimientosordenada.add(aux);
			}
			
			session.setAttribute("origen", "detallecuenta");
			request.setAttribute("cuenta", cuenta);
			request.setAttribute("listaMovimientos", listaMovimientosordenada);
			RequestDispatcher rd = request.getRequestDispatcher("/DetalleCuenta.jsp"); 
	        rd.forward(request, response);
			
			}
		}
		
			if(request.getParameter("detallecuenta")!=null) {

								
				try {
					cuenta = cuentaNegocio.obtenerUna(Integer.valueOf(request.getParameter("detallecuenta").toString()));
				} catch (NumberFormatException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
				
				try {
					listaMovimientos.clear();
					listaMovimientos = (ArrayList<Movimiento>) negocioMovimiento.obtenerPorCBU(cuenta.getCbu());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				session.setAttribute("origen", "detallecuenta");
				request.setAttribute("cuenta", cuenta);
				request.setAttribute("listaMovimientos", listaMovimientos);
				RequestDispatcher rd = request.getRequestDispatcher("/DetalleCuenta.jsp"); 
		        rd.forward(request, response);
				
				}
			
			
		
		
		
		response.getWriter().append("Served at getdetalle cuenta: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
