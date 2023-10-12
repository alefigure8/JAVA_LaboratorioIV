<%@page import="dominio.PersonaDaoImpI"%>
<%@page import="dominio.Persona"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<p>Ingrese su nombre, apellido y dni</p>
<form  action="servletUsuario" method="get">
	<input type="text" name="nombre" placeholder="Ingrese su nombre"/><br>
	<input type="text" name="apellido" placeholder="Ingrese su apillido"/><br>
	<input type="text" name="dni" placeholder="Ingrese su dni"/><br>
	<input type="submit" name="btn" value="Aceptar" />
</form>


<% 
boolean resultado = Boolean.TRUE == request.getAttribute("resultado");
if(resultado){ %>
		<p>Agrego con éxito</p>
<% } %>

</body>
</html>