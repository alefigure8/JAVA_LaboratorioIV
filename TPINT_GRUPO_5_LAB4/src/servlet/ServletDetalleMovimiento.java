package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import entidad.Movimiento;
import entidad.Prestamo;
import entidad.Cuenta;
import entidad.CuotaPrestamo;
import negocioDao.ICuentaNegocioDao;
import negocioDao.IMovimientoNegocioDao;
import negocioDao.IPrestamosNegocioDao;
import negocioDaoImp.MovimientoNegocioDaoImp;
import negocioDaoImp.*;

/**
 * Servlet implementation class ServletDetalleMovimiento
 */
@WebServlet("/ServletDetalleMovimiento")
public class ServletDetalleMovimiento extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	Movimiento movimiento=null;
	Prestamo prestamo = null;
	CuotaPrestamo cuota = null;
    public ServletDetalleMovimiento() {
        super();
 
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			HttpSession session = request.getSession();
		if(request.getParameter("btnVerDetalleMovimiento")!=null) {

						
				int idmovimiento = Integer.valueOf(request.getParameter("idmovimiento"));
				String ncuenta = session.getAttribute("numerocuentavisible").toString();
				RequestDispatcher rd;
				IMovimientoNegocioDao negocioMovimiento = new MovimientoNegocioDaoImp();
				IPrestamosNegocioDao negocioPrestamo = new PrestamosNegocioDaoImpl();
				
				try {
					movimiento=negocioMovimiento.obtenerUnoPorId(idmovimiento);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				String origen = session.getAttribute("origen").toString();
				String redirect="";
				if(origen.equals("homecliente")) {
					
					redirect = "/TPINT_GRUPO_5_LAB4/ServletHomeCliente?homecliente=" + ncuenta; 
				}
				else if (origen.equals("detallecuenta")) {
					
					redirect = "/TPINT_GRUPO_5_LAB4/ServletDetalleCuenta?detallecuenta=" + ncuenta;
				}
				
				request.setAttribute("redirect",redirect);
				request.setAttribute("movimiento", movimiento);
				
				switch(movimiento.getTipoMovimiento().getId()) {
				case 1:
				
				
			        
			rd = request.getRequestDispatcher("/DetalleMovimiento.jsp"); 
			rd.forward(request, response);
				break;
				case 2:
				
				prestamo = negocioPrestamo.obteneruno(movimiento.getNumeroReferencia());
				prestamo.setMontoxMes(prestamo.getMontoConIntereses()/prestamo.getTipoTasa().getCantCuotas());
				request.setAttribute("prestamo", prestamo);
				
				rd = request.getRequestDispatcher("/DetalleMovimiento.jsp"); 
		        rd.forward(request, response);
		        break;
				case 3:
				
			
				cuota = negocioPrestamo.obtenerUnaCuotaxidcuota(movimiento.getNumeroReferencia());
				
				request.setAttribute("cuota", cuota);
				
				rd = request.getRequestDispatcher("/DetalleMovimiento.jsp"); 
		        rd.forward(request, response);
				break;
				case 4:
					
					
				
					rd = request.getRequestDispatcher("/DetalleMovimiento.jsp"); 
			        rd.forward(request, response);	
					break;
				}
			
			
				
			} 
			

		
		
		response.getWriter().append("Served at servletmovimiento: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
