<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.google.appengine.api.datastore.Entity"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% String sUsuario = session.getAttribute("usuario")==null?"":(String)session.getAttribute("usuario");
String sTipo = session.getAttribute("tipo_empleado")==null?"":(String)session.getAttribute("tipo_empleado");%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Horarios Escalonados</title>
	<link rel="stylesheet" type="text/css" href="config/css/estilo.css" />
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
 	<link rel='stylesheet' type="text/css" href='config/css/NewCalendar.css'>
	<script src="config/js/jquery-1.11.3.min.js"></script>
	<script src='config/js/jquery-ui.min.js'></script>
	<script type="text/javascript" src="/config/js/acciones.js"></script>
	<script type="text/javascript" src='/config/js/calendar.js'></script>
	<script type="text/javascript">
	
	
	function onclickReporte(valor){
		var indice = document.getElementById("opcionReporte").value = valor;
		document.getElementById("hiddenSemana").value = document.getElementById('fechaSemana').value;
		document.getElementById("hiddenUsuario").value = document.getElementById("hiddenUsuario").value = "<%=sUsuario%>";
		document.getElementById("hiddenTipoUsuario").value = document.getElementById("hiddenTipoUsuario").value = "<%=sTipo%>";
	}
	
	function generar(){
		formreportes.action = "/generaexcel";
		formreportes.submit();
	}
	
	</script>
</head>

<body>:

<!-- formulario para reporte cyge -->

<form id="formreportes" action="/generareporte" method="post">
		<input type="hidden" name="opcionReporte" id="opcionReporte" />
		<input type="hidden" name="hiddenSemana" id="hiddenSemana" /> 
		<input type="hidden" name="hiddenUsuario" id="hiddenUsuario" />
		<input type="hidden" name="hiddenTipoUsuario" id="hiddenTipoUsuario" />
		
<!--                                                          -->

<div class="contenedor">
	
	<!-- Inicia Menu -->

	<c:set var="tipEmp" scope="session" value="${sessionScope.tipo_empleado}"/>
	<c:if test="${tipEmp == 'SS'}">
	<%@include file="header.jsp"%> 
	</c:if>
		
	<c:if test="${tipEmp == 'RH'}">
	<%@include file="menuEmplAA.jsp"%> 
	</c:if>
	
	<c:if test="${tipEmp == 'GE'}">
	<%@include file="menuEmplA.jsp"%> 
	</c:if>
	
	<c:if test="${tipEmp == 'C'}">
	<%@include file="menuEmplC.jsp"%> 
	</c:if>

	<!-- Final Menu -->
	
