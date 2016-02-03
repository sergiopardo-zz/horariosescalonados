<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.google.appengine.api.datastore.Entity"%>
<%@page import="com.gonet.horariosescalonados.bean.BeanHorario"%>
<%@page import="com.gonet.horariosescalonados.bean.BeanMensaje"%>
<%@page import="java.util.List"%>
<%@page import="com.gonet.horariosescalonados.util.AgregaHoras"%>
<%
		BeanMensaje msg5 =  (BeanMensaje)(request.getAttribute("message5")!=null?request.getAttribute("message5"):new Entity("Mensaje"));
		BeanMensaje msg6 =  (BeanMensaje)(request.getAttribute("message6")!=null?request.getAttribute("message6"):new Entity("Mensaje"));
		BeanMensaje msg7 =  (BeanMensaje)(request.getAttribute("message7")!=null?request.getAttribute("message7"):new Entity("Mensaje"));
		BeanMensaje msg8 =  (BeanMensaje)(request.getAttribute("message8")!=null?request.getAttribute("message8"):new Entity("Mensaje"));    
    %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!--   <link rel="stylesheet" type="text/css" href="../config/css/stylesHorarios.css"/> -->

<title>Mantenimiento de Horarios</title>
<link rel="stylesheet" type="text/css" href="../config/css/estilosHE.css" />
<link rel="stylesheet" type="text/css" href="../config/css/jquery-ui.css" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>

<script language="JavaScript" type="text/javascript">
var message5 = "<%=msg5.getMensajeClave()%> - <%=msg5.getTexto()%>";
var message6 = "<%=msg6.getMensajeClave()%> - <%=msg6.getTexto()%>";
var message7 = "<%=msg7.getMensajeClave()%> - <%=msg7.getTexto()%>";
var message8 = "<%=msg8.getMensajeClave()%> - <%=msg8.getTexto()%>";
var phorario = "..";
var paccion = "";
var pEntrada ="";
var pSalida ="";
var pKey ="";

function inicio(){
	
}

/*function Eliminar(reng){
	var Entrada =document.getElementById("entrada"+reng).innerHTML;
	var Salida =document.getElementById("salida"+reng).innerHTML;	
	var Key =document.getElementById("key"+reng).innerHTML;
	var horario = Entrada + ' a ' + Salida;
	var accion = "eliminar";	
	var m = (message6.replace("EHH:MM", Entrada)).replace("SHH:MM",Salida);	
	if(!confirm(m)) {
		return false;
	}else{		
		document.getElementById("HAccion").value = accion;
		document.getElementById("HEntrada").value = Entrada;
		document.getElementById("HSalida").value = Salida;
		document.getElementById("horarioKey").value = Key;		
		document.getElementById("Message").innerHTML = (message7.replace("EHH:MM", Entrada)).replace("SHH:MM",Salida);
		displayMessage();
		return true;
	}	
}*/


function Bloquear(reng,bandera) {	
	var Entrada =document.getElementById("entrada"+reng).innerHTML;
	var Salida =document.getElementById("salida"+reng).innerHTML;
	var Key =document.getElementById("key"+reng).innerHTML;	
	var horario = Entrada + ' a ' + Salida	
	var accion = ""; 
	document.getElementById("HEntrada").value = Entrada;
	document.getElementById("HSalida").value = Salida;
	document.getElementById("horarioKey").value = Key;
	if (bandera == '1'){
		accion = "bloqueado";
		document.getElementById("Message").innerHTML = (message5.replace("EHH:MM", Entrada)).replace("SHH:MM",Salida);
	}else{
		accion = "desbloqueado";
		document.getElementById("Message").innerHTML = (message8.replace("EHH:MM", Entrada)).replace("SHH:MM",Salida);
	}	
	document.getElementById("HAccion").value = accion;
	displayMessage();
			
}		

function AgregaHorario(){
	var hEntrada = document.getElementById("HoraEntrada").value;
	var hSalida = document.getElementById("HoraSalida").value;
	if(valida(hEntrada) && valida(hSalida)){
		var accion = "agrega";	
		document.getElementById("HEntrada").value = hEntrada;
		document.getElementById("HSalida").value = hSalida;	
		document.getElementById("HAccion").value = accion;
		myform.submit();
	}else{
		document.getElementById("MessageValidaHora").innerHTML ="Formato de hora incorrecto, favor de validar.";
		displayMessageValidaHora();
	}
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
	});
}

