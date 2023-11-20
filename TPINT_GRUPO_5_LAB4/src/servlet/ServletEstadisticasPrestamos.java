package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Helper.GUI;
import dao.IPrestamosDao;
import negocioDao.IClienteNegocioDao;
import negocioDaoImp.ClienteNegocioDaoImp;
import daoImp.*;
import entidad.*;
import excepciones.RangoException;
import negocioDao.*;
import negocioDaoImp.*;
/**
 * Servlet implementation class ServletEstadisticasPrestamos
 */
@WebServlet("/ServletEstadisticasPrestamos")
public class ServletEstadisticasPrestamos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ArrayList<Prestamo> listadoTodosPrestamos = null;
	ArrayList<Prestamo>[] vectorDeListasPorMesesPrestamos= null; 
	ArrayList<Prestamo> listaFiltradaPrestamos = null;
	ArrayList  <CuotaPrestamo> listadoTodosCuotas = null;
	ArrayList  <CuotaPrestamo> listaFiltradaCuotas = null;
	ArrayList<CuotaPrestamo>[] vectordeListasdeMesesCuotas = null;
	ArrayList<Provincia> listaDeProvincias = null;
	IPrestamosNegocioDao negocioPrestamo = null;
	IProvinciaNegocioDao provinciaDao = null;
	
    public ServletEstadisticasPrestamos() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		HttpSession session = request.getSession();

		
		
		if(request.getParameter("Estadisticas")!=null) {
			
			String value = request.getParameter("Estadisticas");
				
			if(value.compareTo("Prestamos")==0) {
	
					
				limpiarSession(session);			
			
				IProvinciaNegocioDao provinciaDao = new ProvinciaNegocioDaoImp();
				negocioPrestamo 	= new PrestamosNegocioDaoImpl();	
	
				try {
					listaDeProvincias = (ArrayList<Provincia>) provinciaDao.obtenerTodas();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				listadoTodosPrestamos =	(ArrayList<Prestamo>) negocioPrestamo .obtenerTodos();
				listadoTodosCuotas= (ArrayList<CuotaPrestamo>) negocioPrestamo .obtenertodas();						
				cargarTablaxAnio(2023,session);
			
				
				session.setAttribute("listaDeProvincias", listaDeProvincias);
				RequestDispatcher rd = request.getRequestDispatcher("/EstadisticasPrestamos.jsp");   
		        rd.forward(request, response);
			
			}
			}
		
		if(request.getParameter("btnFiltrar")!=null) {
			
			
			limpiarSession(session);	
			listaFiltradaCuotas = new ArrayList<CuotaPrestamo> ();
			listaFiltradaPrestamos = new ArrayList<Prestamo> ();
			negocioPrestamo  = new PrestamosNegocioDaoImpl();	
			listadoTodosPrestamos =	(ArrayList<Prestamo>) negocioPrestamo .obtenerTodos();
			listadoTodosCuotas= (ArrayList<CuotaPrestamo>) negocioPrestamo .obtenertodas();
			IClienteNegocioDao negocioCliente = new ClienteNegocioDaoImp();
			int edadMinseleccionada = Integer.valueOf(request.getParameter("EdadMin").toString());
			int edadMaxseleccionada = Integer.valueOf(request.getParameter("EdadMax").toString());

			try {
				if(edadMaxseleccionada-edadMinseleccionada<10) {
				throw new RangoException();
				}
				if(request.getParameter("Anio")!=null) {
					int anio = Integer.valueOf(request.getParameter("Anio"));
						
					for (int i=0 ; i<listadoTodosPrestamos.size(); i++) {

						if(listadoTodosPrestamos.get(i).getFechaPrestamo().getYear()==Integer.valueOf(request.getParameter("Anio"))) {
						
										
						
						if(listadoTodosPrestamos.get(i).getIdCliente()!=1) {
						
							
							int idcliente = listadoTodosPrestamos.get(i).getIdCliente();
							Cliente cliente = negocioCliente.obtenerUno(idcliente);
			
							boolean resultadofiltro = filtroPorTipoCliente(cliente,request, response); 
							
							if(resultadofiltro) {
										
								
										listaFiltradaPrestamos.add(listadoTodosPrestamos.get(i));
							}
									
								}
							}
							


						}
					
					
					

						for (int y=0 ; y<listaFiltradaPrestamos.size(); y++) {
							
										
							ArrayList  <CuotaPrestamo> cuotasxpresamo =	(ArrayList  <CuotaPrestamo>) negocioPrestamo.obtenerCuotasxprestamo(listaFiltradaPrestamos.get(y).getId());
							
						for (CuotaPrestamo cp : cuotasxpresamo) {
							
							if(cp.getFechaVencimiento().getYear()==anio)
							listaFiltradaCuotas.add(cp);
							}
						}

					
																				
						
					cargarTablaFiltrada(session,request,response);	
						
					}
					session.setAttribute("edadMinSeleccionada",edadMinseleccionada);			
					session.setAttribute("edadMaxSeleccionada",edadMaxseleccionada);
					RequestDispatcher rd = request.getRequestDispatcher("/EstadisticasPrestamos.jsp");   
			        rd.forward(request, response);	
				
				
			}
			catch (RangoException e) {
				
				session.setAttribute("edadMinSeleccionada",edadMinseleccionada);			
				session.setAttribute("edadMaxSeleccionada",edadMaxseleccionada);
				limpiarSession(session);
				int anio = Integer.valueOf(request.getParameter("Anio"));
				cargarTablaxAnio(anio,session);
				request.setAttribute("errorRangoEdad",true);
				request = GUI.mensajes(request, "error", "Verifica el rango de edad", e.getMessage());
				
				RequestDispatcher rd = request.getRequestDispatcher("/EstadisticasPrestamos.jsp");   
		        rd.forward(request, response);
				
			}
		
			
			
						
			
			
			
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

	protected void cargarTablaxAnio(int anio,HttpSession session) {

		limpiarSession(session);
		
		double promedioTasas[] = new double[12];
		double promedioMorosos[] = new double [12];
 		int prestamosOtorgados[]= new int[12];
		int prestamosRechazados[] = new int[12];
		int prestamosCancelados[] = new int[12];
		double montoTotalOtorgado[] = new double[12]; 
		double montoAcumuladoaPagarAnual[] = new double[12];
		double pagosRecibidos[] = new double[12];
		
		// INSTANCIO VECTORES
		vectorDeListasPorMesesPrestamos = new ArrayList[12];
		vectordeListasdeMesesCuotas = new ArrayList[12];
		
		// INSTANCIO ARRAYLISTS
		for (int i=0;i<12; i++) {
		vectordeListasdeMesesCuotas[i] = new ArrayList<CuotaPrestamo>();	
		vectorDeListasPorMesesPrestamos[i] = new ArrayList<Prestamo>(); 
		montoTotalOtorgado[i] = 0; 
		montoAcumuladoaPagarAnual[i] = 0;
		}
		//System.out.println("listaPrestamos:"+listadoTodosPrestamos.size());
		// PROCESO LISTADO TOTAL PRESTAMOS
		for (Prestamo p : listadoTodosPrestamos) {
		
	
		if(p.getFechaPrestamo().getYear() == anio) {
		
		
		
		int mesIndice = p.getFechaPrestamo().getMonthValue()-1;
		vectorDeListasPorMesesPrestamos[mesIndice].add(p);
		System.out.println(p.getFechaPrestamo());
		
	
		
		}
		
		}	
		//System.out.println("listaCuotas:"+listadoTodosCuotas.size());
		//PROCESO LISTADO TOTAL CUOTAS
		for (CuotaPrestamo cp : listadoTodosCuotas) {
		
		
			
		if(cp.getFechaVencimiento().getYear() == anio) { 	
			
		
		int mesIndice = cp.getFechaVencimiento().getMonthValue()-1;
		vectordeListasdeMesesCuotas[mesIndice].add(cp);

				 			      
		}
		}
		// PROCESO VECTORES MENSUALES
		for (int i = 0; i<12;i++) {
			
			for (CuotaPrestamo c: vectordeListasdeMesesCuotas[i]) {
	
				
				if(cuotaMorosaEseMes(c, i)) {
					
				promedioMorosos[i]++;
					
				}
				//ACUMULO PAGOS RECIBIDOS POR MES
				if(isPagaEseMes(c,i)) {
			
				pagosRecibidos[i]+=c.getMontoCuota();
				}
				
				if (c.getFechaPago()==null){
				
				montoAcumuladoaPagarAnual[i] += c.getMontoCuota(); 
					
				}
				
				
			}
			// PRESTAMOS
			for (Prestamo p : vectorDeListasPorMesesPrestamos[i]) {
			
				
				if (isAceptadoEseMes(p,i)) {	
			
				// ACUMULAMOS LAS TASAS DE INTERES DE CADA MES
				promedioTasas[i] += p.getTipoTasa().getTasaInteres();		
				prestamosOtorgados[i]++;
				montoTotalOtorgado[i] += p.getMontoPedido();
				}
				if(isCanceladoEseMes(p, i)) {
					
					
					prestamosCancelados[i]++;
				}
				if(isRechazadoEseMes(p,i)) {
					
					prestamosRechazados[i]++;
				}
			
			}
		
			
		}
		
		for (int i = 0; i<12;i++) {
		
		//SACAMOS EL PROMEDIO DE LAS TASAS POR MES
		promedioTasas[i] = (promedioTasas[i]/prestamosOtorgados[i])/100;

		// PROMEDIAMOS MOROSOS
		promedioMorosos[i] = promedioMorosos[i]/vectordeListasdeMesesCuotas[i].size();		

		}
		
		
		session.setAttribute("promedioMorosos",promedioMorosos);
		session.setAttribute("pagosRecibidos",pagosRecibidos);
		session.setAttribute("promedioTasas", promedioTasas);
		session.setAttribute("prestamosOtorgados", prestamosOtorgados);
		session.setAttribute("prestamosRechazados", prestamosRechazados);
		session.setAttribute("montoTotalOtorgado", montoTotalOtorgado);
		session.setAttribute("montoAcumuladoaPagarAnual", 	montoAcumuladoaPagarAnual);
		}
	
	protected void cargarTablaFiltrada(HttpSession session,HttpServletRequest request, HttpServletResponse response) {
	
	limpiarSession(session);
	double promedioTasas[] = new double[12];
	double promedioMorosos[] = new double [12];
	int prestamosOtorgados[]= new int[12];
	int prestamosRechazados[] = new int[12];
	int prestamosCancelados[] = new int[12];
	double montoTotalOtorgado[] = new double[12]; 
	double montoAcumuladoaPagarAnual[] = new double[12];
	double pagosRecibidos[] = new double[12];
	
	// INSTANCIO VECTORES
	vectorDeListasPorMesesPrestamos = new ArrayList[12];
	vectordeListasdeMesesCuotas = new ArrayList[12];
	
	// INSTANCIO ARRAYLISTS
	for (int i=0;i<12; i++) {
	vectordeListasdeMesesCuotas[i] = new ArrayList<CuotaPrestamo>();	
	vectorDeListasPorMesesPrestamos[i] = new ArrayList<Prestamo>(); 
	montoAcumuladoaPagarAnual[i] = 0;
	}
	
	//System.out.println("listafiltradaP:"+listaFiltradaPrestamos.size());
	// PROCESO LISTA FILTRADA PRESTAMOS
	for (Prestamo p : listaFiltradaPrestamos) {
	
	
	int mesIndice = p.getFechaPrestamo().getMonthValue()-1;
	vectorDeListasPorMesesPrestamos[mesIndice].add(p);
	
	}	
	//System.out.println("listafiltradaC:"+listaFiltradaCuotas.size());
	//PROCESO LISTADO TOTAL CUOTAS
	for (CuotaPrestamo cp : listaFiltradaCuotas) {
	
	//System.out.println("Cuotas:"+cp.getFechaVencimiento());
	int mesIndice = cp.getFechaVencimiento().getMonthValue()-1;
	vectordeListasdeMesesCuotas[mesIndice].add(cp);
	}
			 			      
	
	
	// PROCESO VECTORES MENSUALES
	for (int i = 0; i<12;i++) {
		//System.out.println("vectorlistameses:"+vectordeListasdeMesesCuotas[i].size());
		for (CuotaPrestamo c: vectordeListasdeMesesCuotas[i]) {

			
			if(cuotaMorosaEseMes(c, i)) {
				
			promedioMorosos[i]++;
				
			}
			//ACUMULO PAGOS RECIBIDOS POR MES
			if(isPagaEseMes(c,i)) {
		
			pagosRecibidos[i]+=c.getMontoCuota();
			}
			
			if (c.getFechaPago()==null){
			
			
			montoAcumuladoaPagarAnual[i] =+ c.getMontoCuota(); 
			
			}
						
		}
		if(i>0) {
		montoAcumuladoaPagarAnual[i]+= montoAcumuladoaPagarAnual[i-1];
		}
		// PRESTAMOS
		//System.out.println("vectorlistaprestamos:"+vectorDeListasPorMesesPrestamos[i].size());
		for (Prestamo p : vectorDeListasPorMesesPrestamos[i]) {
		
			
			if (isAceptadoEseMes(p,i)) {	
		
			// ACUMULAMOS LAS TASAS DE INTERES DE CADA MES
			promedioTasas[i] += p.getTipoTasa().getTasaInteres();		
			prestamosOtorgados[i]++;
			montoTotalOtorgado[i] += p.getMontoPedido();
			}
			if(isCanceladoEseMes(p, i)) {
				
				
				prestamosCancelados[i]++;
			}
			if(isRechazadoEseMes(p,i)) {
				
				prestamosRechazados[i]++;
			}
		
		}
		
	}
	
	
	for (int i = 0; i<12;i++) {
	
		
		//SACAMOS EL PROMEDIO DE LAS TASAS POR MES
		promedioTasas[i] = (promedioTasas[i]/prestamosOtorgados[i])/100;
		
		// PROMEDIAMOS MOROSOS
		promedioMorosos[i] = promedioMorosos[i]/vectordeListasdeMesesCuotas[i].size();	
		System.out.println(montoAcumuladoaPagarAnual[i]);
	}
	
	
	session.setAttribute("promedioMorosos",promedioMorosos);
	session.setAttribute("pagosRecibidos",pagosRecibidos);
	session.setAttribute("promedioTasas", promedioTasas);
	session.setAttribute("prestamosOtorgados", prestamosOtorgados);
	session.setAttribute("prestamosRechazados", prestamosRechazados);
	session.setAttribute("montoTotalOtorgado", montoTotalOtorgado);
	session.setAttribute("montoAcumuladoaPagarAnual", 	montoAcumuladoaPagarAnual);
	
	}
		
		
protected boolean isAceptadoEseMes(Prestamo p,int indiceMes) {
			
			if(p.getEstado().getIdEstado()==1) {
				if(p.getFechaPrestamo().getMonthValue()-1 ==indiceMes) {
				return true;
				}}
				else {
					
					return false;
					}
				return false;
			
		}
protected boolean isCanceladoEseMes(Prestamo p, int indiceMes) {
			
	negocioPrestamo  = new PrestamosNegocioDaoImpl();
	CuotaPrestamo ultimaCuota = negocioPrestamo .obtenerUltimaCuota(p);
	if (p.isCancelado()) {
		if(ultimaCuota.getFechaPago()!=null && ultimaCuota.getFechaPago().getMonthValue()==indiceMes) 
			return true;
			else return false;
			}
	return false;
	}

protected boolean isRechazadoEseMes(Prestamo p, int indiceMes) {
	
	if(p.getEstado().getIdEstado()==3 && (p.getFechaPrestamo().getMonthValue()-1) == indiceMes) {
		return true;
	}
	else { return false;}
	
}		
		
protected boolean cuotaMorosaEseMes (CuotaPrestamo cp, int mesIndice) {
		
	 
		if(cp.getFechaPago()==null) {
		if((cp.getFechaVencimiento().getMonthValue()-1>(mesIndice+1))){
			
			return false;
			
		}else if(cp.getFechaVencimiento().getMonthValue()-1<=(mesIndice+1)) {
			
			return true;
		}
		}
		return false;
		
		
		
			
	
		}
	
protected boolean isPagaEseMes (CuotaPrestamo cp, int i) {
		
		if (cp.getFechaPago()!=null && cp.getFechaPago().getMonthValue()-1==i) { 
			return true;
		}
		else {
			return false;
		} 
			
	}
	
@SuppressWarnings("null")
protected boolean filtroPorTipoCliente (Cliente cliente,HttpServletRequest request, HttpServletResponse response) {
		
		if(request.getParameter("Sexo")!=null && request.getParameter("EdadMin")!=null &&request.getParameter("EdadMax")!=null && request.getParameter("Provincia")!=null){
		
		String sexo = request.getParameter("Sexo").toString();
		int edadMin = Integer.valueOf(request.getParameter("EdadMin").toString());
		int edadMax = Integer.valueOf(request.getParameter("EdadMax").toString());
		String Provincia = request.getParameter("Provincia").toString(); 
		String sexoCliente = cliente.getSexo(); 
		
		if(sexoCliente.equals("M")) {
			
			sexoCliente = "Masculino";
		
		}
		if(sexoCliente.equals("F")){
		
			
			sexoCliente = "Femenino";
	
		}
	
			
		Period tiempodevida = Period.between(cliente.getNacimiento(), LocalDate.now());
		int edad = tiempodevida.getYears(); 

		if(!sexoCliente.equals(sexo)) {
	
			return false;
		}
		else if (!cliente.getDireccion().getProvincia().getNombre().equals(Provincia)) {
			
			
			return false;
		}
		else if (edad<edadMin || edad>edadMax){
			return false;
		}
		else {
			
			return true;
		}
		}
		return false;
		
	}	
			
public void limpiarSession(HttpSession session) {
		

		
		session.removeAttribute("tasasInteres");
		session.removeAttribute("promedioMorosos");
		session.removeAttribute("prestamosOtorgados");
		session.removeAttribute("prestamosRechazados");
		session.removeAttribute("montoTotalOtorgado");
		session.removeAttribute("prestamosCancelados");
		session.removeAttribute("pagosRecibidos");
		session.removeAttribute("montoAcumuladoaPagarAnual");
		
		session.setAttribute("tasasInteres", null);
		session.setAttribute("promedioMorosos",null);
		session.setAttribute("prestamosOtorgados", null);
		session.setAttribute("prestamosRechazados", null);
		session.setAttribute("montoTotalOtorgado",null);
		session.setAttribute("prestamosCancelados", null);
		session.setAttribute("pagosRecibidos", null);		
		session.setAttribute("montoAcumuladoaPagarAnual",null);

		
	}

}
	
	
		
		
		
	


