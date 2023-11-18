package servlet;

import java.io.IOException;
import java.sql.SQLException;
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
import negocioDaoImp.ClienteNegocioDaoImp;
import negocioDaoImp.MovimientoNegocioDaoImp;

@WebServlet("/ServletDetalleTransferencia")
public class ServletDetalleTransferencia extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletDetalleTransferencia() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MovimientoNegocioDaoImp movimientoNegocioDaoImp = new MovimientoNegocioDaoImp();
		HttpSession session = request.getSession(true);
		Cliente cliente = (Cliente)session.getAttribute("cliente");
		
		if(request.getParameter("numeroReferencia") != null){
			int numeroReferencia = Integer.parseInt(request.getParameter("numeroReferencia"));
			int idMovimiento = Integer.parseInt(request.getParameter("id"));
			
			try {
				List<Movimiento> movimientoTransferencia = movimientoNegocioDaoImp.obtenerTransferenciasPorCliente(cliente.getId());
				HashMap<Integer, Destinatario> destinatarios = movimientoNegocioDaoImp.obtenerDestinatariosTransferenciasPorNumeroCliente(cliente.getId());
				Movimiento transferencia = new Movimiento();
				
				for(Movimiento movimiento : movimientoTransferencia) {
					if(movimiento.getId() == idMovimiento) {
						transferencia = movimiento;
					}
				}
				
				request.setAttribute("transferencia", transferencia);
				request.setAttribute("destinatario", destinatarios);
				
				RequestDispatcher rd = request.getRequestDispatcher("DetalleTransferencia.jsp");
				rd.forward(request, response);
			} catch (Exception e) {
				request = GUI.mensajes(request, "error", "Erro Base de Datos", e.getMessage());
				RequestDispatcher rd = request.getRequestDispatcher("ListaTransferencias.jsp");
				rd.forward(request, response);
			}
			
		}
		
					
					
			
	
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
