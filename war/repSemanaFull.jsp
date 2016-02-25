<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.google.appengine.api.datastore.Entity"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String sUsuario = session.getAttribute("usuario") == null ? "" : (String) session.getAttribute("usuario");
	String sTipo = session.getAttribute("tipo_empleado") == null ? "" : (String) session.getAttribute("tipo_empleado");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Horarios Escalonados</title>
<link rel="stylesheet" type="text/css" href="config/css/estilo.css" />
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<link rel='stylesheet' type="text/css" href='config/css/NewCalendar.css'>
<script src="config/js/jquery-1.11.3.min.js"></script>
<script src='config/js/jquery-ui.min.js'></script>
<script type="text/javascript" src="/config/js/acciones.js"></script>
<script type="text/javascript" src='/config/js/calendar.js'></script>
<script type="text/javascript">

$(document).ready(function(){
	  $("#aviso2, #aviso3, #btnRegresar, #btn2do").hide();
	  var misVariablesGet = getVarsUrl();
	  switch (misVariablesGet.opcionReporte) {
	  case "alta":
		  document.getElementById("lstOpcion").value = "Alta";
	    break;
	  case "modificacion":
		  document.getElementById("lstOpcion").value = "Modificación";
	  	break;
	  case "baja":
		  document.getElementById("lstOpcion").value = "Baja";
	  	break;
	  case "noasignacion":
		  document.getElementById("lstOpcion").value = "No Asignación";
		break;
	  case "cyge":
		  document.getElementById("lstOpcion").value = "Registros CYGE";
		break;
	  case "cumplimiento":
		  document.getElementById("lstOpcion").value = "Cumplimiento Internos";
		break;
	  case "cumplimientoExternoCyge":
		  document.getElementById("lstOpcion").value = "Cumplimiento Externos CYGE";
		break;
	  case "cumplimientoExternoRRHH":
		  document.getElementById("lstOpcion").value = "Cumplimiento Externos RRHH";
		break;
	}
	  document.getElementById("fechaSemana").value = misVariablesGet.hiddenSemana.replace("+", " ");
	  $("#opcionReporte").val(misVariablesGet.opcionReporte);
	  onclickReporte();
	  direccionamiento();
	 });
		 
		 function getVarsUrl(){
		     var url= location.search.replace("?", "");
		     var arrUrl = url.split("&");
		     var urlObj={};   
		     for(var i=0; i<arrUrl.length; i++){
		         var x= arrUrl[i].split("=");
		         urlObj[x[0]]=x[1]
		     }
		     return urlObj;
		 }
	
	function onclickReporte(){	
		document.getElementById("hiddenSemana").value = (document.getElementById('fechaSemana').value);
		document.getElementById("hiddenUsuario").value = document.getElementById("hiddenUsuario").value = "<%=sUsuario%>";
		document.getElementById("hiddenTipoUsuario").value = document.getElementById("hiddenTipoUsuario").value = "<%=sTipo%>";
	}

	function generar() {
		formreportes.action = "/Servlet_Archivodos";
		formreportes.submit();
		$(".muestraDeshabilitado").removeClass("noMostrar");
		$(".muestraReposo").addClass("noMostrar");
		$("#btn2do").focus(10000, function() {
			$("#btn2do, #aviso2").show();
		});
	}

	function generarDos() {
		formreportes.action = "/Servlet_Archivotres";
		formreportes.submit();
		$(".muestraDeshabilitado2").removeClass("noMostrar");
		$("#btn2do").hide();
		$("#btnRegresar").focus(10000, function() {
			$("#btnRegresar, #aviso3").show();
		});
	}

	RepSemana
	function direccionamiento() {
	  var TipoUsr = "<%=sTipo%>";
	  if (TipoUsr == "SS" || TipoUsr == "RH" || TipoUsr == "GE") {
	   $("#aRegresar").attr("href", "/repSemana.jsp");
	   $("#txtTitulo").hide();
	  } else if (TipoUsr == "CI") {
	   $("#aRegresar").attr("href", "/repSemanaRHInternos.jsp");
	   $("#txtTitulo").val("- Internos");
	  } else if (TipoUsr == "CE" || TipoUsr == "CC") {
	   $("#aRegresar").attr("href", "/repSemanaRHExternos.jsp");
	   $("#txtTitulo").val("- Externos");
	  } else if (TipoUsr == "CA") {
	   if (misVariablesGet.opcionReporte == "cumplimiento") {
	    $("#aRegresar").attr("href", "/repSemanaRHInternos.jsp");
	    $("#txtTitulo").val("- Internos");
	   } else {
	    $("#aRegresar").attr("href", "/repSemanaRHExternos.jsp");
	    $("#txtTitulo").val("- Externos");
	   }
	  }
	 }
	}
