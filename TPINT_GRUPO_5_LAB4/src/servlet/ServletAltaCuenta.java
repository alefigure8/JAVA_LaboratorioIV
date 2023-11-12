package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidad.Cliente;
import entidad.Cuenta;
import entidad.Estado;
import entidad.Movimiento;
import entidad.Operacion;
import entidad.TipoCuenta;
import entidad.TipoMovimiento;
import negocioDaoImp.ClienteNegocioDaoImp;
import negocioDaoImp.CuentaNegocioDaoImp;
import negocioDaoImp.MovimientoNegocioDaoImp;

/**
 * Servlet implementation class ServletAltaCuenta
 */
@WebServlet("/ServletAltaCuenta")
public class ServletAltaCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    ClienteNegocioDaoImp clienteNegocioDao=new ClienteNegocioDaoImp();
    CuentaNegocioDaoImp cuentaNegocioDao=new CuentaNegocioDaoImp();
    MovimientoNegocioDaoImp movimientoNegocioDao= new MovimientoNegocioDaoImp();
    
	
    public ServletAltaCuenta() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		HttpSession session = request.getSession();

		//CLICK BUSCAR DNI
		if(request.getParameter("btnBuscarDni")!=null) {
			String dni=request.getParameter("dni");
	
			//Validamos que no contenga espacios vacios
			if(!dni.trim().isEmpty()) {
				//Validamos que exista ese dni en clientes y lo devolvemos como con un request
				if(clienteNegocioDao.existeDni(Integer.parseInt(dni))){
					//Agregamos cliente
					boolean existedni=true;
					request.setAttribute("existedni",existedni);
					Cliente cliente=new Cliente();
					cliente=clienteNegocioDao.obtenerCliente(Integer.parseInt(dni));
					int idCliente=cliente.getId();
					session.setAttribute("idCliente", idCliente);
					
					System.out.println("id cliente"+idCliente);
					request.setAttribute("cliente", cliente);
					
					//Obtenemos cantidad de cuentas segun el dni
					try {
						int cantidadCuentas=cuentaNegocioDao.cantidadCuentas(idCliente);
						request.setAttribute("cantidadCuentas", cantidadCuentas);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
					
					//Agregamos tipo cuenta
					try {
						List<TipoCuenta> tiposCuenta=(List<TipoCuenta>)cuentaNegocioDao.listarTiposCuenta();
						request.setAttribute("tiposCuenta", tiposCuenta);
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					RequestDispatcher rDispatcher=request.getRequestDispatcher("AltaCuentaCliente.jsp");
					rDispatcher.forward(request, response);
				}else {
					
					boolean existedni=false;
					request.setAttribute("existedni",existedni);;
					RequestDispatcher rDispatcher=request.getRequestDispatcher("AltaCuentaCliente.jsp");
					rDispatcher.forward(request, response);
					
				}
				
				
				
			}
		}
		
		//CLICK ALTA CUENTA CLIENTE
		if(request.getParameter("btnAltaCuentaCliente")!=null) {
			//OBTENER TIPO CUENTA
			String tipoCuenta= request.getParameter("tipoCuenta");
			String nuevoCbu="";
			
			//GENERAR CBU UNICO VERIFICANDO QUE NO EXISTA EL GENERADO
			try {
				nuevoCbu=cuentaNegocioDao.generarCbuUnico();
				session.setAttribute("nuevoCbu", nuevoCbu);
				System.out.println("nuevo cbu:"+nuevoCbu);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		//INSERTAR CUENTA
			
			int idCliente = (int) session.getAttribute("idCliente");
			nuevoCbu = (String) session.getAttribute("nuevoCbu");
			
			Cuenta cuenta = new Cuenta();
			cuenta.setCbu(nuevoCbu);
			cuenta.setFechaCreacion(LocalDate.now());
			cuenta.setIdCliente(idCliente);
			cuenta.setSaldo(10000);
			TipoCuenta tCuenta=new TipoCuenta();
			tCuenta.setId(Integer.parseInt(tipoCuenta));
			try {
				tCuenta.setDescripcion(cuentaNegocioDao.obtenerDescripcion(tCuenta.getId()));
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			cuenta.setTipoCuenta(tCuenta);
			cuenta.setActivo(true);
			try {
				if(cuentaNegocioDao.insertar(cuenta)) {
					System.out.println("Cuenta insertada");
					cuenta.setNumeroCuenta(cuentaNegocioDao.obtenerUltimaInsertada((int) session.getAttribute("idCliente")));
					request.setAttribute("cuenta", cuenta);
				}
				else {
					System.out.println("idCliente:"+idCliente);
				}
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			
			//INSERTAR MOVIMIENTO
			Movimiento movimiento= new Movimiento();
			TipoMovimiento tipoMovimiento=new TipoMovimiento();
			tipoMovimiento.setId(1);
			movimiento.setTipoMovimiento(tipoMovimiento);
			try {
				movimiento.setNumeroReferencia(MovimientoNegocioDaoImp.generarNumeroReferencia
						(movimientoNegocioDao.obtenerUltimoIdMovimiento()));
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			
			Cuenta cuentaMov= new Cuenta();
			cuentaMov.setCbu(nuevoCbu);
			
			//movimiento.setCbudestino(nuevoCbu);
			movimiento.setCuenta(cuentaMov);
			
			movimiento.setMonto(10000);
			movimiento.setOperacion(Operacion.Entrada);
			movimiento.setFechaMovimiento(LocalDate.now());
			Estado estado=new Estado();
			estado.setIdEstado(1);   		//aceptado
			movimiento.setEstado(estado);
			movimiento.setConcepto("Nueva cuenta");
			
			try {
				if(movimientoNegocioDao.insertar(movimiento)) {					
					System.out.println("Insertado todo");
					//REDIRECCION
					RequestDispatcher rDispatcher=request.getRequestDispatcher("DetalleAltaCuenta.jsp");
					rDispatcher.forward(request, response);
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			
		}
		
		
		
		
	}

}
