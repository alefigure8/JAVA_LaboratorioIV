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
    <div class="container-fluid">
        <div class="row">

            <!-- SIDEBAR -->
            <div class="col-2">
	  
	      
	      <jsp:include page= "/WEB-INF/Components/menu.jsp">
	      	<jsp:param name="usuario" value="Ram�n Ramirez" />
	      </jsp:include>
</div>
     		<!--MAIN-->
      		 
	        <div class="col-10 d-flex flex-column justify-content-between">
	          <div class="w-100 pt-2">
	            <!--TIUTLO PAGINA-->
	            <h1 class="mt-2">CUENTAS CLIENTES</h1>
	          </div>
	          <div class="flex-grow-1">
	            <!--FILTRO-->
	          <div class="d-flex flex-md-row flex-column justify-content-start align-items-center w-100 gap-2 mt-2 mb-2">
   

        <form action="ServletCuentasClientes" method="post">
            <% if (request.getAttribute("mostrandoInactivos") == null) { %>
                <input class="btn btn-outline-primary" type="submit" name="btnMostrarInactivos" value="Mostrar Inactivas">
            <% } else if ((boolean)request.getAttribute("mostrandoInactivos")) { %>
                <input class="btn btn-outline-primary" type="submit" name="btnMostrarInactivos" value="Mostrar solo Activas">
            <% } else { %>
                <input class="btn btn-outline-primary" type="submit" name="btnMostrarInactivos" value="Mostrar Inactivas">
            <% } %>
        </form>
  
       
     <form style="margin-left:400px;" action="ServletCuentasClientes" method="get" class="d-flex gap-2">
        <div class="d-flex">
            <select Name="TipoCuenta" class="form-select">
                <option value="0">Todas</option>
                <% for (TipoCuenta tc : tiposCuenta) { %>
                    <option value="<%=String.valueOf(tc.getId())%>"><%=tc.getDescripcion() %></option>
                <% } %>
            </select>
          
              </div>
              <div>
            <input class="btn btn-outline-primary" type="submit" name="btnfiltrar" value="Filtrar">
      </div>
        
        </form>
    

 
        <a type="submit" class="btn btn-primary"  href="/TPINT_GRUPO_5_LAB4/AltaCuentaCliente.jsp">Dar cuenta de Alta</a>

</div>

	                	  
	    
	            <!--TABLA-->
	            <div class="d-flex flex-md-row flex-column">
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
						<input type="submit" class="btn btn-danger btn-sm" name="btnEliminarCuenta" value="Eliminar" onclick="return confirm('�Est�s seguro de que deseas eliminar esta cuenta?');">
				        
				         </th>	
	                      <%}else{%>
	                      <td><span class="btn btn-secondary btn-sm disabled">Inactiva</span></td>
	                              		<th>
						<input type="submit" class="btn btn-primary btn-sm disabled" name="btnModificarCuenta" value="Modificar">
				             
				         </th>
				         	      
					                      	<th>
						<input type="submit" class="btn btn-danger btn-sm disabled" name="btnEliminarCuenta" value="Eliminar" onclick="return confirm('�Est�s seguro de que deseas eliminar esta cuenta?');">
				        
				         </th>	
	                      <%} %>
	                   
	                 
	                   
				         	                 
	                      </form>
	                  </tr>
	                  
	                  <%} }%>           
	              	          
	                </tbody>
	              </table>
	              
	            
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