$(document).ready(function(){
	$("#aceptar2").click(function() {
		$("#dialogo2").dialog("close");
		myform.submit();
	});	
	
	$("#aceptar3").click(function() {
		document.getElementById("HAccion").value = paccion;
		document.getElementById("HEntrada").value = pEntrada;
		document.getElementById("HSalida").value = pSalida;
		document.getElementById("horarioKey").value = pKey;
		document.getElementById("Message").innerHTML = (message7.replace("EHH:MM", pEntrada)).replace("SHH:MM",pSalida);
		displayMessage();
		$("#dialogo3").dialog("close");
		
	});

	$("#cancelar").click(function() {
		$("#dialogo3").dialog("close");
	});
	
	$("#aceptarValida").click(function() {
		$("#dialogoValidaHora").dialog("close");		
	});	

	
});


function Agregar(ObThis){
	LimpiaCamposAgregarHorario();
	document.getElementById("idNuevoHorario").style.display = "";	
	document.getElementById("idNuevoHorariobotones").style.display = "";
		
	ObThis.setAttribute("class", "botonCandidatoDsb");
	ObThis.disabled = true;
	}
	
function CancelarAgregarHorario(){	
	LimpiaCamposAgregarHorario();
	document.getElementById("idNuevoHorario").style.display = 'none';	
	document.getElementById("idNuevoHorariobotones").style.display = 'none';
	
	document.getElementById("idAgregar").setAttribute("class", "botonCandidato");
	document.getElementById("idAgregar").disabled = false;	
	}	
	
function valida(valor){		
	//var re = /^(0[1-9]|1\d|2[0-3]):([0-5]\d):([0-5]\d)$/;
	//campo.value = "bebe";
	if(!/^([1-9]|1\d|2[0-3]):([0-5]\d)$/.test(valor)){
		return false;	
	}
	return true;
}

function HabilitaAceptar(campo){
	
	if(document.getElementById("HoraEntrada").value != "0:00" && document.getElementById("HoraSalida").value != "0:00"){		
		document.getElementById("aceptar").setAttribute("class", "botonCandidato");
		document.getElementById("aceptar").disabled = false;
		}	
	else
		{
		document.getElementById("aceptar").setAttribute("class", "botonCandidatoDsb");
		document.getElementById("aceptar").disabled = true;		
		}
	}	

	
function LimpiaCamposAgregarHorario(){		
		//document.getElementById("HoraEntrada").value = "0:00";
		//document.getElementById("HoraSalida").value = "0:00";		
		
		document.getElementById("aceptar").setAttribute("class", "botonCandidatoDsb");	
		document.getElementById("aceptar").disabled = true;		
	}
	
	function Eliminar(reng){
		pEntrada =document.getElementById("entrada"+reng).innerHTML;
		pSalida =document.getElementById("salida"+reng).innerHTML;
		pKey = document.getElementById("key"+reng).innerHTML;
		phorario = pEntrada + ' a ' + pSalida
		paccion = "eliminar";
		document.getElementById("MessageConfirm").innerHTML = (message6.replace("EHH:MM", pEntrada)).replace("SHH:MM",pSalida);
		displayMessageConfirm();
	}

	function displayMessageConfirm(){
		$(document).ready(function(){
			$("#dialogo3").dialog({
				width: 400,
				height: 200,
				show: "scale",
				hide: "scale",
				resizable: "false",
				position: "center",
				modal: "true"
			});
		});
	}
	

	function displayMessageValidaHora(){
		$(document).ready(function(){
			$("#dialogoValidaHora").dialog({
				width: 400,
				height: 200,
				show: "scale",
				hide: "scale",
				resizable: "false",
				position: "center",
				modal: "true"
			});
		});
	}
	
	
	
</script>
</head>
<body onload="inicio();">
	<form id="myform" action="/cargainicial" method="post">
		<input type="hidden" id="HAccion" name="HAccion" value="nulo">
		<input type="hidden" id="HEntrada" name="HEntrada"> 
		<input type="hidden" id="HSalida" name="HSalida"> 
		<input type="hidden" id="horarioKey" name="horarioKey">

		<table width="600px" class="contenedor" cellspacing="0"
			cellpadding="0" border="0">
			<tr>
				<td width="55%">
					<table class="tblSeguimientoT" width="83%" cellpadding="1"
						cellspacing="1">
						<tr>
							<td>Mantenimiento de Horarios</td>
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
						<fieldset class="fieldset-2">
							<legend class="Títulotabla"> Horarios </legend>
							<table width="100%" border="0" cellpadding="2" cellspacing="2"
								class="tblCandidato">
								<thead>
									<tr>
										<th width="25%">Empleados</th>
										<th width="25%">Entrada</th>
										<th width="25%">Salida</th>
										<th width="25%">Mantenimiento</th>
									</tr>
								</thead>
								<tbody>
