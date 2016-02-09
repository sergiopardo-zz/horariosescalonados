<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    %>
    
 	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Horarios Escalonados</title>
	<link rel="stylesheet" type="text/css" href="config/css/estilo.css" />
	<link rel='stylesheet' type="text/css" href='config/css/calendar.css'>
		<script type="text/javascript" src="config/js/jquery-1.11.3.min.js"></script>
	<script src='config/js/jquery-ui.min.js'></script>
	<script type="text/javascript" src="config/js/acciones.js"></script>
	<script type="text/javascript" src='js/calendar.js'></script>
		
</head>

<body>
<div class="contenedor">
<c:set var="tipEmp" scope="session" value="${sessionScope.tipo_empleado}"/>
	
	<c:if test="${tipEmp == 'RH'}">
	<%@include file="menuEmplRRHH.jsp"%> 
	</c:if>
	
			<c:if test="${tipEmp == 'C'}">
	<%@include file="menuEmplRRHH.jsp"%> 
	</c:if>
		
	<!-- Inicia Contenido -->
	<div class="contenidoMiga">
				<ul class="posicionMiga">	
					<li class="menuMiga">
						<span class="iconoMiga imgMigaAdm"></span>
					</li>
					<li class="menuMiga" >
						<a href="reportesRHExternos.jsp"  style="text-decoration:none;" class="migaReporte tituloMiga">
							- Reportes - Externos</a>
					</li>
				</ul>				
	</div>

	
	<div id="contenido" class="contenido">	
		<div class="contenidoSolicitud">
				<table width="100%" border="0" class="centro">
				 <tbody>
					 <tr>
						<td><a href="repDiaRHExternos.jsp"><img src="config/img/Bt_PorDia.png"></a>
							<a href="repSemanaRHExternos.jsp" class="consultaRepo"><img src="config/img/Bt_PorSemana.png"></a>
							<a href="repMesRHExternos.jsp"><img src="config/img/Bt_PorMes.png"></a>
						</td>
					
					</tr>
				</tbody>
			</table>
			<!--  <div class="moduloReportes" id="container">
				<div class="tituloModulo tx1Tabla">Filtros</div>
				<table class="tablas" cellpadding="0" width="100%" >
					<tr class=" tituloModulo tx2Tabla tablaPorDia">
						<th class="tamColumnaPeriodo">Periodo</th>
						<th class="tamColumnaReporte">Reporte</th>
					</tr>
					<tr>
						<td class="calendarioDia">
							<label class="etiqueta1 alineacionIzq ">Desde:</label><input class="inputSemanas campoObligatorio" type='text' id='fechaDesde' readonly> 
							<label class="etiqueta1 separacionCalendario">Hasta:</label><input class="inputSemanas campoObligatorio" type='text' id='fechaHasta' readonly>
						</td>
						<td class="tx3Tabla">
							<div class="contenidoSelecciona tamSeleccionaAutorizar">
								<div class ="imagSelec3"></div>
								<div class="primerOpcion campoObligatorio">Selecciona</div>
								<div class="opcionesSelecciona">
									<table class="listaSeleccciona" cellpadding="0" cellspacing="0">														
										<tbody>
											<tr>
												<td class="valorSeleccionado">Registros CYGE</td>
											</tr>
											<tr>
												<td class="valorSeleccionado">Cumplimiento</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</td>
					</tr>
				</table>
			</div>
			<div class="boton botonDerecho muestraReposo noMostrar">		
				<a href="contenido/ejemplo.xlsx" target="_blank" class="reposo">Generar</a>
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