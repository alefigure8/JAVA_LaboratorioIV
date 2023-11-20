package servlet;

import java.io.IOException;
import java.util.ArrayList;
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
import entidad.Estado;
import entidad.Prestamo;
import entidad.TipoTasa;
import entidad.Usuario;
import negocioDaoImp.ClienteNegocioDaoImp;
import negocioDaoImp.CuentaNegocioDaoImp;
import negocioDaoImp.MovimientoNegocioDaoImp;
import negocioDaoImp.PrestamosNegocioDaoImpl;

@WebServlet("/ServletPrestamosDetalle")
public class ServletPrestamosDetalle extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletPrestamosDetalle() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    MovimientoNegocioDaoImp movimientoNegocio=new MovimientoNegocioDaoImp();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		if(request.getParameter("PrestamoDetalle") != null ) {
			
			
			
			if(request.getParameter("PrestamoDetalle") != null) {
				int IdPrestamo = Integer.parseInt(request.getParameter("PrestamoDetalle").toString());
				
				//NEGOCIOS
				ClienteNegocioDaoImp clienteNegocioDaoImp = new ClienteNegocioDaoImp();
				CuentaNegocioDaoImp cuentaNegocioDaoImp = new CuentaNegocioDaoImp();
				PrestamosNegocioDaoImpl prestamosNegocioDaoImpl = new PrestamosNegocioDaoImpl();
				
				try {
					
					Prestamo prestamo = prestamosNegocioDaoImpl.obteneruno(IdPrestamo);
					
					
					if(prestamo != null) {
						
						//Buscar prestamo	
						Cliente cliente = clienteNegocioDaoImp.obtenerUno(prestamo.getIdCliente());
						
						//Buscar Cuotas
						List<CuotaPrestamo> listaCuotas = prestamosNegocioDaoImpl.obtenerCuotasxprestamo(IdPrestamo);
						
						//Buscamos Cuenta
						Cuenta cuenta = cuentaNegocioDaoImp.obtenerUna(prestamo.getNumeroCuenta());
						
						//AGREGAR NRO.CUENTA SEGUN CADA CUOTA, TIPOMOVIMIENTO=3 PAGO PRESTAMO, NRO REFERENCIA=IDCUOTA
						List<Cuenta> cuentasPagoCuota=new ArrayList<Cuenta>();
						
						for(int x=0;x<listaCuotas.size();x++) {
							cuentasPagoCuota.add(cuentaNegocioDaoImp.obtenerPorMovimientoYreferencia(3, listaCuotas.get(x).getId()));
						}
						
						
						request.setAttribute("cliente", cliente);
						request.setAttribute("cuenta", cuenta);
						request.setAttribute("prestamo", prestamo);
						request.setAttribute("cuotas", listaCuotas);
						
						int cantCuotas=0;
						List<TipoTasa> tasas=(List<TipoTasa>)prestamosNegocioDaoImpl.obtenerTodosTiposTasas();
						for(TipoTasa tipoTasa:tasas) {
							if(tipoTasa.getId()==tipoTasa.getId()) {
								cantCuotas=tipoTasa.getCantCuotas();
							}
						}
						
						request.setAttribute("cantCuotas", cantCuotas);
						
						//AGREGAR NRO.CUENTA SEGUN CADA CUOTA
						request.setAttribute("cuentasPagoCuota", cuentasPagoCuota);
						
						
						
						RequestDispatcher rd = request.getRequestDispatcher("PrestamosDetalle.jsp");
						rd.forward(request, response);
					}
					
				} catch (Exception e) {
					//TODO: Retornar error con mensaje de error
					System.out.println(e.getMessage());
					
					RequestDispatcher rd = request.getRequestDispatcher("PrestamosClientes.jsp");
					rd.forward(request, response);
				}
				
			} else {
				
				RequestDispatcher rd = request.getRequestDispatcher("PrestamosClientes.jsp");
				rd.forward(request, response);
			}
		}
		else {
			System.out.println("El url  es nulo");
		}
		
		/** USUARIO **/
		if(request.getParameter("user") != null) {
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
