<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Cuenta"%>
<%@page import="java.util.List"%>
<%@page import="entidad.Cliente"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
	<body class="d-flex flex-column">
	    <div class="row flex-grow-1 m-0">
	      <!--SIDEBAR-->
	      <jsp:include page= "/WEB-INF/Components/menu.jsp">
	      	<jsp:param name="usuario" value="Ramón Ramirez" />
	      </jsp:include>
		      
	      <!--CONTENIDO-->
	        <div class="col-9 d-flex flex-column justify-content-between">
	          <div class="w-100 pt-2">
	            <!--TIUTLO PAGINA-->
	            <h1 class="mt-2">NUEVA TRANSFERENCIA</h1>
	          </div>
	          <div class="flex-grow-1">
	          
	            <!--FORMULARIO BUSCAR CLIENTE-->
	            <%
        			List<Cuenta> cuentas = new ArrayList<Cuenta>();
        		
	        		if(request.getAttribute("cuentas") != null){
	        			cuentas = (List<Cuenta>)request.getAttribute("cuentas");
	        		}
	            %>
	            <%
	            	if(request.getParameter("cargacbu")!=null){
	            	
	            		/* OTRA CUENTA*/
	            		if(request.getParameter("otraCuenta") != null){
	            	%>
			            <form action="ServletNuevaTransferencia" class="col-6 mt-4">
			              <div class="mb-3">
			                <label class="form-label" for="cbu">A otro cliente. ¿Cuál es el CBU?</label>
			                <div class="d-flex align-content-center gap-4">
			                  <input type="text" name="cbuDestinatario" placeholder="22 números" class="form-control w-50" id="cbu" onkeypress="return /[0-9]/i.test(event.key)" required oninput="this.value = this.value.substring(0, 22); validateInput(this, 22);">
			                  <span class="form-text text-danger d-none">Debe tener 22 números.</span>
			                </div>
			              </div>
			              <div class="w-50 d-flex justify-content-center">
			                <input type="submit" name="btnBuscar" class="btn btn_main bg-warning text-black w-100" value="Siguiente">
			              </div>
			            </form>
			            
			            <%} 
			            
	            		/* CUENTA PROPIA */
		            	if(request.getParameter("cuentaPropia") != null){%>
			            
			            <form action="ServletNuevaTransferencia" class="col-6 mt-4">
			               <div class="mb-3">
		                	<label class="form-label" for="concepto">Elija una cuenta propia a la cual transferir.</label>
			                <div class="d-flex align-content-center gap-4">
	                			<select class="form-select w-50" name="cbuDestinatario">
			                	<%
			                		for(Cuenta cuenta : cuentas){%>
					                	<option value="<%=cuenta.getCbu()%>"><%=cuenta.getTipoCuenta().getDescripcion() + ": N° " + cuenta.getNumeroCuenta() + " - " +NumberFormat.getCurrencyInstance(new Locale("es", "AR")).format(cuenta.getSaldo())%></option>
		                			<%} 
	                			%>
			                	</select>
			                </div>
			              </div>
			              <div class="w-50 d-flex justify-content-center">
			                <input type="submit" class="btn btn_main bg-warning text-black w-100" name="btnBuscar" value="Siguiente">
			              </div>
			           </form>    
			
			           <%}%>  
			           <div class="mt-4">
				           <a href="ServletListaTransferencias?listado=true&todos=true" class="p-2 rounded bg-main text-white text-decoration-none col-4 col-md-1 mb-4 mt-4"><i class="fa-solid fa-arrow-left me-4"></i>Regresar</a>
			           </div>   
               	<%}%>
               	
            <!--FIN FORMULARIO BUSCAR CLIENTE-->

            <!--FORMULARIO CARGA DE MONTO-->
            <%
            	Cliente destinatario = new Cliente();
            
            	if(request.getParameter("cargamonto")!=null){

            		if(session.getAttribute("destinatario") != null){
            			destinatario = (Cliente)session.getAttribute("destinatario");
            		} 	
            	%>
            	
            		<form action="ServletNuevaTransferencia" class="col-6 mt-5">
            			<input type="hidden" name="cbuDestinatario" value="<%=request.getParameter("cbuDestinatario")%>"/>
		              <div class="text-center w-50 mb-4 d-flex flex-column justify-content-center">
		                <span class="fs-5 text-uppercase mb-3"><%=destinatario.getNombre() + " " + destinatario.getApellido() %></span>
		                <span class="fs-5 text-uppercase mb-3">CBU: <%=request.getParameter("cbuDestinatario") %></span>
		                <span class="fs-5 text-uppercase mb-3">BANCO CINCO</span>
		              </div>
		              <div class="mb-3">
		                <label class="form-label" for="monto">Monto a transferir</label>
		                <div class="d-flex align-content-center gap-4">
		                <!-- VALIDACION 0 -->
		                  <input type="text" name="monto" placeholder="Punto para los decimales" class="form-control w-50" id="monto" onkeypress="return /[0-9]/i.test(event.key)" required oninput="validateInputMonto(this);">
		                </div>
		              </div>
		              <!-- MAX CARACTERES -->
		              <div class="mb-3">
		                <label class="form-label" for="concepto">Concepto</label>
		                <div class="d-flex align-content-center gap-4">
		                  <input type="text" name="concepto" placeholder="Varios" class="form-control w-50" id="concepto" oninput="this.value = this.value.substring(0, 20); validateInput(this, 0)">
		                </div>
		              </div>
		              <div class="mb-3">
		                <label class="form-label" for="concepto">Cuenta</label>
		                <div class="d-flex align-content-center gap-4">
                			<select class="form-select w-50" name="cbuOrigen" oninput="validateInputCuenta(this)">
		                	<%
		                		for(Cuenta cuenta : cuentas){%>
				                	<option value="<%=cuenta.getCbu()%>"><%=cuenta.getTipoCuenta().getDescripcion() + ": N° " + cuenta.getNumeroCuenta() + " - " +NumberFormat.getCurrencyInstance(new Locale("es", "AR")).format(cuenta.getSaldo())%></option>
	                			<%} 
                			%>
		                	</select>
		                </div>
		              </div>
		              <div class="w-50 d-flex justify-content-center">
		                <input type="submit" name="btnTransferir" class="btn btn_main" value="Aceptar">
		                <a href="ServletNuevaTransferencia?btnCancelar=true" name="btnCancelar" class="btn btn_main bg-danger d-flex ms-4" onclick="return confirm('¿Desea cancelar la transferencia?')">Cancelar</a>
		              </div>
		            </form>
            
            	<%}%>
            
            <!--FIN FORMULARIO CARGA DE MONTO-->
            
          </div>
        </div>
       </div>
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
	 
	 <script>
	 	//VALIDAR DIMENSIONES
	    function validateInput(input, minLength) {
	    	 const trimmedValue = input.value.trim();
	    	    if (trimmedValue.length < minLength || !trimmedValue) {
	    	        input.setCustomValidity(`Debe ingresar al menos 1 carácter(es)`);
	    	        input.classList.add("is-invalid");
	    	    } else {
	    	        input.setCustomValidity('');
	    	        input.classList.remove("is-invalid");
	    	        input.classList.add("is-valid");
	    	    }
   		}
	 	
	 	function validateInputMonto(input){
	 		const trimmedValue = input.value.trim();
	 		if(parseInt(trimmedValue) <= 0){
	 			 input.setCustomValidity(`El monto debe ser superior a $0`);
	    	     input.classList.add("is-invalid");
	 		} else {
    	        input.setCustomValidity('');
    	        input.classList.remove("is-invalid");
    	        input.classList.add("is-valid");
    	    }
	 	}
	 	
	 </script>
	 
</html>