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
	                <h1>HOME / BIENVENIDO</h1>
	            </div>
	
	            <div class="flex-grow-1">
		            <!-- CONTENIDO -->
		            <!-- MENU -->
					<div class="p-4 col-md-12 justify-content-center align-items-start">
			    		<h4>Menú</h4>
			    		<a href="#" class="btn btn-primary btnEnviar col-5 p-4 m-1">MIS CUENTAS</a>
			    		<a href="#" class="btn btn-primary btnEnviar col-5 p-4 m-1">TRANSFERENCIAS</a>
			    		<a href="#" class="btn btn-primary btnEnviar col-5 p-4 m-1">PRESTAMOS</a>
			    		<a href="#" class="btn btn-primary btnEnviar col-5 p-4 m-1">PERFIL</a>
					</div>
					<!-- ÚLTIMOS MOVIMIENTOS // CAJA AHORRO DEFAULT -->
	                <form class="container mt-4 d-flex justify-content-left align-items-start" action="#" method="post">
	                    <div class="form-group col-md-12 d-flex flex-column">
	                        <h5>Últimos movimientos | Caja Ahorro</h5>
	                        <table class="table table-bordered mt-2">
					        <tr>
	            				<th>TRANSF. N0001</th> <th>25/09/2023</th> <th>$5500.00</th> <th><button class="btn btn-primary">Ver más</button></th>
	        				</tr>
	        				<tr>
	            				<th>TRANSF. N0002</th> <th>19/09/2023</th> <th>$10000.00</th> <th><button class="btn btn-primary">Ver más</button></th>
	        				</tr>
	        				<tr>
	            				<th>TRANSF. N0003</th> <th>07/09/2023</th> <th>$2.00</th> <th><button class="btn btn-primary">Ver más</button></th>
	        				</tr>
						  </table>
	   					</div>
					</form>
		    	</div>
	       </div>   
		</div>
		<!-- FIN MAIN -->
	 	<!--FOOTER-->
	    <jsp:include page= "/WEB-INF/Components/footer.html"></jsp:include>
	 </body>
</html>