<%@page import="java.util.ArrayList"%>
<%@page import="entidad.CuotaPrestamo"%>
<%@page import="entidad.Cuenta"%>
<%@page import="entidad.Prestamo"%>
<%@page import="java.util.List"%>
<%@page import="entidad.Cliente"%>
<%@page import="entidad.Usuario"%>
<%@page import="entidad.TipoAcceso"%>
<%@ page import="java.text.DecimalFormat" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!-- AUTENTICACION -->
<% 
	//PUEDEN INGRESAR AMBOS
	session = request.getSession();
	Usuario usuario = new Usuario();
	
	if(session.getAttribute("usuario") != null){
		usuario = (Usuario)session.getAttribute("usuario");
	}
%>

<% 
session.removeAttribute("montoSeleccionado");
session.removeAttribute("tipoTasaSeleccionada");
session.removeAttribute("interesCalculado");
session.removeAttribute("totalCalculado"); %>

<jsp:include page="/WEB-INF/Components/autenticacion/autenticacion.jsp"> 
	<jsp:param name="TipoUsuarioPagina" value="<%=usuario.getTipoAcceso()%>" />
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
          <h1><i class="fas fa-hand-holding-usd me-2"></i>DETALLE DE PRESTAMO</h1>
        </div>
        <div class="flex-grow-1">
          <!-- CONTENIDO-->
          <%
      			Cliente cliente = new Cliente();
          		Prestamo prestamo = new Prestamo();
          		Cuenta cuenta = new Cuenta();
          		List<CuotaPrestamo> listaCuota = new ArrayList<CuotaPrestamo>();
          		List<Cuenta> cuentasPago= new ArrayList<Cuenta>();
          		int indice = 0;
          		String cantCuotas="";
          		
          		if(request.getAttribute("prestamo")!=null ){
          			prestamo = (Prestamo)request.getAttribute("prestamo");
          			cuenta = (Cuenta)request.getAttribute("cuenta");
          			if(request.getAttribute("cuotas")!=null){
          				listaCuota = (List<CuotaPrestamo>)request.getAttribute("cuotas");
          				
          			}
          			
          			if(request.getAttribute("cantCuotas")!=null){
          				cantCuotas =request.getAttribute("cantCuotas").toString();
          			}
          			
          			
          		}
          		
          		if(request.getAttribute("cuentasPagoCuota")!=null){
          			cuentasPago=(List<Cuenta>)request.getAttribute("cuentasPagoCuota");
          			
          		}
          		
          		if(usuario.getTipoAcceso().compareTo(TipoAcceso.Administrador) == 0){
          			
   	          		cliente = (Cliente)request.getAttribute("cliente");
    	       		
          		}
          %>
       
			<div class="text-center">
				
			    <p>Nro. Prestamo: <%=prestamo.getId() %></p>
			    <p>Fecha de prestamo: <%=prestamo.getFechaPrestamo()%></p>
			    <p>Nro. Cuenta del Prestamo: <%=prestamo.getNumeroCuenta()%></p>
			    <p>Estado: <%=prestamo.getEstado().getDescripcion()%></p>
			    <p>Saldado: <%if(prestamo.isCancelado()){%> SI <%}else{%> NO <%}%></p>
			    
			    <%DecimalFormat decimalFormat = new DecimalFormat("#,##0.00"); 
			    	Double montoPedido=prestamo.getMontoPedido();
			    %>
			    <p>Importe solicitado: $<%=decimalFormat.format(montoPedido) %></p>
			    
			    <%Double importeConInteres = prestamo.getMontoPedido() * ((prestamo.getTipoTasa().getTasaInteres() / 100) + 1); 
			     double montoCalculado = Math.abs(prestamo.getMontoPedido() - prestamo.getMontoConIntereses());
			    %>
			    
			    
			    <p>Intereses: $<%= decimalFormat.format(montoCalculado)%></p>
			    <p>Importe con intereses: $<%=decimalFormat.format(importeConInteres)%> </p>
			    
			    <p>Cantidad de Cuotas: <%=prestamo.getTipoTasa().getCantCuotas() %></p>
			    
			    <% double importeCuota=importeConInteres/prestamo.getTipoTasa().getCantCuotas(); %>
			    <p>Importe por cuota: $<%= decimalFormat.format(importeCuota)%></p>
			    
			</div>

		<!-- Cuadro para detalles de cuotas -->
		
		    <h2>Detalles de Cuotas</h2>
		    <table id="table_id" class="table display text-center">
			    <thead>
			        <tr>
			            <th>Número de Cuota</th>
			            <th>Importe</th>
			            <th>Estado</th>
			            <th>Fecha de Pago</th>
			            <th>Nro. Cuenta de pago</th>
			            <% if(usuario.getTipoAcceso().equals(TipoAcceso.Cliente)) { %>
			                <th>Accion</th>	
			            <% } %>
			        </tr>
			    </thead>
			    <tbody>
			        <% for(CuotaPrestamo cuota : listaCuota) { %>
			            <tr>
			                <td><%=cuota.getNumeroCuota()%></td>
			                
			                <td>$<%=decimalFormat.format(cuota.getMontoCuota())%></td>
			                
			                <td><%=cuota.getEstado()%></td>
			                <!-- CUENTA DE PAGO NO DE DEPOSITO DEL PRESTAMO -->
			                <% if(cuota.getFechaPago() != null && cuentasPago != null && indice < cuentasPago.size()) { %>
			                    <td><%=cuota.getFechaPago()%></td>	
			                    <td><%=cuentasPago.get(indice).getNumeroCuenta()%></td>
			                <% } else { %>
			                    <td>No abonado</td>
			                    <td>No abonado</td>
			                <% } %>
			                <% if(usuario.getTipoAcceso().equals(TipoAcceso.Cliente)) { %>
			                <td>
			                    <% if(cuota.getFechaPago() == null) { %>
			                        
			                            <form action="ServletPrestamos" method="post">
			                                <input type="hidden" name="idPrestamo" value="<%=prestamo.getId() %>">
			                                <input type="hidden" name="idCuota" value="<%=cuota.getId() %>">
			                                <button type="submit" class="btn btn-primary btnEnviar" name="btnPagarPrestamo">Pagar</button>
			                            </form>
			                       
			                    <% } %>
			                 </td>
			                <% } %>
			            </tr>
			            <% indice++; %>
			        <% } %>
			    </tbody>
			</table>


					<div class="mt-2 mb-4">
						<a href="ServletPrestamos?Prestamos=true" class=" btn btn-primary btnEnviar  "><i class="fa-solid fa-arrow-left me-4"></i>Regresar</a>
					</div>
		
        </div>
      	</div>
       </div>
	 	<!--FOOTER-->
	    <jsp:include page= "/WEB-INF/Components/footer.html"></jsp:include>
	 </body>
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
	  
</html>

