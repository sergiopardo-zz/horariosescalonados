<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@page import="com.google.appengine.api.datastore.Entity"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Carga Manual</title>
<link rel="stylesheet" type="text/css"
	href="../config/css/estilosHE.css" />
<link rel="stylesheet" type="text/css"
	href="../config/css/jquery-ui.css" />
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script
	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
<script type="text/javascript">
    function getId(id){
		return document.getElementById(id);
    }

    function Limpia() {	
    	document.getElementById("idUser").value = "";
    	document.getElementById('nombre').value = "";
    	document.getElementById('paterno').value = "";
    	document.getElementById('materno').value = "";
    	document.getElementById('tipoEmpleado').value = "";
    	document.getElementById('ubicacion').value = "";
    	document.getElementById('correo').value = "";
    	document.getElementById('idJefe').value = "";
    	document.getElementById('registro').value = "";
    	document.getElementById("nomCR").value = "";
		document.getElementById("dga").value = "";
		document.getElementById("aceptar").disabled = true;
		document.getElementById("aceptar").setAttribute("class", "botonCandidatoDsb");
    }
    
	function enableAceptarButton() {
				
		var a = document.getElementById("idUser").value;
		var b = document.getElementById("nombre").value;
		var c = document.getElementById("paterno").value;
		var d = document.getElementById("materno").value;
		var e = document.getElementById("tipoEmpleado").value;
		var f = document.getElementById("ubicacion").value;
		var g = document.getElementById("idJefe").value;
		var h = document.getElementById("correo").value;
		var i = document.getElementById("registro").value;
		var j = document.getElementById("nomCR").value;
		var k = document.getElementById("dga").value;
						
		// Se valida que los campos NO sean sólo espacios
		a = soloEspacios(a) ? "" : a;
		b = soloEspacios(b) ? "" : b;
		c = soloEspacios(c) ? "" : c;
		d = soloEspacios(d) ? "" : d;
		e = soloEspacios(e) ? "" : e;
		f = soloEspacios(f) ? "" : f;
		g = soloEspacios(g) ? "" : g;
		h = soloEspacios(h) ? "" : h;
		i = soloEspacios(i) ? "" : i;
		j = soloEspacios(j) ? "" : j;
		k = soloEspacios(k) ? "" : k;
		
		//Valida que todos los campos tengan valor		
		if(a!=""&&b!=""&&c!=""&&d!=""&&e!=""&&f!=""&&g!=""&&h!=""&&i!=""&&j!=""&&k!="") {
			document.getElementById("aceptar").disabled = false;
			document.getElementById("aceptar").setAttribute("class", "botonCandidato");
		} else {
			document.getElementById("aceptar").disabled = true;
			document.getElementById("aceptar").setAttribute("class", "botonCandidatoDsb");
		}
		// Se convierten los valores de los campos a mayusculas
		document.getElementById("idUser").value = a.toUpperCase();
		document.getElementById("nombre").value = b.toUpperCase();
		document.getElementById("paterno").value = c.toUpperCase();
		document.getElementById("materno").value = d.toUpperCase();
		document.getElementById("tipoEmpleado").value = e.toUpperCase();
		document.getElementById("ubicacion").value = f.toUpperCase();
		document.getElementById("idJefe").value = g.toUpperCase();
		document.getElementById("registro").value = i.toUpperCase();
		document.getElementById("nomCR").value = j.toUpperCase();
		document.getElementById("dga").value = k.toUpperCase();
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

    function aceptarButton() {    	
		getId("hiddenIdUser").value = getId("idUser").value;
		getId("hiddenNombre").value = getId("nombre").value;
		getId("hiddenPaterno").value = getId("paterno").value;
		getId("hiddenMaterno").value = getId("materno").value;
		getId("hiddenTipoEmpleado").value = getId("tipoEmpleado").value;
		getId("hiddenUbicacion").value = getId("ubicacion").value;
		getId("hiddenCorreo").value = getId("correo").value;
		getId("hiddenIdJefe").value = getId("idJefe").value;		
		getId("hiddenRegistro").value = getId("registro").value;
		getId("hiddenNombreCR").value = getId("nomCR").value;
		getId("hiddenDGA").value = getId("dga").value;
		getId("hiddenOpcionReporte").value = "Aceptar";		
		myform.submit();
   	}
    
    function evitaEspeciales(inputtext){
    	inputtext.value=inputtext.value.replace(/[`~¨°ñÑ´!¡@#$%^&*()_|+\-=¿?;:''"",.<>\{\}\[\]\\\/]/gi,'');
    	}

    </script>
</head>
<body class="BodyPrincipal" style="background: #FFFFFF"
	onload="enableAceptarButton();">
	<form id="myform" action="/cargamanual" method="post">
		<input type="hidden" name="hiddenOpcionReporte" id="hiddenOpcionReporte"/>
		<input type="hidden" name="hiddenIdUser" id="hiddenIdUser"/>
		<input type="hidden" name="hiddenNombre" id="hiddenNombre"/>
		<input type="hidden" name="hiddenPaterno" id="hiddenPaterno"/>
		<input type="hidden" name="hiddenMaterno" id="hiddenMaterno"/>
		<input type="hidden" name="hiddenTipoEmpleado" id="hiddenTipoEmpleado"/>
		<input type="hidden" name="hiddenUbicacion" id="hiddenUbicacion" />
		<input type="hidden" name="hiddenCorreo" id="hiddenCorreo" />
		<input type="hidden" name="hiddenIdJefe" id="hiddenIdJefe" />
		<input type="hidden" name="hiddenRegistro" id="hiddenRegistro" />
		<input type="hidden" name="hiddenNombreCR" id="hiddenNombreCR" />
		<input type="hidden" name="hiddenDGA" id="hiddenDGA" />
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
											<td>Carga Manual</td>
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
											<legend class="Títulotabla"> Datos </legend>
											<table width="100%" border="0" cellpadding="2"
												cellspacing="2" class="tblCandidatoDer" aling="left">
												<thead>
													<tr>
														<th colspan="2" width="20%">IdUser:</th>
														<td class="tblSeguimientoI1" width="80%"><input
															type="text" name="idUser" id="idUser" maxlength="10"
															onKeyUp="enableAceptarButton();evitaEspeciales(this);" class="texto"
															style="width: 30%" /></td>
													</tr>
													<tr>
														<th colspan="2">Nombre:</th>
														<td class="tblSeguimientoI2"><input type="text"
															name="nombre" id="nombre" maxlength="50"
															onKeyUp="enableAceptarButton();evitaEspeciales(this);" class="texto"
															style="width: 98%" /></td>
													</tr>
													<tr>
														<th colspan="2">Apellido Paterno:</th>
														<td class="tblSeguimientoI1"><input type="text"
															name="paterno" id="paterno" maxlength="50"
															onKeyUp="enableAceptarButton();evitaEspeciales(this);" class="texto"
															style="width: 98%" /></td>
													</tr>
													<tr>
														<th colspan="2">Apellido Materno:</th>
														<td class="tblSeguimientoI2"><input type="text"
															name="materno" id="materno" maxlength="50"
															onKeyUp="enableAceptarButton();evitaEspeciales(this);" class="texto"
															style="width: 98%" /></td>
													</tr>
													<tr>
														<th colspan="2">Tipo Empleado:</th>
														<td class="tblSeguimientoI1"><input type="text"
															name="tipoEmpleado" id="tipoEmpleado" maxlength="1"
															onKeyUp="enableAceptarButton();evitaEspeciales(this);" class="texto"
															style="width: 5%" /></td>
													</tr>
													<tr>
														<th colspan="2">Edificio:</th>
														<td class="tblSeguimientoI2"><input type="text"
															name="ubicacion" id="ubicacion" maxlength="20"
															onKeyUp="enableAceptarButton();evitaEspeciales(this);" class="texto"
															style="width: 98%" /></td>
													</tr>
													<tr>
														<th colspan="2">Correo:</th>
														<td class="tblSeguimientoI1"><input type="text"
															name="correo" id="correo" maxlength="60"
															onKeyUp="enableAceptarButton();" class="texto"
															style="width: 98%" /></td>
													</tr>
													<tr>
														<th colspan="2">ID Jefe Inmediato:</th>
														<td class="tblSeguimientoI2"><input type="text"
															name="idJefe" id="idJefe" maxlength="10"
															onKeyUp="enableAceptarButton();evitaEspeciales(this);" class="texto"
															style="width: 30%" /></td>
													</tr>
													<tr>
														<th colspan="2">Registro:</th>
														<td class="tblSeguimientoI1"><input type="text"
															name="registro" id="registro" maxlength="10"
															onKeyUp="enableAceptarButton();evitaEspeciales(this);" class="texto"
															style="width: 30%" /></td>
													</tr>
													<tr>
														<th colspan="2">Nombre CR:</th>
														<td class="tblSeguimientoI2"><input type="text"
															name="nomCR" id="nomCR" maxlength="150"
															onKeyUp="enableAceptarButton();evitaEspeciales(this);" class="texto"
															style="width: 98%" /></td>
													</tr>
													<tr>
														<th colspan="2">DGA (LN3):</th>
														<td class="tblSeguimientoI1"><input type="text"
															name="dga" id="dga" maxlength="150"
															onKeyUp="enableAceptarButton();evitaEspeciales(this);" class="texto"
															style="width: 98%" /></td>
													</tr>
												</thead>
												<tbody>
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
													<input type="button" id='limpiar' class="botonCandidato"
														value='Limpiar' onclick="javascript:Limpia();" />&nbsp <input
														type="button" id='aceptar' class="botonCandidatoDsb"
														value='Aceptar' onclick="javascript:aceptarButton();"
														disabled />
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
	</form>
</body>
</html>
