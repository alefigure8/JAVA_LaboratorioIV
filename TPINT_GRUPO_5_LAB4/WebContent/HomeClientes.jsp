<%@page import="entidad.TipoAcceso"%>
<%@page import="entidad.Movimiento"%>
<%@page import="entidad.Cuenta"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="java.text.DecimalFormat" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!-- AUTENTICACION -->
<jsp:include page="/WEB-INF/Components/autenticacion/autenticacion.jsp"> 
	<jsp:param name="TipoUsuarioPagina" value="<%=TipoAcceso.Cliente%>" />
</jsp:include>
<!-- FIN AUTENTICACION -->

<% 
session.removeAttribute("montoSeleccionado");
session.removeAttribute("tipoTasaSeleccionada");
session.removeAttribute("interesCalculado");
session.removeAttribute("totalCalculado"); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
	<!-- HEAD -->
	<% 
		//TITULO DEL ARCHIVO COMO TITULO DE LA PAGINA
		String PATH = request.getRequestURI().split("/")[2];
		String[] palabras = PATH.split("(?=[A-Z]|\\s)");
		String URL = String.join(" ", palabras).split(".jsp")[0];
	%>
	<jsp:include page= "/WEB-INF/Components/head.jsp">
		<jsp:param name="titulo" value="<%=URL%>"/>
	</jsp:include>
	<body class="d-flex flex-column">
	
	<%
	ArrayList<Movimiento> movimientosCuenta = null;
	String tipoCuenta = null;
	String numerocuenta= null;
	Cuenta cuenta=null;
	
	if(request.getAttribute("tipoCuenta")!=null){
		tipoCuenta = request.getAttribute("tipoCuenta").toString();
	}
	if(request.getAttribute("listaMovimientos")!=null){
		movimientosCuenta = (ArrayList<Movimiento>) request.getAttribute("listaMovimientos");
	}	
	if(request.getAttribute("cuentaVisible")!=null){
		cuenta=(Cuenta)request.getAttribute("cuentaVisible");
	}
	
	StringBuilder CuentaFormateada = new StringBuilder();
	
	if(cuenta != null){
		String cuentastring = String.valueOf(cuenta.getNumeroCuenta());
		CuentaFormateada.append(cuentastring, 0, 2).append("-").append(cuentastring, 2, 9).append("/").append(cuentastring.charAt(9));
	}
	
 	%>
	    <div class="row flex-grow-1 m-0">
	      <!--SIDEBAR-->
	      <jsp:include page= "/WEB-INF/Components/menu.jsp">
	      	<jsp:param name="usuario" value="Ramï¿½n Ramirez" />
	      </jsp:include>
         <!--CONTENT-->
	        <div class="col-lg-9 col-md-12 d-flex flex-column justify-content-between">
	            <div class="w-100 pt-2">
	                <h1>BIENVENIDO</h1>
	            </div>
	
	            <div class="flex-grow-1">
		            <!-- CONTENIDO -->
		            <!-- MENU -->
		            
					<div class="p-4 col-md-12 text-center">
					    <div class="row justify-content-center">
					        <div class="col-md-4">
					            <div class="row">
					                <div class="col-md-12">
					                    <a href="ServletCuentas?Cuentas=true" class="btn btn-primary btnEnviar col-12 p-3 m-1">
					                        <i class="fas fa-wallet fa-2x me-2"></i>MIS CUENTAS
					                    </a>
					                </div>
					                <div class="col-md-12">
					                    <a href="ServletListaTransferencias?listado=true&todos=true" class="btn btn-primary btnEnviar col-12 p-3 m-1">
					                        <i class="fas fa-exchange-alt fa-2x me-2"></i>TRANSFERENCIAS
					                    </a>
					                </div>
					            </div>
					        </div>
					        <div class="col-md-4">
					            <div class="row">
					                <div class="col-md-12">
					                    <a href="ServletPrestamos?PrestamosCliente=true" class="btn btn-primary btnEnviar col-12 p-3 m-1">
					                        <i class="fas fa-hand-holding-usd fa-2x me-2"></i>PRESTAMOS
					                    </a>
					                </div>
					                <div class="col-md-12">
					                    <a href="Perfil.jsp" class="btn btn-primary btnEnviar col-12 p-3 m-1">
					                        <i class="fas fa-user-circle fa-2x me-2"></i>PERFIL
					                    </a>
					                </div>
					            </div>
					        </div>
					    </div>
					</div>


					<!-- ÚLTIMOS MOVIMIENTOS // CAJA AHORRO DEFAULT -->
	                <div class="container mt-4 d-flex justify-content-left align-items-start">
	                    <div class="form-group col-md-12 d-flex flex-column">
	                    
					<%if(movimientosCuenta != null){ %>
	                    <% String saldoFormateado= String.format("%,.2f",cuenta.getSaldo());%>
	                        <h5>Ultimos movimientos | <%=tipoCuenta%> | <%=CuentaFormateada%> | Saldo: $<span id="saldo"><%=saldoFormateado %></span><a href="#" id="visibilidad"><i id="eye" class="fa-regular fa-eye fa-2x ms-2"></i></a></h5>
	                        		<table class="table table-bordered mt-2">
				                       <tr>
							                <th>Tipo de Operación</th>
							                <th>Fecha</th>
							                <th>Monto</th>
							                <th>Acción</th>
							            </tr>
	                        
	                       			 <% for (Movimiento m : movimientosCuenta) { %>
						        			<tr>
						              
					            				<th><%=m.getTipoMovimiento().getDescripcion() %></th>
					            				 <th><%=m.getFechaMovimiento() %></th>
					            				 
				            					 <% if(m.getOperacion().equals("Entrada")) { %>
					            				 	<th style=color:#00a000;>$<%=String.format("%,.2f", m.getMonto()) %></th>
					            				 <% } else{ %>
					            				 	<th  style=color:#ff0000;>$<%=String.format("%,.2f", m.getMonto()) %></th>
					            				 <%} %>
	        									<th><a class="btn btn-primary btnEnviar" href="ServletDetalleMovimiento?idmovimiento=<%=m.getId()%>&btnVerDetalleMovimiento=VER">VER</a></th>
			            					</tr>
	        						<%} %>
						  		</table>
					<% } 

					if(movimientosCuenta == null){ %>
						<div class="d-flex justify-content-center">
							<h3>NO POSEE CUENTAS</h3>
						</div>
					<%} %>
	   					</div>
					</div>
		    	</div>
	       </div>   
		</div>
		<!-- FIN MAIN -->
	 	<!--FOOTER-->
	    <jsp:include page= "/WEB-INF/Components/footer.html"></jsp:include>
	 </body>
	 
	 
	 <script>
	 
	 <%if(movimientosCuenta != null){ %>
	 document.addEventListener("DOMContentLoaded", function() {
	 	const visibilidad=document.getElementById("visibilidad");
	 	const saldo=document.getElementById("saldo");
	 	const eye=document.getElementById("eye");
	 	let saldoVisible=true;
	 	console.log(visibilidad)
	 	
	 	visibilidad.addEventListener("click", function(){
	 		saldoVisible=!saldoVisible;
	 		if(saldoVisible){
	 			saldo.textContent="<%=String.format("%,.2f", cuenta.getSaldo()) %>";
	 			eye.classList.remove("fa-eye-slash");
	 			eye.classList.add("fa-eye");
	 		}else{
	 			saldo.textContent="*********";
	 			eye.classList.remove("fa-eye");
	 			eye.classList.add("fa-eye-slash");
	 		}
	 	});
	 	
	 });
	 <%}%>
	 </script>
	 
	 
</html>