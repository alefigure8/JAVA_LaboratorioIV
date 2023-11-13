
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
	
	HashMap<Integer, String> destinatarios = new HashMap<Integer, String>();
	
	if(request.getAttribute("destinatarios")!=null){
		destinatarios = (HashMap<Integer, String>)request.getAttribute("destinatarios");
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
			  <h1 class="mt-2">TRANSFERENCIA</h1>
			</div>
	    	<div class="row m-0">
	   			<a href="/TPINT_GRUPO_5_LAB4/ServletNuevaTransferencia?cargacbu=true" class="btn btn_main mt-4 d-flex justify-content-center align-items-center col-2">+ Nueva Ttransferencia</a>
	      	</div>
	      <div class="flex-grow-1">
	        <!--FILTRO-->
	        <div class="d-flex flex-md-row flex-column justify-content-around align-items-center w-100 gap-2 mt-4">
	          <div class="col-4 text-md-start text-center">
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
	                  <th scope="col">Numero Referencia</th>
	                  <th scope="col">Monto</th>
	                  <th scope="col">Estado</th>
	                  <th scope="col">Operaci�n</th>
	                  <th scope="col">Detalle</th>
	                </tr>
	              </thead>
	              <tbody>
	              <% for(Movimiento movimiento : listadoMovimientos) {%>
	              
	                <tr>
	                  <td><span class="black-75"><%= movimiento.getFechaMovimiento() %></span></td>
	                  <td><span class="black-75"><%= destinatarios.get(movimiento.getNumeroReferencia())%> <i class="fa-solid fa-user opacity-75 ms-2"></i></span></td>
	                  <td><span class="black-75"><%= movimiento.getNumeroReferencia() %></span></td>
	                  <td><span class="black-75"><%= NumberFormat.getCurrencyInstance(new Locale("es", "AR")).format(movimiento.getMonto()) %></span></td>
	                  <td><span class="badge <%if(movimiento.getEstado().getDescripcion().equals("Aprobado")){%> bg-success <%} else {%> bg-danger <%}%> text-white"><%= movimiento.getEstado().getDescripcion() %></span></td>
	                  <td><span class="black-75 me-2"><%= movimiento.getOperacion()%> <i class="fa-solid <%if(movimiento.getOperacion().equals(String.valueOf(Operacion.Salida))){%> fa-arrow-right text-danger <%} else {%> fa-arrow-left text-sucess <%}%>  opacity-75"></i></span></td>
	                  <td><a class="p-2 bg-secondary text-light rounded"><i class="fa-solid fa-circle-info"></i> Detalle</a></td>
	                </tr>
	                
					<%} %>
	              </tbody>
	            </table>  
	          </div>
	          
	          <!--DETALLE-->
	          <!-- 
	          
	          <div class="mt-4 border border-1 border-black border-opacity-25 rounded-1 p-2 mb-4" style="min-width: 300px;">
	            <div class="col-7 d-flex justify-content-between w-100 align-items-center mb-2">
	              <h4 class="opacity-75 m-0">Detalle</h4>
	              <span class="text-danger fw-bolder fs-6">X</span>
	            </div>
	            <div>
	              <p class="mb-0">Estado</p>
	              <p class="fs-5">Realizada</p>
	              <p class="mb-0">Cuenta d�bito</p>
	              <p class="fs-5">123456789</p>
	              <p class="mb-0">Fecha de Transferencia</p>
	              <p class="fs-5">28/10/2023</p>
	              <p class="mb-0">Monto</p>
	              <p class="fs-5">$1.950,00</p>
	              <p class="mb-0">Transferencia a</p>
	              <p class="fs-5">Cliente 2 xxxxx </p>
	              <p class="mb-0">CBU</p>
	              <p class="fs-5">123456789123</p>
	              <p class="mb-0">Concepto</p>
	              <p class="fs-5">Varios</p>
	              
	            </div>
	            
	          </div>
			 -->
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
		
		<!-- SCRIPT -->
		<script>
			function operacion(select){
				const opcion = select.value;
				window.location.href ="${pageContext.request.contextPath}/ServletListaTransferencias?listado=true&operacion=" + opcion;
			}
		</script>
	 </body>
</html>
