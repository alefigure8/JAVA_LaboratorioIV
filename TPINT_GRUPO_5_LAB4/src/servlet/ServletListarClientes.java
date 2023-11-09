package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Helper.GUI;
import entidad.Cliente;
import negocioDaoImp.ClienteNegocioDaoImp;

@WebServlet("/ServletListarClientes")
public class ServletListarClientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ClienteNegocioDaoImp clienteNegocioDao=new ClienteNegocioDaoImp();

	
    public ServletListarClientes() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	/** OBTENER LISTADO DE CLIENTES **/
    	if(request.getParameter("obtener")!=null) {
    		try {
        	    // Llama al m�todo para obtener la lista de clientes
        	    List<Cliente> listaClientes = clienteNegocioDao.obtenerTodos();
        	    
         	    // Verifica si la lista de clientes es null o vac�a
    		    if (listaClientes == null || listaClientes.isEmpty()) {
    	   	        // Configura un mensaje de error en lugar de la lista
    	   	        request.setAttribute("mensajeError", "No se encontraron clientes" );
    	   	    } else {
        	        // Configura un atributo de solicitud con la lista de clientes
    	   	    	request.setAttribute("clientes", ListarClientesActivos(listaClientes));
    	   	    }

        	    // Env�a la solicitud al archivo JSP para mostrar la tabla
        	    RequestDispatcher dispatcher = request.getRequestDispatcher("ListadoClientes.jsp");
        	    dispatcher.forward(request, response);
        	} catch (Exception e) {
        	    // Maneja las excepciones, por ejemplo, redirigiendo a una p�gina de error
        	    e.printStackTrace();
        	    response.sendRedirect("PerfilBanco.jsp");
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

							List<Cliente> listaClientes = clienteNegocioDao.obtenerTodos();
					    	    
							request.setAttribute("clientes", ListarClientesActivos(listaClientes));
							
							//Popup de Exito
							request = GUI.mensajes(request, "exito", "Cliente eliminado", "El cliente se borr� correctamente");
							
				    	    // Env�a la solicitud al archivo JSP para mostrar la tabla
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
	private List<Cliente> ListarClientesActivos(List<Cliente> listaClientes) {
		listaClientes = clienteNegocioDao.obtenerTodos();
		 List<Cliente> listaClientesActivos = new ArrayList<Cliente>();
    	    
		 for(Cliente cliente : listaClientes) {
			if(cliente.getActivo()) {
				listaClientesActivos.add(cliente);
			}
		}
		    
		return listaClientesActivos;
	}
	
}
