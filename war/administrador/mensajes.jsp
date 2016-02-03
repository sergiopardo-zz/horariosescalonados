<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.google.appengine.api.datastore.Entity"%>
<%@page import="com.gonet.horariosescalonados.bean.BeanMensaje"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Autorizar Solicitud</title>

<link rel="stylesheet" type="text/css" href="../config/css/estilosHE.css"/>

<link rel="stylesheet" type="text/css"
	href="../config/css/jquery-ui.css" />
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>

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
			document.getElementById("autorizar").setAttribute("class", "botonCandidato");
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
<body class="BodyPrincipal" style="background:#FFFFFF">
	<form name="fHeMensajes" action="/cargainicialmensajes" onsubmit="javascript:mensaje()" method="post" target="_self">
	<input type="hidden" id="HAccion" name="HAccion" value="nulo">
	<input type="hidden" id="mensajeKey" name="mensajeKey">
	<input type="hidden" id="mensajetexto" name="mensajetexto">			
	 <table width="100%" cellspacing="10" cellpadding="10" class="contenedor" border="0">
		<tr>
			<td width="55%">
				<table class="tblSeguimientoT" width="83%" cellpadding="1" cellspacing="1">
					<tr>
						<td>Mantenimiento Mensajes</td>
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
				<legend  class="Títulotabla"> Mensajes </legend>
					<table width="100%" border="0" cellpadding="2" cellspacing="2" class="tblCandidato">
						<thead>   
							<tr>		
								<th colspan="1" width="20%">
									Mensaje
								</th>
								<th colspan="1" width="40%">
									Texto Actual
								</th>						
								<th width="40%">
									Texto Nuevo
								</th>
							</tr>					
						</thead> 						
						<tbody>
	             			<tr class="tblSeguimientoI1" id="tr1">	             
                 				<td id="tdCombo">
									<select name="cb1" class="combo1"  onchange="javascript:SeleccionarMensaje(this)" style="width: 100%">
										<option value="">--Selecciona--</option>																											
							<%			
							String texto,id;
							List<BeanMensaje> result = (List)request.getAttribute("entity");
							for(BeanMensaje bean: result){
								id = bean.getMensajeClave(); 
								texto = bean.getTexto();
								%>							 
							<option value="<%=texto%>"><%=id%></option>
							<%
							}
							%>
							</select>
				                 </td>
				                 <td id="tdTextoActual" valign="top">&nbsp;
				                 </td>
				                 <td>					                                
									<textarea id="nuevo_texto" name="nuevo_texto" class="texto" style="width: 98%; height: 50px;" maxlength="250" disabled="disabled"></textarea>
								</td>
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
				<div align="center">	
					<table width="65%" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<td>
								<br/>
								<div align="right">
								<input type="button" class="botonCandidatoDsb" 
									disabled id="autorizar" value="Aceptar" 
									onclick="javascript:modificacionMensaje();"/>
								</div>											
							</td>
						</tr>
						<tr>
							<td  class="confirmacion">								
									* Estimado usuario, al modificar los mensajes que presenten horarios de entrada y salida 
									debe respetar los formatos EHH:MM (Entrada) y SHH:MM (Salida) para su correcto funcionamiento.								
							</td>
						</tr>
					</table>
				</div>				
			</td>
		</tr> 
       </table>	 
	<div id="dialogo2" class="ventana" style="height: 50px;">
		<label id="Message">Se han guardado los cambios.</label> <br /> <br />
		<div align="center">
			<input type="button" value="Aceptar " class="botonCandidato" id="aceptar2" />
		</div>
	</div>	
	
	</form>
			
</body>
</html>
