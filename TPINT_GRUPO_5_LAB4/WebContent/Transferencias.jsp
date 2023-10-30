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
            <h1 class="mt-2">TRANSFERENCIA</h1>
          </div>
          <div>
            <input type="submit" class="btn mt-4" value="+ Nueva Ttransferencia">
          </div>
          <div class="flex-grow-1">
            <!--FILTRO-->
            <div class="d-flex flex-md-row flex-column justify-content-around align-items-center w-100 gap-2 mt-4">
              <div class="col-md-7 text-md-start text-center">
                <h4 class="opacity-75">Historial de transferencias realizadas</h4>
              </div>
              <div class="col-md-5">
                <form action="" class="d-flex justify-content-around align-items-center gap-2  flex-md-row flex-column">
                  <select name="Estados" class="form-select form-select-sm w-md-50">
                    <option value="Todos los Estados">Todos los Estados</option>
                    <option value="Realiazada">Realiazada</option>
                    <option value="Rechazada">Rechazada</option>
                    <option value="Pendiente">Pendiente</option>
                  </select>
                  <div class="d-flex gap-2">
                    <span >Desde: </span>
                    <input type="date" name="transferenicaDesde" value="28/10/2023">
                  </div>
                  <div class="d-flex gap-2">
                    <span>Hasta: </span>
                    <input type="date" name="transferenicaHasta">
                  </div>
                  <input type="submit" class="btn" value="Buscar">
                </form>
              </div>
            </div>

            <!--TABLA-->
            <div class="d-flex flex-md-row flex-column">
              <div class="h-100 me-4 w-100">
                <table class="table table-striped">
                  <thead>
                    <tr>
                      <th scope="col">Fecha</th>
                      <th scope="col">Destinatario</th>
                      <th scope="col">Monto</th>
                      <th scope="col">Estado</th>
                      <th scope="col"></th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <td class="d-flex align-items-center"><span class="black-75 me-2">28/10/2023</span><i class="fa-solid fa-user opacity-75"></i></td>
                      <td><span class="black-75">Cliente xxxxx</span></td>
                      <td><span class="black-75">$1.950,00</span></td>
                      <td><span class="badge bg-danger text-white">Rechazada</span></td>
                      <td><i class="fa-solid fa-circle-info opacity-50"></i> Detalle</td>
                    </tr>
                    <tr>
                      <td class="d-flex align-items-center"><span class="black-75 me-2">28/10/2023</span><i class="fa-solid fa-user opacity-75"></i></td>
                      <td><span class="black-75">Cliente xxxxx</span></td>
                      <td><span class="black-75">$1.950,00</span></td>
                      <td><span class="badge bg-success text-white">Realizada</span></td>
                      <td><i class="fa-solid fa-circle-info opacity-50"></i> Detalle</td>
                    </tr>
                    <tr>
                      <td class="d-flex align-items-center"><span class="black-75 me-2">28/10/2023</span><i class="fa-solid fa-user opacity-75"></i></td>
                      <td><span class="black-75">Cliente xxxxx</span></td>
                      <td><span class="black-75">$1.950,00</span></td>
                      <td><span class="badge bg-success text-white">Realizada</span></td>
                      <td><i class="fa-solid fa-circle-info opacity-50"></i> Detalle</td>
                    </tr>
                    <tr>
                      <td class="d-flex align-items-center"><span class="black-75 me-2">28/10/2023</span><i class="fa-solid fa-user opacity-75"></i></td>
                      <td><span class="black-75">Cliente xxxxx</span></td>
                      <td><span class="black-75">$1.950,00</span></td>
                      <td><span class="badge bg-danger text-white">Rechazada</span></td>
                      <td><i class="fa-solid fa-circle-info opacity-50"></i> Detalle</td>
                    </tr>
                    <tr>
                      <td class="d-flex align-items-center"><span class="black-75 me-2">28/10/2023</span><i class="fa-solid fa-user opacity-75"></i></td>
                      <td><span class="black-75">Cliente xxxxx</span></td>
                      <td><span class="black-75">$1.950,00</span></td>
                      <td><span class="badge bg-success text-white">Realizada</span></td>
                      <td><i class="fa-solid fa-circle-info opacity-50"></i> Detalle</td>
                    </tr>
                  </tbody>
                </table>
                <!--PAGINACION-->
                <nav class="w-100 d-flex justify-content-center">
                  <ul class="pagination">
                    <li class="page-item">
                      <a class="page-link" href="#" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                      </a>
                    </li>
                    <li class="page-item"><a class="page-link bg-primary text-light" href="#">1</a></li>
                    <li class="page-item"><a class="page-link" href="#">2</a></li>
                    <li class="page-item">
                      <a class="page-link" href="#" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                      </a>
                    </li>
                  </ul>
                </nav>
              </div>
              <!--DETALLE-->
              <div class="mt-4 border border-1 border-black border-opacity-25 rounded-1 p-2 mb-4" style="min-width: 300px;">
                <div class="col-7 d-flex justify-content-between w-100 align-items-center mb-2">
                  <h4 class="opacity-75 m-0">Detalle</h4>
                  <span class="text-danger fw-bolder fs-6">X</span>
                </div>
                <div>
                  <p class="mb-0">Estado</p>
                  <p class="fs-5">Realizada</p>
                  <p class="mb-0">Cuenta débito</p>
                  <p class="fs-5">123456789</p>
                  <p class="mb-0">Fecha de Transferencia</p>
                  <p class="fs-5">28/10/2023</p>
                  <p class="mb-0">Monto</p>
                  <p class="fs-5">$1.950,00</p>
                  <p class="mb-0">Transferencia a</p>
                  <p class="fs-5">Cliente 2 xxxxx </p>
                  <p class="mb-0">CBU</p>
                  <p class="fs-5">123456789123</p>
                  <p class="mb-0">Concepto</p>
                  <p class="fs-5">Varios</p>
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
