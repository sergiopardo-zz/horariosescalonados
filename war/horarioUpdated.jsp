<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory"%>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService"%>
<%
    BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
%>
 	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Horarios Escalonados</title>
	
	<link rel="stylesheet" type="text/css" href="/config/css/estilo.css"/>

	<script type="text/javascript" src="config/js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="config/js/acciones.js"></script>
	<script type="text/javascript" language="javascript">
	
	function fileName(){
	var fileName = document.getElementById("fileSistema").value;
	document.getElementById("iptExaminar").value = fileName;
	if(fileName !=""){
	document.getElementById("enviarHorario").disabled = false;
	}else{
	document.getElementById("enviarHorario").disabled=true;
	}
	
	}
	
	$(document).ready(function(){
		  $("#btnExaminar").click(function () {
		      $("#fileSistema").trigger('click');
		  });
	});
	
	</script>
	
</head>
<body>
<div class="contenedor">

	<c:if test="${tipEmp == 'RH'}">
		<%@include file="menuEmplAA.jsp"%> 
	</c:if>

	<c:if test="${tipEmp == 'SS'}">
		<%@include file="header.jsp"%> 
	</c:if> 
	
	<!-- Inicia Contenido -->	
	<div id="contenido" class="contenido">
		<!-- Inicio Miga -->
		<div class="contenidoMiga">
			<ul class="posicionMiga">	
				<li class="menuMiga">
					<span class="iconoMiga imgMigaAdm"></span>
				</li>
				<li class="menuMiga">
				<span class="separacionMiga">-</span>
					<span class="tituloMiga">Horarios</span>
				</li>
			</ul>				
		</div>
		<!-- Fin Miga -->

		<!-- Inicio AdminHorarios -->
		<div class="imgCargaHorarios"></div> 	
		<!--  <div class="moduloContenido" id="container">
		<div class="tituloModulo tx1Tabla">Carga de horarios</div>
		<table class="tablas" cellpadding="0" width="100%">
			<tr class=" tituloModulo tx2Tabla">
				<th width="33%">ID del Usuario</th>
				<th width="33%">Hora de Entrada</th>
				<th width="33%">Hora de Salida</th>

			</tr>
			<tr class="tx3Tabla">
				
				<td>m2146412</td>
				<td> 16:30</td>                                    
				<td> 18:30</td>

			
			<c:forEach items="${entity}" var="horario">
			<c:set var="idHorario" value="${horario.horarioId}"/>
			
			<c:if test="${ idHorario !='00000000'}">
			<tr class="tx3Tabla">
				<td><c:out value="${horario.horarioId }"/></td>
				<td><c:out value="${horario.entrada}"/></td>
				<td><c:out value="${horario.salida}"/></td>
			</tr>
			</c:if>
			
			<c:if test="${ idHorario =='00000000'}">
				
			</c:if>
			
			</c:forEach>
			</table>
			</div>-->

		<table width="100%">
			<tr>
				<td width="75%">&nbsp;</td>
				<td> <img src="/config/img/Ic_ExcelDescarga.png"></td>
				<td class="linkDescarga"><a href="contenido/CargaHorarioEjemplo.xls" target="_blank">Descarga archivo de ejemplo</a> </td>
			</tr>
		</table>
		<div style="margin-top:30px;">
			<table >
			<tr>
				<td>
					<label class="etiqueta1">Selecciona el archivo:</label>
				</td>
				<td width="337px">
					<input class="examinar" id="iptExaminar" readonly>
				</td>
			<td>
			<!--  <input type="file" id="upload" class="" accept=".csv, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel" style="display:none"/> 
            <div class="botonExaminar boton muestraReposo">
			<a href="" id="upload_link" class="reposo">Examinar</a>	
			</div>-->
			<form action="<%=blobstoreService.createUploadUrl("/uploadhorarios") %>" method="post" enctype="multipart/form-data">
				<input type="file" name="myFile" id="fileSistema" style="display:none" onchange="fileName()" />
    			<input type="button" class="botonMsg" id="btnExaminar" value="Examinar" size="130"/>
			</td>
			</tr>
			</table>
		</div>
		<div id="seecionEnviar">

			</div>
				<br/>
				<br/>
				<!--
			<button type="submit" class=" botonReposo" id="enviarHorario" value="Enviar" disabled style="aling:center">
			</button> -->
			<div align="center">
				<input type="submit" class="botonMsg" id="enviarHorario" value="Enviar" size="130" style="width: 90px;"  disabled/>
			</div>
			</form>
		<!--  <div class="boton botonCentrado muestraDeshabilitado">		
			<a class="deshabilitado" >Enviar</a>
		</div>
		</div>-->
		<!-- Fin AdminHorarios -->
	</div>
	<!-- Final Contenido -->	
	
</div>
</body>
</html>