</script>
</head>
<body>
	:
	<!-- formulario para reporte cyge -->
	<form id="formreportes" action="/generareporte" method="get">
		<input type="hidden" name="opcionReporte" id="opcionReporte" /> 
		<input type="hidden" name="hiddenSemana" id="hiddenSemana" /> 
		<input type="hidden" name="hiddenUsuario" id="hiddenUsuario" /> 
		<input type="hidden" name="hiddenTipoUsuario" id="hiddenTipoUsuario" />

		<div class="contenedor">
			<!-- Inicia Menu -->

			<c:set var="tipEmp" scope="session" value="${sessionScope.tipo_empleado}" />
			<c:if test="${tipEmp == 'SS'}">
				<%@include file="header.jsp"%>
			</c:if>

			<c:if test="${tipEmp == 'RH'}">
				<%@include file="menuEmplAA.jsp"%>
			</c:if>

			<c:if test="${tipEmp == 'E'}">
				<%@include file="menuEmplE.jsp"%>
			</c:if>

			<c:if test="${tipEmp == 'S'}">
				<%@include file="menuEmplS.jsp"%>
			</c:if>

			<c:if test="${tipEmp == 'GE'}">
				<%@include file="menuEmplA.jsp"%>
			</c:if>

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

			<!-- Final Menu -->

			<!-- Inicia Contenido -->
			<div class="contenidoMiga">
				<ul class="posicionMiga">
					<li class="menuMiga"><span class="iconoMiga imgMigaAdm"></span>
					</li>
					<li class="menuMiga">
						<a href="/cargareportes" style="text-decoration: none;" class="migaReporte tituloMiga"> - Reportes </a>
					</li>
					<li class="menuMiga">
						<input class="tituloMiga" id="txtTitulo" readonly style="width: 105px; border: none;"/>
						<span class="separacionMiga">-</span> 
						<span class="tituloMiga">Por semana</span>
					</li>
				</ul>
			</div>


			<div id="contenido" class="contenido" style="height: 295px;">
				<div class="contenidoSolicitud">
					<table width="100%" border="0" class="centro">
						<tbody>
							<tr>
								<td><a href="repDia.jsp"><img
										src="config/img/Bt_PorDia.png"></a> <a href="repSemana.jsp"
									class="consultaRepo"><img src="config/img/Bt_PorSemana.png"></a>
									<a href="repMes.jsp"><img src="config/img/Bt_PorMes.png"></a>
								</td>
							</tr>
						</tbody>
					</table>
					
					<div class="moduloReportes" id="container">
						<div class="tituloModulo tx1Tabla">Filtros</div>
						<table class="tablas" cellpadding="0" width="100%">
							<tr class=" tituloModulo tx2Tabla">
								<th width="534px">Periodo</th>
								<th width="439px">Reporte</th>
							</tr>
							<tr class="tx3Tabla">
								<td>
									<label class="etiqueta1 ">Semana:</label> 
									<input class="inputSemanas campoObligatorio" id='fechaSemana' name="fechaSemana" readonly> 
									<img class="ui-datepicker-trigger" src="config/img/calendar.png" alt="Seleccionar dÃ­a" title="Seleccionar dÃ­a">
								</td>

								<td class="tx3Tabla">
									<div class="tamSelecciona">
										<div class="imgSelSemanas"></div>
										<input class="primerOpcion campoObligatorio colorSeleccionado"
											id="lstOpcion" readonly />
									</div>
								</td>
							</tr>
						</table>
					</div>
					
			<div class="aviso" style="margin-top: 10px">
				 El reporte a generar es demasiado grande, por ello sera divido en dos segmentos.
			</div>
			<div class="boton botonDerecho muestraReposo" style="height: 40px;">		
				<a class="reposo" id='BTNCONSULTAR' value='Generar' href="javascript:generar();" style="background-size: 103px 41px; height: 35px;">Generar 1ra Parte</a>
			</div>
			<div class="boton botonDerecho muestraDeshabilitado noMostrar" style="height: 40px;">		
				<a class="deshabilitado" style="background-size: 103px 41px; height: 35px;" >Generar 1ra Parte</a>
			</div>
			
			<div id="aviso2" class="aviso" style="margin-top: 10px">
				 Genere el reporte cuando haya confirmado que se descargo la primera parte.
			</div>
			<div class="boton botonDerecho muestraReposo2" id="btn2do" style="height: 40px;">		
				<a class="reposo" id='BTNCONSULTAR' value='Generar' href="javascript:generarDos();" style="background-size: 103px 41px; height: 35px;">Generar 2da parte</a>
			</div>
			<div class="boton botonDerecho muestraDeshabilitado2 noMostrar" style="height: 40px;">		
				<a class="deshabilitado" style="background-size: 103px 41px; height: 35px;" >Generar 2da Parte</a>
			</div>
			
			<div id="aviso3" class="aviso" style="margin-top: 10px">
				 Cuando se hayan terminado de descargar los reportes, regrese a la anterior vista.
			</div>
			<div class="boton muestraReposo" id="btnRegresar" align="center">		
				<a class="reposo"  value="Regresar" id="aRegresar">Regresar</a>
			</div>
				</div>
			</div>

			<!-- Final Contenido -->
		</div>
	</div>
</body>
</html>