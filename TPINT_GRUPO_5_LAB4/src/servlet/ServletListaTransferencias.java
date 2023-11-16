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


import com.sun.javafx.collections.MappingChange.Map;

import Helper.GUI;
import entidad.Cliente;
import entidad.Destinatario;
import entidad.Movimiento;
import entidad.Operacion;
import entidad.TipoMovimiento;
import negocioDaoImp.ClienteNegocioDaoImp;
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
					RequestDispatcher rd = request.getRequestDispatcher("ServletListaTransferencias?listado=true&todos=true");
					rd.forward(request, response);
				}
			}
			
			/** TRANSFERENCIA DE OPERACION :: ENTRADA Y SALIDA **/
			if(request.getParameter("operacion")!=null) {
				
				String operacion = request.getParameter("operacion");
				
				try {
					//Obtener listado de movimientos por cliente
					List<Movimiento> listadoMovimiento = movimientoNegocioDaoImp.obtenerTransferenciasPorCliente(cliente.getId());
					HashMap<Integer, Destinatario> destinatarios = movimientoNegocioDaoImp.obtenerDestinatariosTransferenciasPorNumeroCliente(cliente.getId());
					
					//Operacion filtrada					
					List<Movimiento> listadoMovimientoFiltrado = obtenerListaPorOperacion(listadoMovimiento, operacion);
					
					//Set atributos
					request.setAttribute("lista", listadoMovimientoFiltrado);
					request.setAttribute("destinatarios", destinatarios);
					
					//Redirigir
					RequestDispatcher rd = request.getRequestDispatcher("ListaTransferencias.jsp");
					rd.forward(request, response);
				} catch (Exception e) {
					//Error Base de Datos
					request = GUI.mensajes(request, "error", "Erro Base de Datos", e.getMessage());
					RequestDispatcher rd = request.getRequestDispatcher("ServletListaTransferencias?listado=true&todos=true");
					rd.forward(request, response);
				}
			}
			
			/** TRANSFERENCIA DE ESTADO :: APROBADA y RECHAZADA **/
			if(request.getParameter("estado")!=null) {
				
				
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

}
