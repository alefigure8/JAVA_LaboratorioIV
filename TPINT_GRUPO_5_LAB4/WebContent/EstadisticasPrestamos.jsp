<%@page import="entidad.TipoAcceso"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="entidad.*"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!-- 
<jsp:include page="/WEB-INF/Components/autenticacion/autenticacion.jsp"> 
	<jsp:param name="TipoUsuarioPagina" value="<%=TipoAcceso.Administrador%>" />
</jsp:include>
 -->

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
		<%double[] promedioTasa = new double[12];
			double[] promedioMorosos = new double[12];
			int [] prestamosOtorgados = new int[12];
			int [] prestamosRechazados = new int[12];
			double [] montoTotalOtorgado = new double[12]; 
			int [] prestamosCancelados = new int[12];
			double [] pagosRecibidos = new double [12];
			double montoAcumuladoaPagarAnual[] = new double[12];
			ArrayList<Provincia> listaDeProvincias = null;
			
			 DecimalFormat formatodinero = new DecimalFormat("$#");
			 DecimalFormat formatoporcentaje = new DecimalFormat("#%");
			 
		      if (session.getAttribute("promedioTasas")!=null){
			        promedioTasa = (double[])session.getAttribute("promedioTasas");
			        }
			        if (session.getAttribute("promedioMorosos")!=null){
			        promedioMorosos = (double[])session.getAttribute("promedioMorosos");
			        }
			        if (session.getAttribute("prestamosOtorgados")!=null){
			        	prestamosOtorgados = (int[])session.getAttribute("prestamosOtorgados");
				        }
			        if (session.getAttribute("prestamosRechazados")!=null){
			        	prestamosRechazados = (int[])session.getAttribute("prestamosRechazados");
				        }
			        if (session.getAttribute("montoTotalOtorgado")!=null){
			        	montoTotalOtorgado = (double[])session.getAttribute("montoTotalOtorgado");
				        }
			        if (session.getAttribute("prestamosCancelados")!=null){
			        	prestamosCancelados = (int[])session.getAttribute("prestamosCancelados");
				        }
			        if (session.getAttribute("pagosRecibidos")!=null){
			        	pagosRecibidos = (double[])session.getAttribute("pagosRecibidos");
				        }  		       
			        if (session.getAttribute("montoAcumuladoaPagarAnual")!=null){
			        	montoAcumuladoaPagarAnual = (double[])session.getAttribute("montoAcumuladoaPagarAnual");
				        } 
			        
			        if (session.getAttribute("listaDeProvincias")!=null){
				 listaDeProvincias = (ArrayList<Provincia>)session.getAttribute("listaDeProvincias");
					        }      
				             
			        
					
					
			
			%>	
			
	
	    <div class="row flex-grow-1 m-0">
	      <!--SIDEBAR-->
	      <jsp:include page= "/WEB-INF/Components/menu.jsp">
	      	<jsp:param name="usuario" value="Ramón Ramirez" />
	      </jsp:include>
	        
	        	        
      		<!--CONTENT-->
	        <div class="col-lg-9 col-md-12 d-flex flex-column">
	

	            <div class="card" style="padding-bottom:0px;padding-top:0px; margin-bottom: 5px; margin-top: 0px;">
	                <div class="card-body" style="padding-bottom:0px;padding-top:0px; margin-bottom: 0px; margin-top: 0px;">
	         <form action="ServletEstadisticasPrestamos" method="get">
           	
