<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    %>
 	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bloqueo</title>
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
	
	<c:if test="${tipEmp == 'C'}">
	<%@include file="menuEmplC.jsp"%> 
	</c:if>
	</center>
</div>
	<table align="center" width="50%">
		<tr>
			<td>
				<p align="center" style="font: Geneva, Arial, Helvetica, sans-serif; font-size:18px; color:#0080C0;">
					Estimado Usuario, tu solicitud esta en proceso de autorizaci&oacute;n por parte de tu jefe directo.
				</p>
			</td>
		</tr>
	</table>
</div>
</body>
</html>