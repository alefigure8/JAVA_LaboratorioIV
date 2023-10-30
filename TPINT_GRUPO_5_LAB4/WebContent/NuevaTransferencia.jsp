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
            <h1 class="mt-2">NUEVA TRANSFERENCIA</h1>
          </div>
          <div class="flex-grow-1">
            <!--FORMULARIO BUSCAR CLIENTE-->
            <form action="" class="col-6 mt-4">
              <div class="mb-3">
                <label class="form-label" for="cbu">¿Cuál es el CBU?</label>
                <div class="d-flex align-content-center gap-4">
                  <input type="text" name="cbu" placeholder="11 números" class="form-control w-50" id="cbu">
                  <span class="form-text text-danger d-none">Debe tener 11 números.</span>
                </div>
              </div>
              <div class="w-50 d-flex justify-content-center">
                <input type="submit" class="btn" value="Buscar">
              </div>
            </form>

            <!--FORMULARIO TRANSFERENCIA-->
            <form action="" class="col-6 mt-5">
              <div class="text-center w-50 mb-4">
                <span class="fs-5 text-uppercase mb-3 d-none">Jerry Seinfeld</span>
              </div>
              <div class="mb-3">
                <label class="form-label" for="monto">Monto a transferir</label>
                <div class="d-flex align-content-center gap-4">
                  <input type="text" name="monto" placeholder="Punto para los decimales" class="form-control w-50" id="monto" disabled>
                  <span class="form-text text-danger d-none">No puede ser $0,00</span>
                </div>
              </div>
              <div class="mb-3">
                <label class="form-label" for="concepto">Concepto</label>
                <div class="d-flex align-content-center gap-4">
                  <input type="text" name="concepto" placeholder="Varios" class="form-control w-50" id="concepto" disabled>
                  <span class="form-text text-danger d-none">Debe tener menos de 20 caracteres</span>
                </div>
              </div>
              <div class="w-50 d-flex justify-content-center">
                <input type="submit" class="btn" value="Aceptar">
              </div>
            </form>
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
