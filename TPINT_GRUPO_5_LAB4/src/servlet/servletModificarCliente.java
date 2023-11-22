package servlet;

import java.io.IOException;
import java.sql.SQLException;
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

import com.sun.corba.se.impl.javax.rmi.PortableRemoteObject;

import Helper.GUI;
import daoImp.LocalidadDaoImp;
import daoImp.ProvinciaDaoImp;
import entidad.Cliente;
import entidad.Localidad;
import entidad.Provincia;
import entidad.TipoDireccion;
import excepciones.CorreoException;
import excepciones.ErrorInternoException;
import excepciones.UsuarioIncorrectoException;
import negocioDaoImp.ClienteNegocioDaoImp;
import negocioDaoImp.LocalidadNegocioDaoImp;
import negocioDaoImp.ProvinciaNegocioDaoImp;
import sun.security.util.math.intpoly.P256OrderField;


@WebServlet("/servletModificarCliente")
public class servletModificarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public servletModificarCliente() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Charset
		request.setCharacterEncoding("UTF-8");
		
		//Negocio cliente
		ClienteNegocioDaoImp clienteNegocioDaoImp = new ClienteNegocioDaoImp();
		
		/** BUSCAR CLIENTE PARA MODIFICAR **/
		if(request.getParameter("obtener") != null) {	
			try {
				
				String id = request.getParameter("ID");
				
				/* Buscar cliente por id */
				Cliente cliente = clienteNegocioDaoImp.obtenerUno(Integer.parseInt(id));
					
				/* Obtener todas las licalidad y Provincias y guardar en request*/
				obtenerLocalidadYProvincia(request);
				
				/* guardar cliente */
				request.setAttribute("cliente", cliente);
				
				/* Redirigir a página de Modificar cliente */
				RequestDispatcher rd = request.getRequestDispatcher("ModificarCliente.jsp");
				rd.forward(request, response);
				
				return;
			} catch (Exception e) {
				//Popup de error
				request = GUI.mensajes(request, "error", "Error Base de Datoso", e.getMessage());
				
				RequestDispatcher rd = request.getRequestDispatcher("ModificarCliente.jsp");
				rd.forward(request, response);
				
				return;
			}
		}
		
		/** MODIFICAR EL CLIENTE **/
		if(request.getParameter("modificar") != null) {

			Cliente cliente = new Cliente();
			
			try {		
				/* Pasar modificaciones a objeto cliente  */
				cliente = obtenerCliente(request, response);

				if(cliente != null && cliente.getActivo()) {

					boolean clienteEditado = clienteNegocioDaoImp.editar(cliente);
					
					if(clienteEditado) {
						//Popup de exito
						request = GUI.mensajes(request, "exito", "Cliente modificado", "El cliente se modificó correctamente");
						if (!response.isCommitted()) {
							RequestDispatcher rd = request.getRequestDispatcher("ServletListarClientes?obtener=true");
							rd.forward(request, response);
							return;
						}
					} else {
						//Popup de error de modificación
						request = GUI.mensajes(request, "Error", "Cliente no modificado", "El cliente no pudo ser modificado");
						
						RequestDispatcher rd = request.getRequestDispatcher("ServletListarClientes?obtener=true");
						rd.forward(request, response);
						
						return;
					}

				} else {				
					//Popup de error. Cliente no encontrado o no activo
					request = GUI.mensajes(request, "Error", "Cliente no modificado", "El cliente no existe o no se encuentra activo");
					
					RequestDispatcher rd = request.getRequestDispatcher("ServletListarClientes?obtener=true");
					rd.forward(request, response);
					
					return;
				}
				
			} catch (Exception e) {
				//Retornamos cliente modificado para que corrija datos
				request.setAttribute("cliente", cliente); 
				
				request = GUI.mensajes(request, "error", "Error", e.getMessage());
				RequestDispatcher rd = request.getRequestDispatcher("ModificarCliente.jsp");
				rd.forward(request, response);
				
				return;
			}
		}
	}
	
	private Cliente obtenerCliente(HttpServletRequest request, HttpServletResponse response) throws ErrorInternoException, Exception, CorreoException {	
		String id = request.getParameter("ID");
		
		//Negocio cliente
		ClienteNegocioDaoImp clienteNegocioDaoImp = new ClienteNegocioDaoImp();
		
		//Buscar cliente por id
		Cliente cliente = clienteNegocioDaoImp.obtenerUno(Integer.parseInt(id));
		
		/* Obtener mos parámetros */
		String nombre = request.getParameter("nombre").trim();
		String apellido = request.getParameter("apellido").trim();
		String dni = request.getParameter("dni").trim();
		String cuil = request.getParameter("cuil").trim();
		String nacionalidad = request.getParameter("nacionalidad").trim();
		String fechaNacimiento = request.getParameter("fechaNacimiento").trim();
		String sexo = request.getParameter("sexo").trim();
		String calle = request.getParameter("calle").trim();
		String numero = request.getParameter("numero").trim();
		String tipoDireccion = request.getParameter("tipoDireccion").trim();
		String apartamento = request.getParameter("apartamento").trim();
		String codPos = request.getParameter("codpos").trim();
		String localidadID = request.getParameter("localidadID");
		String telefono = request.getParameter("telefono").trim();
		String correo = request.getParameter("correo").trim();
		String contrasenaNueva = request.getParameter("claveNueva").trim();
		String contrasenaRepetida = request.getParameter("claveRepetida").trim();
		
		/* Guardamos valores en cliente auxiliar */
		cliente.setNombre(nombre);
		cliente.setApellido(apellido);
		cliente.setDni(dni.isEmpty() ? 0 : Integer.parseInt(dni));
		cliente.setCuil(cuil.isEmpty() ? 0 : Long.parseLong(cuil));
		cliente.setNacionalidad(nacionalidad);
		cliente.setNacimiento(LocalDate.parse(fechaNacimiento));
		cliente.getDireccion().setCalle(calle);
		cliente.getDireccion().setNumero(numero.isEmpty() ? 0 :Integer.parseInt(numero));
		cliente.getDireccion().setCodigoPostal(codPos.isEmpty() ? 0 : Integer.parseInt(codPos));
		cliente.getDireccion().getLocalidad().setIdLocalidad(localidadID.isEmpty() ? 0 : Integer.parseInt(localidadID));
		cliente.setTelefono(telefono.isEmpty() ? 0 : Integer.parseInt(telefono));
			
		/*Cambiar Sexo si se seleccionó algo */
		if(!sexo.equals("Seleccionar")) {
			cliente.setSexo(sexo);	
		}
		
		/* Cambiar numero apartamento si se seleccion algo */
		if(!tipoDireccion.equals("Seleccionar")) {
			cliente.getDireccion().setTipoDireccion(TipoDireccion.valueOf(tipoDireccion));
			if(tipoDireccion.equals("Departamento")) {
				cliente.getDireccion().setNumeroDepartamento(apartamento);
			} 
		}
		
		/* Verificar mail */
		if(!correo.equals(cliente.getEmail())) {
			
			try {
				
				boolean existeCorreo = clienteNegocioDaoImp.existeCorreo(correo);
				
				if(!existeCorreo) {
					/* Modificar Correo*/
					cliente.setEmail(correo);
				}
				
			} catch (CorreoException e) {
				/* Correo existe */
				request.setAttribute("cliente", cliente);
				
				request = GUI.mensajes(request, "error", "Correo invalido", "El correo ya existe. Pruebe con otro");

				RequestDispatcher rd = request.getRequestDispatcher("ModificarCliente.jsp");
				rd.forward(request, response);
				
				return null;
			}
		}
		
		/* Verificar que las contraseñas sean iguales o estén ambas */
		if(!contrasenaNueva.isEmpty() && !contrasenaRepetida.isEmpty()) {
			
			if(contrasenaNueva.equals(contrasenaRepetida)) {
				cliente.setContrasenia(contrasenaNueva);
			} else {
				//Retornamos cliente modificado para que corrija datos
				request.setAttribute("cliente", cliente); 
				
				//Popup de error
				request = GUI.mensajes(request, "error", "Contraseña invalida", "La contraseña deben ser iguales");
				
				RequestDispatcher rd = request.getRequestDispatcher("ModificarCliente.jsp");
				rd.forward(request, response);
				
				return null;
			}
			
		} else if(!contrasenaNueva.isEmpty() || !contrasenaRepetida.isEmpty()) {
			//Retornamos cliente modificado para que corrija datos
			request.setAttribute("cliente", cliente); 
						
			//Popup de error
			request = GUI.mensajes(request, "error", "Contraseña invalida", "Debe complatar ambas contraseñas");
			RequestDispatcher rd = request.getRequestDispatcher("ModificarCliente.jsp");
			rd.forward(request, response);
			
			return null;
			
		}
		
		/* Validamos datos luego de colocar los campos en cliente para evitar que el Admin deba completarlos nuevamente */
		if(
			nombre.isEmpty() ||
			apellido.isEmpty() ||
			cliente.getDni() == 0 ||
			cliente.getCuil() == 0 ||
			nacionalidad.isEmpty() ||
			fechaNacimiento.isEmpty() ||
			sexo.isEmpty() ||
			calle.isEmpty() ||
			cliente.getDireccion().getNumero() == 0 ||
			cliente.getDireccion().getCodigoPostal() == 0 ||
			cliente.getTelefono() == 0 ||
			correo.isEmpty()
			) {
				//Retornamos cliente modificado para que corrija datos
				request.setAttribute("cliente", cliente); 
				
				//Popup de error
				request = GUI.mensajes(request, "error", "Datos incorrectos", "Todos los datos deben ser completados");
				
				RequestDispatcher rd = request.getRequestDispatcher("ModificarCliente.jsp");
				rd.forward(request, response);
				
				return null;
			}
		
		return cliente;
	}
	
	/** OBTENER LOCALIDADES Y PROVINCIAS DESDE NEGOCIO **/
	private void obtenerLocalidadYProvincia(HttpServletRequest request) throws Exception {
		LocalidadNegocioDaoImp localidadNegocioDao = new LocalidadNegocioDaoImp();
		ProvinciaNegocioDaoImp provinciaNegocioDao = new ProvinciaNegocioDaoImp();
		
		try {
		//Buscar localidades
    	List<Localidad> listaLocalidad = localidadNegocioDao.obtenerTodas();
		  	
	  	//Buscar provincias
	  	List<Provincia> listaProvincia = provinciaNegocioDao.obtenerTodas();
	  	
	  	/* Session de Localidad y Provincia */
	  	HttpSession session = request.getSession(true);
	  	session.setAttribute("localidades", listaLocalidad);
	  	session.setAttribute("provincias", listaProvincia);
	  	
		} catch(Exception e) {
			throw new ErrorInternoException();
		}
	}
}
