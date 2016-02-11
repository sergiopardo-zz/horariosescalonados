<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Horarios Escalonados</title>
	<link rel="stylesheet" type="text/css" href="/config/css/estilo.css"/>

	<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="js/acciones.js"></script>
	<script type="text/javascript" language="javascript"></script>
	<script type="text/javascript" src="config/js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="config/js/acciones.js"></script>
<script type="text/javascript">
	function inicial(){
		frmDownload.submit();		
	}
</script>
</head>
<body  style="background: #FFFFFF" onLoad="inicial();">

	<div class="contenedor">
<c:set var="tipEmp" scope="session" value="${sessionScope.tipo_empleado}"/>
	<div>
<center>

	<c:if test="${tipEmp == 'GE'}">
		<%@include file="menuEmplA.jsp"%> 
	</c:if>
	<c:if test="${tipEmp == 'RH'}">
		<%@include file="menuEmplAA.jsp"%> 
	</c:if>
	<c:if test="${tipEmp == 'SS'}">
		<%@include file="header.jsp"%> 
	</c:if>
</center>
</div>
		<form name="frmDownload" action="/generadoc" method="post" target="_self">
			<input type="hidden" id="hiddenTexto" name="hiddenTexto" value="<%=request.getAttribute("sTexto").toString()%>">
			<input type="hidden" id="hiddenNombre" name="hiddenNombre" value="<%=request.getAttribute("sNombre").toString()%>">
			<table>
				<tr>
					<td>
						<p class="texto2">
							Log Generado......
						</p>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>	