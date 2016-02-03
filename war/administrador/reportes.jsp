<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="com.google.appengine.api.datastore.Entity"%>
<%@page import="com.gonet.horariosescalonados.bean.BeanMensaje"%>

<%
	//Entity msg9 =  (Entity)(request.getAttribute("message9")!=null?request.getAttribute("message9"):new Entity("Mensaje"));

	BeanMensaje msg9 =  (BeanMensaje)(request.getAttribute("message9")!=null?request.getAttribute("message9"):new Entity("Mensaje"));
	String fechaActual = request.getAttribute("pFecha").toString();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Reportes</title>

<link rel="stylesheet" type="text/css" href="../config/css/calendar_styles.css" />
<link rel="stylesheet" type="text/css" href="../config/css/estilosHE.css" />
<link rel="stylesheet" type="text/css" href="../config/css/calendar_styles.css" />
<link rel="stylesheet" type="text/css" href="../config/css/jquery-ui.css" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
<script type="text/javascript" src="../config/js/popcalendar.js"></script>
<script type="text/javascript">
		
	function CambioDeVentana() {	
		var indice = document.getElementById('IDComboReporte').value;
		document.getElementById("hiddenDesde").value = document.getElementById("fechaSince").value;
		document.getElementById("hiddenHasta").value = document.getElementById("fechaTo").value;
		switch(indice) {
			case "alta":
				document.getElementById("opcionReporte").value = "alta";
				modal();
				break;
			 case "modificacion":
				document.getElementById("opcionReporte").value = "modificacion";
				modal();
				break;
			 case "baja":
				document.getElementById("opcionReporte").value = "baja";
				modal();
				break;
			 case "noasignacion":
				document.getEFlementById("opcionReporte").value = "noasignacion";
				modal();
				break;		
			 case "cumplimiento":
				document.getElementById("opcionReporte").value = "cumplimiento";
				modal();
				break;
			 case "cyge":
				document.getElementById("opcionReporte").value = "cyge";
				modal();
				break;
			case "cumplimientoExterno":
				document.getElementById("opcionReporte").value = "cumplimientoExterno";
				modal();
					break;
		} 	
	}
	
	function modal(){
		document.getElementById("Message").innerHTML = "<%=msg9.getMensajeClave().toString()%> - <%=msg9.getTexto().toString()%>";
		displayMessage();		
	}

	function displayMessage(){
		$(document).ready(function(){
			$("#dialogo2").dialog({
				width: 390,
				height: 250,
				show: "scale",
				hide: "scale",
				resizable: "false",
				position: "center",
				modal: "true"
			});
			document.getElementById("aceptar2").focus();
		});
	}

	$(document).ready(function(){
		$("#aceptar2").click(function() {
			$("#dialogo2").dialog("close");
			formreportes.action = "/generaexcel";
			//alert(document.getElementById("hiddenDesde").value+" "+document.getElementById("hiddenHasta").value)
			formreportes.submit();
		});
	});
    </script>
</head>
<body class="BodyPrincipal" style="background: #FFFFFF" onload="">
	<form id="formreportes" action="/generareporte" method="post">
		<input type="hidden" name="opcionReporte" id="opcionReporte" />
		<input type="hidden" name="hiddenDesde" id="hiddenDesde" />
		<input type="hidden" name="hiddenHasta" id="hiddenHasta" />
		<div id="ContenedorPrincipal">
			<table width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td width="70%;">
						<table width="100%" cellspacing="10" cellpadding="10"
							class="contenedor" border="0">
							<tr>
								<td width="55%">
									<table class="tblSeguimientoT" width="83%" cellpadding="1"
										cellspacing="1">
										<tr>
											<td>Reportes</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td>
									<div align=center>
										<fieldset class="fieldset-3">
											<legend class="Títulotabla"> Filtros </legend>
											<table width="100%" border="0" cellpadding="2"
												cellspacing="2" class="tblCandidato">
												<thead>
													<tr>
														<th colspan="2" width="40%">Periodo</th>
														<th width="60%">Reporte</th>
													</tr>
													<tr class="CabeceraTabla">
														<th>De:</th>
														<th>A:</th>
														<th>&nbsp;</th>
													</tr>
												</thead>
												<tbody>
													<tr class="tblSeguimientoI1" id="tr1">
														<td><input type="text" name="fechaSince"
															id="fechaSince" readonly value="<%=fechaActual%>"
															size="7" class="texto" style="width: 73%"
															onblur="javascript:cambiaFechaSolic();" /> <img
															src="../config/images/calendar.gif" alt="fecha"
															onclick="javascript:displayCalendar(document.getElementById('fechaSince'), 'dd/mm/yyyy');"
															style="cursor: hand;" /></td>
														<td><input type="text" name="fechaTo" id="fechaTo"
															readonly value="<%=fechaActual%>" size="7" class="texto"
															style="width: 73%"
															onblur="javascript:cambiaFechaSolic();" /> <img
															src="../config/images/calendar.gif" alt="fecha"
															onclick="javascript:displayCalendar(document.getElementById('fechaTo'), 'dd/mm/yyyy');"
															style="cursor: hand;" /></td>
														<td><select class="combo1" id="IDComboReporte"
															size="1" style="width: 100%">
																<option value="0">- Seleccionar -</option>
																<option value="alta">Alta</option>
																<option value="modificacion">Modificación</option>
																<!--  option value="baja">Baja</option>-->
																<option value="noasignacion">No asignados</option>
																<option value="cumplimiento">Cumplimiento</option>
																<option value="cyge">Cyge</option>
																<option value="cumplimientoExterno">Cumplimiento Externo</option>
														</select></td>
													</tr>
													<tr id="tr2">
														<td>&nbsp;</td>
														<td>&nbsp;</td>
														<td>&nbsp;</td>
													</tr>
												</tbody>
											</table>
										</fieldset>
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<table width="83%" cellpadding="0" cellspacing="0">
										<tr>
											<td><br />
												<div align="right">
													<input type="button" id='BTNCONSULTAR'
														class="botonCandidato" value='Generar'
														onclick="javascript:CambioDeVentana()" />
												</div></td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</div>
		<div id="dialogo2" class="ventana">
			<label id="Message"></label> <br /> <br />
			<div align="center">
				<input type="button" value="Aceptar " class="botonCandidato" id="aceptar2" />
			</div>
		</div>
	</form>
</body>
</html>
