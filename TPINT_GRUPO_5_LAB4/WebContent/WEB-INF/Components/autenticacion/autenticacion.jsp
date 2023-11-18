
<%@page import="entidad.Usuario"%>
<%@page import="java.io.IOException"%>

<%

    // AUTENTICACIÓN
    session = request.getSession();

    if (session.getAttribute("usuario") == null) {
    	
        // Borramos lo que haya en la sesión
        try {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/servletAutenticacion?sesion=error");
            dispatcher.forward(request, response);
        } catch(Exception e){
        	e.printStackTrace();
        }
    } 
    
    /** PERMISOS **/
    if(session.getAttribute("usuario") != null){
    	Usuario usuario = (Usuario)session.getAttribute("usuario");
    	
	    if (request.getParameter("TipoUsuarioPagina") != null && request.getParameter("TipoUsuarioPagina").compareTo(usuario.getTipoAcceso().toString()) != 0) {
	    	try {
	            RequestDispatcher dispatcher = request.getRequestDispatcher("/servletAutenticacion?sesion=permiso");
	            dispatcher.forward(request, response);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
    }

%>
