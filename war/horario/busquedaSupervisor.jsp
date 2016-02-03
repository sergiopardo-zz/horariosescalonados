<%@page import="com.gonet.horariosescalonados.bean.BeanEmpleado"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String empleado = session.getAttribute("usuario")==null?"":(String)session.getAttribute("usuario");;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="../config/css/estilosHE.css" />
<link rel="stylesheet" type="text/css"
	href="../config/css/jquery-ui.css" />
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js">
	</script>

<script type="text/javascript">
	function getVal(obj){
		var ret = document.getElementById(obj);
		return ret;
	}
	
	function inicial(){
	}
	
	function searchUser(){
		//getVal("divPopup").style.display="block";
		getVal("flag").value = "1";
		document.frmBusqueda.action="/buscasupervisores";
		document.frmBusqueda.method="post";
		getVal("evento").value="buscar"
		//document.frmBusqueda.submit();
	}

	function enabledAcept(id) {
		var rbSeleccionado = getVal(id);
		if(rbSeleccionado.checked) {
			getVal("aceptar").disabled = false;
			getVal("aceptar").setAttribute("class", "botonCandidato");
			var vValoresRb = rbSeleccionado.value;
			var vDatos = vValoresRb.split("|");
			vRegAutSel = vDatos[0];
			vNomAutSel = vDatos[1];
			asignaValores(vRegAutSel, vNomAutSel);
		} else {
			getVal("aceptar").disabled = true;
			getVal("aceptar").setAttribute("class", "botonCandidatoDsb");
			vRegAutSel = "";
			vNomAutSel = "";
		}
	}
	
	function enableSearchButton() {
		var a = getVal("idUser").value;
		var b = getVal("registerUser").value;
		var c = getVal("nameUser").value;
		var d = getVal("firstNameUser").value;
		var e = getVal("secondNameUser").value;
	
		// Se valida que los campos NO sean sólo espacios
		a = soloEspacios(a) ? "" : a;
		b = soloEspacios(b) ? "" : b;
		c = soloEspacios(c) ? "" : c;
		d = soloEspacios(d) ? "" : d;
		e = soloEspacios(e) ? "" : e;
		/*
		Se habilitará el boton [Buscar] si:
			- sólo se ha capturado el ID del usuario
			- ó sólo se ha capturado el número de registro del empleado
			- ó se ha capturado el Nombre y Apellido Paterno
			- ó se ha capturado el Nombre y Apellido Materno
			- ó se ha capturado el Apellido Paterno y Apellido Materno
		*/
		if(a!="" || b!="" || (c!="" && d!="") || (c!="" && e!="") || (d!="" && e!="")) {
			getVal("buscar").disabled = false;
			getVal("buscar").setAttribute("class", "botonCandidato");
		} else {
			getVal("buscar").disabled = true;
			getVal("buscar").setAttribute("class", "botonCandidatoDsb");			
		}
		// Se convierten los valores de los campos a mayusculas
		getVal("idUser").value = a.toUpperCase();
		getVal("registerUser").value = b.toUpperCase();
		getVal("nameUser").value = c.toUpperCase();
		getVal("firstNameUser").value = d.toUpperCase();
		getVal("secondNameUser").value = e.toUpperCase();
	}

	function limpiaCamposIdUser(){
		if(getVal("idUser").value!=""){
			getVal("registerUser").value = "";
			getVal("nameUser").value = "";
			getVal("firstNameUser").value = "";
			getVal("secondNameUser").value = "";
		}
	}
	function limpiaCamposRegistro(){
		if(getVal("registerUser").value!=""){
			getVal("idUser").value = "";
			getVal("nameUser").value = "";
			getVal("firstNameUser").value = "";
			getVal("secondNameUser").value = "";
		}
	}
	
	function limpiaCamposNombre(){
		if((getVal("nameUser").value!="") || (getVal("firstNameUser").value != "") || (getVal("secondNameUser").value != "")){
			getVal("idUser").value = "";
			getVal("registerUser").value = "";
		}
	}	
	
	function soloEspacios(cadena) {
		var vLongitud = cadena.length;
		var contEspacios = 0;
		
		for(var indice=0; indice<vLongitud; indice++) {
			var caracter = cadena.charAt(indice);
			if(caracter == ' ') {
				contEspacios++;
			}
		}
		
		if(vLongitud == contEspacios)
			return true;
		else
			return false;
	}

	function asignaValores(idUser, nombreUser){	
		getVal("selectedIdUser").value = idUser;
		getVal("selectedNombre").value = nombreUser;		
	}

	function replace(value){
		return value.replace(/\s/g, "_");
	}

	$(document).ready(function(){
		$('#aceptar').click(function(){
			var empleado = "<%=empleado%>"; 
			if(empleado != getVal("selectedIdUser").value){
				frmBusqueda.submit("/supervisorseleccionado");
			}else{
				$("#dialogo1").dialog({
					width: 390,
					height: 250,
					show: "scale",
					hide: "scale",
					resizable: "false",
					position: "center",
					modal: "true"
				});
			}
		});

		$('#cancelar').click(function(){
			getVal("hiddenCancelar").value = "true";
			frmBusqueda.submit("/supervisorseleccionado");						
		});
		
		$('#buscar').click(function(){
			getVal("hiddenIdUser").value = getVal("idUser").value.trim();
			getVal("hiddenRegisterUser").value = getVal("registerUser").value.trim();
			getVal("hiddenNameUser").value = getVal("nameUser").value.trim();
			getVal("hiddenFirstNameUser").value = getVal("firstNameUser").value.trim();
			//alert(getVal("hiddenFirstNameUser").value);
			getVal("hiddenSecondNameUser").value = getVal("secondNameUser").value.trim();
			frmBusqueda.action="/buscasupervisores";
			frmBusqueda.submit();
		});

		$('#aceptar1').click(function(){
			$("#dialogo1").dialog("close");						
		});
	});
