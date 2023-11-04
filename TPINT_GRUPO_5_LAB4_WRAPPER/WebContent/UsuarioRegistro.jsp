<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
    <div class="container">
        <div class="d-flex justify-content-center" style="margin-top: 20px;">
            <div class="card d-flex" style="width: 900px;">
                <div class="card-header btnEnviar" style="color: whitesmoke;">
                    <h3>Completa tu registro:</h3>
                </div>
                <div class="card-body">
                    <form>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="mb-3">
                                    <label for="nombres" class="form-label">Nombres:</label>
                                    <input type="text" class="form-control" id="nombres" placeholder="Ingresa tu nombre completo">
                                </div>
                                <div class="mb-3">
                                    <label for="apellidos" class="form-label">Apellidos:</label>
                                    <input type="text" class="form-control" id="apellidos" placeholder="Ingresa tu apellido completo">
                                </div>
                                <div class="mb-3">
                                    <label for="dni" class="form-label">DNI:</label>
                                    <input type="text" class="form-control" id="dni" placeholder="Ingresa aquí tu número de DNI">
                                </div>
                                <div class="mb-3">
                                    <label for="cuil" class="form-label">CUIL:</label>
                                    <input type="text" class="form-control" id="cuil" placeholder="Ingresa aquí tu número de CUIL">
                                </div>
                                <div class="mb-3">
                                 
                                    <label for="nacionalidad" class="form-label">Nacionalidad:</label>
                                      <input type="text" class="form-control" id="nacionalidad" >
                                </div>
                                <div class="mb-3">
                                   
                                </div>
                                <div class="mb-3">
                                    <label for="correo" class="form-label">Correo Electrónico:</label>
                                    <input type="email" class="form-control" id="correo" placeholder="Ingresa tu correo electrónico">
                                </div>
                                <div class="mb-3">
                                    <label for="telefonos" class="form-label">Teléfonos:</label>
                                    <input type="text" class="form-control" id="telefonos" placeholder="Ingresa tus números de teléfono">
                                </div>
                         
                            </div>
                            <div class="col-md-6">
                                <div class="mb-3">
                                    <label for="direccion" class="form-label">Calle:</label>
                                    <input type="text" class="form-control" id="direccion" placeholder="Ingresa tu dirección">
                                </div>
                                <div class="mb-3">
                                    <label for="direccion" class="form-label"> Numero:</label>
                                    <input type="text" class="form-control" id="direccion" placeholder="Ingresa el numero de tu calle">
                                </div>
                                <div class="mb-3">
                                    <label for="tipo_direccion" class="form-label">Tipo de Domicilio:</label>
                                    <select class="form-select" id="tipo_direccion">
                                        <option value="casa">Casa</option>
                                        <option value="departamento">Departamento</option>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <label for="numero_departamento" class="form-label">Número de Departamento:</label>
                                    <input type="text" class="form-control" id="numero_departamento" placeholder="Ingresa el número de departamento">
                                </div>
                                <div class="mb-3">
                                    <label for="codigo_postal" class="form-label">Código Postal:</label>
                                    <input type="text" class="form-control" id="codigo_postal" placeholder="Ingresa el código postal">
                                </div>
                                <div class="mb-3">
                                    <label for="localidad" class="form-label">Localidad:</label>
                                    <select class="form-select" id="localidad">
                                        <option value="localidad1">Tigre</option>
                                        <option value="localidad2">Localidad 2</option>
                                        <option value="localidad3">Localidad 3</option>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <label for="provincia" class="form-label">Provincia:</label>
                                    <select class="form-select" id="provincia">
                                        <option value="provincia1">Buenos Aires</option>
                                        <option value="provincia2">Provincia 2</option>
                                        <option value="provincia3">Provincia 3</option>
                                    </select>
                                </div>
                            </div>
                        </div>
<br>
                            <div class="col d-flex justify-content-center">
                                <div class="card d-flex" style="padding: 5px; width: 450px;">
                        <div class="mb-3">
                            <label for="usuario" class="form-label">Usuario:</label>
                            <input type="text" class="form-control" id="usuario" placeholder="Ingresa un nombre de usuario">
                        </div>
                  
           
                        <div class="mb-3">
                            <label for="contrasena" class="form-label">Contraseña:</label>
                            <input type="password" class="form-control" id="contrasena" placeholder="Ingresa una contraseña">
                        </div>
                        <div class="mb-3">
                            <label for="Confirmacontrasena" class="form-label">Confirma Contraseña:</label>
                            <input type="password" class="form-control" id="contrasena" placeholder="Confirma tu contraseña">
                        </div>
                        <div class="mb-3">
                        <input type="submit" class="btn btn-primary btnEnviar" style="width: 100%;" value="Confirmar Registro">
                    </div>
                    </div>
                    </div>
                    
                </form>
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