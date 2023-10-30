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
          <span class="fw-lighter">Nombre Admin</span>
        </div>
        <ul class="list-group border-0">
          <li class="list-group-item border-0 bg-transparent">Inicio</li>
          <li class="list-group-item border-0 bg-transparent">Cuentas</li>
          <li class="list-group-item border-0 bg-transparent">Prestamos</li>
          <li class="list-group-item border-0 bg-transparent mt-4">
            Cerrar Sesión
          </li>
        </ul>
      </nav>

      <!--CONTENIDO-->
        <div class="col-10 d-flex flex-column justify-content-between">
          <div class="w-100 pt-2">
            <!--TIUTLO PAGINA-->
            <h1 class="mt-2">PRESTAMOS CLIENTES</h1>
          </div>
          <div class="flex-grow-1">
            <!--FILTRO-->
            <div class="d-flex flex-md-row flex-column justify-content-around align-items-center w-100 gap-2 mt-4">
              <div class="col-md-7 text-md-start text-center">
                <h4 class="opacity-75">Historial de los prestamos de clientes</h4>
              </div>
              <div class="col-md-5">
                <form action="" class="d-flex justify-content-around align-items-center gap-2  flex-md-row flex-column">
                  <div class="d-flex gap-2">
                    <input type="text" name="cliente" placeholder="Buscar">
                  </div>
                  <select name="Estados" class="form-select form-select-sm w-md-50">
                    <option value="Todos los Estados">Todos los Campos</option>
                    <option value="Cuente">Cuenta</option>
                    <option value="CBU">CBU</option>
                    <option value="DNI">DNI</option>
                    <option value="CUIL">CUIL</option>
                    <option value="Apellido">Apellido</option>
                  </select>
                  <select name="Estados" class="form-select form-select-sm w-md-50">
                    <option value="Todos los Estados">Todos los Estados</option>
                    <option value="Realiazada">Aprobados</option>
                    <option value="Rechazada">Rechazados</option>
                    <option value="Rechazada">Pendientes</option>
                  </select>
                  <input type="submit" class="btn" value="Buscar">
                </form>
              </div>
            </div>

            <div class="d-flex flex-md-row flex-column">
              <div class="h-100 me-4 w-100">
                <table class="table table-striped">
                  <thead>
                    <tr>
                      <th scope="col"># Prestamo</th>
                      <th scope="col">Cliente</th>
                      <th scope="col">Monto</th>
                      <th scope="col">Intereses</th>
                      <th scope="col">Cuotas</th>
                      <th scope="col">Fecha de Alta</th>
                      <th scope="col">Estado</th>
                      <th scope="col">Detalle</th>
                      <th scope="col">Acción</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <td><span class="black-75 me-2">10000</span><i class="fa-solid fa-chart-line opacity-50"></i></td>
                      <td><i class="fa-solid fa-user opacity-50 me-2"></i>J. Peterman</td>
                      <td><span class="black-75">$50.000,00</span></td>
                      <td><span class="black-75">15%</span></td>
                      <td><span class="black-75">24</span></td>
                      <td><span class="black-75">28/10/2023</span></td>
                      <td><span class="badge bg-warning text-white">Pendiente</span></td>
                      <td><i class="fa-solid fa-circle-info opacity-50 fs-5"></i></td>
                      <td><div class="bg-warning p-2 w-100 rounded-2 text-center badge"><span class="text-black">Aprobar</span></div></td>
                  </tr>
                  <tr>
                      <td><span class="black-75 me-2">20000</span><i class="fa-solid fa-chart-line opacity-50"></i></td>
                      <td><i class="fa-solid fa-user opacity-50 me-2"></i>Jerry Seinfeld</td>
                      <td><span class="black-75">$70.000,00</span></td>
                      <td><span class="black-75">18%</span></td>
                      <td><span class="black-75">12</span></td>
                      <td><span class="black-75">21/09/2019</span></td>
                      <td><span class="badge bg-success text-white">Aprobado</span></td>
                      <td><i class="fa-solid fa-circle-info opacity-50 fs-5"></i></td>
                      <td></td>
                  </tr>
                  <tr>
                    <td><span class="black-75 me-2">30000</span><i class="fa-solid fa-chart-line opacity-50"></i></td>
                    <td><i class="fa-solid fa-user opacity-50 me-2"></i>Newman</td>
                    <td><span class="black-75">$180.000,00</span></td>
                    <td><span class="black-75">5%</span></td>
                    <td><span class="black-75">48</span></td>
                    <td><span class="black-75">19/11/2023</span></td>
                    <td><span class="badge bg-danger text-white">Rechazado</span></td>
                    <td><i class="fa-solid fa-circle-info opacity-50 fs-5"></i></td>
                    <td></td>
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
                  <p class="mb-0">Prestamo</p>
                  <p class="fs-5">10000</p>    
                  <p class="mb-0">Cliente</p>
                  <p class="fs-5">J. Peterman</p>  
                  <p class="mb-0">Estado</p>
                  <p class="fs-5">Pendiente</p>
                  <p class="mb-0">Cuotas</p>
                  <p class="fs-5">24</p>
                  <p class="mb-0">Fecha de Alta</p>
                  <p class="fs-5">28/10/2023</p>
                  <p class="mb-0">Monto</p>
                  <p class="fs-5">$50.000,000</p>
                  <p class="mb-0">Interés</p>
                  <p class="fs-5">15%</p>
                  <p class="mb-0">Monto por Cuota</p>
                  <p class="fs-5">$2.500,00</p>
                  <p class="mb-0">Total a pagar</p>
                  <p class="fs-3 fw-bold text-secondary">$60.000,00</p>
                </div>
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

  td div,
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

  .fs-7{
    font-size: 12px;
  }

  td {
    vertical-align: middle;
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
