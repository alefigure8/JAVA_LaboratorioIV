
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
	      	<jsp:param name="usuario" value="Ramón Ramirez" />
	      </jsp:include>
      <!--CONTENIDO-->
	    <div class="col-10 d-flex flex-column justify-content-between">
	    	<!--TITULO-->
			<div class="w-100 pt-2">
			  <!--TIUTLO PAGINA-->
			  <h1 class="mt-2">TRANSFERENCIA</h1>
			</div>
			<div class="text-md-start">
	            <h4 class="opacity-75">Nueva Transferencia</h4>
          	</div>
	    	<div class="m-0 d-flex col-12 col-md-4">
   				<a href="/TPINT_GRUPO_5_LAB4/ServletNuevaTransferencia?cargacbu=true&otraCuenta=true" class="btn btn_main mt-4 d-flex justify-content-center align-items-center p-4 m-1">Otra Cuenta</a>
      			<a href="/TPINT_GRUPO_5_LAB4/ServletNuevaTransferencia?cargacbu=true&cuentaPropia=true" class="btn btn_main mt-4 d-flex justify-content-center align-items-center p-4 m-1">Cuenta Propia</a>
	      	</div>
	      <div class="flex-grow-1">
	        <!--FILTRO-->
	        <div class="d-flex flex-md-row flex-column justify-content-around align-items-center w-100 gap-2 mt-4">
	          <div class="col-4 text-md-start">
	            <h4 class="opacity-75">Historial de transferencias realizadas</h4>
	          </div>
	          <div class="col-md-8">
	            <form action="" class="d-flex justify-content-around align-items-center gap-2  flex-md-row flex-column">
	              <select name="Estados" class="form-select form-select-sm w-md-50">
	                <option value="Todos los Estados">Todos los Estados</option>
	                <option value="Realiazada">Realiazada</option>
	                <option value="Rechazada">Rechazada</option>
	              </select>
	              <select name="Operacion" class="form-select form-select-sm w-md-50" onChange="operacion(this)">
	                <option value="Todos las operaciones">Todos las Operaciones</option>
	                <option value="Entrada">Entrada</option>
	                <option value="Salida">Salida</option>
	              </select>
	              <div class="d-flex gap-2">
	                <span >Desde: </span>
	                <input type="date" name="transferenicaDesde" value="28/10/2023">
	              </div>
	              <div class="d-flex gap-2">
	                <span>Hasta: </span>
	                <input type="date" name="transferenicaHasta">
	              </div>
	              <input type="submit" class="btn btn_main" value="Buscar">
	            </form>
	          </div>
	        </div>
	        <!--TABLA-->
	        <div class="d-flex flex-md-row flex-column">
	          <div class="h-100 me-4 w-100">
	            <table id="table_id" class="table display text-center">
	              <thead>
	                <tr>
	                  <th scope="col">Fecha</th>
	                  <th scope="col">Destinatario</th>
	                  <th scope="col">Monto</th>
	                  <th scope="col">Estado</th>
	                  <th scope="col">Operación</th>
	                  <th scope="col">Detalle</th>
	                </tr>
	              </thead>
	              <tbody>
	              <% for(Movimiento movimiento : listadoMovimientos) {
	            	  Destinatario destinatario = new Destinatario();
	            	  destinatario = (Destinatario)destinatarios.get(movimiento.getNumeroReferencia());
	              %>
	              
	                <tr>
	                  <td><span class="black-75"><%= movimiento.getFechaMovimiento() %></span></td>
	                    <td>
					        <span class="black-75">
					            <% if (destinatario != null) { %>
					                <%= destinatario.getNombre() + " " + destinatario.getApellido() %>
					                <i class="fa-solid fa-user opacity-75 ms-2"></i>
					            <% } else { %>
					                <!-- Manejar el caso donde destinatario es nulo -->
					                No Disponible
					            <% } %>
					        </span>
					    </td>
	                  <td><span class="black-75"><%= NumberFormat.getCurrencyInstance(new Locale("es", "AR")).format(movimiento.getMonto()) %></span></td>
	                  <td><span class="badge <%if(movimiento.getEstado().getDescripcion().equals("Aprobado")){%> bg-success <%} else {%> bg-danger <%}%> text-white"><%= movimiento.getEstado().getDescripcion() %></span></td>
	                  <td><span class="black-75 me-4"><%= movimiento.getOperacion()%> <i class="fa-solid <%if(movimiento.getOperacion().equals(String.valueOf(Operacion.Salida))){%> fa-arrow-right text-danger <%} else {%> fa-arrow-left text-success <%}%>  opacity-75"></i></span></td>
	                  <td><a class="p-2 bg-secondary text-light rounded" href="ServletDetalleTransferencia?numeroReferencia=<%= movimiento.getNumeroReferencia()%>"><i class="fa-solid fa-circle-info"></i> Detalle</a></td>
	                </tr>
	                
					<%} %>
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
		</script>
	 </body>
</html>
