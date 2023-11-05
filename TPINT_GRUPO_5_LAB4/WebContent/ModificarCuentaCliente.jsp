<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@page import="entidad.TipoAcceso"%>
<%@page import="entidad.Cliente"%>
<%@page import="entidad.TipoCuenta"%>
<%@page import="entidad.Cuenta"%>

<!-- PRUEBA -->
<%@page import="negocioDaoImp.ClienteNegocioDaoImp"%>
<%@page import="negocioDaoImp.CuentaNegocioDaoImp"%>

<!-- PRUEBA -->
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
			    	 <%  ClienteNegocioDaoImp clienteNegocio=new ClienteNegocioDaoImp();
			    	 	CuentaNegocioDaoImp cuentaNegocio=new CuentaNegocioDaoImp();
			    	 	Cliente cliente=new Cliente();
			    	 	Cuenta cuenta=new Cuenta();
			    	 	cliente=clienteNegocio.obtenerUno(4);
			    	 	cuenta=cuentaNegocio.obtenerUna(1000000035);
			    	 %>
			    	 
			    	   <!-- <% /*if(request.getAttribute("cliente")!=null && request.getAttribute("cuenta")!=null && request.getAttribute("tiposCuenta")!=null) {
			    	 	Cliente cliente=(Cliente)request.getAttribute("cliente");
			    	 	Cuenta cuenta=(Cuenta)request.getAttribute("cuenta");*/
			    	 	
			    	 %> -->
			    	  <form action="ServletModificarCuenta" method="post" onsubmit="return confirm('¿Está seguro que desea modificar la cuenta?')">
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

						                	  <% /*List<TipoCuenta> tiposCuenta = (List<TipoCuenta>)request.getAttribute("tiposCuenta"); */
							            		 List<TipoCuenta> tiposCuenta=(List<TipoCuenta>)cuentaNegocio.listarTiposCuenta();
						                	  		
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
							                 <select class="form-control mt-2 me-2" name="activa" id="activa" value="<%= cuenta.getSaldo() %>">
								                 <%
												    boolean valorDefecto = true; 
												    boolean valorActual = cuenta.isActivo(); 
												    
												    %>
												    <option value="true" <%=valorActual ? "selected" : ""%>>Si</option>
												    <option value="false" <%=!valorActual ? "selected" : ""%>>No</option>

								            </select>
							         </div>
							           
							        </div>
						    </div>
						    <div class="text-center" style="margin-top:5%">
						        <input type="submit" class="btn btn-primary" name="btnModificarCuentaCiente" value="Modificar Cuenta Cliente">
						    </div>
						    
						     <div class="text-center" style="margin-top:1%">
						    		<a href="#" class="btn btn-success btnVolver" width="200px">Volver al listado</a>
						    </div>
						    
						</form>
			    	 <!--   <% /*}*/ %> -->
			    	
			    </div>
			</div>
 	<!--FIN FOOTER-->
	<!--FOOTER-->
	    <jsp:include page= "/WEB-INF/Components/footer.html"></jsp:include>
	 </body>
</html>