<div class="row mt-0 mb-0 d-flex align-items-center">

	                    <div class="col-md-2"> <!-- Dividimos el formulario en 4 columnas para los 4 elementos -->
	              
	                        <label for="Año">Año:</label>
	                        
	                        <select name="Anio" id="Año" class="form-select">
	                            <option value="2023">2023</option>
	                            <option value="2022">2022</option>
	                            <!-- Agrega más opciones según sea necesario -->
	                        </select>
	                    </div>
	                    <div class="col-md-2">
	                        <label for="Sexo">Sexo:</label>
	                        <select Name="Sexo"id="Sexo" class="form-select">
	                            <option value="Masculino">Masculino</option>
	                            <option value="Femenino">Femenino</option>
	                        
	                        </select>
	                    </div>
	                    <div class="col-md-3">
	                        <label for="Rangoedad" style="margin-left:-20px;">Rango de Edad:</label>
	                               <div class="d-flex justify-content-start" style="margin-left: 15px; width:105%; margin-bottom: 0px;">
  
    <div class="col" style="border-left: 1px solid black;background-color:scrollbar; ">20</div>
    <div class="col" style="border-left: 1px solid black;background-color:scrollbar;">30</div>
    <div class="col" style="border-left: 1px solid black;background-color:scrollbar; ">40</div>
    <div class="col" style="border-left: 1px solid black;background-color:scrollbar; ">50</div>
    <div class="col" style="border-left: 1px solid black;background-color:scrollbar; ">60</div>
   <div class="col" style="border-left: 1px solid black; border-right: 1px solid black;background-color:scrollbar; ">70</div>
    <div class="col">80</div>
</div>
	                    
	                   <div class="d-flex justify-content-start" style="margin-left:-20px; margin-bottom: -5px;">  
	                    	       <label for="EdadMinima">Min:</label>  
	                    	     <input type="range" name="EdadMin" min="20" max="80" step="10" class="form-range" <% if(session.getAttribute("edadMinSeleccionada")==null){%>value="20"<%}else{%>value="<%=String.valueOf(session.getAttribute("edadMinSeleccionada"))%>"<%} %>>

	                    	       </div>                       
	                         <div class="d-flex justify-content-start" style="margin-top:-5px; margin-left:-23px; margin-bottom: 0px;">        
	                          
	                        <label for="EdadMaxima">Max:</label>
	                         <input type="range" name="EdadMax" min="20" max="80" step="10" class="form-range" <% if(session.getAttribute("edadMaxSeleccionada")==null){%>value="80"<%}else{%>value="<%=String.valueOf(session.getAttribute("edadMaxSeleccionada"))%>"<%} %>>
		 </div>                       
                   <div class="d-flex justify-content-start" style="margin-left: 15px; margin-top: 0px; width:104%;"> 
    <div class="col" style="border-left: 1px solid black;">20</div>
    <div class="col" style="border-left: 1px solid black; ">30</div>
    <div class="col" style="border-left: 1px solid black; ">40</div>
    <div class="col" style="border-left: 1px solid black; ">50</div>
    <div class="col" style="border-left: 1px solid black; ">60</div>
    <div class="col" style="border-right: 1px solid black; border-left: 1px solid black; ">70</div>
    <div class="col">80</div>
</div>


	                    </div>
	                    <div class="col-md-3">
	                        <label for="provinceSelect">Provincia:</label>
	                        
	                        <select id="provinceSelect" name="Provincia" class="form-select">
	                        	
	                        <%for(Provincia provincia :listaDeProvincias){ %>
	                            <option name=Provincia value="<%=provincia.getNombre()%>"><%=provincia.getNombre()%></option>
	                                <%}%>
	                            
	                        </select>
	                    </div>
	                   <div class="col-md-2 d-flex flex-column align-items-center position-relative"> 
    <button type="submit" name="btnFiltrar" class="btn btn-primary btnEnviar" style="width: 100%; margin-top: 2px;">Filtrar</button>
    
   
