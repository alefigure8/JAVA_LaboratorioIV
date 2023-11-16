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
import entidad.Movimiento;
import negocioDao.IClienteNegocioDao;
import negocioDao.ICuentaNegocioDao;
import negocioDao.IMovimientoNegocioDao;
import negocioDaoImp.ClienteNegocioDaoImp;
import negocioDaoImp.CuentaNegocioDaoImp;
import negocioDaoImp.MovimientoNegocioDaoImp;

/**
 * Servlet implementation class ServletHomeCliente
 */
@WebServlet("/ServletHomeCliente")
public class ServletHomeCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
  

	ICuentaNegocioDao negocioCuenta = new CuentaNegocioDaoImp();
	ArrayList<Cuenta> cuentasCliente = null;
	IMovimientoNegocioDao negocioMovimiento = new MovimientoNegocioDaoImp();
	ArrayList<Movimiento> movimientosCuenta = null;
	ArrayList<Movimiento> tresmovimientosCuenta = new ArrayList<Movimiento>();
	IClienteNegocioDao negocioCliente = new ClienteNegocioDaoImp();
	Cliente cliente = null;
	Cuenta cuentaVisible = null;
    public ServletHomeCliente() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		HttpSession session = request.getSession();

		if(request.getParameter("homecliente")!=null) {
			
			if(session.getAttribute("cliente")!=null) {
			cliente = (Cliente)session.getAttribute("cliente");
			
			}
			else {
			//	System.out.println("cliente nulo");
						
			}
			try {
				cuentasCliente = (ArrayList<Cuenta>)negocioCuenta.obtenerCuentasCliente(cliente.getId());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if(cuentasCliente.size()>0) {
			
				cuentaVisible = cuentasCliente.get(0);
	
			}
			if(cuentaVisible!=null) {
			
				try {
					movimientosCuenta = (ArrayList<Movimiento>) negocioMovimiento.obtenerPorCBU(cuentaVisible.getCbu());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
			
			for(Movimiento m : movimientosCuenta) {
				
				if(movimientosCuenta.indexOf(m)<3)
				tresmovimientosCuenta.add(m);
			}
			
			
			session.setAttribute("origen", "homecliente");
			session.setAttribute("numerocuentavisible", cuentaVisible.getNumeroCuenta());
			request.setAttribute("tipoCuenta",cuentaVisible.getTipoCuenta().getDescripcion());
			request.setAttribute("listaMovimientos", tresmovimientosCuenta);
			RequestDispatcher rd = request.getRequestDispatcher("/HomeClientes.jsp"); 
	        rd.forward(request, response);
		
		}
		
	
		
		
		response.getWriter().append("Served at: homecliente ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

if(request.getParameter("homecliente")!=null) {
			
			//System.out.println("acciona post");
			
			
		}
if(request.getAttribute("homecliente")!=null){
	
	//System.out.println("acciona post attribute");
	
}
		doGet(request, response);
	}

}
