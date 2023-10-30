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
	
	<!-- TABLA PAGINADA -->
	<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
	
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
	<script type="text/javascript" charset="utf8"
		src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function() {
			$('#table_id').DataTable();
		});
	</script>
	

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
          <h1>Listado Clientes</h1>
        </div>
        <div class="flex-grow-1">
          <!-- CONTENIDO-->
       
			

<!-- Cuadro para detalles de cuotas -->
		<div>
		    
		    <table id="table_id" class="table display text-center">
		        <thead>
		            <tr>
		                <th>Nombre de Usuario</th>
		                <th>Nombre </th>
		                <th>DNI</th>
		                <th>CUIL</th>
		                <th>Nacionalidad</th>
		                <th>Fecha de Nacimiento</th>
		                <th>Correo</th>
		                <th>Telefono</th>
		                <th>Modificar Clave</th>
		                <th>Eliminar Cliente</th>
		            </tr>
		        </thead>
		        <tbody>
		             <tr>
		             <td>JuanPerez200</td>
            <td>Juan Pérez</td>
            <td>12345678</td>
            <td>20-12345678-9</td>
            <td>Argentino</td>
            <td>01/02/1985</td>
            <td>juan.perez@example.com</td>
            <td>555-123-4567</td>
            <td><button>Modificar</button></td>
            <td><button>Eliminar</button></td>
        </tr>
        <tr>
        <td>MARIA200</td>
            <td>Maria López</td>
            <td>98765432</td>
            <td>27-98765432-4</td>
            <td>Mexicana</td>
            <td>15/07/1990</td>
            <td>maria.lopez@example.com</td>
            <td>555-987-6543</td>
            <td><button>Modificar</button></td>
            <td><button>Eliminar</button></td>
        </tr>
        <tr>
        <td>CARLO1000</td>
            <td>Carlos Rodríguez</td>
            <td>55443322</td>
            <td>35-55443322-7</td>
            <td>Español</td>
            <td>10/11/1980</td>
            <td>carlos.rodriguez@example.com</td>
            <td>555-554-4332</td>
            <td><button>Modificar</button></td>
            <td><button>Eliminar</button></td>
        </tr>
        <tr>
        <td>LARUA200</td>
            <td>Laura Smith</td>
            <td>65432123</td>
            <td>42-65432123-8</td>
            <td>Estadounidense</td>
            <td>25/05/1995</td>
            <td>laura.smith@example.com</td>
            <td>555-654-3212</td>
            <td><button>Modificar</button></td>
            <td><button>Eliminar</button></td>
        </tr>
        <tr>
        <td>ALI0</td>
            <td>Mohammed Ali</td>
            <td>11223344</td>
            <td>68-11223344-3</td>
            <td>Egipcio</td>
            <td>03/08/1988</td>
            <td>mohammed.ali@example.com</td>
            <td>555-112-2334</td>
            <td><button>Modificar</button></td>
            <td><button>Eliminar</button></td>
        </tr>
        <tr>
        <td>078iiuuy0</td>
            <td>Sophie Dupont</td>
            <td>78901234</td>
            <td>52-78901234-1</td>
            <td>Francés</td>
            <td>12/09/1993</td>
            <td>sophie.dupont@example.com</td>
            <td>555-789-0123</td>
            <td><button>Modificar</button></td>
            <td><button>Eliminar</button></td>
        </tr>
		        </tbody>
		    </table>
		</div>


			
			
			
			
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