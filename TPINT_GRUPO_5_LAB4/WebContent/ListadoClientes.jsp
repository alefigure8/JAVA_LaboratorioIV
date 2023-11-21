<%@page import="entidad.Provincia"%>
<%@page import="entidad.TipoAcceso"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@page import="entidad.Cliente"%>
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

     		<!--MAIN-->
		      <div class="col-lg-10 col-md-12 d-flex flex-column justify-content-between">
		        <div class="w-100 pt-2">
	            	<!--TIUTLO PAGINA-->
	           		<h1 class="mt-4"> <i class="fas fa-users me-2"></i>LISTADO CLIENTES</h1>
	          	</div>
		        <div class="flex-grow-1">
		          <!-- CONTENIDO-->
			        <div class="d-flex flex-md-row flex-column justify-content-around align-items-center w-100 gap-2 mt-4">
			          <div class="col-4 text-md-start">
			            <h4 class="opacity-75">Listado de Clientes Generales</h4>
			          </div>
			          <div class="col-md-8 mb-4">
				        <!--FILTRO-->
			            <form action="ServletListarClientes" id="form" method="get" class="d-flex justify-content-around align-items-center gap-2 flex-md-row flex-column" onsubmit="return validarFechas()">
		
							  <!--  ESTADO -->
			                   <select id="opcion" name="opcion" class="form-select">
			                    <option value="todos">Todos los Estados</option>
			                    <option value="activos">Activos</option>
			                    <option value="inactivos">Inactivos</option>
			                  </select>
			                  
			                  <!--  GENERO -->
			                  <select name="genero" id="genero" class="form-select">
			                    <option value="todos">Todos los Generos</option>
			                    <option value="masculino">Masculino</option>
			                    <option value="femenino">Femenino</option>
			                  </select>
			                  
			                   <!--  PROVINCIA -->
			                   <select id="provincia" name="provincia" class="form-select ">
			                   	<option value="todas">Todas las Provincias</option>
			                   <%
			                   	if(session.getAttribute("provinciasFiltroCliente") != null){
			                   		List<Provincia> listaProvincias = (List<Provincia>)session.getAttribute("provinciasFiltroCliente");
			                   		
			                   		for(Provincia provincia : listaProvincias){
			                   			if(session.getAttribute("provinciaSelect") != null && !session.getAttribute("provinciaSelect").equals("todas") && Integer.parseInt((String)session.getAttribute("provinciaSelect")) == provincia.getIdProvincia()){
			                  		 %>
			                   		
			                    	<option value="<%=provincia.getIdProvincia()%>" selected ><%=provincia.getNombre()%></option>
			                  			<%} else { %>
			                	  	<option value="<%=provincia.getIdProvincia()%>" ><%=provincia.getNombre()%></option>
			                	  <%}
			                  	}
			                  } %>
			                  </select>
			                  
			                  <!--  NACIMIENTO -->
			                  <div class="d-flex gap-2">
				                <span >Desde: </span>
				                <input type="date" id="desde" name="fechaDesde" id="fechaDesde"/>
				              </div>
				              <div class="d-flex gap-2">
				                <span>Hasta: </span>
				                <input type="date" id="hasta" name="fechaHasta" id="fechaHasta"/>
				              </div>
			                  <input type="submit" class="btn btn_main" name="btnFiltrarTransferencias" value="Buscar"/>
			                  <input type="submit" class="btn btn_main" name="btnLimpiarFiltros" value="Limpiar filtros"/>  
		                </form >
			          </div>
			        </div>
	       			 <!-- FIN FILTRO -->
					<div>
					    <table id="table_id" class="table display text-center">
					        <thead>
					            <tr>
					                <th>Nombre de Usuario</th>
					                <th>Nombre y Apellido </th>
					                <th>DNI</th>
					                <th>CUIL</th>
					                <th>Nacionalidad</th>
					                <th>Fecha de Nacimiento</th>
					                <th>Correo</th>
					                <th>Telefono</th>
					                <th>Modificar</th>
					                <th>Eliminar Cliente</th>
					            </tr>
					        </thead>
					        <tbody>
							    <%
							    List<Cliente> clientes = (List<Cliente>) request.getAttribute("clientes");
							    if (clientes != null) {
							        for (Cliente cliente : clientes) {
							    %>
							        <tr>
							            <td><%= cliente.getUsuario() %></td>
							            <td><%= cliente.getNombre() + " " + cliente.getApellido() %></td>
							            <td><%= cliente.getDni() %></td>
							            <td><%= cliente.getCuil() %></td>
							            <td><%= cliente.getNacionalidad() %></td>
							            <td><%= cliente.getNacimiento() %></td>
							            <td><%= cliente.getEmail() %></td>
							            <td><%= cliente.getTelefono() %></td>
							            <td>
								            <button onclick="window.location.href='${pageContext.request.contextPath}/servletModificarCliente?obtener=true&ID=<%=cliente.getId()%>'" class="btn btn_main bg-success text-light">
								           	 	Modificar
								            </button>
							            </td>
							            <td>
							            	<% if(cliente.getActivo()){ %>
							            	<button onclick="confirmarBorrado(<%=cliente.getId()%>)" class="btn btn_main bg-danger text-light">
							            		Eliminar
							            	</button>
							            	<%} else {%>
							            	<button onclick="confirmarActivacion(<%=cliente.getId()%>)" class="btn btn_main bg-warning text-black">
							            		Activar
							            	</button>
							            	<%} %>
							            </td>
							        </tr>
							    <%
							        }
							    }
							    %>
						</tbody>
		    		</table>
		    		<div>					
						<a class="btn btn-success " href="ServletAltaCliente?AltaCliente=true">Agregar cliente</a>
						<a href="PerfilBanco.jsp" class="btn btn-primary btnEnviar "><i class="fa-solid fa-arrow-left me-4"></i>Regresar</a>
					</div>	
		    		
				</div>
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
	 </body>
	 <script>
	 	 
	 	//Confirmar y borrar cliente
	 	function confirmarBorrado(id) {
		 if (confirm('¿Seguro que desea eliminar al cliente')) {
			 	window.location.href ="${pageContext.request.contextPath}/ServletListarClientes?borrar=true&ID=" + id;
		 	}
		 }
	 	
	 	function confirmarActivacion(id) {
		 if (confirm('¿Seguro que desea eliminar al cliente')) {
			 	window.location.href ="${pageContext.request.contextPath}/ServletListarClientes?activar=true&ID=" + id;
		 	}
		 }
	 	
	 	let desde = ""
	 	<%if(session.getAttribute("desdeSelect")!=null){%>
	 		desde = "<%= session.getAttribute("desdeSelect") %>"
	 	<%}%>
	 	
	 	const selectDesde = document.getElementById("desde");
	 	selectDesde.value = desde;

	 	
	 	let hasta = ""
	 	<%if(session.getAttribute("hastaSelect")!=null){%>
	 		hasta = "<%= session.getAttribute("hastaSelect") %>"
	 	<%}%>
	 	
	 	const selectHasta= document.getElementById("hasta");
	 	selectHasta.value = hasta;

	 	let genero = ""
	 	
	 	<%if(session.getAttribute("generoSelect")!=null){%>
	 		genero = "<%= session.getAttribute("generoSelect") %>"
	 	<%}%>
	 	
	 	const selectGenero= document.getElementById("genero");

	 	for (let i = 0; i < selectGenero.options.length; i++) {
	 		selectGenero.options[i].value == genero ? selectGenero.options[i].selected = true : selectGenero.options[i].selected = false;
	 	}
	 	
	 	let opcion = ""
	 	<%if(session.getAttribute("opcionSelect")!=null){%>
	 		opcion = "<%= (String)session.getAttribute("opcionSelect") %>"
	 	<%}%>

	 	const selectOpcion = document.getElementById("opcion");

	 	for (let i = 0; i < selectOpcion.options.length; i++) {
	 		selectOpcion.options[i].value == opcion ? selectOpcion.options[i].selected = true : selectOpcion.options[i].selected = false;
	 	}
	 	
	 </script>
</html>