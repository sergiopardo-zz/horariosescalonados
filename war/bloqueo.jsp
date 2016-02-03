<%@ page contentType="text/html; charset=iso-8859-1" language="java"%>
<%
String sPerfil = request.getAttribute("tipo_empleado")==null ? "" : (String)request.getAttribute("tipo_empleado");
String sEdificio = request.getAttribute("edificio")==null ? "" : (String)request.getAttribute("edificio");
String sFechaSolicitud = request.getAttribute("fecha_solicitud")==null ? "" : (String)request.getAttribute("fecha_solicitud");
String sFechaAplicacion = request.getAttribute("fecha_aplicacion")==null ? "" : (String)request.getAttribute("fecha_aplicacion");
boolean bEdificio = false;
String sPantInicial = "bloqueo.html";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Horarios Escalonados</title>
</head>
<frameset rows="73,*" cols="*" frameborder="NO" border="0" framespacing="0"/> 
<frame src="top.html" name="top" scrolling="NO" noresize/> 
<frameset cols="330px;,*" frameborder="NO" border="0" framespacing="0"/> 
<frame name="menu" 
    src="MenuHE.jsp?Perfil=<%=sPerfil%>&Edificio=<%=sEdificio%>&FechSol=<%=sFechaSolicitud%>&FechApl=<%=sFechaAplicacion%>"
	name="frMenu" scrolling="NO" noresize/>
<frame src="<%=sPantInicial%>" name="ventana"/></frameset>
<noframes>
	<body>No se soportan Frames
	</body>
</noframes>
</html>