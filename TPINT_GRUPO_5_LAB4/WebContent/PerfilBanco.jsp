<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="body" tagdir="/WEB-INF/tags" %>

<body:wrapper>
      <!--CONTENT-->
      <div class="col-lg-9 col-md-12 d-flex flex-column justify-content-between">
        <div class="w-100 pt-2">
          <h1>MENU BANCO</h1>
        </div>
        <div class="flex-grow-1">
          <!-- CONTENIDO-->
		    <ul class="list-group" style="margin-top:10%">
		        <li class="list-group-item"><a href="ListadoClientes.jsp">Listado de Clientes</a></li>
		        <li class="list-group-item"><a href="AltaCliente.jsp">Alta de Cliente</a></li>
		        <li class="list-group-item"><a href="AltaCuentaClientes.jsp">Alta de Cuenta de Cliente</a></li>
		    </ul>
        </div>
      </div>
</body:wrapper>
