<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="body" tagdir="/WEB-INF/tags" %>

<body:wrapper>

      <!--CONTENT-->
      <div class="col-lg-9 col-md-12 d-flex flex-column justify-content-between">
        <div class="w-100 pt-2">
          <h1>Listado Clientes</h1>
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
		                <th>Nacionalidad</th>
		                <th>Fecha de Nacimiento</th>
		                <th>Correo</th>
		                <th>Telefono</th>
		                <th>Modificar Clave</th>
		                <th>Eliminar Cliente</th>
		            </tr>
		        </thead>
		        <tbody>
		             <tr>
		             <td>JuanPerez200</td>
            <td>Juan Pérez</td>
            <td>12345678</td>
            <td>20-12345678-9</td>
            <td>Argentino</td>
            <td>01/02/1985</td>
            <td>juan.perez@example.com</td>
            <td>555-123-4567</td>
            <td><button>Modificar</button></td>
            <td><button>Eliminar</button></td>
        </tr>
        <tr>
        <td>MARIA200</td>
            <td>Maria López</td>
            <td>98765432</td>
            <td>27-98765432-4</td>
            <td>Mexicana</td>
            <td>15/07/1990</td>
            <td>maria.lopez@example.com</td>
            <td>555-987-6543</td>
            <td><button>Modificar</button></td>
            <td><button>Eliminar</button></td>
        </tr>
        <tr>
        <td>CARLO1000</td>
            <td>Carlos Rodríguez</td>
            <td>55443322</td>
            <td>35-55443322-7</td>
            <td>Español</td>
            <td>10/11/1980</td>
            <td>carlos.rodriguez@example.com</td>
            <td>555-554-4332</td>
            <td><button>Modificar</button></td>
            <td><button>Eliminar</button></td>
        </tr>
        <tr>
        <td>LARUA200</td>
            <td>Laura Smith</td>
            <td>65432123</td>
            <td>42-65432123-8</td>
            <td>Estadounidense</td>
            <td>25/05/1995</td>
            <td>laura.smith@example.com</td>
            <td>555-654-3212</td>
            <td><button>Modificar</button></td>
            <td><button>Eliminar</button></td>
        </tr>
        <tr>
        <td>ALI0</td>
            <td>Mohammed Ali</td>
            <td>11223344</td>
            <td>68-11223344-3</td>
            <td>Egipcio</td>
            <td>03/08/1988</td>
            <td>mohammed.ali@example.com</td>
            <td>555-112-2334</td>
            <td><button>Modificar</button></td>
            <td><button>Eliminar</button></td>
        </tr>
        <tr>
        <td>078iiuuy0</td>
            <td>Sophie Dupont</td>
            <td>78901234</td>
            <td>52-78901234-1</td>
            <td>Francés</td>
            <td>12/09/1993</td>
            <td>sophie.dupont@example.com</td>
            <td>555-789-0123</td>
            <td><button>Modificar</button></td>
            <td><button>Eliminar</button></td>
        </tr>
		        </tbody>
		    </table>
		</div>


			
			
			
			
        </div>
        
      </div>
</body:wrapper>