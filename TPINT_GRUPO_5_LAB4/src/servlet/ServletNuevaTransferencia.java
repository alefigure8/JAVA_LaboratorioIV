package servlet;

import java.io.IOException;
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

import com.sun.org.apache.bcel.internal.Const;

import Helper.GUI;
import entidad.Cliente;
import entidad.Cuenta;
import entidad.Estado;
import entidad.Movimiento;
import entidad.Operacion;
import entidad.TipoMovimiento;
import excepciones.OperacionCanceladaException;
import excepciones.SaldoInsuficienteException;
import negocioDaoImp.ClienteNegocioDaoImp;
import negocioDaoImp.CuentaNegocioDaoImp;
import negocioDaoImp.MovimientoNegocioDaoImp;

@WebServlet("/ServletNuevaTransferencia")
public class ServletNuevaTransferencia extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletNuevaTransferencia() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/* Session */
		HttpSession session = request.getSession(true);
		Cliente cliente = (Cliente)session.getAttribute("cliente");
		
		/* Cuenta Negocio */
		CuentaNegocioDaoImp cuentaNegocioDaoImp = new CuentaNegocioDaoImp();
		List<Cuenta> cuentasCliente = new ArrayList<Cuenta>();

		try {
			cuentasCliente = cuentaNegocioDaoImp.obtenerCuentasActivasCliente(cliente.getId());
			request.setAttribute("cuentas", cuentasCliente);
		} catch (Exception e) {
			
			/* Mensaje */
			request = GUI.mensajes(request, "error", "Cliente Incorrecto", "Destinatario no encontrado");
			
			RequestDispatcher rd = request.getRequestDispatcher("NuevaTransferencia.jsp?cargacbu=true.jsp");
			rd.forward(request, response);
		}
		
		//Formulario para cargar cbu
		if(request.getParameter("cargacbu") != null) {
				RequestDispatcher rd = request.getRequestDispatcher("NuevaTransferencia.jsp?cargacbu=true.jsp");
				rd.forward(request, response);
		}
		
		/* Se carga el cbu o se elige la cuenta propia a la cual transferir*/
		if(request.getParameter("btnBuscar")!=null) {
			
			if(request.getParameter("cbuDestinatario") != null) {
				
				/* URLS */
				String url = "ServletNuevaTransferencia?cargacbu=true&otraCuenta=true";
				String urlRedirect = "NuevaTransferencia.jsp?cargamonto=true.jsp";
				String urlRegresar ="ServletListaTransferencias?listado=true&todos=true";
				
				try {
					String cbu = request.getParameter("cbuDestinatario");
					
					//Bucamos usuario por CBU
					ClienteNegocioDaoImp clienteNegocioDaoImp = new ClienteNegocioDaoImp();
					Cliente destinatario = clienteNegocioDaoImp.obtenerClientePorCBU(cbu);
					Cuenta cuenta = cuentaNegocioDaoImp.obtenerUnaPorCBU(cbu);
					
					if(destinatario.getActivo() && cuenta.isActivo() && cuentasCliente.size() != 0) {
	
						/* Transferencia a otro destinatario*/
						if(destinatario.getId() != cliente.getId()) {
							
							/* Set Session */
							session.setAttribute("destinatario", destinatario);
							
							/* Request */
							RequestDispatcher rd = request.getRequestDispatcher(urlRedirect);
							rd.forward(request, response);
							
						} 
						/* Tranferencia a cuenta propia */
						else if(destinatario.getId() == cliente.getId() & cuentasCliente.size() > 1){
							
							List<Cuenta> cuentasATransferir =  new ArrayList<Cuenta>();
							
							for(Cuenta cuentaATransferir : cuentasCliente) {
								if(cuentaATransferir.getCbu().compareTo(cbu) != 0 && cuentaATransferir.isActivo()) {
										cuentasATransferir.add(cuentaATransferir);
								}
							}
														
							/* Set Session */
							session.setAttribute("destinatario", destinatario);
							
							request.removeAttribute("cuentas");
							request.setAttribute("cuentas", cuentasATransferir);
							
							/* Request */
							RequestDispatcher rd = request.getRequestDispatcher(urlRedirect);
							rd.forward(request, response);
						} else {
													
							/* Mensaje cliente no cuenta con suficientes cuentas*/
							request = GUI.mensajes(request, "error", "Cuentas Insuficientes", "No cuenta con suficientes cuentas para transferirse a sí mismo");
							
							/* Request */
							RequestDispatcher rd = request.getRequestDispatcher(urlRegresar);
							rd.forward(request, response);
						}
					} else {
						
						/* Mensaje error cliente no encontrado */
						request = GUI.mensajes(request, "error", "Cliente Incorrecto", "Destinatario no encontrado o dado de baja");
						
						/* Request */
						RequestDispatcher rd = request.getRequestDispatcher(url);
						rd.forward(request, response);
					}
				} catch (Exception e) {
					
					/* Mensaje */
					request = GUI.mensajes(request, "error", "Error Base de Datos", e.getMessage());
					
					/* Request */
					RequestDispatcher rd = request.getRequestDispatcher(url);
					rd.forward(request, response);
				}
			}
		}
		
		/* Realizaciï¿½n de la transferencia */
		if(request.getParameter("btnTransferir")!=null) {
			
			if(
				request.getParameter("cbuOrigen")!=null && 
				request.getParameter("monto")!=null &&
				request.getParameter("cbuDestinatario")!=null
				) {
				try {
					
					String cbuCliente = request.getParameter("cbuOrigen");
					String cbuDestinatario = request.getParameter("cbuDestinatario");
					Double monto = Double.valueOf(request.getParameter("monto"));
					String concepto = request.getParameter("concepto");
					
					MovimientoNegocioDaoImp movimientoNegocioDaoImp = new MovimientoNegocioDaoImp();
					boolean movimientoCreado = movimientoNegocioDaoImp.insertarTransferencia(cbuCliente, cbuDestinatario, monto, concepto);
					
					if(movimientoCreado) {
						/* Borramos destinatario de session */
						session.removeAttribute("destinatario");
						
						/* Mensaje de exito */
						request = GUI.mensajes(request, "exito", "Transferencia realizada", "La transferencia se realizo correctamente.");
						
						/* Request */
						RequestDispatcher rd = request.getRequestDispatcher("ServletListaTransferencias?listado=true&todos=true");
						rd.forward(request, response);
					} else {
						
						request = GUI.mensajes(request, "error", "Transferencia fallida", "La transferencia no pudo ser realizada. Intente nuevamente.");
						RequestDispatcher rd = request.getRequestDispatcher("ListaTransferencias.jsp");
						rd.forward(request, response);
					}
					
				} catch (OperacionCanceladaException e) {
					/* Error Operacion cancelada*/
					request = GUI.mensajes(request, "error", "Operacion Cancelada", e.getMessage());
					RequestDispatcher rd = request.getRequestDispatcher("NuevaTransferencia.jsp?cargacbu=true.jsp");//Regresa Lista Trans
					rd.forward(request, response);
				}catch (SaldoInsuficienteException e) {
					/* Error saldo insuficiente*/
					request = GUI.mensajes(request, "error", "Saldo Insuficiente", e.getMessage());
					RequestDispatcher rd = request.getRequestDispatcher("NuevaTransferencia.jsp?cargamonto=true.jsp");//Regresa para insertar otro monto
					rd.forward(request, response);
				}catch (Exception e) {
					/* Errores*/
					request = GUI.mensajes(request, "error", "Error Base de Datos", e.getMessage());
					RequestDispatcher rd = request.getRequestDispatcher("NuevaTransferencia.jsp?cargacbu=true.jsp");
					rd.forward(request, response);
				}
			}
		}
		
		if(request.getParameter("btnCancelar") != null) {
			
			/* removemos datos */
			session.removeAttribute("destinatario");
			request.removeAttribute("cbuDestinatario");
			request.removeAttribute("cuentas");
			
			RequestDispatcher rd = request.getRequestDispatcher("ServletListaTransferencias?listado=true&todos=true");
			rd.forward(request, response);
		}
		
	}
	
}
