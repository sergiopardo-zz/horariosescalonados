<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.text.SimpleDateFormat"%>
    <%@ page import="com.google.appengine.api.datastore.Entity"%>
	<%@ page import="java.util.*"%>
	<%@ page import="java.lang.*"%>
    
	<% String sNombre = session.getAttribute("nombreCompleto")==null?"":(String)session.getAttribute("nombreCompleto");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Horarios Escalonados</title>
	<link rel="stylesheet" type="text/css" href="config/css/estilo.css" />
	<script type="text/javascript" src="config/js/jquery-1.11.3.min.js"></script>
	<script src='config/js/jquery-ui.min.js'></script>
	<script type="text/javascript" src="config/js/acciones.js"></script>
	<script type="text/javascript" src='config/js/calendar.js'></script>	
</head>

<body>
<div class="contenedor">
	
<!-- Inicia Encabezado -->
		<div class="cabecera">
		<a href="#" class="btnSalir"></a>
		<div class="cabCliente">
			<h2><%=sNombre%></h2>
		</div>
		<img src="/config/img/Ic_Usuario.png">
		<span class="cabTitulo">Horarios Escalonados</span>
	</div>
	<!-- Final Encabezado -->
	 
	<!-- Inicia Menu -->
	<div class="imgAdm">
		<div class="menuPrincipal">
				<ul class="home">
					<li  >
						<a id="btnHomeApagado" class="btnHome homeApagado"  href="/MainMenu"></a>
					</li>
					<li class="menu horario centro">
						<a href="reportesRHInternos.jsp" class="estiloMenu" >
							<span class="texto">Internos</span>
						</a>
					</li>
					<li class="menu adm centro">
						<a href="reportesRHExternos.jsp" class="estiloMenu">
							<span class="texto">Externos</span>
						</a>
							
					</li>
				</ul>
		</div>
	</div>	
	<!-- Final Menu -->
</html>