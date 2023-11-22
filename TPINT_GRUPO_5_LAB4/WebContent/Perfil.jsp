<%@page import="entidad.TipoAcceso"%>
<%@page import="entidad.Cliente" %>
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
<% 
		Cliente cliente=(Cliente)session.getAttribute("cliente");
		String sexo="";
		if (cliente.getSexo().equals("M")){
	  		sexo="Masculino";
		}
		if(cliente.getSexo().equals("F")){
			sexo="Femenino";
		}
		
%>
      <!--CONTENT-->
      <div  class="col-lg-10 col-md-12 mx-2">
<div class="container">
        <div class="pt-2 d-flex gap-4 justify-content-center row">
          <h1 class="mt-4" >MIS DATOS</h1>
        </div>
        <div class="d-flex flex-column gap-4 mt-4">
          <!-- CONTENIDO-->
          <div class="row">
        <div class="col-md-4">
          <div class="border border-1 rounded p-2"  style="min-height: 330px;" >
          		<h3><i class="fas fa-user" style="margin-right: 0.5em;"></i>DATOS PERSONALES</h3>
	          <p class="mb-0">Nombre y Apellido</p>
	          <p class="fs-5"><%=cliente.getNombre() + " " + cliente.getApellido() %></p>
	          <p class="mb-0">CUIL</p>
	          <p class="fs-5"><%=cliente.getCuil() %></p>
	          <p class="mb-0">Fecha de Nacimiento</p>
	          <p class="fs-5"><%=cliente.getNacimiento() %></p>
	          <p class="mb-0">Sexo</p>
	          <p class="fs-5"><%=sexo %></p>
          	</div>
          </div>
          
           <div class="col-md-4">
          <div class="border border-1 rounded p-2"  style="min-height: 330px;">
          	<h3><i class="fas fa-map-marker-alt" style="margin-right: 0.5em;"></i>DIRECCIÓN </h3>
	          <p class="mb-0">Domicilio</p>
	          <p class="fs-5"><%=cliente.getDireccion().getCalle() + " "+ cliente.getDireccion().getNumero()  %></p>
	       	  <p class="mb-0">Provincia</p>
	          <p class="fs-5"><%=cliente.getDireccion().getProvincia().getNombre() %></p>
	       	  <p class="mb-0">Localidad</p>
   	          <p class="fs-5"><%=cliente.getDireccion().getLocalidad().getNombre() %></p>
	       	 
          	</div>
          </div>
          
           <div class="col-md-4">
          <div class="border border-1 rounded p-2"  style="min-height: 330px;">
          	<h3><i class="fas fa-phone" style="margin-right: 0.5em;"></i> CONTACTO </h3>
	          <p class="mb-0">Domicilio</p>
	          <p class="fs-5"><%=cliente.getDireccion().getCalle() + " "+ cliente.getDireccion().getNumero() + " - " + cliente.getDireccion().getProvincia().getNombre() %></p>
	          <p class="mb-0">Correo</p>
	          <p class="fs-5"><%=cliente.getEmail() %></p>
	          <p class="mb-0">Telefono</p>
	          <p class="fs-5"><%=cliente.getTelefono() %></p>
	          
          	</div>
          </div>
          </div>
          
        
        </div>
        <div class="mt-2 mb-2">
            <a href="ServletHomeCliente?homecliente=homecliente" class="btn btn-primary btnEnviar"><i class="fa-solid fa-arrow-left me-4"></i>Regresar</a>
        </div>
 </div>       
        
      </div>



	       </div>
	 	<!--FOOTER-->
	    <jsp:include page= "/WEB-INF/Components/footer.html"></jsp:include>
	 </body>
</html>