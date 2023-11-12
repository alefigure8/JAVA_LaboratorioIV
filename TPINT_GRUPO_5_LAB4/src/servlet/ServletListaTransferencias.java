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

import com.mysql.cj.Session;
import com.sun.javafx.collections.MappingChange.Map;

import entidad.Cliente;
import entidad.Movimiento;
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
			ClienteNegocioDaoImp clienteNegocioDaoImp = new ClienteNegocioDaoImp();
			HttpSession session = request.getSession(true);
			
			Cliente cliente = (Cliente)session.getAttribute("cliente");
			
			if(request.getParameter("todos")!=null) {
				try {
					List<Movimiento> listadoMovimiento = movimientoNegocioDaoImp.obtenerPorCliente(cliente.getId());
					List<Movimiento> listadoTransferencia = new ArrayList<Movimiento>();
					HashMap<Integer, String> destinatarios = new HashMap<Integer, String>();
					
					//Obtenemos solamente las transferencias -- TODO: hacerlo desde DAO
					for(Movimiento movimiento : listadoMovimiento) {
						
						if(movimiento.getTipoMovimiento().getDescripcion().equals("Transferencia")) {
							
							//Buscamos cliente Destinatario
							List<Movimiento> movimientosPorReferencias = movimientoNegocioDaoImp.obtenerPorNumeroDeReferencia(movimiento.getNumeroReferencia());
							
							int IDCliente = 0;
							
							for(Movimiento movimiento2 : movimientosPorReferencias) {
								if(movimiento2.getCuenta().getIdCliente() != cliente.getId()) {
									IDCliente = movimiento2.getCuenta().getIdCliente();
								}
							}
							
							Cliente destinatario = clienteNegocioDaoImp.obtenerUno(IDCliente);
							
							destinatarios.put(movimiento.getNumeroReferencia(), destinatario.getNombre() + " " + destinatario.getApellido());

							listadoTransferencia.add(movimiento);
						}
					}
					
					
					System.out.println(listadoMovimiento.size());

					request.setAttribute("lista", listadoTransferencia);
					request.setAttribute("destinatarios", destinatarios);
					
					RequestDispatcher rd = request.getRequestDispatcher("ListaTransferencias.jsp");
					rd.forward(request, response);
				} catch (Exception e) {
					//ERROR
					System.out.println(e.getMessage());
				}
				
			}
			
			if(request.getParameter("cbu")!=null) {
				
				
			}
			

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
