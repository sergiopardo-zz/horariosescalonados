<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    %>
 	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Horarios Escalonados</title>
	<title>Horarios Escalonados</title>
	<link rel="stylesheet" type="text/css" href="config/css/estilo.css"/>
	<script type="text/javascript" src="config/js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="config/js/acciones.js"></script>
</head>
<body>
<div class="contenedor">
<div>
<c:set var="tipEmp" scope="session" value="${sessionScope.tipo_empleado}"/>
	<center>
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
	<%@include file="menuEmplC.jsp"%> 
	</c:if>
	
	<c:if test="${tipEmp == 'CE'}">
	<%@include file="menuEmplC.jsp"%> 
	</c:if>
	
	<c:if test="${tipEmp == 'CA'}">
	<%@include file="menuEmplC.jsp"%> 
	</c:if>
	
	<c:if test="${tipEmp == 'CC'}">
	<%@include file="menuEmplC.jsp"%> 
	</c:if>
	</center>
<!-- Inicia Contenido -->	
	<div id="contenido" class="contenido">
		<!-- Inicio Miga -->
		<div class="contenidoMiga">
			<ul class="posicionMiga">	
				<li class="menuMiga">
					<span class="iconoMiga imgMiga"></span>
				</li>
				<li class="menuMiga">
				<span class="separacionMiga">-</span>
					<span class="tituloMiga">Cambio</span>
				</li>
				<!-- <li class="menuMiga">
					<span class="separacionMiga">-</span>
					<span class="tituloMiga">Cambio</span>
				</li>
				<li class="menuMiga">
				<span class="separacionMiga">-</span>
					<span class="tituloMiga">Cambio</span>
				</li> -->
			</ul>				
		</div>
		<!-- Fin Miga -->

		<!-- Inicio Cambio -->
		<div class="contenidoCambio">
			<div class="avisoTemporal">
				<span class="iconoAviso imgAviso"></span>
				<span class="avisoCambio">Por el momento no puedes ingresar a este m&oacute;dulo.</span>
			</div>
		</div>
		<!-- Fin Cambio -->
	</div>
	<!-- Final Contenido -->
	
</div>
</body>
</html>