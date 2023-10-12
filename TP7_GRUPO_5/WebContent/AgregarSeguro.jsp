<%@page import="dominio.TipoSeguro"%>
<%@page import="java.util.List"%>
<%@page import="dominio.TipoSeguroDao"%>
<%@page import="dominio.SeguroDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Agregar Seguro</title>
</head>
<body>
<a href="Inicio.jsp">Inicio</a>
<a href="AgregarSeguro.jsp">Agregar Seguro</a>
<a href="ListarSeguro.jsp">Listar Seguros</a>
<h1>Agregar Seguros</h1>


<form action="servletAgregarSeguro" method="GET">
	<table>
		<tr>
			<td><p>Id Seguro</p></td>
			<!-- ID -->
			<% 
				SeguroDao seguroDao = new SeguroDao();
				int id = seguroDao.ultimoID() + 1;
			%>
			<td><p><%= id %></p><input type="hidden" name="id" value="<%= id %>"/></td>
		</tr>
		<tr>
			<td><p>Descripción</p></td>
			<td><input type="text" name="descripcion" placeholder="Agregue la descripcion" required/></td>
		</tr>
		<tr>
			<td><p>Tipo de Seguro</p>
			<td>
			<!-- SELECT TIPO DE SEGUROS -->
				<select name="selectTipoSeguro">
			<%
				TipoSeguroDao tipoSeguroDao = new TipoSeguroDao();
				List<TipoSeguro> listaTipoSeguro = tipoSeguroDao.readAll();
			
				for(TipoSeguro ts : listaTipoSeguro){
				%>
					<option value="<%= ts.getIdTipo()%>" ><%= ts.getDescripcion() %> </option>
				<% }
			%>
		</select>
			</td>
		</tr>
		<tr>
			<td><p>Costo Contratación</p><td>
			<input type="text" name="contratacion" placeholder="Agregue Costo Contratación" required/></td>
		</tr>
		<tr>
			<td><p>Costo Máximo Asegurado</p>
			<td><input type="text" name="asegurado" placeholder="Agregue Costo Máximo" required/></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" name="btnAceptar" value="Aceptar"/></td>
		</tr>
	</table>
</form>

</body>
</html>