
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<%@page import="entidad.TipoAcceso"%>

<!-- AUTENTICACION -->
<jsp:include page="/WEB-INF/Components/autenticacion/autenticacion.jsp"> 
	<jsp:param name="TipoUsuarioPagina" value="<%=TipoAcceso.Cliente%>" />
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
			  <h1 class="mt-2">Transferencia</h1>
			</div>
	    	<div>
	   			<input type="submit" class="btn btn_main mt-4" value="+ Nueva Ttransferencia">
	      	</div>
	      <div class="flex-grow-1">
	        <!--FILTRO-->
	        <div class="d-flex flex-md-row flex-column justify-content-around align-items-center w-100 gap-2 mt-4">
	          <div class="col-md-7 text-md-start text-center">
	            <h4 class="opacity-75">Historial de transferencias realizadas</h4>
	          </div>
	          <div class="col-md-5">
	            <form action="" class="d-flex justify-content-around align-items-center gap-2  flex-md-row flex-column">
	              <select name="Estados" class="form-select form-select-sm w-md-50">
	                <option value="Todos los Estados">Todos los Estados</option>
	                <option value="Realiazada">Realiazada</option>
	                <option value="Rechazada">Rechazada</option>
	                <option value="Pendiente">Pendiente</option>
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
	            <table class="table table-striped">
	              <thead>
	                <tr>
	                  <th scope="col">Fecha</th>
	                  <th scope="col">Destinatario</th>
	                  <th scope="col">Monto</th>
	                  <th scope="col">Estado</th>
	                  <th scope="col"></th>
	                </tr>
	              </thead>
	              <tbody>
	                <tr>
	                  <td class="d-flex align-items-center"><span class="black-75 me-2">28/10/2023</span><i class="fa-solid fa-user opacity-75"></i></td>
	                  <td><span class="black-75">Cliente xxxxx</span></td>
	                  <td><span class="black-75">$1.950,00</span></td>
	                  <td><span class="badge bg-danger text-white">Rechazada</span></td>
	                  <td><i class="fa-solid fa-circle-info opacity-50"></i> Detalle</td>
	                </tr>
	                <tr>
	                  <td class="d-flex align-items-center"><span class="black-75 me-2">28/10/2023</span><i class="fa-solid fa-user opacity-75"></i></td>
	                  <td><span class="black-75">Cliente xxxxx</span></td>
	                  <td><span class="black-75">$1.950,00</span></td>
	                  <td><span class="badge bg-success text-white">Realizada</span></td>
	                  <td><i class="fa-solid fa-circle-info opacity-50"></i> Detalle</td>
	                </tr>
	                <tr>
	                  <td class="d-flex align-items-center"><span class="black-75 me-2">28/10/2023</span><i class="fa-solid fa-user opacity-75"></i></td>
	                  <td><span class="black-75">Cliente xxxxx</span></td>
	                  <td><span class="black-75">$1.950,00</span></td>
	                  <td><span class="badge bg-success text-white">Realizada</span></td>
	                  <td><i class="fa-solid fa-circle-info opacity-50"></i> Detalle</td>
	                </tr>
	                <tr>
	                  <td class="d-flex align-items-center"><span class="black-75 me-2">28/10/2023</span><i class="fa-solid fa-user opacity-75"></i></td>
	                  <td><span class="black-75">Cliente xxxxx</span></td>
	                  <td><span class="black-75">$1.950,00</span></td>
	                  <td><span class="badge bg-danger text-white">Rechazada</span></td>
	                  <td><i class="fa-solid fa-circle-info opacity-50"></i> Detalle</td>
	                </tr>
	                <tr>
	                  <td class="d-flex align-items-center"><span class="black-75 me-2">28/10/2023</span><i class="fa-solid fa-user opacity-75"></i></td>
	                  <td><span class="black-75">Cliente xxxxx</span></td>
	                  <td><span class="black-75">$1.950,00</span></td>
	                  <td><span class="badge bg-success text-white">Realizada</span></td>
	                  <td><i class="fa-solid fa-circle-info opacity-50"></i> Detalle</td>
	                </tr>
	              </tbody>
	            </table>
	            <!--PAGINACION-->
	            <nav class="w-100 d-flex justify-content-center">
	              <ul class="pagination">
	                <li class="page-item">
	                  <a class="page-link" href="#" aria-label="Previous">
	                    <span aria-hidden="true">&laquo;</span>
	                  </a>
	                </li>
	                <li class="page-item"><a class="page-link bg-primary text-light" href="#">1</a></li>
	                <li class="page-item"><a class="page-link" href="#">2</a></li>
	                <li class="page-item">
	                  <a class="page-link" href="#" aria-label="Next">
	                    <span aria-hidden="true">&raquo;</span>
	                  </a>
	                </li>
	              </ul>
	            </nav>
	          </div>
	          <!--DETALLE-->
	          <div class="mt-4 border border-1 border-black border-opacity-25 rounded-1 p-2 mb-4" style="min-width: 300px;">
	            <div class="col-7 d-flex justify-content-between w-100 align-items-center mb-2">
	              <h4 class="opacity-75 m-0">Detalle</h4>
	              <span class="text-danger fw-bolder fs-6">X</span>
	            </div>
	            <div>
	              <p class="mb-0">Estado</p>
	              <p class="fs-5">Realizada</p>
	              <p class="mb-0">Cuenta débito</p>
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
	        </div>
	      
	      </div>
	    </div>
	    
	 </div>
	 	<!--FOOTER-->
	    <jsp:include page= "/WEB-INF/Components/footer.html"></jsp:include>
	 </body>
</html>
