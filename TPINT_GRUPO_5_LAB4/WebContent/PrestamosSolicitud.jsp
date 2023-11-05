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
	          <h1>SOLICITUD DE PRESTAMO</h1>
	        </div>
	        <div class="flex-grow-1">
	        
	          <!-- MAIN--> 
			      <div class="row d-flex ">
					    <div class="col-md-4 mx-auto">
					        <div class="form-group mt-2">
					            <label for="monto">Monto:</label>
					            <input type="text" class="form-control" id="monto" name="monto">
					        </div>
					        <div class="form-group mt-2">
					            <label for="cuotas">Cantidad de Cuotas:</label>
					            <select class="form-control" id="cuotas" name="cuotas">
					                <option >3 cuotas</option>
					                <option >6 cuotas</option>
					                <option >12 cuotas</option>
					                <option >24 cuotas</option>
					                <option >48 cuotas</option>
					            </select>
					        </div>
					        <div class="form-group mt-2">
					            <label for="interesTotal">Total de Intereses:</label>
					            <input type="text" class="form-control" id="interesTotal" name="interesTotal" disabled>
					        </div>
					        <div class="form-group mt-2">
					            <label for="totalMonto">Total (Monto + Intereses):</label>
					            <input type="text" class="form-control" id="totalMonto" name="totalMonto" disabled>
					        </div>
					        
					        <div class="form-group mt-2">
					            <label for="cuotas">Nro. de cuenta a depositar</label>
					            <select class="form-control" id="cuentas" name="cuentas">
					                <option >XXXX-XXXX-XXXX-1234</option>
					                <option >XXXX-XXXX-XXXX-1235</option>
					            </select>
					        </div>
					        
					           <input type="submit" class="btn btn-success mt-4" name="btnSolicitarPrestamo" value="Solicitar Prestamo" >
					    </div>
					</div>
		        </div>
	      	</div>
       </div>
	 	<!--FOOTER-->
	    <jsp:include page= "/WEB-INF/Components/footer.html"></jsp:include>
	 </body>
</html>