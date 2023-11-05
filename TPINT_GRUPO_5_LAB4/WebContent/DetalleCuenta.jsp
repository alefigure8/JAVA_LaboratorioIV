<%@page import="entidad.TipoAcceso"%>
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
	      	<jsp:param name="usuario" value="Ramón Ramirez" />
	      </jsp:include>
		      
		      <!--CONTENT-->
		      <div class="col-lg-9 col-md-12 d-flex flex-column justify-content-between">
		        <div class="w-100 pt-2">
		          <h1>CUENTA SELECCIONADA: CAJA DE AHORRO</h1>
		          <h4>NRO CUENTA: 204-87865/8</h4>
		          <h4>CBU: 01702046600000087865</h4>
		          <h4>SALDO DISPONIBLE: $150.000,00</h4>
		        </div>
		        <div class="flex-grow-1">
		          	<!-- CONTENIDO-->
		      	 	<li class="list-group-item border-0 border-bottom border-secondary bg-transparent"></li>
		      	 
			      	 <table class="table">
			      	 	<tr> <th>FECHA</th> <th>DETALLE</th> <th>CONCEPTO</th> <th>IMPORTE</th> <th>TIPO DE MOV.</th> </tr>
			      	 	<tr> <td>23/10/2023</td> <td>TRANS. 0039</td> <td>VARIOS</td> <td>$15.000,00</td> <td>TRANSFERENCIA</td> </tr>
			      	 	<tr> <td>20/10/2023</td> <td>DEBITO AUT.</td> <td>CUOTA 03/06</td> <td>- $7.000,00</td> <td>PAGO PRESTAMO</td> </tr>
			      	 	<tr> <td>20/10/2023</td> <td>TRANS. 0034</td> <td>VARIOS</td> <td>- $15.000,00</td> <td>TRANSFERENCIA</td> </tr>
			      	 </table>
		        </div>
		      </div>
	       </div>
	 	<!--FIN MAIN-->
	 	<!--FOOTER-->
	    <jsp:include page= "/WEB-INF/Components/footer.html"></jsp:include>
	 </body>
</html>