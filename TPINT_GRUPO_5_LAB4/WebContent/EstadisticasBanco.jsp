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
          <li class="list-group-item border-0 bg-transparent"><a  href=#>Estadisticas Banco </a></li>
          <li class="list-group-item border-0 bg-transparent mt-4"><a  href=#>Cerrar Sesion</a></li>
        </ul>
      </div>

      <!--CONTENT-->

        <div class="col-lg-9 col-md-12 d-flex flex-column">


            <div class="card mt-3">
                <div class="card-body">
         
                <div class="row">

                    <div class="col">
                        <label for="montoCuentasClientes">Monto Total Cuentas Clientes</label>
                        <input type="text" class="form-control" id="montoCuentasClientes" name="montoCuentasClientes" value="200000000" disabled>
                    </div>
                    <div class="col">
                        <label for="montoCuentaBanco">Monto Total Cuenta Banco</label>
                        <input type="text" class="form-control" id="montoCuentaBanco" name="montoCuentaBanco" value="350000000" disabled>
                    </div>
                    <div class="col">
                        <label for="montoCuentasCorrientes">Monto Total Cuentas Corrientes</label>
                        <input type="text" class="form-control" id="montoCuentasCorrientes" name="montoCuentasCorrientes" value="100000000" disabled>
                    </div>
                    <div class="col">
                        <label for="montoCajasAhorro">Monto Total Cajas de Ahorro</label>
                        <input type="text" class="form-control" id="montoCajasAhorro" name="montoCajasAhorro" value="100000000" disabled>
                    </div>
       
                </div>
            </div>
       
        </div>
 
            
            


        <div class="card mt-3">
                    <div class="card-body">

            <div class="row mt-2 d-flex">
                
                    <div class="col-md-3"> <!-- Dividimos el formulario en 4 columnas para los 4 elementos -->
                        <div class = "row">
                        <div class="col">
                        <label for="Año">Año:</label>
                        <select id="Año" class="form-select">
                            <option value="2023">2023</option>
                            <option value="2022">2022</option>
                            <!-- Agrega más opciones según sea necesario -->
                        </select>
                        </div>
                        <div class="col">
                            <label for="Mes">Mes:</label>
                        <select id="Mes" class="form-select">
                            <option value="Enero">Enero</option>
                            <option value="Febrero">Febrero</option>
                            <!-- Agrega más opciones según sea necesario -->
                        </select>
                    </div>
                        </div>
                    </div>
                     <div class="col-md-2">
                        <label for="Sexo">Sexo:</label>
                        <select id="Sexo" class="form-select">
                            <option value="Masculino">Masculino</option>
                            <option value="Femenino">Femenino</option>
                            <option value="PrefiereNoDecir">Prefiere no decir</option>
                        </select>
                    </div>
                    <div class="col-md-2">
                        <label for="Rangoedad">Rango de Edad:</label>
                        <span id="Edadseleccionada">20</span> <!-- Elemento para mostrar el valor del rango -->
                        <input type="range" name="Rangoedad" min="20" max="80" class="form-range">
                        
                    </div>
                    <div class="col-md-2">
                        <label for="provinceSelect">Provincia:</label>
                        <select id="provinceSelect" class="form-select">
                            <option value="Provincia1">Buenos Aires</option>
                            <option value="Provincia2">Ciudad Autonoma de Buenos Aires</option>
                            <option value="Provincia3">Chubut</option>
                            <!-- Agrega más opciones según sea necesario -->
                        </select>
                    </div>
                    <div class="col-md-2">
                        <label for="SucursalSelect">Sucursal:</label>
                        <select id="SucursalSelect" class="form-select">
                            <option value="Sucursal1">Tigre</option>
                            <option value="Sucursal2">Ciudad Autonoma de Buenos Aires</option>
                            <option value="Sucursal3">San Martin</option>
                            <!-- Agrega más opciones según sea necesario -->
                        </select>
                    </div>
                    <div class="col-md-1 d-flex"> <!-- Agregamos un margen top para el botón -->
                        <input type="submit" class="btn btn-primary btnEnviar" style="width: 100%;" value="Filtrar">
                    </div>
                </form>
            </div>
        </div>
       
    </div>

   
     

<div class="card mt-3">
    <div class="card-body">
       
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="clientesNuevos">Clientes Nuevos</label>
                            <input type="text" class="form-control" id="clientesNuevos" name="clientesNuevos" value="132" disabled>
                        </div>
                        <div class="form-group">
                            <label for="bajasClientes">Bajas Clientes</label>
                            <input type="text" class="form-control" id="bajasClientes" name="bajasClientes" value="14" disabled>
                        </div>
                        <div class="form-group">
                            <label for="cuentasNuevas">Cuentas Nuevas</label>
                            <input type="text" class="form-control" id="cuentasNuevas" name="cuentasNuevas" value="142" disabled>
                        </div>
                        <div class="form-group">
                            <label for="bajasCuentas">Bajas Cuentas</label>
                            <input type="text" class="form-control" id="bajasCuentas" name="bajasCuentas" value="25" disabled>
                        </div>
                        <div class="form-group">
                            <label for="cajasAhorro">Cajas de Ahorros nuevas</label>
                            <input type="text" class="form-control" id="cajasAhorro" name="cajasAhorro" value="75" disabled>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="cuentasCorrientes">Cuentas Corrientes nuevas</label>
                            <input type="text" class="form-control" id="cuentasCorrientes" name="cuentasCorrientes" value="80" disabled>
                        </div>
                        <div class="form-group">
                            <label for="transferencias">Cantidad de Transferencias</label>
                            <input type="text" class="form-control" id="transferencias" name="transferencias" value="1532" disabled >
                        </div>
                        <div class="form-group">
                            <label for="prestamosOtorgados">Prestamos Otorgados</label>
                            <input type="text" class="form-control" id="prestamosOtorgados" name="prestamosOtorgados" value="85" disabled>
                        </div>
                        <div class="form-group">
                            <label for="prestamosCancelados">Prestamos Cancelados</label>
                            <input type="text" class="form-control" id="prestamosCancelados" name="prestamosCancelados" value="35" disabled>
                        </div>
                        <div class="form-group">
                            <label for="montoTotalTransferido">Monto Total Transferido</label>
                            <input type="text" class="form-control" id="montoTotalTransferido" name="montoTotalTransferido"value="2535805" disabled>
                        </div>
                    </div>
                </div>
            </div>
        </div>
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