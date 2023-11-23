<%@page import="entidad.TipoAcceso"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!-- AUTENTICACION -->
	<jsp:include page="/WEB-INF/Components/autenticacion/autenticacion.jsp"> 
	<jsp:param name="TipoUsuarioPagina" value="<%=TipoAcceso.Administrador%>" />
	</jsp:include>
<!-- FIN AUTENTICACION -->

<%@page import="entidad.Cliente"%>
<%@page import="entidad.Provincia"%>
<%@page import="entidad.Localidad"%>
<%@page import="negocioDaoImp.ProvinciaNegocioDaoImp"%>
<%@page import="negocioDaoImp.LocalidadNegocioDaoImp"%>
<%@page import="java.util.List"%>
<%@page import="entidad.TipoDireccion"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.format.DateTimeFormatter"%>


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
	<!--FIN HEAD -->
	<body class="d-flex flex-column">
	    <div class="row flex-grow-1 m-0">
	      <!--SIDEBAR-->
	      <jsp:include page= "/WEB-INF/Components/menu.jsp">
	      	<jsp:param name="usuario" value="Ramón Ramirez" />
      	</jsp:include>
	      <!--MAIN-->
	      <div class="col-lg-9 col-md-12 d-flex flex-column justify-content-between">
	        <div class="w-100 pt-2">
	          <h1 id="tituloCliente" style="margin-bottom:5%"> <i class="fas fa-user-plus me-2"></i>ALTA DE CLIENTE</h1>
	        </div>
	        <div class="flex-grow-1">
	          <!-- CONTENIDO-->
	          
	          <%if(request.getAttribute("clienteAmodificar")!=null && request.getAttribute("clienteAgregado")==null){
	        	  Cliente clienteAmodificar = (Cliente) request.getAttribute("clienteAmodificar");
	        	  if (clienteAmodificar != null) {
	        	      System.out.println("Cliente: " + clienteAmodificar.getDireccion().toString()); // verificar cliente
	        	      
	        	  }
	        	  
				%>
	          
	          <form action="ServletAltaCliente" method="post"  onsubmit="return validarContraseñas()" onsubmit="return confirm('¿Está seguro que desea realizar el alta?')">
				    <div class="row justify-content-center">
				        <div class="col-md-3">
				            <div class="form-group">
				                <label for="nombre">Nombre</label>
				               
				                <input type="text" class="form-control" name="nombre" id="nombre" placeholder="Ingrese el nombre"  value="<%= clienteAmodificar.getNombre() %>" required oninput="this.value = this.value.replace(/[^A-Za-z\s]/g, ''); this.value = this.value.substring(0, 20);validateInput(this, 1);">
				            </div>
				            <div class="form-group">
				                <label for="apellido">Apellido</label>
				                <input type="text" class="form-control" name="apellido" id="apellido" placeholder="Ingrese el apellido" value="<%= clienteAmodificar.getApellido() %>"  required oninput="this.value = this.value.replace(/[^A-Za-z\s]/g, ''); this.value = this.value.substring(0, 20);validateInput(this, 1);">
				            </div>
				            <div class="form-group">
				                <label for="dni">DNI</label>
				                <input type="text" class="form-control" name="dni" id="dni" placeholder="Ingrese el DNI" value="<%= clienteAmodificar.getDni() %>"   required oninput="this.value = this.value.replace(/[^0-9]/g, '');this.value = this.value.substring(0, 8);validateInput(this, 1);">
				            	    
				            	    
				            	    <small id="dniError" class="text-danger" style="<% if ((String)request.getAttribute("error") != null && !((String)request.getAttribute("error")).isEmpty()) { %> display: block; <% } else { %> display: none; <% } %>"></small>
				            </div>
				            <div class="form-group">
				                <label for="cuil">CUIL</label>
				                <input type="text" class="form-control" name="cuil" id="cuil" placeholder="Ingrese el CUIL" value="<%= clienteAmodificar.getCuil() %>"  required oninput="this.value = this.value.replace(/[^0-9]/g, '');this.value = this.value.substring(0, 11);validateInput(this, 1);">
				            </div>
				            <div class="form-group">
				                <label for="nacionalidad">Nacionalidad</label>
				                <input type="text" class="form-control" name="nacionalidad" id="nacionalidad" placeholder="Ingrese la nacionalidad"  value="<%= clienteAmodificar.getNacionalidad() %>" required oninput="this.value = this.value.replace(/[^A-Za-z\s]/g, ''); this.value = this.value.substring(0, 20);validateInput(this, 1);">
				            </div>
				            
				             <div class="form-group">
				                <label for="sexo">Género</label>
				                <select class="form-control" id="sexo" required name="sexo">
							        <option value="M" <%= clienteAmodificar.getSexo().equals("M") ? "selected" : "" %>>Masculino</option>
							        <option value="F" <%= clienteAmodificar.getSexo().equals("F") ? "selected" : "" %>>Femenino</option>
							    </select>
				            </div>
				            
				             <div class="form-group">
				                <label for="correo" >Correo</label>
				                <input type="email" class="form-control" id="correo" placeholder="Ingrese el correo" name="correo"  value="<%= clienteAmodificar.getEmail() %>" required oninput="this.value =  this.value = this.value.substring(0, 30);validateInput(this, 1);">
				            		 <small id="correoError" class="text-danger" style="<% if ((String)request.getAttribute("errorCorreo") != null && !((String)request.getAttribute("errorCorreo")).isEmpty()) { %> display: block; <% } else { %> display: none; <% } %>"></small>
				            	
				            
				            </div>
				            <div class="form-group">
				                <label for="telefono">Teléfono</label>
				                <input type="text" class="form-control" id="telefono" placeholder="Ingrese el teléfono" name="telefono" required  value="<%= clienteAmodificar.getTelefono() %>"  oninput="this.value = this.value.replace(/[^0-9]/g, '');this.value = this.value.substring(0, 10);validateInput(this, 8);">
				            </div>
				            
				            <div class="form-group">
				                <label for="fechaNacimiento">Fecha de Nacimiento</label>
				                <% LocalDate fechaNacimiento = clienteAmodificar.getNacimiento();

				                String fechaNacimientoStr = (fechaNacimiento != null) ? fechaNacimiento.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : ""; %>
				                <input type="date" class="form-control" name="fechaNacimiento" id="fechaNacimiento" placeholder="Ingrese la fecha de nacimiento" value="<%= fechaNacimientoStr %>" required onblur="validarFecha(this);">
				            </div>
				           
				        </div>
				        
				        
				        
				        <div class="col-md-3">
				            
				          
				            
				          
						
						  <!-- Provincia  y localidad -->
				           <div class="form-group">
					            <label for="provincia">Provincia</label>
					            <select class="form-control" id="provincia" name="provincia" required onchange="seleccionarProvincia()">
					                <option value="" disabled>Seleccione una provincia</option>
					                <% 
					                Provincia provinciaSeleccionada = clienteAmodificar.getDireccion().getProvincia();
					                List<Provincia> provincias = (List<Provincia>)session.getAttribute("provincias");

					                if (provincias != null) {
					                    for (Provincia provincia : provincias) {
					                        String selected = "";
					                        if (provinciaSeleccionada != null && provinciaSeleccionada.getIdProvincia() == provincia.getIdProvincia()) {
					                            selected = "selected";
					                        }
					                        %>
					                        <option value="<%= provincia.getIdProvincia() %>" <%= selected %>> <%= provincia.getNombre() %> </option>
					                    <% } 
					                } %>
					            </select>
					        </div>
					
					        <div class="form-group">
					            <label for="localidad">Localidad</label>
					            <select class="form-control" id="localidad" name="localidad" required>
					                <option value="" disabled>Seleccione una localidad</option>
					                <% 
					                List<Localidad> localidades = (List<Localidad>) session.getAttribute("localidades");
					                if (localidades != null) {
					                    for (Localidad localidad : localidades) {
					                        String selected = "";
					                        if (clienteAmodificar.getDireccion().getLocalidad() != null && clienteAmodificar.getDireccion().getLocalidad().getIdLocalidad() == localidad.getIdLocalidad()) {
					                            selected = "selected";
					                        }
					                        %>
					                        <option value="<%= localidad.getIdLocalidad() %>" <%= selected %>> <%= localidad.getNombre() %> </option>
					                    <% } 
					                } %>
					            </select>
					        </div>


							
						

						    <!-- Direccion -->
						    <div class="form-group">
						        <label for="codigoPostal">Código Postal</label>
						        <input type="text" class="form-control" name="codigoPostal" id="codigoPostal" value="<%= clienteAmodificar.getDireccion().getCodigoPostal() %>"   placeholder="Ingrese el código postal" required oninput="this.value = this.value.replace(/[^0-9]/g, '');this.value = this.value.substring(0, 6);validateInput(this, 1);">
						    </div>
						    <div class="form-group">
						        <label for="calle">Calle</label>
						        <input type="text" class="form-control" name="calle" id="calle" value="<%= clienteAmodificar.getDireccion().getCalle() %>"  placeholder="Ingrese la calle" required oninput="this.value = this.value.replace(/[^A-Za-z0-9\s]/g, ''); this.value = this.value.substring(0, 20);validateInput(this, 1);">
						    </div>
						    <div class="form-group">
						        <label for="numero">Número</label>
						        <input type="text"  class="form-control" name="numero" id="numero" value="<%= clienteAmodificar.getDireccion().getNumero()%>"  placeholder="Ingrese el número" oninput="this.value = this.value.replace(/[^0-9]/g, '');this.value = this.value.substring(0, 10);validateInput(this, 1);">
						    </div>
						    
						    <div class="form-group">
							    <label for="tipoDireccion">Tipo de Dirección</label>
							    <select class="form-control" id="tipoDireccion" name="tipoDireccion" required  onchange="habilitarInput()">
							        <% 
							        for (TipoDireccion tipo : TipoDireccion.values()) {
							            String selected = "";
							            if (clienteAmodificar.getDireccion().getTipoDireccion() == tipo) {
							                selected = "selected";
							            }
							        %>
							        <option value="<%= tipo.name() %>" <%= selected %>><%= tipo.name() %></option>
							        <% } %>
							    </select>
							</div>

						    
						    <div class="form-group">
							    <label for="numeroDepartamento">Número de Departamento</label>
							    <%
							        String readonly = "";
							        String clases = "";
							        if (clienteAmodificar.getDireccion().getTipoDireccion().equals(TipoDireccion.Casa)) {
							            readonly = "readonly";
							            clases = "bg-light text-secondary";
							        }
							    %>
							    <input type="text" class="form-control <%= clases %>" name="numeroDepartamento" id="numeroDepartamento" value="<%= clienteAmodificar.getDireccion().getNumeroDepartamento() %>" placeholder="Ingrese el número de departamento" oninput="this.value = this.value.replace(/[^A-Za-z0-9\s]/g, ''); this.value = this.value.substring(0, 20);validateInput(this, 1);" <%= readonly %> >
							</div>

				            
				            
				        </div>
				        
				         <div class="col-md-3">
				         		<div class="form-group">
				                <label for="usuario">Nombre de Usuario</label>
				                <input type="text" class="form-control" id="usuario" placeholder="Ingrese el nombre de usuario" value="<%= clienteAmodificar.getUsuario() %>"  name="usuario" required oninput="this.value = this.value.replace(/[^A-Za-z0-9\s]/g, ''); this.value = this.value.substring(0, 20);validateInput(this, 1);">
				           		<small id="usuarioError" class="text-danger" style="<% if ((String)request.getAttribute("errorUsuario") != null && !((String)request.getAttribute("errorUsuario")).isEmpty()) { %> display: block; <% } else { %> display: none; <% } %>"></small>
				           
				           
				            </div>
				            <div class="form-group">
				                <label for="contraseña">Contraseña</label>
				                <input type="password" class="form-control" id="contraseña" placeholder="Ingrese la contraseña"  value="<%= clienteAmodificar.getContrasenia() %>" name="contraseña"  required oninput="this.value = this.value.replace(/[^A-Za-z0-9\s]/g, ''); this.value = this.value.substring(0, 20);validateInput(this, 1);">
				            </div>
				            
				            <div class="form-group">
				                <label for="confirmarContraseña">Re ingrese contraseña</label>
				                <input type="password" class="form-control" id="confirmarContraseña" placeholder="Reingrese la contraseña" value="<%= clienteAmodificar.getContrasenia() %>" name="confirmarContraseña" required oninput="this.value = this.value.replace(/[^A-Za-z0-9\s]/g, ''); this.value = this.value.substring(0, 20);validateInput(this, 1);">
				            </div>
				            
				            <div class="d-flex justify-content-end" style="margin-top:5%">
						        <button type="submit" class="btn btn-primary btnEnviar " name="btnAltaCliente">Alta de Cliente</button>
						    </div>
						    
						   
						    
						    <div class="d-flex justify-content-end" style="margin-top:5%">
							    <a href="ServletListarClientes?obtener=true" class="btn btn-primary btnEnviar">Ir a Listado</a>
							</div>
							
							<div class="d-flex justify-content-end" style="margin-top:5%">
							    <a href="PerfilBanco.jsp" class="btn btn-success ">Inicio</a>
							</div>
						    
				         </div>
				        
				    </div>
				    
				</form>
				
	          <%
				} if(request.getAttribute("clienteAmodificar")==null && request.getAttribute("clienteAgregado")==null) {
				%>
	          
	          
	          
	          
				<form action="ServletAltaCliente" method="post" onsubmit="return validarContraseñas()" onsubmit="return confirm('¿Está seguro que desea realizar el alta?')" >
				    <div class="row justify-content-center">
				        <div class="col-md-3">
				            <div class="form-group">
				                <label for="nombre">Nombre</label>
				               
				                <input type="text" class="form-control" name="nombre" id="nombre" placeholder="Ingrese el nombre" required oninput="this.value = this.value.replace(/[^A-Za-z\s]/g, ''); this.value = this.value.substring(0, 20);validateInput(this, 1);">
				            </div>
				            <div class="form-group">
				                <label for="apellido">Apellido</label>
				                <input type="text" class="form-control" name="apellido" id="apellido" placeholder="Ingrese el apellido" required oninput="this.value = this.value.replace(/[^A-Za-z\s]/g, ''); this.value = this.value.substring(0, 20);validateInput(this, 1);">
				            </div>
				            <div class="form-group">
				                <label for="dni">DNI</label>
				                <input type="text" class="form-control" name="dni" id="dni" placeholder="Ingrese el DNI" required oninput="this.value = this.value.replace(/[^0-9]/g, '');this.value = this.value.substring(0, 8);validateInput(this, 1);">
				            	
				            </div>
				            <div class="form-group">
				                <label for="cuil">CUIL</label>
				                <input type="text" class="form-control" name="cuil" id="cuil" placeholder="Ingrese el CUIL" required oninput="this.value = this.value.replace(/[^0-9]/g, '');this.value = this.value.substring(0, 11);validateInput(this, 1);">
				            </div>
				            <div class="form-group">
				                <label for="nacionalidad">Nacionalidad</label>
				                <input type="text" class="form-control" name="nacionalidad" id="nacionalidad" placeholder="Ingrese la nacionalidad" required oninput="this.value = this.value.replace(/[^A-Za-z\s]/g, ''); this.value = this.value.substring(0, 20);validateInput(this, 1);">
				            </div>
				            
				             <div class="form-group">
				                <label for="sexo">Género</label>
				                <select class="form-control" id="sexo" required name="sexo">
				                    <option value="M">Masculino</option>
				                    <option value="F">Femenino</option>
				                </select>
				            </div>
				            
				             <div class="form-group">
				                <label for="correo" >Correo</label>
				                <input type="email" class="form-control" id="correo" placeholder="Ingrese el correo" name="correo" required oninput="this.value =  this.value = this.value.substring(0, 30);validateInput(this, 1);">
				            </div>
				            <div class="form-group">
				                <label for="telefono">Teléfono</label>
				                <input type="text" class="form-control" id="telefono" placeholder="Ingrese el teléfono" name="telefono" required oninput="this.value = this.value.replace(/[^0-9]/g, '');this.value = this.value.substring(0, 10);validateInput(this, 8);">
				            </div>
				            
				            <div class="form-group">
				                <label for="fechaNacimiento">Fecha de Nacimiento</label>
				                <input type="date" class="form-control" name="fechaNacimiento" id="fechaNacimiento" placeholder="Ingrese la fecha de nacimiento" required onblur="validarFecha(this);">
				            </div>
				           
				        </div>
				        
				        
				        
				        <div class="col-md-3">
				            
				            
				            <!-- Provincia  y localidad -->
				          <div class="form-group">
						    <label for="provincia">Provincia</label>
						    <select class="form-control" id="provincia" name="provincia" required onchange="seleccionarProvincia()">
						        <option value="" disabled selected>Seleccione una provincia</option>
						        <% 
						        List<Provincia> provincias = (List<Provincia>) session.getAttribute("provincias");
						        if (provincias != null) {
						            for (Provincia provincia : provincias) {
						                %>
						                <option value="<%= provincia.getIdProvincia() %>"> <%= provincia.getNombre() %> </option>
						            <% } } %>
						    </select>
						</div>


							
							<div class="form-group">
							    <label for="localidad">Localidad</label>
							    <select class="form-control" id="localidad" name="localidad" required>
							    	<option value="" disabled selected>Seleccione una localidad</option>
							        
							    </select>
							</div>
						    
						    <!-- Direccion -->
						    <div class="form-group">
						        <label for="codigoPostal">Código Postal</label>
						        <input type="text" class="form-control" name="codigoPostal" id="codigoPostal" placeholder="Ingrese el código postal" required oninput="this.value = this.value.replace(/[^0-9]/g, '');this.value = this.value.substring(0, 6);validateInput(this, 1);">
						    </div>
						    <div class="form-group">
						        <label for="calle">Calle</label>
						        <input type="text" class="form-control" name="calle" id="calle" placeholder="Ingrese la calle" required oninput="this.value = this.value.replace(/[^A-Za-z0-9\s]/g, ''); this.value = this.value.substring(0, 20);validateInput(this, 1);">
						    </div>
						    <div class="form-group">
						        <label for="numero">Número</label>
						        <input type="text"  class="form-control" name="numero" id="numero" placeholder="Ingrese el número" oninput="this.value = this.value.replace(/[^0-9]/g, '');this.value = this.value.substring(0, 10);validateInput(this, 1);">
						    </div>
						    
						    
				            
				            <div class="form-group">
							    <label for="tipoDireccion">Tipo de Dirección</label>
							    <select class="form-control" id="tipoDireccion" name="tipoDireccion" required  onchange="habilitarInput()" >
							    
							        <% 
							        for (TipoDireccion tipo : TipoDireccion.values()) { %>
							            <option value="<%= tipo.name() %>"><%= tipo.name() %></option>
							        <% } %>
							    </select>
							</div>

						    
						    <div class="form-group">
						        <label for="numeroDepartamento">Número de Departamento</label>
						        <input type="text" class="form-control bg-light text-secondary" name="numeroDepartamento" id="numeroDepartamento" placeholder="Ingrese el número de departamento" oninput="this.value = this.value.replace(/[^A-Za-z0-9\s]/g, ''); this.value = this.value.substring(0, 20);" readonly  >
						    </div>
				            
				            
				            
				        </div>
				        
				         <div class="col-md-3">
				         		<div class="form-group">
				                <label for="usuario">Nombre de Usuario</label>
				                <input type="text" class="form-control" id="usuario" placeholder="Ingrese el nombre de usuario" name="usuario" required oninput="this.value = this.value.replace(/[^A-Za-z0-9\s]/g, ''); this.value = this.value.substring(0, 20);validateInput(this, 1);">
				            </div>
				            <div class="form-group">
				                <label for="contraseña">Contraseña</label>
				                <input type="password" class="form-control" id="contraseña" placeholder="Ingrese la contraseña" name="contraseña"  required oninput="this.value = this.value.replace(/[^A-Za-z0-9\s]/g, ''); this.value = this.value.substring(0, 20);validateInput(this, 1);">
				            </div>
				            
				            <div class="form-group">
				                <label for="confirmarContraseña">Re ingrese contraseña</label>
				                <input type="password" class="form-control" id="confirmarContraseña" placeholder="Reingrese la contraseña" name="confirmarContraseña" required oninput="this.value = this.value.replace(/[^A-Za-z0-9\s]/g, ''); this.value = this.value.substring(0, 20);validateInput(this, 1);">
				            </div>
				            
				            <div class="d-flex justify-content-end" style="margin-top:5%">
						        <button type="submit" class="btn btn-primary btnEnviar " name="btnAltaCliente">Alta de cliente</button>
						    </div>
						    
						   
						    <div class="d-flex justify-content-end" style="margin-top:5%">
							    <a href="ServletListarClientes?obtener=true" class="btn btn-primary btnEnviar">Ir a Listado</a>
							</div>
							
							<div class="d-flex justify-content-end" style="margin-top:5%">
							    <a href="PerfilBanco.jsp" class="btn btn-success ">Inicio</a>
							</div>
				         </div>
				        
				    </div>
				    
				</form>
				
				<%
				
				} if(request.getAttribute("clienteAgregado")!=null) {
					 Cliente clienteAmodificar = (Cliente) request.getAttribute("clienteAgregado");
					// eliminamos sessions
					request.removeAttribute("clienteAmodificar");
					request.removeAttribute("clienteAgregado");
				%>
					
					<script>
					        document.getElementById('tituloCliente').innerText = "CLIENTE AGREGADO EXITOSAMENTE";
					</script>
					
					<div class="row justify-content-center">
					    <!-- Columna de Datos Personales -->
					    <div class="col-md-3">
					        <h3>Datos Personales</h3>
					        <table class="table">
					            <tbody>
					                <tr>
					                    <th>Nombre</th>
					                    <td><%= clienteAmodificar.getNombre() %></td>
					                </tr>
					                <tr>
					                    <th>Apellido</th>
					                    <td><%= clienteAmodificar.getApellido() %></td>
					                </tr>
					                <tr>
					                    <th>Fecha de Alta</th>
					                    <td><%= clienteAmodificar.getFechaAlta() %></td>
					                </tr>
					                <tr>
					                    <th>DNI</th>
					                    <td><%= clienteAmodificar.getDni() %></td>
					                </tr>
					                <tr>
					                    <th>CUIL</th>
					                    <td><%= clienteAmodificar.getCuil() %></td>
					                </tr>
					                <tr>
					                    <th>Nacionalidad</th>
					                    <td><%= clienteAmodificar.getNacionalidad() %></td>
					                </tr>
					                <tr>
					                    <th>Género</th>
					                    <td><%= clienteAmodificar.getSexo() %></td>
					                </tr>
					                <tr>
					                    <th>Correo</th>
					                    <td><%= clienteAmodificar.getEmail() %></td>
					                </tr>
					                <tr>
					                    <th>Teléfono</th>
					                    <td><%= clienteAmodificar.getTelefono() %></td>
					                </tr>
					                <tr>
					                    <th>Fecha de Nacimiento</th>
					                    <td><%= clienteAmodificar.getNacimiento() %></td>
					                </tr>
					            </tbody>
					        </table>
					    </div>
					    
					    <!-- Columna de Datos de Dirección -->
					    <div class="col-md-3">
					        <h3>Datos de Dirección</h3>
					        <table class="table">
					            <tbody>
					                <tr>
					                    <th>Provincia</th>
					                    <td><%= clienteAmodificar.getDireccion().getProvincia().getNombre() %></td>
					                </tr>
					                <tr>
					                    <th>Localidad</th>
					                    <td><%= clienteAmodificar.getDireccion().getLocalidad().getNombre() %></td>
					                </tr>
					                <tr>
					                    <th>Código Postal</th>
					                    <td><%= clienteAmodificar.getDireccion().getCodigoPostal() %></td>
					                </tr>
					                <tr>
					                    <th>Calle</th>
					                    <td><%= clienteAmodificar.getDireccion().getCalle() %></td>
					                </tr>
					                <tr>
					                    <th>Número</th>
					                    <td><%= clienteAmodificar.getDireccion().getNumero() %></td>
					                </tr>
					                 <tr>
					                    <th>Tipo de Dirección</th>
					                    <td><%= clienteAmodificar.getDireccion().getTipoDireccion() %></td>
					                </tr>
					                <%if (clienteAmodificar.getDireccion().getTipoDireccion().equals(TipoDireccion.Departamento)){ %>
					                <tr>
					                    <th>Número de Departamento</th>
					                    <td><%= clienteAmodificar.getDireccion().getNumeroDepartamento() %></td>
					                </tr>
					               <%} %>
					            </tbody>
					        </table>
					    </div>
					    
					    
					    <div class="col-md-3">
					        <h3>Datos de usuario</h3>
					        <table class="table">
					           
					                <tbody>
						                <tr>
						                    <th>Usuario</th>
						                    <td><%= clienteAmodificar.getUsuario() %></td>
						                </tr>
						                <tr>
						                    <th>Contraseña</th>
						                    <td><%= clienteAmodificar.getContrasenia() %></td>
						                </tr>
						            </tbody>
					           
					        </table>
					    </div>
					</div>

					
					<div style="text-align: center;">
					    <a href="ServletListarClientes?obtener=true&filtro=Activos" class="btn btn-primary btnEnviar">Ir a Listado</a>
					</div>
					
					<div class="mt-4" style="text-align: center;">
					    <a href="PerfilBanco.jsp" class="btn btn-success  ">Inicio</a>
					</div>
					
					
				<%} %>
					
        	</div>
     	 </div>
 	</div>
	<!--FIN MAIN-->
 	<!--FOOTER-->
    <jsp:include page= "/WEB-INF/Components/footer.html"></jsp:include>
    
	 <script>
	 //VALIDAR CONTRASEÑA
		function validarContraseñas() {
		    var password = document.getElementById("contraseña").value;
		    var confirmPassword = document.getElementById("confirmarContraseña").value;
		
		    if (password !== confirmPassword) {
		        alert("Las contraseñas no coinciden");
		        return false; // detiene el envio del formulario 
		    }
		    return true; // envia del formulario
		}
	
	///ERRORES
	    var errorDni = "<%= (String) request.getAttribute("error") %>";
	    var dniError = document.getElementById("dniError");
	
	    if (errorDni) {
	        dniError.innerText = errorDni;
	    }
	
	    
	    var errorUsuario = "<%= (String) request.getAttribute("errorUsuario") %>";
	    var usuarioError = document.getElementById("usuarioError");
	
	    if (errorUsuario) {
	        usuarioError.innerText = errorUsuario;
	    }
	    
	    
	    var errorCorreo= "<%= (String)request.getAttribute("errorCorreo")%>";
	    var correoError= document.getElementById("correoError");
	    if (errorCorreo){
	    	correoError.innerText=errorCorreo;
	    }
	    
	    
	   
    //VALIDACIONES CARACTERES
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
	</script>
	<script>
    //PROVINCIAS Y LOCALIDADES
    
    var provinciasArray = [];
    var localidadesArray = [];

    <% if (session.getAttribute("provincias") != null) {
        List<Provincia> provincias = (List<Provincia>) session.getAttribute("provincias");
        for (Provincia provincia : provincias) { %>
            provinciasArray.push({ id: "<%= provincia.getIdProvincia() %>", nombre: "<%= provincia.getNombre() %>" });
        <% }
    } %>

    <% if (session.getAttribute("localidades") != null) {
        List<Localidad> localidades = (List<Localidad>) session.getAttribute("localidades");
        for (Localidad localidad : localidades) { %>
            localidadesArray.push({ id: "<%= localidad.getIdLocalidad() %>", nombre: "<%= localidad.getNombre() %>", idProvincia: "<%= localidad.getIdProvincia() %>" });
        <% }
        
        
    } %>

    function seleccionarProvincia() {
    	
        var provinciaSelect = document.getElementById("provincia");
        var localidadSelect = document.getElementById("localidad");
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
     
   <script>
    
  //VALIDAR FECHA					

	 	function validarFecha(input) {
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

	 
	</script>
	
    
    <script>
	    function habilitarInput() {
	        var tipoDireccion = document.getElementById("tipoDireccion");
	        var numeroDepartamentoInput = document.getElementById("numeroDepartamento");
	
	        if (tipoDireccion.value === "Departamento") {
	            
	            numeroDepartamentoInput.readOnly = false;
	            numeroDepartamentoInput.classList.remove("bg-light")
	 			numeroDepartamentoInput.classList.remove("text-secondary")
	 			return;
	        } else {
	            
	            numeroDepartamentoInput.value = "";
	            numeroDepartamentoInput.readOnly = true;
	            numeroDepartamentoInput.classList.add("bg-light")
		 		numeroDepartamentoInput.classList.add("text-secondary")
	        }
	    }
	</script>
    
    
 </body>
</html>
