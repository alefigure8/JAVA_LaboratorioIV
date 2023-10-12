<%@page import="java.util.List"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
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

	<h1>Ejemplo 3</h1>
	<form action="Ejemplo3.jsp" method="GET">
		<input type="text" name="nombre" placeholder="Agregue nombre"/><br><br>
		<input type="text" name="apellido" placeholder="Agregue apellido"/><br><br>
		<input type="text" name="dni" placeholder="Agregue dni"/><br><br>
		<input type="submit" name="btn" value="Aceptar"/>
	</form>
	
	<!-- JAVA -->
	<%
	
		String resultado = "";
		
		if(request.getParameter("btn") != null){
			
			String nombre = request.getParameter("nombre");
			String apellido = request.getParameter("apellido");
			String dni = request.getParameter("dni");
						
			//AGREGAR A LA BASE DE DATOS
			try{
				
				Persona persona = new Persona(dni, nombre, apellido);
				PersonaDaoImpI pdi = new PersonaDaoImpI();
				Boolean insertado = pdi.insert(persona);
				
				
				if(insertado){
					resultado = "Se cargo con exito";
				} else {
					resultado = "Error al cargar";
				}
				
			} catch (Exception e){
				e.printStackTrace();
			}
			
		}
	
	%>
	
	<%=resultado%>
	
	<%
		
		PersonaDaoImpI pdi = new PersonaDaoImpI();
		List<Persona> lista = pdi.readAll();
		
		for(Persona persona : lista){
			%>
			
			<%= persona.getNombre() %>
		<%
		}; %>
		
	
	


</body>
</html>