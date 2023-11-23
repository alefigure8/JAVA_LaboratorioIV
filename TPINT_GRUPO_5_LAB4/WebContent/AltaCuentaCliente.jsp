<%@page import="entidad.TipoAcceso"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!-- AUTENTICACION -->
<jsp:include page="/WEB-INF/Components/autenticacion/autenticacion.jsp"> 
	<jsp:param name="TipoUsuarioPagina" value="<%=TipoAcceso.Administrador%>" />
</jsp:include>
<!-- FIN AUTENTICACION -->

<%@page import="entidad.Cliente"%>
<%@page import="entidad.TipoCuenta"%>
<%@page import="java.util.List"%>


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
	<!--FIN HEAD-->
	<body class="d-flex flex-column">
	    <div class="row flex-grow-1 m-0">
	      <!--SIDEBAR-->
	      <jsp:include page= "/WEB-INF/Components/menu.jsp">
	      	<jsp:param name="usuario" value="Ramón Ramirez" />
	      </jsp:include>
		      
		 <!--MAIN-->
			<div class="col-lg-9 col-md-12 d-flex flex-column justify-content-between">
			     <div class="w-100 pt-2">
			        <h1 style="margin-bottom:10%"> <i class="fas fa-money-check me-2"></i>ALTA DE CUENTA DE CLIENTE</h1>
			     </div>
			   <div class="flex-grow-1">
	          <!-- CONTENIDO-->     
				  <% if(request.getAttribute("cliente")==null ) {
				  %>  
				  <div class="row">
           			 <div class="col-12">
					      <form action="ServletAltaCuenta" method="post" class="mx-auto" style="width: 300px;">
					      	<label for="dni"></label>
					      	<input type="text" id="dni" name="dni" required placeholder="Ingrese Dni de cliente" oninput="this.value = this.value.replace(/[^0-9]/g, '');this.value = this.value.substring(0, 8);validateInput(this, 1);">
					      	<input type="submit" name="btnBuscarDni" value="Buscar" class="btn btn-primary btnEnviar">
					      	 	<% if(request.getAttribute("existedni")!=null && (boolean)request.getAttribute("existedni")==false){
						

						
					  %>
				      		<label style="color: red" for="dni">Ingrese un Dni valido </label>
				   <%} %>
					      	<div class="text-center" style="margin-top:5%">
						    	  <a type="submit" class="btn btn-primary btnEnviar" href="/TPINT_GRUPO_5_LAB4/ServletCuentasClientes?Cuentas=CuentasClientes" width="200px">Volver a Listado</a>
						    </div>
					      </form>   
					  </div>
					 
					  
					</div>
				
				    <%} %>
				    
				    
				    
				   <%  if(request.getAttribute("cantidadCuentas")!=null){
					   int cantidadCuentas=(int)request.getAttribute("cantidadCuentas");
					  		 if (request.getAttribute("cliente")!=null && cantidadCuentas<3){ 
						   			Cliente cliente= new Cliente();
						   			cliente=(Cliente)request.getAttribute("cliente");
				   %>
				       
				   
			    	  <form action="ServletAltaCuenta" method="post" onsubmit="return confirm('¿Está seguro que desea realizar el alta?')">
						    <div class="row justify-content-center">
						        <div class="col-md-4">
						            <div class="form-group">
						                <label for="nombre">Nombre</label>
						                <input type="text"  class="form-control" id="nombre" readonly="true" disabled value="<%= cliente.getNombre() %>">
						            </div>
						            <div class="form-group">
						                <label for="nombre">Apellido</label>
						                <input type="text" class="form-control" id="apellido" readonly="true" disabled value="<%= cliente.getApellido() %>">
						            </div>
						            <div class="form-group">
						                <label for="dni">Dni</label>
						                <input type="text" class="form-control" id="dni" readonly="true" disabled value="<%= cliente.getDni() %>">
						            </div>
						           
						        </div>
						        
						        <div class="col-md-4">
						        <div class="form-group">
						        
						                <label for="tipoCuenta">Tipo Cuenta</label>
						               
						                 <select class="form-control mt-2 me-2" name="tipoCuenta" id="tipoCuenta">
						                	  <% List<TipoCuenta> tiposCuenta = (List<TipoCuenta>)request.getAttribute("tiposCuenta"); 
						                	  		for(TipoCuenta tc: tiposCuenta){
						                	  %>
							                <option value="<%= tc.getId()%>"><%= tc.getDescripcion() %></option>
							                
							           		 <% } %>
							            </select>
						            </div>
						           
						           <div class="form-group">
						                <label for="monto">Monto inicial</label>
						                <input type="monto" class="form-control" id="monto" readonly="true" disabled value="$10.000">
						            </div>
						           
						        </div>
						           
						        </div>
						        
						         <div class="text-center" style="margin-top:5%">
						        	<input type="submit" class="btn btn-primary btnEnviar" name="btnAltaCuentaCliente" value="Generar alta de cuenta" width="200px" >
						        	
						    	</div>
						    	
						    	 <div class="text-center" style="margin-top:1%">
						    	  <a type="submit" class="btn btn-primary" href="/TPINT_GRUPO_5_LAB4/ServletCuentasClientes?Cuentas=CuentasClientes" width="200px">Volver a Listado</a>
						    	</div>
						</form>
					</div>
						   
			    	<%}
					   		 if (request.getAttribute("cliente")!=null && cantidadCuentas>2){
					   		%>
					   			 <div class="container text-center mt-2">
								    <div class="alert alert-danger p-4" role="alert" style="max-width: 500px; margin: 0 auto;">
								        <h4 class="alert-heading">¡Ups! Error</h4>
								        <p>El cliente ya posee 3 cuentas y no puede crear una nueva.</p>
								    </div>
								
								    <div class="mt-4">
								        <a href="AltaCuentaCliente.jsp" class="btn btn-primary btnEnviar me-3">Atrás</a>
								     
								    </div>
								</div>

							    
							    
					   		<%} 
					   
					  }%>  
			    	
			    	
			    	
			    </div>
			</div>
 		<!--FIN MAIN-->
		<!--FOOTER-->
	    <jsp:include page= "/WEB-INF/Components/footer.html"></jsp:include>
	 </body>
</html>