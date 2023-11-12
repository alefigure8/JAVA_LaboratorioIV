<%@page import="entidad.Estado"%>
<%@page import="entidad.TipoAcceso"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>
<%@page import="entidad.Cliente" %>
<%@page import="entidad.Prestamo" %>
<%@page import="entidad.Estado" %>

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


      <!--CONTENIDO-->
        <div class="col-10 d-flex flex-column justify-content-between">
          <div class="w-100 pt-2">
            <!--TIUTLO PAGINA-->
            <h1 class="mt-2">PRESTAMOS CLIENTES</h1>
          </div>
          <div class="flex-grow-1">
            <!--FILTRO-->
            <div class="d-flex flex-md-row flex-column justify-content-around align-items-center w-100 gap-2 mt-4">
              <div class="col-md-7 text-md-start text-center">
                <h4 class="opacity-75">Historial de los prestamos de clientes</h4>
              </div>
              <div class="col-md-5">
                <form action="ServletPrestamos" method="get" class="d-flex justify-content-around align-items-center gap-2  flex-md-row flex-column">
                
                  <!--  <div class="d-flex gap-2">
                    <input type="text" name="cliente" placeholder="Buscar">
                  </div>
                  <select name="Estados" class="form-select form-select-sm w-md-50">
                    <option value="Todos los Estados">Todos los Campos</option>
                    <option value="Cuenta">Cuenta</option>
                    <option value="CBU">CBU</option>
                    <option value="DNI">DNI</option>
                    <option value="CUIL">CUIL</option>
                    <option value="Apellido">Apellido</option>
                  </select>
                  -->
                  
                  
                  <select name="Estados" class="form-select form-select-sm w-md-50">
                    <option value="Todos los Estados">Todos los Estados</option>
                    <option value="Aprobado">Aprobados</option>
                    <option value="Rechazado">Rechazados</option>
                    <option value="Pendiente">Pendientes</option>
                    
	                    <%if(session.getAttribute("prestamoSeleccionado")!=null){
	                		String opcion=session.getAttribute("prestamoSeleccionado").toString();
	                		%>
	                  
	                   <script>
				           
				            document.querySelector('select[name="Estados"]').value = '<%= opcion %>';
				        </script>
	                  
	                  <% }%>
                    
                  </select>
                  <input type="submit" class="btn btn_main" name="btnFiltrarPrestamos" value="Buscar">
                  <!--   <input type="submit" class="btn btn_main" name="btnLimpiar" value="Limpiar filtros"> -->
                </form>
              </div>
            </div>

		<!--  LISTADO DE PRESTAMOS Y CLIENTES -->
		<% if(session.getAttribute("prestamos")!=null && session.getAttribute("clientes")!=null){
			List<Prestamo> prestamos= (List<Prestamo>)session.getAttribute("prestamos");
			List<Cliente> clientes= (List<Cliente>)session.getAttribute("clientes");
		
		%>
            <div class="d-flex flex-md-row flex-column mt-4">
              <div class="h-100 me-4 w-100">
                <table class="table table-striped" id="table_id">
                  <thead>
                    <tr>
                    <th ></th>
                    <th ></th>
                    <th ></th>
                      <th scope="col"># Prestamo</th>
                      <th scope="col">Cliente</th>
                      <th scope="col">Cuenta</th>
                      <th scope="col">Monto solicitado</th>
                      <th scope="col">Intereses</th>
                      <th scope="col">Cuotas</th>
                      <th scope="col">Fecha de Alta</th>
                      <th scope="col">Estado</th>
                      <th scope="col">Detalle</th>
                      <th scope="col">Acción</th>
                      <th scope="col">Acción</th>
                    </tr>
                  </thead>
                  <tbody>
                  
                  <% for(int x=0;x<prestamos.size();x++){
                	 	
                	  %>
                  
                    <tr>
                    
	                 <form action="ServletPrestamos" method="post">
                    <td> <input type="hidden"  name="idPrestamo"value="<%=prestamos.get(x).getId() %>"></td>
	                <td> <input type="hidden"  name="numCuenta" value="<%=prestamos.get(x).getNumeroCuenta() %>"></td>
	                <td> <input type="hidden"  name="montoPedido" value="<%=prestamos.get(x).getMontoPedido() %>"></td>
	                      
	                      
                      <td><span class="black-75 me-2"  ><%= prestamos.get(x).getId() %></span><i class="fa-solid fa-chart-line opacity-50"></i></td>
                      <td><i class="fa-solid fa-user opacity-50 me-2"></i><%= clientes.get(x).getApellido() +" "+ clientes.get(x).getNombre() %></td>
                       <td><span class="black-75"><%=prestamos.get(x).getNumeroCuenta() %></span></td>
                      <td><span class="black-75">$<%=prestamos.get(x).getMontoPedido() %></span></td>
                      <td><span class="black-75"><%=prestamos.get(x).getTipoTasa().getTasaInteres()/100*100 %>%</span></td>
                      <td><span class="black-75"><%=prestamos.get(x).getTipoTasa().getCantCuotas() %></span></td>
                      <td><span class="black-75"><%=prestamos.get(x).getFechaPrestamo() %></span></td>
                      
                       <% String estadoDescripcion=prestamos.get(x).getEstado().getDescripcion(); %>
                      <td><span class="badge text-white <%= estadoDescripcion.equals("Pendiente")?"bg-warning" : (estadoDescripcion.equals("Aprobado")? "bg-success" :"bg-danger") %>">
                      		<%= estadoDescripcion %>
                      </span></td>
                      
                      <td><i class="fa-solid fa-circle-info opacity-50 fs-5"></i></td>
                      <% if(prestamos.get(x).getEstado().getDescripcion().equals("Pendiente")) {%>
	                      <td><div class="bg-warning p-2 w-100 rounded-2 text-center badge">
	                      		<input type="submit" class="btn btn-alert" value="Aprobar" name="btnAprobarPrestamo" onclick="return confirm('¿Estás seguro que deseas confirmar el prestamo?')">
	                      </div></td>
	                      
	                       <td><div class="bg-warning p-2 w-100 rounded-2 text-center badge">
	                      		<input type="submit" class="btn btn-alert" value="Rechazar" name="btnRechazarPrestamo" onclick="return confirm('¿Estás seguro que deseas rechazar el prestamo?')">
	                      </div></td>
                  	  <%} else{%>
                  	  	 <td><div class="">
	                      		
	                      </div></td>
	                       <td><div class="">
	                      		
	                      </div></td>
                  	  <%} %>
	                 </form>
                  </tr>
                 
                 <% }%>
                  </tbody>
                </table>
               
              </div>
             
            </div>
           <%} %> 
            
            
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
	 
	 	<!--ESTILOS - TODO: PASAR A CSS-->
	<style>
	
	  td div,
	  nav ul li {
	    cursor: pointer;
	  }
	
	  .fs-7{
	    font-size: 12px;
	  }
	
	  td {
	    vertical-align: middle;
	  }
	  
	 
	
	</style>
</html>
