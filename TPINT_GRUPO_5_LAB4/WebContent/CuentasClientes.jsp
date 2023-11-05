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
	        <div class="col-10 d-flex flex-column justify-content-between">
	          <div class="w-100 pt-2">
	            <!--TIUTLO PAGINA-->
	            <h1 class="mt-2">CUENTAS CLIENTES</h1>
	          </div>
	          <div class="flex-grow-1">
	            <!--FILTRO-->
	            <div class="d-flex flex-md-row flex-column justify-content-around align-items-center w-100 gap-2 mt-4">
	              <div class="col-md-7 text-md-start text-center">
	                <h4 class="opacity-75">Historial de las cuentas de clientes</h4>
	              </div>
	              <div class="col-md-5">
	                <form action="" class="d-flex justify-content-around align-items-center gap-2  flex-md-row flex-column">
	                  <div class="d-flex gap-2">
	                    <input type="text" name="cliente" placeholder="Buscar">
	                  </div>
	                  <select name="Estados" class="form-select form-select-sm w-md-50">
	                    <option value="Todos los Estados">Todos los Campos</option>
	                    <option value="Cuente">Cuenta</option>
	                    <option value="CBU">CBU</option>
	                    <option value="DNI">DNI</option>
	                    <option value="CUIL">CUIL</option>
	                    <option value="Apellido">Apellido</option>
	                  </select>
	                  <select name="Estados" class="form-select form-select-sm w-md-50">
	                    <option value="Todos los Estados">Todos los Estados</option>
	                    <option value="Realiazada">Activos</option>
	                    <option value="Rechazada">No Activos</option>
	                    <option value="Rechazada">Pendientes</option>
	                  </select>
	                  <input type="submit" class="btn btn_main" value="Buscar">
	                </form>
	              </div>
	            </div>
	
	            <!--TABLA-->
	            <div class="d-flex flex-md-row flex-column">
	              <table class="table table-striped h-100 me-4">
	                <thead>
	                  <tr>
	                    <th scope="col">Cuenta</th>
	                    <th scope="col">CBU</th>
	                    <th scope="col">Tipo de Cuenta</th>
	                    <th scope="col">Saldo</th>
	                    <th scope="col">Cliente</th>
	                    <th scope="col">Fecha de Alta</th>
	                    <th scope="col">Estado</th>
	                    <th scope="col">Modificar</th>
	                    <th scope="col">Eliminar</th>
	                    <th scope="col">Acción</th>
	                  </tr>
	                </thead>
	                <tbody>
	                  <tr>
	                      <td><span class="black-75 me-2">1234</span><i class="fa-solid fa-chart-simple opacity-50"></i></td>
	                      <td><span class="black-75">1234567891122</span></td>
	                      <td><span class="black-75">Cuenta Corriente</span></td>
	                      <td><span class="black-75">$12.456,52</span></td>
	                      <td><i class="fa-solid fa-user opacity-50 me-2"></i>Kramer Cosmo</td>
	                      <td><span class="black-75">12/09/2011</span></td>
	                      <td><span class="badge bg-success text-white">Activo</span></td>
	                      <td><div class="bg-success p-2 w-50 rounded-2 text-center badge"><i class="fa-solid fa-pen text-light fs-7"></i></div></td>
	                      <td><div class="bg-danger p-2 w-50 rounded-2 text-center badge"><i class="fa-solid fa-trash text-light fs-7"></i></div></td>
	                      <td><div class="bg-warning p-2 w-100 rounded-2 text-center badge d-none"><span class="text-black">Aprobar</span></div></td>
	                  </tr>
	                  <tr>
	                      <td><span class="black-75 me-2">5678</span><i class="fa-solid fa-chart-simple opacity-50"></i></td>
	                      <td><span class="black-75">9876543210001</span></td>
	                      <td><span class="black-75">Cuenta Corriente</span></td>
	                      <td><span class="black-75">$8,765.43</span></td>
	                      <td><i class="fa-solid fa-user opacity-50 me-2"></i>Jerry Seinfeld</td>
	                      <td><span class="black-75">03/05/2010</span></td>
	                      <td><span class="badge bg-success text-white">Activo</span></td>
	                      <td><div class="bg-success p-2 w-50 rounded-2 text-center badge"><i class="fa-solid fa-pen text-light fs-7"></i></div></td>
	                      <td><div class="bg-danger p-2 w-50 rounded-2 text-center badge"><i class="fa-solid fa-trash text-light fs-7"></i></div></td>
	                      <td><div class="bg-warning p-2 w-100 rounded-2 text-center badge d-none"><span class="text-black">Aprobar</span></div></td>
	                  </tr>
	                  <tr>
	                      <td><span class="black-75 me-2">9876</span><i class="fa-solid fa-chart-simple opacity-50"></i></td>
	                      <td><span class="black-75">5555555555555</span></td>
	                      <td><span class="black-75">Cuenta Corriente</span></td>
	                      <td><span class="black-75">$56,789.00</span></td>
	                      <td><i class="fa-solid fa-user opacity-50 me-2"></i>Elaine Benes</td>
	                      <td><span class="black-75">25/11/2012</span></td>
	                      <td><span class="badge bg-danger text-white">No Activo</span></td>
	                      <td><div class="bg-success p-2 w-50 rounded-2 text-center badge"><i class="fa-solid fa-pen text-light fs-7"></i></div></td>
	                      <td><div class="bg-danger p-2 w-50 rounded-2 text-center badge"><i class="fa-solid fa-trash text-light fs-7"></i></div></td>
	                      <td><div class="bg-warning p-2 w-100 rounded-2 text-center badge d-none"><span class="text-black">Aprobar</span></div></td>
	                  </tr>
	                  <tr>
	                      <td><span class="black-75 me-2">2468</span><i class="fa-solid fa-chart-simple opacity-50"></i></td>
	                      <td><span class="black-75">8888888888888</span></td>
	                      <td><span class="black-75">Caja de Ahorros</span></td>
	                      <td><span class="black-75">$2,345.67</span></td>
	                      <td><i class="fa-solid fa-user opacity-50 me-2"></i>George Costanza</td>
	                      <td><span class="black-75">18/06/2013</span></td>
	                      <td><span class="badge bg-success text-white">Activo</span></td>
	                      <td><div class="bg-success p-2 w-50 rounded-2 text-center badge"><i class="fa-solid fa-pen text-light fs-7"></i></div></td>
	                      <td><div class="bg-danger p-2 w-50 rounded-2 text-center badge"><i class="fa-solid fa-trash text-light fs-7"></i></div></td>
	                      <td><div class="bg-warning p-2 w-100 rounded-2 text-center badge d-none"><span class="text-black">Aprobar</span></div></td>
	                  </tr>
	                  <tr>
	                      <td><span class="black-75 me-2">1357</span><i class="fa-solid fa-chart-simple opacity-50"></i></td>
	                      <td><span class="black-75">7777777777777</span></td>
	                      <td><span class="black-75">Caja de Ahorros</span></td>
	                      <td><span class="black-75">$10.000,00</span></td>
	                      <td><i class="fa-solid fa-user opacity-50 me-2"></i>Newman</td>
	                      <td><span class="black-75">10/02/2008</span></td>
	                      <td><span class="badge bg-warning text-white">Pendiente</span></td>
	                      <td><div class="bg-success p-2 w-50 rounded-2 text-center badge"><i class="fa-solid fa-pen text-light fs-7"></i></div></td>
	                      <td><div class="bg-danger p-2 w-50 rounded-2 text-center badge"><i class="fa-solid fa-trash text-light fs-7"></i></div></td>
	                      <td><div class="bg-warning p-2 w-100 rounded-2 text-center badge"><span class="text-black">Aprobar</span></div></td>
	                  </tr>
	                  <tr>
	                      <td><span class="black-75 me-2">3690</span><i class="fa-solid fa-chart-simple opacity-50"></i></td>
	                      <td><span class="black-75">5555555555444</span></td>
	                      <td><span class="black-75">Caja de Ahorros</span></td>
	                      <td><span class="black-75">$5,432.10</span></td>
	                      <td><i class="fa-solid fa-user opacity-50 me-2"></i>Susan Ross</td>
	                      <td><span class="black-75">22/12/2014</span></td>
	                      <td><span class="badge bg-success text-white">Activo</span></td>
	                      <td><div class="bg-success p-2 w-50 rounded-2 text-center badge"><i class="fa-solid fa-pen text-light fs-7"></i></div></td>
	                      <td><div class="bg-danger p-2 w-50 rounded-2 text-center badge"><i class="fa-solid fa-trash text-light fs-7"></i></div></td>
	                      <td><div class="bg-warning p-2 w-100 rounded-2 text-center badge d-none"><span class="text-black">Aprobar</span></div></td>
	                  </tr>
	                  <tr>
	                      <td><span class="black-75 me-2">9870</span><i class="fa-solid fa-chart-simple opacity-50"></i></td>
	                      <td><span class="black-75">1234432156789</span></td>
	                      <td><span class="black-75">Caja de Ahorros</span></td>
	                      <td><span class="black-75">$4,321.98</span></td>
	                      <td><i class="fa-solid fa-user opacity-50 me-2"></i>Estelle Costanza</td>
	                      <td><span class="black-75">05/03/2015</span></td>
	                      <td><span class="badge bg-danger text-white">No Activo</span></td>
	                      <td><div class="bg-success p-2 w-50 rounded-2 text-center badge"><i class="fa-solid fa-pen text-light fs-7"></i></div></td>
	                      <td><div class="bg-danger p-2 w-50 rounded-2 text-center badge"><i class="fa-solid fa-trash text-light fs-7"></i></div></td>
	                      <td><div class="bg-warning p-2 w-100 rounded-2 text-center badge d-none"><span class="text-black">Aprobar</span></div></td>
	                  </tr>
	                  <tr>
	                      <td><span class="black-75 me-2">7531</span><i class="fa-solid fa-chart-simple opacity-50"></i></td>
	                      <td><span class="black-75">9999999999999</span></td>
	                      <td><span class="black-75">Cuenta Corriente</span></td>
	                      <td><span class="black-75">$10,000.00</span></td>
	                      <td><i class="fa-solid fa-user opacity-50 me-2"></i>J. Peterman</td>
	                      <td><span class="black-75">15/08/2009</span></td>
	                      <td><span class="badge bg-warning text-white">Pendiente</span></td>
	                      <td><div class="bg-success p-2 w-50 rounded-2 text-center badge"><i class="fa-solid fa-pen text-light fs-7"></i></div></td>
	                      <td><div class="bg-danger p-2 w-50 rounded-2 text-center badge"><i class="fa-solid fa-trash text-light fs-7"></i></div></td>
	                      <td><div class="bg-warning p-2 w-100 rounded-2 text-center badge"><span class="text-black">Aprobar</span></div></td>
	                  </tr>
	                  <tr>
	                </tbody>
	              </table>
	            </div>

	            <!--PAGINATION-->
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
	          </div>
	       </div>
	    <!-- FIN MAIN -->
	 	<!--FOOTER-->
	    <jsp:include page= "/WEB-INF/Components/footer.html"></jsp:include>
	 </body>
</html>
