<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="body" tagdir="/WEB-INF/tags" %>

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
      <jsp:include page= "/WEB-INF/Components/menu_cliente.jsp">
      	<jsp:param name="usuario" value="Ramón Ramirez" />
      </jsp:include> 
      
      <!--CONTENT-->
	     <div class="col-lg-9 col-md-12 d-flex flex-column justify-content-between">
		        <div class="w-100 pt-2">
		          <h1>MI DATOS</h1>
		        </div>
		        <div class="flex-grow-1">
				  <ul class="list-group border-0">
		          <li class="list-group-item border-0 border-bottom border-secondary bg-transparent"></li>
		          <li class="list-group-item border-0 bg-transparent"><p>NOMBRE Y APELLIDO: JUAN PEREZ</p></li>
				  <li class="list-group-item border-0 bg-transparent"><p>DNI: 35.508.974</p></li>
				  <li class="list-group-item border-0 bg-transparent"><p>CUIL: 20-35508974-1</p></li>
				  <li class="list-group-item border-0 bg-transparent"><p>FECHA NAC: 25/04/1984</p></li>
				  <li class="list-group-item border-0 bg-transparent"><p>SEXO: X</p></li>
				  <li class="list-group-item border-0 bg-transparent"><p>DIRECCIÓN: MONTES DE OCA 345 - JUNIN  - BS. AS.</p></li>
				  <li class="list-group-item border-0 bg-transparent"><p>E-MAIL: JUANPEREZ@YAHOO.ES</p></li>
				  <li class="list-group-item border-0 bg-transparent"><p>TELEFONO: 345-512-1455</p></li>
		        </ul>
		        </div>
		      </div>
	       </div>
	 	<!--FOOTER-->
	    <jsp:include page= "/WEB-INF/Components/footer.html"></jsp:include>
	 </body>
</html>