</script>
</head>
<body onload="inicial();">
	<form name="frmBusqueda" action="/supervisorseleccionado" method="post"
		target="_self">
		<input type="hidden" id="hiddenIdUser" name="hiddenIdUser" value="">
		<input type="hidden" id="hiddenRegisterUser" name="hiddenRegisterUser" value=""> 
		<input type="hidden" id="hiddenNameUser" name="hiddenNameUser" value=""> 
		<input type="hidden" id="hiddenFirstNameUser" name="hiddenFirstNameUser" value="">
		<input type="hidden" id="hiddenSecondNameUser" name="hiddenSecondNameUser" value=""> 
		
		<input type="hidden" id="selectedIdUser" name="selectedIdUser" value=""> 
		<input type="hidden" id="selectedNombre" name="selectedNombre" value="">
		
		<input type="hidden" name="hiddenSelectedRadio" id="hiddenSelectedRadio" value="<%=request.getAttribute("hiddenSelectedRadio")%>"> 
		<input type="hidden" name="hiddenIsChecked" id="hiddenIsChecked" value="<%=request.getAttribute("hiddenIsChecked")%>"> 
		<input type="hidden" name="hiddenCancelar" id="hiddenCancelar" value="false">
		<input type="hidden" name="hiddenFrom" id="hiddenFrom" value="<%=request.getAttribute("hiddenFrom")%>">
		<input type="hidden" id="opcion" name="opcion" value="buscar" />
		
		<input type="hidden" id="opcion" name="opcion" value="buscar" />
		<table width="100%" cellspacing="2" cellpadding="2" border="0">
			<caption class="tblSeguimientoT"
				style="border-bottom: 1px solid rgb(153, 186, 215);">
				B&uacute;squeda de usuario autorizador</caption>
			<tr>
				<td width="22%" class="tblSeguimientoD1">ID de Usuario (M999999)
				</td>
				<td width="28%" class="tblSeguimientoI1"><input type="text"
					class="cajaTexto" size="27"
					onKeyUp="limpiaCamposIdUser();enableSearchButton();" id="idUser"
					name="idUser"
					value="<%=request.getAttribute("hiddenIdUser")!=null?request.getAttribute("hiddenIdUser"):""%>" />
				</td>
				<td width="22%" class="tblSeguimientoD1">Registro</td>
				<td width="28%" class="tblSeguimientoI1"><input type="text"
					class="cajaTexto" size="27"
					onKeyUp="limpiaCamposRegistro();enableSearchButton();"
					id="registerUser"
					value="<%=request.getAttribute("hiddenRegisterUser")!=null?request.getAttribute("hiddenRegisterUser"):""%>" />
				</td>
			</tr>
			<tr>
				<td class="tblSeguimientoD1">Nombre</td>
				<td class="tblSeguimientoI1"><input type="text"
					class="cajaTexto" size="27"
					onKeyUp="limpiaCamposNombre();enableSearchButton();" id="nameUser"
					value="<%=request.getAttribute("hiddenNameUser")!=null?request.getAttribute("hiddenNameUser"):""%>" />
				</td>
				<td class="tblSeguimientoD1">Apellido Paterno</td>
				<td class="tblSeguimientoI1"><input type="text"
					class="cajaTexto" size="27"
					onKeyUp="limpiaCamposNombre();enableSearchButton();"
					id="firstNameUser"
					value="<%=request.getAttribute("hiddenFirstNameUser")!=null?request.getAttribute("hiddenFirstNameUser"):""%>" />
				</td>
			</tr>
			<tr>
				<td class="tblSeguimientoD1">Apellido Materno</td>
				<td class="tblSeguimientoI1"><input type="text"
					class="cajaTexto" size="27"
					onKeyUp="limpiaCamposNombre();enableSearchButton();"
					id="secondNameUser"
					value="<%=request.getAttribute("hiddenSecondNameUser")!=null?request.getAttribute("hiddenSecondNameUser"):""%>" />
				</td>
				<td class="tblSeguimientoI1"><input type="button"
					class="botonCandidatoDsb" disabled value="Buscar" id="buscar" /></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="4">
					<!--div id="divPopup" class="ventana"-->
					<table width="100%" cellspacing="2" cellpadding="2"
						class="tblCandidato" border="0">
						<thead>
							<tr class="CabeceraTabla">
								<th width="2%">&nbsp;</th>
								<th width="24%">ID de Usuario (Mxxx)</th>
								<th width="24%">Registro</th>
								<th width="25%">Nombre</th>
								<th width="25%">Apellidos</th>
							</tr>
						</thead>
						<tbody>
