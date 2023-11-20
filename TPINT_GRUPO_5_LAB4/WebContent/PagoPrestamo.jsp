<%@page import="entidad.TipoAcceso"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>
<%@page import="entidad.Cuenta"%>
<%@page import="entidad.Prestamo"%>
<%@page import="entidad.CuotaPrestamo"%>
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
		      
      <!--CONTENIDO-->
      
     <% if(request.getAttribute("cuotaApagar")!=null){
    	 CuotaPrestamo cuota= (CuotaPrestamo)request.getAttribute("cuotaApagar");
    	 Prestamo prestamo=(Prestamo)request.getAttribute("prestamo");
    	 List<Cuenta> cuentasCliente=(List<Cuenta>)request.getAttribute("cuentasCliente");
    	 
    	 Double monto=prestamo.getMontoPedido()/prestamo.getTipoTasa().getCantCuotas();
    	 Double interes=monto*prestamo.getTipoTasa().getTasaInteres()/100;
    	 System.out.println("RECIBO CON PARAMETER PRESTAMO A PAGAR"  + prestamo);
			System.out.println("RECIBO CON PARAMETER CUOTA A PAGAR"  + cuota);
    	 %>
     
        <div class="col-10 d-flex flex-column justify-content-center align-items-center ">
          <div class="w-100 pt-2 mt-4">
            <!--TIUTLO PAGINA-->
            <h1><i class="fa-solid fa-hand-holding-dollar me-2"></i>PAGO PRESTAMO</h1>
          </div>
          <div class="flex-grow-1 ">
            <!--FILTRO-->
            <div class="text-center mt-4">
                <h4 class="opacity-75" name="idPrestamo">Prestamo #<%= prestamo.getId()%></h4>
            </div>

            <!--TABLA-->
            
       <form action="ServletPrestamos" method="post" >
            <div class="d-flex justify-content-center flex-md-row flex-column">
              <div class="mt-4 border border-1 border-black border-opacity-25 rounded-1 p-2 mb-4" style="min-width: 300px;">
                <div class="col-7 d-flex justify-content-between w-100 align-items-center mb-2">
                  <h4 class="opacity-75 m-0">Detalle</h4>
                </div>
                <div>
                  <input type="hidden" name="idCuotaApagar" value=<%= cuota.getId() %>>
                  <input type="hidden" name="montoCuota" value=<%= cuota.getMontoCuota()%>>
                  <input type="hidden" name="idPrestamo" value=<%= prestamo.getId()%>>
                  <p class="mb-0">Cuota</p>
                  <p class="fs-5"><%=cuota.getNumeroCuota()%>/<%=prestamo.getTipoTasa().getCantCuotas() %></p>
                  <p class="mb-0">Estado</p>
                  <p class="fs-5"><%= cuota.getEstado().Pendiente %></p>
                  <p class="mb-0">Fecha de Vencimiento</p>
                  <p class="fs-5"><%= cuota.getFechaVencimiento() %></p>
                  <p class="mb-0">Monto</p>
                  <p class="fs-5"><%=monto %></p>
                  <p class="mb-0">Interés</p>
                  <p class="fs-5"><%=interes %></p>
                  <p class="mb-0">Total a pagar</p>
                  <p class="fs-3 fw-bold text-secondary"><%= cuota.getMontoCuota() %></p>
                  <p class="mb-0">Pagando desde</p>
                  <!-- LISTA DE CUENTAS ACTIVAS DE CLIENTE -->
                  <select name="CuentasClientePago" class="form-select form-select-sm w-md-50">
                  <% for(Cuenta cuentas:cuentasCliente)  {
                	  if(cuentas.isActivo()){
                  	%>
                    <option value="<%= cuentas.getNumeroCuenta() %>">N° <%=cuentas.getNumeroCuenta() %> - $<%=cuentas.getSaldo() %></option>
                  	
                    <%} 
                	  }%>
                  </select>
                  <input type="submit" class="btn btn-success  d-inline w-100 mt-4" name="btnPagarCuota" value="Pagar cuota" onclick="return confirm('¿Estás seguro que deseas abonar la cuota?')">
                </div>
               
              </div>
              
            </div>
            <div class="text-center mb-4">
           	  <a href="ServletPrestamosDetalle?PrestamoDetalle=<%=prestamo.getId() %>" class=" btn btn-primary btnEnviar  "><i class="fa-solid fa-arrow-left me-4"></i>Regresar</a>
             </div>
       </form>   
          </div>
          
          
          
        </div>
<%} %>
	       </div>
	 	<!--FOOTER-->
	    <jsp:include page= "/WEB-INF/Components/footer.html"></jsp:include>
	 </body>
</html>