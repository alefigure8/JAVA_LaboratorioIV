package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.org.apache.bcel.internal.Const;

import Helper.GUI;
import entidad.Cliente;
import entidad.Cuenta;
import entidad.Estado;
import entidad.Movimiento;
import entidad.Operacion;
import entidad.TipoMovimiento;
import negocioDaoImp.ClienteNegocioDaoImp;
import negocioDaoImp.CuentaNegocioDaoImp;
import negocioDaoImp.MovimientoNegocioDaoImp;

/**
 * Servlet implementation class ServletNuevaTransferencia
 */
@WebServlet("/ServletNuevaTransferencia")
public class ServletNuevaTransferencia extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletNuevaTransferencia() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Cliente del banco
		HttpSession session = request.getSession(true);
		Cliente cliente = (Cliente)session.getAttribute("cliente");
		
		//Formulario para cargar cbu
		if(request.getParameter("cargacbu") != null) {
			
			RequestDispatcher rd = request.getRequestDispatcher("NuevaTransferencia.jsp?cargacbu=true.jsp");
			rd.forward(request, response);
		}
		
		//Llega el CBU para buscar
		if(request.getParameter("cbu") != null) {
			
			try {
				String cbu = request.getParameter("cbu");
				
				//verificar si existe el usuario
				ClienteNegocioDaoImp clienteNegocioDaoImp = new ClienteNegocioDaoImp();
				Cliente destinatario = clienteNegocioDaoImp.obtenerClientePorCBU(cbu);
				
				//Cuentas Cliente
				CuentaNegocioDaoImp cuentaNegocioDaoImp = new CuentaNegocioDaoImp();
				List<Cuenta> cuentasCliente = cuentaNegocioDaoImp.obtenerCuentasCliente(cliente.getId());

				if(destinatario.getNombre() != null && cuentasCliente.size() != 0) {
					if(destinatario.getId() != cliente.getId()) {
						
						request.setAttribute("destinatario", destinatario);
						request.setAttribute("cuentas", cuentasCliente);
						
						RequestDispatcher rd = request.getRequestDispatcher("NuevaTransferencia.jsp?cargamonto=true.jsp");
						rd.forward(request, response);
					} else {
						//Validacion cliente transfiriendose a sí mismo
						request = GUI.mensajes(request, "error", "Cliente Incorrecto", "No puede transferirse a sí mismo");
						RequestDispatcher rd = request.getRequestDispatcher("NuevaTransferencia.jsp?cargacbu=true.jsp");
						rd.forward(request, response);
					}
				} else {
					//Validacion cliente no entonctrado
					request = GUI.mensajes(request, "error", "Cliente Incorrecto", "Destinatario no encontrado");
					RequestDispatcher rd = request.getRequestDispatcher("NuevaTransferencia.jsp?cargacbu=true.jsp");
					rd.forward(request, response);
				}
			} catch (Exception e) {
				request = GUI.mensajes(request, "error", "Error Base de Datos", e.getMessage());
				RequestDispatcher rd = request.getRequestDispatcher("NuevaTransferencia.jsp?cargacbu=true.jsp");
				rd.forward(request, response);
			}
		}
		
		if(request.getParameter("cbuOrigen")!=null && request.getParameter("monto")!=null) {
			try {
				
				String cbuCliente = request.getParameter("cbuOrigen");
				String cbuDestinatario = request.getParameter("cbuDestintario");
				Double monto = Double.valueOf(request.getParameter("monto"));
				String concepto = request.getParameter("concepto");
				
				MovimientoNegocioDaoImp movimientoNegocioDaoImp = new MovimientoNegocioDaoImp();
				boolean movimientoCreado = movimientoNegocioDaoImp.insertarTransferencia(cbuCliente, cbuDestinatario, monto, concepto);
				
				if(movimientoCreado) {
					request = GUI.mensajes(request, "exito", "Transferencia realizada", "La transferencia se realizó correctamente.");
					RequestDispatcher rd = request.getRequestDispatcher("ListaTransferencias.jsp");
					rd.forward(request, response);
				} else {
					
					request = GUI.mensajes(request, "error", "Transferencia fallida", "La transferencia no pudo ser realizada. Intente nuevamente.");
					RequestDispatcher rd = request.getRequestDispatcher("ListaTransferencias.jsp");
					rd.forward(request, response);
				}
				
			} catch (Exception e) {
				request = GUI.mensajes(request, "error", "Error Base de Datos", e.getMessage());
				RequestDispatcher rd = request.getRequestDispatcher("NuevaTransferencia.jsp?cargacbu=true.jsp");
				rd.forward(request, response);
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
