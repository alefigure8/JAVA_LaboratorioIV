package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import negocioDaoImp.CuentaNegocioDaoImp;
import negocioDaoImp.ClienteNegocioDaoImp;
import negocioDaoImp.PrestamosNegocioDaoImpl;
import negocioDaoImp.MovimientoNegocioDaoImp;;
/**
 * Servlet implementation class ServletEstadisticasBancos
 */
@WebServlet("/ServletEstadisticasBancos")
public class ServletEstadisticasBancos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEstadisticasBancos() {
        super();
        // TODO Auto-generated constructor stub
    }
  
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		CuentaNegocioDaoImp negocioCuenta = new CuentaNegocioDaoImp();
		  ClienteNegocioDaoImp negocioCliente = new ClienteNegocioDaoImp();
		PrestamosNegocioDaoImpl negocioPrestamo = new PrestamosNegocioDaoImpl();
		MovimientoNegocioDaoImp negocioMovimiento = new MovimientoNegocioDaoImp();
		
		try {
			int totalCuentaCorriente = negocioCuenta.obtenerTotalSaldoCuentaCorriente();
        int totalCuentas = negocioCuenta.obtenerTotalSaldoCuentas();
		int	totalCajaAhorro = negocioCuenta.obtenerTotalSaldoCajaAhorro();
		int clientesNuevos = 0;	
		int cuentasNuevas = 0;
		int cuentasNuevasCaja = 0;
		int cuentasNuevasCorriente = 0;
		int prestamosCancelados = 0;
		int prestamosNoCancelados = 0;
		int cantidadTransferencias = 0;
		double totalMontoTransferencias = 0;
			// Estableces los atributos en el request
        request.setAttribute("totalCajaAhorro", totalCajaAhorro);
        request.setAttribute("totalCuentaCorriente", totalCuentaCorriente);
        request.setAttribute("totalCuentas", totalCuentas);
        String anio = " ";
        String mesSeleccionado = " ";
        
        if (request.getParameter("btnEnviar") != null) {
        	 
        	 anio = request.getParameter("anio");
             mesSeleccionado = request.getParameter("Mes");
        	int mesSeleccionadoInt = Integer.parseInt(mesSeleccionado);
        	if(mesSeleccionadoInt== 0) {
        		
        		clientesNuevos = negocioCliente.clientesPorAnio(anio);
        		cuentasNuevas = negocioCuenta.obtenerTotalCuentasPorAnio(anio);
        		cuentasNuevasCaja = negocioCuenta.obtenerTotalCuentasPorAnioCaja(anio);
        		cuentasNuevasCorriente = negocioCuenta.obtenerTotalCuentasPorAnioCorriente(anio);
        		 prestamosCancelados = negocioPrestamo.cantidadPrestamosAnioCancelados(anio);
        		 prestamosNoCancelados = negocioPrestamo.cantidadPrestamosAnio(anio);
        		 cantidadTransferencias = negocioMovimiento.totalTransferenciasAnio(anio);
        		 totalMontoTransferencias = negocioMovimiento.MontoTransferenciaAnio(anio);
        		 
        	}
        	else {
        		clientesNuevos = negocioCliente.clientesPorFecha(anio, mesSeleccionado);
        		cuentasNuevas = negocioCuenta.obtenerTotalCuentasPorAnioYMes(anio, mesSeleccionado);
        		cuentasNuevasCaja = negocioCuenta.obtenerTotalCuentasPorAnioYMesCaja(anio, mesSeleccionado);
        		cuentasNuevasCorriente = negocioCuenta.obtenerTotalCuentasPorAnioYMesCorriente(anio, mesSeleccionado);
        		 prestamosCancelados = negocioPrestamo.cantidadPrestamosAnioYMesCancelados(anio, mesSeleccionado);
        		 prestamosNoCancelados = negocioPrestamo.cantidadPrestamosAnioYMes(anio, mesSeleccionado);
        		 cantidadTransferencias = negocioMovimiento.totalTransferenciasAnioMes(anio, mesSeleccionado);
        		 totalMontoTransferencias = negocioMovimiento.MontoTransferenciaAnioMes(anio,mesSeleccionado);
        		 
        	}
        	 
        	
        }
        

        switch (mesSeleccionado) {
            case "0":
                mesSeleccionado = "Todo el año";
                break;
            case "1":
                mesSeleccionado = "Enero";
                break;
            case "2":
                mesSeleccionado = "Febrero";
                break;
            case "3":
                mesSeleccionado = "Marzo";
                break;
            case "4":
                mesSeleccionado = "Abril";
                break;
            case "5":
                mesSeleccionado = "Mayo";
                break;
            case "6":
                mesSeleccionado = "Junio";
                break;
            case "7":
                mesSeleccionado = "Julio";
                break;
            case "8":
                mesSeleccionado = "Agosto";
                break;
            case "9":
                mesSeleccionado = "Septiembre";
                break;
            case "10":
                mesSeleccionado = "Octubre";
                break;
            case "11":
                mesSeleccionado = "Noviembre";
                break;
            case "12":
                mesSeleccionado = "Diciembre";
                break;
            default:
                mesSeleccionado = " ";
                break;
        }
        
        
        request.setAttribute("clientesNuevos", clientesNuevos);
        request.setAttribute("cuentasNuevas", cuentasNuevas);
        request.setAttribute("cuentasNuevasCaja", cuentasNuevasCaja);
        request.setAttribute("cuentasNuevasCorriente", cuentasNuevasCorriente);
        request.setAttribute("prestamosCancelados", prestamosCancelados);
        request.setAttribute("prestamosNoCancelados", prestamosNoCancelados);
        request.setAttribute("cantidadTransferencias",cantidadTransferencias );
        request.setAttribute("totalMontoTransferencias",totalMontoTransferencias );
        request.setAttribute("mostrarMes",mesSeleccionado);
        request.setAttribute("mostrarAnio",anio );
        
        
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("EstadisticasBanco.jsp");
        dispatcher.forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 RequestDispatcher dispatcher = request.getRequestDispatcher("EstadisticasBanco.jsp");
		        dispatcher.forward(request, response);
		}
        

        

       
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
