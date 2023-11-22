<%@page import="entidad.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
	<!--HEAD-->
	<jsp:include page= "/WEB-INF/Components/head.jsp">
		<jsp:param name="titulo" value="Login"/>
	</jsp:include>
	<!--FIN HEAD-->
	
	<!--BODY-->
	<body class="d-flex flex-column bg-bank"> 
		<div class=" d-flex flex-column justify-content-center align-items-center flex-grow-1">
			<div class="card d-flex justify-content-center align-items-center" style="margin-top: 20px; background-color: var(--color-main); color: whitesmoke; min-width: 400px;">
				<h1 style="font-size: 34px; height: 60px;" class="m-0 p-2"> Banco Cinco <i class="fa-solid fa-building-columns"></i></h1>
			</div>
			<div class="card " style="min-width: 400px; margin-top: 5px;">
				<div class="card-header btnEnviar" style="color:whitesmoke">
					<h3 class="text-center m-0">Iniciar Sesion</h3>
				</div>
				<div class="card-body">
					<!-- FORMULARIO -->
					<form action="servletLogin" method="GET" class="mb-3">
						<div class="input-group form-group" >
							<span class="input-group-text" style="background-color: var(--color-main)"><i class="fa-solid fa-user" style="color:whitesmoke"></i></span>  							
							<input name="usuario" type="text" class="form-control" placeholder="Usuario">
						</div>
						<div class="input-group form-group mt-2">
							<span class="input-group-text" style="background-color: var(--color-main)"><i class="fas fa-key" style="color:whitesmoke"></i>	</span>		
							<input name="pass" type="password" class="form-control" placeholder="Contraseña">
						</div>
						<div class="form-group" style="text-align: center;margin-top: 20px">
							<input type="submit" name="btnAceptar" value="Ingresar" class="btn btn-primary btnEnviar" style="background-color: var(--color-main)">
						</div>
					</form>
					<!-- FIN FORMULARIO -->
					<div class="d-flex justify-content-center">
						<% 
							if(request.getAttribute("existeCliente") != null){
								
								boolean existeCliente = Boolean.TRUE == request.getAttribute("existeCliente");
							
							if(!existeCliente){
							%>
								<p class="text-danger">Usuario o Contraseña Incorrecta</p>
							<% }
							}
						%>
					</div>
				</div>
			</div>
		</div>
		<!--FOOTER-->
    	<jsp:include page= "/WEB-INF/Components/footer.html"></jsp:include>
		
		<!-- POPUP ERROR DE AUTENTICACION-->
		<%if(request.getAttribute("tipo") != null){
			%>
			<jsp:include page="/WEB-INF/Components/popup.jsp">
				<jsp:param name="tipo" value="<%= request.getAttribute(\"tipo\") %>"/>
				<jsp:param name="mensaje" value="<%= request.getAttribute(\"mensaje\") %>"/>
				<jsp:param name="titulo" value="<%= request.getAttribute(\"titulo\") %>"/>
			</jsp:include>
		<% } %>
		<!-- FIN POPUP -->
		
	</body>
	<!--FIN BODY-->    

</html>