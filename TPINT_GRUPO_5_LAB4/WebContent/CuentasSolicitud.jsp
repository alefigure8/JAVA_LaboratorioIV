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
		      	<jsp:param name="usuario" value="Ram�n Ramirez" />
		      </jsp:include>

      <!--CONTENT-->
      <div class="col-lg-9 col-md-12 d-flex flex-column justify-content-between">
        <div class="w-100 pt-2">
          <h1>SOLICITUD DE CUENTA</h1>
        </div>
        <div class="flex-grow-1">
          <!-- CONTENIDO-->
       
			
			<form class="container mt-4 d-flex justify-content-center align-items-start" action="#" method="post">
			    <div class="form-group col-md-4 d-flex flex-column">
			        <label for="tipoCuenta">Seleccione el tipo de cuenta a solicitar:</label>
			        <div class="d-flex">
			            <select class="form-control mt-2 me-2" name="tipoCuenta" id="tipoCuenta">
			                <option>Caja de Ahorro</option>
			                <option>Cuenta Corriente</option>
			            </select>
			            <input type="submit" class="btn btn-primary btnEnviar" name="btnEnviarSolicitudCuenta" value="Enviar solicitud">
			        </div>
			    </div>
			</form>
			
        </div>
        
      </div>


	       </div>
	 	<!--FOOTER-->
	    <jsp:include page= "/WEB-INF/Components/footer.html"></jsp:include>
	 </body>
</html>