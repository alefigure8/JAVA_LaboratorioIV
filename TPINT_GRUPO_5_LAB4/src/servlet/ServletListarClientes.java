package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import com.mysql.cj.Session;

import Helper.GUI;
import daoImp.CuentaDaoImp;
import entidad.Cliente;
import entidad.Cuenta;
import entidad.Provincia;
import negocioDaoImp.ClienteNegocioDaoImp;
import negocioDaoImp.ProvinciaNegocioDaoImp;

@WebServlet("/ServletListarClientes")
public class ServletListarClientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ClienteNegocioDaoImp clienteNegocioDao=new ClienteNegocioDaoImp();
	ProvinciaNegocioDaoImp provinciaNegocioDaoImp = new ProvinciaNegocioDaoImp();

	
    public ServletListarClientes() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	  	
    	/** OBTENER LISTADO DE CLIENTES **/
    	if(request.getParameter("obtener") != null || request.getParameter("btnLimpiarFiltros") != null) {
    		try {
        	    // Llama al método para obtener la lista de clientes
        	    List<Cliente> listaClientes = clienteNegocioDao.obtenerTodos();
        	    List<Provincia> listaProvincias = provinciaNegocioDaoImp.obtenerTodas();
        	    
        	    HttpSession session = request.getSession(true);
				session.setAttribute("provinciasFiltroCliente", listaProvincias);
				
				/* limpiamos sesesion de búsqueda */
				session.removeAttribute("opcionSelect");
				session.removeAttribute("generoSelect");
				session.removeAttribute("provinciaSelect");
				session.removeAttribute("desdeSelect");
				session.removeAttribute("hastaSelect");
				
         	    // Verifica si la lista de clientes es null o vacía
    		    if (listaClientes == null || listaClientes.isEmpty()) {
    	   	        // Configura un mensaje de error en lugar de la lista
    	   	        request.setAttribute("mensajeError", "No se encontraron clientes" );
    	   	    } else {
        	        // Configura un atributo de solicitud con la lista de clientes
    	   	    	request.setAttribute("clientes", ListarClientesActivos(listaClientes, "activos"));
    	   	    }

        	    // Envía la solicitud al archivo JSP para mostrar la tabla
        	    RequestDispatcher dispatcher = request.getRequestDispatcher("ListadoClientes.jsp");
        	    dispatcher.forward(request, response);
        	} catch (Exception e) {
        	    // Maneja las excepciones, por ejemplo, redirigiendo a una página de error
        	    e.printStackTrace();
        	    response.sendRedirect("PerfilBanco.jsp");
        	}
    	}
    	
    	/** FILTRO **/
    	if(request.getParameter("btnFiltrarTransferencias")!=null) {
    		HttpSession session = request.getSession(true);
    		try {
        	    // Llama al método para obtener la lista de clientes
        	    List<Cliente> listaClientes = clienteNegocioDao.obtenerTodos();
        	    
         	    // Verifica si la lista de clientes es null o vacía
    		    if (listaClientes == null || listaClientes.isEmpty()) {
    	   	        // Configura un mensaje de error en lugar de la lista
    	   	        request.setAttribute("mensajeError", "No se encontraron clientes" );
    	   	    } else {
    	   	    	
        	        // Configura un atributo de solicitud con la lista de clientes
    	   	    	List<Cliente> auxListaCliente = listaClientes;
    	   	    	
    	   	    	/* Activo o Ianctivo*/
    	   	    	if(request.getParameter("opcion") != null){
    	   	    		String opcion = request.getParameter("opcion");
    	   	    		session.setAttribute("opcionSelect", opcion);
    	   	    		
    	   	    		auxListaCliente = ListarClientesActivos(auxListaCliente, opcion );
    	   	    	}
    	   	    	
    	   	    	/* Sexo */
    	   	    	if(request.getParameter("genero") != null){
    	   	    		String genero = request.getParameter("genero") ;
    	   	    		session.setAttribute("generoSelect", genero);
    	   	    		
    	   	    		auxListaCliente = ListarClientePorGenero(auxListaCliente, genero);
    	   	    	}
    	   	    	
    	   	    	/* Provincia */
    	   	    	if(request.getParameter("provincia") != null){
    	   	    		String provincia = request.getParameter("provincia") ;
    	   	    		session.setAttribute("provinciaSelect", provincia);
    	   	    		
    	   	    		auxListaCliente = ListarClientePorProvincia(auxListaCliente, provincia);
    	   	    	}
    	   	    	
    	   	    	/* Fecha */
    	   	    	if(request.getParameter("fechaDesde") != null || request.getParameter("fechaHasta") != null ){
    	   	    		String desde = request.getParameter("fechaDesde");
    	   	    		String hasta = request.getParameter("fechaHasta");
    	   	    		session.setAttribute("desdeSelect", desde);
    	   	    		session.setAttribute("hastaSelect", hasta);
    	   	    		
    	   	    		auxListaCliente = ListarClientePorFecha(auxListaCliente, desde, hasta);
    	   	    	}
    	   	    
    	   	    	request.setAttribute("clientes", auxListaCliente);
    	   	    }

        	    // Envía la solicitud al archivo JSP para mostrar la tabla
        	    RequestDispatcher dispatcher = request.getRequestDispatcher("ListadoClientes.jsp");
        	    dispatcher.forward(request, response);
        	} catch (Exception e) {
        	    // Maneja las excepciones, por ejemplo, redirigiendo a una página de error
        	    e.printStackTrace();
        	    response.sendRedirect("PerfilBanco.jsp");
        	}
    	}
    	
    	/** ACTIVAR CLIENTE **/
    	if(request.getParameter("activar") != null) {
    		
    		if(request.getParameter("ID") != null) {
    			String ID = request.getParameter("ID");
				//Buscamos que exista
				Cliente existeCliente = clienteNegocioDao.obtenerUno(Integer.parseInt(ID));
				if(existeCliente != null) {
					
					/* Setear activo en true */
					Boolean clienteActivado = clienteNegocioDao.activar(Integer.parseInt(ID));
					
					if(clienteActivado) {
						//Listamos clinetes
						List<Cliente> listaClientes = clienteNegocioDao.obtenerTodos();
				    	    
						//Mandamos lista de clientes con request
						request.setAttribute("clientes", ListarClientesActivos(listaClientes, "activos"));
						
						//Popup de Exito
						request = GUI.mensajes(request, "exito", "Cliente activado", "El cliente se activó correctamente");
						
						 RequestDispatcher dispatcher = request.getRequestDispatcher("ListadoClientes.jsp");
				    	 dispatcher.forward(request, response);
					} else {
						//Popup de Exito
						request = GUI.mensajes(request, "error", "Cuentas", "Las Cuentas del cliente no pudieron ser activado");
						
						 RequestDispatcher dispatcher = request.getRequestDispatcher("ListadoClientes.jsp");
				    	    dispatcher.forward(request, response);
					}
					
				} else {
					//Popup de error
					request = GUI.mensajes(request, "error", "Cliente no existes", "El cliente que intenta borrar no existe");
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("ListadoClientes.jsp");
					dispatcher.forward(request, response);
				}
    		}
    	}
    	
    	/** BORRAR CLIENTE **/
    	try {
			if(request.getParameter("borrar")!= null) {
							
				if(request.getParameter("ID") != null) {
					
					String ID = request.getParameter("ID");
					//Buscamos que exista
					Cliente existeCliente = clienteNegocioDao.obtenerUno(Integer.parseInt(ID));
					if(existeCliente != null) {
						
						//Borramos cliente	
						Boolean clienteBorrado = clienteNegocioDao.borrar(Integer.parseInt(ID));
						
						if(clienteBorrado) {

							//Listamos clinetes
							List<Cliente> listaClientes = clienteNegocioDao.obtenerTodos();
					    	    
							//Mandamos lista de clientes con request
							request.setAttribute("clientes", ListarClientesActivos(listaClientes, "Activos"));
							
							//Borramos cuentas	
							boolean cuentasBorradas = BorrarCuentasClientes(Integer.parseInt(ID));
							
							if(cuentasBorradas) {
								//Popup de Exito
								request = GUI.mensajes(request, "exito", "Cliente eliminado", "El cliente se borró correctamente");
							} else {
								//Popup de Exito
								request = GUI.mensajes(request, "error", "Cuentas", "Las Cuentas del cliente no pudieron ser borradas");
							}
							
							
				    	    // Envía la solicitud al archivo JSP para mostrar la tabla
				    	    RequestDispatcher dispatcher = request.getRequestDispatcher("ListadoClientes.jsp");
				    	    dispatcher.forward(request, response);
						} else {
							//Popup de error
							request = GUI.mensajes(request, "error", "Cliente no existes", "El cliente que intenta borrar no existe");
							
							RequestDispatcher dispatcher = request.getRequestDispatcher("ListadoClientes.jsp");
							dispatcher.forward(request, response);
						}
					}
					
				} else {
					//Popup de error
					request = GUI.mensajes(request, "error", "Cliente no existes", "El cliente que intenta borrar no existe");
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("ListadoClientes.jsp");
					dispatcher.forward(request, response);
				}
				
			}
		} catch (Exception e) {
			//Popup de error
			request = GUI.mensajes(request, "error", "Error Base de Datos", e.getMessage());
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("ListadoClientes.jsp");
			dispatcher.forward(request, response);
		}
    	
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	/** LISTAR CLIENTES ACTIVOS **/
	private List<Cliente> ListarClientesActivos(List<Cliente> listaCliente, String opcion) {
		 List<Cliente> listaClientesFiltrada = new ArrayList<Cliente>();
    	   
		 if(!opcion.equals("todos")) {
			 boolean listar = opcion.equals("activos") ? true : false;
			 
			 for(Cliente cliente : listaCliente) {
				 if(cliente.getActivo() == listar) {
					 listaClientesFiltrada.add(cliente);
				 }
			 }
			 
		 } else {
			 return listaCliente;
		 }
		    
		return listaClientesFiltrada;
	}
	
	/** LISTAR CLIENTE POR GENERO**/
	private List<Cliente> ListarClientePorGenero(List<Cliente> listaCliente, String genero){
		 List<Cliente> listaClientesFiltrada = new ArrayList<Cliente>();
		
		 if(!genero.equals("todos")) {
			 String tipoGenero = genero.equals("masculino") ? "M" : "F";
			 
			 for(Cliente cliente : listaCliente) {
				 if(cliente.getSexo().equals(tipoGenero)) {
					 listaClientesFiltrada.add(cliente);
				 }
			 } 
			 
		 } else {
			 return listaCliente;
		 }
		 
		return listaClientesFiltrada;
	}
	
	/** LISTAR CLIENTE POR PROVINCIA**/
	private List<Cliente> ListarClientePorProvincia(List<Cliente> listaCliente, String provincia){
		 List<Cliente> listaClientesFiltrada = new ArrayList<Cliente>();
		 
		 if(!provincia.equals("todas")) {		 
			 for(Cliente cliente : listaCliente) {
				 if(cliente.getDireccion().getProvincia().getIdProvincia() == Integer.parseInt(provincia)) {
					 listaClientesFiltrada.add(cliente);
				 }
			 } 
			 
		 } else {
			 return listaCliente;
		 }
		 
		return listaClientesFiltrada;
	}
	
	/** LISTAR CLIENTE POR FECHA**/
	private List<Cliente> ListarClientePorFecha(List<Cliente> listaCliente, String desde, String hasta){
		 List<Cliente> listaClientesFiltrada = new ArrayList<Cliente>();
		 
		 LocalDate desdeAux = !desde.isEmpty() ? LocalDate.parse(desde) : null;
		    LocalDate hastaAux = !hasta.isEmpty() ? LocalDate.parse(hasta) : null;

		    Iterator<Cliente> iterator = listaCliente.iterator();
		    
		    while (iterator.hasNext()) {
		        Cliente cliente = iterator.next();
		        LocalDate fechaNacimiento = cliente.getNacimiento();

		        if (desdeAux != null && fechaNacimiento.isBefore(desdeAux)) {
		            iterator.remove();
		        } else if (hastaAux != null && fechaNacimiento.isAfter(hastaAux)) {
		            iterator.remove();
		        } else {
		        	listaClientesFiltrada.add(cliente);
		        }
		    }
		 
		 
		return listaClientesFiltrada;
	}
	
	/** BORRAR CUENTAS CLIENTES **/
	private boolean BorrarCuentasClientes(int idCliente) {
		boolean cuentasBorradas = true;
		
		try {
			CuentaDaoImp cuentaDaoImp = new CuentaDaoImp();
			
			List<Cuenta> cuentasABorrar = cuentaDaoImp.obtenerCuentasCliente(idCliente);
			
			Iterator<Cuenta> it;
			it = cuentasABorrar.iterator();
			
			while(it.hasNext()) {
				Cuenta cuenta = it.next();
				System.out.println(cuenta.getNumeroCuenta());
				boolean cuentaBorrada = cuentaDaoImp.borrar(cuenta.getNumeroCuenta());
				
				if(!cuentaBorrada){
					cuentasBorradas = false;
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			return cuentasBorradas = false;
		}
		
		return cuentasBorradas;
	}
	
}
