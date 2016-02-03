<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Horarios Escalonados</title>
	<link rel="stylesheet" type="text/css" href="config/css/estilo.css" />
	<script type="text/javascript">
	
	$(document).ready(function(){
		$("#autorizar").click(function() {
			$("#dialogo2").dialog({
				width: 400,
				height: 180,
				show: "scale",
				hide: "scale",
				resizable: "false",
				position: "center",
				modal: "true"
				
			});			
		});
	});
	
	
	$(document).ready(function(){
		$("#aceptar2").click(function() {
			$("#dialogo2").dialog("close");
			fHeMensajes.submit();
		});
	});
	
	</script>
</head>

<body>
<form id="formtiempo" action="/generareporte" method="post">
		<input type="hidden" name="opcionTiempo" id="opcionTiempo" />
</form>


<div class="contenedor">
<c:set var="tipEmp" scope="session" value="${sessionScope.tipo_empleado}"/>
	<c:choose>
		<c:when test="${tipEmp== 'AA'}">
			<%@include file="menuEmplAA.jsp"%> 
		</c:when>
		<c:when test="${tipEmp== 'A'}">
			<%@include file="menuEmplA.jsp"%> 
		</c:when>
		<c:otherwise>
			<%@include file="header.jsp"%> 
		</c:otherwise>
	</c:choose>
	<!-- Inicia Contenido -->
	<div class="contenidoMiga">
				<ul class="posicionMiga">	
					<li class="menuMiga">
						<span class="iconoMiga imgMigaAdm"></span>
					</li>
					<li class="menuMiga">
						<span class="separacionMiga">-</span>
						<span class="tituloMiga">Reportes - CYGE</span>
					</li>
				</ul>				
	</div>

	
	<div id="contenido" class="contenido">	
		<div class="contenidoSolicitud">
				<table width="100%" border="0" class="centro">
				 <tbody>
					 <tr>
						<td><a href="repDia.jsp" value="dia" id="dia" name=opcionTiempo onclick="onclickOpcion(this.value)"><img src="config/img/Bt_PorDia.png"></a>
							<a href="repSemana.jsp" class="consultaRepo"><img src="config/img/Bt_PorSemana.png"></a>
							<a href="repMes.jsp"><img src="config/img/Bt_PorMes.png"></a>
						</td>
					
					</tr>
				</tbody>
			</table>
		</div>
	</div>
<!-- Final Contenido -->

</div>
</body>
</html>