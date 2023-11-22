package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidad.Movimiento;
import entidad.Cuenta;
import negocioDao.ICuentaNegocioDao;
import negocioDao.IMovimientoNegocioDao;
import negocioDaoImp.MovimientoNegocioDaoImp;
import negocioDaoImp.*;

/**
 * Servlet implementation class ServletDetalleCuenta
 */
@WebServlet("/ServletDetalleCuenta")
public class ServletDetalleCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       Cuenta cuenta=null;       
       ICuentaNegocioDao cuentaNegocio = new CuentaNegocioDaoImp();
       IMovimientoNegocioDao negocioMovimiento = new MovimientoNegocioDaoImp();
       ArrayList<Movimiento> listaMovimientos = new ArrayList<Movimiento>();
       ArrayList<Movimiento> listaMovimientosordenada = new ArrayList<Movimiento>();
       List<Movimiento>listaMovimientosFiltrada=new ArrayList<Movimiento>();

      public ServletDetalleCuenta() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		if(request.getParameter("btnVerMovimientos")!=null || request.getParameter("btnLimpiarFiltros")!=null && request.getParameter("btnFiltrarMovimientos")==null) {

			if(request.getParameter("numeroCuenta")!=null) {
			
			
				try {
					cuenta = cuentaNegocio.obtenerUna(Integer.valueOf(request.getParameter("numeroCuenta").toString()));
				} catch (NumberFormatException | SQLException e1) {
					
					e1.printStackTrace();
				}
		
				
			try {
				listaMovimientos.clear();
				listaMovimientosordenada.clear();
				listaMovimientos = (ArrayList<Movimiento>) negocioMovimiento.obtenerPorCBU(cuenta.getCbu());
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			for (int i = 0;i<=listaMovimientos.size()-1;i++) {
			
				System.out.println(i);
				
				Movimiento aux = new Movimiento();
				aux = listaMovimientos.get(i);	
			listaMovimientosordenada.add(aux);
			}
			listaMovimientosFiltrada.clear();
			session.setAttribute("origen", "detallecuenta");
			request.setAttribute("cuenta", cuenta);
			request.setAttribute("listaMovimientos", listaMovimientosordenada);
			RequestDispatcher rd = request.getRequestDispatcher("/DetalleCuenta.jsp"); 
	        rd.forward(request, response);
			
			}
		}
		
			if(request.getParameter("detallecuenta")!=null || request.getParameter("btnLimpiarFiltros")!=null && request.getParameter("btnFiltrarMovimientos")==null) {

				if(request.getParameter("detallecuenta")!=null)	{
					try {
						cuenta = cuentaNegocio.obtenerUna(Integer.valueOf(request.getParameter("detallecuenta").toString()));
					} catch (NumberFormatException | SQLException e1) {
						
						e1.printStackTrace();
					}	
					
				}
				
				
				if (request.getParameter("btnLimpiarFiltros")!=null) {
					try {
						cuenta=cuentaNegocio.obtenerUna(Integer.valueOf(request.getParameter("nroCuenta").toString()));
					} catch (NumberFormatException | SQLException e) {
						
						e.printStackTrace();
					}
				}
				
				
				try {
					listaMovimientos.clear();
					listaMovimientos = (ArrayList<Movimiento>) negocioMovimiento.obtenerPorCBU(cuenta.getCbu());
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				listaMovimientosFiltrada.clear();
				session.setAttribute("origen", "detallecuenta");
				request.setAttribute("cuenta", cuenta);
				request.setAttribute("listaMovimientos", listaMovimientos);
				RequestDispatcher rd = request.getRequestDispatcher("/DetalleCuenta.jsp"); 
		        rd.forward(request, response);
				
				}
			
			
		//Filtros
			if(request.getParameter("btnFiltrarMovimientos")!=null && request.getParameter("btnLimpiarFiltros")==null) {
				//CONCEPTOS
				String conceptoSeleccionado=null;	
				if(request.getParameter("Conceptos")!=null) {
					conceptoSeleccionado=request.getParameter("Conceptos");
				}
				
				//IMPORTES
				String importeSeleccionado=null;
				if(request.getParameter("Importes")!=null) {
					importeSeleccionado=request.getParameter("Importes");
				}
				
				//MONTO IMPORTE
				String montoImporte=null;
				if(request.getParameter("rangoImporte")!=null && !request.getParameter("rangoImporte").isEmpty()) {
					montoImporte=request.getParameter("rangoImporte");
				}
				
				//FECHAS
				String fechaDesde=null;
				String fechaHasta=null;
				if(request.getParameter("movimientoDesde")!=null && !request.getParameter("movimientoDesde").isEmpty()) {
					fechaDesde=request.getParameter("movimientoDesde");
				}
				if(request.getParameter("movimientoHasta")!=null && !request.getParameter("movimientoHasta").isEmpty()) {
					fechaHasta=request.getParameter("movimientoHasta");
				}
				
				
				//Listas limpias
				
				if (!listaMovimientosFiltrada.isEmpty()) {
					listaMovimientos.clear();
			        listaMovimientos.addAll(listaMovimientosFiltrada);
			    }
				
				//Listas para filtrar
				listaMovimientosFiltrada.clear();
				
					for(int x=0;x<listaMovimientos.size();x++) {
						
						Movimiento movimiento=listaMovimientos.get(x);
						
						//Conceptos
						if(conceptoSeleccionado!=null && !conceptoSeleccionado.equals("Todos los conceptos")) {
							if(!movimiento.getTipoMovimiento().getDescripcion().equals(conceptoSeleccionado)) {
								continue;
							}
							
						}
						
						//Importe
						if(importeSeleccionado!=null && montoImporte!=null) {
							Double monto=Double.parseDouble(montoImporte);
							switch (importeSeleccionado) {
							case "Mayor a":
								if(movimiento.getMonto()<=monto) {
									continue;
								}
								break;
							case "Igual a":
								if(movimiento.getMonto()!=monto) {
									continue;
								}
								break;
							case "Menor a":
								if(movimiento.getMonto()>=monto) {
									continue;
								}
								break;

							}
						}
						
						//Fechas
						if(fechaDesde!=null && fechaHasta!=null) {
							try {
						        LocalDate fechaMovimiento = movimiento.getFechaMovimiento();
						        LocalDate desde = LocalDate.parse(fechaDesde);
						        LocalDate hasta = LocalDate.parse(fechaHasta);
						        
						        if (fechaMovimiento.isBefore(desde) || fechaMovimiento.isAfter(hasta)) {
						            continue;
						        }
						    } catch (DateTimeParseException e) {
						        e.printStackTrace();
						    }
							
						}
						
						listaMovimientosFiltrada.add(movimiento);
						
					}
					
					
				request.setAttribute("cuenta", cuenta);
				request.setAttribute("listaMovimientos", listaMovimientosFiltrada);	
				
				request.setAttribute("conceptoSeleccionado", conceptoSeleccionado);
				request.setAttribute("importeSeleccionado", importeSeleccionado);
				request.setAttribute("montoImporte", montoImporte);
				request.setAttribute("fechaDesde", fechaDesde);
				request.setAttribute("fechaHasta", fechaHasta);
				
				RequestDispatcher rDispatcher=request.getRequestDispatcher("DetalleCuenta.jsp");
				rDispatcher.forward(request, response);
				
			}	
			
		
		
		response.getWriter().append("Served at getdetalle cuenta: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
