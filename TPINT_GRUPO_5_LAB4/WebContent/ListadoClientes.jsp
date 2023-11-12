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
	           		<h1 class="mt-2">LISTADO CLIENTES</h1>
	          	</div>
		        <div class="flex-grow-1">
		          <!-- CONTENIDO-->
					<div class="d-flex gap-2 align-items-center justify-content-between w-100">
						<div class="col-md-7 text-md-start text-center">
		                	<h4 class="opacity-75">Listado de Clientes Generales</h4>
		             	</div>
						<p>Mostrar: </p>
						<select class="form-select form-select-sm w-md-25 w-25 mb-3" onchange="filtroActivos(this)" data-selected="<%=request.getParameter("filtro")%>" id="filtroActivo">
							<option id="activos">Activos</option>
							<option id="inactivos">Inactivos</option>
						</select>
					</div>

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
							            <td><button onclick="window.location.href='${pageContext.request.contextPath}/servletModificarCliente?obtener=true&ID=<%=cliente.getId()%>'" class="btn btn_main bg-success text-light">Modificar</button></td>
							            <td><button onclick="confirmarBorrado(<%=cliente.getId()%>)" class="btn btn_main bg-danger text-light">Eliminar</button></td>
							        </tr>
							    <%
							        }
							    }
							    %>
						</tbody>
		    		</table>
		    		<div>					
						<a class="btn btn-primary btnEnviar" href="ServletAltaCliente?AltaCliente=true">Agregar cliente</a>
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
	 
	 	const filtroActivo = document.getElementById("filtroActivo");
	 	const filtroSeleccionado = filtroActivo.getAttribute('data-selected');
	 	filtroActivo.value=filtroSeleccionado;	
	 
	 	//Confirmar y borrar cliente
	 	function confirmarBorrado(id) {
		 if (confirm('¿Seguro que desea eliminar al cliente')) {
			 	window.location.href ="${pageContext.request.contextPath}/ServletListarClientes?borrar=true&ID=" + id;
		 	}
		 }
	 	
	 	function filtroActivos(select){
	 		const option = select.value;
	 		window.location.href ="${pageContext.request.contextPath}/ServletListarClientes?obtener=true&filtro=" + option;
	 	}
	 </script>
</html>