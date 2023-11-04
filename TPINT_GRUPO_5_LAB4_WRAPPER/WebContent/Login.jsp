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
<div class="container d-flex flex-column justify-content-center align-items-center" style="margin-top: 10%;" >


	<div class="card d-flex justify-content-center align-items-center" style="margin-top: 20px; background-color: var(--color-main); color: whitesmoke; min-width: 400px;">
		<label style="font-size: xx-large;"> Banco Cinco <i class="fa-solid fa-building-columns"></i></label>
	</div>
	
		
		<div class="card" style="min-width: 400px; margin-top: 5px;">
			<div class="card-header btnEnviar" style="color:whitesmoke">
				<h3>Iniciar Sesion</h3>
			</div>
			<div class="card-body">
				<form>
				
					<div class="input-group form-group" >
						<span class="input-group-text" style="background-color: var(--color-main)"><i class="fa-solid fa-user" style="color:whitesmoke"></i></span>  							
						<input name="usuario" type="text" class="form-control" placeholder="Usuario">
					</div>
					<div class="input-group form-group">
						<span class="input-group-text" style="background-color: var(--color-main)"><i class="fas fa-key" style="color:whitesmoke"></i>	</span>		
						<input name="pass" type="password" class="form-control" placeholder="Contraseña">
					</div>
					
					<div class="form-group" style="text-align: center;margin-top: 20px">
						<input name="btnAceptar" type="submit" value="Ingresar" class="btn btn-primary btnEnviar" style="background-color: var(--color-main)">
					</div>
					
				</form>
				
						<br>
			<div class="d-flex justify-content-center">
			<a style="margin-right: 5px;">¿Olvidaste tu contraseña?  </a> <a> Registrate </a>		
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