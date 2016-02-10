<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory"%>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService"%>
<%
    BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Horarios Escalonados</title>
	<link rel="stylesheet" type="text/css" href="/config/css/estilo.css"/>
	<script type="text/javascript" src="config/js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="config/js/acciones.js"></script>
		<script type="text/javascript" language="javascript">
	
	function cargaEmpleado(){
	var fileName = document.getElementById("fileSistema").value;
	document.getElementById("iptExaminar").value = fileName;
	if(fileName !=""){
		document.getElementById("enviarEmpleado").disabled = false;
	}else{
		document.getElementById("enviarEmpleado").disabled=true;
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
	
	<c:set var="tipEmp" scope="session" value="${sessionScope.tipo_empleado}"/>
	<c:choose>
	  	<c:when test="${tipEmp=='SS' }">
  			<%@include file="header.jsp"%>  
  		</c:when>
	</c:choose> 
	
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
					<span class="tituloMiga">Carga Sistema</span>
				</li>
			</ul>				
		</div>
		<!-- Fin Miga -->

		<!-- Inicio AdminHorarios -->
		<div class="imgCargaSistema"></div> 
		<!--  <div class="moduloContenido" id="container">
		<div class="tituloModulo tx1Tabla">Solicitudes pendientes por autorizar</div>
			<table class="tablas" cellpadding="0" width="100%">
			<tr class=" tituloModulo tx2Tabla">
				<th width="7%">ID <br/>Usuario</th>
				<th width="7%">ID <br/>Supervisor</th>
				<th width="7%">Edificio</th>
				<th width="7%">Tipo <br /> Empleado</th>
				<th width="20%">Nombre</th>
				<th width="20%">Correo Empleado</th>
				<th width="7%">Horario</th>
				<th width="7%">Registro</th>
				<th witdh="7%">Nombre<br/>CR</th>
				<th width="7%">DGA</th>
			</tr>
			<tr class="tx3Tabla">
				<td>M9999</td>
				<td></td>
				<td></td>                                    
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>		
			</tr>
			</table>
		</div>-->
		<table width="100%">
			<tr>
				<td width="75%">&nbsp;</td>
				<td> <img src="/config/img/Ic_ExcelDescarga.png"></td>
				<td class="linkDescarga"><a href="contenido/CargaMasivaEjemplo.xls" target="_blank">Descarga archivo de ejemplo</a> </td>
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
			<form action="<%=blobstoreService.createUploadUrl("/uploadempleados") %>" method="post" enctype="multipart/form-data">
			
				<input type="file" name="myFile" id="fileSistema" style="display:none" onchange="cargaEmpleado()" />
    			<input type="button" class="botonMsg" id="btnExaminar" value="Examinar" size="130"/>
			
			</td>
			</tr>
			</table>
		</div>
		<div id="seecionEnviar">
			<div class="boton botonCentrado muestraReposo noMostrar">		
				<a href="index.html" class="reposo" >Enviar</a>
			</div>
			<br/>
			<br/>
			
			
			<button type="submit" class=" botonReposo" id="enviarEmpleado" value="Enviar" disabled>Enviar</button>
		
			<div class="boton botonCentrado muestraDeshabilitado">		
			
		</div>
		
		</div>
		</form>
		
		<!-- Fin AdminHorarios -->
	</div>
	<!-- Final Contenido -->	
	
</div>
</body>
</html>