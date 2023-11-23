<%@page import="entidad.TipoAcceso"%>
<%@page import="entidad.Cuenta"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!-- AUTENTICACION -->
<jsp:include page="/WEB-INF/Components/autenticacion/autenticacion.jsp"> 
	<jsp:param name="TipoUsuarioPagina" value="<%=TipoAcceso.Cliente%>" />
</jsp:include>
<!-- FIN AUTENTICACION -->

<% 
	if(session.getAttribute("montoSeleccionado")!=null)
		session.removeAttribute("montoSeleccionado");

	if(session.getAttribute("tipoTasaSeleccionada")!=null)
		session.removeAttribute("tipoTasaSeleccionada");
	
	if(session.getAttribute("interesCalculado")!=null)
		session.removeAttribute("interesCalculado");
	
	if(session.getAttribute("totalCalculado")!=null)
		session.removeAttribute("totalCalculado"); 
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
	
	<% 
		ArrayList<Cuenta> cuentasCliente = null;
		
		if(request.getAttribute("cuentasCliente")!=null){
			
			cuentasCliente = (ArrayList<Cuenta>)request.getAttribute("cuentasCliente");
		};

	%>
	    <div class="row flex-grow-1 m-0">
	      <!--SIDEBAR-->
	      <jsp:include page= "/WEB-INF/Components/menu.jsp">
	      	<jsp:param name="usuario" value="Ramón Ramirez" />
	      </jsp:include>
   		<!-- FIN HEAD -->   
     	<!--CONTENT-->
	      <div class="col-lg-9 col-md-12 d-flex flex-column ">
	        <div class="w-100 pt-2">
	          <h1><i class="fas fa-wallet me-2"></i>MIS CUENTAS</h1>
	        </div>
	        
	        <div class="row justify-content-center">
			  <div class="col-sm-6 mb-3 mb-sm-0">
			    <div class="card mt-4 border-0">
					 <div class="card-body ">
						    <% if(cuentasCliente != null) { %>
						        <% for (Cuenta c : cuentasCliente) { 
						            String cuentastring = String.valueOf(c.getNumeroCuenta());
						            StringBuilder CuentaFormateada = new StringBuilder();
						            CuentaFormateada.append(cuentastring, 0, 2).append("-").append(cuentastring, 2, 9).append("/").append(cuentastring.charAt(9));
						        %>
						            <div class="card mb-4">
						                <div class="card-body">
						                    <h5 class="card-title">CUENTA NRO. <%=CuentaFormateada%></h5>
						                    <form action="ServletDetalleCuenta" method="get">
						                        <ul class="list-group border-0">
						                            <li class="list-group-item border-0 bg-transparent d-flex justify-content-between align-items-center">
						                                <h6><%=c.getTipoCuenta().getDescripcion()%> | $<%=String.format("%,.2f",c.getSaldo())%></h6>
						                                <input type="submit" class="btn btn-primary btnEnviar" name="btnVerMovimientos" value="VER MOVIMIENTOS">
						                                <input type="hidden" name="numeroCuenta" value="<%=c.getNumeroCuenta()%>">
						                            </li>
						                        </ul>
						                    </form>
						                </div>
						            </div>
						        <% } %>
						    <% } %>
						</div>

			      
			    </div>
			   		 <div class="text-center">
			    		 <a href="ServletHomeCliente?homecliente=homecliente" class="btn btn-primary btnEnviar mt-3"><i class="fa-solid fa-arrow-left me-2"></i>Regresar</a>
			  		</div>
			  </div>
			</div>

	      </div>
       </div>
       <!-- FIN MAIN -->
	 	<!--FOOTER-->
	    <jsp:include page= "/WEB-INF/Components/footer.html"></jsp:include>
	 </body>
</html>