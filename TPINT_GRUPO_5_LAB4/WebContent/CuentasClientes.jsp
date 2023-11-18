<%@page import="entidad.TipoAcceso"%>
<%@page import="entidad.Cuenta"%>
<%@page import="entidad.TipoCuenta"%>
<%@page import="negocioDao.ICuentaNegocioDao"%>
<%@page import="negocioDaoImp.CuentaNegocioDaoImp"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>



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
	                  ArrayList<Cuenta> listadoCuentas = null;
               		  String [] listadoNombres = null;
      				ArrayList<TipoCuenta> tiposCuenta = null;
	                  if (request.getAttribute("listadoCuentas")!=null){
	                	  
               				listadoCuentas = (ArrayList<Cuenta>)request.getAttribute("listadoCuentas");
	                            			 
	                  }
	                  if (request.getAttribute("listadoNombres")!=null){
	  	             	    listadoNombres= new String[listadoCuentas.size()];
	  	                	listadoNombres = (String [])request.getAttribute("listadoNombres");
	                  }
	                  if (request.getAttribute("tiposCuenta")!=null){
	      				tiposCuenta= (ArrayList<TipoCuenta>)  request.getAttribute("tiposCuenta");
	                  }
	                  
	                  
	                  
	                  
               	  %>
            
      <!--CONTENIDO-->
                	  
    <div class="container-fluid justify-content-center">
        <div class="row ">

            <!-- SIDEBAR -->
           
			      <jsp:include page= "/WEB-INF/Components/menu.jsp">
			      	<jsp:param name="usuario" value="Ramón Ramirez" />
			      </jsp:include>
				
     		<!--MAIN-->
      		 
	        <div class="col-10 d-flex flex-column justify-content-between">
	          <div class="w-100 pt-2">
	            <!--TIUTLO PAGINA-->
	            <h1 class="mt-4"><i class="fas fa-address-card me-2"></i>CUENTAS CLIENTES</h1>
	          </div>
	          <div class="flex-grow-1">
	            <!--FILTRO-->
				 <div class="d-flex flex-wrap gap-2 justify-content-between align-items-center w-100 mt-2 mb-2">
			    <form action="ServletCuentasClientes" method="post">
			        <% if (request.getAttribute("mostrandoInactivos") == null) { %>
			            <input class="btn btn-outline-primary" type="submit" name="btnMostrarInactivos" value="Mostrar Inactivas">
			        <% } else if ((boolean)request.getAttribute("mostrandoInactivos")) { %>
			            <input class="btn btn-outline-primary" type="submit" name="btnMostrarInactivos" value="Mostrar solo Activas">
			        <% } else { %>
			            <input class="btn btn-outline-primary" type="submit" name="btnMostrarInactivos" value="Mostrar Inactivas">
			        <% } %>
			    </form>
			
			    <form class="d-flex gap-2" action="ServletCuentasClientes" method="get" onsubmit="return validarFechas()">
			        <select Name="TipoCuenta" class="form-select">
			            <option value="0">Todas</option>
			            <% for (TipoCuenta tc : tiposCuenta) { %>
			                <option value="<%=String.valueOf(tc.getId())%>"><%=tc.getDescripcion() %></option>
			            <% } %>
			        </select>
			        
			        <select id="importes" name="Importes" class="form-select ">			
	                    <option value="Todos los importes">Todos los importes</option>			
	                    <option value="Mayor a">Mayor a</option>			
	                    <option value="Igual a">Igual a</option>			
	                    <option value="Menor a">Menor a</option>			
	                  </select>			
	                  <input type="text" id="rangoImporte" name="rangoImporte" oninput="this.value = this.value.replace(/[^0-9]/g, '');this.value = this.value.substring(0, 10);">			
	                  			
	                  			
	                  <div class="d-flex gap-2">			
		                <span >Desde: </span>		
		                <input type="date" name="cuentaDesde" id="desdeInput">		
		              </div>		
		              <div class="d-flex gap-2">		
		                <span>Hasta: </span>		
		                <input type="date" name="cuentaHasta" id="hastaInput">		
		              </div>		
			
			        
			        
			        <input class="btn btn-outline-primary" type="submit" name="btnfiltrar" value="Filtrar">
			        <input type="submit" class="btn btn-primary btnEnviar" name="btnLimpiarFiltros" value="Limpiar filtros">
			    </form>
			    
			    <a type="submit" class="btn btn-primary btnEnviar mt-2 mt-md-0" href="/TPINT_GRUPO_5_LAB4/AltaCuentaCliente.jsp">Dar cuenta de Alta</a>
			</div>


	                	  
	    
	            <!--TABLA-->
	            <div class="d-flex  flex-column">
	 				   <table id="table_id" class="table display text-center">
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
	              	                  </tr>
	                </thead>
	                <tbody>
	               
	                	  
	                	  <% 
	                	
	                	  if (listadoCuentas !=null){
	           
	                	  for(Cuenta c : listadoCuentas ){
	                	
	                		  
	                		  %>
	                    <tr>
	                         <form action="ServletCuentasClientes" method="get">
	                      <td><i class="fa-solid fa-chart-simple opacity-50"></i><span class="black-75 me-2"><%=c.getNumeroCuenta()%></span></td>
	                      <input type="hidden" name="numerocuenta" value="<%=c.getNumeroCuenta() %>">
	                      <input type="hidden" name="idcliente" value="<%=c.getIdCliente() %>">
	                      
	                      <td><span class="black-75"><%= c.getCbu() %></span></td>
	                      <td><span class="black-75"><%=c.getTipoCuenta().getDescripcion() %></span></td>
	                      <td><span class="black-75"><%=c.getSaldo() %></span></td>
	                      <td><i class="fa-solid fa-user opacity-50 me-2"></i><%=listadoNombres[listadoCuentas.indexOf(c)] %></td>
	                      <td><span class="black-75"><%=c.getFechaCreacion().toString() %></span></td>
	                      <%if(c.isActivo()){%>
	                      <td><span class="btn btn-success btn-sm disabled">Activa</span></td>
	                         		<th>
						<input type="submit" class="btn btn-primary btn-sm" name="btnModificarCuenta" value="Modificar">
				             
				         </th>
				         	      
					                      	<th>
						<input type="submit" class="btn btn-danger btn-sm" name="btnEliminarCuenta" value="Eliminar" onclick="return confirm('¿Estás seguro de que deseas eliminar esta cuenta?');">
				        
				         </th>	
	                      <%}else{%>
	                      <td><span class="btn btn-secondary btn-sm disabled">Inactiva</span></td>
	                              		<th>
						<input type="submit" class="btn btn-primary btn-sm disabled" name="btnModificarCuenta" value="Modificar">
				             
				         </th>
				         	      
					                      	<th>
						<input type="submit" class="btn btn-danger btn-sm disabled" name="btnEliminarCuenta" value="Eliminar" onclick="return confirm('¿Estás seguro de que deseas eliminar esta cuenta?');">
				        
				         </th>	
	                      <%} %>
	                   
	                 
	                   
				         	                 
	                      </form>
	                  </tr>
	                  
	                  <%} }%>           
	              	          
	                </tbody>
	              </table>
	              
	            
	            </div>
	          <a href="PerfilBanco.jsp" class=" btn btn-primary btnEnviar  "><i class="fa-solid fa-arrow-left me-4"></i>Regresar</a>

	          
	            </div>
	         
	             </div>
	            	    
	           
			</div>
			</div>
	    <!-- FIN MAIN -->
	 	<!--FOOTER-->
	    <jsp:include page= "/WEB-INF/Components/footer.html"></jsp:include>
	 </body>
	 
	 <script>
	    function validarFechas() {
	        var desde = document.getElementById('desdeInput').value;
	        var hasta = document.getElementById('hastaInput').value;
	
	        if ((desde && !hasta) || (!desde && hasta)) {
	            alert('Por favor, complete ambas fechas o déjelas vacías.');
	            return false;
	        }
	
	        if (desde && hasta) {
	            var fechaDesde = new Date(desde);
	            var fechaHasta = new Date(hasta);
	
	            if (fechaHasta < fechaDesde) {
	                alert('La fecha "Hasta" no puede ser anterior a la fecha "Desde".');
	                return false;
	            }
	        }
	        
	        return true;
	    }
    
    
</script>
</html>
