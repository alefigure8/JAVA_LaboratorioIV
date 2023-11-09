<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@page import="entidad.TipoAcceso"%>
<%@page import="entidad.Cliente"%>
<%@page import="entidad.TipoCuenta"%>
<%@page import="entidad.Cuenta"%>

<!-- PRUEBA -->
<%@page import="negocioDaoImp.ClienteNegocioDaoImp"%>
<%@page import="negocioDaoImp.CuentaNegocioDaoImp"%>

<!-- PRUEBA -->
<%@page import="java.util.ArrayList"%>
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
				        <h1 style="margin-bottom:10%">MODIFICAR CUENTA DE CLIENTE</h1>
				     </div>
				   <div class="flex-grow-1">
				          <!-- CONTENIDO-->
			    	 <%
			    	 	Cliente cliente=null;
			    	 	Cuenta cuenta=null;
			    	 	ArrayList<TipoCuenta> tiposCuenta = null;
			    	 	Integer cantidadcuentas = null;
			    	 %>
			    	 
			    	   <!-- <% 
			    	   
			    	   if(request.getAttribute("cliente")!=null && request.getAttribute("cuenta")!=null && request.getAttribute("tiposCuenta")!=null && request.getAttribute("cantidadCuentas")!=null) {
			    	 	cliente=(Cliente)request.getAttribute("cliente");
			    	 	cuenta=(Cuenta)request.getAttribute("cuenta");
			    	 	tiposCuenta = (ArrayList<TipoCuenta>)request.getAttribute("tiposCuenta");
			    	 	cantidadcuentas = Integer.parseInt(request.getAttribute("cantidadCuentas").toString());
			    	   
			    	 %> -->
			    	  <form action="ServletModificarCuenta" method="get" onsubmit="return confirm('¿Está seguro que desea modificar la cuenta?')">
						    <div class="row justify-content-center">
						        <div class="col-md-4">
						            <div class="form-group">
						                <label for="nombre">Nombre</label>
						                <input type="text" class="form-control" id="nombre" readonly="true" disabled value="<%= cliente.getNombre() %>">
						            </div>
						            <div class="form-group">
						                <label for="nombre">Apellido</label>
						                <input type="text" class="form-control" id="apellido" readonly="true" disabled value="<%= cliente.getApellido() %>">
						            </div>
						            <div class="form-group">
						                <label for="dni">DNI</label>
						                <input type="text" class="form-control"  id="dni" readonly="true" disabled value="<%= cliente.getDni() %>">
						            </div>
						            
						            <input type="hidden" name="dni" value="<%= cliente.getDni() %>">
						            
						             <div class="form-group">
						                <label for="saldo">Saldo</label>
						                <input type="text" class="form-control" id="saldo" readonly="true" disabled value="<%= cuenta.getSaldo() %>">
						            </div>
						           
						        </div>
						        <div class="col-md-4">
						        
							        <div class="form-group">
							             <label for="tipoCuenta">Tipo Cuenta</label>
								             <select class="form-control" id="tipoCuenta" name="tipoCuenta">

						                	  <% 
						                	  					                	  		
							            		 for(TipoCuenta tc: tiposCuenta){
						                	  			int valorActual = tc.getId();
						                	  	        boolean seleccionado = valorActual==(cuenta.getTipoCuenta().getId());
						                	  
						                	  %>
        									<option value="<%=valorActual%>" <%=seleccionado ? "selected" : ""%>><%=tc.getDescripcion()%></option>							                
							           		 <% } %>
							         
								                
								             </select>
							                 
							         </div>
							         
							           
							         <div class="form-group">
							                <label for="cuenta">Nro. Cuenta</label>
							                <input type="text" class="form-control" id="cuenta"  readonly="true" disabled value="<%= cuenta.getNumeroCuenta() %>">
							            </div>
							            
							            <input type="hidden" name="cuenta" value="<%= cuenta.getNumeroCuenta() %>">
							            
							            <div class="form-group">
							                <label for="cbu">CBU</label>
							                <input type="text" class="form-control" id="cbu" readonly="true" disabled value="<%= cuenta.getCbu() %>">
							            </div>
							            
							            <div class="form-group">
							                <label for="activa">Cuenta Activa</label>
							                 
							                 <% if (cantidadcuentas<=2){%>
							                 }
							                 <select class="form-control mt-2 me-2" name="activa" id="activa" value="<%= cuenta.getSaldo() %>">
								                 <%
												    boolean valorDefecto = true; 
												    boolean valorActual = cuenta.isActivo(); 
												    
												    %>
												    <option value="true" <%=valorActual ? "selected" : ""%>>Si</option>
												    <option value="false" <%=!valorActual ? "selected" : ""%>>No</option>

								            </select>
								            <%} else{
								            	if(cuenta.isActivo()){
								            	%>
								            	
								            
								                  <select class="form-control mt-2 me-2" name="activa" id="activa" value="<%= cuenta.getSaldo() %>">
								                 <%
												    boolean valorDefecto = true; 
												    boolean valorActual = cuenta.isActivo(); 
												    
												    %>
												   <option value="false" <%=!valorActual ? "selected" : ""%>>No</option>
 												
								            </select>
								            
								            	<label style="color: red" for="activa">Este cliente ya cuenta con 3 cuentas activas</label>
								            
								            
								            <%} else{ %>
								               <select class="form-control mt-2 me-2" name="activa" id="activa" value="<%= cuenta.getSaldo() %>">
								                 <%
												    boolean valorDefecto = true; 
												    boolean valorActual = cuenta.isActivo(); 
												    
												    %>
												    <option value="true" <%=valorActual ? "selected" : ""%>>Si</option>
												    <option value="false" <%=!valorActual ? "selected" : ""%>>No</option>

								            </select>
								            
								            
								            <%}} %>
							         </div>
							           
							        </div>
						    </div>
						    <div class="text-center" style="margin-top:5%">
						        <input type="submit" class="btn btn-primary" name="btnModificarCuentaCiente" value="Modificar Cuenta Cliente">
			        
						    </div>
						    
						     <div class="text-center" style="margin-top:1%">
						         <a type="submit" class="btn btn-primary" href="/TPINT_GRUPO_5_LAB4/ServletCuentasClientes?Cuentas=CuentasClientes" width="200px">Volver a Listado</a>							    		
						    </div>
						    
						</form>
			    	 <!--   <%}%> -->
			    	
			    </div>
			</div>
 	<!--FIN FOOTER-->
	<!--FOOTER-->
	    <jsp:include page= "/WEB-INF/Components/footer.html"></jsp:include>
	 </body>
</html>