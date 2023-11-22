
<%@page import="entidad.Destinatario"%>
<%@page import="entidad.Operacion"%>
<%@page import="entidad.EstadoCuota"%>
<%@page import="entidad.Estado"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.HashMap"%>
<%@page import="entidad.Movimiento"%>
<%@page import="entidad.Cliente"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="entidad.TipoAcceso"%>

<!-- AUTENTICACION -->
<jsp:include page="/WEB-INF/Components/autenticacion/autenticacion.jsp"> 
	<jsp:param name="TipoUsuarioPagina" value="<%=TipoAcceso.Cliente%>" />
</jsp:include>
<!-- FIN AUTENTICACION -->

<!-- SCRIPTLE -->
<%
	Cliente cliente = new Cliente();

	if(session.getAttribute("cliente")!=null){
		cliente = (Cliente)session.getAttribute("cliente");
	}
	
	List<Movimiento> listadoMovimientos = new ArrayList<Movimiento>();
	
	if(request.getAttribute("lista")!=null){
		listadoMovimientos = (List<Movimiento>)request.getAttribute("lista");
	}
	
	HashMap<Integer, Destinatario> destinatarios = new HashMap<Integer, Destinatario>();
	
	if(request.getAttribute("destinatarios")!=null){
		destinatarios = (HashMap<Integer, Destinatario>)request.getAttribute("destinatarios");
	}

