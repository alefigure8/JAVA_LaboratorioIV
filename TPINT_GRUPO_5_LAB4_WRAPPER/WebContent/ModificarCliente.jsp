<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="body" tagdir="/WEB-INF/tags" %>

<body:wrapper>


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
                <label for="telefono">Tel�fono</label>
                <input type="text" class="form-control" id="telefono" value="555-123-4567" readonly>
            </div>
            <div class="form-group">
                <label for="claveVieja">Contrase�a Vieja</label>
                <input type="password" class="form-control" id="claveVieja" placeholder="Ingrese la contrase�a vieja">
            </div>
            <div class="form-group">
                <label for="claveNueva">Nueva Contrase�a</label>
                <input type="password" class="form-control" id="claveNueva" placeholder="Ingrese la nueva contrase�a">
            </div>
        </div>
    </div>
    <div class="text-center" style="margin-top:5%">
        <button type="submit" class="btn btn-primary">Modificar Clave</button>
    </div>
</form>

			
			
			
			
        </div>
        
      </div>
</body:wrapper>