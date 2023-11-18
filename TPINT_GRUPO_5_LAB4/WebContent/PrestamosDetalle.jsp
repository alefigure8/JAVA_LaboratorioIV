<%@page import="java.util.ArrayList"%>
<%@page import="entidad.CuotaPrestamo"%>
<%@page import="entidad.Cuenta"%>
<%@page import="entidad.Prestamo"%>
<%@page import="java.util.List"%>
<%@page import="entidad.Cliente"%>
<%@page import="entidad.Usuario"%>
<%@page import="entidad.TipoAcceso"%>
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
          <h1>DETALLE DE PRESTAMO</h1>
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
			    <p>Importe solicitado: <%=prestamo.getMontoPedido() %></p>
			    <%Double importeConInteres = prestamo.getMontoPedido() * ((prestamo.getTipoTasa().getTasaInteres() / 100) + 1); %>
			    <p>Importe con intereses: <%=importeConInteres%> </p>
			    <p>Cantidad de Cuotas: <%=prestamo.getTipoTasa().getCantCuotas() %></p>
			</div>

		<!-- Cuadro para detalles de cuotas -->
		<div>
		    <h2>Detalles de Cuotas</h2>
		    <form action="ServletPrestamos" method="post">
		    <table id="table_id" class="table display text-center">
		        <thead>
		            <tr>
		            	
		                <th>Número de Cuota</th>
		                <th>Importe</th>
		                <th>Estado</th>
		                <th>Fecha de Pago</th>
		                <th>Nro. Cuenta de pago</th>
		                <%if(usuario.getTipoAcceso().equals(TipoAcceso.Cliente)){%>
		                <!-- PAGAR SOLO CLIENTE -->
			                <th>Accion</th>	
		                <%}%>
		            </tr>
		        </thead>
		        <tbody>
		        <%
		        	for(CuotaPrestamo cuota : listaCuota){%>
			            <tr>
			        
			        		 <input type="hidden"  name="idCuota" value="<%=cuota.getId() %>">
			        		<input type="hidden"  name="idPrestamo" value="<%=prestamo.getId() %>">
			                <td><%=cuota.getNumeroCuota()%></td>
			                <td><%=cuota.getMontoCuota()%></td>
			                <td><%=cuota.getEstado()%></td>
			                <!-- CUENTA DE PAGO NO DE DEPOSITO DEL PRESTAMO -->
			               
			                 <%if(cuota.getFechaPago() != null && cuentasPago != null && indice < cuentasPago.size()){%>
				                <td><%=cuota.getFechaPago()%></td>	
				                <td><%=cuentasPago.get(indice).getNumeroCuenta()%></td>
			                <%} else {%>
				                <td>No abonado</td>
				                <td>No abonado</td>
			                <%}%>
			                <%if(usuario.getTipoAcceso().equals(TipoAcceso.Cliente)){%>
			                <!-- PAGAR SOLO CLIENTE -->
			                <%if(cuota.getFechaPago() == null){%>
				                <td>
				                	<input type="submit" class="btn btn-primary btnEnviar" value="Pagar" name="btnPagarPrestamo" >
				                </td>	
				                <%} %>
			                <%}%>
			        
			            </tr>
			            <%indice++; %>
	           	 	<%}
	           	 %>
		        </tbody>
		    </table>
		     </form>
		    
		</div>
		
		<div class="mt-2">
		<a href="ServletPrestamos?Prestamos=true" class=" btn btn-primary btnEnviar  "><i class="fa-solid fa-arrow-left me-4"></i>Regresar</a>
		</div>
		
        </div>
      	</div>
       </div>
	 	<!--FOOTER-->
	    <jsp:include page= "/WEB-INF/Components/footer.html"></jsp:include>
	 </body>
	 
	
	 
</html>

