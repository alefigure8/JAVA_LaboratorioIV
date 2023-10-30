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
    <div class="row flex-grow-1 m-0">
      <!--SIDEBAR-->
      <nav class="sidebar">
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
          <li class="list-group-item border-0 bg-transparent">Inicio</li>
          <li class="list-group-item border-0 bg-transparent">Cuenta</li>
          <li class="list-group-item border-0 bg-transparent">Transferencia</li>
          <li class="list-group-item border-0 bg-transparent">Prestamo</li>
          <li class="list-group-item border-0 bg-transparent mt-4">
            Cerrar Sesión
          </li>
        </ul>
      </nav>

      <!--CONTENIDO-->
        <div class="col-10 d-flex flex-column justify-content-between">
          <div class="w-100 pt-2">
            <!--TIUTLO PAGINA-->
            <h1 class="mt-2">PAGO PRESTAMO</h1>
          </div>
          <div class="flex-grow-1">
            <!--FILTRO-->
            <div class="d-flex flex-md-row flex-column w-100 gap-2 mt-4">
                <h4 class="opacity-75">Prestamos #123456</h4>
            </div>

            <!--TABLA-->
            <div class="d-flex flex-md-row flex-column">
              <div class="mt-4 border border-1 border-black border-opacity-25 rounded-1 p-2 mb-4" style="min-width: 300px;">
                <div class="col-7 d-flex justify-content-between w-100 align-items-center mb-2">
                  <h4 class="opacity-75 m-0">Detalle</h4>
                </div>
                <div>
                  <p class="mb-0">Cuota</p>
                  <p class="fs-5">3/12</p>
                  <p class="mb-0">Estado</p>
                  <p class="fs-5">Pendiente</p>
                  <p class="mb-0">Fecha de Vencimiento</p>
                  <p class="fs-5">28/10/2023</p>
                  <p class="mb-0">Monto</p>
                  <p class="fs-5">$1.950,00</p>
                  <p class="mb-0">Interés</p>
                  <p class="fs-5">$295,00</p>
                  <p class="mb-0">Total a pagar</p>
                  <p class="fs-3 fw-bold text-secondary">$2.245,00</p>
                  <p class="mb-0">Pagando desde</p>
                  <select name="Estados" class="form-select form-select-sm w-md-50">
                    <option value="Realiazada">CA N° 123456789 - $12.595,00</option>
                    <option value="Rechazada">CC N° 987654321 - $9.785,07</option>
                    <option value="Pendiente">CAC N° 987651234 - $152,15</option>
                  </select>
                  <input type="button" class="btn d-inline w-100 mt-4" value="Pagar">
                </div>
              </div>
              
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
    --text_table: #555;
    --text-secondary: #fff;
    --color-btn: #0a336c;
    --color-btn-hover: #1157b8;
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

  .black-75 {
    color: var(--text_table)
  }

  nav ul li {
    cursor: pointer;
  }

  .btn{
    background-color: var(--color-btn);
    color: var(--text-secondary);
    height: 30px;
    line-height: 10px;
  }

  .btn:hover{
    background-color: var(--color-btn-hover);
    color: var(--text-secondary);
  }


  @media (max-width: 992px) {
        .sidebar {
            width: 100%;
            height:auto;
           
        }
        .footer p{
            margin-left:20px;
            margin-right:20px;
            text-align:center;
        }
    }

</style>
