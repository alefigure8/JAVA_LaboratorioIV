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
       
			<div class="text-center">
			    <p>Nro. Prestamo: 002</p>
			    <p>Fecha de prestamo: 01/01/2023</p>
			    <p>Nro. Cuenta del Prestamo: XXXX-XXXX-XXXX-1234</p>
			    <p>Estado: Aprobado</p>
			    <p>Saldado: No</p>
			    <p>Importe solicitado: $75.000</p>
			    <p>Importe con intereses: $150.000 </p>
			    <p>Cantidad de Cuotas: 6</p>
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
		                <th>Accion</th>
		            </tr>
		        </thead>
		        <tbody>
		            <tr>
		                <td>1</td>
		                <td>$25.000</td>
		                <td>Pagado</td>
		                <td>XXXX-XXXX-XXXX-1234</td>
		                <td>01/02/2023</td>
		                <td>-</td>
		                
		            </tr>
		            <tr>
		                <td>2</td>
		                <td>$25.000</td>
		                <td>Pagado</td>
		                 <td>XXXX-XXXX-XXXX-1234</td>
		                <td>01/03/2023</td>
		                 <td>-</td>
		                
		            </tr>
		            <tr>
		                <td>3</td>
		                <td>$25.000</td>
		                <td>Pagado</td>
		                <td>XXXX-XXXX-XXXX-1234</td>
		                <td>01/04/2023</td>
		                 <td>-</td>
		               
		            </tr>
		             <tr>
		                <td>4</td>
		                <td>$25.000</td>
		                <td>Pagado</td>
		                <td>XXXX-XXXX-XXXX-1234</td>
		                <td>01/05/2023</td>
		                 <td>-</td>
		                
		            </tr>
		             <tr>
		                <td>5</td>
		                <td>$25.000</td>
		                <td>Pagado</td>
		                <td>XXXX-XXXX-XXXX-1234</td>
		                <td>01/06/2023</td>
		                 <td>-</td>
		                
		            </tr>
		             <tr>
		                <td>6</td>
		                <td>$25.000</td>
		                <td>Impago</td>
		                <td>-</td>
		                 <td>-</td>
		                <td>
			               <input type="submit" class="btn btn-primary btnEnviar" name="btnPagarPrestamo" value="Pagar">
			            </td>
			            
		            </tr>
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