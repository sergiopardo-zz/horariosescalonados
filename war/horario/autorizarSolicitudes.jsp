<%@page import="com.gonet.horariosescalonados.bean.BeanSolicitud"%>
<%@page import="com.gonet.horariosescalonados.bean.BeanMensaje"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="java.lang.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Solicitudes por autorizar</title>
<link rel="stylesheet" type="text/css" href="../config/css/estilosHE.css">
<link rel="stylesheet" type="text/css" href="../config/css/jquery-ui.css" />
<script	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
<script type="text/javascript" language="javascript">
	var arrSolicitudes = new Array(); // Arreglo para almacenar todas las solicitudes de ieSolicitudes
<%
	// -------- Gonet DJZ :  Se llena a arrSolicitudes con los registros de "ListaSolicitudes" ----------------
	BeanMensaje msg4 =  (BeanMensaje)(request.getAttribute("message4")!=null?request.getAttribute("message4"):null);
	Iterable<BeanSolicitud> ieSolicitudes = (Iterable)request.getAttribute("ListaSolicitudes");
	int iNumRegistros = 0; // Contador para el número de solicitudes
	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	if(ieSolicitudes!=null){
		for(BeanSolicitud enSolicitud: ieSolicitudes) {
			String sDatosSolicitud = "";			
			String sClaseTd = iNumRegistros%2 == 0 ? "tblSeguimientoI1" : "tblSeguimientoI2";
			String sIdSol = enSolicitud.getSolicitudID()==null ? "-": enSolicitud.getSolicitudID();
			String sFecha = enSolicitud.getFechaAlta()==null ? "-" : df.format(enSolicitud.getFechaAlta());
			String sIdEmpl = enSolicitud.getEmpleadoID()==null ? "-" : (String)enSolicitud.getEmpleadoID();
			String sNombre = enSolicitud.getNombre()==null ? "-" : (String)enSolicitud.getNombre();
			String sEntradaAct = enSolicitud.getEntradaAct()==null ? "-" : (String)enSolicitud.getEntradaAct();
			String sSalidaAct = enSolicitud.getSalidaAct()==null ? "-" : (String)enSolicitud.getSalidaAct();
			String sEntradaSol = enSolicitud.getEntradaSol()==null ? "-" : (String)enSolicitud.getEntradaSol();
			String sSalidaSol = enSolicitud.getSalidaSol()==null ? "-" : (String)enSolicitud.getSalidaSol();
			String sTipoSol = enSolicitud.getTipo()==null ? "-" : (String)enSolicitud.getTipo();
			String sMotivo = enSolicitud.getMotivo().equals("") ? "-" : (String)enSolicitud.getMotivo();
			String sHorarioSol = enSolicitud.getHoraSel()==null ? "-" : (String)enSolicitud.getHoraSel();
			/* Se arma la información para llenar el arreglo de las solicitudes, separada por "|". La respuesta y Motivo del rechazo se inicializan con "P" y "-" respectivamente
									0			1			2		3			4				5				6				7			8				9			10			11		12 13 */
			sDatosSolicitud = sClaseTd+"|"+sIdSol+"|"+sFecha+"|"+sIdEmpl+"|"+sNombre+"|"+sEntradaAct+"|"+sSalidaAct+"|"+sEntradaSol+"|"+sSalidaSol+"|"+sTipoSol+"|"+sMotivo+"|"+sHorarioSol+"|P|-";
%>	
			arrSolicitudes[<%=iNumRegistros++%>] = "<%=sDatosSolicitud%>";
<%		}// fin del for
	}//fin del if
		
	
	int iNumPaginas = iNumRegistros/10; // Variable con el total de páginas
	
	if(iNumRegistros%10 > 0)
		iNumPaginas++; // Si el total de lotes no es multiplo de 10, se agrega otro indice de página por los sobrantes
