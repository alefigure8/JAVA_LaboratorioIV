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
    <a type="submit" href="ServletHomeCliente?homecliente=true">Inicio</a>
    </li>
    
    <li class="list-group-item border-0 bg-transparent" id="cuenta">
	<a type="submit" href="ServletCuentas?Cuentas=true">Cuentas</a>
	</li>
    
    
    <li class="list-group-item border-0 bg-transparent" id="transferencia">
    <a type="submit" href="ServletListaTransferencias?listado=true&todos=true">Transferencias</a>
    </li>
    
    
   <li class="list-group-item border-0 bg-transparent" id="prestamos">
     <a type="submit" href="ServletPrestamos?PrestamosCliente=true">Prestamos</a>
    </li>
    
     <li class="list-group-item border-0 bg-transparent" id="perfil">
     <a type="submit" href="Perfil.jsp">Perfil</a>
    </li>
    
    <!-- CERRAR SESSION -->
    <li class="list-group-item border-0 bg-transparent mt-4">
      <a type="submit" href="/TPINT_GRUPO_5_LAB4/servletAutenticacion?sesion=cerrar">Cerrar Sesion</a>
    </li>
  </ul>
  
  
</nav>

<script>
	/* HOME */
	const home = "ServletHomeCliente";
	const home_dos = "servletLogin";
	
	/* CUENTA */
	const cuenta ="ServletCuentas";
	const cuentaDetalle = "ServletDetalleCuenta";
	const cuentaDetalleMovimiento = "ServletDetalleMovimiento";
	
	/* TRANSFERENICIA */
	const transferencias = "ServletListaTransferencias";
	const transferenciaNueva = "ServletNuevaTransferencia";
	const transferenciaDetalle = "ServletDetalleTransferencia";
	
	/* PRESTAMOS */
	const prestamos = "ServletPrestamos";
	const prestamoSolicitar = "ServletPrestamos";
	const prestamosDetalle = "ServletPrestamosDetalle";
	
	/* PERFIL */
	const perfil = "Perfil.jsp";
	
	/* PAGINA ACTUAL */
	const paginaActual = window.location.pathname.split("/")[2];
	
	if(paginaActual == home || home_dos == paginaActual){
		const perfilBtn = document.getElementById("inicio");
		perfilBtn.classList.add("fw-bold", "text-primary", "border-bottom");
	}
	
	if(paginaActual == cuenta || paginaActual == cuentaDetalle || paginaActual == cuentaDetalleMovimiento){
		const perfilBtn = document.getElementById("cuenta");
		perfilBtn.classList.add("fw-bold", "text-primary", "border-bottom");
	}
	
	if(paginaActual == transferencias || paginaActual == transferenciaNueva || paginaActual == transferenciaDetalle){
		const perfilBtn = document.getElementById("transferencia");
		perfilBtn.classList.add("fw-bold", "text-primary", "border-bottom");
	}
	
	if(paginaActual == prestamos || paginaActual == prestamoSolicitar || paginaActual == prestamosDetalle){
		const perfilBtn = document.getElementById("prestamos");
		perfilBtn.classList.add("fw-bold", "text-primary", "border-bottom");
	}
	
	if(paginaActual == perfil){
		const perfilBtn = document.getElementById("perfil");
		perfilBtn.classList.add("fw-bold", "text-primary", "border-bottom");
	}
	
</script>