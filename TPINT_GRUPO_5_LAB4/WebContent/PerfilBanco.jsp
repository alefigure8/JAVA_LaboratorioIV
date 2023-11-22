<%@page import="entidad.TipoAcceso"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

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
	      <div class="col-lg-9 col-md-12 d-flex flex-column justify-content-between">
	        <div class="w-100 pt-2">
	          <h1>MENU BANCO</h1>
	        </div>
	        <div class="flex-grow-1">
	          <!-- CONTENIDO-->
	          
	          <div class="p-4 col-md-12 text-center">
					    <div class="row justify-content-center">
					        <div class="col-md-4">
					            <div class="row">
					                <div class="col-md-12">
					                    <a href="AltaCuentaCliente.jsp" class="btn btn-primary btnEnviar col-12 p-3 m-1">
					                       <i class="fas fa-money-check fa-2x me-2"></i>ALTA DE CUENTA
					                    </a>
					                </div>
					                   <div class="col-md-12">
					                    <a href="/TPINT_GRUPO_5_LAB4/ServletCuentasClientes?Cuentas=CuentasClientes" class="btn btn-primary btnEnviar col-12 p-3 m-1">
					                        <i class="fas fa-list fa-2x me-2"></i>LISTADO DE CUENTAS
					                    </a>
					                </div>	
					                 <div class="col-md-12">
					                    <a href="ServletPrestamos?Prestamos=true" class="btn btn-primary btnEnviar col-12 p-3 m-1">
					                        <i class="fas fa-hand-holding-usd fa-2x me-2"></i>PRESTAMOS
					                    </a>
					                </div>
					                 <div class="col-md-12">
					                    <a href="ServletEstadisticasBancos" class="btn btn-primary btnEnviar col-12 p-3 m-1">
					                       <i class="fas fa-chart-line fa-2x me-2"></i>ESTADISTICAS BANCO
					                    </a>
					                </div>
					               
					            </div>
					        </div>
					        <div class="col-md-4">
					            <div class="row">
					             <div class="col-md-12">
					                    <a href="ServletAltaCliente?AltaCliente=true" class="btn btn-primary btnEnviar col-12 p-3 m-1">
					                       <i class="fas fa-user-plus fa-2x me-2"></i>ALTA DE CLIENTE
					                    </a>
					                </div>
					                <div class="col-md-12">
					                    <a href="ServletListarClientes?obtener=true&filtro=Activos" class="btn btn-primary btnEnviar col-12 p-3 m-1">
					                        <i class="fas fa-users fa-2x me-2"></i>LISTADO DE CLIENTES
					                    </a>
					                </div>					                
					              
					                 <div class="col-md-12">
					                    <a href="ServletEstadisticasPrestamos?Estadisticas=Prestamos" class="btn btn-primary btnEnviar col-12 p-3 m-1">
					                       <i class="fas fa-chart-line fa-2x me-2"></i>ESTADISTICAS PRESTAMOS
					                    </a>
					                </div>
					                
					                
					               
					            </div>
					        </div>
					    </div>
					</div>
	          
	        </div>
	      </div>
       </div>
       <!--FIN MAIN-->
	 	<!--FOOTER-->
	    <jsp:include page= "/WEB-INF/Components/footer.html"></jsp:include>
	 </body>
</html>
