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
          <li class="list-group-item border-0 bg-transparent mt-4"><a  href=#>Cerrar Sesi�n</a></li>
        </ul>
      </div>

      <!--CONTENT-->
      <div class="col-lg-9 col-md-12 d-flex flex-column justify-content-between">
        <div class="w-100 pt-2">
          <h1>MIS PRESTAMOS</h1>
        </div>
        <div class="flex-grow-1">
          <!-- CONTENIDO-->
       
			<div class="container mt-4">
			   
			   <!-- SI USAMOS PAGINACION CON JQUERY --> <!--   --> 
			   <table id="table_id" class="table display text-center">
			   <!-- <table class="table"> -->
			        <thead>
			            <tr>
			                <th>Nro. Prestamo</th>
			                <th>Fecha prestamo</th>
			                <th>Estado</th>
			                <th>Saldado</th>
			                <th>Importe solicitado</th>
			                <th>Importe con interes</th>
			                <th>Acci�n</th>
			            </tr>
			        </thead>
			        <tbody>
			            <!-- Ejemplo de prestamo pagado -->
			            <tr>
			                <td>001</td>
			                <td>01/06/2018</td>
			                <td>Aprobado</td>
			                <td>Si</td>
			                <td>$50.000</td>
			                 <td>$100.000</td>
			                <td>
			                    <input type="submit" class="btn btn-primary btnEnviar" name="btnVerPrestamo" value="Detalle">
			                </td>
			            </tr>
			            <!-- Ejemplo de prestamo impago -->
			            <tr>
			                <td>002</td>
			                <td>01/01/2023</td>
			                <td>Aprobado</td>
			                <td>No</td>
			                <td>$75.000</td>
			                <td>$150.000</td>
			                <td>
			                    <input type="submit" class="btn btn-primary btnEnviar" name="btnVerPrestamo" value="Detalle">
			                </td>
			            </tr>
			             
			            <tr>
				            <td>003</td>
				            <td>03/03/2023</td>
				            <td>Aprobado</td>
				            <td>Si</td>
				            <td>$30.000</td>
				            <td>$60.000</td>
				            <td>
				                <input type="submit" class="btn btn-primary btnEnviar" name="btnVerPrestamo" value="Detalle">
				            </td>
				        </tr>
							<tr>
				            <td>004</td>
				            <td>08/12/2022</td>
				            <td>Aprobado</td>
				            <td>Si</td>
				            <td>$85.000</td>
				            <td>$170.000</td>
				            <td>
				                <input type="submit" class="btn btn-primary btnEnviar" name="btnVerPrestamo" value="Detalle">
				            </td>
				        </tr>
			              <tr>
				            <td>005</td>
				            <td>05/10/2020</td>
				            <td>Aprobado</td>
				            <td>Si</td>
				            <td>$60.000</td>
				            <td>$120.000</td>
				            <td>
				                <input type="submit" class="btn btn-primary btnEnviar" name="btnVerPrestamo" value="Detalle">
				            </td>
				        </tr>
			             <tr>
				            <td>006</td>
				            <td>19/11/2021</td>
				            <td>Aprobado</td>
				            <td>Si</td>
				            <td>$40.000</td>
				            <td>$80.000</td>
				            <td>
				                <input type="submit" class="btn btn-primary btnEnviar" name="btnVerPrestamo" value="Detalle">
				            </td>
       					 </tr>
			             
			            
			        </tbody>
			    </table>
			
			
			
			
			    <a  href="PrestamosSolicitud.jsp" class="btn btn-success mt-2 mb-5">Nuevo Prestamo</a>
			</div>
			
			
			
			
        </div>
        
      </div>
    </div>
</div>
    <!--FOOTER-->
    <footer class="footer d-flex align-items-center">
	    <div class="w-100 d-flex justify-content-center">
	        <p class="fw-bold block">EQUIPO 5 - LABORATORIO IV UTN - Copyright � 2023 Todos los derechos reservados</p>
	    </div>
	</footer>
  </body>
  
</html>