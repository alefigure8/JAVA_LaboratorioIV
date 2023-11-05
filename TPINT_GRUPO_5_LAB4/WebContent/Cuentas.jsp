<%@page import="entidad.TipoAcceso"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!-- AUTENTICACION -->
<jsp:include page="/WEB-INF/Components/autenticacion/autenticacion.jsp"> 
	<jsp:param name="TipoUsuarioPagina" value="<%=TipoAcceso.Administrador%>" />
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
	      	<jsp:param name="usuario" value="Ramón Ramirez" />
	      </jsp:include>
   		<!-- FIN HEAD -->   
     	<!--CONTENT-->
	      <div class="col-lg-9 col-md-12 d-flex flex-column justify-content-between">
	        <div class="w-100 pt-2">
	          <h1>MIS CUENTAS</h1>
	        </div>
	        <div class="flex-grow-1">
	          <!-- CONTENIDO-->
		 	 	<ul class="list-group border-0">
		          <li class="list-group-item border-0 border-bottom border-secondary bg-transparent"></li>
		          <li class="list-group-item border-0 bg-transparent">
		          <h4>CAJA AHORRO | CTA NRO 204-87865/8 | $150.000,50</h4>
		          <input type="submit" class="btn btn-primary btnEnviar" name="btnVerMovimientos" value="VER MOVIMIENTOS">
				  </li>
				  <li class="list-group-item border-0 border-bottom border-secondary bg-transparent"></li>  
				  <li class="list-group-item border-0 bg-transparent">
		          <h4>CTA. CORRIENTE | CTA NRO 204-85364/8 | $10.000,00</h4>
		          <input type="submit" class="btn btn-primary btnEnviar" name="btnVerMovimientos" value="VER MOVIMIENTOS">
				  </li>
				  <li class="list-group-item border-0 border-bottom border-secondary bg-transparent"></li>
				  <a href="#" class="btn btn-primary btnEnviar col-12 p-4 mt-4 ">SOLICITAR NUEVA CUENTA</a>
	        	</ul>
	        </div>
	      </div>
       </div>
       <!-- FIN MAIN -->
	 	<!--FOOTER-->
	    <jsp:include page= "/WEB-INF/Components/footer.html"></jsp:include>
	 </body>
</html>