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
		      
      <!--CONTENIDO-->
        <div class="col-10 d-flex flex-column justify-content-between">
          <div class="w-100 pt-2">
            <!--TIUTLO PAGINA-->
            <h1 class="mt-2">NUEVA TRANSFERENCIA</h1>
          </div>
          <div class="flex-grow-1">
            <!--FORMULARIO BUSCAR CLIENTE-->
            <form action="" class="col-6 mt-4">
              <div class="mb-3">
                <label class="form-label" for="cbu">¿Cuál es el CBU?</label>
                <div class="d-flex align-content-center gap-4">
                  <input type="text" name="cbu" placeholder="11 números" class="form-control w-50" id="cbu">
                  <span class="form-text text-danger d-none">Debe tener 11 números.</span>
                </div>
              </div>
              <div class="w-50 d-flex justify-content-center">
                <input type="submit" class="btn btn_main" value="Buscar">
              </div>
            </form>

            <!--FORMULARIO TRANSFERENCIA-->
            <form action="" class="col-6 mt-5">
              <div class="text-center w-50 mb-4">
                <span class="fs-5 text-uppercase mb-3 d-none">Jerry Seinfeld</span>
              </div>
              <div class="mb-3">
                <label class="form-label" for="monto">Monto a transferir</label>
                <div class="d-flex align-content-center gap-4">
                  <input type="text" name="monto" placeholder="Punto para los decimales" class="form-control w-50" id="monto" disabled>
                  <span class="form-text text-danger d-none">No puede ser $0,00</span>
                </div>
              </div>
              <div class="mb-3">
                <label class="form-label" for="concepto">Concepto</label>
                <div class="d-flex align-content-center gap-4">
                  <input type="text" name="concepto" placeholder="Varios" class="form-control w-50" id="concepto" disabled>
                  <span class="form-text text-danger d-none">Debe tener menos de 20 caracteres</span>
                </div>
              </div>
              <div class="w-50 d-flex justify-content-center">
                <input type="submit" class="btn btn_main" value="Aceptar">
              </div>
            </form>
          </div>
        </div>


	       </div>
	 	<!--FOOTER-->
	    <jsp:include page= "/WEB-INF/Components/footer.html"></jsp:include>
	 </body>
</html>