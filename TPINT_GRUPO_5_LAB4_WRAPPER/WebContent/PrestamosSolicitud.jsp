<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="body" tagdir="/WEB-INF/tags" %>

<body:wrapper>
      <!--CONTENT-->
      <div class="col-lg-9 col-md-12 d-flex flex-column justify-content-between">
        <div class="w-100 pt-2">
          <h1>SOLICITUD DE PRESTAMO</h1>
        </div>
        <div class="flex-grow-1">
          <!-- CONTENIDO-->
       
       
      <div class="row d-flex ">
		    <div class="col-md-4 mx-auto">
		        <div class="form-group mt-2">
		            <label for="monto">Monto:</label>
		            <input type="text" class="form-control" id="monto" name="monto">
		        </div>
		        <div class="form-group mt-2">
		            <label for="cuotas">Cantidad de Cuotas:</label>
		            <select class="form-control" id="cuotas" name="cuotas">
		                <option >3 cuotas</option>
		                <option >6 cuotas</option>
		                <option >12 cuotas</option>
		                <option >24 cuotas</option>
		                <option >48 cuotas</option>
		            </select>
		        </div>
		        <div class="form-group mt-2">
		            <label for="interesTotal">Total de Intereses:</label>
		            <input type="text" class="form-control" id="interesTotal" name="interesTotal" disabled>
		        </div>
		        <div class="form-group mt-2">
		            <label for="totalMonto">Total (Monto + Intereses):</label>
		            <input type="text" class="form-control" id="totalMonto" name="totalMonto" disabled>
		        </div>
		        
		        <div class="form-group mt-2">
		            <label for="cuotas">Nro. de cuenta a depositar</label>
		            <select class="form-control" id="cuentas" name="cuentas">
		                <option >XXXX-XXXX-XXXX-1234</option>
		                <option >XXXX-XXXX-XXXX-1235</option>
		            </select>
		        </div>
		        
		           <input type="submit" class="btn btn-success mt-4" name="btnSolicitarPrestamo" value="Solicitar Prestamo" >
		        
		        
		    </div>
		</div>

       
       
			
		
			
        </div>
        
      </div>
</body:wrapper>