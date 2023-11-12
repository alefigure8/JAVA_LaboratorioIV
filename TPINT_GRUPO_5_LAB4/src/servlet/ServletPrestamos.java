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
import javax.xml.bind.ParseConversionEvent;

import com.sun.corba.se.impl.encoding.CodeSetComponentInfo.CodeSetComponent;

import Helper.GUI;
import entidad.Cliente;
import entidad.Cuenta;
import entidad.Estado;
import entidad.Movimiento;
import entidad.Operacion;
import entidad.Prestamo;
import entidad.TipoMovimiento;
import negocioDaoImp.ClienteNegocioDaoImp;
import negocioDaoImp.CuentaNegocioDaoImp;
import negocioDaoImp.MovimientoNegocioDaoImp;
import negocioDaoImp.PrestamosNegocioDaoImpl;



@WebServlet("/ServletPrestamos")
public class ServletPrestamos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletPrestamos() {
        super();
       
    }
    
    MovimientoNegocioDaoImp movimientoNegocio=new MovimientoNegocioDaoImp();
    PrestamosNegocioDaoImpl prestamosNegocio=new PrestamosNegocioDaoImpl();
    ClienteNegocioDaoImp clienteNegocio=new ClienteNegocioDaoImp();
    CuentaNegocioDaoImp cuentaNegocio=new CuentaNegocioDaoImp();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//Obtenemos prestamos
		if(request.getParameter("Prestamos")!=null && request.getParameter("btnFiltrarPrestamos")==null ) {
			cargarPrestamos(request);
			
		}
		
		 if(request.getParameter("btnFiltrarPrestamos")!=null) {
			
			String estadoSeleccionado=request.getParameter("Estados");
			System.out.println("Estado "+estadoSeleccionado);
			//Listas limpias
			List<Prestamo>prestamos= (List<Prestamo>)session.getAttribute("listaPrestamos");
			List<Cliente> clientes = (List<Cliente>)session.getAttribute("listaClientes");
			
			//Listas para filtrar
			List<Prestamo>prestamosFiltrados= new ArrayList<>();
			List<Cliente> clientesFiltrados = new ArrayList<>();
			
			if (estadoSeleccionado.equals("Todos los Estados")) {
				session.setAttribute("prestamos", session.getAttribute("listaPrestamos"));
				session.setAttribute("clientes", session.getAttribute("listaClientes"));
			}
			else {
				for(int x=0;x<prestamos.size();x++) {
					
					if(prestamos.get(x).getEstado().getDescripcion().equals(estadoSeleccionado)){
						prestamosFiltrados.add(prestamos.get(x));
						clientesFiltrados.add(clientes.get(x));
					}
					
				}
				session.setAttribute("prestamos", prestamosFiltrados);
				session.setAttribute("clientes", clientesFiltrados);
				
			}
			
			session.setAttribute("prestamoSeleccionado", estadoSeleccionado);
			
		}
		 
		 
		
		RequestDispatcher rDispatcher=request.getRequestDispatcher("PrestamosClientes.jsp");
		rDispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//ACEPTAR
		if(request.getParameter("btnAprobarPrestamo")!=null) {
			int idPrestamo=Integer.parseInt(request.getParameter("idPrestamo").toString());
			System.out.println("PRESMTAMO ok");
			//ACEPTAMOS PRESTAMO
			if(prestamosNegocio.aceptar(idPrestamo)) {
				//INSERTAMOS CUOTAS
				if(prestamosNegocio.insertarcuotas(prestamosNegocio.obteneruno(idPrestamo))) {
					//GENERAR MOVIMIENTO EN LA CUENTA DEL PRESTAMO (nro. Referencia = idPrestamo)
					TipoMovimiento tipoMovimiento=new TipoMovimiento();
					tipoMovimiento.setId(2);//ALTA DE PRESTAMO
					
					int numeroCuenta=Integer.parseInt(request.getParameter("numCuenta"));
					
					String cbudestino="";
					try {
						cbudestino = cuentaNegocio.obtenerUna(numeroCuenta).getCbu();
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
					
					Double monto= Double.parseDouble(request.getParameter("montoPedido"));
					Estado estado=new Estado();
					estado.setIdEstado(1);
					
					Cuenta cuenta= new Cuenta();
					cuenta.setCbu(cbudestino);
					
					
					Movimiento movimiento=new Movimiento(tipoMovimiento, idPrestamo, /*cbudestino,*/ cuenta, monto, 
							Operacion.Entrada, LocalDate.now(), estado, "Alta de prestamo");
							
					try {
						if(movimientoNegocio.insertar(movimiento)) {
							cargarPrestamos(request);
							request=GUI.mensajes(request, "exito", "Prestamo aceptado", "El prestamo se aceptó correctamente");
						}
					} catch (SQLException e) {
						
						e.printStackTrace();
					}
					
				}
				
			}
			else {
				request=GUI.mensajes(request, "error", "Prestamos", "No se pudo aceptar el prestamo");
			}
			
		}
		
		
		//RECHAZAR
		if(request.getParameter("btnRechazarPrestamo")!=null) {
			System.out.println("PRESMTAMO no");
			int idPrestamo=Integer.parseInt(request.getParameter("idPrestamo").toString());
			if(prestamosNegocio.rechazar(idPrestamo)) {
				cargarPrestamos(request);
				request=GUI.mensajes(request, "exito", "Prestamo rechazado", "El prestamo se rechazó correctamente");
				
			}
			else {
				request=GUI.mensajes(request, "error", "Prestamos", "No se pudo rechazar el prestamo");
			}
			
		}
		
		RequestDispatcher rDispatcher=request.getRequestDispatcher("PrestamosClientes.jsp");
		rDispatcher.forward(request, response);
	}

	private void cargarPrestamos(HttpServletRequest request) {
		HttpSession session = request.getSession();
		List<Prestamo>prestamos= prestamosNegocio.obtenerTodos();
		List<Cliente> clientes = new ArrayList<Cliente>();
		//Obtenemos clientes s/prestamo
		for(Prestamo prestamo : prestamos) {
			int idCliente=prestamo.getIdCliente();
			
			clientes.add(clienteNegocio.obtenerUno(idCliente));
			
		}
		session.setAttribute("prestamos", prestamos);
		session.setAttribute("clientes", clientes);
		//Session limpia
		session.setAttribute("listaPrestamos", prestamos);
		session.setAttribute("listaClientes", clientes);
	}
	
}