</div>

	                     </form>     
		            </div>
		         
		        </div>
		    </div>
		 
    		<div>
				<div class="card overflow-y-auto">
		
		   <table style="table-layout: fixed; height: 300px; width: 100%;">
		            <thead>
				        <tr> 
				            <th style="background-color: var(--color-main); color: whitesmoke;width:10%;">Concepto</th>
				            <th style="background-color: var(--color-main); color: whitesmoke;">Enero</th>
				            <th style="background-color: var(--color-main); color: whitesmoke;">Febrero</th>
				            <th style="background-color: var(--color-main); color: whitesmoke;">Marzo</th>
				            <th style="background-color: var(--color-main); color: whitesmoke;">Abril</th>
				            <th style="background-color: var(--color-main); color: whitesmoke;">Mayo</th>
				            <th style="background-color: var(--color-main); color: whitesmoke;">Junio</th>
				            <th style="background-color: var(--color-main); color: whitesmoke;">Julio</th>
				            <th style="background-color: var(--color-main); color: whitesmoke;">Agosto</th>
				            <th style="background-color: var(--color-main); color: whitesmoke; width:8%">Septiembre</th>
				            <th style="background-color: var(--color-main); color: whitesmoke;">Octubre</th>
				            <th style="background-color: var(--color-main); color: whitesmoke;">Noviembre</th>
				            <th style="background-color: var(--color-main); color: whitesmoke;">Diciembre</th> 
				        </tr>
			        </thead>
			
			
			        <tbody>
				        <tr> <td> Promedio Tasa de Interes </td>
				         
				        <%
				  		        
				        
				        for(int i = 0; i<12;i++){
				        
				        	if(LocalDate.now().getMonthValue()-1>=i && promedioTasa[i]>0){
				        	
				        %>
				        
				        					        					        	
				            <td><%=formatoporcentaje.format(promedioTasa[i])%></td>
				        <%}else { %> <td>N/A</td>  <%}} %>
				        </tr>   
				        <tr> <td>Indice de Morosidad </td> 
				       
				        <% 	        for(int i = 0; i<12;i++){
				        	  	if(LocalDate.now().getMonthValue()-1>=i && promedioMorosos[i]>0){%>
				            <td><%=formatoporcentaje.format(promedioMorosos[i])%></td>
				            <% } else { %> <td>N/A</td>  <%}} %>
				        </tr>
				      
				      
				        <tr> <td>Cantidad de Prestamos Otogrados </td>  
				  	   <% 	        for(int i = 0; i<12;i++){ 
				  	     	if(LocalDate.now().getMonthValue()-1>=i){%>
				            <td><%=prestamosOtorgados[i] %></td>
				            <% } else { %> <td>N/A</td>  <%}} %>
				            
				            				            
				         <tr> <td>Cantidad de Prestamos Rechazados </td>  
				      <% 	        for(int i = 0; i<12;i++){ 
				  	     	if(LocalDate.now().getMonthValue()-1>=i){%>
				            <td><%=prestamosRechazados[i] %></td>
				            <% } else { %> <td>N/A</td>  <%}} %>
				        
				        <tr> <td>Cantidad de Prestamos Cancelados al 100% </td> 
				      <% 	        for(int i = 0; i<12;i++){ 
				  	     	if(LocalDate.now().getMonthValue()-1>=i){%>
				            <td><%=prestamosCancelados[i] %></td>
				            <% } else { %> <td>N/A</td>  <%}} %>
				        
				        <tr> <td>Monto Total Otogrado </td> 
				          <% 	        for(int i = 0; i<12;i++){ 
				  	     	if(LocalDate.now().getMonthValue()-1>=i){%>
				            <td><%=formatodinero.format( montoTotalOtorgado[i]) %></td>
				            <% } else { %> <td>N/A</td>  <%}} %>
				        
				        <tr> <td>Monto en Pagos recibidos </td>  
				          <% 	        for(int i = 0; i<12;i++){ 
				  	     	if(LocalDate.now().getMonthValue()-1>=i){%>
				            <td><%=formatodinero.format(pagosRecibidos[i])%></td>
				            <% } else { %> <td>N/A</td>  <%}} %>   
				        <tr> <td>Monto a recibir acumulado Anual </td> 
				              <% 	        for(int i = 0; i<12;i++){ 
				  	     	if(LocalDate.now().getMonthValue()-1>=i){%>
				            <td><%=formatodinero.format(montoAcumuladoaPagarAnual[i])%></td>
				         <% } } %> 
				         </tr>
				 
			          </tbody>
		    		</table>
					</div>
				</div>
			</div>
		
		<!-- FIN MAIN -->
	 	<!--FOOTER-->
	    <jsp:include page= "/WEB-INF/Components/footer.html"></jsp:include>
	    
	    
	<%if(request.getAttribute("errorRangoEdad") != null){
			%>
			<jsp:include page="/WEB-INF/Components/popup.jsp">
				<jsp:param name="tipo" value="<%= request.getAttribute(\"tipo\") %>"/>
				<jsp:param name="mensaje" value="<%= request.getAttribute(\"mensaje\") %>"/>
				<jsp:param name="titulo" value="<%= request.getAttribute(\"titulo\") %>"/>
			</jsp:include>
		<% } %>
	 </body>
</html>

