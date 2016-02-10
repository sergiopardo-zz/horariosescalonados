<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
    <%@page import="com.gonet.horariosescalonados.bean.BeanMensaje"%>
    
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.google.appengine.api.datastore.Entity"%>
<%@page import="com.gonet.horariosescalonados.bean.BeanMensaje"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Horarios Escalonados</title>
	<link rel="stylesheet" type="text/css" href="config/css/estilo.css"/>
	<link rel="stylesheet" type="text/css" href="config/css/jquery-ui.css"/>
	<script type="text/javascript" src="config/js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="config/js/acciones.js"></script>
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
	<script type="text/javascript" src="config/js/ui.js"></script>
	
	<script type="text/javascript" language="javascript">
	var selectIndex=0;
	var selectValue="";


	function mensaje(){
		document.forms[0].submit();
	}
	
	function SeleccionarMensaje(selectObj){
		selectIndex=selectObj.selectedIndex;
   		selectValue=selectObj.options[selectIndex].value;
   		selectText=selectObj.options[selectIndex].text;
   		
		document.getElementById("tdTextoActual").innerHTML = selectValue; 
		
		fHeMensajes.nuevo_texto.value = "";
		fHeMensajes.nuevo_texto.disabled = "true";
		
		if(selectIndex > 0) {
			fHeMensajes.nuevo_texto.disabled = "";
			fHeMensajes.nuevo_texto.focus();
			
			document.getElementById("autorizar").disabled = false;
			document.getElementById("autorizar").setAttribute("class", "botonReposo");
		}
	}
		
	function modificacionMensaje(){
		var accion = "actualizar";
		document.getElementById("HAccion").value = accion;
		var Key =  selectText;
		document.getElementById("mensajeKey").value = Key;
		document.getElementById("mensajetexto").value = document.getElementById("nuevo_texto").value;
	}
	
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
<div class="contenedor">
	
	<c:set var="tipEmp" scope="session" value="${sessionScope.tipo_empleado}"/>
	<c:choose>
	  	<c:when test="${tipEmp=='A' }">
  			<%@include file="menuEmplA.jsp"%> 
  		</c:when>
  		<c:otherwise>
    		<%@include file="header.jsp"%> 
  		</c:otherwise>
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
					<span class="tituloMiga">Mensajes</span>
				</li>
			</ul>				
		</div>
		<!-- Fin Miga -->

		<!-- Inicio Mensajes -->
			<form name="fHeMensajes" action="/cargainicialmensajes" onsubmit="javascript:mensaje()" method="post" target="_self">
			<input type="hidden" id="HAccion" name="HAccion" value="nulo">
			<input type="hidden" id="mensajeKey" name="mensajeKey">
			<input type="hidden" id="mensajetexto" name="mensajetexto">	
		<div class="contenidoMensajes">			
			<div class="aviso">
				<span>Al modificar los mensajes que presenten horarios de entrada y salida debe respetar los formatos EE:MM (Entrada) y SHH:MM (Salida) para su correcto funcionamiento.</span>
			</div>
			<br>
			<br>
			<div class="moduloContenido" id="container">
				<div class="tituloModulo tx1Tabla">Mantenimiento mensajes</div>
					<table class="tablas" cellpadding="0" width="100%">
					<tbody>
						<tr class="tituloModulo tx2Tabla">
							<th class="tamTabla">Mensaje</th>
							<th class="tamTabla">Texto actual</th>
							<th class="tamTabla">Texto nuevo</th>
						</tr>
						<tr class="tx3Tabla">
							<td>
								
									<select class="contenidoSelecciona tamSelMensajes" onchange="javascript:SeleccionarMensaje(this)">
										<option class="opcionesSelecciona">Selecciona</option>	
											<c:forEach items="${entity}" var ="mensajes">
											
												<option value="${mensajes.texto}" class="opcionesSelecciona">
												${mensajes.mensajeClave}
												</option>
												
											</c:forEach>
										</select>
									</div>
								</div>
							</td>
							<td >
								<label id="tdTextoActual"></label>
							</td>
							<td>
								<input class="inputMensajes campoObligatorio fTextoMensajes" id="nuevo_texto" name="nuevo_texto" maxlength="250" disabled="disabled"></input>
							</td>
						</tr>
									
				</tbody>
				</table>
			</div>
			<br>
			<br><br>
			<div align="center">
     			 <input type="button" class="botonMsg" disabled id="autorizar" value="Aceptar" onclick="javascript:modificacionMensaje();" style="margin-left: 0px" />
    	    </div>
		</div>
		
		<!-- Fin Mensajes -->
	</div>
	<center>
		<div id="dialogo2" class="ventana" style="height: 50px;display:none;font-family: Arial, Helvetica, sans-serif;font-size:15px">
		<label id="Message">Se han guardado los cambios.</label> <br /> <br />
		<div align="center">
			<input type="button" value="Aceptar " class="botonMsg" id="aceptar2" />
		</div>
	</div>	
	<center>	
	<!-- Final Contenido -->
	
</div>
	</form>
</body>
</html>