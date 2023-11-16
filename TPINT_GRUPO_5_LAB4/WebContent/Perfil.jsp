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
      <div  class="col-lg-10 col-md-12 mx-2">
        <div class="pt-2 d-flex gap-4 justify-content-center row">
          <h1>MI DATOS</h1>
        </div>
        <div class="d-flex flex-column gap-4 mt-4">
          <!-- CONTENIDO-->
          <div class="d-flex gap-4 row">
          	<div class="col-3 border border-1 rounded p-2">
          		<h3>DATOS</h3>
	          <p class="mb-0">Nombre y Apellido</p>
	          <p class="fs-5">Juan Perez</p>
	          <p class="mb-0">CUIL</p>
	          <p class="fs-5">20-35508974-1</p>
	          <p class="mb-0">Fecha de Nacimiento</p>
	          <p class="fs-5">25/04/1984</p>
	          <p class="mb-0">Sexo</p>
	          <p class="fs-5">Masculino</p>
          </div>
          <div class="col-3 border border-1 rounded p-2">
          	<h3>CONTACTO</h3>
	          <p class="mb-0">Domicilio</p>
	          <p class="fs-5">Montes de Oca 123 - Buenos Aires</p>
	          <p class="mb-0">Correo</p>
	          <p class="fs-5">juanperez@mail.com</p>
	          <p class="mb-0">Telefono</p>
	          <p class="fs-5">345-512-1455</p>
          </div>
          </div>
          
        
         <a href="ServletHomeCliente?homecliente=homecliente" class="p-2 rounded bg-main text-white text-decoration-none col-4 col-md-1 mb-4"><i class="fa-solid fa-arrow-left me-4"></i>Regresar</a>
        </div>
      </div>

	       </div>
	 	<!--FOOTER-->
	    <jsp:include page= "/WEB-INF/Components/footer.html"></jsp:include>
	 </body>
</html>