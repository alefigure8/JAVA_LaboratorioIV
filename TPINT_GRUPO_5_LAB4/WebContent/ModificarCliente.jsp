<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="body" tagdir="/WEB-INF/tags" %>

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
		      <jsp:include page= "/WEB-INF/Components/menu_cliente.jsp">
		      	<jsp:param name="usuario" value="Ramón Ramirez" />
		      </jsp:include>


      <!--CONTENT-->
      <div class="col-lg-9 col-md-12 d-flex flex-column justify-content-between">
        <div class="w-100 pt-2">
          <h1 style="margin-bottom:10%">MODIFICAR CLIENTE</h1>
        </div>
        <div class="flex-grow-1">
          <!-- CONTENIDO-->
       
			

<form>
    <div class="row">
        <div class="col-md-6">
            <div class="form-group">
                <label for="nombre">Nombre</label>
                <input type="text" class="form-control" id="nombre" value="Nombre del Cliente" readonly>
            </div>
            <div class="form-group">
                <label for="dni">DNI</label>
                <input type="text" class="form-control" id="dni" value="12345678" readonly>
            </div>
            <div class="form-group">
                <label for="cuil">CUIL</label>
                <input type="text" class="form-control" id="cuil" value="20-12345678-9" readonly>
            </div>
            <div class="form-group">
                <label for="nacionalidad">Nacionalidad</label>
                <input type="text" class="form-control" id="nacionalidad" value="Argentino" readonly>
            </div>
            <div class="form-group">
                <label for="fechaNacimiento">Fecha de Nacimiento</label>
                <input type="text" class="form-control" id="fechaNacimiento" value="01/02/1985" readonly>
            </div>
        </div>
        <div class="col-md-6">
          <div class="form-group">
                <label for="NombreUsuario">Nombre de Usuario</label>
                <input type="text" class="form-control" id="NombreUsuario" value="juanperez200" readonly>
            </div>
            <div class="form-group">
                <label for="correo">Correo</label>
                <input type="text" class="form-control" id="correo" value="juan.perez@example.com" readonly>
            </div>
            <div class="form-group">
                <label for="telefono">Teléfono</label>
                <input type="text" class="form-control" id="telefono" value="555-123-4567" readonly>
            </div>
            <div class="form-group">
                <label for="claveVieja">Contraseña Vieja</label>
                <input type="password" class="form-control" id="claveVieja" placeholder="Ingrese la contraseña vieja">
            </div>
            <div class="form-group">
                <label for="claveNueva">Nueva Contraseña</label>
                <input type="password" class="form-control" id="claveNueva" placeholder="Ingrese la nueva contraseña">
            </div>
        </div>
    </div>
    <div class="text-center" style="margin-top:5%">
        <button type="submit" class="btn btn-primary">Modificar Clave</button>
    </div>
</form>

			
			
			
			
        </div>
        
      </div>


	       </div>
	 	<!--FOOTER-->
	    <jsp:include page= "/WEB-INF/Components/footer.html"></jsp:include>
	 </body>
</html>