<% 
				int iReng = 0;
				String cadena="";
				List<BeanHorario> result = (List)request.getAttribute("entity");				
				if(result != null){					
					for(BeanHorario bean: result){
						String id = bean.getHorarioId();
						if(!id.equals("00000000")){
							String sClaseTr = iReng++%2 == 0 ? "tblSeguimientoI1" : "tblSeguimientoI2";
							id = bean.getHorarioId();
%>
									<tr id="tr<%=iReng%>">
										<td style="display: none"><label id="key<%=iReng%>"><%=id%></label>
										</td>
										<td class="<%=sClaseTr%>" style="text-align: center"><label
											id="usuarios<%=iReng%>"><%= bean.getEmpleadosActivos()%></label>
										</td>
										<td class="<%=sClaseTr%>" style="text-align: center"><label
											id="entrada<%=iReng%>"><%=bean.getEntrada()%></label>
										</td>
										<td class="<%=sClaseTr%>" style="text-align: center"><label
											id="salida<%=iReng%>"><%= bean.getSalida()%></label>
										</td>
<% 
							if(bean.getEmpleadosActivos().equals("0")){
							//cadena=bean.getProperty("Entrada").toString();			
%>
										<td class="<%=sClaseTr%>" style="text-align: center">
											<img src="../config/images/eliminar1.png" alt="View" onClick="javascript:Eliminar('<%=iReng%>')" style="width: 32px; height: 32px;" class="mousetoHand"/>
										</td>
<%													
							}else{
								if (bean.getEnabled() == 1){%>
										<td class="<%=sClaseTr%>" style="text-align: center">										
											<img id="img1" src="../config/images/desbloqueado.png" alt="View" onClick="javascript:Bloquear('<%=iReng%>','1')" style="width: 32px; height: 32px" class="mousetoHand"/>
										</td>
								<%}else{%>
										<td class="<%=sClaseTr%>" style="text-align: center">
											<img id="img1" src="../config/images/bloqueado.png" alt="View" onClick="javascript:Bloquear('<%=iReng%>','0')" style="width: 32px; height: 32px" class="mousetoHand"/>
										</td>
<%
								}
							}
%>
									</tr>
<%		
						}
					}// fin del for
				}else{
%>
									<tr id="tr1">
										<td colspan="4" class="tblSeguimientoI1">No se cuenta con
											informaci&oacute;n</td>
									</tr>
				<%}%>								
								
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
									<input type="button" class="botonCandidato" id="idAgregar"
										value="Agrega Horario" name="Agregar Horario"
										onclick="javascript:Agregar(this)" />
								</div></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td><br /> <br /> <br /></td>
			</tr>
			<tr>
				<td>
					<div id="idNuevoHorario" align="center" style="display: none">
						<fieldset class="fieldset-4">
							<legend> Nuevo Horario </legend>
							<table width="30%" border="0" cellpadding="2" cellspacing="2"
								class="tblCandidatoH">
								<thead>
									<tr>
										<th width="15%">Entrada</th>
										<th width="15%">Salida</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>
										<input type="text" id="HoraEntrada" maxlength="5" onKeyUp="javascript:HabilitaAceptar(this);" style="width:80px;"/>										
										</td>
										<td>
										<input type="text" id="HoraSalida" maxlength="5" onKeyUp="javascript:HabilitaAceptar(this);" style="width:80px;"/>
										</td>
									</tr>
								<tbody>
							</table>
						</fieldset>
					</div>
				</td>
			</tr>
			<tr>
				<td>

					<table width="83%" cellpadding="0" cellspacing="0">
						<tr>
							<td>
								<div id="idNuevoHorariobotones" align="right"
									style="display: none">
									<br />
									<!--   <input type="button" class="botonCandidatoDsb" value="Cancelar" name="Cancelar"/>  -->
									<input type="button" class="botonCandidato" value="Cancelar"
										name="Cancelar" onclick="javascript:CancelarAgregarHorario();" />
									<input type="button" id="aceptar" class="botonCandidatoDsb"
										value="Aceptar" name="aceptar**"
										onclick="javascript:AgregaHorario();" /> <br /> <br />
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>		
		<div id="dialogo2" class="ventana">
			<label id="Message"></label> <br> <br> <br>
			<center>
				<input type="button" value="Aceptar" class="botonCandidato"
					id="aceptar2" />
			</center>
		</div>
		<div id="dialogo3" class="ventana">
			<label id="MessageConfirm"></label> <br> <br>
			<center>
				<input type="button" value="Aceptar " class="botonCandidato"
					id="aceptar3" />&nbsp; <input type="button" value="Cancelar"
					class="botonCandidato" id="cancelar" />
			</center>
		</div>
		<div id="dialogoValidaHora" class="ventana">
			<label id="MessageValidaHora"></label> <br /> <br />
			<div align="center">
				<input type="button" value="Aceptar " class="botonCandidato"
					id="aceptarValida" />
			</div>
		</div>		
	</form>
</body>
</html>

