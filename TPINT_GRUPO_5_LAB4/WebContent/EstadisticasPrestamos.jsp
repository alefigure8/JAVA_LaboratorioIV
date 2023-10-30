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
    <title>Inicio Sesion</title>
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



    <script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <script>
        $(document).ready(function () {
            $('#table_id').DataTable();s
        });
    </script>
    
	
</head>
<body>
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
          <li class="list-group-item border-0 bg-transparent"><a  href=#>Estadisticas Prestamos</a></li>
          <li class="list-group-item border-0 bg-transparent"><a  href=#>Estadisticas Banco</a></li>
          <li class="list-group-item border-0 bg-transparent mt-4"><a  href=#>Cerrar Sesion</a></li>
        </ul>
      </div>

      <!--CONTENT-->

        <div class="col-lg-9 col-md-12 d-flex flex-column">

            <div class="card">
                <div class="card-body">
            <div class="row mt-2 d-flex">
                
                    <div class="col-md-2"> <!-- Dividimos el formulario en 4 columnas para los 4 elementos -->
                        <label for="Año">Año:</label>
                        <select id="Año" class="form-select">
                            <option value="2023">2023</option>
                            <option value="2022">2022</option>
                            <!-- Agrega más opciones según sea necesario -->
                        </select>
                    </div>
                    <div class="col-md-2">
                        <label for="Sexo">Sexo:</label>
                        <select id="Sexo" class="form-select">
                            <option value="Masculino">Masculino</option>
                            <option value="Femenino">Femenino</option>
                            <option value="PrefiereNoDecir">Prefiere no decir</option>
                        </select>
                    </div>
                    <div class="col-md-3">
                        <label for="Rangoedad">Rango de Edad:</label>
                        <span id="Edadseleccionada">20</span> <!-- Elemento para mostrar el valor del rango -->
                        <input type="range" name="Rangoedad" min="20" max="80" class="form-range">
                        
                    </div>
                    <div class="col-md-3">
                        <label for="provinceSelect">Provincia:</label>
                        <select id="provinceSelect" class="form-select">
                            <option value="Provincia1">Buenos Aires</option>
                            <option value="Provincia2">Ciudad Autonoma de Buenos Aires</option>
                            <option value="Provincia3">Chubut</option>
                            <!-- Agrega más opciones según sea necesario -->
                        </select>
                    </div>
                    <div class="col-md-2 d-flex"> <!-- Agregamos un margen top para el botón -->
                        <button type="submit" class="btn btn-primary btnEnviar" style="width: 100%;">Filtrar</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
  

    <div>
<div class="card overflow-y-auto">

   	    <table id="table_id" class="table display">
            <thead>
        <tr> 
            <th style="background-color: var(--color-main); color: whitesmoke;">Concepto</th>
            <th style="background-color: var(--color-main); color: whitesmoke;">Enero</th>
            <th style="background-color: var(--color-main); color: whitesmoke;">Febrero</th>
            <th style="background-color: var(--color-main); color: whitesmoke;">Marzo</th>
            <th style="background-color: var(--color-main); color: whitesmoke;">Abril</th>
            <th style="background-color: var(--color-main); color: whitesmoke;">Mayo</th>
            <th style="background-color: var(--color-main); color: whitesmoke;">Junio</th>
            <th style="background-color: var(--color-main); color: whitesmoke;">Julio</th>
            <th style="background-color: var(--color-main); color: whitesmoke;">Agosto</th>
            <th style="background-color: var(--color-main); color: whitesmoke;">Septiembre</th>
            <th style="background-color: var(--color-main); color: whitesmoke;">Octubre</th>
            <th style="background-color: var(--color-main); color: whitesmoke;">Noviembre</th>
            <th style="background-color: var(--color-main); color: whitesmoke;">Diciembre</th> 
        </tr>
        </thead>
        <tbody>
        <tr> <td>Tasa de Interes </td> 
            <td>4.5%</td>
            <td>4.5%</td>
            <td>4.5%</td>
            <td>4.5%</td>
            <td>4.5%</td>
            <td>4.5%</td>
            <td>4.5%</td>
            <td>4.5%</td>
            <td>4.5%</td>
            <td>4.5%</td>
            <td>4.5%</td>
            <td>4.5%</td>
        </tr>   
        <tr> <td>Indice de Morosidad </td> 
            <td>1.2%</td>
            <td>1.3%</td>
            <td>1.4%</td>
            <td>1.4%</td>
            <td>1.5%</td>
            <td>1.5%</td>
            <td>1.6%</td>
            <td>1.6%</td>
            <td>1.7%</td>
            <td>1.7%</td>
            <td>1.8%</td>
            <td>1.8%</td>
        </tr>
        <tr> <td>Cantidad de Prestamos Otogrados </td>  
            <td>200</td>
            <td>210</td>
            <td>220</td>
            <td>230</td>
            <td>240</td>
            <td>250</td>
            <td>260</td>
            <td>270</td>
            <td>280</td>
            <td>290</td>
            <td>300</td>
            <td>310</td>
        </tr>
        <tr> <td>Cantidad de Prestamos Cancelados al 100% </td> 
            <td>180</td>
            <td>190</td>
            <td>200</td>
            <td>210</td>
            <td>220</td>
            <td>230</td>
            <td>240</td>
            <td>250</td>
            <td>260</td>
            <td>270</td>
            <td>280</td>
            <td>290</td>
        </tr>
        <tr> <td>Monto Total Otogrado </td> 
            <td>$2,500,000</td>
            <td>$2,600,000</td>
            <td>$2,700,000</td>
            <td>$2,800,000</td>
            <td>$2,900,000</td>
            <td>$3,000,000</td>
            <td>$3,100,000</td>
            <td>$3,200,000</td>
            <td>$3,300,000</td>
            <td>$3,400,000</td>
            <td>$3,500,000</td>
            <td>$3,600,000</td>
         </tr>
        <tr> <td>Monto en Pagos recibidos </td>  
            <td>$2,200,000</td>
            <td>$2,310,000</td>
            <td>$2,420,000</td>
            <td>$2,530,000</td>
            <td>$2,640,000</td>
            <td>$2,750,000</td>
            <td>$2,860,000</td>
            <td>$2,970,000</td>
            <td>$3,080,000</td>
            <td>$3,190,000</td>
            <td>$3,300,000</td>
            <td>$3,410,000</td></tr>
        <tr> <td>Monto a recibir acumulado </td> 
            <td>$300,000</td>
            <td>$290,000</td>
            <td>$280,000</td>
            <td>$270,000</td>
            <td>$260,000</td>
            <td>$250,000</td>
            <td>$240,000</td>
            <td>$230,000</td>
            <td>$220,000</td>
            <td>$210,000</td>
            <td>$200,000</td>
            <td>$190,000</td>
         </tr>
        <tr> <td>Cuotas restantes promedio </td> 
            <td>15</td>
            <td>15</td>
            <td>14</td>
            <td>14</td>
            <td>13</td>
            <td>13</td>
            <td>12</td>
            <td>12</td>
            <td>11</td>
            <td>11</td>
            <td>10</td>
            <td>10</td>
        </tr>
          </tbody>
    </table>

</div>
</div>
</div>
</body>

<footer class="footer flex-grow-0">
     <div class="w-100 h-100 d-flex justify-content-center align-items-center">
       <p class="fw-bold">EQUIPO 5 - LABORATORIO IV - UTN</p>
     </div>
   </footer>
  
</html>