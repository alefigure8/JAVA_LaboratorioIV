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

import org.apache.jasper.runtime.ProtectedFunctionMapper;

import entidad.Cliente;
import entidad.Cuenta;
import entidad.TipoCuenta;
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
    HttpSession session=null;
	ArrayList<Cuenta> listadoCuentas = null;
	ArrayList<Cuenta> listaFiltrada = null;
	ArrayList<Cuenta> listaFiltradaxTipo = null;
	String [] listadoNombres = null;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession session = request.getSession();
	
		if(request.getParameter("Cuentas")!=null) {
			
			String value = request.getParameter("Cuentas");
			
			
			if(value.compareTo("CuentasClientes")==0) {
				
				
				ICuentaNegocioDao cuentaNegocio = new CuentaNegocioDaoImp ();
				IClienteNegocioDao clienteNegocio = new ClienteNegocioDaoImp();
				ArrayList<TipoCuenta> tiposCuenta = new ArrayList<TipoCuenta>(); 
				try {
					tiposCuenta = (ArrayList<TipoCuenta>) cuentaNegocio.listarTiposCuenta();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
						if(mostrandoInactivos(request, response)){ 
							listadoCuentas = (ArrayList<Cuenta>) cuentaNegocio.obtenerTodas();
							}
						else {
						
						listadoCuentas =  filtroPorActivas((ArrayList<Cuenta>)cuentaNegocio.obtenerTodas());	
						}
					}
				
				 catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				

				listadoNombres = listarNombres(listadoCuentas, clienteNegocio);	
				

				request.setAttribute("tiposCuenta",tiposCuenta);
				request.setAttribute("listadoNombres",listadoNombres);
				request.setAttribute("listadoCuentas",listadoCuentas);
				RequestDispatcher rd = request.getRequestDispatcher("/CuentasClientes.jsp");   
		        rd.forward(request, response);
		
			}
		
		}

		if(request.getParameter("btnfiltrar")!=null) {
			
			
			if(request.getParameter("TipoCuenta")!=null) {
				
				ArrayList<Cuenta> listaFiltradaxTipo  = new ArrayList<Cuenta>();
				int codigotipocuenta = Integer.valueOf(request.getParameter("TipoCuenta"));
				
				ICuentaNegocioDao cuentaNegocio = new CuentaNegocioDaoImp ();
				IClienteNegocioDao clienteNegocio = new ClienteNegocioDaoImp();
				ArrayList<TipoCuenta> tiposCuenta = new ArrayList<TipoCuenta>(); 
				
				try {
					tiposCuenta = (ArrayList<TipoCuenta>) cuentaNegocio.listarTiposCuenta();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
						if(mostrandoInactivos(request, response)){ 
							listadoCuentas = (ArrayList<Cuenta>) cuentaNegocio.obtenerTodas();
							}
						else {
						
						listadoCuentas =  filtroPorActivas((ArrayList<Cuenta>)cuentaNegocio.obtenerTodas());	
						}
					}
				
				 catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				for(Cuenta c : listadoCuentas)
				if(codigotipocuenta==c.getTipoCuenta().getId()) {
									
					listaFiltradaxTipo.add(c);
				}
				listadoNombres = listarNombres(listadoCuentas, clienteNegocio);

				request.setAttribute("tiposCuenta",tiposCuenta);
				if(codigotipocuenta==0) {
					request.setAttribute("listadoCuentas",listadoCuentas);
				}
				else {
					request.setAttribute("listadoCuentas",listaFiltradaxTipo);
				}
				request.setAttribute("listadoNombres",listadoNombres);
			
				RequestDispatcher rd = request.getRequestDispatcher("/CuentasClientes.jsp");   
		        rd.forward(request, response);
				
				
				
			}
			
			
			
		}
		
		if(request.getParameter("btnModificarCuentaCiente")!=null) {
			
			ICuentaNegocioDao cuentaNegocio = new CuentaNegocioDaoImp ();
			IClienteNegocioDao clienteNegocio = new ClienteNegocioDaoImp();
	
			
			
			try {
					if(mostrandoInactivos(request, response)){ 
						listadoCuentas = (ArrayList<Cuenta>) cuentaNegocio.obtenerTodas();
						}
					else {
					
					listadoCuentas =  filtroPorActivas((ArrayList<Cuenta>)cuentaNegocio.obtenerTodas());	
					}
				}
			
			 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			

		
		listadoNombres = listarNombres(listadoCuentas, clienteNegocio);
		
			
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
		
				try {
					
					cuentaNegocio.borrar(cuenta);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
				
				
				try {
						if(mostrandoInactivos(request, response)){ 
							listadoCuentas = (ArrayList<Cuenta>) cuentaNegocio.obtenerTodas();
							}
						else {
						
						listadoCuentas =  filtroPorActivas((ArrayList<Cuenta>)cuentaNegocio.obtenerTodas());	
						}
					}
				
				 catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				listadoNombres = listarNombres(listadoCuentas, clienteNegocio);	
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
			
		}
		
		
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		

		if(request.getParameter("btnMostrarInactivos")!=null)
		{
			HttpSession session = request.getSession();
			ICuentaNegocioDao cuentaNegocio = new CuentaNegocioDaoImp ();
			IClienteNegocioDao clienteNegocio = new ClienteNegocioDaoImp();			
			ArrayList<TipoCuenta> tiposCuenta = new ArrayList<TipoCuenta>(); 
			
			try {
				tiposCuenta = (ArrayList<TipoCuenta>) cuentaNegocio.listarTiposCuenta();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(session.getAttribute("mostrandoInactivos")==null) {
				
				
				try {
					listadoCuentas = (ArrayList<Cuenta>) cuentaNegocio.obtenerTodas();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				listadoNombres = listarNombres(listadoCuentas, clienteNegocio);
				request.setAttribute("tiposCuenta",tiposCuenta);
				request.setAttribute("mostrandoInactivos", true);
				session.setAttribute("mostrandoInactivos", true);
				request.setAttribute("listadoNombres",listadoNombres);
				request.setAttribute("listadoCuentas",listadoCuentas);			
				RequestDispatcher rd = request.getRequestDispatcher("/CuentasClientes.jsp"); 
		        rd.forward(request, response);
				
			}
			else {
					
				if(mostrandoInactivos(request, response)) {
				try {
					listadoCuentas =  filtroPorActivas((ArrayList<Cuenta>)cuentaNegocio.obtenerTodas());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	
				listadoNombres = listarNombres(listadoCuentas, clienteNegocio);	
				request.setAttribute("tiposCuenta",tiposCuenta);
				request.setAttribute("listadoNombres",listadoNombres);
				request.setAttribute("listadoCuentas",listadoCuentas);	
				request.setAttribute("mostrandoInactivos", false);
				session.setAttribute("mostrandoInactivos", false);
				RequestDispatcher rd = request.getRequestDispatcher("/CuentasClientes.jsp"); 
		        rd.forward(request, response);
			}
			else {	
				try {
					listadoCuentas = (ArrayList<Cuenta>) cuentaNegocio.obtenerTodas();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				session.setAttribute("mostrandoInactivos", false);
				request.setAttribute("tiposCuenta",tiposCuenta);
				listadoNombres = listarNombres(listadoCuentas, clienteNegocio);	
				request.setAttribute("listadoNombres",listadoNombres);
				request.setAttribute("listadoCuentas",listadoCuentas);
				request.setAttribute("mostrandoInactivos", true);
				session.setAttribute("mostrandoInactivos", true);
				RequestDispatcher rd = request.getRequestDispatcher("/CuentasClientes.jsp"); 
		        rd.forward(request, response);
			}
		
			

		
			}
					
		}
		}

	

	protected ArrayList<Cuenta> filtroPorActivas (ArrayList<Cuenta> listacompleta){
		
		ArrayList<Cuenta> auxiliar = new ArrayList<Cuenta>();
		
		for(Cuenta c : listacompleta) {
			
			if(c.isActivo()) {
				
				auxiliar.add(c);
			
			}
			
			
		}
		
		return auxiliar;
	}
	
	protected boolean mostrandoInactivos(HttpServletRequest request, HttpServletResponse response) {
		
	HttpSession session = request.getSession();
		
		if(session.getAttribute("mostrandoInactivos")!=null)
		{
			if (!(boolean) session.getAttribute("mostrandoInactivos")) {
				
				return false;
				
			}
			else {
				
				return true;
			}
		
			
		}
		return false;
	}
		
	
	protected String[] listarNombres(ArrayList<Cuenta> listadoCuentas,	IClienteNegocioDao clienteNegocio ) {
		
		listadoNombres = new String[listadoCuentas.size()];
		
		for(Cuenta c : listadoCuentas) {
			
			Cliente aux = new Cliente();
			aux = clienteNegocio.obtenerUno(c.getIdCliente());
			String nombres = aux.getNombre() + " " + aux.getApellido();
			listadoNombres[listadoCuentas.indexOf(c)] = nombres;
			 
			}
		return listadoNombres;
	}
		
	}


