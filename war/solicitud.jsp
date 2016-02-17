
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@page import="com.gonet.horariosescalonados.bean.BeanHorario"%>
<%@page import="com.gonet.horariosescalonados.bean.BeanMensaje"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
	href="../config/css/estilosHE.css" />
<link rel="stylesheet" type="text/css"
	href="../config/css/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="config/css/estilo.css"/>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script
	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>

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
					getVal("trE"+radio).setAttribute("class", "textoRadio");
					getVal("trS"+radio).setAttribute("class", "textoRadio");
					getVal("trN"+radio).setAttribute("class", "textoRadio");
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
				getVal("trE"+id).setAttribute("class", "textoRadio");
				getVal("trS"+id).setAttribute("class", "textoRadio");
				getVal("trN"+id).setAttribute("class", "textoRadio");
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
					getVal("trE"+id).setAttribute("class", "textoRadio");
					getVal("trS"+id).setAttribute("class", "textoRadio");
					getVal("trN"+id).setAttribute("class", "textoRadio");
				} else {
					getVal("trE"+id).setAttribute("class", "textoRadio");
					getVal("trS"+id).setAttribute("class", "textoRadio");
					getVal("trN"+id).setAttribute("class", "textoRadio");			
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
<body class="" style="background: #FFFFFF" onLoad="checkRadio();inicial();">
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
	
	<c:if test="${tipEmp == 'CC'}">
	<%@include file="menuEmplC.jsp"%> 
	</c:if>
		
		<form name="frmSolicitud" action="/generarsolicitud" method="post" target="_self">
			<input type="hidden" id="hiddenSelectedRadio" name="hiddenSelectedRadio" value="0"> 
			<input type="hidden" id="hiddenIsChecked" name="hiddenIsChecked" value=""> 
			<input type="hidden" id="hiddenNameSupervisor" name="hiddenNameSupervisor" value="<%=request.getAttribute("nomAutorizador")%>"> 
			<input type="hidden" id="hiddenIdSupervisor" name="hiddenIdSupervisor" value="<%=request.getAttribute("idAutorizador")%>"> 
			<input type="hidden" id="opcion" name="opcion" value="solicitud"/>
			<div class="contenidoMiga">
				<ul class="posicionMiga">	
					<li class="menuMiga">
						<span class="iconoMiga imgMiga"></span>
					</li>
					<li class="menuMiga">
						<span class="separacionMiga">-</span>
						<span class="tituloMiga">Solicitud</span>
					</li>
				</ul>				
			</div>
	
		  <div class="contenidoSolicitud">
						<div class="aviso">
				 Aún no tenemos registrado tu horario escalonado, por lo que te pedimos acordarlo con tu jefe directo y posteriormente registrar tu solicitud.
			</div>
			<br>
			<br>
			<p class="subTitulos">
				Horarios disponibles
			</p>
		   <!-- Opciones de horarios -->
			<table   class="radio-group" border="0" style="margin: 0 auto;">
			   <tbody>
	<%	iReng = 0;
	Iterable<BeanHorario> e = (Iterable)request.getAttribute("Horarios");
	
	if(e!=null){
		for(BeanHorario eHorario:e) {
			String id = eHorario.getHorarioId();
			if(!id.equals("00000000")){
				String sClaseTr = iReng++%2 == 0 ? "textoRadio" : "textoRadio"; 
				int enabled = eHorario.getEnabled();
				id = eHorario.getHorarioId();
				String entrada = eHorario.getEntrada();
				String salida = eHorario.getSalida();
				String block = "none",block1 = "none", block2 = "none";
				if(enabled==0){
					block="block";
		            %>
		            <script> 
		            $(document).ready(function(){ getVal('trN'+<%=id%>).style.display = "none";
		            $('#trE'+ <%=id%> + ',' + '#trS'+<%=id%>).css({"color": "#0080C0", "font-weight": "bold", "background-color":"rgb(235, 235, 228)"});}); 
		            </script>
		            <%
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
			
<p id="tr<%=iReng%>">
         <td id="trE<%=id%>" class="<%=sClaseTr%>"
          style="text-align: center;"><%=entrada%></th>
         <td id="trS<%=id%>" class="<%=sClaseTr%>"
          style="text-align: center;"><%=salida%></th>
         <td id="trN<%=id%>" class="<%=sClaseTr%>" align="center"><input
          type="radio" class="textoRadio" onClick="onclickRadio();"
          name="rbdgp" id="<%=iReng%>" value="<%=id%>" style="display: <%=block2%>;" />
         </td>
         <td style="font-family: 'Arial'; font-size: 13px;">
          <div id="divActual<%=id%>" style="font-weight: bold; color: #0080C0; display: <%=block1%>">Actual</div>
          <div id="divSaturado<%=id%>" style="background-color:rgb(235, 235, 228);  font-weight: bold;color: #0080C0; display: <%=block%>">Horario Saturado</div>
         </td>
       </p>
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
							</table><br><br>
					<div class="">
						<table class="" style ="margin-left: 40px;">
								<td width="55%;" class="etiqueta1">He validado
									previamente el cambio con mi superior</td>
								<td width="10%;" class=""><input
									type="checkbox" class="check1" name="validate" id="validate"
									onClick="habilitaEnviar();"></input></td>
								<td width="15%;">&nbsp;</td>
								<td width="20%;">&nbsp;</td>
						</table><br><br>
						</div>
							<table class="" style ="margin-left: 40px;">
								<td class="etiqueta1">La autorizaci&oacute;n ser&aacute; enviada a*</td>
								<td colspan="3" class=""><input type="text"
									class="campos" size="30" id="nom_autorizador"
									name="nom_autorizador" onKeyUp="habilitaEnviar();"
									value="<%=request.getAttribute("nomAutorizador")%>" disabled>
									<img src="../config/img/Ic_Busqueda.png"
									style="width: 20px; height: 20px; vertical-align: -5px;"
									onMouseOver="javascript:handCursor(this);"
									onMouseOut="pinterCursor(this);" id="b1" />
								<div class=""></table>
							<br><br>
							<div class="aviso">
								En caso de no ser correcto el nombre del jefe, debe realizar la búsqueda para seleccionar a la persona correcta.
						   </div>
						   <br>
						   <br>
				<input type="button" class="botonCandidatoDsb" disabled id="enviar" value="Enviar"
				         size="30" onClick="lanzar();" />

		</form>
	</div>
	<div id="dialogo2" class="ventana">
		<%=message.getMensajeClave()%>
		<%=message.getTexto()%>
		<br> <br> <br>
		<center>
			<input type="button" value="Aceptar" class="botonMsg"
				id="aceptar2" />
		</center>
	</div>
	<br>
	<br>
	<br>

</body>
</html>


