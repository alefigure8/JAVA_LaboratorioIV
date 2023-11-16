<%@page import="entidad.TipoAcceso"%>
<%@page import="entidad.Movimiento"%>
<%@page import="java.util.ArrayList"%>
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
	if(request.getAttribute("tipoCuenta")!=null){
		
		tipoCuenta = request.getAttribute("tipoCuenta").toString();
	}
	if(request.getAttribute("listaMovimientos")!=null){
		movimientosCuenta = (ArrayList<Movimiento>) request.getAttribute("listaMovimientos");
	}
	
			
 %>
	
	    <div class="row flex-grow-1 m-0">
	      <!--SIDEBAR-->
	      <jsp:include page= "/WEB-INF/Components/menu.jsp">
	      	<jsp:param name="usuario" value="Ram�n Ramirez" />
	      </jsp:include>
		      
         <!--CONTENT-->
	        <div class="col-lg-9 col-md-12 d-flex flex-column justify-content-between">
	            <div class="w-100 pt-2">
	                <h1>HOME / BIENVENIDO</h1>
	            </div>
	
	            <div class="flex-grow-1">
		            <!-- CONTENIDO -->
		            <!-- MENU -->
					<div class="p-4 col-md-12 justify-content-center align-items-start">
			    		<h4>Menu</h4>
			    		<a href="ServletCuentas?Cuentas=true" class="btn btn-primary btnEnviar col-5 p-4 m-1">MIS CUENTAS</a>
			    		<a href="ServletListaTransferencias?listado=true&todos=true" class="btn btn-primary btnEnviar col-5 p-4 m-1">TRANSFERENCIAS</a>
			    		<a href="ServletPrestamos?PrestamosCliente=true" class="btn btn-primary btnEnviar col-5 p-4 m-1">PRESTAMOS</a>
			    		<a href="Perfil.jsp" class="btn btn-primary btnEnviar col-5 p-4 m-1">PERFIL</a>
					</div>
					<!-- �LTIMOS MOVIMIENTOS // CAJA AHORRO DEFAULT -->
	                <div class="container mt-4 d-flex justify-content-left align-items-start">
	                    <div class="form-group col-md-12 d-flex flex-column">
	                        <h5>Ultimos movimientos | <%=tipoCuenta%></h5>
	                        <table class="table table-bordered mt-2">
	                        <% for (Movimiento m : movimientosCuenta) { %>
					        <tr>
					              <form action="ServletDetalleMovimiento" method="get">
	            				<th><%=m.getTipoMovimiento().getDescripcion() %></th>
	            				 <th><%=m.getFechaMovimiento() %></th>
	            				<% if(m.getOperacion().equals("Entrada")) { %>
	            				 <th style=color:#00a000;>$<%=m.getMonto() %></th>
	            				 <% } else{ %>
	            				 <th  style=color:#ff0000;>$<%=m.getMonto() %></th>
	            				 <%} %>
	            				
	            				  	<input type="hidden" class="btn btn-primary btnEnviar" name="idmovimiento" value="<%=m.getId()%>">
	            				   	 <th><input type="submit" class="btn btn-primary btnEnviar" name="btnVerDetalleMovimiento" value="VER"></th>	        				
	        						
	            	</tr>
	        				</form>
	        				<%} %>
						  </table>
	   					</div>
					</div>
		    	</div>
	       </div>   
		</div>
		<!-- FIN MAIN -->
	 	<!--FOOTER-->
	    <jsp:include page= "/WEB-INF/Components/footer.html"></jsp:include>
	 </body>
</html>