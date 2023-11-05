package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cliente;
import entidad.Cuenta;
import entidad.TipoCuenta;
import negocioDaoImp.ClienteNegocioDaoImp;
import negocioDaoImp.CuentaNegocioDaoImp;


@WebServlet("/ServletModificarCuenta")
public class ServletModificarCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ServletModificarCuenta() {
        super();
       
    }
    ClienteNegocioDaoImp clienteNegocioDao=new ClienteNegocioDaoImp();
    CuentaNegocioDaoImp cuentaNegocioDao=new CuentaNegocioDaoImp();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		if(request.getParameter("btnModificarCuentaCiente")!=null) {
			//OBTENEMOS CLIENTE
			String dni=request.getParameter("dni");
			Cliente cliente=new Cliente();
			cliente=clienteNegocioDao.obtenerCliente(Integer.parseInt(dni));
			Cuenta cuenta= new Cuenta();
			try {
				cuenta=cuentaNegocioDao.obtenerUna(Integer.parseInt(request.getParameter("cuenta")));
				
			} catch (NumberFormatException e) {
				
				e.printStackTrace();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			//ACTUALIZAMOS TIPO CUENTA
			TipoCuenta tipoCuenta=new TipoCuenta();
			String tc= request.getParameter("tipoCuenta");
			tipoCuenta.setId(Integer.parseInt(tc));
			cuenta.setTipoCuenta(tipoCuenta);
			
			//ACTUALIZAMOS ESTADO ACTIVO
			String activa = request.getParameter("activa");
		    boolean estado = Boolean.parseBoolean(activa);
		    cuenta.setActivo(estado);
		    
		    try {
				if(cuentaNegocioDao.editar(cuenta)) {
					System.out.println("Cuenta modificada");
					request.setAttribute("cliente", cliente);
					request.setAttribute("cuenta", cuenta);
					RequestDispatcher rDispatcher=request.getRequestDispatcher("AltaCuentaCliente.jsp");//Cambiar a listado cuentas
					rDispatcher.forward(request, response);
				}
				else {
					System.out.println("No se pudo modificar");
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
		
	}

}
