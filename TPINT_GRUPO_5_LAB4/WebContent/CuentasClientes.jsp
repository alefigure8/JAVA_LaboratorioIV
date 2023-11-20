<%@page import="entidad.Usuario"%>
<%@page import="entidad.TipoAcceso"%>
<%@page import="entidad.Cuenta"%>
<%@page import="entidad.TipoCuenta"%>
<%@page import="negocioDao.ICuentaNegocioDao"%>
<%@page import="negocioDaoImp.CuentaNegocioDaoImp"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%
	Usuario usuario = new Usuario();

	if(session.getAttribute("usuario") != null ){
		usuario = (Usuario)session.getAttribute("usuario");
	}
%>

<!-- AUTENTICACION -->
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
	
	<body class="d-flex flex-column m-0">
 <% 
        ArrayList<Cuenta> listadoCuentas = new ArrayList<>();
     	String [] listadoNombres = null;
		ArrayList<TipoCuenta> tiposCuenta = null;
		int [] listadoDni=null;
		
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
         
         if(request.getAttribute("listadoDni")!=null){
        	 listadoDni= new int[listadoCuentas.size()];
        	 listadoDni=(int[])request.getAttribute("listadoDni");
         }
         
 %>
            
      <!--CONTENIDO-->
                	  
    <div class="row justify-content-center flex-grow-1">
        <div class="row">

            <!-- SIDEBAR -->
           
			      <jsp:include page= "/WEB-INF/Components/menu.jsp">
			      	<jsp:param name="usuario" value="Ramón Ramirez" />
			      </jsp:include>
				
     		<!--MAIN-->
      		 
	        <div class="col-9 d-flex flex-column justify-content-between">
	          <div class="w-100 pt-2">
	            <!--TIUTLO PAGINA-->
	            <h1 class="mt-4"><i class="fas fa-address-card me-2"></i>CUENTAS CLIENTES</h1>
	          </div>
	          <div class="flex-grow-1">
	            <!--FILTRO-->
				 <div class="d-flex flex-wrap gap-2 justify-content-between align-items-center w-100 mt-2 mb-2">
	
				   	

			
			    <form class="d-flex gap-2 align-items-end" action="ServletCuentasClientes" method="get" onsubmit="return validarFechas()">
			    
			    <div>
			    <label for="estadoCuenta" style="font-size: 11px;"> Estado de Cuentas: </label>
			        <select id="estadoCuenta" name="EstadoCuenta" class="form-select align-items-end">	
			     		 
	                    <option value="Todas" selected="selected">Todas</option>			
	                 	 <option value="Solo Activas">Solo Activas</option>
	                    <option value="Solo Inactivas">Solo Inactivas</option>			
	                    			
	                  </select>
	                  
	                  </div>    
			    <div style="max-width: 110px; min-width: 110px;">
			          <label for="tipoCuenta" style="font-size: 11px;"> Tipos de Cuenta: </label>
			        <select id="tipoCuenta" Name="TipoCuenta" class="form-select align-items-end">
			            <option value="0">Todas</option>
			            <%if(tiposCuenta != null){ %>
				            <% for (TipoCuenta tc : tiposCuenta) { %>
				                <option value="<%=String.valueOf(tc.getId())%>"><%=tc.getDescripcion() %></option>
				            <% }
			            }%>
			        </select>
			        </div>
			        <div>
			        <label for="importes" style="font-size: 11px;"> Rangos de Importe:</label>
			        <select id="importes" name="Importes" class="form-select align-items-end">			
	                    <option value="Todos los importes" >Todos</option>			
	                    <option value="Mayor a">Mayor a</option>			
	                    <option value="Igual a">Igual a</option>			
	                    <option value="Menor a">Menor a</option>			
	                  </select>		
	                         </div>	
	                          <div>
	                           <label for="rangoImporte" style="font-size: 11px;"> $:</label>
	                  <input style="max-width: 80px; min-width: 80px;"type="text" id="rangoImporte" name="rangoImporte" oninput="this.value = this.value.replace(/[^0-9]/g, '');this.value = this.value.substring(0, 10);">			
	           		</div>
	                  			
	                  <div style="max-width: 110px; min-width: 110px;">			
		            <label for="desdeInput" style="font-size: 11px;"> Desde:</label>    		
		                <input type="date" name="cuentaDesde" id="desdeInput" style="max-width: 110px; min-width: 110px;" >		
		              </div>		
		              <div  style="max-width: 110px; min-width: 110px;">		
		                <label for="hastaInput" style="font-size: 11px;"> Hasta:</label>	
		                <input type="date" name="cuentaHasta" id="hastaInput"  style="max-width: 110px; min-width: 110px;" >		
		              </div>		
			
			        
			        <input class="btn btn-outline-primary" type="submit" name="btnfiltrar" value="Filtrar">
			        <input  type="submit" class="btn btn-primary btnEnviar" name="btnLimpiarFiltros" value="Ver Todo">
			          <a type="submit" class="btn btn-primary btnEnviar" href="/TPINT_GRUPO_5_LAB4/AltaCuentaCliente.jsp">Alta Cuenta</a>
			    </form>
			    
			</div>

	            <!--TABLA-->
	            <div class="d-flex  flex-column">
                    <form action="ServletCuentasClientes" method="get">
	 				   <table id="table_id" class="table display text-center">
		                <thead>
		                  <tr>
		                    <th scope="col">Cuenta</th>
		                    <th scope="col">CBU</th>
		                    <th scope="col">Tipo de Cuenta</th>
		                    <th scope="col">Saldo</th>
		                    <th scope="col">Cliente</th>
		                    <th scope="col">Dni</th>
		                    <th scope="col">Fecha de Alta</th>
		                    <th scope="col">Estado</th>
		                    <th scope="col">Modificar</th>
		                    <th scope="col">Eliminar</th>
		              	                  </tr>
		                </thead>
		                <tbody>
	               
	                	  <% 
	                	  if (listadoCuentas != null){
	           
	                	 	 for(Cuenta c : listadoCuentas ){
	                	
	                		%>
		                    <tr>
		                      <td><i class="fa-solid fa-chart-simple opacity-50"></i><span class="black-75 me-2"><%=c.getNumeroCuenta()%></span></td>
		                      <input type="hidden" name="numerocuenta" value="<%=c.getNumeroCuenta() %>">
		                      <input type="hidden" name="idcliente" value="<%=c.getIdCliente() %>">
		                      
		                      <td><span class="black-75"><%= c.getCbu() %></span></td>
		                      <td><span class="black-75"><%=c.getTipoCuenta().getDescripcion() %></span></td>
		                      <td><span class="black-75"><%=c.getSaldo() %></span></td>
		                      <% if(listadoNombres != null) {%>
		                      	<td><i class="fa-solid fa-user opacity-50 me-2"></i><%=listadoNombres[listadoCuentas.indexOf(c)] %></td>
		                      <%}%>
		                      
		                       <% if(listadoDni != null) {%>			
		                      	<td><i class="fa-solid fa-user opacity-50 me-2"></i><%=listadoDni[listadoCuentas.indexOf(c)] %></td>
		                      <%}%>	
		                      
		                      
		                      
		                      
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
	                 
		                  </tr>
	                  <%} 
              	 	 }%>                    
	                </tbody>
	              </table>
                 </form>
                 <div class="d-grid gap-2 col-6 mx-auto mt-5">
  			    <a type="submit" class="btn btn-primary btn-lg btnEnviar mt-2 mt-md-0" href="/TPINT_GRUPO_5_LAB4/AltaCuentaCliente.jsp"> Alta Cuenta <i class="fa-solid fa-plus"> </i></a>
			    </div>
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
