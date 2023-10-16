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
			<input type="text" name="contratacion" placeholder="Agregue Costo Contratación" required oninput="this.value = this.value.replace(/[^0-9.]/g, '');
				this.value = this.value.substring(0, 10);"/></td>
			
			</tr>
		<tr>
			<td><p>Costo Máximo Asegurado</p>
			<td>

    		<input type="text" name="asegurado" placeholder="Agregue Costo Máximo" required oninput="this.value = this.value.replace(/[^0-9.]/g, ''); 
    			this.value = this.value.substring(0, 10);">
    		</td>
			
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" name="btnAceptar" value="Aceptar"/></td>
		</tr>
	</table>
	
<%
	if(request.getAttribute("agregado")!=null)
	{ 
		boolean agregado=(boolean)request.getAttribute("agregado");
		if(agregado){
		%>
			<p>Seguro agregado exitosamente.</p>
		<%
		}
		else{
		%>
			<p>No se pudo agregar el seguro.</p>
		<%}
	}
%>

<%
	if(request.getAttribute("verificarDescripcion")!=null){
		boolean verificarDescripcion=(boolean)request.getAttribute("verificarDescripcion");
		if(!verificarDescripcion){
			%>
			<p>No se pudo agregar el seguro. Máximo 200 caracteres en descripción.</p>
			<%
		}
	}
%>


<%
	if(request.getAttribute("verificarCostos")!=null){
		boolean verificarCostos=(boolean)request.getAttribute("verificarCostos");
		if(!verificarCostos){
			%>
			<p>No se pudo agregar el seguro. El costo máximo asegurado debe ser mayor al costo de contratación.</p>
			<%
		}
	}
%>



</form>

</body>
</html>