<!-- Inicia Contenido -->
	<div class="contenidoMiga">
				<ul class="posicionMiga">	
					<li class="menuMiga">
						<span class="iconoMiga imgMigaAdm"></span>
					</li>
					<li class="menuMiga" >
						<a href="/cargareportes"  style="text-decoration:none;" class="migaReporte tituloMiga">
							- Reportes - CYGE</a>
					</li>
					<li class="menuMiga">
						<span class="separacionMiga">-</span>
						<span class="tituloMiga">Por semana</span>
					</li>
				</ul>				
	</div>

	
	<div id="contenido" class="contenido">	
		<div class="contenidoSolicitud">
			<table width="100%" border="0" class="centro">
				<tbody>
					<tr>
						<td>
							<a href="repDia.jsp"><img src="config/img/Bt_PorDia.png"></a>
							<a href="repSemana.jsp" class="consultaRepo"><img src="config/img/Bt_PorSemana.png"></a>
							<a href="repMes.jsp"><img src="config/img/Bt_PorMes.png"></a>
						</td>
					</tr>
				</tbody>
			</table>
			<div class="moduloReportes" id="container">
				<div class="tituloModulo tx1Tabla">Filtros</div>
					<table class="tablas" cellpadding="0" width="100%" >
						<tr class=" tituloModulo tx2Tabla">
							<th width="534px">Periodo</th>
							<th width="439px">Reporte</th>
						</tr>
						<tr class="tx3Tabla">
							<td>
								<label class="etiqueta1">Semana: </label><input class="inputSemanas" id='fechaSemana' readonly="" href="javascript:fechas();">
								<!-- <img id="imgSemana" class="ui-datepicker-trigger" src="img/calendar.png" alt="Seleccionar día" title="Seleccionar día"> -->
							</td>
						<td class="tx3Tabla">
							<div class="contenidoSelecciona tamSeleccionaAutorizar">
								<div class ="imagSelec3"></div>
								<div class="primerOpcion campoObligatorio">Selecciona</div>
								<div class="opcionesSelecciona">
									<table class="listaSeleccciona" cellpadding="0" cellspacing="0" id="IDComboReporte" onclick="$('.opcionesSelecciona').css('display','none');">														
										<tbody>
										<c:if test="${tipEmp == 'GE'}">
											<tr>
												<td class="valorSeleccionado"><option value="cyge" onclick="onclickReporte(this.value)">Registros CYGE</option></td>
											</tr>
											<tr>
												<td class="valorSeleccionado"><option  value="cumplimientoExternoCyge" onclick="onclickReporte(this.value)">Cumplimiento Externo CYGE</option></td>
											</tr>
											<tr>
												<td class="valorSeleccionado"><option  value="incumplimiento" onclick="onclickReporte(this.value)">Incumplimiento Externos</option></td>
											</tr>		
										</c:if>
										<c:if test="${tipEmp == 'SS'">
											<tr>
												<td class="valorSeleccionado"><option value="alta" onclick="onclickReporte(this.value)">Alta</option></td>
											</tr>
											<tr>
												<td class="valorSeleccionado"><option value="modificacoin" onclick="onclickReporte(this.value)">Modificacion</option></td>
											</tr>
											<tr>
												<td class="valorSeleccionado"><option value="baja" onclick="onclickReporte(this.value)">Baja</option></td>
											</tr>
											<tr>
												<td class="valorSeleccionado"><option value="noasignacion" onclick="onclickReporte(this.value)">No Asignacion</option></td>
											</tr>
											<tr>
												<td class="valorSeleccionado"><option value="cyge" onclick="onclickReporte(this.value)">Registros CYGE</option></td>
											</tr>
											<tr>
												<td class="valorSeleccionado"><option  value="cumplimiento" onclick="onclickReporte(this.value)">Cumplimiento Internos</option></td>
											</tr>
											<tr>
												<td class="valorSeleccionado"><option  value="incumplimiento" onclick="onclickReporte(this.value)">Incumplimiento Externos</option></td>
											</tr>
											<tr>
												<td class="valorSeleccionado"><option  value="cumplimientoExternoCyge" onclick="onclickReporte(this.value)">Cumplimiento Externos CYGE</option></td>
											</tr>
											<tr>
												<td class="valorSeleccionado"><option  value="cumplimientoExternoRRHH" onclick="onclickReporte(this.value)">Cumplimiento Externos RRHH</option></td>
											</tr>
											</c:if>
											<c:if test="${tipEmp == 'RH'}">
											<tr>
												<td class="valorSeleccionado"><option value="alta" onclick="onclickReporte(this.value)">Alta</option></td>
											</tr>
											<tr>
												<td class="valorSeleccionado"><option value="modificacoin" onclick="onclickReporte(this.value)">Modificacion</option></td>
											</tr>
											<tr>
												<td class="valorSeleccionado"><option value="baja" onclick="onclickReporte(this.value)">Baja</option></td>
											</tr>
											<tr>
												<td class="valorSeleccionado"><option value="noasignacion" onclick="onclickReporte(this.value)">No Asignacion</option></td>
											</tr>
											<tr>
												<td class="valorSeleccionado"><option  value="cumplimiento" onclick="onclickReporte(this.value)">Cumplimiento Internos</option></td>
											</tr>
											<tr>
												<td class="valorSeleccionado"><option  value="cumplimientoExternoRRHH" onclick="onclickReporte(this.value)">Cumplimiento Externos RRHH</option></td>
											</tr>
										</c:if>
										</tbody>
										</table>
										</div>
										</div>
										</td>
										</tr>
										</tbody>
									</table>
								</div>
							</div>
					</div>
			<div class="boton botonDerecho muestraReposo noMostrar" >		
				<a class="reposo" id='BTNCONSULTAR' value='Generar' href="javascript:generar();">Generar</a>
			</div>
			<div class="boton botonDerecho muestraDeshabilitado">		
				<a class="deshabilitado">Generar</a>
			</div>
		</div>
	</div>
<!-- Final Contenido -->


</div>
</body>
</html>