package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
  

    public ServletHomeCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ICuentaNegocioDao negocioCuenta = new CuentaNegocioDaoImp();
		ArrayList<Cuenta> cuentasCliente = new ArrayList<Cuenta>();
		IMovimientoNegocioDao negocioMovimiento = new MovimientoNegocioDaoImp();
		ArrayList<Movimiento> movimientosCuenta = new ArrayList<Movimiento>();
		ArrayList<Movimiento> tresmovimientosCuenta = new ArrayList<Movimiento>();
		
		Cliente cliente = null;
		Cuenta cuentaVisible = null;
		
		HttpSession session = request.getSession();
		
		if(request.getParameter("homecliente")!=null) {
			
			/* Recuperamos clinte de Session*/
			if(session.getAttribute("cliente")!=null) {
				cliente = (Cliente)session.getAttribute("cliente");
			}
		
			try {
				/* Obtenemos cuentas activas */
				cuentasCliente = (ArrayList<Cuenta>)negocioCuenta.obtenerCuentasActivasCliente(cliente.getId());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if(cuentasCliente.size()>0) {
				cuentaVisible = cuentasCliente.get(0);
			}
			
			if(cuentaVisible!=null) {
				try {
					movimientosCuenta = (ArrayList<Movimiento>) negocioMovimiento.obtenerPorCBU(cuentaVisible.getCbu());
					tresmovimientosCuenta.clear();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(movimientosCuenta.size() > 0) {
					
					List<Movimiento> movimientos=new ArrayList<Movimiento>();
					try {
						movimientos = negocioMovimiento.obtenerUltimosTresMovimientos(cuentaVisible.getCbu());
						
					} catch (SQLException e) {
						
						e.printStackTrace();
					}
					movimientos.sort(Comparator.comparingInt(Movimiento::getId).reversed());
					tresmovimientosCuenta.clear();
					for (Movimiento movimiento : movimientos) {
					    tresmovimientosCuenta.add(movimiento);
					}
					/*if(cantidad >=3) {
						for(int i = 0; i<3;i++) {
							Movimiento aux = new Movimiento();
							aux = movimientosCuenta.get(i);
							tresmovimientosCuenta.add(aux);
						}
					}
					else if(cantidad <3) {
						for(int i = 0; i<cantidad;i++) {
							Movimiento aux = new Movimiento();
							aux = movimientosCuenta.get(i);
							tresmovimientosCuenta.add(aux);
						}
					}*/
					
				}
				
				request.setAttribute("cuentaVisible", cuentaVisible);
				session.setAttribute("origen", "homecliente");
				session.setAttribute("numerocuentavisible", cuentaVisible.getNumeroCuenta());
				request.setAttribute("tipoCuenta",cuentaVisible.getTipoCuenta().getDescripcion());
				request.setAttribute("listaMovimientos", tresmovimientosCuenta);
				
				RequestDispatcher rd = request.getRequestDispatcher("/HomeClientes.jsp"); 
		        rd.forward(request, response);
			} else {
				/* Si no hay cuenta */
				session.setAttribute("origen", "homecliente");
				RequestDispatcher rd = request.getRequestDispatcher("/HomeClientes.jsp"); 
		        rd.forward(request, response);
			}

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
