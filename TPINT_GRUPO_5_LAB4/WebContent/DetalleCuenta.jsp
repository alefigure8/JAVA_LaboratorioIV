<%@page import="entidad.TipoAcceso"%>
<%@page import="entidad.Movimiento"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Cuenta"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!-- AUTENTICACION -->
<jsp:include page="/WEB-INF/Components/autenticacion/autenticacion.jsp"> 
	<jsp:param name="TipoUsuarioPagina" value="<%=TipoAcceso.Cliente%>" />
</jsp:include>
<!-- FIN AUTENTICACION -->




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
	
	    <div class="row flex-grow-1 m-0">
	      <!--SIDEBAR-->
	      <jsp:include page= "/WEB-INF/Components/menu.jsp">
	      	<jsp:param name="usuario" value="Ram�n Ramirez" />
	      </jsp:include>
		      
		      <% 


	StringBuilder CuentaFormateada = new StringBuilder();
	Cuenta cuenta=null;
	if(request.getAttribute("cuenta")!=null){
		
		cuenta = (Cuenta)request.getAttribute("cuenta");	
		String cuentastring = String.valueOf(cuenta.getNumeroCuenta());
		CuentaFormateada.append(cuentastring, 0, 2).append("-").append(cuentastring, 2, 9).append("/").append(cuentastring.charAt(9));
	};
	ArrayList<Movimiento> listaMovimientos=null;
	if(request.getAttribute("listaMovimientos")!=null){
		
		listaMovimientos = (ArrayList<Movimiento>)request.getAttribute("listaMovimientos");
	};
	
	
	 
 
	%>
		      
		      
		      <!--CONTENT-->
		      <div class="col-lg-9 col-md-12 d-flex flex-column justify-content-between">
		        <div class="w-100 pt-2">
		          <h1>CUENTA SELECCIONADA: <%=cuenta.getTipoCuenta().getDescripcion() %></h1>
		          <h4>NRO CUENTA: <%=CuentaFormateada.toString() %></h4>
		          <h4>CBU: <%=cuenta.getCbu() %></h4>
		          <h4>SALDO DISPONIBLE: $<%=cuenta.getSaldo()%></h4>
		        </div>
		        <div class="flex-grow-1">
		          	<!-- CONTENIDO-->
		      	 	<li class="list-group-item border-0 border-bottom border-secondary bg-transparent"></li>		      	     	 

			      	 <table class="table" id="table_id">
			      	 	<tr> <th>FECHA</th> <th>CONCEPTO</th><th>DESTINO</th><th>NUMERO</th><th>TIPO DE MOV.</th>  <th>IMPORTE</th>  </tr>
			     <%for (Movimiento m :listaMovimientos){ %>
			     		      	 <form action="ServletDetalleMovimiento" method="get">
			      	 	<tr> <td><%=m.getFechaMovimiento() %></td>	      	 		      	 	
			      	 	<td><%=m.getTipoMovimiento().getDescripcion() %></td>
			      	 	<% if (m.getTipoMovimiento().getId()==1 || m.getTipoMovimiento().getId()==2) {%>
			      	 	<td>CBU:<%=cuenta.getCbu() %></td>			      	 	
			      	 	<%} else{ %>
			      	 	<td>#<%=m.getNumeroReferencia() %></td>
			      	 	<%} %>
			      	 	
			      	 	<td><%=m.getId() %></td>
			      	 	<td><%=m.getOperacion() %></td>
			      	 	<% if(m.getOperacion().equals("Entrada")) { %>
	            				 <th style=color:#00a000;>$<%=m.getMonto() %></th>
	            				 <% } else{ %>
	            				 <th  style=color:#ff0000;>$<%=m.getMonto() %></th>
	            				 <%} %>
	            				
	            		<input type="hidden" class="btn btn-primary btnEnviar" name="idmovimiento" value="<%=m.getId()%>">
	            		    		<td><input type="submit" class="btn btn-primary btnEnviar" name="btnVerDetalleMovimiento" value="VER"></td>
			      	 	</tr>	
			      	 		 </form>	      	 	 
			      	 	<%} %>
			      	 	
			      	 </table>
			      
		        </div>
		      </div>
	       </div>
	 	<!--FIN MAIN-->
	 	<!--FOOTER-->
	    <jsp:include page= "/WEB-INF/Components/footer.html"></jsp:include>
	 </body>
	 
	
</html>