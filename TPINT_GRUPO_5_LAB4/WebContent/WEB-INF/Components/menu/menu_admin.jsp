<!--SIDEBAR-->
<nav class="sidebar">
  <div class="w-100 d-flex justify-content-center">
    <span class="fw-bold mt-4 mb-4 fs-5 p-2 text-white bg-main"
      >Banco Cinco</span
    >
  </div>
  <div class="d-flex align-items-center ms-3 mb-4">
    <div class="me-2">
      <i
        class="fa-regular fa-user opacity-50 border border-opacity-50 border-1 border-black rounded-circle p-1"
      ></i>
    </div>
    <span class="fw-lighter"><%=request.getParameter("usuario")%></span>
  </div>
  <ul class="list-group border-0">
   <li class="list-group-item border-0 bg-transparent" id="inicio">
   	<a type="submit" href="PerfilBanco.jsp">Inicio</a>
   </li>
    <li class="list-group-item border-0 bg-transparent" id="cliente">
    	<a type="submit" href="ServletListarClientes?obtener=true&filtro=Activos">Clientes</a>
    </li>
    <li class="list-group-item border-0 bg-transparent" id="cuenta">
     <a type="submit" href="/TPINT_GRUPO_5_LAB4/ServletCuentasClientes?Cuentas=CuentasClientes">Cuentas</a>
    </li>
    
     <li class="list-group-item border-0 bg-transparent" id="prestamos">
     <a type="submit" href="ServletPrestamos?Prestamos=true">Prestamos</a>
    </li>
      <li class="list-group-item border-0 bg-transparent" id="estadisticaBanco">
     <a type="submit" href="ServletEstadisticasBancos">Estadisticas Banco</a>
    </li>
    <li class="list-group-item border-0 bg-transparent" id="estadisticaPrestamo">
     <a type="submit" href="ServletEstadisticasPrestamos?Estadisticas=Prestamos">Estadisticas Prestamos</a>
    </li>

  
    
    <!--  <li class="list-group-item border-0 bg-transparent">Prestamo</li> -->
    <!-- CERRAR SESSION -->
    <li class="list-group-item border-0 bg-transparent mt-4">
      <a type="submit" href="servletAutenticacion?sesion=cerrar">Cerrar Sesion</a>
    </li>
  </ul>
</nav>

<script>
	/* HOME */
	const home = "PerfilBanco.jsp";
	const home_2 = "servletLogin";
	
	/* CUENTA */
	const cuenta = "ServletCuentasClientes";
	const cuentaDetalle = "ServletDetalleCuenta";
	const cuentaDetalleMovimiento = "ServletDetalleMovimiento";
	
	/* CLIENTE */
	const listado = "ServletListarClientes";
	const clienteAlta = "ServletAltaCliente";

	
	/* PRESTAMOS */
	const prestamos = "ServletPrestamos";
	const prestamoSolicitar = "ServletPrestamos";
	const prestamosDetalle = "ServletPrestamosDetalle";
	
	/* ESTADISTICA */
	const estadisticaBanco = "ServletEstadisticasBancos";
	const estadisticaPrestamo= "ServletEstadisticasPrestamos";

	
	/* PAGINA ACTUAL */
	const paginaActual = window.location.pathname.split("/")[2];
	
	if(paginaActual == home || paginaActual == home_2){
		const perfilBtn = document.getElementById("inicio");
		perfilBtn.classList.add("fw-bold", "text-primary", "border-bottom");
	}
	
	if(paginaActual == listado || paginaActual == clienteAlta){
		const perfilBtn = document.getElementById("cliente");
		perfilBtn.classList.add("fw-bold", "text-primary", "border-bottom");
	}
	
	if(paginaActual == cuenta || paginaActual == cuentaDetalle || paginaActual == cuentaDetalleMovimiento){
		const perfilBtn = document.getElementById("cuenta");
		perfilBtn.classList.add("fw-bold", "text-primary", "border-bottom");
	}
	
	if(paginaActual == prestamos || paginaActual == prestamoSolicitar || paginaActual == prestamosDetalle){
		const perfilBtn = document.getElementById("prestamos");
		perfilBtn.classList.add("fw-bold", "text-primary", "border-bottom");
	}
	
	
	if(paginaActual == estadisticaBanco){
		const perfilBtn = document.getElementById("estadisticaBanco");
		perfilBtn.classList.add("fw-bold", "text-primary", "border-bottom");
	}
	
	if(paginaActual == estadisticaPrestamo){
		const perfilBtn = document.getElementById("estadisticaPrestamo");
		perfilBtn.classList.add("fw-bold", "text-primary", "border-bottom");
	}
	
</script>