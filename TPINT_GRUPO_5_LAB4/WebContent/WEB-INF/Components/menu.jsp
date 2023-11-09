<%@page import="entidad.TipoAcceso"%>
<%@page import="entidad.Usuario"%>

<% 
	if(session.getAttribute("usuario") != null){
	Usuario usuario = (Usuario)session.getAttribute("usuario");
	String usarioNombre = "ADMIN";

	if(usuario.getTipoAcceso() == TipoAcceso.Administrador){%>
		<jsp:include page= "/WEB-INF/Components/menu/menu_admin.jsp">
	      	<jsp:param name="usuario" value="<%=usarioNombre%>" />
	    </jsp:include>
	<%} else if (usuario.getTipoAcceso() == TipoAcceso.Cliente){%>
		<jsp:include page= "/WEB-INF/Components/menu/menu_cliente.jsp">
	      	<jsp:param name="usuario" value="<%=usarioNombre%>" />
	    </jsp:include>
	<%}
	}
%>
