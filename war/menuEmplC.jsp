    <%@page import="java.text.SimpleDateFormat"%>
	<%@ page import="com.google.appengine.api.datastore.Entity"%>
	<%@ page import="java.util.*"%>
	<%@ page import="java.lang.*"%>
    
<%
String sNombre = session.getAttribute("nombreCompleto")==null?"":(String)session.getAttribute("nombreCompleto"); 
String sPerfil = session.getAttribute("tipo_empleado")==null ? "" : (String)session.getAttribute("tipo_empleado");
String sEdificio = session.getAttribute("edificio")==null ? "" : (String)session.getAttribute("edificio");
//String sFechaSolicitud = request.getAttribute("fecha_solicitud")==null ? "" : (String)request.getAttribute("fecha_solicitud");
//String sFechaAplicacion = request.getAttribute("fecha_aplicacion")==null ? "" : (String)request.getAttribute("fecha_aplicacion");
String sFechaSolicitud = session.getAttribute("fecha_solicitud")==null ? "" : (String)session.getAttribute("fecha_solicitud");
String sFechaAplicacion = session.getAttribute("fecha_aplicacion")==null ? "" : (String)session.getAttribute("fecha_aplicacion");
String sHorarioActual = request.getAttribute("hora_actual")==null ? "" : (String)request.getAttribute("hora_actual");
Calendar calFechaActual = new GregorianCalendar(); // Se obtiene la fecha actual
Calendar calFechaSolicitud = new GregorianCalendar(); // Se crea Calendar para la fecha de Solicitud
Calendar calFechaAplicacion = new GregorianCalendar(); // Se crea Calendar para la fecha de Aplicación
Calendar calFechaProxSol = new GregorianCalendar(); // Se crea Calendar para la fecha de la próxima modificación/baja

//boolean bEdificio = sEdificio.trim().equals("REFORMA") || sEdificio.trim().equals("POLANCO");
boolean bMostrarAdministracion = (sPerfil.equals("GE")|| sPerfil.equals("RH"));
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
	//calFechaProxSol.add(Calendar.MONTH,3); // Se suman 3 meses a la fecha
	calFechaProxSol.add(Calendar.MINUTE,10);
}

//true si ya se cumplieron los tres meses de lapso
boolean bMasDe3Meses = calFechaActual.after(calFechaProxSol);
//boolean bMasDe3Meses = true;
//true si el edificio es correcto y se cumplieron mas de tres meses
boolean validacion1 =  bMasDe3Meses;
//true si es una nueva solicitud y es diferente de "0000000"
boolean validacion2 =  bNuevaSolicitud && !bHorarioActual;

//perite solicitud si corresponde al edificio es nueva solicitud y no es cambio
boolean bPermitirSolicitud =  bNuevaSolicitud && bHorarioActual;

boolean bPermitirCambio = validacion1 || validacion2;
boolean bPermitirBaja =  bMasDe3Meses;

String sHrefCambio = bPermitirCambio ? "/cambiohorario" : "bloqueoCambio.jsp";
String sHrefBaja = bPermitirCambio ? "horario/baja.html" : "bloqueoCambio.jsp";
%>

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
	<%//if (sTipo.equals("SS")||sTipo.equals(" ")){ %>
	<!-- Inicia Menu -->
	<div class="imgPrincipal imgTam">
	<%if(bPermitirSolicitud==false){%>
		<div class="menuPrincipal">
				<ul class="home">
					<li>
						<a class="btnHome" id="btnHomeApagado" href="/MainMenu"></a>
					</li>
					<li class="menu horario centro">
						<a href="#" class="estiloMenu" >
							<span class="icono imgHorario" id="btnHorario"></span>
							<span class="texto">Horarios</span>
						</a>
						<ul class="subMenuHorariosSinSolicitud">							
							<li class="subMenu"><a href="<%=sHrefCambio%>">
								<span class="iconoSubMenu imgHorarioSubMenu"></span>
								<span class="textoSubMenu">Cambio</span>
							</a></li>
							<li class="subMenu"><a href="/autorizarsolicitudes" >
								<span class="iconoSubMenu imgHorarioSubMenu"></span>
								<span class="textoSubMenu">Autorizar</span>
							</a></li>
						</ul>
					</li>
					<li class="menu adm centro">
						<a href="#" class="estiloMenu">
							<span class="icono imgAdministracion" id="btnAdministracion"></span>
							<span class="texto">Administración</span>
						</a>
						<ul class="subMenuAdministracionC">		
							
							<li class="subMenu"><a href="/cargareportes">
								<span class="iconoSubMenuAdm imgAdministracionSubMenu"></span>
								<span class="textoSubMenu">Reportes</span>
							</a></li>
						
						</ul>
						
					</li>
				</ul>
		</div>
		<%} %>
			<%if(bPermitirSolicitud==true){%>
		<div class="menuPrincipal">
			<ul class="home">
					<li>
						<a class="btnHome" id="btnHomeApagado" href="/MainMenu"></a>
					</li>
					<li class="menu horario centro">
						<a href="#" class="estiloMenu" >
							<span class="icono imgHorario" id="btnHorario"></span>
							<span class="texto">Horarios</span>
						</a>
						<ul class="subMenuHorarios">							
							<li class="subMenu">
							<%	if(bPermitirSolicitud) { %>
							<a href="/solicitudhorario">
								<span class="iconoSubMenu imgHorarioSubMenu"></span>
								<span class="textoSubMenu">Solicitud</span>
							</a></li>
							<%}%>
							<li class="subMenu"><a href="<%=sHrefCambio%>">
								<span class="iconoSubMenu imgHorarioSubMenu"></span>
								<span class="textoSubMenu">Cambio</span>
							</a></li>
							<li class="subMenu"><a href="/autorizarsolicitudes" >
								<span class="iconoSubMenu imgHorarioSubMenu"></span>
								<span class="textoSubMenu">Autorizar</span>
							</a></li>
						</ul>
					</li>
					<li class="menu adm centro">
						<a href="#" class="estiloMenu">
							<span class="icono imgAdministracion" id="btnAdministracion"></span>
							<span class="texto">Administración</span>
						</a>
						<ul class="subMenuAdministracionC">		
							
							<li class="subMenu"><a href="/cargareportes">
								<span class="iconoSubMenuAdm imgAdministracionSubMenu"></span>
								<span class="textoSubMenu">Reportes</span>
							</a></li>
						
						</ul>
						
					</li>
				</ul>
		</div>
		<%} %>
	</div>	
	<!-- Final Menu -->