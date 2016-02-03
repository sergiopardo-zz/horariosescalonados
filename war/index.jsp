<%@ page contentType="text/html; charset=iso-8859-1" language="java"%>
<%
String sNombre = request.getAttribute("nombre")==null?"":(String)request.getAttribute("nombre");
String sPerfil = request.getAttribute("tipo_empleado")==null ? "" : (String)request.getAttribute("tipo_empleado");
String sEdificio = request.getAttribute("edificio")==null ? "" : (String)request.getAttribute("edificio");
String sFechaSolicitud = request.getAttribute("fecha_solicitud")==null ? "" : (String)request.getAttribute("fecha_solicitud");
String sFechaAplicacion = request.getAttribute("fecha_aplicacion")==null ? "" : (String)request.getAttribute("fecha_aplicacion");
String sHorarioActual = request.getAttribute("hora_actual")==null ? "" : (String)request.getAttribute("hora_actual");
boolean bEdificio = sEdificio.trim().equals("REFORMA") || sEdificio.trim().equals("POLANCO");
String sPantInicial = bEdificio ? "menuv1.jsp" : "bloqueo.html";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Horarios Escalonados</title>
	<link rel="stylesheet" type="text/css" href="config/css/estilo.css"/>
	<script type="text/javascript" src="config/js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="config/js/acciones.js"></script>
<frameset rows="*" cols="*" frameborder="NO" border="0" framespacing="0"/> 
<!-- <frame src="menuv1.jsp" name="top" noresize src="bienvenida.jsp"/>-->
<!--<frame src="top.html" name="top" scrolling="NO" noresize/> -->
<!-- <frameset cols="330px;,*" frameborder="NO" border="0" framespacing="0"/>--> 
<!-- <frame name="menu" 
    src="MenuHE.jsp?Perfil=<%=sPerfil%>&Edificio=<%=sEdificio%>&FechSol=<%=sFechaSolicitud%>&FechApl=<%=sFechaAplicacion%>&HoraActual=<%=sHorarioActual%>"
	name="frMenu" scrolling="NO" noresize/>--->
<frame src="<%=sPantInicial%>" name="ventana"/></frameset>
<noframes>
	<body>No acepta frames	
	</body>
</noframes>
</html>