<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Banco Cinco</title>


	<!-- AGREGAMOS DETALLE DE FUENTES Y ESTILOS -->
	<meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Banco</title>
    <!--FONTAWESOME-->
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
      integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
      crossorigin="anonymous"
      referrerpolicy="no-referrer"
    />
    <!--BOOSTRAP-->
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
      crossorigin="anonymous"
    />
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <!--GOOGLE FONTS-->
    <link
      href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600&display=swap"
      rel="stylesheet"
    />
	
	<!-- AGREGAMOS LINK A CSS -->
	<link rel="stylesheet" type="text/css" href="Styles.css"/>


</head>

<body class="d-flex flex-column">
  

    <!--MAIN-->
    <div class="container-fluid flex-grow-1">
    <div class="row flex-grow-1 m-0">
      <!--SIDEBAR-->
      <div class="sidebar col-lg-3 col-md-12 bg-light">
        <div class="w-100 d-flex justify-content-center">
          <span class="fw-bold mt-4 mb-4 fs-5 p-2 text-white bg-main"
            >Banco Cinco</span
          >
        </div>
        <div class="d-flex align-items-center ms-3 mb-4">
          <div class="me-2">
            <i
              class="fa-regular fa-user opacity-50 border border-opacity-50 border-1 border-black rounded-circle p-1"
            ></i>
          </div>
          <span class="fw-lighter">Nombre Usuario</span>
        </div>
        <ul class="list-group border-0">
          <li
            class="list-group-item border-0 border-bottom border-secondary bg-transparent">
            
          </li>
          <li class="list-group-item border-0 bg-transparent"><a  href=#>Inicio</a></li>
          <li class="list-group-item border-0 bg-transparent"><a  href=#>Transferencia</a></li>
          <li class="list-group-item border-0 bg-transparent"><a  href=#>Prestamo</a></li>
          <li class="list-group-item border-0 bg-transparent mt-4"><a  href=#>Cerrar Sesión</a></li>
        </ul>
      </div>

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
    </div>
</div>
    <!--FOOTER-->
    <footer class="footer d-flex align-items-center">
	    <div class="w-100 d-flex justify-content-center">
	        <p class="fw-bold block">EQUIPO 5 - LABORATORIO IV UTN - Copyright © 2023 Todos los derechos reservados</p>
	    </div>
	</footer>



    
  </body>
</html>