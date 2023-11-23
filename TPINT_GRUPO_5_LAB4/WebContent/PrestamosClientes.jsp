<%@page import="entidad.Estado"%>
<%@page import="entidad.Usuario"%>
<%@page import="entidad.TipoAcceso"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>
<%@page import="entidad.Cliente" %>
<%@page import="entidad.Prestamo" %>
<%@page import="entidad.Estado" %>
<%@page import="java.util.ArrayList"%>
<%@ page import="java.text.DecimalFormat" %>


<!-- AUTENTICACION -->
<% 
	//PUEDEN INGRESAR AMBOS
	session = request.getSession();
	Usuario usuario = new Usuario();
	
	if(session.getAttribute("usuario") != null){
		usuario = (Usuario)session.getAttribute("usuario");
	}
	
%>



<jsp:include page="/WEB-INF/Components/autenticacion/autenticacion.jsp"> 
	<jsp:param name="TipoUsuarioPagina" value="<%=usuario.getTipoAcceso()%>" />
</jsp:include>

<!-- FIN AUTENTICACION -->

<% 

	String estado = null;
	String importe = null;
	String monto = null;
	String desde = null;
	String hasta = null;
	if(request.getAttribute("estadoSeleccionado")!=null){
		estado = (String) request.getAttribute("estadoSeleccionado");
	}
	if(request.getAttribute("importeSeleccionado")!=null){
		importe = (String) request.getAttribute("importeSeleccionado");;
	}
	if(request.getAttribute("montoImporte")!=null){
		monto = (String) request.getAttribute("montoImporte");
	}
	if(request.getAttribute("fechaDesde")!=null){
		desde = (String) request.getAttribute("fechaDesde");
	}
	if(request.getAttribute("fechaHasta")!=null){
		hasta = (String) request.getAttribute("fechaHasta");
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
	
	
	
	<!-- COMIENZA ADMIN -->
	    <div class="row flex-grow-1 m-0">
	      <!--SIDEBAR-->
	      <jsp:include page= "/WEB-INF/Components/menu.jsp">
	      	<jsp:param name="usuario" value="Ramón Ramirez" />
	      </jsp:include>


      <!--CONTENIDO-->
      
        <div class="col-10 d-flex flex-column justify-content-between">
          <div class="w-100 pt-2">
            <!--TIUTLO PAGINA-->
            <% 
            if(usuario.getTipoAcceso().compareTo(TipoAcceso.Administrador)==0){
            %>
            <h1 class="mt-4"><i class="fas fa-hand-holding-usd me-2"></i>PRESTAMOS CLIENTES</h1>
            <%} %>
            
            <%
            if(usuario.getTipoAcceso().compareTo(TipoAcceso.Cliente)==0){
            %>
            <h1 class="mt-4"><i class="fas fa-hand-holding-usd me-2"></i>MIS PRESTAMOS </h1>
            <%} %>
            
          </div>
          <div class="flex-grow-1">
            <!--FILTRO-->
            <div class="d-flex flex-md-row flex-column justify-content-around align-items-center w-100 gap-2 mt-4">
              <div class="col-4 text-md-start ">
              <% if(usuario.getTipoAcceso().compareTo(TipoAcceso.Administrador)==0){ %>
                <h4 class="opacity-75">Historial de los prestamos de clientes</h4>
              <%} %>
               <% if(usuario.getTipoAcceso().compareTo(TipoAcceso.Cliente)==0){ %>
                <h4 class="opacity-75">Historial de mis prestamos </h4>
              <%} %>
              </div>
              
              <div class="col-md-8">
                <form action="ServletPrestamos" method="get" class="d-flex justify-content-around align-items-center gap-2 flex-md-row flex-column" onsubmit="return validarFechas()">
                
                  <select name="Estados" class="form-select ">
                    <option value="Todos los Estados" <%= ("Todos los Estados".equals(estado)) ? "selected" : "" %>>Todos los estados</option>
                    <option value="Aprobado" <%=("Aprobado".equals(estado)) ? "selected" : "" %>>Aprobados</option>
                    <option value="Rechazado" <%=("Rechazado".equals(estado)) ? "selected" : "" %>>Rechazados</option>
                    <option value="Pendiente" <%=("Pendiente".equals(estado)) ? "selected" : "" %>>Pendientes</option>
                  </select>
                  
                  <!--  <select name="Cancelado" class="form-select ">
				    <option value="Todos">Cancelacion</option>
				    <option value="Saldado">Saldado</option>
				    <option value="NoSaldado">No Saldado</option>
				</select>-->
                  
                   <select id="importes" name="Importes" class="form-select ">
                    <option value="Todos los importes" <%= ("Todos los importes".equals(importe)) ? "selected" : "" %>>Todos los importes</option>
                    <option value="Mayor a" <%= ("Mayor a".equals(importe)) ? "selected" : "" %>>Mayor a</option>
                    <option value="Igual a" <%= ("Igual a".equals(importe)) ? "selected" : "" %>>Igual a</option>
                    <option value="Menor a" <%= ("Menor a".equals(importe)) ? "selected" : "" %>>Menor a</option>
                  </select>
                  <input type="text" id="rangoImporte" name="rangoImporte" oninput="this.value = this.value.replace(/[^0-9]/g, '');this.value = this.value.substring(0, 10);" value="<%=(monto!=null)?monto : "" %>"">
                  
                  
                  <div class="d-flex gap-2">
	                <span >Desde: </span>
	                <input type="date" name="prestamoDesde" id="desdeInput" value="<%= (desde!=null)?desde:"" %>">
	              </div>
	              <div class="d-flex gap-2">
	                <span>Hasta: </span>
	                <input type="date" name="prestamoHasta" id="hastaInput" value="<%= (hasta!=null)?hasta:"" %>">
	              </div>
                  
                  
                  
                  <input type="submit" class="btn btn_main" name="btnFiltrarPrestamos" value="Buscar">
                  <input type="submit" class="btn btn_main" name="btnLimpiarFiltros" value="Limpiar filtros">
                  
                  <!--   <input type="submit" class="btn btn_main" name="btnLimpiar" value="Limpiar filtros"> -->
                </form >
                
              
              </div>
            </div>

		<!--  LISTADO DE PRESTAMOS Y CLIENTES -->
		<% if(session.getAttribute("prestamos")!=null ){
			List<Prestamo> prestamos= (List<Prestamo>)session.getAttribute("prestamos");
			List<Cliente> clientes=new ArrayList();
			if(usuario.getTipoAcceso().compareTo(TipoAcceso.Administrador)==0 && session.getAttribute("clientes")!=null){
				clientes= (List<Cliente>)session.getAttribute("clientes");
			}
		
		%>
            <div class="d-flex flex-md-row flex-column mt-4">
              <div class="h-100 me-4 w-100">
                <table class="table table-striped" id="table_id">
                  <thead>
                    <tr>
                    <th ></th>
                    <th ></th>
                    <th ></th>
                      <th scope="col"># Prestamo</th>
                      
                      <!-- N° CLIENTE SOLO ADMIN -->
                      <% if(usuario.getTipoAcceso().compareTo(TipoAcceso.Administrador)==0) {%>
                      <th scope="col">Cliente</th>
                      <%} %>
                      <!--  -->
                      
                      <th scope="col">Cuenta</th>
                      <th scope="col">Monto solicitado</th>
                      <th scope="col">Intereses</th>
                      
                      <!-- AGREGAR IMPORTE CON INTERESES O NO? -->
                      <!--  -->
                      
                      <th scope="col">Cuotas</th>
                      <th scope="col">Fecha de solicitud</th>
                      <th scope="col">Estado</th>
                      
                      <!-- AGREGAR SALDADO -->
                       <th scope="col">Saldado</th>
                      <!--  -->
                      
                       <!-- DETALLE PARA CLIENTE Y ADMIN -->
                      <th scope="col">Detalle</th>
                       <!--  -->
                       <!-- BOTONES SOLO PARA ADMIN -->
                       <% if(usuario.getTipoAcceso().compareTo(TipoAcceso.Administrador)==0) {%>
                      <th scope="col">Acción</th>
                      <th scope="col">Acción</th>
                      <%} %>
                       <!--  -->
                     
                      
                    </tr>
                  </thead>
                  <tbody>
                  
                  <% for(int x=0;x<prestamos.size();x++){
                	 	
                	  %>
                  
                    <tr>
                    
	                 <form action="ServletPrestamos" method="post">
                    <td> <input type="hidden"  name="idPrestamo"value="<%=prestamos.get(x).getId() %>"></td>
	                <td> <input type="hidden"  name="numCuenta" value="<%=prestamos.get(x).getNumeroCuenta() %>"></td>
	                <td> <input type="hidden"  name="montoPedido" value="<%=prestamos.get(x).getMontoPedido() %>"></td>
	                      
	                      
                      <td><span class="black-75 me-2"  ><%= prestamos.get(x).getId() %></span><i class="fa-solid fa-chart-line opacity-50"></i></td>
                      
                      <!-- N° CLIENTE SOLO ADMIN -->
                      <% if(usuario.getTipoAcceso().compareTo(TipoAcceso.Administrador)==0) {%>
                      <td><i class="fa-solid fa-user opacity-50 me-2"></i><%= clientes.get(x).getApellido() +" "+ clientes.get(x).getNombre() %></td>
                       <%} %>
                       <!--  -->
                      
                       <td><span class="black-75"><%=prestamos.get(x).getNumeroCuenta() %></span></td>
                       
                       <%
						DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
						double montoPedido = prestamos.get(x).getMontoPedido();
						double montoCalculado = Math.abs(prestamos.get(x).getMontoPedido() - prestamos.get(x).getMontoConIntereses());
						%>
                      <td><span class="black-75">$<%= decimalFormat.format(montoPedido) %></span></td>
					  <td><span class="black-75">$<%= decimalFormat.format(montoCalculado) %></span></td>
                      
                      <td><span class="black-75"><%=prestamos.get(x).getTipoTasa().getCantCuotas() %></span></td>
                      <td><span class="black-75"><%=prestamos.get(x).getFechaPrestamo() %></span></td>
                      
                       <% String estadoDescripcion=prestamos.get(x).getEstado().getDescripcion(); %>
                      <td><span class="badge text-white <%= estadoDescripcion.equals("Pendiente")?"bg-warning" : (estadoDescripcion.equals("Aprobado")? "bg-success" :"bg-danger") %>">
                      		<%= estadoDescripcion %>
                      </span></td>
                      
                      <!-- AGREGAR SALDADO -->
                      <td><span class="black-75"><%=prestamos.get(x).isCancelado() ?"Si":"No" %></span></td>
                      
                       <!--DETALLE PARA CLIENTE Y ADMIN -->
                      <td>
					    <a  href="ServletPrestamosDetalle?PrestamoDetalle=<%=prestamos.get(x).getId() %>">
					        <i class="fa-solid fa-circle-info opacity-50 fs-5"></i>
					    </a>
					  </td>

                      
                       <!--  -->
                      
                       <!-- BOTONES SOLO PARA ADMIN -->
                  <% if(usuario.getTipoAcceso().compareTo(TipoAcceso.Administrador)==0) {%>
                      <% if(prestamos.get(x).getEstado().getDescripcion().equals("Pendiente")) {%>
	                      <td><div class="bg-warning p-2 w-100 rounded-2 text-center badge">
	                      		<input type="submit" class="btn btn-alert" value="Aprobar" name="btnAprobarPrestamo" onclick="return confirm('¿Estás seguro que deseas confirmar el prestamo?')">
	                      </div></td>
	                      
	                       <td><div class="bg-warning p-2 w-100 rounded-2 text-center badge">
	                      		<input type="submit" class="btn btn-alert" value="Rechazar" name="btnRechazarPrestamo" onclick="return confirm('¿Estás seguro que deseas rechazar el prestamo?')">
	                      </div></td>
                  	  <%} else{%>
                  	  
                  	   <!--  -->
                  	   
                  	   
                  	  	 <td><div class="">
	                      		
	                      </div></td>
	                       <td><div class="">
	                      		
	                      </div></td>
                  	  <%} %>
                  <%} %> 
                  	  
                  	  
	                 </form>
                  </tr>
                 
                 <% }%>
                  </tbody>
                </table>
                
                
               
              </div>
             
            </div>
           <%} %> 
           
            <form action="ServletPrestamos" method="get">
			    
			    <% if(usuario.getTipoAcceso().compareTo(TipoAcceso.Cliente) == 0) { %>
			        <input type="submit" class="btn btn-success" name="btnSolicitarPrestamo" value="Solicitar Préstamo">
			        
			        <a href="ServletHomeCliente?homecliente=homecliente" class=" btn btn-primary btnEnviar  "><i class="fa-solid fa-arrow-left me-4"></i>Regresar</a>
			        
			    <% } %>
			</form>
			
			<%if(usuario.getTipoAcceso().compareTo(TipoAcceso.Administrador)==0) {%>
					 <a href="PerfilBanco.jsp" class="btn btn-primary btnEnviar "><i class="fa-solid fa-arrow-left me-4"></i>Regresar</a>
				
			<%} %>

           
          </div>
         </div>

	 </div>
	 
	<!-- TERMINA ADMIN -->
	 
	 
	 	<!--FOOTER-->
	    <jsp:include page= "/WEB-INF/Components/footer.html"></jsp:include>
	    
	    
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
		
	 </body>
	 
	 	<!--ESTILOS - TODO: PASAR A CSS-->
	<style>
	
	  td div,
	  nav ul li {
	    cursor: pointer;
	  }
	
	  .fs-7{
	    font-size: 12px;
	  }
	
	  td {
	    vertical-align: middle;
	  }
	  
	 
	
	</style>
	
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
