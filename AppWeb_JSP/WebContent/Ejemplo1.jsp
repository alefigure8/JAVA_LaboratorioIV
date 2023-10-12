<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Practica</title>
</head>
<body>
	<h1>Bienvenidos!</h1>
	<!-- Declaracion -->
	<%String user = "Admin"; %>
	
	<!-- ScriptLet -->
	<%if(user == "Admin"){ %>
		<form action="Ejemplo2.jsp" method="GET">
			<input type="text" name="nombre" placeholder="Agregue su nombre" style="width: 250px" requiered/><br><br>
			<input type="text" name="apellido" placeholder="Agregue su apellido" style="width: 250px" requiered/><br><br>
			<input type="submit" name="enviar" value="Enviar"/>
			<input type="submit" name="despedir" value="Despedir"/>
			<br>
			<br>
			<select name="seleccion">
				<option>Ejemplo 1</option>
				<option>Ejemplo 2</option>
				<option>Ejemplo 3</option>
				<option>Ejemplo 4</option>
			</select>
			<br>
			<br>
			<p>radio 1</p><input type="radio" name="radio" value="radio1"/>
			<p>radio 2</p><input type="radio" name="radio" value="radio2"/>
			<p>radio 3</p><input type="radio" name="radio" value="radio3"/>
		</form>
		
		
	<%}%>
	
	<!-- Expresion -->
			
</body>
</html>