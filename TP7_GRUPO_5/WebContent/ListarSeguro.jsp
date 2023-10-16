<%@page import="dominio.SeguroDao"%>
<%@page import="dominio.Seguro"%>
<%@page import="dominio.TipoSeguro"%>
<%@page import="java.util.List"%>
<%@page import="dominio.TipoSeguroDao"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="java.text.DecimalFormatSymbols"%>
<%@ page import="java.util.Locale"%>

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

            <!-- SELECT CON TIPOS DE SEGUROS -->
            <select name="selectTipoSeguro">>
            
             	<option value="todos">Todos los Seguros</option>
                <%
                    TipoSeguroDao tipoSeguroDao = new TipoSeguroDao();
                    List<TipoSeguro> listaTipoSeguro = tipoSeguroDao.readAll();

                    for (TipoSeguro ts : listaTipoSeguro) {
                    
                    	int opcion= 0;
                    	if (request.getAttribute("opcionselect")!= null){
                    		
                    		opcion = (int)request.getAttribute("opcionselect");
                    	}
                    	
                    	
                    	if (ts.getIdTipo() == opcion) {
                    		%>    		
                <option value="<%= ts.getIdTipo() %>" selected><%= ts.getDescripcion()  %> </option>    		
                    		<%
                    	} else {
                    	
                    	 %>

                <option value="<%= ts.getIdTipo() %>"><%= ts.getDescripcion() %> </option>

                <%
                    }}
                %>
               
            </select>

            <input type="submit" name="btnFiltrar" value="Filtrar" />
        </form>
    </div>

    <table style="width: 100%;">
        <tr>
            <th>ID Seguro</th>
            <th> Descripción seguro</th>
            <th>Descripción tipo seguro</th>
            <th>Costo contratación</th>
            <th>Costo máximo asegurado</th>
        </tr>

	<!-- LLAMAR DESDE BASE DE DATOS -->
        <%
        	ArrayList<Seguro> listaSeguros = null;
   		 Locale argentinaLocale = new Locale("es", "AR");                
       	 DecimalFormatSymbols symbols = new DecimalFormatSymbols(argentinaLocale);
        	DecimalFormat decimalFormat = new DecimalFormat("$#,##0.00", symbols);
        	String numFormateado;
            if (request.getAttribute("listaSeguros") != null) {
                listaSeguros = (ArrayList<Seguro>) request.getAttribute("listaSeguros");

                for (Seguro seguro : listaSeguros){
                	
                	String descripcion = "";
                	for (TipoSeguro tp : listaTipoSeguro){
                		
                		if(seguro.getIdTipo()==tp.getIdTipo()){
                			
                		descripcion = tp.getDescripcion();
                		}
                	}
                	
        %>
        <tr>
            <td style="width:5%;"><%= seguro.getIdSeguro() %></td>
            <td style="width:50%;"><%= seguro.getDescripcion() %></td>
            <td style="width:15%;text-align: center;"><%= descripcion%></td>
            <td style="width:15%;text-align: right;"><%= numFormateado=decimalFormat.format(seguro.getCostoContratacion()) %></td>
            <td style="width:15%;text-align: right;"><%= numFormateado=decimalFormat.format(seguro.getCostoAsegurado()) %></td>
        </tr>
        <%
            }
        }
        %>
    </table>

    <!-- ESTILOS -->
    <style>
        table,
        td,
        tr {
            border: 1px solid black;
            width: 50%;
        }

        tr {
            font-weight: bold;
            font-size: 16px;
        }

        td,
        tr {
            padding: 5px;
        }

        p {
            display: inline;
        }

        div {
            margin-bottom: 10px;
        }
    </style>
</body>
</html>
