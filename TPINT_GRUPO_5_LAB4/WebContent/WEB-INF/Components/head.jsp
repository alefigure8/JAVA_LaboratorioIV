 <head>
   <meta charset="UTF-8" />
   <meta name="viewport" content="width=device-width, initial-scale=1.0" />
   <title><%=request.getParameter("titulo")%></title>
   <!--FONTAWESOME-->
   <link
     rel="stylesheet"
     href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
     integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
     crossorigin="anonymous"
     referrerpolicy="no-referrer"
   />
   <!--BOOSTRAP-->
   <link
     href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
     rel="stylesheet"
     integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
     crossorigin="anonymous"
   />
   <link rel="preconnect" href="https://fonts.googleapis.com" />
   <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
   <!--GOOGLE FONTS-->
   <link
     href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600&display=swap"
     rel="stylesheet"
   />
   
	<!-- AGREGAMOS LINK A CSS -->
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/Styles.css">
	
	<!-- TABLA PAGINADA -->
	<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
	
	<!-- DATATABLE -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
	<script type="text/javascript" charset="utf8"
		src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
	
	<script type="text/javascript">
		//Sin Ordenar
		$(document).ready(function() {
			$('#table_id').DataTable({
			   "language": {
		        	"url": "//cdn.datatables.net/plug-ins/1.10.15/i18n/Spanish.json"
		      	}
			});
		});
		
		//Ordenar Columna 0
		$(document).ready(function() {
			$('#table_id_2').DataTable({
			   "language": {
		        	"url": "//cdn.datatables.net/plug-ins/1.10.15/i18n/Spanish.json"
		      	},
		      	"order": [[0, "desc"]]
			});
		});
		
		//Ordenar Columna por fecha y por id
		$(document).ready(function() {
		    $('#table_id_3').DataTable({
		        "language": {
		            "url": "//cdn.datatables.net/plug-ins/1.10.15/i18n/Spanish.json"
		        },
		        "order": [
		            [0, "desc"], 
		            [3, "desc"]  
		        ]
		    });
		});
	</script>
 </head>