<%@page import="entidad.TipoCuenta"%>
<%@page import="entidad.TipoAcceso"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="entidad.TipoTasa"%>
<%@page import="entidad.Cuenta"%>
<%@page import="java.util.List"%>
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
		      
		  <% if(session.getAttribute("cuentasCliente")!=null && session.getAttribute("tiposTasa")!=null) {
		  		List<TipoTasa> tiposTasa=(List<TipoTasa>)session.getAttribute("tiposTasa");
				List<Cuenta> cuentasCliente=(List<Cuenta>)session.getAttribute("cuentasCliente");
		  %>
		  
		  <% if(session.getAttribute("tipoTasaSeleccionada")!=null){
				Double tasaSeleccionada=Double.parseDouble(session.getAttribute("tipoTasaSeleccionada").toString()) ; 
		  }%>
	      <!--CONTENT-->
	      <div class="col-lg-9 col-md-12 d-flex flex-column justify-content-between">
	        <div class="w-100 pt-2">
	          <h1>SOLICITUD DE PRESTAMO</h1>
	        </div>
	        <div class="flex-grow-1">
	        
	          <!-- MAIN--> 
	      <form action="ServletPrestamos" method="get">
			      <div class="row d-flex ">
					    <div class="col-md-4 mx-auto">
					        <div class="form-group mt-2">
					            <label for="monto">Monto:</label>
					            <!-- INGRESO DE MONTO  (NUMERO POSITIVO) -->
					            <input type="text" class="form-control" id="monto" name="monto" value="<%= session.getAttribute("montoSeleccionado") != null ?  session.getAttribute("montoSeleccionado") : ""%>"required oninput="this.value = this.value.replace(/[^0-9]/g, '');this.value = this.value.substring(0, 10);validateInput(this, 1);">
					       		<label style="color:orange;">* El monto minimo es de $10.000</label>
					        </div>
					        <div class="form-group mt-2">
					            <label for="cuotas">Cantidad de Cuotas:</label>
					            <select class="form-control" id="cuotas" name="cuotas">
					            <!-- DESPLEGABLE DE TIPO TASA -->
					               <%
								    Double tasaSeleccionada = null;
					               String tipoTasa="";
					               	if(session.getAttribute("tipoTasaSeleccionada")!=null){
								    tipoTasa = session.getAttribute("tipoTasaSeleccionada").toString();
								    tasaSeleccionada=Double.parseDouble(tipoTasa);
								    System.out.println("TASA SEELCCIONADA" + tasaSeleccionada);
					               	}
								    
								
								    for (TipoTasa tasas : tiposTasa) {
								    	
								    	 System.out.println("TASA del for" + tasas.getTasaInteres());
								        String selected = (tasaSeleccionada != null && tasaSeleccionada == tasas.getTasaInteres()) ? "selected" : "";
								%>
								        <option value="<%= tasas.getTasaInteres() %>" <%= selected %>> <%= tasas.getCantCuotas() %> </option>
								<%
								    }
								%>

					            </select>
					        </div>
					        <div class="form-group mt-2">
					        	<!-- MONTO * % INTERES DEL DESPLEGABLE SELECCIONADO -->
					        	
					            <label for="interesTotal">Total de Intereses:</label>
					            <input type="text" class="form-control" id="interesTotal" value="<%= session.getAttribute("interesCalculado") != null ?  session.getAttribute("interesCalculado") : ""%>" name="interesTotal" disabled>
					        </div>
					        <div class="form-group mt-2">
					        	<!-- MONTO + INTERESES -->
					            <label for="totalMonto">Total (Monto + Intereses):</label>
					            <input type="text" class="form-control" id="totalMonto" value="<%= session.getAttribute("totalCalculado") != null ?  session.getAttribute("totalCalculado") : "" %>"  name="totalMonto" disabled>
					        </div>
					        
					        <div class="form-group mt-2">
					         	<!-- DESPLEGABLE DE CUENTAS DEL CLIENTE (ACTIVAS) -->
					            <label for="cuotas">Nro. de cuenta a depositar</label>
					            <select class="form-control" id="cuentas" name="cuentas">
					                <% for(Cuenta cuentas: cuentasCliente) {%>
					                	<option value="<%= cuentas.getNumeroCuenta() %>"> <%= cuentas.getNumeroCuenta() %> </option>
					                <%} %>
					            </select>
					        </div>
					        
					            <input type="submit" class="btn btn-primary btnEnviar mt-4" name="btnCalcularIntereses" value="Calcular Intereses">
   							    <input type="submit" class="btn btn-success mt-4" name="btnConfirmarSolicitarPrestamo" value="Solicitar Prestamo" onclick="return confirm('¿Está seguro que desea solicitar el prestamo?')">
					    	<div class="form-group mt-2">
							    <a href="PrestamosClientes.jsp" class=" btn btn-primary btnEnviar  "><i class="fa-solid fa-arrow-left me-4"></i>Regresar</a>
							</div>
					    
					    </div>
					    
					</div>
					
			</form>		
		
			
		        </div>
		        
		        
	      	</div>
       </div>
	 <%} else{%>
	 	<label for="cuotas">Nro. de cuenta a depositar</label>
	 <%} %>
	 	<!--FOOTER-->
	    <jsp:include page= "/WEB-INF/Components/footer.html"></jsp:include>
	 </body>
	 
	  <script>
    
		    function validateInput(input, minLength) {
		    	 const trimmedValue = input.value.trim();
		    	    if (trimmedValue<10000) {
		    	        input.setCustomValidity(`Monto minimo $10.000`);
		    	        input.classList.add("is-invalid");
		    	    } 
		    	    else {
		    	        input.setCustomValidity('');
		    	        input.classList.remove("is-invalid");
		    	        input.classList.add("is-valid");
		    	    }
		    }
		    
		    

    </script>
	 
</html>