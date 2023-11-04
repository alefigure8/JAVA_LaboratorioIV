<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="body" tagdir="/WEB-INF/tags" %>

<body:wrapper>
      <!--CONTENIDO-->
        <div class="col-10 d-flex flex-column justify-content-between">
          <div class="w-100 pt-2">
            <!--TIUTLO PAGINA-->
            <h1 class="mt-2">PAGO PRESTAMO</h1>
          </div>
          <div class="flex-grow-1">
            <!--FILTRO-->
            <div class="d-flex flex-md-row flex-column w-100 gap-2 mt-4">
                <h4 class="opacity-75">Prestamos #123456</h4>
            </div>

            <!--TABLA-->
            <div class="d-flex flex-md-row flex-column">
              <div class="mt-4 border border-1 border-black border-opacity-25 rounded-1 p-2 mb-4" style="min-width: 300px;">
                <div class="col-7 d-flex justify-content-between w-100 align-items-center mb-2">
                  <h4 class="opacity-75 m-0">Detalle</h4>
                </div>
                <div>
                  <p class="mb-0">Cuota</p>
                  <p class="fs-5">3/12</p>
                  <p class="mb-0">Estado</p>
                  <p class="fs-5">Pendiente</p>
                  <p class="mb-0">Fecha de Vencimiento</p>
                  <p class="fs-5">28/10/2023</p>
                  <p class="mb-0">Monto</p>
                  <p class="fs-5">$1.950,00</p>
                  <p class="mb-0">Interés</p>
                  <p class="fs-5">$295,00</p>
                  <p class="mb-0">Total a pagar</p>
                  <p class="fs-3 fw-bold text-secondary">$2.245,00</p>
                  <p class="mb-0">Pagando desde</p>
                  <select name="Estados" class="form-select form-select-sm w-md-50">
                    <option value="Realiazada">CA N° 123456789 - $12.595,00</option>
                    <option value="Rechazada">CC N° 987654321 - $9.785,07</option>
                    <option value="Pendiente">CAC N° 987651234 - $152,15</option>
                  </select>
                  <input type="button" class="btn d-inline w-100 mt-4" value="Pagar">
                </div>
              </div>
              
            </div>
          
          </div>
        </div>
</body:wrapper>