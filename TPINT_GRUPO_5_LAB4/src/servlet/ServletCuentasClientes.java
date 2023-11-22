package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

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
    
	ArrayList<Cuenta> listadoCuentas = null;
	ArrayList<Cuenta> listaFiltrada = null;
	ArrayList<Cuenta> listaFiltradaxTipo = null;
	String [] listadoNombres = null;
	int [] listadoDni=null;
	
	ICuentaNegocioDao cuentaNegocio = new CuentaNegocioDaoImp ();
	IClienteNegocioDao clienteNegocio = new ClienteNegocioDaoImp();
	ArrayList<TipoCuenta> tiposCuenta = new ArrayList<TipoCuenta>(); 
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession session = request.getSession();
		
		if(request.getParameter("btnLimpiarFiltros")!=null && request.getParameter("btnfiltrar")==null) {
			
			limpiarSession(session);

			try {
				tiposCuenta = (ArrayList<TipoCuenta>) cuentaNegocio.listarTiposCuenta();
				listadoCuentas = (ArrayList<Cuenta>) cuentaNegocio.obtenerTodas();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
						

			listadoNombres = listarNombres(listadoCuentas, clienteNegocio);	
			listadoDni=listarDni(listadoCuentas, clienteNegocio);	

			session.setAttribute("estadoCuentaSeleccionado","Todas");
			session.setAttribute("estadoCuentaSeleccionado","Todas");
			request.setAttribute("tiposCuenta",tiposCuenta);
			request.setAttribute("listadoNombres",listadoNombres);
			request.setAttribute("listadoDni",listadoDni);
			request.setAttribute("listadoCuentas",listadoCuentas);
			RequestDispatcher rd = request.getRequestDispatcher("/CuentasClientes.jsp");   
	        rd.forward(request, response);
			
		}
		
		if(request.getParameter("Cuentas")!=null && request.getParameter("btnLimpiarFiltros")==null && request.getParameter("btnfiltrar")==null) {
			
			limpiarSession(session);
		
			
				try {
					tiposCuenta = (ArrayList<TipoCuenta>) cuentaNegocio.listarTiposCuenta();
					listadoCuentas = (ArrayList<Cuenta>) cuentaNegocio.obtenerActivas();
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
							

				listadoNombres = listarNombres(listadoCuentas, clienteNegocio);	
				listadoDni=listarDni(listadoCuentas, clienteNegocio);	
				session.setAttribute("estadoCuentaSeleccionado","Solo Activas");
				request.setAttribute("tiposCuenta",tiposCuenta);
				request.setAttribute("listadoNombres",listadoNombres);
				request.setAttribute("listadoDni",listadoDni);
				request.setAttribute("listadoCuentas",listadoCuentas);
				RequestDispatcher rd = request.getRequestDispatcher("/CuentasClientes.jsp");   
		        rd.forward(request, response);
		
			//}
		
		}

		if(request.getParameter("btnfiltrar")!=null && request.getParameter("btnLimpiarFiltros")==null) {
			
			ArrayList<Cuenta> listaFiltradaxTipo  = new ArrayList<Cuenta>();					
			ICuentaNegocioDao cuentaNegocio = new CuentaNegocioDaoImp ();
			IClienteNegocioDao clienteNegocio = new ClienteNegocioDaoImp();
			ArrayList<TipoCuenta> tiposCuenta = new ArrayList<TipoCuenta>(); 
			listadoCuentas.clear();
				
			try {
				tiposCuenta = (ArrayList<TipoCuenta>) cuentaNegocio.listarTiposCuenta();
			} catch (SQLException e1) {
					e1.printStackTrace();
			}
				
			
				
				// ESTADO CUENTAS			
				if(request.getParameter("EstadoCuenta")!=null) {
					
					String estadoCuentaSeleccionado = request.getParameter("EstadoCuenta").toString();
					session.setAttribute("estadoCuentaSeleccionado",estadoCuentaSeleccionado);
					
					try {
					if(request.getParameter("EstadoCuenta").toString().equals("Todas")) {
						System.out.println(request.getParameter("EstadoCuenta").toString());
						listadoCuentas = (ArrayList<Cuenta>) cuentaNegocio.obtenerTodas();
						
						
					}else if (request.getParameter("EstadoCuenta").toString().equals("Solo Activas")){
						
						
						listadoCuentas = (ArrayList<Cuenta>) cuentaNegocio.obtenerActivas();
						
						
						}else {
							
						listadoCuentas =(ArrayList<Cuenta>) cuentaNegocio.obtenerTodas();
						listadoCuentas = filtroPorInactivas(listadoCuentas);
						}
					}catch(SQLException sql){}
					
					
						
				}
				
				//TIPOS DE CUENTA
				String tipocuenta = null;
				if(request.getParameter("TipoCuenta")!=null) {
					tipocuenta = request.getParameter("TipoCuenta").toString();
					session.setAttribute("tipoCuentaSeleccionado", tipocuenta);
				}
				
				
				//IMPORTE
				String importeSeleccionado=null;
				if(request.getParameter("Importes")!=null) {
					importeSeleccionado=request.getParameter("Importes");
					session.setAttribute("rangoImportesSeleccionado",importeSeleccionado);
				}
				String montoImporte=null;
				if(request.getParameter("rangoImporte")!=null && !request.getParameter("rangoImporte").isEmpty()) {
					montoImporte=request.getParameter("rangoImporte");
				}
				
				//FECHAS
				String fechaDesde=null;
				String fechaHasta=null;
				if(request.getParameter("cuentaDesde")!=null && !request.getParameter("cuentaDesde").isEmpty()) {
					fechaDesde=request.getParameter("cuentaDesde");
					session.setAttribute("fechadesdeSeleccionada",fechaDesde);
				}
				if(request.getParameter("cuentaHasta")!=null && !request.getParameter("cuentaHasta").isEmpty()) {
					fechaHasta=request.getParameter("cuentaHasta");
					session.setAttribute("fechahastaSeleccionada", fechaHasta);
				}
				
		
				for(Cuenta c : listadoCuentas) {
				//if(codigotipocuenta==c.getTipoCuenta().getId()) {
									
					//Tipos cuenta
					if(tipocuenta!=null) {
			
						if(!tipocuenta.equals("Todas") && !tipocuenta.equals(c.getTipoCuenta().getDescripcion())) {
							
							continue;
						}
						
					}
					
								
					//Importe
					if(importeSeleccionado!=null && montoImporte!=null) {
						Double monto=Double.parseDouble(montoImporte);
						switch (importeSeleccionado) {
						case "Mayor a":
							if(c.getSaldo()<=monto) {
								continue;
							}
							break;
						case "Igual a":
							if(c.getSaldo()!=monto) {
								continue;
							}
							break;
						case "Menor a":
							if(c.getSaldo()>=monto) {
								continue;
							}
							break;

						}
					}
					
					//Fechas
					
					if(fechaDesde!=null && fechaHasta!=null) {
						try {
					        LocalDate fechaPrestamo = c.getFechaCreacion();
					        LocalDate desde = LocalDate.parse(fechaDesde);
					        LocalDate hasta = LocalDate.parse(fechaHasta);
					        //System.out.println("FECHA PRESTAMO " + fechaPrestamo);

					        if (fechaPrestamo.isBefore(desde) || fechaPrestamo.isAfter(hasta)) {
					            continue;
					        }
					    } catch (DateTimeParseException e) {
					        e.printStackTrace();
					    }
						
					}
					
					listaFiltradaxTipo.add(c);
				}
				
						
				
				
				listadoNombres = listarNombres(listaFiltradaxTipo, clienteNegocio);
				listadoDni = listarDni(listaFiltradaxTipo, clienteNegocio);
				request.setAttribute("listadoCuentas",listaFiltradaxTipo);
				request.setAttribute("tiposCuenta",tiposCuenta);		
				request.setAttribute("listadoNombres",listadoNombres);
				request.setAttribute("listadoDni",listadoDni);
			
				
				
				RequestDispatcher rd = request.getRequestDispatcher("/CuentasClientes.jsp");   
		        rd.forward(request, response);
				
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
				
				e.printStackTrace();
			}
			

		
			listadoNombres = listarNombres(listadoCuentas, clienteNegocio);
			listadoDni=listarDni(listadoCuentas, clienteNegocio);

			
			request.setAttribute("listadoNombres",listadoNombres);
			request.setAttribute("listadoDni",listadoDni);
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
					listadoCuentas = (ArrayList<Cuenta>) cuentaNegocio.obtenerTodas();
				} catch (SQLException e1) {
					
					e1.printStackTrace();
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
	
	protected ArrayList<Cuenta> filtroPorInactivas (ArrayList<Cuenta> listacompleta){
		
		ArrayList<Cuenta> auxiliar = new ArrayList<Cuenta>();
		
		for(Cuenta c : listacompleta) {
			
			if(!c.isActivo()) {
				
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
	
	protected int[] listarDni(ArrayList<Cuenta> listadoCuentas,	IClienteNegocioDao clienteNegocio ) {
			
		listadoDni = new int[listadoCuentas.size()];
			
			for(Cuenta c : listadoCuentas) {
				
				Cliente aux = new Cliente();
				aux = clienteNegocio.obtenerUno(c.getIdCliente());
				int dni= aux.getDni();
				listadoDni[listadoCuentas.indexOf(c)]=dni;
				
				}
			return listadoDni;
		}
		protected void limpiarSession( HttpSession session) {
			session.removeAttribute("fechahastaSeleccionada");
			session.setAttribute("fechahastaSeleccionada", null);
			session.removeAttribute("fechadesdeSeleccionada");
			session.setAttribute("fechadesdeSeleccionada",null);
			session.removeAttribute("rangoImportesSeleccionado");
			session.setAttribute("rangoImportesSeleccionado",null);
			session.removeAttribute("tipoCuentaSeleccionado");
			session.setAttribute("tipoCuentaSeleccionado", null);
		}
	}


