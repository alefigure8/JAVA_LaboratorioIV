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

import daoImp.LocalidadDaoImp;
import entidad.Cliente;
import entidad.Localidad;
import entidad.TipoDireccion;
import negocioDaoImp.ClienteNegocioDaoImp;


@WebServlet("/servletModificarCliente")
public class servletModificarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public servletModificarCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Negocio cliente
		ClienteNegocioDaoImp clienteNegocioDaoImp = new ClienteNegocioDaoImp();
		
		//Buscar cliente para modificar
		if(request.getParameter("obtener") != null) {	
			try {
				String id = request.getParameter("ID");
				
				//Buscar cliente por id
				Cliente cliente = clienteNegocioDaoImp.obtenerUno(Integer.parseInt(id));
				
				//Buscar localidades
				LocalidadDaoImp localidadNegocioDao = new LocalidadDaoImp();
	        	List<Localidad> listaLocalidad = new ArrayList<Localidad>();
			  	listaLocalidad = localidadNegocioDao.obtenerTodas();
				
				//Redirigir a página de Modificar cliente
				request.setAttribute("cliente", cliente);
				request.setAttribute("localidades", listaLocalidad);
				RequestDispatcher rd = request.getRequestDispatcher("ModificarCliente.jsp");
				rd.forward(request, response);
			} catch (Exception e) {
				//Popup de error
				request.setAttribute("tipo", "error");
				request.setAttribute("titulo", "Erro Base de Datos");
				request.setAttribute("mensaje", e.getMessage());
				RequestDispatcher rd = request.getRequestDispatcher("ModificarCliente.jsp");
				rd.forward(request, response);
			}
		}
		
		//Modificar clientes
		if(request.getParameter("modificar") != null) {
					
			Cliente cliente = new Cliente();
			try {
				
				//Modificar Cliente
				cliente = obtenerCliente(request, response);
				
				if(cliente != null)
					clienteNegocioDaoImp.editar(cliente);
				
				//Popup de exito
				request.setAttribute("tipo", "exito");
				request.setAttribute("titulo", "Cliente modificado");
				request.setAttribute("mensaje", "El cliente se modificó correctamente");
				RequestDispatcher rd = request.getRequestDispatcher("ListadoClientes.jsp");
				rd.forward(request, response);
			} catch (Exception e) {
				//Retornamos cliente modificado para que corrija datos
				request.setAttribute("cliente", cliente); 
				
				//Popup de error
				request.setAttribute("tipo", "error");
				request.setAttribute("titulo", "Error Base de Datos");
				request.setAttribute("mensaje", e.getMessage());
				RequestDispatcher rd = request.getRequestDispatcher("ModificarCliente.jsp");
				rd.forward(request, response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private Cliente obtenerCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		String id = request.getParameter("ID");
		//Negocio cliente
		ClienteNegocioDaoImp clienteNegocioDaoImp = new ClienteNegocioDaoImp();
		
		//Buscar cliente por id
		Cliente cliente = clienteNegocioDaoImp.obtenerUno(Integer.parseInt(id));
		
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
		String contrasenaVieja = request.getParameter("claveVieja").trim();
		String contrasenaNueva = request.getParameter("claveNueva").trim();
		
		cliente.setNombre(nombre);
		cliente.setApellido(apellido);
		cliente.setDni(dni.isEmpty() ? 0 : Integer.parseInt(dni));
		cliente.setCuil(cuil.isEmpty() ? 0 : Integer.parseInt(cuil));
		cliente.setNacionalidad(nacionalidad);
		cliente.setNacimiento(LocalDate.parse(fechaNacimiento));
		cliente.getDireccion().setCalle(calle);
		cliente.getDireccion().setNumero(numero.isEmpty() ? 0 :Integer.parseInt(numero));
		cliente.getDireccion().setCodigoPostal(codPos.isEmpty() ? 0 : Integer.parseInt(codPos));
		cliente.getDireccion().getLocalidad().setIdLocalidad(localidadID.isEmpty() ? 0 : Integer.parseInt(localidadID));
		cliente.setTelefono(telefono.isEmpty() ? 0 : Integer.parseInt(telefono));
		cliente.setEmail(correo);
				
		//Cambiar Sexo si se seleccionó algo
		if(sexo.compareTo("Seleccionar") != 0) {
			cliente.setSexo(sexo);	
		}
		
		//Cambiar numero apartamento si se seleccion algo
		if(tipoDireccion.compareTo("Seleccionar") != 0) {
			cliente.getDireccion().setTipoDireccion(TipoDireccion.valueOf(tipoDireccion));
			if(tipoDireccion.compareTo("Departamento") == 0) {
				cliente.getDireccion().setNumeroDepartamento(apartamento);
			} 
		}
		
		//cambiar contrasena si la vieja contrasena es correcta
		if(!contrasenaVieja.isEmpty()) {
			
			boolean cambiarContrasena = false;
			
			try {
				cambiarContrasena = clienteNegocioDaoImp.existeUsuario(cliente.getUsuario(), contrasenaVieja);
			} catch (SQLException e) {
				//Retornamos cliente modificado para que corrija datos
				request.setAttribute("cliente", cliente); 
				throw e;
			}
			
			if(cambiarContrasena) {
				cliente.setContrasenia(contrasenaNueva);
			} else {
				//Retornamos cliente modificado para que corrija datos
				request.setAttribute("cliente", cliente); 
				
				//Popup de error
				request.setAttribute("tipo", "error");
				request.setAttribute("titulo", "Contraseña invalida");
				request.setAttribute("mensaje", "La contraseña no puede ser modificada");
				RequestDispatcher rd = request.getRequestDispatcher("ModificarCliente.jsp");
				rd.forward(request, response);
				
				return null;
			}
		}
		
		//Validamos datos luego de colocar los campos en cliente para evitar que el Admin deba completarlos nuevamente
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
				request.setAttribute("tipo", "error");
				request.setAttribute("titulo", "Datos incorrectos");
				request.setAttribute("mensaje", "Todos los datos deben ser completados");
				RequestDispatcher rd = request.getRequestDispatcher("ModificarCliente.jsp");
				rd.forward(request, response);
				
				return null;
			}
		
		return cliente;
	}

}
