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
	<!-- FIN HEAD -->
	<body class="d-flex flex-column">
	    <div class="row flex-grow-1 m-0">
	      <!--SIDEBAR-->
	      <jsp:include page= "/WEB-INF/Components/menu.jsp">
	      	<jsp:param name="usuario" value="Ramón Ramirez" />
	      </jsp:include>
		      
      <!--CONTENT-->
       		 <div class="col-lg-9 col-md-12 d-flex flex-column">
	       		 <div class="w-100 pt-2" >
		          <h1 id="tituloCliente" style="margin-bottom:5%"> <i class="fas fa-chart-line me-2"></i>ESTADISTICAS BANCO</h1>
		        </div>
	            <div class="card mt-3">
	                <div class="card-body">
		                <div class="row">
		                    <div class="col">
					    <label for="montoCuentasClientes">Monto Total Cuentas Clientes</label>
					    <input type="text" class="form-control" id="montoCuentasClientes" name="montoCuentasClientes" value="${totalCuentas}" disabled>
					</div>
					<div class="col">
					    <label for="montoCuentasCorrientes">Monto Total Cuentas Corrientes</label>
					    <input type="text" class="form-control" id="montoCuentasCorrientes" name="montoCuentasCorrientes" value="${totalCuentaCorriente}" disabled>
					</div>
					<div class="col">
					    <label for="montoCajasAhorro">Monto Total Cajas de Ahorro</label>
					    <input type="text" class="form-control" id="montoCajasAhorro" name="montoCajasAhorro" value="${totalCajaAhorro}" disabled>
					</div>
	               		</div>
	            	</div>
        		</div>
              <form action="ServletEstadisticasBancos" method="get">
        		<div class="card mt-3">
                    <div class="card-body">
           				 <div class="row mt-2 d-flex">
		                    <div class="col-md-3"> <!-- Dividimos el formulario en 4 columnas para los 4 elementos -->
		                       
		                        <label for="anio">Año:</label>
                                <input type="number" id="anio" name="anio" min="1900" max="9999" oninput="validarAnio()">
                                <div id="mensajeError" style="color: red;"></div>
		                        </div>
		                         <div class="col-md-3">
		                            <label for="Mes">Mes:</label>
			                        <select id="Mes" class="form-select" name="Mes" onchange="validarMes()" disabled>
			                        <option value="0">Todo el año</option>
			                            <option value="1">Enero</option>
                               <option value="2">Febrero</option>
                                <option value="3">Marzo</option>
                                <option value="4">Abril</option>
                                <option value="5">Mayo</option>
                                <option value="6">Junio</option>
                                <option value="7">Julio</option>
                                <option value="8">Agosto</option>
                                <option value="9">Septiembre</option>
                               <option value="10">Octubre</option>
                               <option value="11">Noviembre</option>
                               <option value="12">Diciembre</option>
			                            <!-- Agrega más opciones según sea necesario -->
			                        </select>
			                        <div id="mensajeErrorMes" style="color: red;"></div>
		                    	</div>
                       		 
                   		
	                    
	                    <div class="col-md-1 d-flex"> <!-- Agregamos un margen top para el botón -->
	                        <input type="submit" id="btnEnviar" name="btnEnviar" class="btn btn-primary btnEnviar"  value="Filtrar" disabled>
	                    </div>
	            	</div>
	       		 </div>
	   		 </div>
</form>
			<div class="card mt-3">
			    <div class="card-body">
		       <div class="row"  style="margin-bottom: 50px;">
		       
		       <div class="col-md-6">
		       <label for="clientesNuevos">Año Seleccionado</label>
    <input type="text" class="form-control" id="clientesNuevos" name="MostrarAño" value="<%= request.getAttribute("mostrarAnio") %>" disabled>
		       </div>
		       
		         <div class="col-md-6">
		         <label for="clientesNuevos">Mes Seleccionado</label>
    <input type="text" class="form-control" id="clientesNuevos" name="MostrarMes" value="<%= request.getAttribute("mostrarMes") %>" disabled>
		       </div>
		       
		       </div>	
		                <div class="row">
		                    <div class="col-md-6">
		                        <div class="form-group">
    <label for="clientesNuevos">Clientes Nuevos</label>
    <input type="text" class="form-control" id="clientesNuevos" name="clientesNuevos" value="<%= request.getAttribute("clientesNuevos") %>" disabled>