<%	
	Iterable<BeanEmpleado> ieAutorizadores = (Iterable)request.getAttribute("listaAutorizadores");
	int iReng1 = 0;
	int flag = 0;
	if(ieAutorizadores!=null){
		for(BeanEmpleado e:ieAutorizadores){
			flag++;
			break;
		}
	}
	
	if(ieAutorizadores!=null && flag>0){
		for(BeanEmpleado eAutorizador:ieAutorizadores) {
			String sClaseTr = iReng1++%2 == 0 ? "tblSeguimientoI1" : "tblSeguimientoI2";
			String sIdUsuario = eAutorizador.getEmpleadoID();
			String sRegUsuario = eAutorizador.getRegistro();
			String sNombre = eAutorizador.getNombre();
			String sApellidos =  eAutorizador.getApePaterno()+" "+eAutorizador.getApeMaterno();
			String sNomAutorizador = sApellidos+", "+sNombre;%>
							<tr id="tr1">
								<td class="<%=sClaseTr%>"><input type="radio"
									onClick="javascript:enabledAcept(this.id);"
									name="rbAutorizador" id="autorizador<%=iReng1%>"
									value="<%=sIdUsuario%>|<%=sNomAutorizador%>"></td>
								<td class="<%=sClaseTr%>"><%=sIdUsuario%></td>
								<td class="<%=sClaseTr%>"><%=sRegUsuario%></td>
								<td class="<%=sClaseTr%>"><%=sNombre%></td>
								<td class="<%=sClaseTr%>"><%=sApellidos%></td>
							</tr>
							<%	
		}// fin del for 		
	}else{	
%>
							<tr id="tr1">
								<td colspan="5" class="tblSeguimientoI1">No se encontro
									informaci&oacute;n</td>
							</tr>
							<%	}%>
						</tbody>
					</table>
					<table width="100%" cellspacing="5" cellpadding="2" border="0">
						<tr>
							<td style="text-align: right;"><input type="button"
								value="Cancelar" class="botonCandidato" id="cancelar" />&nbsp;&nbsp;&nbsp;
								<input type="button" value="Aceptar" class=botonCandidatoDsb
								id="aceptar" disabled /></td>
						</tr>
					</table> <!--  /div-->
				</td>
			</tr>
			<tr>
			</tr>
		</table>
		<div id="dialogo1" class="ventana">
			ESTIMADO USUARIO, DEBE SELECCIONAR OTRO SUPERVISOR.
			<br> <br> <br>
			<center>
				<input type="button" value="Aceptar" class="botonCandidato" id="aceptar1" />
			</center>
		</div>
	</form>
</body>
</html>