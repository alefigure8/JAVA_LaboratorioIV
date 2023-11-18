<%@page import="entidad.Operacion"%>
<%@page import="entidad.Destinatario"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.HashMap"%>
<%@page import="entidad.Movimiento"%>
<%@page import="entidad.TipoAcceso"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!-- AUTENTICACION -->
<jsp:include page="/WEB-INF/Components/autenticacion/autenticacion.jsp"> 
	<jsp:param name="TipoUsuarioPagina" value="<%=TipoAcceso.Cliente%>" />
</jsp:include>
<!-- FIN AUTENTICACION -->

<%
	Movimiento transferencia = new Movimiento();
	HashMap<Integer, Destinatario> destinatarios = new HashMap<Integer, Destinatario>();
	Destinatario destinatario = null;
	if(request.getAttribute("transferencia") != null && request.getAttribute("destinatario") != null){
		transferencia = (Movimiento)request.getAttribute("transferencia");
		destinatarios = (HashMap<Integer, Destinatario>)request.getAttribute("destinatario");
		destinatario = destinatarios.get(transferencia.getNumeroReferencia());
	}
%>

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
      <div class="col-lg-10 col-md-12 mx-2" >
        <div class="pt-2 d-flex gap-4 justify-content-center row">

          <h1 class="mb-2">DETALLE DE TRANSFERENCIA</h1>

          <div class="d-flex flex-column gap-4">
            <div class="border border-1 rounded p-2 col-11 col-md-3">
              <p class="mb-0">Estado</p>
              <p class="fs-5"><%= transferencia.getEstado().getDescripcion() %></p>
              <p class="mb-0">Estado</p>
              <p class="fs-5"><%= transferencia.getOperacion() %></p>
              <% if(transferencia.getOperacion().equals(String.valueOf(Operacion.Salida))){ %>
              <p class="mb-0">Cuenta débito</p>
              <p class="fs-5"><%=transferencia.getCuenta().getTipoCuenta().getDescripcion() + ": N° " + transferencia.getCuenta().getNumeroCuenta() %></p>
              <%} else { %>
              <p class="mb-0">Cuenta crédito</p>
              	<% if (destinatario != null) { %>
					<p class="fs-5"><%= destinatario.getNumeroCuenta() %></p>
					<% } else { %>
					<p class="fs-5"><%=transferencia.getCuenta().getTipoCuenta().getDescripcion() + ": N° " + transferencia.getCuenta().getNumeroCuenta() %></p>
				<% } %>
              <%} %>
              <p class="mb-0">Fecha de Transferencia</p>
              <p class="fs-5"><%= transferencia.getFechaMovimiento() %></p>
              <p class="mb-0">Monto</p>
              <p class="fs-5"><%= NumberFormat.getCurrencyInstance(new Locale("es", "AR")).format(transferencia.getMonto()) %></p>
              <p class="mb-0">Transferencia a</p>
              <p class="fs-5">
             	 <% if (destinatario != null) { %>
					<%= destinatario.getNombre() + " " + destinatario.getApellido() %>
					<% } else { %>
					<!-- Manejar el caso donde destinatario es nulo -->
					Cuenta Propia
				<% } %></p>
              <p class="mb-0">CBU</p>
              <p class="fs-5">
				 <% if (destinatario != null) { %>
					<%= destinatario.getCbu() %>
				<% } else { %>
					<!-- Manejar el caso donde destinatario es nulo -->
					<%=transferencia.getCuenta().getCbu() %>
				<% } %></p>
              <p class="mb-0">Concepto</p>
              <p class="fs-5"><%= transferencia.getConcepto() %></p>
            </div>
  
            <a href="/TPINT_GRUPO_5_LAB4/ServletListaTransferencias?listado=true&todos=true" class="p-2 rounded bg-main text-white text-decoration-none col-4 col-md-1 mb-4"><i class="fa-solid fa-arrow-left me-4"></i>Regresar</a>
          </div>

        </div>

      </div>
	</div>
      <!--FIN MAIN-->
	 	<!--FOOTER-->
	    <jsp:include page= "/WEB-INF/Components/footer.html"></jsp:include>
	 </body>
</html>