package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cliente;
import entidad.Cuenta;
import entidad.CuotaPrestamo;
import entidad.Prestamo;
import entidad.Usuario;
import negocioDaoImp.ClienteNegocioDaoImp;
import negocioDaoImp.CuentaNegocioDaoImp;
import negocioDaoImp.PrestamosNegocioDaoImpl;

@WebServlet("/ServletPrestamosDetalle")
public class ServletPrestamosDetalle extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletPrestamosDetalle() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/** ADMIN **/
		if(request.getParameter("admin") != null) {

			if(request.getParameter("idPrestamo") != null) {
				int IdPrestamo = Integer.parseInt(request.getParameter("idPrestamo"));
				
				//NEGOCIOS
				ClienteNegocioDaoImp clienteNegocioDaoImp = new ClienteNegocioDaoImp();
				CuentaNegocioDaoImp cuentaNegocioDaoImp = new CuentaNegocioDaoImp();
				PrestamosNegocioDaoImpl prestamosNegocioDaoImpl = new PrestamosNegocioDaoImpl();
				
				try {
					
					Prestamo prestamo = prestamosNegocioDaoImpl.obteneruno(IdPrestamo);
					System.out.println("ID CLIENTE" + prestamo.getNumeroCuenta());
					
					if(prestamo != null) {
						
						//Buscar prestamo	
						Cliente cliente = clienteNegocioDaoImp.obtenerUno(prestamo.getIdCliente());
						
						//Buscar Cuotas
						List<CuotaPrestamo> listaCuotas = prestamosNegocioDaoImpl.obtenerCuotasxprestamo(IdPrestamo);
						
						//Buscamos Cuenta
						Cuenta cuenta = cuentaNegocioDaoImp.obtenerUna(prestamo.getNumeroCuenta());
						
						request.setAttribute("cliente", cliente);
						request.setAttribute("cuenta", cuenta);
						request.setAttribute("prestamo", prestamo);
						request.setAttribute("cuotas", listaCuotas);
						
						RequestDispatcher rd = request.getRequestDispatcher("PrestamosDetalle.jsp");
						rd.forward(request, response);
					}
					
				} catch (Exception e) {
					//TODO: Retornar error con mensaje de error
					System.out.println(e.getMessage());
					RequestDispatcher rd = request.getRequestDispatcher("PrestamosDetalle.jsp");
					rd.forward(request, response);
				}
				
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("PrestamosDetalle.jsp");
				rd.forward(request, response);
			}
		}
		
		
		/** USUARIO **/
		if(request.getParameter("user") != null) {
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
