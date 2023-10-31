<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="body" tagdir="/WEB-INF/tags" %>

<body:wrapper>
      <!--CONTENT-->
      <div class="col-lg-9 col-md-12 d-flex flex-column justify-content-between">
        <div class="w-100 pt-2">
          <h1>DETALLE DE PRESTAMO</h1>
        </div>
        <div class="flex-grow-1">
          <!-- CONTENIDO-->
       
			<div class="text-center">
			    <p>Nro. Prestamo: 002</p>
			    <p>Fecha de prestamo: 01/01/2023</p>
			    <p>Nro. Cuenta del Prestamo: XXXX-XXXX-XXXX-1234</p>
			    <p>Estado: Aprobado</p>
			    <p>Saldado: No</p>
			    <p>Importe solicitado: $75.000</p>
			    <p>Importe con intereses: $150.000 </p>
			    <p>Cantidad de Cuotas: 6</p>
			</div>

<!-- Cuadro para detalles de cuotas -->
		<div>
		    <h2>Detalles de Cuotas</h2>
		    <table id="table_id" class="table display text-center">
		        <thead>
		            <tr>
		                <th>Número de Cuota</th>
		                <th>Importe</th>
		                <th>Estado</th>
		                <th>Nro. Cuenta de pago</th>
		                <th>Fecha de Pago</th>
		                <th>Accion</th>
		            </tr>
		        </thead>
		        <tbody>
		            <tr>
		                <td>1</td>
		                <td>$25.000</td>
		                <td>Pagado</td>
		                <td>XXXX-XXXX-XXXX-1234</td>
		                <td>01/02/2023</td>
		                <td>-</td>
		                
		            </tr>
		            <tr>
		                <td>2</td>
		                <td>$25.000</td>
		                <td>Pagado</td>
		                 <td>XXXX-XXXX-XXXX-1234</td>
		                <td>01/03/2023</td>
		                 <td>-</td>
		                
		            </tr>
		            <tr>
		                <td>3</td>
		                <td>$25.000</td>
		                <td>Pagado</td>
		                <td>XXXX-XXXX-XXXX-1234</td>
		                <td>01/04/2023</td>
		                 <td>-</td>
		               
		            </tr>
		             <tr>
		                <td>4</td>
		                <td>$25.000</td>
		                <td>Pagado</td>
		                <td>XXXX-XXXX-XXXX-1234</td>
		                <td>01/05/2023</td>
		                 <td>-</td>
		                
		            </tr>
		             <tr>
		                <td>5</td>
		                <td>$25.000</td>
		                <td>Pagado</td>
		                <td>XXXX-XXXX-XXXX-1234</td>
		                <td>01/06/2023</td>
		                 <td>-</td>
		                
		            </tr>
		             <tr>
		                <td>6</td>
		                <td>$25.000</td>
		                <td>Impago</td>
		                <td>-</td>
		                 <td>-</td>
		                <td>
			               <input type="submit" class="btn btn-primary btnEnviar" name="btnPagarPrestamo" value="Pagar">
			            </td>
			            
		            </tr>
		        </tbody>
		    </table>
		</div>


			
			
			
			
        </div>
        
      </div>
</body:wrapper>