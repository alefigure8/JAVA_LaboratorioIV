package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Helper.GUI;
import entidad.Cliente;
import entidad.TipoAcceso;
import entidad.Usuario;
import negocioDaoImp.ClienteNegocioDaoImp;

@WebServlet("/servletLogin")
public class servletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public servletLogin() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		if(request.getParameter("btnAceptar") != null) {
			String usuario = request.getParameter("usuario");
			String contrasena = request.getParameter("pass");
			boolean existeCliente = false;
			ClienteNegocioDaoImp clienteNegocio = new ClienteNegocioDaoImp();

			try {
				existeCliente = clienteNegocio.existeUsuario(usuario, contrasena);
			} catch (Exception e) {
				//Mandamos mensaje de error
				request = GUI.mensajes(request, "error", "Erro Base de Datos", e.getMessage());
				RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
				rd.forward(request, response);
			}
			
			if(existeCliente) {
				
				try {
					Usuario usuarioEncontrado = clienteNegocio.obtenerUsuarioPorUsuario(usuario);
					
					if(usuarioEncontrado.getActivo()) {
						
						//Guardamos en Session
						HttpSession session = request.getSession(true);
						session.setAttribute("usuario", usuarioEncontrado);
						
						//Guardamos cliente
						if(usuarioEncontrado.getTipoAcceso().compareTo(TipoAcceso.Cliente) == 0) {
							Cliente cliente = clienteNegocio.obtenerUno(usuarioEncontrado.getId());
							session.setAttribute("cliente", cliente);
						}
						
						/** REDIRECCIONAMIENTO **/
						if(usuarioEncontrado.getTipoAcceso() == TipoAcceso.Administrador) {
							//Redirigimos a perfil banco
							RequestDispatcher rd = request.getRequestDispatcher("PerfilBanco.jsp");
							rd.forward(request, response);	
						} else if(usuarioEncontrado.getTipoAcceso() == TipoAcceso.Cliente) {
							//Redirigimos a Home Clinet
							request.setAttribute("homecliente", "homecliente");
							RequestDispatcher rd = request.getRequestDispatcher("/ServletHomeCliente?homecliente=homecliente");
							rd.forward(request, response);	
						}
						
					}
					
					//Redirigimos a p�gina de Login en caso de que no tenga TipoAcceso o este Inactivo
					request = GUI.mensajes(request, "error", "Sin Privilegios", "Su usuario no tiene suficientes privilegios para acceder");
					RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
					rd.forward(request, response);	
					
					
				} catch (Exception e) {
					//Mandamos mensaje de error
					request = GUI.mensajes(request, "error", "Error Base de Datos", e.getMessage());
					RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
					rd.forward(request, response);	
				}
				
			}
			
			//El usuario no existe
			request.setAttribute("existeCliente", existeCliente);
			RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
			rd.forward(request, response);		
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
