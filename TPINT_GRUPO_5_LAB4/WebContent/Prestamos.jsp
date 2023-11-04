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
          <h1>MIS PRESTAMOS</h1>
        </div>
        <div class="flex-grow-1">
          <!-- CONTENIDO-->
       
			<div class="container mt-4">
			   
			   <!-- SI USAMOS PAGINACION CON JQUERY --> <!--   --> 
			   <table id="table_id" class="table display text-center">
			   <!-- <table class="table"> -->
			        <thead>
			            <tr>
			                <th>Nro. Prestamo</th>
			                <th>Fecha prestamo</th>
			                <th>Estado</th>
			                <th>Saldado</th>
			                <th>Importe solicitado</th>
			                <th>Importe con interes</th>
			                <th>Acción</th>
			            </tr>
			        </thead>
			        <tbody>
			            <!-- Ejemplo de prestamo pagado -->
			            <tr>
			                <td>001</td>
			                <td>01/06/2018</td>
			                <td>Aprobado</td>
			                <td>Si</td>
			                <td>$50.000</td>
			                 <td>$100.000</td>
			                <td>
			                    <input type="submit" class="btn btn-primary btnEnviar" name="btnVerPrestamo" value="Detalle">
			                </td>
			            </tr>
			            <!-- Ejemplo de prestamo impago -->
			            <tr>
			                <td>002</td>
			                <td>01/01/2023</td>
			                <td>Aprobado</td>
			                <td>No</td>
			                <td>$75.000</td>
			                <td>$150.000</td>
			                <td>
			                    <input type="submit" class="btn btn-primary btnEnviar" name="btnVerPrestamo" value="Detalle">
			                </td>
			            </tr>
			             
			            <tr>
				            <td>003</td>
				            <td>03/03/2023</td>
				            <td>Aprobado</td>
				            <td>Si</td>
				            <td>$30.000</td>
				            <td>$60.000</td>
				            <td>
				                <input type="submit" class="btn btn-primary btnEnviar" name="btnVerPrestamo" value="Detalle">
				            </td>
				        </tr>
							<tr>
				            <td>004</td>
				            <td>08/12/2022</td>
				            <td>Aprobado</td>
				            <td>Si</td>
				            <td>$85.000</td>
				            <td>$170.000</td>
				            <td>
				                <input type="submit" class="btn btn-primary btnEnviar" name="btnVerPrestamo" value="Detalle">
				            </td>
				        </tr>
			              <tr>
				            <td>005</td>
				            <td>05/10/2020</td>
				            <td>Aprobado</td>
				            <td>Si</td>
				            <td>$60.000</td>
				            <td>$120.000</td>
				            <td>
				                <input type="submit" class="btn btn-primary btnEnviar" name="btnVerPrestamo" value="Detalle">
				            </td>
				        </tr>
			             <tr>
				            <td>006</td>
				            <td>19/11/2021</td>
				            <td>Aprobado</td>
				            <td>Si</td>
				            <td>$40.000</td>
				            <td>$80.000</td>
				            <td>
				                <input type="submit" class="btn btn-primary btnEnviar" name="btnVerPrestamo" value="Detalle">
				            </td>
       					 </tr>
			             
			            
			        </tbody>
			    </table>
			

			    <a  href="PrestamosSolicitud.jsp" class="btn btn-success mt-2 mb-5">Nuevo Prestamo</a>
			</div>
			

        </div>
        
      </div>
      
      
	       </div>
	 	<!--FOOTER-->
	    <jsp:include page= "/WEB-INF/Components/footer.html"></jsp:include>
	 </body>
</html>