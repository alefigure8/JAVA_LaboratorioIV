<%@page import="dominio.SeguroDao"%>
<%@page import="dominio.Seguro"%>
<%@page import="dominio.TipoSeguro"%>
<%@page import="java.util.List"%>
<%@page import="dominio.TipoSeguroDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listar Seguro</title>
</head>
<body>
<a href="Inicio.jsp">Inicio</a>
<a href="AgregarSeguro.jsp">Agregar Seguro</a>
<a href="ListarSeguro.jsp">Listar Seguros</a>


<h1>Listar seguros</h1>

<div>
	<form action="servletListarSeguro" method="GET">
		<p>Búsqueda por tipo de Seguros: </p>
		
		<!--  SELECT CON TIPOS DE SEGUROS -->
		<select name="selectTipoSeguro">
		<%
			TipoSeguroDao tipoSeguroDao = new TipoSeguroDao();
			List<TipoSeguro> listaTipoSeguro = tipoSeguroDao.readAll();
			
			for(TipoSeguro ts : listaTipoSeguro){
				%>
				
				<option value="<%= ts.getIdTipo()%>" ><%= ts.getDescripcion() %> </option>
				
			<% }
		%>
			<option value="todos">Todos los Seguros</option>
		</select>
		
		<input type="submit" name="btnFiltrar" value="Filtrar"/>
	</form>
</div>



<table>
	<tr>
		<th>ID Seguro</th>
		<th>Descripción seguro</th>
		<th>Descripción tipo seguro</th>
		<th>Costo contratación</th>
		<th>Costo máximo asegurado</th>
	</tr>
	
	<!-- LLAMAR DESDE BASE DE DATOS
		<tr>
			<td></td> ID
			<td></td> DESCRIPCION
			<td></td> TIPO DE SEGUROS
			<td></td> COSTO CONTRATACION
			<td></td> COSTO MAXIMO ASEGURADO
		</tr>
	 -->

</table>


<!-- ESTILOS -->
<style>
	table, td, tr {
		border:1px solid black;
		width:50%;
	}
	
	tr {
		  font-weight: bold;
		  font-size: 16px;
	  }
	  
	td, tr {
	 	padding: 5px;
	 }
	
	p{
		display: inline;
	}
	
	div{
		margin-bottom: 10px;
	}

</style>

</body>
</html>