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
	<link rel="stylesheet" type="text/css" href="config/css/estilo.css"/>
	<script type="text/javascript" src="config/js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="config/js/acciones.js"></script>
</head>
<body>
<div class="contenedor">
<c:set var="tipEmp" scope="session" value="${sessionScope.tipo_empleado}"/>
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

	<!-- Inicia Contenido -->	
	<div id="contenido" class="contenido">
		<div class="contenidoBienvenido">
			<div class="txtAjutado4"> 
				<span class="texto1">Bienvenido </span>
				<span class="texto2">a la aplicación para la </span> 
				<span class="texto3">Gestión de Horarios.</span>
			</div>
			<div class="txtAjutado1">
				<span class="texto4">En esta aplicación podrás realizar tu petición de cambio de horario, para lo cual es imprescindible que previamente lo acuerdes con tu jefe directo.</span>
			</div>
			<div class="imgContenido">
				<p class="txtAjutado5">
					<span class="texto5">Te recordamos que tu horario de trabajo debe ser fijado considerando:</span>
				</p>			
				<p class="txtAjutado6">
					<span class="texto4">Tu función y responsabilidades.</span>
				</p>
				<p class="txtAjutado7">
					<span class="texto4">El no descuidar las ventanillas de servicio o soporte que puedas proporcionar a otras áreas.</span>
				</p>
				<p class="txtAjutado7">
					<span class="texto4">El acuerdo previo con tu responsable, el cual será el facultado para autorizarte o no el cambio de horario.</span>
				</p>
			</div>
			<div class="txtAjutado3">
				<span class="texto4">La definición o cambio del horario no modifica el esquema de trabajo de Jornada Discontinua.</span>
			</div>
			<div class="centro">
				<span class="texto6">El cumplimiento de nuestra jornada es responsabilidad de todos.</span>
			</div>
		</div>
	</div>
	<!-- Final Contenido -->
	</div>
</html>