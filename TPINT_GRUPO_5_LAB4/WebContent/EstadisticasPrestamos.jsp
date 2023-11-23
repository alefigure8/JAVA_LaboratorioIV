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
		<%
		ArrayList<Provincia> listaDeProvincias = new ArrayList<Provincia>();
		
			int [] prestamosOtorgados = new int[12];
			int [] prestamosRechazados = new int[12];			
			int [] pagosRecibidos = new int [12];
			int cuotasMorosas[] = new int[12];
			double[] promedioTasa = new double[12];
			double [] montoTotalOtorgado = new double[12]; 
			double[] promedioMorosos = new double[12];
			double SumaGananciaenIntereses[] = new double[12];
			double sumaEnPagosCuotas[] = new double[12];
			double sumaEnMora[] = new double[12];
			
			int edadMinseleccionada=20;
			int edadMaxseleccionada=80;
			String sexoSeleccionado ="";
			String provinciaSeleccionada="";
			
	    	int acumCuotasMorosas=0;
			int acumOtorgados=0;
			int acumRechazados=0;
			int acumPagosRecibidos=0;
			double acumPromedioMorosos=0;
			double acumSumaGananciaenIntereses=0;
			double acumsumaEnPagosCuotas=0;
			double acumsumaEnMora=0;
			double acumPromedioTasa = 0;
			double acumMontoTotalOtorgado = 0;
			
			int anio=2023;
			 DecimalFormat formatodinero = new DecimalFormat("$#");
			 DecimalFormat formatoporcentaje = new DecimalFormat("#%");
	

			 
			 if (session.getAttribute("sexoSeleccionado")!=null){
				 
				 sexoSeleccionado = session.getAttribute("sexoSeleccionado").toString();
			 }

			 
 if (session.getAttribute("provinciaSeleccionada")!=null){
	 
	 provinciaSeleccionada = session.getAttribute("provinciaSeleccionada").toString();
 }
			 
		      if (session.getAttribute("promedioTasas")!=null){
			        promedioTasa = (double[])session.getAttribute("promedioTasas");
			        }
			        if (session.getAttribute("promedioMorosos")!=null){
			        promedioMorosos = (double[])session.getAttribute("promedioMorosos");
			        }
			        if (session.getAttribute("anio")!=null){
				        anio = (int)session.getAttribute("anio");
				        }
			        if (session.getAttribute("sumaEnMora")!=null){
			        	sumaEnMora = (double[])session.getAttribute("sumaEnMora");
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
			        if (session.getAttribute("pagosRecibidos")!=null){
			        	pagosRecibidos = (int[])session.getAttribute("pagosRecibidos");
				        }
			        if (session.getAttribute("cuotasMorosas")!=null){
			        	cuotasMorosas = (int[])session.getAttribute("cuotasMorosas");
				        }  		   
			    
			        if (session.getAttribute("sumaGananciaenIntereses")!=null){
			        	SumaGananciaenIntereses = (double[])session.getAttribute("sumaGananciaenIntereses");
				    
			        }
			        if (session.getAttribute("sumaEnPagosCuotas")!=null){
			        	sumaEnPagosCuotas = (double[])session.getAttribute("sumaEnPagosCuotas");
				        } 
						        
			        if (session.getAttribute("listaDeProvincias")!=null){
				 listaDeProvincias = (ArrayList<Provincia>)session.getAttribute("listaDeProvincias");
					     }      
				             
					
			        int mesconprestamo=0; 
			        
			        for(int i=0;i<12;i++){
			        	
			    	acumCuotasMorosas+=cuotasMorosas[i];
					acumOtorgados+=prestamosOtorgados[i];
					acumRechazados+=prestamosRechazados[i];
					acumPagosRecibidos+=pagosRecibidos[i];
					acumPromedioMorosos+=promedioMorosos[i];
					acumSumaGananciaenIntereses+=SumaGananciaenIntereses[i];
					acumsumaEnPagosCuotas+=sumaEnPagosCuotas[i];
					acumsumaEnMora+=sumaEnMora[i];
					
					if(promedioTasa[i]>0){
					acumPromedioTasa += promedioTasa[i];
					mesconprestamo+=1;
			        }
					acumMontoTotalOtorgado += montoTotalOtorgado[i];
			        }
					
			        acumPromedioTasa = acumPromedioTasa / mesconprestamo; 
					
			
			%>	
			
	
	    <div class="row flex-grow-1 m-0">
	      <!--SIDEBAR-->
	      <jsp:include page= "/WEB-INF/Components/menu.jsp">
	      	<jsp:param name="usuario" value="Ramón Ramirez" />
	      </jsp:include>
	      
	     <!--MAIN-->
	      <div class="col-lg-9 col-md-12 d-flex flex-column justify-content-center" >
	        <div class="w-100 pt-2" >
	          <h1 id="tituloCliente" style="margin-bottom:1%"> <i class="fas fa-chart-line me-2"></i>ESTADISTICAS PRESTAMOS</h1>
	        </div>
	        <div class="flex-grow-1" >   
	       
	            
	        
	        	        
      		<!--CONTENT-->
	        <div class="col-lg-10 col-md-12 d-flex flex-column" style="width:100%;">
	

	            <div class="card" style="padding-bottom:0px;padding-top:0px; margin-bottom: 5px; margin-top: 0px;"  >
	                <div class="card-body" style="padding-bottom:0px;padding-top:0px; margin-bottom: 0px; margin-top: 0px; ">
	         <form action="ServletEstadisticasPrestamos" method="get">
           	
	<div class="row mt-0 mb-0 d-flex align-items-center" >

	                    <div class="col-md-2"> <!-- Dividimos el formulario en 4 columnas para los 4 elementos -->
	              
	                        <label for="Año">Año:</label>
	                        
	                        <select name="Anio" id="Año" class="form-select">
	                            <option value="2023">2023</option>
	                            <option <%if(anio==2022){%> selected="selected" <%} %> value="2022">2022</option>
	                            <!-- Agrega más opciones según sea necesario -->
	                        </select>
	                    </div>
	                    
	                    	<% %>
	                    <div class="col-md-2">
	                        <label for="Sexo">Sexo:</label>
	                        <select Name="Sexo"id="Sexo" class="form-select">
	                            <option value="Indistinto" <%if(sexoSeleccionado.equals("Indistinto")){ %>selected="selected"<%} %>>Indistinto</option>
	                            <option value="Masculino" <%if(sexoSeleccionado.equals("Masculino")){ %> selected="selected"<%} %>>Masculino</option>
	                            <option value="Femenino"<%if(sexoSeleccionado.equals("Femenino")){ %> selected="selected"<%} %>>Femenino</option>
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
	                        	          <option value="Todas">Todas</option>
	                        <%for(Provincia provincia :listaDeProvincias){ %>
	                            <option name=Provincia <%if(provinciaSeleccionada.equals(provincia.getNombre())){ %> selected="selected" <%} %> value="<%=provincia.getNombre()%>"><%=provincia.getNombre()%></option>
	                                <%}%>
	                            
	                        </select>
	                    </div>
	                   <div class="col-md-2 d-flex flex-column position-relative"> 
    <button type="submit" name="btnReestablecer" class="btn btn-outline-primary" style="margin-top: 2px;">Reestablecer</button>
    <button type="submit" name="btnFiltrar" class="btn btn-primary btnEnviar" style="width: 100%; margin-top: 2px;">Filtrar</button>
    
</div>
	                   
    
    
</div>
</div>
	                     </form>     
		            </div>
		         
		        </div>
		    </div>
		 
    		<div>
				<div class="card overflow-y-auto mb-5">
		
		   <table style="table-layout: fixed; height: 300px; width: 100%;">
		       <thead>
<tr> 
    <th style="background-color: var(--color-main); color: whitesmoke; width: 150px; font-size: 12px; text-align: center;" >Concepto</th>
    <th style="background-color: var(--color-main); color: whitesmoke; width: auto; font-size: 12px; text-align: center;">Enero</th>
    <th style="background-color: var(--color-main); color: whitesmoke; width: auto; font-size: 12px;text-align: center;">Febrero</th>
    <th style="background-color: var(--color-main); color: whitesmoke; width: auto; font-size: 12px;text-align: center;">Marzo</th>
    <th style="background-color: var(--color-main); color: whitesmoke; width: auto; font-size: 12px;text-align: center;">Abril</th>
    <th style="background-color: var(--color-main); color: whitesmoke; width: auto; font-size: 12px;text-align: center;">Mayo</th>
    <th style="background-color: var(--color-main); color: whitesmoke; width: auto; font-size: 12px;text-align: center;">Junio</th>
    <th style="background-color: var(--color-main); color: whitesmoke; width: auto; font-size: 12px;text-align: center;">Julio</th>
    <th style="background-color: var(--color-main); color: whitesmoke; width: auto; font-size: 12px;text-align: center;">Agosto</th>
    <th style="background-color: var(--color-main); color: whitesmoke; width: 100px; font-size: 12px;text-align: center;">Septiembre</th>
    <th style="background-color: var(--color-main); color: whitesmoke; width: auto; font-size: 12px;text-align: center;">Octubre</th>
    <th style="background-color: var(--color-main); color: whitesmoke; width: auto; font-size: 12px;text-align: center;">Noviembre</th>
    <th style="background-color: var(--color-main); color: whitesmoke; width: auto; font-size: 12px;text-align: center;">Diciembre</th>
    <th style="background-color: var(--color-main); color: whitesmoke; width: auto; font-size: 12px; text-align: center;">+=</th> 
</tr>

</thead>

			
			
			        <tbody>
			        	   			        	
			        	<tr> <td style = "font-weight: bold; text-align: center; padding-top: 5px; padding-bottom: 5px;">Prestamos Otorgados  </td>  
				  	   <%  for(int i = 0; i<12;i++){if((anio==2023 && LocalDate.now().getMonthValue()-1<i)){%> <td style = "text-align: right ;">-</td>
				  	    <% } else { %> <td style = "text-align: right; font-weight: bolder;"> <%=prestamosOtorgados[i]%></td> <%}}%>  <td style = "text-align: right;font-weight: bolder; "><%=acumOtorgados%> </td> 
				            
				        
				                       
				           
				        
<tr> <td style = "font-weight: bold; text-align: center; padding-top: 5px; padding-bottom: 5px;"> Prestamos Rechazados</td>  
				      <% 	        for(int i = 0; i<12;i++ ){   if((anio==2023 && LocalDate.now().getMonthValue()-1<i) ||  prestamosRechazados[i]==0){%> <td style = "text-align: right ;">-</td>   <% } else { %> 
				               <td style = "text-align: right;"><%=prestamosRechazados[i] %></td> <%}} %> <td style = "text-align: right;font-weight: bolder; "><%=acumRechazados%> </td>
				        
		<tr> <td style = "font-weight: bold; text-align: center; padding-top: 5px; padding-bottom: 5px;">Total Otorgado </td> 
				          <% 	        for(int i = 0; i<12;i++){ 
				        	  if((anio==2023 && LocalDate.now().getMonthValue()-1<i )|| montoTotalOtorgado[i]==0){%> <td style = "text-align: right ;">-</td>   <% } else { %>
				               <td style = "text-align: right;"><%=formatodinero.format( montoTotalOtorgado[i])%></td><%}} %> <td style = "text-align: right;font-weight: bolder;"><%=formatodinero.format(acumMontoTotalOtorgado)%> </td>
				             
				             
				                  
				                  
				        <tr> <td style = "font-weight: bold; text-align: center;"> Ganancias en Interes</td>
				         <%	  for(int i = 0; i<12;i++){				       
				        	 if((anio==2023 && LocalDate.now().getMonthValue()-1<i) || SumaGananciaenIntereses[i]==0){%> <td style = "text-align: right ;">-</td>   <% } else { %>	
				        		
				        	<td style = "text-align: right;"><%=formatodinero.format(SumaGananciaenIntereses[i])%></td>
				        <%}} %>   <td style = "text-align: right;font-weight: bold;"><%=formatodinero.format(acumSumaGananciaenIntereses)%> </td>   </tr>                 
				          
				        				        
				    
				        
				        	        
		<tr> <td style = "font-weight: bold; text-align: center; padding-top: 5px; padding-bottom: 5px;"> Promedio Tasas Interes </td>
				         <%			  		        
				        
				        for(int i = 0; i<12;i++){
				        
				       	 if((anio==2023 && LocalDate.now().getMonthValue()-1<i) || promedioTasa[i]==0){%> <td style = "text-align: right ;">-</td>   <% } else { %>
				        					        
				        					        					        	
				               <td style = "text-align: right;"><%=formatoporcentaje.format(promedioTasa[i])%></td>
				        <%}} %>
				          <td style = "text-align: right;font-weight: bold; "><%=formatoporcentaje.format(acumPromedioTasa)%> </td></tr>
				         

				        
				     				        
<tr> <td style = "font-weight: bold; text-align: center; padding-top: 5px; padding-bottom: 5px;">Pagos recibidos </td>  
				          <% 	        for(int i = 0; i<12;i++){ 
				        		 if((anio==2023 && LocalDate.now().getMonthValue()-1<i) || pagosRecibidos[i]==0){%> <td style = "text-align: right ;">-</td>   <% } else { %>
				           <td style = "text-align: right;"><%=pagosRecibidos[i]%></td>         <%}} %>    <td style = "text-align: right;font-weight: bold; "><%=acumPagosRecibidos%> </td></tr>
				            
				        
				            <tr> <td style = "font-weight: bold; text-align: center; padding-top: 5px; padding-bottom: 5px;">Ingresos en Pagos </td> 
				              <% 	        for(int i = 0; i<12;i++){ 
				            	  if((anio==2023 && LocalDate.now().getMonthValue()-1<i) || sumaEnPagosCuotas[i]==0){%> <td style = "text-align: right ;">-</td>   <% } else { %>
				             <td style = "text-align: right;"><%=formatodinero.format(sumaEnPagosCuotas[i])%></td>	<%}} %> 
				       <td style = "text-align: right;font-weight: bold;"><%=formatodinero.format(acumsumaEnPagosCuotas)%> </td></tr>
				         
		<tr> <td style = "font-weight: bold; text-align: center; padding-top: 5px; padding-bottom: 5px;">Cuotas no Cobradas </td> 
				              <% 	        for(int i = 0; i<12;i++){ 
				            	  if(anio==2023 && LocalDate.now().getMonthValue()-1<i){%> <td style = "text-align: right ;">-</td>   <% } else { %>
				            <td style = "text-align: right;"><%=cuotasMorosas[i]%></td>          <%}} %> <td style = "text-align: right;font-weight: bold; "><%=acumCuotasMorosas%> </td></tr>
				         
	<tr> <td style = "font-weight: bold; text-align: center; padding-top: 5px; padding-bottom: 5px;">Monto no cobrado </td> 
				              <% 	        for(int i = 0; i<12;i++){ 
				            	  if(anio==2023 && LocalDate.now().getMonthValue()-1<i){%> <td style = "text-align: right ;">-</td>   <% } else { %>
				              <td style = "text-align: right;"><%=formatodinero.format(sumaEnMora[i])%></td>
				         <%}} %> <td style = "text-align: right;font-weight: bold; "><%=formatodinero.format(acumsumaEnMora)%> </td></tr>
				         </tr>
				         
				         		<tr> <td style = "font-weight: bold; text-align: center; padding-top: 5px; padding-bottom: 5px;">Vencimientos Incumplidos</td> 
				       		      <%	    for(int i = 0; i<12;i++){
				         if((anio==2023 && LocalDate.now().getMonthValue()-1<i) || promedioMorosos[i]==0){%> <td style = "text-align: right ;">-</td>   <% } else { %>
				      
				              <td style = "text-align: right;"><%=formatoporcentaje.format(promedioMorosos[i])%></td>
				             <%}} %> <td style = "text-align: right;font-weight: bold;"><%=formatoporcentaje.format((acumPromedioMorosos/12))%> </td></tr>
				        </tr>                   				            				            
				     
	
				         
				 
			          </tbody>
		    		</table>
					</div>
					<div class= "mt-2 mb-2">
						<a href="PerfilBanco.jsp" class="btn btn-primary btnEnviar "><i class="fa-solid fa-arrow-left me-4"></i>Regresar</a>
					</div>
					
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

