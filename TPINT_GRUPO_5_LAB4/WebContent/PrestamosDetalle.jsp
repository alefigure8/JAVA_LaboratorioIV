<%@page import="java.util.ArrayList"%>
<%@page import="entidad.CuotaPrestamo"%>
<%@page import="entidad.Cuenta"%>
<%@page import="entidad.Prestamo"%>
<%@page import="java.util.List"%>
<%@page import="entidad.Cliente"%>
<%@page import="entidad.Usuario"%>
<%@page import="entidad.TipoAcceso"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!-- AUTENTICACION -->
<% 
	//PUEDEN INGRESAR AMBOS
	session = request.getSession();
	Usuario usuario = new Usuario();
	
	if(session.getAttribute("usuario") != null){
		usuario = (Usuario)session.getAttribute("usuario");
	}
%>
<jsp:include page="/WEB-INF/Components/autenticacion/autenticacion.jsp"> 
	<jsp:param name="TipoUsuarioPagina" value="<%=usuario.getTipoAcceso()%>" />
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
	<!-- FIN HEAD -->
	<body class="d-flex flex-column">
	    <div class="row flex-grow-1 m-0">
	      <!--SIDEBAR-->
	      <jsp:include page= "/WEB-INF/Components/menu.jsp">
	      	<jsp:param name="usuario" value="Ramón Ramirez" />
	      </jsp:include>
		      
		      
      <!--CONTENT-->
      <div class="col-lg-9 col-md-12 d-flex flex-column justify-content-between">
        <div class="w-100 pt-2">
          <h1>DETALLE DE PRESTAMO</h1>
        </div>
        <div class="flex-grow-1">
          <!-- CONTENIDO-->
          <%
      			Cliente cliente = new Cliente();
          		Prestamo prestamo = new Prestamo();
          		Cuenta cuenta = new Cuenta();
          		List<CuotaPrestamo> listaCuota = new ArrayList<CuotaPrestamo>();
          		
          		if(usuario.getTipoAcceso().compareTo(TipoAcceso.Administrador) == 0){
          			
   	          		cliente = (Cliente)request.getAttribute("cliente");
   	          		prestamo = (Prestamo)request.getAttribute("prestamo");
   	          		cuenta = (Cuenta)request.getAttribute("cuenta");
   	          		listaCuota = (List<CuotaPrestamo>)request.getAttribute("cuotas");
    	       		
          		}
          %>
       
			<div class="text-center">
			    <p>Nro. Prestamo: <%=prestamo.getId() %></p>
			    <p>Fecha de prestamo: <%=prestamo.getFechaPrestamo()%></p>
			    <p>Nro. Cuenta del Prestamo: <%=prestamo.getNumeroCuenta()%></p>
			    <p>Estado: <%=prestamo.getEstado().getDescripcion()%></p>
			    <p>Saldado: <%if(prestamo.isCancelado()){%> SI <%}else{%> NO <%}%></p>
			    <p>Importe solicitado: <%=prestamo.getMontoPedido() %></p>
			    <%Double importeConInteres = prestamo.getMontoPedido() * ((prestamo.getTipoTasa().getTasaInteres() / 100) + 1); %>
			    <p>Importe con intereses: <%=importeConInteres%> </p>
			    <p>Cantidad de Cuotas: <%=listaCuota.size() %></p>
			</div>

		<!-- Cuadro para detalles de cuotas -->
		<div>
		    <h2>Detalles de Cuotas</h2>
		    <table id="table_id" class="table display text-center">
		        <thead>
		            <tr>
		                <th>Número de Cuota</th>
		                <th>Importe</th>
		                <th>Estado</th>
		                <th>Nro. Cuenta de pago</th>
		                <th>Fecha de Pago</th>
		                <%if(usuario.getTipoAcceso().equals(TipoAcceso.Cliente)){%>
			                <th>Accion</th>	
		                <%}%>
		            </tr>
		        </thead>
		        <tbody>
		        <%
		        	for(CuotaPrestamo cuota : listaCuota){%>
			            <tr>
			                <td><%=cuota.getNumeroCuota()%></td>
			                <td><%=cuota.getMontoCuota()%></td>
			                <td><%=cuota.getEstado()%></td>
			                <td><%=cuenta.getNumeroCuenta()%></td>
			                 <%if(cuota.getFechaPago() != null){%>
				                <td><%=cuota.getFechaPago()%></td>	
			                <%} else {%>
			                	<td>-</td>	
			                <%}%>
			                <%if(usuario.getTipoAcceso().equals(TipoAcceso.Cliente)){%>
				                <td>-</td>	
			                <%}%>
			            </tr>
	           	 	<%}
	           	 %>
		        </tbody>
		    </table>
		</div>
        </div>
      	</div>
       </div>
	 	<!--FOOTER-->
	    <jsp:include page= "/WEB-INF/Components/footer.html"></jsp:include>
	 </body>
</html>