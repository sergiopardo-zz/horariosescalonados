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
	
	<c:if test="${tipEmp == 'AA'}">
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
						<a href="reportesRHInternos.jsp"  style="text-decoration:none;" class="migaReporte tituloMiga">
							- Reportes - Internos</a>
					</li>
				</ul>				
	</div>

	
	<div id="contenido" class="contenido">	
		<div class="contenidoSolicitud">
				<table width="100%" border="0" class="centro">
				 <tbody>
					 <tr>
						<td><a href="repDiaRHInternos.jsp"><img src="config/img/Bt_PorDia.png"></a>
							<a href="repSemanaRHInternos.jsp" class="consultaRepo"><img src="config/img/Bt_PorSemana.png"></a>
							<a href="repMesRHInternos.jsp"><img src="config/img/Bt_PorMes.png"></a>
						</td>
					
					</tr>
				</tbody>
			</table>

</div>
</body>
</html>