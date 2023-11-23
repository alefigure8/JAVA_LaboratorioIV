<%@page import="entidad.TipoAcceso"%>
<%@page import="entidad.Movimiento"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Cuenta"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

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
	<body class="d-flex flex-column">
	
	    <div class="row flex-grow-1 m-0">
	      <!--SIDEBAR-->
	      <jsp:include page= "/WEB-INF/Components/menu.jsp">
	      	<jsp:param name="usuario" value="Ramón Ramirez" />
	      </jsp:include>
		      
	 <% 
	StringBuilder CuentaFormateada = new StringBuilder();
	Cuenta cuenta=null;
	if(request.getAttribute("cuenta")!=null){
		
		cuenta = (Cuenta)request.getAttribute("cuenta");	
		String cuentastring = String.valueOf(cuenta.getNumeroCuenta());

		CuentaFormateada.append(cuentastring, 0, 2).append("-").append(cuentastring, 2, 9).append("/").append(cuentastring.charAt(9));
	};
	ArrayList<Movimiento> listaMovimientos=null;
	if(request.getAttribute("listaMovimientos")!=null){
		
		listaMovimientos = (ArrayList<Movimiento>)request.getAttribute("listaMovimientos");
	};
	
	String concepto = null;
	String importe = null;
	String monto = null;
	String desde = null;
	String hasta = null;
	if(request.getAttribute("conceptoSeleccionado")!=null){
		concepto = (String) request.getAttribute("conceptoSeleccionado");
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
		      
		      
		      <!--CONTENT-->
		      <div class="col-10 d-flex flex-column justify-content-between">
		        <div class="w-100 pt-2 text-center mt-2">
		          <h1><i class="fas fa-wallet me-2"></i>CUENTA SELECCIONADA: <%=cuenta.getTipoCuenta().getDescripcion() %></h1>
		          <h5 class="mt-4">Numero de cuenta: <%=CuentaFormateada.toString() %></h5>
		          <h5>CBU: <%=cuenta.getCbu() %></h5>
		          <h5>Saldo: $<%=String.format("%,.2f",cuenta.getSaldo())%></h5>
		        </div>
		        
		         <div class="flex-grow-1">
            <!--FILTRO-->
            <div class="d-flex flex-md-row flex-column justify-content-around  w-100 gap-2 mt-4">
            
            <div class="col-4 text-md-start ">
                <h4 class="opacity-75">Historial de movimientos</h4>
              </div>
            
              
             <div class="col-md-8">
			    <form action="ServletDetalleCuenta" method="get" class="d-flex justify-content-around gap-2 flex-md-row flex-column" onsubmit="return validarFechas()">
			        <input type="hidden" name="nroCuenta" value="<%= CuentaFormateada.toString()%>">
			        <input type="hidden" name="cbu" value="<%= cuenta.getCbu()%>">
			        <select name="Conceptos" class="form-select w-100">
			            <option value="Todos los conceptos" <%= ("Todos los conceptos".equals(concepto)) ? "selected" : "" %>>Todos los conceptos</option>
			            <option value="Alta de cuenta" <%= ("Alta de cuenta".equals(concepto)) ? "selected" : "" %>>Alta de cuenta</option>
			            <option value="Alta de un prestamo" <%= ("Alta de un prestamo".equals(concepto)) ? "selected" : "" %>>Alta de un prestamo</option>
			            <option value="Pago prestamo" <%= ("Pago prestamo".equals(concepto)) ? "selected" : "" %>>Pago prestamo</option>
			            <option value="Transferencia" <%= ("Transferencia".equals(concepto)) ? "selected" : "" %>>Transferencia</option>
			        </select>
			
			        <select id="importes" name="Importes" class="form-select w-100">
			            <option value="Todos los importes" <%= ("Todos los importes".equals(importe)) ? "selected" : "" %>>Todos los importes</option>
			            <option value="Mayor a" <%= ("Mayor a".equals(importe)) ? "selected" : "" %>>Mayor a</option>
			            <option value="Igual a" <%= ("Igual a".equals(importe)) ? "selected" : "" %>>Igual a</option>
			            <option value="Menor a" <%= ("Menor a".equals(importe)) ? "selected" : "" %>>Menor a</option>
			        </select>
			
			        <input type="text" id="rangoImporte" name="rangoImporte" 
			               oninput="this.value = this.value.replace(/[^0-9]/g, '');this.value = this.value.substring(0, 10);" 
			               value="<%= (monto != null) ? monto : "" %>" >
			
			        <div class="d-flex gap-2">
			            <span>Desde: </span>
			            <input type="date" name="movimientoDesde" id="desdeInput" value="<%= (desde != null) ? desde : "" %>" >
			        </div>
			        <div class="d-flex gap-2">
			            <span>Hasta: </span>
			            <input type="date" name="movimientoHasta" id="hastaInput" value="<%= (hasta != null) ? hasta : "" %>">
			        </div>
			
			        <input type="submit" class="btn btn_main" name="btnFiltrarMovimientos" value="Buscar">
			        <input type="submit" class="btn btn_main" name="btnLimpiarFiltros" value="Limpiar filtros">
			
			    </form>
			</div>

            </div>
		        
		        
		        <div class="flex-grow-1 mt-4">
		          	<!-- CONTENIDO-->
		      	 	<!--  <li class="list-group-item border-0 border-bottom border-secondary bg-transparent"></li>		 -->     	     	 

			      	<table class="table" id="table_id_3">
					  <thead>
					    <tr> 
					      <th>FECHA</th>
					      <th>CONCEPTO</th>
					      <th>DESTINO</th>
					      <th>NUMERO</th>
					      <th>TIPO DE MOV.</th>
					      <th>IMPORTE</th>
					      <th>ACCIÓN</th>
					    </tr>
					  </thead>
					  <tbody>
					    <% for (Movimiento m : listaMovimientos) { %>
					    <tr>
					      <td><%= m.getFechaMovimiento() %></td>
					      <td><%= m.getTipoMovimiento().getDescripcion() %></td>
					      <% if (m.getTipoMovimiento().getId() == 1 || m.getTipoMovimiento().getId() == 2) { %>
					      <td>CBU: <%= cuenta.getCbu() %></td>
					      <% } else { %>
					      <td>#<%= m.getNumeroReferencia() %></td>
					      <% } %>
					      <td><%= m.getId() %></td>
					      <td><%= m.getOperacion() %></td>
					      <% if (m.getOperacion().equals("Entrada")) { %>
					      <td style="color:#00a000;">$<%= String.format("%,.2f",m.getMonto()) %></td>
					      <% } else { %>
					      <td style="color:#ff0000;">$<%= String.format("%,.2f",m.getMonto()) %></td>
					      <% } %>
					      <td>
					        <form action="ServletDetalleMovimiento" method="get">
					          <input type="submit" class="btn btn-primary btnEnviar" name="btnVerDetalleMovimiento" value="VER">
					          <input type="hidden" name="idmovimiento" value="<%= m.getId() %>">
					        </form>
					      </td>
					    </tr>
					    <% } %>
					  </tbody>
					</table>
      <div> 
 <a href="ServletCuentas?Cuentas=true" class="btn btn-primary btnEnviar mt-3"><i class="fa-solid fa-arrow-left me-2"></i>Regresar</a>
			  </div>
		        </div>
		      </div>
	       </div>
	 	<!--FIN MAIN-->
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