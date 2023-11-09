<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@page import="entidad.TipoAcceso"%>
<%@page import="entidad.Cuenta"%>
<%@page import="entidad.Cliente"%>
<%@page import="entidad.TipoCuenta"%>
<%@page import="java.util.List"%>

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

			<div class="col-lg-9 col-md-12 d-flex flex-column justify-content-between">
			 <div class="w-100 pt-2">
				 <h1 style="margin-bottom:10%">CUENTA GENERADA</h1>
			 </div>
				<div class="flex-grow-1">
			 	<!-- MAIN-->
   
			          <%if (request.getAttribute("cuenta")!=null){
			        	  Cuenta cuenta= new Cuenta();
			        	  cuenta=(Cuenta)request.getAttribute("cuenta");
			        	  %>
				          
				          
				          <div class="row justify-content-center">
						        <div class="col-md-6">
						            <div class="form-group">
						                <label for="nro">Nro. Cuenta</label>
						                <input type="text"  class="form-control" id="nro" readonly="true" disabled value="<%= cuenta.getNumeroCuenta() %>">
						            </div>
						            <div class="form-group">
						                <label for="cbu">CBU</label>
						                <input type="text" class="form-control" id="cbu" readonly="true" disabled value="<%= cuenta.getCbu()%>">
						            </div>
						            <div class="form-group">
						                <label for="tipo">Tipo de cuenta</label>
						                <input type="text" class="form-control" id="tipo" readonly="true" disabled value="<%= cuenta.getTipoCuenta().getDescripcion() %>">
						            </div>
						            
						            <div class="form-group">
						                <label for="fecha">Fecha de creación</label>
						                <input type="text" class="form-control" id=""fecha"" readonly="true" disabled value="<%= cuenta.getFechaCreacion() %>">
						            </div>
						           
						           <div class="form-group">
						                <label for="saldo">Saldo</label>
						                <input type="text" class="form-control" id="saldo" readonly="true" disabled value="<%= cuenta.getSaldo()%>">
						            </div>
						        </div>
						        
						        
						        
						         <div class="text-center" style="margin-top:5%">
						         	<a type="submit" class="btn btn-primary" href="/TPINT_GRUPO_5_LAB4/ServletCuentasClientes?Cuentas=CuentasClientes" width="200px">Volver a Listado</a>
						        	
						    	</div>
				          <% } %>
				          
				          
				          
					 </div>
				 </div>
			</div>
		</div>
		<!--FOOTER-->
	    <jsp:include page= "/WEB-INF/Components/footer.html"></jsp:include>
	 </body>
</html>