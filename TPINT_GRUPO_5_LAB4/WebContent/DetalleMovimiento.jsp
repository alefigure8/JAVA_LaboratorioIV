<%@page import="com.sun.org.apache.bcel.internal.generic.CPInstruction"%>
<%@page import="com.sun.swing.internal.plaf.metal.resources.metal"%>
<%@page import="entidad.Destinatario"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.HashMap"%>
<%@page import="entidad.Movimiento"%>
<%@page import="entidad.TipoAcceso"%>
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
	<%
	Movimiento m = null;
	if(request.getAttribute("movimiento")!=null){
		
		m = (Movimiento)request.getAttribute("movimiento");
	}

	
	
	%>
	    <div class="row flex-grow-1 m-0">
	      <!--SIDEBAR-->
	      <jsp:include page= "/WEB-INF/Components/menu.jsp">
	      	<jsp:param name="usuario" value="Ramón Ramirez" />
	      </jsp:include>
		  <!--CONTENIDO-->
      <div class="col-lg-9 col-md-12 mx-2" >
        <div class="pt-2 d-flex gap-4 justify-content-center row">

          <h1 class="mb-2">DETALLE DE MOVIMIENTO</h1>


          <div class="d-flex flex-column gap-4">
            <div class="border border-1 rounded p-2 col-11 col-md-3">
              
              <%  switch(m.getTipoMovimiento().getId()) {
              case 1:
                %>
                  <p class="mb-0">Tipo de Movimiento</p>
              <p class="fs-5"> <%=m.getTipoMovimiento().getDescripcion() %> </p> 
              <p class="mb-0">Numero de Movimiento</p>
              <p class="fs-5"><%=m.getId() %> </p>
           
              <p class="mb-0">Fecha de Alta</p>
              <p class="fs-5"><%=m.getFechaMovimiento() %> </p>
              <p class="mb-0">Monto Incial</p>
              <p class="fs-5"><%=String.format("%,.2f",m.getMonto()) %> </p>
              <p class="mb-0">Cuenta</p>
              <p class="fs-5"><%=m.getCuenta().getCbu() %> </p>        
                
             <%
                
                  break;
              case 2:
            		Prestamo p = null;
            		if(request.getAttribute("prestamo")!=null){
            				
            				p = (Prestamo)request.getAttribute("prestamo");
            			}
       ;
            	  
             %>
                         <p class="mb-0">Tipo de Movimiento</p>
              <p class="fs-5"> <%=m.getTipoMovimiento().getDescripcion() %> </p>
              <p class="mb-0">Numero de Movimiento</p>
              <p class="fs-5"><%=m.getId() %> </p>
   
              <p class="mb-0">Fecha de Alta</p>
              <p class="fs-5"><%=m.getFechaMovimiento() %> </p>
              <p class="mb-0">Monto Otorgado</p>
              <p class="fs-5"><%=String.format("%,.2f",p.getMontoPedido()) %> </p>
              <p class="mb-0">Monto con Intereses</p>
              <p class="fs-5"><%=String.format("%,.2f",p.getMontoConIntereses()) %> </p>
              <p class="mb-0">Cantidad de Cuotas</p>
              <p class="fs-5"><%=p.getTipoTasa().getCantCuotas()%> </p>
              <p class="mb-0">Tasa de interes</p>
              <p class="fs-5"><%=p.getTipoTasa().getTasaInteres()%> </p>
              <p class="mb-0">Monto por Cuota</p>
              <p class="fs-5"><%= String.format("%,.2f", p.getMontoxMes())%> </p>
                <%
                
                  break;
              case 3:
            	  
            	  CuotaPrestamo cp = null;
          		if(request.getAttribute("cuota")!=null){
          				
          				cp = (CuotaPrestamo)request.getAttribute("cuota");
          			}
            	  
             %>
                         	
               <p class="mb-0">Tipo de Movimiento</p>
              <p class="fs-5"> <%=m.getTipoMovimiento().getDescripcion() %> </p>
               <p class="mb-0">Numero de Movimiento</p>
              <p class="fs-5"><%=m.getId() %> </p>
             
              <p class="mb-0">Numero de Prestamo</p>
              <p class="fs-5"><%= cp.getIdPrestamo()%> </p>
              <p class="mb-0">Numero de Cuota</p>
              <p class="fs-5"><%= cp.getNumeroCuota()%> </p>
              <p class="mb-0">Fecha de Vencimiento</p>
              <p class="fs-5"><%= cp.getFechaVencimiento() %> </p>
              <p class="mb-0">Fecha de Pago</p>
              <p class="fs-5"><%= m.getFechaMovimiento() %> </p>
              <p class="mb-0">Monto Abonado</p>
              <p class="fs-5"><%= cp.getMontoCuota()%> </p>
         
                  <%
                                
                
                  break;
              case 4:
             %>
                 <p class="mb-0">Tipo de Movimiento</p>
              <p class="fs-5"> <%=m.getTipoMovimiento().getDescripcion() %> </p>
             <p class="mb-0">Tipo Operacion</p>
              <p class="fs-5"><%=m.getOperacion() %> </p>
              <p class="mb-0">Numero de Movimiento</p>
              <p class="fs-5"><%=m.getId() %> </p>     
                   <p class="mb-0">Fecha</p>
              <p class="fs-5"><%=m.getFechaMovimiento() %> </p>        
             <p class="mb-0">Cuenta Origen</p>
              <p class="fs-5"><%=m.getCuenta().getCbu() %> </p>
             <p class="mb-0">Cuenta Destino</p>
              <p class="fs-5"><%=m.getNumeroReferencia() %> </p>
             <p class="mb-0">Monto</p>
              <p class="fs-5"><%=String.format("%,.2f",m.getMonto()) %> </p>
             
                    <%
                
                  break;
              }
             %>
            </div>
  		<% 
  		String redirect="";
  		
  		if(request.getAttribute("redirect")!=null){
  			
  		redirect = request.getAttribute("redirect").toString();
  		
		}
  		
  		
  		%>
            <a href=<%=redirect%> class="p-2 rounded bg-main text-white text-decoration-none col-4 col-md-1 mb-4"><i class="fa-solid fa-arrow-left me-4"></i>Regresar</a>
          </div>
   
        </div>
</div>
			    	
      </div>
            
	
      <!--FIN MAIN-->
	 	<!--FOOTER-->
	    <jsp:include page= "/WEB-INF/Components/footer.html"></jsp:include>
	 </body>
</html>