%>
<!-- FIN SCRIPTLE -->

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
	<!--FIN HEAD -->      
	<body class="d-flex flex-column">
	    <div class="row flex-grow-1 m-0">
	      <!--SIDEBAR-->
	      <jsp:include page= "/WEB-INF/Components/menu.jsp">
	      	<jsp:param name="usuario" value="Ram�n Ramirez" />
	      </jsp:include>
      <!--CONTENIDO-->
	    <div class="col-10 d-flex flex-column justify-content-between">
	    	<!--TITULO-->
			<div class="w-100 pt-2">
			  <!--TIUTLO PAGINA-->
			  <h1 class="mt-4"><i class="fas fa-exchange-alt me-2"></i>TRANSFERENCIA</h1>
			</div>
			<div class="text-md-start">
	            <h4 class="opacity-75">Nueva Transferencia</h4>
          	</div>
	    	<div class="m-0 d-flex col-12 col-md-4">
   				<a href="/TPINT_GRUPO_5_LAB4/ServletNuevaTransferencia?cargacbu=true&otraCuenta=true" class="btn btn-main btn-success mt-4 d-flex justify-content-center align-items-center p-4 m-1">Otra Cuenta</a>
      			<a href="/TPINT_GRUPO_5_LAB4/ServletNuevaTransferencia?cargacbu=true&cuentaPropia=true" class="btn btn-main btn-success mt-4 d-flex justify-content-center align-items-center p-4 m-1">Cuenta Propia</a>
	      	</div>
	      <div class="flex-grow-1">
	      
	        <!--FILTRO-->
	        <div class="d-flex flex-md-row flex-column justify-content-around align-items-center w-100 gap-2 mt-4">
	          <div class="col-4 text-md-start">
	            <h4 class="opacity-75">Historial de transferencias realizadas</h4>
	          </div>
	          <div class="col-md-8 mb-4">
	            <form action="ServletListaTransferencias" method="get" class="d-flex justify-content-around align-items-center gap-2 flex-md-row flex-column" onsubmit="return validarFechas()">
                <input type="hidden" name="listado" value="true"/>
                  <select id="destino" name="cuentasDestino" class="form-select ">
                    <option value="todas">Todos los estados</option>
                    <option value="terceros">Tercerceros</option>
                    <option value="propias">Propias</option>
                  </select>
      
                   <select id="importe" name="importes" class="form-select ">
                    <option value="todas">Todos los importes</option>
                    <option value="mayor">Mayor a</option>
                    <option value="igual">Igual a</option>
                    <option value="menor">Menor a</option>
                  </select>
                  <input type="text" id="rango" name="rangoImporte" oninput="this.value = this.value.replace(/[^0-9]/g, '');this.value = this.value.substring(0, 10);">
                  
                  <div class="d-flex gap-2">
	                <span >Desde: </span>
	                <input type="date" name="prestamoDesde" id="desde">
	              </div>
	              <div class="d-flex gap-2">
	                <span>Hasta: </span>
	                <input type="date" name="prestamoHasta" id="hasta">
	              </div>
                  

                  <input type="submit" class="btn btn_main" name="btnFiltrarTransferencias" value="Buscar">
                  <input type="submit" class="btn btn_main" name="btnLimpiarFiltros" value="Limpiar filtros">
                  
                </form >
	          </div>
	        </div>
	        <!-- FIN FILTRO -->
	        
	        <!--TABLA-->
	        <div class="d-flex flex-md-row flex-column">
	          <div class="h-100 me-4 w-100">
	            <table id="table_id_2" class="table display text-center">
	              <!-- TABLE HEAD -->
	              <thead>
	                <tr>
	                  <th scope="col">Fecha</th>
	                  <th scope="col">Destinatario</th>
	                  <th scope="col">Monto</th>
	                  <th scope="col">Estado</th>
	                  <th scope="col">Operacion</th>
	                  <th scope="col">Detalle</th>
	                </tr>
	              </thead>
	              <tbody>
	              
	              <!-- INICIO FOR -->
	              <% for(Movimiento movimiento : listadoMovimientos) {
	            	  Destinatario destinatario = destinatarios.get(movimiento.getNumeroReferencia());
	              %>
	              
	                <tr>
	                  <!-- FECHA -->
	                  <td><span class="black-75"><%= movimiento.getFechaMovimiento() %></span></td>
	                  
	                  <!-- DESTINATARIO -->
					  <td>
						<span class="black-75">
							<% if (destinatario != null) { %>
								<%= destinatario.getNombre() + " " + destinatario.getApellido() %>
							<% } else { %>
								<!-- Manejar el caso donde destinatario es nulo -->
								Cuenta Propia - <%= movimiento.getCuenta().getNumeroCuenta() %>
							<% } %>
							<i class="fa-solid fa-user opacity-75 ms-2"></i>
						</span>
					</td>
					
					 <!-- MONTO -->
	                  <td>
		                  <%if(movimiento.getOperacion().equals(String.valueOf(Operacion.Entrada))){%>
		                  	<span class="fw-bolder text-success">
		                  		<%= NumberFormat.getCurrencyInstance(new Locale("es", "AR")).format(movimiento.getMonto()) %> 
		                  	</span>
		                  <%}else{%> 
		                  	<span class="black-75 text-danger">
		                  		<%= NumberFormat.getCurrencyInstance(new Locale("es", "AR")).format(movimiento.getMonto()) %> 
	                  		</span>
		                  <%}%>
	                  </td>
	                  
	                  <!-- ESTADO -->
	                  <td>
		                  <span class="badge 
			                  	<%if(movimiento.getEstado().getDescripcion().equals("Aprobado")){%> 
			                  		bg-success 
			                  	<%} else {%> 
			                  		bg-danger 
			                  	<%}%> 
		                  			text-white">
		                  		<%= movimiento.getEstado().getDescripcion() %>
               			 </span>
           			</td>
               			
                  	<!-- OPERACION -->
	                  <td>
	                  	<span class="black-75 me-4">
	                  		<%= movimiento.getOperacion()%> 
	                  		<i class="fa-solid 
	                  			<%if(movimiento.getOperacion().equals(String.valueOf(Operacion.Salida))){%>
	                  				 fa-arrow-right text-danger 
	                  			<%} else {%> 
	                  				fa-arrow-left text-success 
	                  			<%}%>  opacity-75">
	                  		</i>
	                  	</span>
	                  </td>
	                  
	                  <!-- DETALLE -->
	                  <td>
	                  	<a class="p-2 bg-secondary text-light rounded" href="ServletDetalleTransferencia?numeroReferencia=<%= movimiento.getNumeroReferencia()%>&id=<%=movimiento.getId()%>">
	                  		<i class="fa-solid fa-circle-info"></i> Detalle
	                  	</a>
                  	  </td>
	                </tr>
	                
					<%} %>
					<!-- FIN FOR -->
					
	              </tbody>
	            </table>  
	          </div>
	        </div>
	          <a href="ServletHomeCliente?homecliente=homecliente" class="p-2 rounded bg-main text-white text-decoration-none col-4 col-md-1 mb-4"><i class="fa-solid fa-arrow-left me-4"></i>Regresar</a>
	      </div>
	    </div>
	 </div>
	 	<!--FOOTER-->
	    <jsp:include page= "/WEB-INF/Components/footer.html"></jsp:include>
	    
	    <!-- POPUP ERROR DE AUTENTICACION-->
		<%if(request.getAttribute("tipo") != null){
			%>
			<jsp:include page="/WEB-INF/Components/popup.jsp">
				<jsp:param name="tipo" value="<%= request.getAttribute(\"tipo\") %>"/>
				<jsp:param name="mensaje" value="<%= request.getAttribute(\"mensaje\") %>"/>
				<jsp:param name="titulo" value="<%= request.getAttribute(\"titulo\") %>"/>
			</jsp:include>
		<% } %>
		<!-- FIN POPUP -->
		
		<!-- SCRIPT -->		
		<script>
			function operacion(select){
				const opcion = select.value;
				window.location.href ="${pageContext.request.contextPath}/ServletListaTransferencias?listado=true&operacion=" + opcion;
			}
			
			
		 	let desde = ""
			 	<%if(session.getAttribute("fechaDesdeSelect")!=null){%>
			 		desde = "<%= session.getAttribute("fechaDesdeSelect") %>"
			 	<%}%>
			 	
			 	const selectDesde = document.getElementById("desde");
			 	selectDesde.value = desde;

			 	
			 	let hasta = ""
			 	<%if(session.getAttribute("fechaHastaSelect")!=null){%>
			 		hasta = "<%= session.getAttribute("fechaHastaSelect") %>"
			 	<%}%>
			 	
			 	const selectHasta= document.getElementById("hasta");
			 	selectHasta.value = hasta;

			 	let importe = ""
			 	let rango = ""
			 	<%if(session.getAttribute("importesSelect")!=null){%>
			 		importe = "<%= session.getAttribute("importesSelect") %>"
			 		rango = "<%= session.getAttribute("rangoSelect") %>"
			 	<%} else {%>
			 		rango = 0;
			 	<%}%>
			 	
			 	const selectImporte= document.getElementById("importe");
				const rangoInput = document.getElementById("rango");
				rangoInput.value = rango;
				
			 	for (let i = 0; i < selectImporte.options.length; i++) {
			 		selectImporte.options[i].value == importe ? selectImporte.options[i].selected = true : selectImporte.options[i].selected = false;
			 	}
			 	
			 	let destino = ""
			 	<%if(session.getAttribute("destinoSelect")!=null){%>
			 		destino = "<%= (String)session.getAttribute("destinoSelect") %>"
			 	<%}%>

			 	const selectDestino = document.getElementById("destino");

			 	for (let i = 0; i < selectDestino.options.length; i++) {
			 		selectDestino.options[i].value == destino ? selectDestino.options[i].selected = true : selectDestino.options[i].selected = false;
			 	}
			
		</script>
	 </body>
</html>
