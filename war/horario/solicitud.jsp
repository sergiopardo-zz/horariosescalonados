<%@page import="com.gonet.horariosescalonados.bean.BeanHorario"%>
<%@page import="com.gonet.horariosescalonados.bean.BeanMensaje"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>

<%	
	String sHorarioAct = (String)(request.getAttribute("HorarioActual")!=null?request.getAttribute("HorarioActual"):"1");
	String sHorarioEna = (String)(request.getAttribute("Enabled")!=null?request.getAttribute("Enabled"):"false");
	String selectIdUser = (String)(request.getAttribute("selectedIdUser")!=null?request.getAttribute("selectedIdUser"):"");
	String selectNombre = (String)(request.getAttribute("selectedNombre")!=null?request.getAttribute("selectedNombre"):"");
	String radioChecked = (String)(request.getAttribute("radioChecked")!=null?request.getAttribute("radioChecked"):"");
	String idChecked = (String)request.getAttribute("idChecked")!=null?request.getAttribute("idChecked").toString():"false";
	BeanMensaje message =  (BeanMensaje)(request.getAttribute("message")!=null?request.getAttribute("message"):null);
	int iReng = 0;
	Iterable<BeanHorario> elementos = (Iterable)request.getAttribute("Horarios");
	
	// Ciclo para contar los registros de horarios
	if(elementos!=null){
		for(BeanHorario eHorario:elementos) {
			iReng++; // Se incrementa el contador
		}	
	}else{
		iReng=0;
	}
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Solicitud</title>
<link rel="stylesheet" type="text/css"
zhref="../config/css/estilosHE.css" />
<link rel="stylesheet" type="text/css"
	href="../config/css/jquery-ui.css" />
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>

