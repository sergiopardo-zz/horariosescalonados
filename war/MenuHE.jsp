<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="java.text.SimpleDateFormat"%>
<%@ page contentType="text/html; charset=iso-8859-1" language="java"%>
<%@ page import="com.google.appengine.api.datastore.Entity"%>
<%@ page import="java.util.*"%>
<%@ page import="java.lang.*"%>
<%
String sPerfil = request.getParameter("Perfil")==null ? "" : (String)request.getParameter("Perfil");
String sEdificio = request.getParameter("Edificio")==null ? "" : (String)request.getParameter("Edificio");
//String sFechaSolicitud = request.getAttribute("fecha_solicitud")==null ? "" : (String)request.getAttribute("fecha_solicitud");
//String sFechaAplicacion = request.getAttribute("fecha_aplicacion")==null ? "" : (String)request.getAttribute("fecha_aplicacion");
String sFechaSolicitud = request.getParameter("FechSol")==null ? "" : (String)request.getParameter("FechSol");
String sFechaAplicacion = request.getParameter("FechApl")==null ? "" : (String)request.getParameter("FechApl");
String sHorarioActual = request.getParameter("HoraActual")==null ? "" : (String)request.getParameter("HoraActual");
Calendar calFechaActual = new GregorianCalendar(); // Se obtiene la fecha actual
Calendar calFechaSolicitud = new GregorianCalendar(); // Se crea Calendar para la fecha de Solicitud
Calendar calFechaAplicacion = new GregorianCalendar(); // Se crea Calendar para la fecha de Aplicación
Calendar calFechaProxSol = new GregorianCalendar(); // Se crea Calendar para la fecha de la próxima modificación/baja

boolean bEdificio = sEdificio.trim().equals("REFORMA") || sEdificio.trim().equals("POLANCO");
boolean bMostrarAdministracion = bEdificio && (sPerfil.equals("A")|| sPerfil.equals("SS"));
boolean bMostrarSuperAdministrador = sPerfil.equals("SS");

boolean bNuevaSolicitud = sFechaSolicitud.equals("");
boolean bHorarioActual = sHorarioActual.equals("00000000");

if(!bNuevaSolicitud) {
	int iAnioSol = Integer.parseInt(sFechaSolicitud.substring(0,3));
	int iMesSol = Integer.parseInt(sFechaSolicitud.substring(5,6));
	int iDiaSol = Integer.parseInt(sFechaSolicitud.substring(8,9));
	calFechaSolicitud = new GregorianCalendar(iAnioSol,iMesSol-1,iDiaSol); // Se carga la fecha de Aplicación
}

if(!sFechaAplicacion.equals("")) {
	int iAnioAp = Integer.parseInt(sFechaAplicacion.substring(0,3));
	int iMesAp = Integer.parseInt(sFechaAplicacion.substring(5,6));
	int iDiaAp = Integer.parseInt(sFechaAplicacion.substring(8,9));
	calFechaAplicacion = new GregorianCalendar(iAnioAp,iMesAp-1,iDiaAp); // Se carga la fecha de Aplicación
}

boolean bSolicitudCambio = calFechaSolicitud.after(calFechaAplicacion);
boolean bSolicitudPendiente = ((!bNuevaSolicitud && sFechaAplicacion.equals("")) || bSolicitudCambio);

if(!bSolicitudPendiente) {
	calFechaProxSol = calFechaAplicacion;
	calFechaProxSol.add(Calendar.MONTH,3); // Se suman 3 meses a la fecha
	//calFechaProxSol.add(Calendar.HOUR,1);
}

//true si ya se cumplieron los tres meses de lapso
//boolean bMasDe3Meses = calFechaActual.after(calFechaProxSol);
boolean bMasDe3Meses = true;
//true si el edificio es correcto y se cumplieron mas de tres meses
boolean validacion1 = bEdificio && bMasDe3Meses;
//true si es una nueva solicitud y es diferente de "0000000"
boolean validacion2 = bEdificio && bNuevaSolicitud && !bHorarioActual;

//perite solicitud si corresponde al edificio es nueva solicitud y no es cambio
boolean bPermitirSolicitud = bEdificio && bNuevaSolicitud && bHorarioActual;

boolean bPermitirCambio = validacion1 || validacion2;
boolean bPermitirBaja = bEdificio && bMasDe3Meses;

String sHrefCambio = bPermitirCambio ? "/cambiohorario" : "bloqueo.html";
String sHrefBaja = bPermitirCambio ? "horario/baja.html" : "bloqueo.html";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Menu Horarios Escalonados</title>
<link href="/config/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<!--p>sPerfil = <%=sPerfil%></p>
	<p>sEdificio = <%=sEdificio%></p>
	<p>sFechaSolicitud = <%=sFechaSolicitud%></p>
	<p>sFechaAplicacion = <%=sFechaAplicacion%></p-->
	<div id="header">
		<h1>&nbsp;&nbsp;Horarios Escalonados</h1>
	</div>
	<hr />
	<div id="page"></div>
	<!-- end #content -->
	<div id="sidebar">
		<%	if(bEdificio) { %>
		<ul>
			<li>
				<h2>Horarios</h2>
				<ul>					
					<%	if(bPermitirSolicitud) { %>
					<li><a href="/solicitudhorario" target="ventana">Solicitud</a></li>
					<%	} %>
					<li><a href="<%=sHrefCambio%>" target="ventana">Cambio</a></li>
					<%	if(sPerfil.equals("A") || sPerfil.equals("S") || sPerfil.equals("SS")) { %>
					<li><a href="/autorizarsolicitudes" target="ventana">Autorizar</a></li>
					<%	} %>
				</ul>
			</li>
			<%	if(bMostrarAdministracion || bMostrarSuperAdministrador) { %>
			<li>
				<h2>Administraci&oacute;n</h2>
				<ul>
					<li><a href="/cargainicialmensajes" target="ventana">Mensajes</a></li>
					<!-- li><a href="administrador/mensajes.html" target="ventana">Mensajes</a></li -->
					<li><a href="/cargareportes" target="ventana">Reportes</a></li>
					<li><a href="/cargainicial" target="ventana">Horarios</a></li>
					<li><a href="/administrador/carga.jsp" target="ventana">Carga Horarios</a></li>
					<li><a href="/administrador/cargaSistema.jsp" target="ventana">Carga Sistema</a></li>
					<li><a href="/cargamanual" target="ventana">Carga Manual</a></li>
					<li><a href="/horariosescalonados" target="ventana" >Descarga Mes</a></li>
					<%	if(bMostrarSuperAdministrador) { %>
					<li><a href="/cargadocumentomes" target="ventana" >Carga Doc Mes</a></li>
					<li><a href="/administrador/reportesDeCumplimiento.jsp" target="ventana"> Reporte de Cumplimiento</a></li>
					<%	} %>
				</ul>
			</li>
			<%	} %>
		</ul>
		<%	} %>
	</div>
	</div>
</body>
</html>
