<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <%@page import="com.google.appengine.api.datastore.Entity"%>
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
	<script type="text/javascript" src="config/js/acciones.js"></script>
	<script type="text/javascript" src='js/calendar.js'></script>
	<script type="text/javascript">
	
	function onclickReporte(valor){	
		var indice = document.getElementById("opcionReporte").value = valor;
		document.getElementById("hiddenMes").value = (document.getElementById('fechaMes').value);
		document.getElementById("hiddenUsuario").value = document.getElementById("hiddenUsuario").value = "<%=sUsuario%>";
		document.getElementById("hiddenTipoUsuario").value = document.getElementById("hiddenTipoUsuario").value = "<%=sTipo%>";
	}
				
	function generar(){
		formreportes.action = "/generaexcel";
		formreportes.submit();
	}

	</script>
</head>

<body>

<form id="formreportes" action="/generareporte" method="post">
		<input type="hidden" name="opcionReporte" id="opcionReporte" />
		<input type="hidden" name="hiddenDesde" id="hiddenDesde" />
		<input type="hidden" name="hiddenMes" id="hiddenMes" />
		<input type="hidden" name="hiddenUsuario" id="hiddenUsuario" />
		<input type="hidden" name="hiddenTipoUsuario" id="hiddenTipoUsuario" />
		
<div class="contenedor">
<c:set var="tipEmp" scope="session" value="${sessionScope.tipo_empleado}"/>
	
	<c:if test="${tipEmp == 'CI'}">
	<%@include file="menuEmplRRHHCI.jsp"%> 
	</c:if>
	
	<c:if test="${tipEmp == 'CE'}">
	<%@include file="menuEmplRRHHCE.jsp"%> 
	</c:if>

	<c:if test="${tipEmp == 'CA'}">
	<%@include file="menuEmplRRHH.jsp"%> 
	</c:if>
	
	<c:if test="${tipEmp == 'CC'}">
	<%@include file="menuEmplRRHHCE.jsp"%> 
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
					<li class="menuMiga">
						<span class="separacionMiga">-</span>
						<span class="tituloMiga">Por mes</span>
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
				
				
				<div class="moduloReportes" id="container">
				<div class="tituloModulo tx1Tabla">Filtros</div>
					<table class="tablas" cellpadding="0" width="100%" >
						<tr class=" tituloModulo tx2Tabla">
							<th width="534px">Periodo</th>
							<th width="439px">Reporte</th>
						</tr>
						<tr class="tx3Tabla">
							<td>						
								<label class="etiqueta1 ">Mes:</label><input class="inputSemanas campoObligatorio" id='fechaMes' name="fechaMes" readonly=""> 
								
							</td>
							
							<td class="tx3Tabla">
							<div class="contenidoSelecciona tamSelecciona">
								<div class ="imgSelSemanas"></div>
								<div class="primerOpcion campoObligatorio">Selecciona</div>
								<div class="opcionesSelecciona">
									<table class="listaSeleccciona" cellpadding="0" cellspacing="0" onclick="$('.opcionesSelecciona').css('display','none');">														
										<tbody>
										<c:if test="${tipEmp == 'CE'}">
											<tr>
												<td class="valorSeleccionado"><option  value="cumplimientoExternoRRHH" onclick="onclickReporte(this.value)">Cumplimiento Externos</option></td>
											</tr>
										</c:if>
										<c:if test="${tipEmp == 'CI'}">
											<tr>
												<td class="valorSeleccionado"><option  value="cumplimientoExternoRRHH" onclick="onclickReporte(this.value)">Cumplimiento Externos</option></td>
											</tr>
										</c:if>
										<c:if test="${tipEmp == 'CA'}">
											<tr>
												<td class="valorSeleccionado"><option  value="cumplimientoExternoRRHH" onclick="onclickReporte(this.value)">Cumplimiento Externos</option></td>
											</tr>
										</c:if>
										<c:if test="${tipEmp == 'CC'}">
											<tr>
												<td class="valorSeleccionado"><option  value="cumplimientoExternoCyge" onclick="onclickReporte(this.value)">Cumplimiento Externos</option></td>
											</tr>
										</c:if>
										</tbody>
									</table>
								</div>
							</div>
							</td>
						</tr>
					</table>
			</div>
			
			<div class="boton botonDerecho muestraReposo noMostrar">		
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