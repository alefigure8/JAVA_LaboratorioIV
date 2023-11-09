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
import negocioDao.IClienteNegocioDao;
import negocioDao.ICuentaNegocioDao;
import negocioDaoImp.ClienteNegocioDaoImp;
import negocioDaoImp.CuentaNegocioDaoImp;


@WebServlet("/ServletCuentasClientes")
public class ServletCuentasClientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletCuentasClientes() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		if(request.getParameter("Cuentas")!=null) {
			
			String value = request.getParameter("Cuentas");
			HttpSession session = request.getSession();	
			
			if(value.compareTo("CuentasClientes")==0) {
					
				ICuentaNegocioDao cuentaNegocio = new CuentaNegocioDaoImp ();
				IClienteNegocioDao clienteNegocio = new ClienteNegocioDaoImp();
				ArrayList<Cuenta> listadoCuentas = null;
				

				try {
					listadoCuentas = (ArrayList<Cuenta>) cuentaNegocio.obtenerTodas();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				String [] listadoNombres = new String[listadoCuentas.size()];
				
				for(Cuenta c : listadoCuentas) {
					Cliente aux = new Cliente();
					aux = clienteNegocio.obtenerUno(c.getIdCliente());
					String nombres = aux.getNombre() + " " + aux.getApellido();
					System.out.println("cliente: "+aux.getNombre());
					listadoNombres[listadoCuentas.indexOf(c)] = nombres;
				}
				
				request.setAttribute("listadoNombres",listadoNombres);
				request.setAttribute("listadoCuentas",listadoCuentas);
				RequestDispatcher rd = request.getRequestDispatcher("/CuentasClientes.jsp");   
		        rd.forward(request, response);

			}
		
		}
			
		
		if(request.getParameter("btnModificarCuentaCiente")!=null) {
			
			ICuentaNegocioDao cuentaNegocio = new CuentaNegocioDaoImp ();
			IClienteNegocioDao clienteNegocio = new ClienteNegocioDaoImp();
			ArrayList<Cuenta> listadoCuentas = null;
			
			try {
				listadoCuentas = (ArrayList<Cuenta>) cuentaNegocio.obtenerTodas();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String [] listadoNombres = new String[listadoCuentas.size()];
			for(Cuenta c : listadoCuentas) {
			
			Cliente aux = new Cliente();
				aux = clienteNegocio.obtenerUno(c.getIdCliente());
				String nombres = aux.getNombre() + " " + aux.getApellido();
				System.out.println("cliente: "+aux.getNombre());
				listadoNombres[listadoCuentas.indexOf(c)] = nombres;
			}
			
			request.setAttribute("listadoNombres",listadoNombres);
			request.setAttribute("listadoCuentas",listadoCuentas);
			RequestDispatcher rd = request.getRequestDispatcher("/CuentasClientes.jsp");   
	        rd.forward(request, response);
			
		}
		
			
			if(request.getParameter("btnEliminarCuenta")!=null)
			{
				int cuenta = Integer.valueOf(request.getParameter("numerocuenta"));
				ICuentaNegocioDao cuentaNegocio = new CuentaNegocioDaoImp ();
				IClienteNegocioDao clienteNegocio = new ClienteNegocioDaoImp();
				ArrayList<Cuenta> listadoCuentas = null;
				
				
				try {
					cuentaNegocio.borrar(cuenta);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try {
					listadoCuentas = (ArrayList<Cuenta>) cuentaNegocio.obtenerTodas();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String [] listadoNombres = new String[listadoCuentas.size()];
				for(Cuenta c : listadoCuentas) {
				
				Cliente aux = new Cliente();
				aux = clienteNegocio.obtenerUno(c.getIdCliente());
				String nombres = aux.getNombre() + " " + aux.getApellido();
				listadoNombres[listadoCuentas.indexOf(c)] = nombres;
				 
				}
				
				request.setAttribute("listadoNombres",listadoNombres);
				request.setAttribute("listadoCuentas",listadoCuentas);				
				
				RequestDispatcher rd = request.getRequestDispatcher("/CuentasClientes.jsp"); 
		        rd.forward(request, response);
			}
			
			if(request.getParameter("btnModificarCuenta")!=null)
			{
				int numerocuenta = Integer.valueOf(request.getParameter("numerocuenta"));
				int cliente = Integer.valueOf(request.getParameter("idcliente"));		
							
				String redirectURL = "/ServletModificarCuenta?numerocuenta=" + numerocuenta + "&idcliente=" + cliente;
				
				RequestDispatcher rDispatcher=request.getRequestDispatcher(redirectURL);
				rDispatcher.forward(request, response);
			}

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
