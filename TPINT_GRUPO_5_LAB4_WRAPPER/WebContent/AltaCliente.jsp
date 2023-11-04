<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="body" tagdir="/WEB-INF/tags" %>

<body:wrapper>


      <!--CONTENT-->
      <div class="col-lg-9 col-md-12 d-flex flex-column justify-content-between">
        <div class="w-100 pt-2">
          <h1 style="margin-bottom:10%">ALTA DE CLIENTE</h1>
        </div>
        <div class="flex-grow-1">
          <!-- CONTENIDO-->
       
		


<form>
    <div class="row">
        <div class="col-md-6">
            <div class="form-group">
                <label for="nombre">Nombre</label>
                <input type="text" class="form-control" id="nombre" placeholder="Ingrese el nombre">
            </div>
            <div class="form-group">
                <label for="dni">DNI</label>
                <input type="text" class="form-control" id="dni" placeholder="Ingrese el DNI">
            </div>
            <div class="form-group">
                <label for="cuil">CUIL</label>
                <input type="text" class="form-control" id="cuil" placeholder="Ingrese el CUIL">
            </div>
            <div class="form-group">
                <label for="nacionalidad">Nacionalidad</label>
                <input type="text" class="form-control" id="nacionalidad" placeholder="Ingrese la nacionalidad">
            </div>
            <div class="form-group">
                <label for="fechaNacimiento">Fecha de Nacimiento</label>
                <input type="text" class="form-control" id="fechaNacimiento" placeholder="Ingrese la fecha de nacimiento">
            </div>
           
        </div>
        <div class="col-md-6">
             <div class="form-group">
                <label for="sexo">Género</label>
                <select class="form-control" id="sexo">
                    <option value="masculino">Masculino</option>
                    <option value="femenino">Femenino</option>
                </select>
            </div>
            <div class="form-group">
                <label for="correo">Correo</label>
                <input type="text" class="form-control" id="correo" placeholder="Ingrese el correo">
            </div>
            <div class="form-group">
                <label for="telefono">Teléfono</label>
                <input type="text" class="form-control" id="telefono" placeholder="Ingrese el teléfono">
            </div>
            <div class="form-group">
                <label for="NombreUsuario">Nombre de Usuario</label>
                <input type="text" class="form-control" id="NombreUsuario" placeholder="Ingrese el nombre de usuario">
            </div>
            <div class="form-group">
                <label for="claveNueva">Contraseña</label>
                <input type="password" class="form-control" id="claveNueva" placeholder="Ingrese la contraseña">
            </div>
        </div>
    </div>
    <div class="text-center" style="margin-top:5%">
        <button type="submit" class="btn btn-primary">Alta de Cliente</button>
    </div>
</form>

			
			
			
			
        </div>
        
      </div>
</body:wrapper>
