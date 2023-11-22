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
	
	ArrayList<Prestamo> listadoTodosPrestamos = new ArrayList<Prestamo> ();


	ArrayList<Prestamo> listaFiltradaPrestamos = new ArrayList<Prestamo> ();
	ArrayList  <CuotaPrestamo> listadoTodosCuotas = new ArrayList<CuotaPrestamo> ();
	ArrayList  <CuotaPrestamo> listaFiltradaCuotas = new ArrayList<CuotaPrestamo> ();
	ArrayList<CuotaPrestamo>[] vectordeListasdeMesesCuotas = new ArrayList[12];
	ArrayList<Prestamo>[] vectorDeListasPorMesesPrestamos = new ArrayList[12];
	ArrayList<Provincia> listaDeProvincias = null;
	IPrestamosNegocioDao negocioPrestamo = new PrestamosNegocioDaoImpl();;
	IProvinciaNegocioDao provinciaDao = new ProvinciaNegocioDaoImp();
	IClienteNegocioDao negocioCliente = new ClienteNegocioDaoImp();
	double promedioTasas[] = new double[12];
	double promedioMorosos[] = new double [12];
	int prestamosOtorgados[]= new int[12];
	int prestamosRechazados[] = new int[12];
	double montoTotalOtorgado[] = new double[12]; 
	int pagosRecibidos[] = new int[12];
	double sumaGananciaenIntereses[] = new double[12];
	double sumaEnPagosCuotas[] = new double[12];
	double sumaEnMora[] = new double[12];
	int cuotasMorosas[] = new int[12];
	
    public ServletEstadisticasPrestamos() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		HttpSession session = request.getSession();
		
		// LOAD
		if(request.getParameter("Estadisticas")!=null) {
			
			limpiarSession(session);
			String value = request.getParameter("Estadisticas");
				
			if(value.compareTo("Prestamos")==0) {	
				
				session.removeAttribute("provinciaSeleccionada");	
				session.setAttribute("provinciaSeleccionada",null);
				session.removeAttribute("sexoSeleccionado");	
				session.setAttribute("sexoSeleccionado",null);
				session.removeAttribute("anio");	
				session.setAttribute("anio",null);
				session.removeAttribute("edadMinSeleccionada");	
				session.removeAttribute("edadMaxSeleccionada");	
				session.setAttribute("edadMinSeleccionada",null);			
				session.setAttribute("edadMaxSeleccionada",null);
				
				try {
					listaDeProvincias = (ArrayList<Provincia>) provinciaDao.obtenerTodas();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				listadoTodosCuotas.clear();
				listadoTodosPrestamos.clear();
				listadoTodosPrestamos =	(ArrayList<Prestamo>) negocioPrestamo .obtenerTodos();								
				listadoTodosCuotas= (ArrayList<CuotaPrestamo>) negocioPrestamo .obtenertodas();				
				cargarTablaxAnio(2023,session, listadoTodosPrestamos,listadoTodosCuotas);
			
				session.setAttribute("listaDeProvincias", listaDeProvincias);
				RequestDispatcher rd = request.getRequestDispatcher("/EstadisticasPrestamos.jsp");   
		        rd.forward(request, response);
			
			}
			}
		if(request.getParameter("btnReestablecer")!=null) {
			
			session.removeAttribute("provinciaSeleccionada");	
			session.setAttribute("provinciaSeleccionada",null);
			session.removeAttribute("sexoSeleccionado");	
			session.setAttribute("sexoSeleccionado",null);
			session.removeAttribute("anio");	
			session.setAttribute("anio",null);
			session.removeAttribute("edadMinSeleccionada");	
			session.removeAttribute("edadMaxSeleccionada");	
			session.setAttribute("edadMinSeleccionada",null);			
			session.setAttribute("edadMaxSeleccionada",null);
			
			try {
				listaDeProvincias = (ArrayList<Provincia>) provinciaDao.obtenerTodas();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			listadoTodosCuotas.clear();
			listadoTodosPrestamos.clear();
			listadoTodosPrestamos =	(ArrayList<Prestamo>) negocioPrestamo .obtenerTodos();								
			listadoTodosCuotas= (ArrayList<CuotaPrestamo>) negocioPrestamo .obtenertodas();				
			cargarTablaxAnio(2023,session, listadoTodosPrestamos,listadoTodosCuotas);
		
			session.setAttribute("listaDeProvincias", listaDeProvincias);
			RequestDispatcher rd = request.getRequestDispatcher("/EstadisticasPrestamos.jsp");   
	        rd.forward(request, response);
			
			
		}
		// BOTON FILTRAR
		if(request.getParameter("btnFiltrar")!=null) {
					
			if(request.getParameter("Sexo")!=null && request.getParameter("EdadMin")!=null &&request.getParameter("EdadMax")!=null && request.getParameter("Provincia")!=null){
			
			int anio;
			limpiarSession(session);	
			
			listadoTodosCuotas.clear();
			listadoTodosPrestamos.clear();
			listaFiltradaCuotas.clear();
			listaFiltradaPrestamos.clear();
			listadoTodosPrestamos =	(ArrayList<Prestamo>) negocioPrestamo .obtenerTodos();
			listadoTodosCuotas= (ArrayList<CuotaPrestamo>) negocioPrestamo .obtenertodas();	
			int edadMinseleccionada = Integer.valueOf(request.getParameter("EdadMin").toString());

			int edadMaxseleccionada = Integer.valueOf(request.getParameter("EdadMax").toString());
	
		
			
			String sexoSeleccionado = request.getParameter("Sexo").toString();
			session.setAttribute("sexoSeleccionado",sexoSeleccionado);
			String provinciaSeleccionada = request.getParameter("Provincia").toString();
			session.setAttribute("provinciaSeleccionada",provinciaSeleccionada);
			anio = Integer.valueOf(request.getParameter("Anio"));
			session.setAttribute("anioSeleccionado",anio);
			
			try {
				if(edadMaxseleccionada-edadMinseleccionada<10) {
				throw new RangoException();
				}				
											
				for (int i=0 ; i<listadoTodosPrestamos.size(); i++) {


										
				//OBTENEMOS EL CLIENTE QUE LO SOLICITO
				int idcliente = listadoTodosPrestamos.get(i).getIdCliente();
				Cliente cliente = negocioCliente.obtenerUno(idcliente);
	
				
				boolean resultadofiltro = filtroPorTipoCliente(cliente,sexoSeleccionado,edadMinseleccionada,edadMaxseleccionada, provinciaSeleccionada); 

							// SI EL CLIENTE COINCIDE ENTRA A LISTA FILTRADA
							if(resultadofiltro) {
										
								ArrayList  <CuotaPrestamo> cuotasxprestamo = new ArrayList<CuotaPrestamo>();
								Prestamo prestamo = new Prestamo();
								prestamo = listadoTodosPrestamos.get(i);						
									
								
								for (CuotaPrestamo cp : listadoTodosCuotas) {
									
										
									if(cp.getFechaVencimiento().getYear()==anio && cp.getIdPrestamo()==prestamo.getId()) {
										
										listaFiltradaCuotas.add(cp);
									}
								}
								
								// OBTENEMOS LOS PRESTAMOS DEL AÑO SELECCIONADO
								if(listadoTodosPrestamos.get(i).getFechaPrestamo().getYear()==anio) {
								
								listaFiltradaPrestamos.add(prestamo);
																
								// CARGAMOS LA LISTA FILTRADA DE CUOTAS
								}
							}
									
					
							}	
				
			
				cargarTablaxAnio(anio, session,listaFiltradaPrestamos,listaFiltradaCuotas);	
					
				    session.setAttribute("anio", anio);
					session.setAttribute("edadMinSeleccionada",edadMinseleccionada);			
					session.setAttribute("edadMaxSeleccionada",edadMaxseleccionada);
					RequestDispatcher rd = request.getRequestDispatcher("/EstadisticasPrestamos.jsp");   
			        rd.forward(request, response);	
				
			}
				
				
			
			catch (RangoException e) {
				
				session.setAttribute("edadMinSeleccionada",20);			
				session.setAttribute("edadMaxSeleccionada",80);
				limpiarSession(session);
				cargarTablaxAnio(2023,session, listadoTodosPrestamos,listadoTodosCuotas);
				request.setAttribute("errorRangoEdad",true);
				request = GUI.mensajes(request, "error", "Verifica el rango de edad", e.getMessage());
				
				RequestDispatcher rd = request.getRequestDispatcher("/EstadisticasPrestamos.jsp");   
		        rd.forward(request, response);
				
			}			
			}
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

	protected void cargarTablaxAnio(int anio,HttpSession session, 	ArrayList<Prestamo> listadoPrestamos, 	ArrayList  <CuotaPrestamo> listaCuotas) {

		
		InicializarVectoresdeCalculos();
		CargarVectoresMensuales(anio, listadoPrestamos, listaCuotas);
		CargarVectoresdeCalculos();
		CargarSession(session); 
		
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
protected boolean isRechazadoEseMes(Prestamo p, int indiceMes) {
	
	if(p.getEstado().getIdEstado()==3 && (p.getFechaPrestamo().getMonthValue()-1) == indiceMes) {
		return true;
	}
	else { return false;}
	
}		
protected boolean isMorosa (CuotaPrestamo cp) {

	
	if(cp.getFechaPago()!=null) {
		
		if(!cp.getFechaVencimiento().isAfter(cp.getFechaPago())){
		return false;
		}
		else {
			return true;	
		}
	} 
	
	
	return true;	
	}	
@SuppressWarnings("null")
protected boolean filtroPorTipoCliente (Cliente cliente, String sexo, int edadMin,int edadMax,String provincia) {
		
		
		String sexoCliente="";
		Period tiempodevida = Period.between(cliente.getNacimiento(), LocalDate.now());
		int edad = tiempodevida.getYears();	
	
		if(cliente.getSexo().equals("M")) {
			
			sexoCliente = "Masculino";		
		}
		else{
				
			sexoCliente = "Femenino";
	
		}
		System.out.println("sexoCliente" +sexoCliente);
		System.out.println(sexo);
		
		if(!sexoCliente.equals(sexo)&&!sexo.equals("Indistinto")) {
	
			return false;
		}
		
		System.out.println(cliente.getDireccion().getProvincia().getNombre());
		if(!provincia.equals("Todas")) {
		if (!cliente.getDireccion().getProvincia().getNombre().equals(provincia)) {
					
			return false;
		}
		}
		if (edad<edadMin || edad>edadMax){
			return false;
		}
		
			
			return true;		
		
	}	
public void limpiarSession(HttpSession session) {
		

	session.removeAttribute("sumaEnPagosCuotas");	
	session.setAttribute("sumaEnPagosCuotas",null);
		session.removeAttribute("promedioMorosos");	
	session.setAttribute("promedioMorosos",null);
	session.removeAttribute("tasasInteres");
	session.setAttribute("tasasInteres", null);	
		session.removeAttribute("prestamosOtorgados");
		session.setAttribute("prestamosOtorgados", null);		
		session.removeAttribute("montoTotalOtorgado");
		session.setAttribute("montoTotalOtorgado",null);
		session.removeAttribute("pagosRecibidos");
		session.setAttribute("pagosRecibidos", null);
		session.removeAttribute("sumaGananciaenIntereses");
		session.setAttribute("sumaGananciaenIntereses",null);
		session.removeAttribute("prestamosRechazados");		
		session.setAttribute("prestamosRechazados", null);
		session.removeAttribute("sumaEnMora");		
		session.setAttribute("sumaEnMora",null);
		session.removeAttribute("cuotasMorosas");
		session.setAttribute("cuotasMorosas",null);

		
	}
public void CargarSession(HttpSession session) {
	
	
	
	session.setAttribute("sumaEnMora",sumaEnMora);
	session.setAttribute("cuotasMorosas",cuotasMorosas);
	session.setAttribute("sumaEnPagosCuotas",sumaEnPagosCuotas);
	session.setAttribute("sumaGananciaenIntereses",sumaGananciaenIntereses);
	session.setAttribute("promedioMorosos",promedioMorosos);
	session.setAttribute("pagosRecibidos",pagosRecibidos);
	session.setAttribute("promedioTasas", promedioTasas);
	session.setAttribute("prestamosOtorgados", prestamosOtorgados);
	session.setAttribute("prestamosRechazados", prestamosRechazados);
	session.setAttribute("montoTotalOtorgado", montoTotalOtorgado);
	
	
}

private void CargarVectoresMensuales(int anio , ArrayList<Prestamo> listaPrestamos, ArrayList  <CuotaPrestamo> listaCuotas) {

	
	
for (Prestamo p : listaPrestamos) {
	
	
	if(p.getFechaPrestamo().getYear() == anio) {
	
	
	
	int mesIndice = p.getFechaPrestamo().getMonthValue()-1;
	vectorDeListasPorMesesPrestamos[mesIndice].add(p);
	
	}
	
	}	
	//System.out.println("listaCuotas:"+listadoTodosCuotas.size());
	//PROCESO LISTADO TOTAL CUOTAS
	for (CuotaPrestamo cp : listaCuotas) {
			
			if(cp.getFechaVencimiento().getYear() == anio) {			
				
				int mesIndice = cp.getFechaVencimiento().getMonthValue()-1;
			
				vectordeListasdeMesesCuotas[mesIndice].add(cp);
				
			}		
		
		}
		}


private void InicializarVectoresdeCalculos() {

// INSTANCIO ARRAYLISTS
for (int i=0;i<12; i++) {
vectordeListasdeMesesCuotas[i] = new ArrayList<CuotaPrestamo>();	
vectorDeListasPorMesesPrestamos[i] = new ArrayList<Prestamo>(); 
vectorDeListasPorMesesPrestamos[i].clear();
vectordeListasdeMesesCuotas[i].clear();
montoTotalOtorgado[i] = 0; 
promedioTasas[i] = 0;
promedioMorosos[i] = 0;
prestamosOtorgados[i] = 0 ;
prestamosRechazados[i] = 0;
pagosRecibidos[i] = 0;
sumaEnPagosCuotas[i] = 0;
sumaEnMora[i] = 0;
sumaGananciaenIntereses[i] = 0;
cuotasMorosas[i] = 0;
}
}

private void CargarVectoresdeCalculos() {
	
for (int i = 0; i<12;i++) {
		

	
		for (CuotaPrestamo c: vectordeListasdeMesesCuotas[i]) {
			
					
			if(c.getEstado()==EstadoCuota.Pagado) {
				//ACUMULO PAGOS RECIBIDOS POR MES
					
			pagosRecibidos[i]+=1;
			
			sumaEnPagosCuotas[i]+=c.getMontoCuota();
			
			}
			else {
				
			sumaEnMora[i]+=c.getMontoCuota();
				
			cuotasMorosas[i]+=1;				
			 
			}
			 
				
								
			
		}
		// PRESTAMOS
		for (Prestamo p : vectorDeListasPorMesesPrestamos[i]) {
		
			
			if (isAceptadoEseMes(p,i)) {	
		
		// ACUMULAMOS LAS TASAS DE INTERES DE CADA MES

	
			promedioTasas[i] += p.getTipoTasa().getTasaInteres();
			prestamosOtorgados[i]++;
			montoTotalOtorgado[i] += p.getMontoPedido();
			sumaGananciaenIntereses[i]+= p.getMontoConIntereses()-p.getMontoPedido();
			
			}
		
			if(isRechazadoEseMes(p,i)) {
				
				prestamosRechazados[i]++;
			}
		
		}
	
		
	}
	
	for (int i = 0; i<12;i++) {
	
	//SACAMOS EL PROMEDIO DE LAS TASAS POR MES
	if (promedioTasas[i]!=0) {
	promedioTasas[i] = (promedioTasas[i]/prestamosOtorgados[i])/100;

	}
	// PROMEDIAMOS MOROSOS
	
	if(vectordeListasdeMesesCuotas[i].size()!=0) {
	promedioMorosos[i] = (double)cuotasMorosas[i]/(double)vectordeListasdeMesesCuotas[i].size();		
	}
	

	}
	
}
}
	
	
		
		
		
	


