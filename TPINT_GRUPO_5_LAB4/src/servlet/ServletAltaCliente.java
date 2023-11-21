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

import entidad.Cliente;
import entidad.Direccion;
import entidad.Localidad;
import entidad.Provincia;
import entidad.TipoAcceso;
import entidad.TipoDireccion;
import excepciones.CorreoException;
import negocioDaoImp.ClienteNegocioDaoImp;
import negocioDaoImp.LocalidadNegocioDaoImp;
import negocioDaoImp.ProvinciaNegocioDaoImp;


@WebServlet("/ServletAltaCliente")
public class ServletAltaCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletAltaCliente() {
        super();
       
    }
    
    ClienteNegocioDaoImp clienteNegocioDao= new ClienteNegocioDaoImp();
    ProvinciaNegocioDaoImp provinciaNegocio=new ProvinciaNegocioDaoImp();
    LocalidadNegocioDaoImp localidadNegocio=new LocalidadNegocioDaoImp();

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		//OBTENER PROVINCIAS
		if(request.getParameter("AltaCliente")!=null) {
			ProvinciaNegocioDaoImp provinciaNegocio= new ProvinciaNegocioDaoImp();
        	try {
				List<Provincia> provincias= (List<Provincia>)provinciaNegocio.obtenerTodas();
				session.setAttribute("provincias", provincias);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
		//OBTENER LOCALIDADES 
		if(request.getParameter("AltaCliente")!=null) {
			LocalidadNegocioDaoImp localidadNegocio=new LocalidadNegocioDaoImp();
			List<Localidad> localidades= new ArrayList<Localidad>();
			try {
				localidades=(List<Localidad>)localidadNegocio.obtenerTodas();
				session.setAttribute("localidades", localidades);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		RequestDispatcher rDispatcher=request.getRequestDispatcher("AltaCliente.jsp");
		rDispatcher.forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getParameter("btnAltaCliente") != null) {
		    
		    String dni = request.getParameter("dni");
		    String correo = request.getParameter("correo");
		    Cliente cliente = new Cliente();

		    try {
		        cargarCliente(request, cliente);
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    // dni
		    if (!dni.trim().isEmpty()) {
		        if (!clienteNegocioDao.existeDni(Integer.parseInt(dni))) {
		            try {
		                // correo
		                if (!clienteNegocioDao.existeCorreo(correo)) {
		                    // usuario
		                    if (!clienteNegocioDao.existeSoloUsuario(request.getParameter("usuario"))) {
		                        if (clienteNegocioDao.insertar(cliente)) {
		                            
		                            request.setAttribute("clienteAgregado", cliente);
		                            System.out.println("Cliente insertado");
		                        } else {
		                            System.out.println("No se pudo insertar");
		                        }
		                    } else {
		                        
		                        request.setAttribute("clienteAmodificar", cliente);
		                        request.setAttribute("errorUsuario", "El Usuario ya existe. Introduce un Usuario diferente.");
		                    }
		                } else {
		                   
		                    request.setAttribute("clienteAmodificar", cliente);
		                }
		            } catch (CorreoException e) {
		            	
		            	request.setAttribute("clienteAmodificar", cliente);
		                request.setAttribute("errorCorreo", e.getMessage());
		            } catch (Exception e) {
		                e.printStackTrace();
		                
		            }
		        } else {
		            
		            request.setAttribute("clienteAmodificar", cliente);
		            request.setAttribute("error", "El DNI ya existe. Introduce un DNI diferente.");
		        }
		    }
		}
		
		RequestDispatcher rDispatcher=request.getRequestDispatcher("AltaCliente.jsp");
		rDispatcher.forward(request, response);
	}
	
	//CARGAR CLIENTE
	private void cargarCliente(HttpServletRequest request, Cliente cliente) throws SQLException {
		LocalidadNegocioDaoImp localidadNegocio= new LocalidadNegocioDaoImp();
		ProvinciaNegocioDaoImp provinciaNegocio= new ProvinciaNegocioDaoImp();
		//CLIENTE
		cliente.setNombre(request.getParameter("nombre"));
		cliente.setApellido(request.getParameter("apellido"));
		cliente.setTipoAcceso(TipoAcceso.Cliente);
		cliente.setFechaAlta(LocalDate.now());
		cliente.setDni(Integer.parseInt(request.getParameter("dni")));
		cliente.setCuil(Long.parseLong(request.getParameter("cuil")));
		cliente.setNacionalidad(request.getParameter("nacionalidad"));
		String genero = request.getParameter("sexo");
		cliente.setSexo(genero);
		cliente.setEmail(request.getParameter("correo"));
		cliente.setTelefono(Long.parseLong(request.getParameter("telefono")));
		LocalDate fechaNacimiento = LocalDate.parse(request.getParameter("fechaNacimiento"));
		cliente.setNacimiento(fechaNacimiento);
		
		//DOMICILIO
		Provincia provincia= new Provincia();
		provincia.setIdProvincia(Integer.parseInt(request.getParameter("provincia")));
		provincia.setNombre(provinciaNegocio.obtenerUna(provincia.getIdProvincia()).getNombre());
		
		Localidad localidad= new Localidad();
		localidad.setIdLocalidad(Integer.parseInt(request.getParameter("localidad")));
		localidad.setNombre(localidadNegocio.obtenerUna(localidad.getIdLocalidad()).getNombre());
		
		Direccion direccion= new Direccion();
		direccion.setCalle(request.getParameter("calle"));
		direccion.setCodigoPostal(Integer.parseInt(request.getParameter("codigoPostal")));
		direccion.setLocalidad(localidad);
		direccion.setNumero(Integer.parseInt(request.getParameter("numero")));
		String numeroDepartamento = request.getParameter("numeroDepartamento").trim();
		direccion.setNumeroDepartamento(numeroDepartamento != null ? numeroDepartamento : "");
		direccion.setProvincia(provincia);
		direccion.setTipoDireccion(TipoDireccion.valueOf(request.getParameter("tipoDireccion")));
		cliente.setDireccion(direccion);
		
		
		//USUARIO
		cliente.setUsuario(request.getParameter("usuario"));
		cliente.setContrasenia(request.getParameter("contraseña"));
		
	}

}
