<%@page import="entidad.Localidad"%>
<%@page import="daoImp.LocalidadDaoImp"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="negocioDaoImp.ProvinciaNegocioDaoImp"%>
<%@page import="entidad.Provincia"%>
<%@page import="entidad.Cliente"%>
<%@page import="entidad.TipoAcceso"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!-- AUTENTICACION -->
<jsp:include page="/WEB-INF/Components/autenticacion/autenticacion.jsp"> 
	<jsp:param name="TipoUsuarioPagina" value="<%=TipoAcceso.Administrador%>" />
</jsp:include>
<!-- FIN AUTENTICACION -->

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es">
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

	      <!--MAIN-->
	      <div class="col-lg-9 col-md-12 d-flex flex-column justify-content-between">
	        <div class="w-100 pt-2">
	          <h1 class="mb-4">MODIFICAR CLIENTE</h1>
	        </div>
	        <div class="flex-grow-1">
	          <!-- CONTENIDO-->
	          <%
	          	//REQUEST DESDE SERVLET
	          	
	          	Cliente cliente= new Cliente();
	          	List<Localidad> listaLocalidad = new ArrayList<Localidad>();
	          	List<Provincia> listaProvincia = new ArrayList<Provincia>();
	          	
	          	//USUARIO
	          	if(request.getAttribute("cliente")!=null){
	          		cliente = (Cliente)request.getAttribute("cliente");
	          	}
	          	
	          	//LOCALIDADES
	          	if(session.getAttribute("localidades")!=null){
	          		listaLocalidad = (List<Localidad>)session.getAttribute("localidades");
	          	}
	          	
	          	//PROVINCIA
	          	if(session.getAttribute("provincias")!=null){
	          		listaProvincia = (List<Provincia>)session.getAttribute("provincias");
	          	}
	          %>
				<form action="servletModificarCliente" method="get" accept-charset="UTF-8">
					<!-- HIDDEN ID -->
					<input type="hidden" name="ID" value="<%=cliente.getId() %>">
					
				    <div class="row">
				        <div class="col-md-4">
					    	<fieldset class="border p-2 mt-2">
					    		<legend class="w-auto">Cliente:</legend>
					    		<div class="form-group">
					                <label for="nombre">Nombre</label>
					                <input type="text" class="form-control" name="nombre" id="nombre" value="<%=cliente.getNombre() %>" required oninput="this.value = this.value.replace(/[^A-Za-zÁ-Úá-ú\s]/g, ''); this.value = this.value.substring(0, 20); validateInput(this, 2)">
					            </div>
					            <div class="form-group">
					                <label for="apellido">Apellido</label>
					                <input type="text" class="form-control" name="apellido" id="apellido" value="<%=cliente.getApellido() %>" required oninput="this.value = this.value.replace(/[^A-Za-zÁ-Úá-ú\s]/g, ''); this.value = this.value.substring(0, 20); validateInput(this, 2)">
					            </div>
					            <div class="form-group">
					                <label for="dni">DNI</label>
					                <input type="text" class="form-control" name="dni" id="dni" value="<%=cliente.getDni() %>" onkeypress="return /[0-9]/i.test(event.key)" required oninput="this.value = this.value.substring(0, 8); validateInput(this, 8)">
					            </div>
					            <div class="form-group">
					                <label for="cuil">CUIL</label>
					                <input type="text" class="form-control" name="cuil"  id="cuil" value="<%=cliente.getCuil()%>" onkeypress="return /[0-9]/i.test(event.key)" required oninput="this.value = this.value.substring(0, 11); validateInput(this, 11)">
					            </div>
					            <div class="form-group">
					                <label for="nacionalidad">Nacionalidad</label>
					                <input type="text" class="form-control" name="nacionalidad" id="nacionalidad" value="<%=cliente.getNacionalidad() %>" required oninput="this.value = this.value.replace(/[^A-Za-z\s]/g, ''); this.value = this.value.substring(0, 20); validateInput(this, 2)">
					            </div>
					            <div class="form-group">
					                <label for="fechaNacimiento">Fecha de Nacimiento</label>
					                <input type="date" placeholder='DD-MM-AAAA' class="form-control" name="fechaNacimiento" id="fechaNacimiento" value="<%=cliente.getNacimiento() %>" required onblur="validarFecha(this);">
					            </div>
   					            <div class="form-group">
					                <label for="sexo">Sexo</label>
					                <select class="form-select" name="sexo" id="sexo" data-sexoID="<%=cliente.getSexo()%>">
					               		<option value="Seleccionar">Seleccionar</option>
					                	<option value="F">Femenino</option>
					                	<option value="M">Masculino</option>
					                </select>
					            </div>
			            	</fieldset>
			        	</div>
				        <div class="col-md-4">
				        <fieldset class="border p-2 mt-2">
					    		<legend class="w-auto">Contactos:</legend>
					          	<div class="form-group">
					                <label for="calle">Calle</label>
					                <input type="text" class="form-control" name="calle" id="calle" value="<%=cliente.getDireccion().getCalle()%>" required oninput="this.value = this.value.substring(0, 20); validateInput(this, 2);">
					            </div>
					            <div class="form-group">
					                <label for="numero">Numero</label>
					                <input type="text" class="form-control" name="numero" id="numero" value="<%=cliente.getDireccion().getNumero()%>" onkeypress="return /[0-9]/i.test(event.key)" required oninput="this.value = this.value.substring(0, 10); validateInput(this, 1);">
					            </div>
					            <div class="form-group">
					                <label for="tipoDireccion">Tipo Dirección</label>
					                <select class="form-select" name="tipoDireccion"  id="tipoDireccion" data-TipoDireccionID="<%=cliente.getDireccion().getTipoDireccion()%>">
					               		<option value="Seleccionar">Seleccionar</option>
					                	<option value="Casa">Casa</option>
					                	<option value="Departamento">Departamento</option>
					                </select>
					            </div>
					            <div class="form-group">
					                <label for="apartamento">Apartamento</label>
					                <input type="text" class="form-control bg-light text-secondary" name="apartamento" id="apartamento" value="<%=cliente.getDireccion().getNumeroDepartamento() == null ? "" : cliente.getDireccion().getNumeroDepartamento() %>" readonly>
					            </div>
					            <div class="form-group">
					                <label for="codpos">Código Postal</label>
					                <input type="text" class="form-control" name="codpos" id="codpos" value="<%=cliente.getDireccion().getCodigoPostal()%>" onkeypress="return /[0-9]/i.test(event.key)" required oninput="this.value = this.value.substring(0, 10); validateInput(this, 4);">
					            </div>
					             <div class="form-group">
					                  <label for="provincia">Provincia</label>
					                	
					                <select class="form-select" id="provinciaID" name="provincia" data-provinciaID="<%=cliente.getDireccion().getLocalidad().getIdProvincia()%>" onchange="seleccionarProvincia()">
					                	<%
				                		for(Provincia provincia : listaProvincia){%>
					                		<option value="<%= provincia.getIdProvincia() %>"> <%= provincia.getNombre() %> </option>
					                	<%}%>
					                </select>
					            </div>
					            <div class="form-group">
					                  <label for="localidadID">Localidad</label>
					                	
					                <select class="form-select" id="localidadID" name="localidadID" data-localidadID="<%=cliente.getDireccion().getLocalidad().getIdLocalidad()%>">
					                	<%
				                		for(Localidad localidad : listaLocalidad){%>
					                		<option value="<%=localidad.getIdLocalidad()%>"><%=localidad.getNombre()%></option>
					                	<%}%>
					                </select>
					            </div>
					            <div class="form-group">
					                <label for="telefono">Teléfono</label>
					                <input type="text" class="form-control" name="telefono" id="telefono" value="<%=cliente.getTelefono()%>" onkeypress="return /[0-9]/i.test(event.key)" required oninput="this.value = this.value.substring(0, 20); validateInput(this, 8)">
					            </div>
					            <div class="form-group">
					                <label for="correo">Correo</label>
					                <input type=email class="form-control" name="correo" id="correo" value="<%=cliente.getEmail()%>" required >
					            </div>

				            </fieldset>
				            </div>
					        <div class="col-md-4">
					            <fieldset class="border p-2 mt-2">
						    		<legend class="w-auto">Usuario:</legend>
							    		<div class="form-group">
							                <label for="NombreUsuario">Nombre de Usuario</label>
							                <input type="text" class="form-control bg-light text-secondary" name="NombreUsuario" id="NombreUsuario" value="<%=cliente.getUsuario()%>" readonly>
							            </div>
							            <div class="form-group">
						                	<label for="claveVieja">Contraseña Nueva</label>
						                	<input type="password" class="form-control" id="claveNueva" name="claveNueva" placeholder="Ingrese la nueva contraseña">
							            </div>
							            <div class="form-group">
							                <label for="claveNueva">Repetir Contraseña</label>
							                <input type="password" class="form-control" id="claveRepetida" name="claveRepetida" placeholder="Repita la nueva contraseña">
							            </div>
					    		</fieldset>
					        </div>
					    </div>
					    <div class="d-flex gap-4 justify-content-center">
						    <div class="text-center mt-5">
						        <button type="submit" class="btn btn-primary" name="modificar" value="true">Modificar Usuario</button>
						    </div>
						    <div class="text-center mt-5">
						        <a class="btn btn-danger" href="ServletListarClientes?obtener=true&filtro=Activos">Cancelar</a>
						    </div>
					   </div>
				</form>
	       		</div>
      		  </div>
		   </div>
	 		   <!-- FIN MAIN -->
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
	 	//CONTROL DE TIPO DE APARTAMENTO
	 	const tipoDireccion = document.getElementById("tipoDireccion");
	 	const apartamento = document.getElementById("apartamento");
	 	
	 	tipoDireccion.onchange = () => {
	 		if(tipoDireccion.value == "Departamento"){
	 			apartamento.readOnly = false;
	 			apartamento.classList.remove("bg-light")
	 			apartamento.classList.remove("text-secondary")
	 			return;
	 		} 
	 		
	 		apartamento.readOnly = true;
	 		apartamento.classList.add("bg-light")
	 		apartamento.classList.add("text-secondary")
	 	}
	 	
	 	//SELECT LOCALIDAD
	 	const localidad = document.getElementById("localidadID");
	 	const localidadID = localidad.getAttribute('data-localidadID');
	 	localidad.value=localidadID;	 
	 	
	 	//SELECT PROVINCIA
	 	const provincia = document.getElementById("provinciaID");
	 	const provinciaID = provincia.getAttribute('data-provinciaID');
	 	provincia.value=provinciaID;
	 	
	 	//SELECT SEXO
	 	const sexo = document.getElementById("sexo");
	 	const sexoID = sexo.getAttribute('data-sexoID');
	 	sexo.value=sexoID;
	 	
	 	//SELECT TIPO DIRECCION
	 	const tipoCasa = document.getElementById("tipoDireccion");
	 	const tipoCasaID = tipoCasa.getAttribute('data-TipoDireccionID');
	 	tipoCasa.value=tipoCasaID;	
    
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
	 	
	 	//VALIDAR FECHA
	 	function validarFecha(input){
	 		const fecha = input.value;
	 	    const arrayFecha = fecha.split("-");
	 	    const añoNacimiento = parseInt(arrayFecha[0]);
	 	    const mesNacimiento = parseInt(arrayFecha[1]) - 1; 
	 	    const diaNacimiento = parseInt(arrayFecha[2]);
	 	    
	 	    const fechaNacimiento = new Date(añoNacimiento, mesNacimiento, diaNacimiento);
	 	    const fechaActual = new Date();
	 	    const añoActual = fechaActual.getFullYear();

	 	    const edad = añoActual - añoNacimiento;

	 	    
	 	    if (
	 	        edad < 18 ||
	 	        (edad === 18 && (fechaActual < new Date(añoActual, mesNacimiento, diaNacimiento)))
	 	    ) {
	 	        input.setCustomValidity('Debe ser mayor de edad');
	 	        input.classList.add("is-invalid");
	 	    } else if (añoNacimiento < 1900) {
	 	        input.setCustomValidity('Fecha inválida');
	 	        input.classList.add("is-invalid");
	 	    } else {
	 	        input.setCustomValidity('');
	 	        input.classList.remove("is-invalid");
	 	        input.classList.add("is-valid");
	 	    }
	 	}
	    
	    var provinciasArray = [];
	    var localidadesArray = [];

	    <% if (request.getAttribute("provincias") != null) {
	        for (Provincia provincia : listaProvincia) { %>
	            provinciasArray.push({ id: "<%= provincia.getIdProvincia() %>", nombre: "<%= provincia.getNombre() %>" });
	        <% }
	    } 
	    
	    if (request.getAttribute("localidades") != null) {
	        for (Localidad localidad : listaLocalidad) { %>
	            localidadesArray.push({ id: "<%= localidad.getIdLocalidad() %>", nombre: "<%= localidad.getNombre() %>", idProvincia: "<%= localidad.getIdProvincia() %>" });
	        <% }
	    } %>
	    
	    //Validar locacion x provincia
	    function seleccionarProvincia() {
	        var provinciaSelect = document.getElementById("provinciaID");
	        var localidadSelect = document.getElementById("localidadID");
	        var selectedProvinciaId = provinciaSelect.value;
		
	        //borramos select de localidades
	        localidadSelect.innerHTML = "";

	        //localidad default
	        var defaultOption = document.createElement("option");
	        defaultOption.value = "";
	        defaultOption.text = "Selecciona una localidad";
	        localidadSelect.appendChild(defaultOption);

	        // localidad s/provincia
	        for (var i = 0; i < localidadesArray.length; i++) {
	            var localidad = localidadesArray[i];
	            if (localidad.idProvincia === selectedProvinciaId) {
	                var option = document.createElement("option");
	                option.value = localidad.id;
	                option.text = localidad.nombre;
	                localidadSelect.appendChild(option);
	            }
	        }
	    }

    </script>
	 
</html>