</div>
		                        
		                        <div class="form-group">
		                            <label for="cuentasNuevas">Cuentas Nuevas</label>
		                            <input type="text" class="form-control" id="cuentasNuevas" name="cuentasNuevas" value="<%= request.getAttribute("cuentasNuevas") %>" disabled>
		                        </div>
		                        
		                        <div class="form-group">
		                            <label for="cajasAhorro">Cajas de Ahorros nuevas</label>
		                            <input type="text" class="form-control" id="cajasAhorro" name="cajasAhorro" value="<%= request.getAttribute("cuentasNuevasCaja") %>" disabled>
		                        </div>
		                        <div class="form-group">
		                            <label for="montoTotalTransferido">Monto Total Transferido</label>
		                            <input type="text" class="form-control" id="montoTotalTransferido" name="montoTotalTransferido" value="<%= request.getAttribute("totalMontoTransferencias") %>" disabled>
		                        </div>
		                    </div>
		                    <div class="col-md-6">
		                        <div class="form-group">
		                            <label for="cuentasCorrientes">Cuentas Corrientes nuevas</label>
		                            <input type="text" class="form-control" id="cuentasCorrientes" name="cuentasCorrientes" value="<%= request.getAttribute("cuentasNuevasCorriente") %>" disabled>
		                        </div>
		                        <div class="form-group">
		                            <label for="transferencias">Cantidad de Transferencias</label>
		                            <input type="text" class="form-control" id="transferencias" name="transferencias" value="<%= request.getAttribute("cantidadTransferencias") %>" disabled >
		                        </div>
		                        <div class="form-group">
		                            <label for="prestamosOtorgados">Prestamos Otorgados</label>
		                            <input type="text" class="form-control" id="prestamosOtorgados" name="prestamosOtorgados" value="<%= request.getAttribute("prestamosNoCancelados") %>" disabled>
		                        </div>
		                        <div class="form-group">
		                            <label for="prestamosCancelados">Prestamos Cancelados</label>
		                            <input type="text" class="form-control" id="prestamosCancelados" name="prestamosCancelados" value="<%= request.getAttribute("prestamosCancelados") %>" disabled>
		                        </div>
		                        
		                    </div>
		                     
		                </div>
		                
		            </div>
		           
		        </div>
		        
		        <div class= "mt-2 mb-2">
						<a href="PerfilBanco.jsp" class="btn btn-primary btnEnviar "><i class="fa-solid fa-arrow-left me-4"></i>Regresar</a>
				</div>
				
		    </div> 
       </div>
       <script>
    function validarAnio() {
    	 var botonEnviar = document.getElementById("btnEnviar");
        var inputAnio = document.getElementById("anio");
        var mensajeError = document.getElementById("mensajeError");
        var selectMes = document.getElementById("Mes");
        var anioActual = new Date().getFullYear();
        var anioIngresado = parseInt(inputAnio.value, 10);

        if (isNaN(anioIngresado) || anioIngresado < 1900 || anioIngresado > 9999) {
            mensajeError.textContent = "Ingrese un año válido.";
            inputAnio.style.borderColor = "red";
            botonEnviar.disabled = true;
            selectMes.disabled = true;
        } else if (anioIngresado > anioActual) {
            mensajeError.textContent = "El año no puede ser superior al actual.";
           
            inputAnio.style.borderColor = "red";
            botonEnviar.disabled = true;
            selectMes.disabled = true;
        } else {
        	
            mensajeError.textContent = "";
            inputAnio.style.borderColor = "";
            botonEnviar.disabled = false;// Restaurar el color del borde por defecto
            selectMes.disabled = false;
            document.getElementById("mensajeErrorMes").innerHTML = "";
        }
    }
    function validarMes() {
        var mesSelect = document.getElementById("Mes");
        var botonEnviar = document.getElementById("btnEnviar");

        // Obtén el mes actual
        var mesActual = new Date().getMonth() + 1; // Los meses en JavaScript van de 0 a 11

        // Parsea el valor del mes como entero
        var mes = parseInt(mesSelect.value);

        // Verifica si el mes es superior al mes actual
        if (mes > mesActual) {
            // Muestra el mensaje de error en rojo
            document.getElementById("mensajeErrorMes").style.color = "red";
            document.getElementById("mensajeErrorMes").innerHTML = "El mes no puede ser superior al actual.";

            // Desactiva el botón de enviar
            botonEnviar.disabled = true;
        } else {
            // Borra el mensaje de error
            document.getElementById("mensajeErrorMes").innerHTML = "";

            // Activa el botón de enviar
            botonEnviar.disabled = false;
        }
    }

   
</script>
       <!-- FIN MAIN -->
	 	<!--FOOTER-->
	    <jsp:include page= "/WEB-INF/Components/footer.html"></jsp:include>
	 </body>
</html>