<script type="text/javascript">
	var radioSelectedTr = "<%=sHorarioAct%>";
	var vRegAutSel = "";
	var vNomAutSel = "";

	function getVal(obj){
		var ret = document.getElementById(obj);
		return ret;
	}

	function inicial(){
		var idUser = "<%=selectIdUser%>";
		var nameUser = "<%=selectNombre%>";
		var radio = "<%=radioChecked%>";
		var isCheked =  <%=idChecked!=""?idChecked:"false"%>
		if((radio!="0") || isCheked || (idUser!="")){
			for	(i=1; i<=<%=iReng-1%>; i++){
				var id = getVal(i).value;
				if(radio==id){
					getVal(i).checked = true;
					getVal("trE"+radio).setAttribute("class", "tblSeguimientoI3");
					getVal("trS"+radio).setAttribute("class", "tblSeguimientoI3");
					getVal("trN"+radio).setAttribute("class", "tblSeguimientoI3");
				}
			}					
			getVal("validate").checked = isCheked;			
			if(idUser!=""){
				getVal("hiddenNameSupervisor").value = nameUser;
				getVal("hiddenIdSupervisor").value = idUser;
				getVal("nom_autorizador").value = nameUser;
			}
			habilitaEnviar();		
		}
	}
	
	function checkRadio(){
	}

	function onclickRadio(){
		cleanRadio();	
		habilitaEnviar();
		var i=0;
		for	(i=1; i<=<%=iReng-1%>; i++){
			var id = getVal(i).value;
			if(getVal(i).checked){
				getVal("trE"+id).setAttribute("class", "tblSeguimientoI3");
				getVal("trS"+id).setAttribute("class", "tblSeguimientoI3");
				getVal("trN"+id).setAttribute("class", "tblSeguimientoI3");
				getVal("hiddenSelectedRadio").value = id;
			}		
		}		
	}
	
	function cleanRadio(){
		var i=0;
		for	(i=1; i<=<%=iReng-1%>; i++){
			if(getVal(i).value!=radioSelectedTr){
				var id = getVal(i).value;				
				if(i%2==0){
					getVal("trE"+id).setAttribute("class", "tblSeguimientoI2");
					getVal("trS"+id).setAttribute("class", "tblSeguimientoI2");
					getVal("trN"+id).setAttribute("class", "tblSeguimientoI2");
				} else {
					getVal("trE"+id).setAttribute("class", "tblSeguimientoI1");
					getVal("trS"+id).setAttribute("class", "tblSeguimientoI1");
					getVal("trN"+id).setAttribute("class", "tblSeguimientoI1");			
				}
			}
		}		
	}

	function handCursor(obj){
		obj.style.cursor = "hand";
	}

	function pinterCursor(obj){
		obj.style.cursor = "pointer";
	}

	function lanzar(){
		
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
	
	function habilitaEnviar(){
		var radioChecked = false;
		
		for(var i=1; i<=<%=iReng-1%>; i++){
			if(getVal(i).checked){
				radioChecked = true;
				getVal("hiddenSelectedRadio").value = getVal(i).value;
			}			
		} 
		var checkBuChecked = getVal("validate").checked;
		
		var textFilled = false;

		if((getVal("nom_autorizador").value!="") && (getVal("nom_autorizador").value!="null") 
				&& (getVal("nom_autorizador").value!="null null, null")){
			textFilled = true;
		}
		
		if(radioChecked && checkBuChecked && textFilled){
			getVal("enviar").disabled = false;
			getVal("enviar").setAttribute("class", "botonCandidato");
		}else{
			getVal("enviar").disabled = true;
			getVal("enviar").setAttribute("class", "botonCandidatoDsb");
		}
	}
	
	$(document).ready(function(){

		$("#aceptar2").click(function() {
			$("#dialogo2").dialog("close");
			frmSolicitud.submit();
		});

		$('#b1').click(function(){
			frmSolicitud.action="/buscasupervisores";
			getVal("hiddenIsChecked").value = getVal("validate").checked;
			frmSolicitud.submit();
							
		});
	});

	</script>
</head>
<body class="BodyPrincipal" style="background: #FFFFFF" onLoad="checkRadio();inicial();">
	<div id="ContenedorPrincipal">
		<form name="frmSolicitud" action="/generarsolicitud" method="post" target="_self">
			<input type="hidden" id="hiddenSelectedRadio" name="hiddenSelectedRadio" value="0"> 
			<input type="hidden" id="hiddenIsChecked" name="hiddenIsChecked" value=""> 
			<input type="hidden" id="hiddenNameSupervisor" name="hiddenNameSupervisor" value="<%=request.getAttribute("nomAutorizador")%>"> 
			<input type="hidden" id="hiddenIdSupervisor" name="hiddenIdSupervisor" value="<%=request.getAttribute("idAutorizador")%>"> 
			<input type="hidden" id="opcion" name="opcion" value="solicitud"/>
			<table width="100%" cellspacing="0" cellpadding="0" border="0"
				class="contenedor">
				<tr>
					<td width="55%;">
						<table width="83%" cellspacing="1" cellpadding="1"
							class="tblSeguimientoT" border="0">
							<tr>
								<td>Selecci&oacute;n de Horario Laboral</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>
						<p align="center" style="font: Geneva, Arial, Helvetica, sans-serif; color:red">Estimado usuario, 
						aun no tenemos registrado tu horario Escalonado 
						por lo que te pedimos acordarlo con tu jefe directo y 
						posteriormente registrar tu solicitud, gracias.</p>
						<fieldset class="fieldset-2">
							<legend class="tituloVentanaIzq">Horarios disponibles</legend>
							<table width="100%" cellpadding="2" cellspacing="2" border="0"
								class="tblCandidato">
								<thead>
									<tr>
										<th width="35%">Entrada</th>
										<th width="35%">Salida</th>
										<th width="30%">Nuevo</th>
									</tr>
								</thead>
								<tbody>
	<%	iReng = 0;
	Iterable<BeanHorario> e = (Iterable)request.getAttribute("Horarios");
	
	if(e!=null){
		for(BeanHorario eHorario:e) {
			String id = eHorario.getHorarioId();
			if(!id.equals("00000000")){
				String sClaseTr = iReng++%2 == 0 ? "tblSeguimientoI1" : "tblSeguimientoI2"; 
				int enabled = eHorario.getEnabled();
				id = eHorario.getHorarioId();
				String entrada = eHorario.getEntrada();
				String salida = eHorario.getSalida();
				String block = "none",block1 = "none", block2 = "none";
				if(enabled==0){
					block="block";
				}
				if(id.equals(sHorarioAct)){
					//block1 = "block";
					block1 = "none";
				}
				//if(!enabled.equals("false") && !id.equals(sHorarioAct)){
				if(enabled!=0){
					block2 = "block";
				}
			
			%>
									<tr id="tr<%=iReng%>">
										<td id="trE<%=id%>" class="<%=sClaseTr%>"
											style="text-align: center;"><%=entrada%></td>
										<td id="trS<%=id%>" class="<%=sClaseTr%>"
											style="text-align: center;"><%=salida%></td>
										<td id="trN<%=id%>" class="<%=sClaseTr%>" align="center">
											<input type="radio" onClick="onclickRadio();" name="rbdgp"
											id="<%=iReng%>" value="<%=id%>" style="display: <%=block2%>;" />
											<div id="divActual<%=id%>" style="display: <%=block1%>">Actual</div>
											<div id="divSaturado<%=id%>" style="display: <%=block%>">Horario
												Saturado</div>
										</td>
									</tr>
<%
			}	//Fin if !id.equals("00000000")
		}//fin For 
	}else{
%>
									<tr id="tr1">
										<td colspan="4" class="tblSeguimientoI1">No se cuenta con
											iformaci&oacute;n</td>
									</tr>
									<%		
} %>
								</tbody>
							</table>
						</fieldset>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="4">
						<table width="100%" cellspacing="10" cellpadding="5" border="0">
							<tr>
								<td width="55%;" class="tblSeguimientoI1">He validado
									previamente el cambio con mi superior</td>
								<td width="10%;" class="tblSeguimientoI1"><input
									type="checkbox" name="validate" id="validate"
									onClick="habilitaEnviar();"></input></td>
								<td width="15%;">&nbsp;</td>
								<td width="20%;">&nbsp;</td>
							</tr>

							<tr>
								<td class="tblSeguimientoI1">La autorizaci&oacute;n ser&aacute; enviada a*</td>
								<td colspan="3" class="tblSeguimientoI1"><input type="text"
									class="cajaTextoEx" size="30" id="nom_autorizador"
									name="nom_autorizador" onKeyUp="habilitaEnviar();"
									value="<%=request.getAttribute("nomAutorizador")%>" disable>
									<img src="../config/images/BTPGLUPA.gif"
									style="width: 40px; height: 20px; vertical-align: top;"
									onMouseOver="javascript:handCursor(this);"
									onMouseOut="pinterCursor(this);" id="b1" /></td>
							</tr>
							<tr>
								<td colspan="4"><input type="button"
									class="botonCandidatoDsb" disabled id="enviar" value="Enviar"
									size="30" onClick="lanzar();" /></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="4" class="confirmacion">* En caso de no ser
						correcto el nombre del jefe, debe realizar la b&uacute;squeda para
						seleccionar a la persona correcta.</td>
				</tr>
			</table>
		</form>
	</div>
	<div id="dialogo2" class="ventana">
		<%=message.getMensajeClave()%>		-
		<%=message.getTexto()%>
		<br> <br> <br>
		<center>
			<input type="button" value="Aceptar" class="botonCandidato"
				id="aceptar2" />
		</center>
	</div>
	<br>
	<br>
	<br>

</body>
</html>