%>  
	// ------------------------------------------------------------------------------------------
	// -----------------------  Gonet DJZ : Funciones para la paginación  -----------------------
	// ------------------------------------------------------------------------------------------
	var vPaginaActual = 1;
	var vTotalPaginas = <%=iNumPaginas%>;
	var vTotalSolicitudes = <%=iNumRegistros%>;
	
	// Función para llenar la tabla con las solicitudes correspondientres a la página
	function irApagina(paginaSig) {
		var vSolicitudFinal = paginaSig*10; // Ultimo lote de la página (p.e. 4*10=40)
		var vSolicitudInicial = vSolicitudFinal-10; // Primer lote de la pagina (p.e. 40-10=30)
		
		// Si vSolicitudFinal resultó mayor que vTotalSolicitudes, se toma el valor de vTotalSolicitudes
		if(vSolicitudFinal > vTotalSolicitudes) {
			vSolicitudFinal = vTotalSolicitudes;
		}
		
		limpiarTabla("tablaSolicitudes");
		var tablaSolicitudes = document.getElementById("tablaSolicitudes");
		
		// Se crea el encabezado de la tabla
		var tEncabezado = tablaSolicitudes.createTHead();
			var renglonEnc = tEncabezado.insertRow(0); // Se crea el primer renglon del encabezado
				var celdaTh1 = document.createElement("TH"); // Primera celda
					celdaTh1.colSpan = 2;
					renglonEnc.appendChild(celdaTh1);
				var celHorarioAct = document.createElement("TH"); // Segunda celda
					celHorarioAct.colSpan = 2;
					celHorarioAct.className = "CabeceraTabla";
					celHorarioAct.innerHTML = "Horario Actual";
					renglonEnc.appendChild(celHorarioAct);
				var celHorarioSol = document.createElement("TH"); // Tercer celda
					celHorarioSol.colSpan = 2;
					celHorarioSol.className = "CabeceraTabla";
					celHorarioSol.innerHTML = "Horario Solicitado";
					renglonEnc.appendChild(celHorarioSol);
				var celdaTh2 = document.createElement("TH"); // Cuarta celda
					celdaTh2.colSpan = 3;
					renglonEnc.appendChild(celdaTh2);
					
			var renglonEnc2 = tEncabezado.insertRow(1); // Se crea el segundo renglón del encabezado
				renglonEnc2.className = "CabeceraTabla";
				var celFechaSol = document.createElement("TH"); // Primera celda
					celFechaSol.width = "10%";
					celFechaSol.innerHTML = "Fecha de Solicitud";
					renglonEnc2.appendChild(celFechaSol);
				var celNombre = document.createElement("TH"); // Segunda celda
					celNombre.width = "20%";
					celNombre.innerHTML = "Nombre";
					renglonEnc2.appendChild(celNombre);
				var celEntAct = document.createElement("TH"); // Tercer celda
					celEntAct.width = "6%";
					celEntAct.innerHTML = "Entrada";
					renglonEnc2.appendChild(celEntAct);
				var celSalAct = document.createElement("TH"); // Cuarta celda
					celSalAct.width = "6%";
					celSalAct.innerHTML = "Salida";
					renglonEnc2.appendChild(celSalAct);
				var celEntSol = document.createElement("TH"); // Quinta celda
					celEntSol.width = "6%";
					celEntSol.innerHTML = "Entrada";
					renglonEnc2.appendChild(celEntSol);
				var celSalSol = document.createElement("TH"); // Sexta celda
					celSalSol.width = "6%";
					celSalSol.innerHTML = "Salida";
					renglonEnc2.appendChild(celSalSol);
				var celdTipo = document.createElement("TH"); // Septima celda
					celdTipo.width = "12%";
					celdTipo.innerHTML = "Tipo";
					renglonEnc2.appendChild(celdTipo);
				var celDesicion = document.createElement("TH"); // Octava celda
					celDesicion.width = "12%";
					celDesicion.innerHTML = "Desici&oacute;n";
					renglonEnc2.appendChild(celDesicion);
				var celMotRech = document.createElement("TH"); // Novena celda
					celMotRech.width = "22%";
					celMotRech.innerHTML = "Motivo rechazo";
					renglonEnc2.appendChild(celMotRech);
		// Fin de los encabezados
		// --------------  Se llena la tabla con las solicitudes del arreglo  ------------------
		for(var solicitudAct=vSolicitudInicial; solicitudAct<vSolicitudFinal; solicitudAct++) {
			var datosSolicitud = arrSolicitudes[solicitudAct]; // Se obtienen los datos de la solicitud en el arreglo
			var camposSolicitud = datosSolicitud.split("|"); // Se crea un arreglo con los datos del registro de solicitud.
			var numRenglon = tablaSolicitudes.rows.length; // De 0 a n.
			var renglonSolicitud = tablaSolicitudes.insertRow(numRenglon); // Se crea el renglón de la tabla
			var solicitudProcesar = solicitudAct;
			
			var celdaFecha = renglonSolicitud.insertCell(0); // Creamos la primera celda para la Fecha de Solicitud
			celdaFecha.width = "10%";
			celdaFecha.className = camposSolicitud[0]; // Se establece el estilo de la celda
			celdaFecha.innerHTML = camposSolicitud[2]; // Se pinta la fecha
			
			var celdaNombre = renglonSolicitud.insertCell(1); // Creamos la segunda celda para el Nombre del Solicitante
			celdaNombre.width = "20%";
			celdaNombre.className = camposSolicitud[0]; // Se establece el estilo de la celda
			celdaNombre.innerHTML = camposSolicitud[4]; // Se pinta el nombre
			
			var celdaEntradaActual = renglonSolicitud.insertCell(2); // Creamos la tercera celda para la Hora de Entrada Actual
			celdaEntradaActual.width = "6%";
			celdaEntradaActual.className = camposSolicitud[0]; // Se establece el estilo de la celda
			celdaEntradaActual.align = "center";
			celdaEntradaActual.innerHTML = camposSolicitud[5]; // Se pinta la Hora Entrada Actual
			
			var celdaSalidaActual = renglonSolicitud.insertCell(3); // Creamos la cuarta celda para la Hora de Salida Actual
			celdaSalidaActual.width = "6%";
			celdaSalidaActual.className = camposSolicitud[0]; // Se establece el estilo de la celda
			celdaSalidaActual.align = "center";
			celdaSalidaActual.innerHTML = camposSolicitud[6]; // Se pinta la Hora Salida Actual
			
			var celdaEntradaSolicitada = renglonSolicitud.insertCell(4); // Creamos la quinta celda para la Hora de Entrada Solicitada
			celdaEntradaSolicitada.width = "6%";
			celdaEntradaSolicitada.className = camposSolicitud[0]; // Se establece el estilo de la celda
			celdaEntradaSolicitada.align = "center";
			celdaEntradaSolicitada.innerHTML = camposSolicitud[7]; // Se pinta la Hora Entrada Solicitada
			
			var celdaSalidaSolicitada = renglonSolicitud.insertCell(5); // Creamos la sexta celda para la Hora de Salida Solicitada
			celdaSalidaSolicitada.width = "6%";
			celdaSalidaSolicitada.className = camposSolicitud[0]; // Se establece el estilo de la celda
			celdaSalidaSolicitada.align = "center";
			celdaSalidaSolicitada.innerHTML = camposSolicitud[8]; // Se pinta la Hora Salida Solicitada
			
			var celdaTipo = renglonSolicitud.insertCell(6); // Creamos la septima celda para el Tipo de Solicitud
			celdaTipo.width = "12%";
			celdaTipo.className = camposSolicitud[0]; // Se establece el estilo de la celda
			celdaTipo.innerHTML = camposSolicitud[9]; // Se pinta el Tipo de Solicitud
			
			if(camposSolicitud[9]=="MODIFICACION" || camposSolicitud[9]=="BAJA") {
				celdaTipo.title = camposSolicitud[10]; // Si es Modificación o Baja, se despliega el motivo.
			}
			
			var celdaDesicion = renglonSolicitud.insertCell(7); // Creamos la octava celda para la respuesta a la Solicitud
			celdaDesicion.width = "12%";
			celdaDesicion.className = camposSolicitud[0]; // Se establece el estilo de la celda
			var ComboRespuesta = document.createElement("SELECT"); // Se crea el combo box
			ComboRespuesta.name = "cb"+solicitudAct;
			ComboRespuesta.id = "resp"+solicitudAct;
			ComboRespuesta.className = "combo";
			ComboRespuesta.options[0] = new Option("--Selecciona--", "P"); // texto,valor
			ComboRespuesta.options[1] = new Option("Autorizar", "A"); // texto,valor
			ComboRespuesta.options[2] = new Option("Rechazar", "R"); // texto,valor
			// Se valida la respuesta
			if(camposSolicitud[12] == "A") {
				ComboRespuesta.selectedIndex = 1;
			} else if(camposSolicitud[12] == "R") {
				ComboRespuesta.selectedIndex = 2;
			}
			ComboRespuesta.onchange=function() {
				var vIdCombo = this.id;
				var vIdTextRechazo = "mot_"+vIdCombo;
				//alert("vIdCombo = "+vIdCombo+"\nvIdTextRechazo = "+vIdTextRechazo);
				motivo(vIdCombo,vIdTextRechazo);
			};
			celdaDesicion.appendChild(ComboRespuesta); // Se agrega el combo box a la celda
			
			var celdaMotivo = renglonSolicitud.insertCell(8); // Creamos la novena celda para el motivo del rechazo
			celdaMotivo.width = "22%";
			celdaMotivo.className = camposSolicitud[0]; // Se establece el estilo de la celda
			var campoMotRech = document.createElement("INPUT"); // Creamos el Input text para la novena celda y que se usa en la función del combo
			campoMotRech.setAttribute("type", "text");
			campoMotRech.name = "motivo_rech"+solicitudAct;
			campoMotRech.className = "cajaTexto";
			campoMotRech.size = 30;
			campoMotRech.maxLength = 250;
			campoMotRech.id = "mot_resp"+solicitudAct;
			campoMotRech.onkeyup = function() {
				this.value = this.value.toUpperCase();
			};
			campoMotRech.onblur = function() {
				cargarMotivoRechazo(this.id);
			};
			campoMotRech.disabled = camposSolicitud[12] == "R" ? false : true;
			var valorMotivoRechazo = camposSolicitud[13]=="-" ? "" : camposSolicitud[13];
			campoMotRech.value = valorMotivoRechazo;
			celdaMotivo.appendChild(campoMotRech); // Se agrega el campo Text a la celda
		} // fin del for
		
		if(vTotalPaginas > 1) // Si hay más de una página, entonces se realiza la paginación
			cargarIndices(paginaSig);
	}
	
	function cargarIndices(pagina) {
		// Se cargan valores en las variables globales
		vPaginaActual = pagina; 
		vPaginaSiguiente = vPaginaActual+1;
		document.getElementById("priPag").innerHTML = "";
		document.getElementById("pagAnt").innerHTML = "";
		document.getElementById("desplPag").innerHTML = "";
		document.getElementById("pagSig").innerHTML = "";
		document.getElementById("UltPag").innerHTML = "";
		
		if(vTotalPaginas > 2) {
			// Si aplica, se ponen los botones Primera Página y Ultima Página
			if(vPaginaActual > 1) {
				document.getElementById("priPag").innerHTML = "<img src='../config/images/Izq1.gif' onclick='irApagina(1)' title='Primera página'>";
			}
			if(vPaginaActual < vTotalPaginas) {
				document.getElementById("UltPag").innerHTML = "<img src='../config/images/Dere1.gif' onclick='irApagina("+vTotalPaginas+")' title='Ultima página'>";
			}
		}
		
		if(vPaginaActual > 1) {
			var vPagAnt = vPaginaActual-1;
			document.getElementById("pagAnt").innerHTML = "<img src='../config/images/Izq2.gif' onclick='irApagina("+vPagAnt+")' title='Página anterior'>";
		}
		
		if(vPaginaActual < vTotalPaginas) {
			var vPagSig = vPaginaActual+1;
			document.getElementById("pagSig").innerHTML = "<img src='../config/images/Dere2.gif' onclick='irApagina("+vPagSig+")' title='Página siguiente'>";
		}
		
		document.getElementById("desplPag").innerHTML = "Página "+vPaginaActual+" de "+vTotalPaginas+" ("+vTotalSolicitudes+" solicitudes)";
	}
	
	function limpiarTabla(tableID) {
		try {
			var table = document.getElementById(tableID);
			var renglones = table.rows.length;
			
			for(var i=renglones-1; i>-1; i--) {
				table.deleteRow(i);
			} // fin dl for
		} catch(e) {
			alert("Error en limpiarTabla("+tableID+") : "+e);
		}
	}
	
	function inicio() {
		if(vTotalSolicitudes > 0) {
			irApagina(vPaginaActual);
		} else {
			var botonAutorizar = document.getElementById("autorizar");
			botonAutorizar.disabled = true;
			botonAutorizar.setAttribute("class", "botonCandidatoDsb");
		}
	}
	// -------------------------------- Fin Gonet DJZ  ------------------------------------------
	// ------------------------------------------------------------------------------------------
	
	function motivo(idCombo, idCampo) {
		var objComboResp = document.getElementById(idCombo);
		var vRespuesta = objComboResp.value;
		var objTextResp = document.getElementById(idCampo);
		objTextResp.disabled = "true";
		objTextResp.value = "";
		
		var vNombreCombo = objComboResp.name; // Obtenemos el nombre del combo box
		//alert("vNombreCombo = " +vNombreCombo);
		var indiceSolicitud = parseInt(vNombreCombo.substring(2)); // Del nombre obtenemos el indice desde la 2da posición (cbXXX)
		//alert("indiceSolicitud = "+indiceSolicitud);
		// Se actualiza la respuesta (campo 11) en la solicitud dentro del arreglo
		actualizarCampo(indiceSolicitud,vRespuesta,12);
		//alert("objComboResp.value = "+objComboResp.value);
		if(vRespuesta == "R") {
			objTextResp.disabled = "";
			objTextResp.focus();
		} else {
			actualizarCampo(indiceSolicitud,"",13);
		}
	}
	
	function cargarMotivoRechazo(idMotivo) {
		var objMotRech = document.getElementById(idMotivo);
		var valorMotivoRech = objMotRech.value=="" ? "-" : objMotRech.value; // Obtenemos el valor del combo box
		//alert("valorMotivoRech = "+valorMotivoRech);
		var vNombreCampo = objMotRech.name; // Obtenemos el nombre del campo
		//alert("vNombreCampo = "+vNombreCampo);
		var indiceSolicitud = parseInt(vNombreCampo.substring(11)); // Del nombre obtenemos el indice desde la 11va posición de la cadena (mot_respXXX)
		// Se actualiza el motivo del rechazo (campo 12) en la solicitud dentro del arreglo
		actualizarCampo(indiceSolicitud,valorMotivoRech,13);
	}
	
	function actualizarCampo(indice, valor, indCampo) {
		//alert("ORIGINAL : arrSolicitudes["+indice+"] = "+arrSolicitudes[indice]);
		var vDatosSolicitud = arrSolicitudes[indice]; // Se obtiene en el arreglo la cadena con los datos de la solicitud
		//alert("Actual : vDatosSolicitud = "+vDatosSolicitud);
		var arrCamposSolicitud = vDatosSolicitud.split("|"); // Se hace un arreglo de lod datod (campos)
		//alert("Anterior : arrCamposSolicitud["+indCampo+"] = "+arrCamposSolicitud[indCampo]);
		arrCamposSolicitud[indCampo] = valor;
		//alert("Nuevo : arrCamposSolicitud["+indCampo+"] = "+arrCamposSolicitud[indCampo]);
		// Se arma la nueva cadena con el dato actualizado
		var cadenaNueva = "";
		for(var iCampo=0; iCampo<14; iCampo++) {
			var separador = iCampo<13 ? "|" : "";
			cadenaNueva += arrCamposSolicitud[iCampo] + separador;
		}
		
		arrSolicitudes[indice] = cadenaNueva; // Se actualiza el arreglo con el nuevo dato
		//alert("FINAL : arrSolicitudes["+indice+"] = "+arrSolicitudes[indice]);
	}
	
	function Autorizar() {
		var solicitudesProcesadas = "";
		
		// Se recorre todo el arreglo de solicitudes para  obtener las que fueron rechazadas o aceptadas
		for(i=0; i<vTotalSolicitudes; i++ ) {
			var solicitudActual = arrSolicitudes[i];
			var arrCamposSolicitud = solicitudActual.split("|");
			var respuestaSolicitud = arrCamposSolicitud[12];
			
			if(arrCamposSolicitud[12] != "P") { // Si la respuesta es diferente de "Pendiente"
				var motivoRechazo = arrCamposSolicitud[13].trim()=="-"||arrCamposSolicitud[13].trim()=="" ? "NA" : arrCamposSolicitud[13];
				var sDesc = arrCamposSolicitud[12];
				// Se arma la cadena  :     IdSolicitud         -     IdEmpleado          -   Horario solicitado     -        Respuesta         -  MotivoRechazo  |
				solicitudesProcesadas += arrCamposSolicitud[1]+"_"+arrCamposSolicitud[3]+"_"+arrCamposSolicitud[11]+"_"+sDesc+"_"+motivoRechazo+"|";
				if(motivoRechazo=="NA" && sDesc=="R"){
					$(document).ready(function(){
						$("#dialogo4").dialog({
							width: 390,
							height: 250,
							show: "scale",
							hide: "scale",
							resizable: "false",
							position: "center",
							modal: "true"
						});
					});
					return true;
				}				
			}
		}
		
		if(solicitudesProcesadas == "") {
			$(document).ready(function(){
				$("#dialogo3").dialog({
					width: 390,
					height: 250,
					show: "scale",
					hide: "scale",
					resizable: "false",
					position: "center",
					modal: "true"
				});
			});
			return false;
		} else{
			fHeAutorizar.cadena_autorizaciones.value = solicitudesProcesadas;
			
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
			return true;
		}
	}
	
	$(document).ready(function() {
		$("#aceptar2").click(function() {
			$("#dialogo2").dialog("close");
			fHeAutorizar.submit();
		});
		
		$("#aceptar3").click(function() {
			$("#dialogo3").dialog("close");
		});
		
		$("#aceptar4").click(function() {
			$("#dialogo4").dialog("close");
		});
	});
	
	$(document).on("ready", function() {
		$("#area_tabla0 table tr td").click(function() {
			//var celda = $(this);
			var celda = $("tr td")[2].innerHTML;
			alert(celda);
		});
	});
