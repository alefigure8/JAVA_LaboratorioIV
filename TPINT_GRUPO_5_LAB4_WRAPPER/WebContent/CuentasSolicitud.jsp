<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="body" tagdir="/WEB-INF/tags" %>

<body:wrapper>

      <!--CONTENT-->
      <div class="col-lg-9 col-md-12 d-flex flex-column justify-content-between">
        <div class="w-100 pt-2">
          <h1>SOLICITUD DE CUENTA</h1>
        </div>
        <div class="flex-grow-1">
          <!-- CONTENIDO-->
       
			
			<form class="container mt-4 d-flex justify-content-center align-items-start" action="#" method="post">
			    <div class="form-group col-md-4 d-flex flex-column">
			        <label for="tipoCuenta">Seleccione el tipo de cuenta a solicitar:</label>
			        <div class="d-flex">
			            <select class="form-control mt-2 me-2" name="tipoCuenta" id="tipoCuenta">
			                <option>Caja de Ahorro</option>
			                <option>Cuenta Corriente</option>
			            </select>
			            <input type="submit" class="btn btn-primary btnEnviar" name="btnEnviarSolicitudCuenta" value="Enviar solicitud">
			        </div>
			    </div>
			</form>
			
        </div>
        
      </div>
 </body:wrapper>