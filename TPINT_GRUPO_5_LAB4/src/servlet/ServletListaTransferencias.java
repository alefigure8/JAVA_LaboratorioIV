package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Helper.GUI;
import entidad.Cliente;
import entidad.Destinatario;
import entidad.Movimiento;

import negocioDaoImp.MovimientoNegocioDaoImp;

@WebServlet("/ServletListaTransferencias")
public class ServletListaTransferencias extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletListaTransferencias() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("listado")!=null) {

			MovimientoNegocioDaoImp movimientoNegocioDaoImp = new MovimientoNegocioDaoImp();
			HttpSession session = request.getSession(true);
			Cliente cliente = (Cliente)session.getAttribute("cliente");
			
			/** TODOS LOS MOVIMIENTOS DE TRANSFERENCIA **/
			if(request.getParameter("todos")!=null) {
				
				try {
					//Obtener listado de movimientos por cliente
					List<Movimiento> listadoMovimiento = movimientoNegocioDaoImp.obtenerTransferenciasPorCliente(cliente.getId());
					HashMap<Integer, Destinatario> destinatarios = movimientoNegocioDaoImp.obtenerDestinatariosTransferenciasPorNumeroCliente(cliente.getId());
					
					request.setAttribute("lista", listadoMovimiento);
					request.setAttribute("destinatarios", destinatarios);
					
					RequestDispatcher rd = request.getRequestDispatcher("ListaTransferencias.jsp");
					rd.forward(request, response);
				} catch (Exception e) {
					//ERROR
					request = GUI.mensajes(request, "error", "Erro Base de Datos", e.getMessage());
					RequestDispatcher rd = request.getRequestDispatcher("ListaTransferencias.jsp");
					rd.forward(request, response);
				}
			}
			
			
			/** FILTRO **/
			if(request.getParameter("btnFiltrarTransferencias")!=null) {
					request.removeAttribute("lista");
					
					/* Cuentas Destino :: Tercero o Propia */
					String cuentasDestino = request.getParameter("cuentasDestino") != null ? request.getParameter("cuentasDestino") : "";
					
					/* Importes :: Mayor, Menor o Igual */
					String importes = request.getParameter("importes") != null ? request.getParameter("importes") : "";
					String rangoImporte = request.getParameter("rangoImporte") != null ? request.getParameter("rangoImporte") : "";
					
					/* Fecha :: Desde y Hasta*/
					String fechaDesde = request.getParameter("prestamoDesde") != null ? request.getParameter("prestamoDesde") : "";
					String fechaHasta = request.getParameter("prestamoHasta") != null ? request.getParameter("prestamoHasta") : "";
										
					try {
						/* Listado de Movimientos por Transferencia */
						List<Movimiento> listadoMovimiento = movimientoNegocioDaoImp.obtenerTransferenciasPorCliente(cliente.getId());
						HashMap<Integer, Destinatario> destinatarios = movimientoNegocioDaoImp.obtenerDestinatariosTransferenciasPorNumeroCliente(cliente.getId());
						List<Movimiento> listadoMovimientoFiltrado = listadoMovimiento;
						
						if(!cuentasDestino.equals("todas")) {
							listadoMovimientoFiltrado = obtenerListaPorDestino(listadoMovimiento, cuentasDestino, cliente, destinatarios);
						}				
						
						//Set atributos
						request.setAttribute("lista", listadoMovimientoFiltrado);
						request.setAttribute("destinatarios", destinatarios);
						
						//Redirigir
						RequestDispatcher rd = request.getRequestDispatcher("ListaTransferencias.jsp");
						rd.forward(request, response);
					} catch (Exception e) {
						//Error Base de Datos
						request = GUI.mensajes(request, "error", "Erro Base de Datos", e.getMessage());
						RequestDispatcher rd = request.getRequestDispatcher("ListaTransferencias.jsp");
						rd.forward(request, response);
					}
				}
			}
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	/** FILTRAR LISTADO POR OPERACION **/
	protected List<Movimiento> obtenerListaPorOperacion(List<Movimiento> listado, String operacion){

			List<Movimiento> listadoMovimiento = new ArrayList<Movimiento>();
			
			for(Movimiento movimiento : listado) {
				if(movimiento.getOperacion().equals(operacion)) {
					listadoMovimiento.add(movimiento);
				}
			}
			
			return listadoMovimiento;
	}
	
	/** FILTRAR LISTADO POR DESTINO **/
	protected List<Movimiento> obtenerListaPorDestino(List<Movimiento> listado, String destino, Cliente cliente, HashMap<Integer, Destinatario> destinatario){
			System.out.println("FUNCION DESTINO " + destino);
			List<Movimiento> listadoMovimiento = new ArrayList<Movimiento>();
			
			if(destino.equals("terceros")) {
				for(Movimiento movimiento : listado) {
					if(destinatario.containsKey(movimiento.getNumeroReferencia())) {
						listadoMovimiento.add(movimiento);
					}
				}
			} else {
				for(Movimiento movimiento : listado) {
					if(!destinatario.containsKey(movimiento.getNumeroReferencia())) {
						listadoMovimiento.add(movimiento);
					}
				}
			}
			
			return listadoMovimiento;
	}

}
