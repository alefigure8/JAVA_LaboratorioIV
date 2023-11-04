<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="body" tagdir="/WEB-INF/tags" %>

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
	<!-- HEAD -->
	<% 
		//TITULO DEL ARCHIVO COMO TITULO DE LA PAGINA
		String PATH = request.getRequestURI().split("/")[2];
		String[] palabras = PATH.split("(?=[A-Z]|\\s)");
		String URL = String.join(" ", palabras).split(".jsp")[0];
	%>
	<jsp:include page= "/WEB-INF/Components/head.jsp">
		<jsp:param name="titulo" value="<%=URL%>"/>
	</jsp:include>
	<body class="d-flex flex-column">
		    <div class="row flex-grow-1 m-0">
		      <!--SIDEBAR-->
		      <jsp:include page= "/WEB-INF/Components/menu_cliente.jsp">
		      	<jsp:param name="usuario" value="Ramón Ramirez" />
		      </jsp:include>
		      
		      
      <!--CONTENT-->
      <div class="col-lg-9 col-md-12 d-flex flex-column justify-content-between">
        <div class="w-100 pt-2">
          <h1>SOLICITUD DE ALTA DE CUENTA</h1>
        </div>
        <div class="flex-grow-1">
          <!-- CONTENIDO-->
       
<!-- Cuadro para detalles de cuotas -->
		<div>
		    <table id="table_id" class="table display text-center">
		        <thead>
		            <tr>
		                <th>Nombre de Usuario</th>
		                <th>Nombre </th>
		                <th>DNI</th>
		                <th>CUIL</th>
		                <th>Tipo de Cuenta</th>
		                
		                <th>Aprobar</th>
		                <th>Rechazar</th>
		            </tr>
		        </thead>
		        <tbody>
	             <tr>
			        <td>usuario1</td>
			        <td>Juan Pérez</td>
			        <td>12345678</td>
			        <td>20-12345678-9</td>
			        <td>Caja de Ahorro</td>
			        <td><button>Aprobar</button></td>
			        <td><button>Rechazar</button></td>
			    </tr>
			    <tr>
			        <td>usuario2</td>
			        <td>Maria López</td>
			        <td>98765432</td>
			        <td>27-98765432-4</td>
			        <td>Cuenta Corriente</td>
			        <td><button>Aprobar</button></td>
			        <td><button>Rechazar</button></td>
			    </tr>
			    <tr>
			        <td>usuario3</td>
			        <td>Carlos Rodríguez</td>	
			        <td>55443322</td>
			        <td>35-55443322-7</td>
			        <td>Caja de Ahorro</td>
			        <td><button>Aprobar</button></td>
			        <td><button>Rechazar</button></td>
			    </tr>
			    <tr>
			        <td>usuario4</td>
			        <td>Laura Smith</td>
			        <td>65432123</td>
			        <td>42-65432123-8</td>
			        <td>Cuenta Corriente</td>
			        <td><button>Aprobar</button></td>
			        <td><button>Rechazar</button></td>
			    </tr>
			    <tr>
			        <td>usuario5</td>
			        <td>Mohammed Ali</td>
			        <td>11223344</td>
			        <td>68-11223344-3</td>
			        <td>Caja de Ahorro</td>
			        <td><button>Aprobar</button></td>
			        <td><button>Rechazar</button></td>
			    </tr>
			    <tr>
			        <td>usuario6</td>
			        <td>Sophie Dupont</td>
			        <td>78901234</td>
			        <td>52-78901234-1</td>
			        <td>Cuenta Corriente</td>
			        <td><button>Aprobar</button></td>
			        <td><button>Rechazar</button></td>
			    </tr>
			</tbody>
Estos son ingresos ficticios en la tabla, y puedes personalizarlos con datos reales o de prueba según tus necesidades.

			        </tbody>
			    </table>
			</div>
        </div>
      </div>


	       </div>
	 	<!--FOOTER-->
	    <jsp:include page= "/WEB-INF/Components/footer.html"></jsp:include>
	 </body>
</html>
           