</script>
</head>
<body onLoad="inicio();">
	<form name="fHeAutorizar" action="/autorizarsolicitudes" method="post"
		target="_self">
		<input type="hidden" name="cadena_autorizaciones" value="">
		<table width="100%" cellspacing="0" cellpadding="0" border="0">
			<tr>
				<td width="55%;">
					<table class="tblSeguimientoT" width="83%" cellpadding="1"
						cellspacing="1">
						<tr>
							<td>Solicitudes pendientes de autorizar</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>
					<table width="100%" cellspacing="2" cellpadding="2" border="0"
						class="tblCandidato" id="tablaSolicitudes">
						<thead>
							<tr>
								<th colspan="2" class=""></th>
								<th class="CabeceraTabla" colspan="2">Horario Actual</th>
								<th class="CabeceraTabla" colspan="2">Horario Solicitado</th>
								<th colspan="3" class=""></th>
							</tr>
							<tr class="CabeceraTabla">
								<th width="10%">Fecha de Solicitud</th>
								<th width="20%">Nombre</th>
								<th width="6%">Entrada</th>
								<th width="6%">Salida</th>
								<th width="6%">Entrada</th>
								<th width="6%">Salida</th>
								<th width="12%">Tipo</th>
								<th width="12%">Decisi&oacute;n</th>
								<th width="22%">Motivo Rechazo</th>
							</tr>
						</thead>
						<tr>
							<td align="center" colspan="9">
								<p class="error">Sin solicitudes por aprobar</p>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table width="30%" border="0" align="center">
						<tr id="indicesPagina">
							<td align="center" id="priPag"></td>
							<td align="center" id="pagAnt"></td>
							<td align="center" id="desplPag" class="confirmacion"></td>
							<td align="center" id="pagSig"></td>
							<td align="center" id="UltPag"></td>
						</tr>
					</table>
					<table width="50%" cellspacing="0" cellpadding="0" border="0"
						align="right">
						<tr>
							<td align="center">
								<input type="button" id="autorizar" value="Aceptar" class="botonCandidato" onClick="javascrip:Autorizar();">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<div id="dialogo2" class="ventana">
			<%=msg4.getMensajeClave()%> - <%=msg4.getTexto()%>
			<br> <br> <br>
			<center>
				<input type="button" value="Aceptar" class="botonCandidato" id="aceptar2" />
			</center>
		</div>
		<div id="dialogo3" class="ventana">
			ESTIMADO USUARIO, DEBE DE SELECCIONAR UNA ACCION.
			<br> <br> <br>
			<center>
				<input type="button" value="Aceptar" class="botonCandidato" id="aceptar3" />
			</center>
		</div>
		<div id="dialogo4" class="ventana">
			ESTIMADO USUARIO, DEBE INGRESAR UN "MOTIVO DE RECHAZO" EN TODAS LAS SOLICITUDES RECHAZADAS.
			<br> <br> <br>
			<center>
				<input type="button" value="Aceptar" class="botonCandidato" id="aceptar4" />
			</center>
		</div>
	</form>
</body>
</html>