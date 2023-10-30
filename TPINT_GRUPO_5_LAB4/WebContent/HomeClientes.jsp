<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
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
            class="list-group-item border-0 border-bottom border-secondary bg-transparent"
          >
            
          </li>
         <!--  <li class="list-group-item border-0 bg-transparent"><a  href=#>Inicio</a></li>
          <li class="list-group-item border-0 bg-transparent"><a  href=#>Transferencia</a></li>
          <li class="list-group-item border-0 bg-transparent"><a  href=#>Prestamo</a></li> -->
          <li class="list-group-item border-0 bg-transparent mt-4"><a  href=#>Cerrar Sesión</a></li>
        </ul>
      </div>

         <!--CONTENT-->
        <div class="col-lg-9 col-md-12 d-flex flex-column justify-content-between">
            <div class="w-100 pt-2">
                <h1>HOME / BIENVENIDO</h1>
            </div>

            <div class="flex-grow-1">
            <!-- CONTENIDO -->
            <!-- MENU -->
			<div class="p-4 col-md-12 justify-content-center align-items-start">
    		<h4>Menú</h4>
    		<a href="#" class="btn btn-primary btnEnviar col-5 p-4 m-1">MIS CUENTAS</a>
    		<a href="#" class="btn btn-primary btnEnviar col-5 p-4 m-1">TRANSFERENCIAS</a>
    		<a href="#" class="btn btn-primary btnEnviar col-5 p-4 m-1">PRESTAMOS</a>
    		<a href="#" class="btn btn-primary btnEnviar col-5 p-4 m-1">PERFIL</a>
			</div>
			<!-- ÚLTIMOS MOVIMIENTOS // CAJA AHORRO DEFAULT -->
                <form class="container mt-4 d-flex justify-content-left align-items-start" action="#" method="post">
                    <div class="form-group col-md-12 d-flex flex-column">
                        <h5>Últimos movimientos | Caja Ahorro</h5>
                        <table class="table table-bordered mt-2">
				        <tr>
            				<th>TRANSF. N0001</th> <th>25/09/2023</th> <th>$5500.00</th> <th><button class="btn btn-primary">Ver más</button></th>
        				</tr>
        				<tr>
            				<th>TRANSF. N0002</th> <th>19/09/2023</th> <th>$10000.00</th> <th><button class="btn btn-primary">Ver más</button></th>
        				</tr>
        				<tr>
            				<th>TRANSF. N0003</th> <th>07/09/2023</th> <th>$2.00</th> <th><button class="btn btn-primary">Ver más</button></th>
        				</tr>
					  </table>
   					</div>
			    </div>
			</form>
        </div>
        
      </div>
      
    </div>
</div>
    <!--FOOTER-->
    <footer class="footer flex-grow-0">
      <div class="w-100 h-100 d-flex justify-content-center align-items-center">
        <p class="fw-bold">EQUIPO 5 - LABORATORIO IV - UTN</p>
      </div>
    </footer>
    
  </body>
</html>

<!--ESTILOS - TODO: PASAR A CSS-->
<style>
  :root {
    --bg-main: #f4f4f4;
    --color-main: #0a336c;
    --text: #333;
    --text-secondary: #fff;
  }

  body {
    font-family: "Inter", sans-serif;
    font-size: 14px;
    color: var(--text);
    height: 100vh;
  }

  h1 {
    font-size: 32px;
    font-weight: 400;
  }

  .sidebar {
    flex: 0 0 auto;
    width: 240px;
    background-color: var(--bg-main);
  }

  .footer {
    background-color: var(--color-main);
    color: var(--text-secondary);
    height: 100px;
  }

  .bg-main {
    background-color: var(--color-main);
  }
</style>
