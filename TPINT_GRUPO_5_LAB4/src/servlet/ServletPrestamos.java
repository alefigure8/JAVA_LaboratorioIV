package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.ParseConversionEvent;

import com.sun.corba.se.impl.encoding.CodeSetComponentInfo.CodeSetComponent;

import Helper.GUI;
import entidad.Cliente;
import entidad.Cuenta;
import entidad.CuotaPrestamo;
import entidad.Estado;
import entidad.Movimiento;
import entidad.Operacion;
import entidad.Prestamo;
import entidad.TipoAcceso;
import entidad.TipoMovimiento;
import entidad.TipoTasa;
import entidad.Usuario;
import jdk.nashorn.internal.runtime.ListAdapter;
import negocioDaoImp.ClienteNegocioDaoImp;
import negocioDaoImp.CuentaNegocioDaoImp;
import negocioDaoImp.MovimientoNegocioDaoImp;
import negocioDaoImp.PrestamosNegocioDaoImpl;



@WebServlet("/ServletPrestamos")
public class ServletPrestamos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletPrestamos() {
        super();
       
    }
    
    MovimientoNegocioDaoImp movimientoNegocio=new MovimientoNegocioDaoImp();
    PrestamosNegocioDaoImpl prestamosNegocio=new PrestamosNegocioDaoImpl();
    ClienteNegocioDaoImp clienteNegocio=new ClienteNegocioDaoImp();
    CuentaNegocioDaoImp cuentaNegocio=new CuentaNegocioDaoImp();
    Usuario usuario = new Usuario();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//Obtenemos prestamos desde admin
		if(request.getParameter("Prestamos")!=null && request.getParameter("btnFiltrarPrestamos")==null ) {
			cargarPrestamos(request);
			RequestDispatcher rDispatcher=request.getRequestDispatcher("PrestamosClientes.jsp");
			rDispatcher.forward(request, response);
		}
		
		//Obtenemos prestamos desde cliente
		if(request.getParameter("PrestamosCliente")!=null && request.getParameter("btnFiltrarPrestamos")==null) {
			cargarPrestamos(request);
			RequestDispatcher rDispatcher=request.getRequestDispatcher("PrestamosClientes.jsp");
			rDispatcher.forward(request, response);
		}
		
		
		
		//Filtros
		 if(request.getParameter("btnFiltrarPrestamos")!=null && request.getParameter("btnSolicitarPrestamo")==null) {
			
			String estadoSeleccionado=request.getParameter("Estados");
			System.out.println("Estado "+estadoSeleccionado);
			
			//Listas limpias
			List<Prestamo>prestamos= (List<Prestamo>)session.getAttribute("listaPrestamos");
			//Listas para filtrar
			List<Prestamo>prestamosFiltrados= new ArrayList<>();
			
			if(session.getAttribute("usuario") != null){
				usuario = (Usuario)session.getAttribute("usuario");
			}
			
			//*****SI ES ADMIN FILTRA LISTADO DE PRESTAMOS Y CLIENTES*****....
			
			if(usuario.getTipoAcceso().compareTo(TipoAcceso.Administrador)==0) {
				//Listas limpias
				List<Cliente> clientes = (List<Cliente>)session.getAttribute("listaClientes");
				
				//Listas para filtrar
				List<Cliente> clientesFiltrados = new ArrayList<>();
				
				if (estadoSeleccionado.equals("Todos los Estados")) {
					session.setAttribute("prestamos", session.getAttribute("listaPrestamos"));
					session.setAttribute("clientes", session.getAttribute("listaClientes"));
				}
				else {
					for(int x=0;x<prestamos.size();x++) {
						
						if(prestamos.get(x).getEstado().getDescripcion().equals(estadoSeleccionado)){
							prestamosFiltrados.add(prestamos.get(x));
							clientesFiltrados.add(clientes.get(x));
						}
						
					}
					session.setAttribute("prestamos", prestamosFiltrados);
					session.setAttribute("clientes", clientesFiltrados);
					
				}
			}
				
			
			//SI ES CLIENTE FILTRAMOS SOLO PRESTAMOS DE ESE CLIENTE...
			
			if(usuario.getTipoAcceso().compareTo(TipoAcceso.Cliente)==0) {
				
				
				if (estadoSeleccionado.equals("Todos los Estados")) {
					session.setAttribute("prestamos", session.getAttribute("listaPrestamos"));
					
				}
				else {
					for(int x=0;x<prestamos.size();x++) {
						
						if(prestamos.get(x).getEstado().getDescripcion().equals(estadoSeleccionado)){
							prestamosFiltrados.add(prestamos.get(x));
							
						}
						
					}
					session.setAttribute("prestamos", prestamosFiltrados);
					
					
				}
			}
			
			
			
			session.setAttribute("prestamoSeleccionado", estadoSeleccionado);
			
			RequestDispatcher rDispatcher=request.getRequestDispatcher("PrestamosClientes.jsp");
			rDispatcher.forward(request, response);
			
		}
		 
		
		//SOLICITAR PRESTAMO
			if(request.getParameter("btnSolicitarPrestamo")!=null) {
				System.out.println("NO ES NULL");
				if(session.getAttribute("usuario") != null){
					usuario = (Usuario)session.getAttribute("usuario");
				}
				
				List<TipoTasa> tiposTasa=(List<TipoTasa>)prestamosNegocio.obtenerTodosTiposTasas();
				List<Cuenta> cuentasCliente=new ArrayList<Cuenta>();
				try {
					cuentasCliente=(List<Cuenta>)cuentaNegocio.obtenerCuentasCliente(usuario.getId());
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				
				session.setAttribute("tiposTasa", tiposTasa);
				session.setAttribute("cuentasCliente", cuentasCliente);
				//request.setAttribute("tiposTasa", tiposTasa);
				//request.setAttribute("cuentasCliente", cuentasCliente);
				RequestDispatcher rDispatcher=request.getRequestDispatcher("PrestamosSolicitud.jsp");
				rDispatcher.forward(request, response);
			}
			
		 
		//CALCULAR INTERESES
			if(request.getParameter("btnCalcularIntereses")!=null) {
				
				
				double montoSeleccionado = Double.parseDouble(request.getParameter("monto"));
		        double tipoTasaSeleccionada = Double.parseDouble(request.getParameter("cuotas")) ;

		        Double interesCalculado = (montoSeleccionado * tipoTasaSeleccionada/ 100);
		        Double totalCalculado =(montoSeleccionado + interesCalculado);
				
				session.setAttribute("montoSeleccionado", montoSeleccionado);
				session.setAttribute("tipoTasaSeleccionada", tipoTasaSeleccionada);
				session.setAttribute("interesCalculado", interesCalculado);
				session.setAttribute("totalCalculado", totalCalculado);
				RequestDispatcher rDispatcher=request.getRequestDispatcher("PrestamosSolicitud.jsp");
				rDispatcher.forward(request, response);
			}
			
			
		//CONFIRMAR SOLICITAR PRESTAMO
			if(request.getParameter("btnConfirmarSolicitarPrestamo")!=null) {
				
				if(session.getAttribute("usuario") != null){
					usuario = (Usuario)session.getAttribute("usuario");
				}
				
				double montoSeleccionado = Double.parseDouble(request.getParameter("monto"));
		        double tipoTasaSeleccionada = Double.parseDouble(request.getParameter("cuotas")) ;
		        TipoTasa tipoTasa=new TipoTasa();
		        List<TipoTasa> tiposTasa=(List<TipoTasa>)session.getAttribute("tiposTasa");
		        for(TipoTasa tasas:tiposTasa) {
		        	if(tipoTasaSeleccionada==tasas.getTasaInteres()) {
		        		tipoTasa.setId(tasas.getId());
		        		tipoTasa.setCantCuotas(tasas.getCantCuotas());
		        	}
		        }
		        
		        int numeroCuenta=Integer.parseInt(request.getParameter("cuentas"));
		        Double interesCalculado = (montoSeleccionado * tipoTasaSeleccionada/ 100);
		        Double totalCalculado =(montoSeleccionado + interesCalculado);
				
				
		        //CREAMOS PRESTAMO
		        
		        Prestamo prestamo=new Prestamo();
		        prestamo.setMontoPedido(montoSeleccionado);
		        prestamo.setMontoConIntereses(totalCalculado);
		        
		        prestamo.setTipoTasa(tipoTasa);
		        prestamo.setMontoxMes(totalCalculado/tipoTasa.getCantCuotas());
		        Estado estado=new Estado();//id
		        estado.setIdEstado(2);//2=PENDIENTE
		        prestamo.setEstado(estado);
		        prestamo.setFechaPrestamo(LocalDate.now());//hoy
		        prestamo.setIdCliente(usuario.getId());
		        prestamo.setNumeroCuenta(numeroCuenta);
		        
		        
		        //INSERTAR PRESTAMO
		        if(prestamosNegocio.insertarprestamo(prestamo)) {
		        	System.out.println("PRESTAMO SOLICITADO");
		        	session.removeAttribute("montoSeleccionado");
		        	session.removeAttribute("tipoTasaSeleccionada");
		        	session.removeAttribute("interesCalculado");
		        	session.removeAttribute("totalCalculado");
		        	cargarPrestamos(request);
		        	request=GUI.mensajes(request, "exito", "Prestamo solicitado", "El prestamo se solicit� correctamente");
		        	RequestDispatcher rDispatcher=request.getRequestDispatcher("PrestamosClientes.jsp");
					rDispatcher.forward(request, response);
		        }
		        else {
		        	request=GUI.mensajes(request, "exito", "Prestamo no solicitado", "El prestamo no se pudo solicitar");
		        	RequestDispatcher rDispatcher=request.getRequestDispatcher("PrestamosClientes.jsp");
					rDispatcher.forward(request, response);
		        }
		        
			}
		
			
			
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		
		//ACEPTAR
		if(request.getParameter("btnAprobarPrestamo")!=null) {
			int idPrestamo=Integer.parseInt(request.getParameter("idPrestamo").toString());
			System.out.println("PRESMTAMO ok");
			//ACEPTAMOS PRESTAMO
			if(prestamosNegocio.aceptar(idPrestamo)) {
				
					//GENERAR MOVIMIENTO EN LA CUENTA DEL PRESTAMO (nro. Referencia = idPrestamo)
					TipoMovimiento tipoMovimiento=new TipoMovimiento();
					tipoMovimiento.setId(2);//ALTA DE PRESTAMO
					
					int numeroCuenta=Integer.parseInt(request.getParameter("numCuenta"));
					
					String cbudestino="";
					try {
						cbudestino = cuentaNegocio.obtenerUna(numeroCuenta).getCbu();
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
					
					Double monto= Double.parseDouble(request.getParameter("montoPedido"));
					Estado estado=new Estado();
					estado.setIdEstado(1);//APROBADO
					
					Cuenta cuenta= new Cuenta();
					try {
						cuenta=(Cuenta)cuentaNegocio.obtenerUna(numeroCuenta);
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
					cuenta.setSaldo(cuenta.getSaldo()+monto);
					
					Movimiento movimiento=new Movimiento(tipoMovimiento, idPrestamo, /*cbudestino,*/ cuenta, monto, 
							Operacion.Entrada, LocalDate.now(), estado, "Alta de prestamo");
							
					try {
						if(movimientoNegocio.insertar(movimiento)) {
							if(cuentaNegocio.editar(cuenta)) {
								cargarPrestamos(request);
								request=GUI.mensajes(request, "exito", "Prestamo aceptado", "El prestamo se acept� correctamente");
							}
							
						}
					} catch (SQLException e) {
						
						e.printStackTrace();
					}
					
				
				
			}
			else {
				request=GUI.mensajes(request, "error", "Prestamos", "No se pudo aceptar el prestamo");
			}
			
		}
		
		
		//RECHAZAR
		if(request.getParameter("btnRechazarPrestamo")!=null) {
			System.out.println("PRESMTAMO no");
			int idPrestamo=Integer.parseInt(request.getParameter("idPrestamo").toString());
			if(prestamosNegocio.rechazar(idPrestamo)) {
				cargarPrestamos(request);
				request=GUI.mensajes(request, "exito", "Prestamo rechazado", "El prestamo se rechaz� correctamente");
				
			}
			else {
				request=GUI.mensajes(request, "error", "Prestamos", "No se pudo rechazar el prestamo");
			}
			
		}
		
		
		//REDIRECCIONAR A PAGAR CUOTA
		if(request.getParameter("btnPagarPrestamo")!=null) {
			if(request.getParameter("idCuota")!=null && request.getParameter("idPrestamo")!=null) {
				
				int idCuota=Integer.parseInt(request.getParameter("idCuota"));
				
				int idPrestamo=Integer.parseInt(request.getParameter("idPrestamo"));
				
				//ENVIAR PRESTAMO
				Prestamo prestamo=prestamosNegocio.obteneruno(idPrestamo);
				
				//ENVIAR CUOTA 
				CuotaPrestamo cuotaApagar=(CuotaPrestamo)prestamosNegocio.obtenerUnaCuota(idCuota, idPrestamo);
				
				//ENVIAR CUENTAS DEL CLIENTE
				if(session.getAttribute("usuario") != null){
					usuario = (Usuario)session.getAttribute("usuario");
				}
				List<Cuenta>cuentasCliente=new ArrayList<Cuenta>();
				try {
					cuentasCliente=(List<Cuenta>)cuentaNegocio.obtenerCuentasCliente(usuario.getId());
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				request.setAttribute("prestamo", prestamo);
				request.setAttribute("cuotaApagar", cuotaApagar);
				request.setAttribute("cuentasCliente", cuentasCliente);
			
				RequestDispatcher rDispatcher=request.getRequestDispatcher("PagoPrestamo.jsp");
				rDispatcher.forward(request, response);
			}
		}
		
		
		//PAGAR CUOTA
		if (request.getParameter("btnPagarCuota") != null) {
			Double monto= Double.parseDouble(request.getParameter("montoCuota"));
		    String idCuota = request.getParameter("idCuotaApagar");
		    Cuenta cuenta= new Cuenta();
		    int idprestamo=Integer.parseInt(request.getParameter("idPrestamo"));
		    try {
				 cuenta= (Cuenta)cuentaNegocio.obtenerUna(Integer.parseInt(request.getParameter("CuentasClientePago")));
			} catch (NumberFormatException e) {
				
				e.printStackTrace();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		    
		    //VERIFICAMOS QUE HAYA SALDO SUFICIENTE
		    if(cuenta.getSaldo()>=monto) {
		    	
		    	cuenta.setSaldo(cuenta.getSaldo()-monto);
			    
			    Estado estado=new Estado();
			    estado.setIdEstado(1);//Aprobado
			    
			    //GENERAMOS MOVIMIENTO
			    TipoMovimiento tipoMovimiento=new TipoMovimiento();
			    tipoMovimiento.setId(3);//PAGO DE PRESTAMO
			    
			    //CREAMOS MOVIMIENTO
			    Movimiento movimiento=new Movimiento(tipoMovimiento, Integer.parseInt(idCuota), cuenta, monto, Operacion.Salida, 
			    		LocalDate.now(), estado, "Pago de prestamo");
			    try {
					if(movimientoNegocio.insertar(movimiento)) {
						if(cuentaNegocio.editar(cuenta)) {
							if(prestamosNegocio.setcuotapagada(idprestamo, Integer.parseInt(idCuota))) {
								//EVALUAMOS SI SE PAGARON TODAS LAS CUOTAS, SI ES ABOANRON TODAS SETEAMOS EL PRESTAMO COMO SALDADO
								boolean abonado=prestamosNegocio.prestamoSaldado(idprestamo);
								System.out.println(abonado);
									cargarPrestamos(request);
									request=GUI.mensajes(request, "exito", "Pago exitoso", "La cuota se abon� con �xito");
									
								
							}
							
						}
						
					}
					else {
						
						request=GUI.mensajes(request, "error", "Error en pago", "La cuota no se pudo abonar");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	
		    	
		    }
		    else {
		    	request=GUI.mensajes(request, "error", "Error en pago", "No posee saldo suficiente");
		    	
		    	
		    }
		    
		    
		    
		}
		
		RequestDispatcher rDispatcher=request.getRequestDispatcher("PrestamosClientes.jsp");
		rDispatcher.forward(request, response);
	}

	private void cargarPrestamos(HttpServletRequest request) {
		HttpSession session = request.getSession();
		//SI ES ADMIN LISTA DE PRESTAMOS Y CLIENTES
		session = request.getSession();	
		if(session.getAttribute("usuario") != null){
			usuario = (Usuario)session.getAttribute("usuario");
		}
		
		if(usuario.getTipoAcceso().compareTo(TipoAcceso.Administrador)==0) {
			List<Prestamo>prestamos= prestamosNegocio.obtenerTodos();
			List<Cliente> clientes = new ArrayList<Cliente>();
			//Obtenemos clientes s/prestamo
			for(Prestamo prestamo : prestamos) {
				int idCliente=prestamo.getIdCliente();
				
				clientes.add(clienteNegocio.obtenerUno(idCliente));
				
			}
			session.setAttribute("prestamos", prestamos);
			session.setAttribute("clientes", clientes);
			//Session limpia
			session.setAttribute("listaPrestamos", prestamos);
			session.setAttribute("listaClientes", clientes);
		}
			
		
		//SI ES CLIENTE SOLO PRESTAMOS DE ESE CLIENTE
		if(usuario.getTipoAcceso().compareTo(TipoAcceso.Cliente)==0) {
			List<Prestamo>prestamos=prestamosNegocio.obtenerTodosxcliente(usuario.getId());
			session.setAttribute("prestamos", prestamos);
			
			//Session limpia
			session.setAttribute("listaPrestamos", prestamos);
			
		}
	}
	
}
